package com.ustermetrics.ecos4j;

import com.ustermetrics.ecos4j.bindings.ecos_h;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.val;

import static com.google.common.base.Preconditions.checkArgument;
import static com.ustermetrics.ecos4j.bindings.ecos_h.*;

/**
 * A wrapper class for <a href="https://github.com/embotech/ecos">ECOS</a> solver options, version, and status.
 */
@Getter
@Builder
public class Solver {

    @Builder.Default
    private double feasTol = FEASTOL();
    @Builder.Default
    private double absTol = ABSTOL();
    @Builder.Default
    private double relTol = RELTOL();
    @Builder.Default
    private double feasTolInacc = FTOL_INACC();
    @Builder.Default
    private double absTolInacc = ATOL_INACC();
    @Builder.Default
    private double relTolInacc = RTOL_INACC();
    @Builder.Default
    private int nItRef = NITREF();
    @Builder.Default
    private int maxIt = MAXIT();
    @Builder.Default
    private boolean verbose = VERBOSE() == 1;

    /**
     * Setting the solver options.
     *
     * @param feasTol      the primal/dual infeasibility tolerance.
     * @param absTol       the absolute tolerance on the duality gap.
     * @param relTol       the relative tolerance on the duality gap.
     * @param feasTolInacc the inaccurate solution feasibility tolerance.
     * @param absTolInacc  the inaccurate solution absolute tolerance.
     * @param relTolInacc  the inaccurate solution relative tolerance.
     * @param nItRef       the number of iterative refinement steps.
     * @param maxIt        the maximum number of iterations.
     * @param verbose      the verbose mode.
     * @see <a href="https://github.com/embotech/ecos">ECOS</a>
     */
    public Solver(double feasTol, double absTol, double relTol, double feasTolInacc, double absTolInacc,
                  double relTolInacc, int nItRef, int maxIt, boolean verbose) {
        val errMsg = "%s must be positive";
        checkArgument(feasTol > 0., errMsg, "feasTol");
        checkArgument(absTol > 0., errMsg, "absTol");
        checkArgument(relTol > 0., errMsg, "relTol");
        checkArgument(feasTolInacc > 0., errMsg, "feasTolInacc");
        checkArgument(absTolInacc > 0., errMsg, "absTolInacc");
        checkArgument(relTolInacc > 0., errMsg, "relTolInacc");
        checkArgument(nItRef > 0, errMsg, "nItRef");
        checkArgument(maxIt > 0, errMsg, "maxIt");

        this.feasTol = feasTol;
        this.absTol = absTol;
        this.relTol = relTol;
        this.feasTolInacc = feasTolInacc;
        this.absTolInacc = absTolInacc;
        this.relTolInacc = relTolInacc;
        this.maxIt = maxIt;
        this.nItRef = nItRef;
        this.verbose = verbose;
    }

    /**
     * @return the version of the <a href="https://github.com/embotech/ecos">ECOS</a> solver.
     */
    @NonNull
    public static String version() {
        return ecos_h.ECOS_ver().getUtf8String(0);
    }

    /**
     * The <a href="https://github.com/embotech/ecos">ECOS</a> solving status from solving a {@link Problem}.
     */
    public enum Status {
        OPTIMAL(ECOS_OPTIMAL()),
        PINF(ECOS_PINF()),
        DINF(ECOS_DINF()),
        INACC_OFFSET(ECOS_INACC_OFFSET()),
        MAXIT(ECOS_MAXIT()),
        NUMERICS(ECOS_NUMERICS()),
        OUTCONE(ECOS_OUTCONE()),
        SIGINT(ECOS_SIGINT()),
        FATAL(ECOS_FATAL());

        private final int status;

        Status(int status) {
            this.status = status;
        }

        int status() {
            return status;
        }

        static Status valueOf(int status) {
            for (var c : values()) {
                if (c.status() == status) {
                    return c;
                }
            }

            throw new IllegalArgumentException(STR."Unknown status \{status}");
        }
    }
}
