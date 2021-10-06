package com.OOAD;

// An original artwork by Bruce Montgomery = September 2021
// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3

public class Main {

    // How many days in a run?
    final static int NUMBER_OF_DAYS = 30;

    public static void main(String[] args) {

        Announcer announce = new Announcer("guy");
        //Call the simulator to run for that many days
        Simulator simulator = new Simulator();
//        System.out.println("Starting simulator for "+NUMBER_OF_DAYS+" days");     // original Bruce Montgomery code
        announce.makeAnnouncement("Starting the " + NUMBER_OF_DAYS + " day simulator");
        simulator.run(NUMBER_OF_DAYS);
    }
}
