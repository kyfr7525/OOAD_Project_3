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
public class Baker extends ObservableEmployee
{
    int numPacksOfCookies = 1;
    double cookiePrice = Utility.rndFromRange(5,15); // determine rand price to sell a pack of cookies to customers between $5 and $15
    double totalCookieMoney;
    int totalCookiesSold;

    public Baker(String name, Announcer announcer)
    {
        super(name, announcer);
    }




    public void cookieDelivery(Store store)
    {
        // note: store.registerCash will control the amount of money in the register
        // pay Gonger for his cookies
        totalCookieMoney += numPacksOfCookies * (cookiePrice /2);

        store.registerCash -= (numPacksOfCookies * (cookiePrice /2));
        store.numCookiesAvailable += numPacksOfCookies;
        totalCookiesSold += numPacksOfCookies;

        announcer.makeAnnouncement("Gonger is here to deliver cookies.");
        announcer.makeAnnouncement("Gonger is dropping off " + numPacksOfCookies + " packages of cookies.");
        announcer.makeAnnouncement("Gonger receives " + Utility.asDollar(numPacksOfCookies * (cookiePrice /2))+ " for his cookies.");
        announcer.makeAnnouncement("Gonger leaves for the day.");
    }
}
