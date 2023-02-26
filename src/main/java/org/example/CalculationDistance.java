package org.example;

public class CalculationDistance {

    public static Double calculationDistance3(Double x1, Double y1,Double z1,Double x2, Double y2, Double z2){
            return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1,2));
    }
    public static Double calculationDistance2(Double x1, Double y1, Double x2, Double y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
