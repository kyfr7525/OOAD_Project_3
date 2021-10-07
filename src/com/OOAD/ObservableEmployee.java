package com.OOAD;

public class ObservableEmployee extends Employee
{
    Announcer announcer;

    public ObservableEmployee(String name, Announcer a)
    {
        super(name);
        this.announcer = a;

    }

    public void arriveAtStore(int day)
    {
//        System.out.println(name+" has arrived on day "+day);

        announcer.makeAnnouncement(name+" has arrived on day "+day);

    }

    public void leaveTheStore()
    {
//        System.out.println(name+" has left on day "+day);
        announcer.makeAnnouncement(name+" has left for the day");
    }
}
