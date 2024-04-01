package com.ustermetrics.ecos4j;

import com.ustermetrics.ecos4j.bindings.ecos_h;
import com.ustermetrics.ecos4j.bindings.pwork;
import com.ustermetrics.ecos4j.bindings.settings;
import com.ustermetrics.ecos4j.bindings.stats;
import lombok.NonNull;
import lombok.val;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Verify.verify;
import static com.ustermetrics.ecos4j.bindings.ecos_h.*;
import static java.lang.foreign.MemorySegment.NULL;

/**
 * An optimization model which can be optimized with the <a href="https://github.com/embotech/ecos">ECOS</a> solver.
 * <p>
 * In order to control the lifecycle of native memory, {@link Model} implements the {@link AutoCloseable}
 * interface and should be used with the <i>try-with-resources</i> statement.
 */
public class Model implements AutoCloseable {

    private enum Stage {NEW, SETUP, OPTIMIZED}

    private static final String STAGE_OPTIMIZED_ERR_MSG = "Model must be in stage optimized";
    private static final String FATAL_ERR_MSG = "Solver status must not be fatal";

    private final Arena arena = Arena.ofConfined();
    private Stage stage = Stage.NEW;
    private long n;
    private long m;
    private long p;
    private MemorySegment workSeg;
    private MemorySegment stgsSeg;
    private MemorySegment infoSeg;
    private Status status;

    /**
     * @return the version of the <a href="https://github.com/embotech/ecos">ECOS</a> solver.
     */
    @NonNull
    public static String version() {
        return ecos_h.ECOS_ver().getUtf8String(0);
    }

    /**
     * Set up the {@link Model} data for a convex second-order cone program (SOCP) of type
     * <pre>
     * minimize        c'x
     * subject to      Gx + s = h
     *                 Ax = b
     *                 s in K
     * </pre>
     * where x are the primal variables, s are slack variables, c, G, h, A, and b are the model data, and K is the
     * convex cone. The cone K is the Cartesian product of the positive orthant cone, the second order cone, and the
     * exponential cone.
     *
     * @param l    the dimension of the positive orthant.
     * @param q    the dimensions of each cone.
     * @param nExC the number of exponential cones.
     * @param gpr  the sparse G matrix data (Column Compressed Storage CCS).
     * @param gjc  the sparse G matrix column index (CCS).
     * @param gir  the sparse G matrix row index (CCS).
     * @param c    the cost function weights.
     * @param h    the right-hand-side of the cone constraints.
     * @param apr  the sparse A matrix data (CCS).
     * @param ajc  the sparse A matrix column index (CCS).
     * @param air  the sparse A matrix row index (CCS).
     * @param b    the right-hand-side of the equalities.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public void setup(long l, long @NonNull [] q, long nExC, double @NonNull [] gpr, long @NonNull [] gjc,
                      long @NonNull [] gir, double @NonNull [] c, double @NonNull [] h, double @NonNull [] apr,
                      long @NonNull [] ajc, long @NonNull [] air, double @NonNull [] b) {
        checkState(stage == Stage.NEW, "Model must be in stage new");
        checkArgument(apr.length == 0 && ajc.length == 0 && air.length == 0 && b.length == 0
                || apr.length > 0 && ajc.length > 0 && air.length > 0 && b.length > 0);
        val nonNegErrMsg = "%s must be non-negative";
        checkArgument(l >= 0, nonNegErrMsg, "l");
        checkArgument(nExC >= 0, nonNegErrMsg, "nExC");

        n = c.length;
        m = h.length;
        p = b.length;
        val nCones = q.length;

        checkArgument(m == l + Arrays.stream(q).sum() + 3 * nExC,
                "Length of h must be equal to the sum of l, q, and 3*nExC");
        checkArgument(n == gjc.length - 1, "Length of c must be equal to the length of gjc minus one");
        checkArgument(ajc.length == 0 || ajc.length == n + 1,
                "ajc has zero length or must be equal to the length of c plus one");

        val qSeg = arena.allocateArray(C_LONG_LONG, q);
        val gprSeg = arena.allocateArray(C_DOUBLE, gpr);
        val gjcSeg = arena.allocateArray(C_LONG_LONG, gjc);
        val girSeg = arena.allocateArray(C_LONG_LONG, gir);
        val aprSeg = apr.length > 0 ? arena.allocateArray(C_DOUBLE, apr) : NULL;
        val ajcSeg = ajc.length > 0 ? arena.allocateArray(C_LONG_LONG, ajc) : NULL;
        val airSeg = air.length > 0 ? arena.allocateArray(C_LONG_LONG, air) : NULL;
        val cSeg = arena.allocateArray(C_DOUBLE, c);
        val hSeg = arena.allocateArray(C_DOUBLE, h);
        val bSeg = b.length > 0 ? arena.allocateArray(C_DOUBLE, b) : NULL;

        workSeg = ECOS_setup(n, m, p, l, nCones, qSeg, nExC, gprSeg, gjcSeg, girSeg, aprSeg, ajcSeg, airSeg, cSeg,
                hSeg, bSeg).reinterpret(pwork.sizeof(), arena, null);
        verify(!NULL.equals(workSeg));

        stgsSeg = pwork.stgs$get(workSeg).reinterpret(settings.sizeof(), arena, null);
        infoSeg = pwork.info$get(workSeg).reinterpret(stats.sizeof(), arena, null);

        stage = Stage.SETUP;
    }

    /**
     * Same as
     * {@link Model#setup(long l, long[] q, long nExC, double[] gpr, long[] gjc, long[] gir, double[] c, double[] h, double[] apr, long[] ajc, long[] air, double[] b)}
     * without equality constraint, i.e. {@code apr}, {@code ajc}, {@code air}, and {@code b} are empty arrays.
     *
     * @param l    the dimension of the positive orthant.
     * @param q    the dimensions of each cone.
     * @param nExC the number of exponential cones.
     * @param gpr  the sparse G matrix data (Column Compressed Storage CCS).
     * @param gjc  the sparse G matrix column index (CCS).
     * @param gir  the sparse G matrix row index (CCS).
     * @param c    the cost function weights.
     * @param h    the right-hand-side of the cone constraints.
     */
    public void setup(long l, long @NonNull [] q, long nExC, double @NonNull [] gpr, long @NonNull [] gjc,
                      long @NonNull [] gir, double @NonNull [] c, double @NonNull [] h) {
        setup(l, q, nExC, gpr, gjc, gir, c, h, new double[]{}, new long[]{}, new long[]{}, new double[]{});
    }

