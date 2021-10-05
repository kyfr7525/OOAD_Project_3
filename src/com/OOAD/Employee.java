package com.OOAD;

// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3

public abstract class Employee {
    String name;

    public Employee(String name)
    {
        this.name = name;
    }

    abstract public void arriveAtStore(int day);
    abstract public void leaveTheStore();


}

