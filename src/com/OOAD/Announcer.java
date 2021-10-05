package com.OOAD;



/*
Add a new Employee type called an Announcer (named “Guy”).

Guy arrives each day just before the Cashier in the morning and is the last Employee to leave the store.

Guy must subscribe for events from all other Employees, each of which becomes an observable publisher (using the Observer pattern).

When an Employee (other than Guy) has an event to announce, they must publish the event to Guy.

Guy will then announce their event, such as: “Guy says: Ernie stacks 2 Magic games in shelf position 4
(pile height = 14”)”.

Guy is the only Employee capable of sending strings to System.out for output to
files or to the console.

Guy should also announce his own arrival at and departure from the store each
day. The uses of Observer should be clearly commented in code. You can use any Observer support in
Java you like, including writing your own. State in your README which Observer approach you used.
 */



public class Announcer extends Employee
{
    public Announcer(String name)
    {
        super(name);
    }

    public void Arrival(int day)
    {
        System.out.println("Guy is here first thing in the morning on day " + day + ".");
    }

    public void Leave()
    {
        System.out.println("Guy is the last to leave the store for the night.");
    }

    public void makeAnnouncement(String announce)
    {
        System.out.println(announce);
    }
}
