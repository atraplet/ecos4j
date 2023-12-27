package com.ustermetrics.ecos4j;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemTest {

    private static long l;
    private static long[] q;
    private static long nExC;
    private static double[] gpr;
    private static long[] gjc;
    private static long[] gir;
    private static double[] apr;
    private static long[] ajc;
    private static long[] air;
    private static double[] c;
    private static double[] h;
    private static double[] b;

    @BeforeAll
    static void setup() {
        // Set up the portfolio optimization problem described
        // here https://www.ustermetrics.com/post/convex-optimization-in-java-with-project-panama.
        l = 5;
        q = new long[]{5};
        nExC = 0;
        gpr = new double[]{-1., -0.15, -1., -0.02, -0.198997487421324, -1., -0.1, -0.16583123951776996,
                -0.158113883008419, -1., -0.15, -0.10552897060221729, -0.17392527130926083, -0.16159714218895202, 1.,
                -1.};
        gjc = new long[]{0, 2, 5, 9, 14, 16};
        gir = new long[]{0, 6, 1, 6, 7, 2, 6, 7, 8, 3, 6, 7, 8, 9, 4, 5};
        apr = new double[]{1., 1., 1., 1., 0.};
        ajc = new long[]{0, 1, 2, 3, 4, 4};
        air = new long[]{0, 0, 0, 0};
        c = new double[]{-0.05, -0.06, -0.08, -0.06, 0.};
        h = new double[]{0., 0., 0., 0., 0.2, 0., 0., 0., 0., 0.};
        b = new double[]{1.};
    }

    @Test
    void solvePortfolioOptimizationProblemReturnsExpectedSolution() {
        try (val problem = new Problem()) {
            problem.setup(l, q, nExC, gpr, gjc, gir, c, h, apr, ajc, air, b);
            val solver = Solver.builder()
                    .verbose(false)
                    .build();

            val status = problem.solve(solver);
            assertEquals(Solver.Status.OPTIMAL, status);

            val tol = 1e-8;
            assertArrayEquals(new double[]{0.24879020572078372, 0.049684806182020855, 0.7015249845663684,
                    3.5308169265756875e-09, 0.19999999978141014}, problem.x(), tol);

            assertEquals(-0.07154259763411892, problem.pCost(), tol);
        }
    }

    @Test
    void solvePortfolioOptimizationProblemWithMaxitReturnsMaxitStatus() {
        try (val problem = new Problem()) {
            problem.setup(l, q, nExC, gpr, gjc, gir, c, h, apr, ajc, air, b);
            val solver = Solver.builder()
                    .maxIt(5)
                    .verbose(false)
                    .build();

            val status = problem.solve(solver);
            assertEquals(Solver.Status.MAXIT, status);
        }
    }

    @Test
    void solveProblemWithoutSetupThrowsException() {
        val exception = assertThrows(IllegalStateException.class, () -> {
            try (val problem = new Problem()) {
                problem.solve();
            }
        });

        assertEquals("Problem must be in stage setup", exception.getMessage());
    }
}
