package com.OOAD;

// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3

public abstract class Employee {
    String name;
    public Employee(String name) {
        this.name = name;
    }
    public void arriveAtStore(int day) {
        System.out.println(name+" has arrived on day "+day);
    }  // TODO call to announcer
    public void leaveTheStore(int day) {
        System.out.println(name+" has left on day "+day);
    }
}

