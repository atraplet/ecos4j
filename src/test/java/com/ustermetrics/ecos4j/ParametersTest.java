package com.ustermetrics.ecos4j;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParametersTest {

    @Test
    void buildParametersWithCustomOptionsReturnsParametersWithCustomOptions() {
        val parameters = Parameters.builder()
                .feasTol(1.)
                .absTol(1.)
                .relTol(1.)
                .feasTolInacc(1.)
                .absTolInacc(1.)
                .relTolInacc(1.)
                .nItRef(1)
                .maxIt(1)
                .verbose(true)
                .build();

        val tol = 1e-8;
        assertEquals(1., parameters.getFeasTol(), tol);
        assertEquals(1., parameters.getAbsTol(), tol);
        assertEquals(1., parameters.getRelTol(), tol);
        assertEquals(1., parameters.getFeasTolInacc(), tol);
        assertEquals(1., parameters.getAbsTolInacc(), tol);
        assertEquals(1., parameters.getRelTolInacc(), tol);
        assertEquals(1, parameters.getNItRef());
        assertEquals(1, parameters.getMaxIt());
        assertTrue(parameters.isVerbose());
    }

    @Test
    void buildParametersWithInvalidFeasTolThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class, () -> Parameters.builder()
                .feasTol(-1.)
                .build());

        assertEquals("feasTol must be positive", exception.getMessage());
    }

    @Test
    void buildParametersWithInvalidAbsTolThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class, () -> Parameters.builder()
                .absTol(-1.)
                .build());

        assertEquals("absTol must be positive", exception.getMessage());
    }

    @Test
    void buildParametersWithInvalidRelTolThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class, () -> Parameters.builder()
                .relTol(-1.)
                .build());

        assertEquals("relTol must be positive", exception.getMessage());
    }

    @Test
    void buildParametersWithInvalidFeasTolInaccThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class, () -> Parameters.builder()
                .feasTolInacc(-1.)
                .build());

        assertEquals("feasTolInacc must be positive", exception.getMessage());
    }

    @Test
    void buildParametersWithInvalidAbsTolInaccThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class, () -> Parameters.builder()
                .absTolInacc(-1.)
                .build());

        assertEquals("absTolInacc must be positive", exception.getMessage());
    }

    @Test
    void buildParametersWithInvalidRelTolInaccThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class, () -> Parameters.builder()
                .relTolInacc(-1.)
                .build());

        assertEquals("relTolInacc must be positive", exception.getMessage());
    }

    @Test
    void buildParametersWithInvalidNItRefThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class, () -> Parameters.builder()
                .nItRef(-1)
                .build());

        assertEquals("nItRef must be positive", exception.getMessage());
    }

    @Test
    void buildParametersWithInvalidMaxItThrowsException() {
        val exception = assertThrowsExactly(IllegalArgumentException.class, () -> Parameters.builder()
                .maxIt(-1)
                .build());

        assertEquals("maxIt must be positive", exception.getMessage());
    }

}
