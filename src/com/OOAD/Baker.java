package com.OOAD;






/*

Gonger arrives each day at the store after the Cashier Count the Money event.

Gonger will drop off packages of a dozen chocolate chip cookies
that will be added to the store’s new cookie inventory. Initially, Gonger will drop off 1 package of 12
cookies.

The money in the store Cash Register should be reduced by ½ the sale price of the cookies
being delivered (you decide the price), and that money should go into Gonger’s pocket.

Cookie events at the store may increase or decrease the number of cookie packages Gonger drops off (as described
below).

Gonger is an observable publisher, and will issue events for his arrival, the count of packages
dropped off, the money he receives, and his departure (which Guy will announce).

*/
public class Baker extends Employee
{
    int numPacksOfCookies;
    double cookiePrice = Utility.rndFromRange(5,10); // determine rand price to sell a pack of cookies to customers between $5 and $10

    public Baker(String name)
    {
        super(name);
    }


//    TODO determine the num of cookie packages and observable publisher stuff


    public void cookieDelivery(Store store)
    {
        // note: store.registerCash will control the amount of money in the register
        // pay Gonger for his cookies
        store.registerCash -= (numPacksOfCookies * (cookiePrice /2));
        store.numCookiesAvailable += numPacksOfCookies;
    }
}
