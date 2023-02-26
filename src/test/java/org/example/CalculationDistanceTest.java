package org.example;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CalculationDistanceTest {

    @Test
    void calculationDistance3() {
        assertEquals(1.0, CalculationDistance.calculationDistance3(1.0,0.1,1.1,-1.0,-2.1,0.5));
        assertEquals(1.0, CalculationDistance.calculationDistance3(0.0, 0.0,0.0,0.0,0.0,0.0));
        assertEquals(1.0, CalculationDistance.calculationDistance3(100.0,230.1,51.1,31.0,153.1,111.5));
        assertTimeout(Duration.ofMillis(50), ()->{CalculationDistance.calculationDistance3(100.0,230.1,51.1,31.0,153.1,111.5);});
    }

    @Test
    void calculationDistance2() {
        assertEquals(1.0, CalculationDistance.calculationDistance2(1.0,0.1,-1.0,-2.1));
        assertEquals(1.0, CalculationDistance.calculationDistance2(0.0, 0.0,0.0,0.0));
        assertEquals(1.0, CalculationDistance.calculationDistance2(230.1,51.1,31.0,111.5));
        assertTimeout(Duration.ofMillis(50), ()->{CalculationDistance.calculationDistance2(100.0,230.1,31.0,153.1);});

    }
}