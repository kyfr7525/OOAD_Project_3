package com.OOAD;

import java.text.NumberFormat;


// need to import random to use random functions https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
import java.util.Random;

// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3


public interface Utility {

    // making this utility function that can be used by saying Utility.rndFromRange(min,max)
    // https://www.baeldung.com/java-generating-random-numbers-in-range
    static int rndFromRange(int min, int max) {
        //returns a uniform inclusive random number from a given min and max range
        return (int) ((Math.random() * ((max+1) - min)) + min);
    }

    // another utility for printing out pretty $ values
    // https://stackoverflow.com/questions/13791409/java-format-double-value-as-dollar-amount
    static String asDollar(double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(value);
    }


//// source for Poisson distribution: https://stackoverflow.com/questions/9832919/generate-poisson-arrival-in-java
    static int getPoissonRandom(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

}