    /**
     * Sets the <a href="https://github.com/embotech/ecos">ECOS</a> solver options.
     *
     * @param parameters the parameter object for the solver options.
     */
    public void setParameters(@NonNull Parameters parameters) {
        checkState(stage != Stage.NEW, "Model must not be in stage new");

        settings.feastol$set(stgsSeg, parameters.getFeasTol());
        settings.abstol$set(stgsSeg, parameters.getAbsTol());
        settings.reltol$set(stgsSeg, parameters.getRelTol());
        settings.feastol_inacc$set(stgsSeg, parameters.getFeasTolInacc());
        settings.abstol_inacc$set(stgsSeg, parameters.getAbsTolInacc());
        settings.reltol_inacc$set(stgsSeg, parameters.getRelTolInacc());
        settings.maxit$set(stgsSeg, parameters.getMaxIt());
        settings.nitref$set(stgsSeg, parameters.getNItRef());
        settings.verbose$set(stgsSeg, parameters.isVerbose() ? 1 : 0);
    }

    /**
     * Optimizes this {@link Model} with the <a href="https://github.com/embotech/ecos">ECOS</a> solver.
     *
     * @return the solver status.
     */
    public Status optimize() {
        checkState(stage != Stage.NEW, "Model must not be in stage new");

        val status = ECOS_solve(workSeg);
        if (settings.verbose$get(stgsSeg) == 1) {
            fflush(NULL);
        }

        this.status = Status.valueOf((int) status);
        stage = Stage.OPTIMIZED;

        return this.status;
    }

    /**
     * Cleanup: free this {@link Model} native memory.
     */
    public void cleanup() {
        checkState(stage != Stage.NEW, "Model must not be in stage new");

        ECOS_cleanup(workSeg, 0);
        stage = Stage.NEW;
    }

    /**
     * @return the primal objective of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double pCost() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.pcost$get(infoSeg);
    }

    /**
     * @return the dual objective of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double dCost() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.dcost$get(infoSeg);
    }

    /**
     * @return the primal residual on inequalities and equalities of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double pRes() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.pres$get(infoSeg);
    }

    /**
     * @return the dual residual of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double dRes() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.dres$get(infoSeg);
    }

    /**
     * @return the primal infeasibility measure of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double pInf() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.pinf$get(infoSeg);
    }

    /**
     * @return the dual infeasibility measure of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double dInf() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.dinf$get(infoSeg);
    }

    /**
     * @return the residual as primal infeasibility certificate of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double pInfRes() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.pinfres$get(infoSeg);
    }

    /**
     * @return the residual as dual infeasibility certificate of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double dInfRes() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.dinfres$get(infoSeg);
    }

    /**
     * @return the duality gap of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double gap() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.gap$get(infoSeg);
    }

    /**
     * @return the relative duality gap of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double relGap() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.relgap$get(infoSeg);
    }

    /**
     * @return the performed number of iterations until this {@link Model} was optimized.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public long iter() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return stats.iter$get(infoSeg);
    }

    /**
     * @return the primal variables of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double @NonNull [] x() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return pwork.x$get(workSeg).reinterpret(C_DOUBLE.byteSize() * n, arena, null).toArray(C_DOUBLE);
    }

    /**
     * @return the dual variables for the equality constraints of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double @NonNull [] y() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return pwork.y$get(workSeg).reinterpret(C_DOUBLE.byteSize() * p, arena, null).toArray(C_DOUBLE);
    }

    /**
     * @return the dual variables for the inequality constraints of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double @NonNull [] z() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return pwork.z$get(workSeg).reinterpret(C_DOUBLE.byteSize() * m, arena, null).toArray(C_DOUBLE);
    }

    /**
     * @return the slack variables of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double @NonNull [] s() {
        checkState(stage == Stage.OPTIMIZED, STAGE_OPTIMIZED_ERR_MSG);
        checkState(status != Status.FATAL, FATAL_ERR_MSG);

        return pwork.s$get(workSeg).reinterpret(C_DOUBLE.byteSize() * m, arena, null).toArray(C_DOUBLE);
    }

    @Override
    public void close() {
        if (stage != Stage.NEW) {
            ECOS_cleanup(workSeg, 0);
        }
        arena.close();
    }

}
