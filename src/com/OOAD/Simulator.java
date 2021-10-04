package com.OOAD;

public class Simulator {
    public void run(int days) {
        //initialize and instantiate the store for the simulation
        Store store = new Store();

        //run for n days
        for (int day = 1; day <= days; day++) {
            System.out.println("--- Simulation starting Day "+day);
            store.startADay(day);
        }

        //display summary
        store.summaryReport();
    }
}
