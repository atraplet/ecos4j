package com.ustermetrics.ecos4j;

import lombok.val;
import org.junit.jupiter.api.Test;

import static com.ustermetrics.ecos4j.bindings.ecos_h.ABSTOL;
import static com.ustermetrics.ecos4j.bindings.ecos_h.ECOS_OPTIMAL;
import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void solverWithCustomOptionsContainsCustomAndDefaultOptions() {
        val solver = Solver.builder()
                .feasTol(1.)
                .build();

        assertEquals(1., solver.getFeasTol(), 1e-8);
        assertEquals(ABSTOL(), solver.getAbsTol(), 1e-8);
    }

    @Test
    void solverWithInvalidArgumentThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class, () -> Solver.builder()
                .feasTol(-1.)
                .build());
        assertEquals("feasTol must be positive", exception.getMessage());
    }

    @Test
    void versionReturnsNonEmptyString() {
        val version = Solver.version();

        assertFalse(version.isEmpty());
    }

    @Test
    void statusValueOfEcosOptimalReturnsOptimal() {
        assertEquals(Solver.Status.OPTIMAL, Solver.Status.valueOf(ECOS_OPTIMAL()));
    }
}
