package com.OOAD;

// An original artwork by Bruce Montgomery = September 2021

public class Main {

    // How many days in a run?
    final static int NUMBER_OF_DAYS = 30;

    public static void main(String[] args) {

        //Call the simulator to run for that many days
        Simulator simulator = new Simulator();
        System.out.println("Starting simulator for "+NUMBER_OF_DAYS+" days");
        simulator.run(NUMBER_OF_DAYS);
    }
}
