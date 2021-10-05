package com.OOAD;

// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3

public class Simulator {
    public void run(int days) {
        //initialize and instantiate the store for the simulation
        Store store = new Store();

        //run for n days
        for (int day = 1; day <= days; day++) {
//            System.out.println("--- Simulation starting Day "+day);
            System.out.println("-----------------\nDay " + day);
            store.startADay(day);
        }

        //display summary
        store.summaryReport();
    }
}
