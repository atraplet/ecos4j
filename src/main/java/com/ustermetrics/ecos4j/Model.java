package com.ustermetrics.ecos4j;

import com.ustermetrics.ecos4j.bindings.pwork;
import com.ustermetrics.ecos4j.bindings.settings;
import com.ustermetrics.ecos4j.bindings.stats;
import lombok.NonNull;
import lombok.val;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.util.Arrays;
import java.util.Optional;

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
        return ECOS_ver().getString(0);
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
     * @param q    the dimensions of the second-order cones.
     * @param nExC the number of exponential cones.
     * @param gpr  the sparse G matrix data (Column Compressed Storage CCS).
     * @param gjc  the sparse G matrix column index (CCS).
     * @param gir  the sparse G matrix row index (CCS).
     * @param c    the cost function weights.
     * @param h    the right-hand-side of the cone constraints.
     * @param apr  the (optional) sparse A matrix data (CCS).
     * @param ajc  the (optional) sparse A matrix column index (CCS).
     * @param air  the (optional) sparse A matrix row index (CCS).
     * @param b    the (optional) right-hand-side of the equalities.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public void setup(long l, long @NonNull [] q, long nExC, double @NonNull [] gpr, long @NonNull [] gjc,
                      long @NonNull [] gir, double @NonNull [] c, double @NonNull [] h, double[] apr, long[] ajc,
                      long[] air, double[] b) {
        checkState(stage == Stage.NEW, "Model must be in stage new");

        checkArgument(l >= 0, "dimension of the positive orthant l must be non-negative");
        val nCones = q.length;
        checkArgument(nCones == 0 || Arrays.stream(q).allMatch(d -> d > 0),
                "second-order cone dimensions q must be empty or each dimension q[i] must be positive");
        checkArgument(nExC >= 0, "number of exponential cones nExC must be non-negative");
        val nnzG = gpr.length;
        checkArgument(nnzG > 0, "number of non-zero elements in G (gpr.length) must be positive");
        checkArgument(nnzG == gir.length,
                "number of non-zero elements in G (gpr.length) must be equal to the number of elements in the row " +
                        "index of G (gir.length)");
        val nColsG = gjc.length - 1;
        checkArgument(nColsG > 0, "number of columns of G (gjc.length - 1) must be positive");
        n = c.length;
        checkArgument(n > 0, "number of variables x (c.length) must be positive");
        m = h.length;
        checkArgument(m > 0, "dimension of all cones (h.length) must be positive");
        checkArgument(m == l + Arrays.stream(q).sum() + 3 * nExC,
                "dimension of all cones (h.length) must be equal to the sum of the positive orthant dimension l, the " +
                        "second-order cone dimensions q[i], and three times the number of exponential cones 3 * nExC");
        checkArgument(nColsG == n, "number of columns of G (gjc.length - 1) must be equal to the number of variables " +
                "x (c.length)");

        checkArgument(apr != null && ajc != null && air != null && b != null || apr == null && ajc == null && air == null && b == null,
                "A (apr, ajc, air) and b must be supplied (all non-null) or omitted (all null) together");
        if (apr != null) {
            val nnzA = apr.length;
            checkArgument(nnzA > 0, "number of non-zero elements in A (apr.length) must be positive");
            checkArgument(nnzA == air.length,
                    "number of non-zero elements in A (apr.length) must be equal to the number of elements in the row" +
                            " index of A (air.length)");
            val nColsA = ajc.length - 1;
            checkArgument(nColsA > 0, "number of columns of A (ajc.length - 1) must be positive");
            p = b.length;
            checkArgument(p > 0, "number of equalities (b.length) must be positive");
            checkArgument(nColsA == n, "number of columns of A (ajc.length - 1) must be equal to the number of " +
                    "variables x (c.length)");
        } else {
            p = 0;
        }

        val qSeg = arena.allocateFrom(C_LONG_LONG, q);
        val gprSeg = arena.allocateFrom(C_DOUBLE, gpr);
        val gjcSeg = arena.allocateFrom(C_LONG_LONG, gjc);
        val girSeg = arena.allocateFrom(C_LONG_LONG, gir);
        val aprSeg = apr != null ? arena.allocateFrom(C_DOUBLE, apr) : NULL;
        val ajcSeg = ajc != null ? arena.allocateFrom(C_LONG_LONG, ajc) : NULL;
        val airSeg = air != null ? arena.allocateFrom(C_LONG_LONG, air) : NULL;
        val cSeg = arena.allocateFrom(C_DOUBLE, c);
        val hSeg = arena.allocateFrom(C_DOUBLE, h);
        val bSeg = b != null ? arena.allocateFrom(C_DOUBLE, b) : NULL;

        workSeg = ECOS_setup(n, m, p, l, nCones, qSeg, nExC, gprSeg, gjcSeg, girSeg, aprSeg, ajcSeg, airSeg, cSeg,
                hSeg, bSeg).reinterpret(pwork.sizeof(), arena, null);
        verify(!NULL.equals(workSeg));

        stgsSeg = pwork.stgs(workSeg).reinterpret(settings.sizeof(), arena, null);
        infoSeg = pwork.info(workSeg).reinterpret(stats.sizeof(), arena, null);

        stage = Stage.SETUP;
    }

    /**
     * Same as
     * {@link Model#setup(long l, long[] q, long nExC, double[] gpr, long[] gjc, long[] gir, double[] c, double[] h, double[] apr, long[] ajc, long[] air, double[] b)}
     * without equality constraint, i.e. {@code apr}, {@code ajc}, {@code air}, and {@code b} are empty arrays.
     *
     * @param l    the dimension of the positive orthant.
     * @param q    the dimensions of the second-order cones.
     * @param nExC the number of exponential cones.
     * @param gpr  the sparse G matrix data (Column Compressed Storage CCS).
     * @param gjc  the sparse G matrix column index (CCS).
     * @param gir  the sparse G matrix row index (CCS).
     * @param c    the cost function weights.
     * @param h    the right-hand-side of the cone constraints.
     */
    public void setup(long l, long @NonNull [] q, long nExC, double @NonNull [] gpr, long @NonNull [] gjc,
                      long @NonNull [] gir, double @NonNull [] c, double @NonNull [] h) {
        setup(l, q, nExC, gpr, gjc, gir, c, h, null, null, null, null);
    }

    /**
     * Sets the <a href="https://github.com/embotech/ecos">ECOS</a> solver options.
     * <p>
     * For {@code null} option values solver defaults are applied.
     *
     * @param parameters the parameter object for the solver options.
     */
    public void setParameters(@NonNull Parameters parameters) {
        checkState(stage != Stage.NEW, "Model must not be in stage new");

        Optional.ofNullable(parameters.feasTol())
                .ifPresent(feasTol -> settings.feastol(stgsSeg, feasTol));
        Optional.ofNullable(parameters.absTol())
                .ifPresent(absTol -> settings.abstol(stgsSeg, absTol));
        Optional.ofNullable(parameters.relTol())
                .ifPresent(relTol -> settings.reltol(stgsSeg, relTol));
        Optional.ofNullable(parameters.feasTolInacc())
                .ifPresent(feasTolInacc -> settings.feastol_inacc(stgsSeg, feasTolInacc));
        Optional.ofNullable(parameters.absTolInacc())
                .ifPresent(absTolInacc -> settings.abstol_inacc(stgsSeg, absTolInacc));
        Optional.ofNullable(parameters.relTolInacc())
                .ifPresent(relTolInacc -> settings.reltol_inacc(stgsSeg, relTolInacc));
        Optional.ofNullable(parameters.maxIt())
                .ifPresent(maxIt -> settings.maxit(stgsSeg, maxIt));
        Optional.ofNullable(parameters.nItRef())
                .ifPresent(nItRef -> settings.nitref(stgsSeg, nItRef));
        Optional.ofNullable(parameters.verbose())
                .ifPresent(verbose -> settings.verbose(stgsSeg, verbose ? 1 : 0));
    }

    /**
     * Optimizes this {@link Model} with the <a href="https://github.com/embotech/ecos">ECOS</a> solver.
     *
     * @return the solver status.
     */
    public Status optimize() {
        checkState(stage != Stage.NEW, "Model must not be in stage new");

        val status = ECOS_solve(workSeg);
        if (settings.verbose(stgsSeg) == 1) {
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
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.pcost(infoSeg);
    }

    /**
     * @return the dual objective of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double dCost() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.dcost(infoSeg);
    }

    /**
     * @return the primal residual on inequalities and equalities of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double pRes() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.pres(infoSeg);
    }

    /**
     * @return the dual residual of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double dRes() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.dres(infoSeg);
    }

    /**
     * @return the primal infeasibility measure of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double pInf() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.pinf(infoSeg);
    }

    /**
     * @return the dual infeasibility measure of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double dInf() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.dinf(infoSeg);
    }

    /**
     * @return the residual as primal infeasibility certificate of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double pInfRes() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.pinfres(infoSeg);
    }

    /**
     * @return the residual as dual infeasibility certificate of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double dInfRes() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.dinfres(infoSeg);
    }

    /**
     * @return the duality gap of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double gap() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.gap(infoSeg);
    }

    /**
     * @return the relative duality gap of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double relGap() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.relgap(infoSeg);
    }

    /**
     * @return the performed number of iterations until this {@link Model} was optimized.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public long iter() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return stats.iter(infoSeg);
    }

    /**
     * @return the primal variables of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double @NonNull [] x() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return pwork.x(workSeg).reinterpret(C_DOUBLE.byteSize() * n, arena, null).toArray(C_DOUBLE);
    }

    /**
     * @return the dual variables for the equality constraints of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double @NonNull [] y() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return pwork.y(workSeg).reinterpret(C_DOUBLE.byteSize() * p, arena, null).toArray(C_DOUBLE);
    }

    /**
     * @return the dual variables for the inequality constraints of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double @NonNull [] z() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return pwork.z(workSeg).reinterpret(C_DOUBLE.byteSize() * m, arena, null).toArray(C_DOUBLE);
    }

    /**
     * @return the slack variables of this optimized {@link Model}.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public double @NonNull [] s() {
        checkStageIsOptimizedAndStatusIsNotFatal();
        return pwork.s(workSeg).reinterpret(C_DOUBLE.byteSize() * m, arena, null).toArray(C_DOUBLE);
    }

    @Override
    public void close() {
        if (stage != Stage.NEW) {
            ECOS_cleanup(workSeg, 0);
        }
        arena.close();
    }

    private void checkStageIsOptimizedAndStatusIsNotFatal() {
        checkState(stage == Stage.OPTIMIZED, "Model must be in stage optimized");
        checkState(status != Status.FATAL, "Solver status must not be fatal");
    }

}
