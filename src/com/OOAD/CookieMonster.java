package com.OOAD;


/*
    When a customer visits, there is a 1% chance that the customer is the Cookie Monster in disguise.

    If there are any cookies in the store, the Cookie Monster will eat all the cookies in the store without
    paying for them and damage 1 to 6 games (randomly). If there are no cookies in the store, the Cookie
    Monster will sadly leave the store, taking no action. The working Cashier should announce any Cookie
    Monster events (via Guy). The logic for damaging games should be delegated and referred to by both
    this logic and the logic for Vacuum the Store.
*/

public class CookieMonster
{
    String name;
    int packsOfCookiesStolen;
    int gamesToBreak;

    public CookieMonster(String name)
    {
        this.name = name;

    }

    public void eatAllCookies(Store store, Announcer a)
    {
        a.makeAnnouncement("Oh no! The customer was the Cookie Monster in disguise!");

        if (store.numCookiesAvailable > 0)
        {
            a.makeAnnouncement("The Cookie Monster ate all " + store.numCookiesAvailable + " packages of cookies, so there are none left!");
            packsOfCookiesStolen = store.numCookiesAvailable;

            store.numCookiesAvailable = 0;

            gamesToBreak = Utility.rndFromRange(1,6); // the cookie monster will break 1-6 random games

            a.makeAnnouncement("While storming away after eating all of the cookies, the Cookie Monster broke " + gamesToBreak + " game(s)!");

            for (int i = 0; i < gamesToBreak; i++)
            {
//                a.makeAnnouncement("The Cookie Monster broke a game!");
                store.breakARandomGame();
            }

        }

        else // there are no cookies
        {
            a.makeAnnouncement("The Cookie Monster sadly left the store because there were no cookies left :(");
        }
    }
}
