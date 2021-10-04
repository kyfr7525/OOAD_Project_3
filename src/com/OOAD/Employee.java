package com.OOAD;

public abstract class Employee {
    String name;
    public Employee(String name) {
        this.name = name;
    }
    public void arriveAtStore(int day) {
        System.out.println(name+" has arrived on day "+day);
    }
    public void leaveTheStore(int day) {
        System.out.println(name+" has left on day "+day);
    }
}

