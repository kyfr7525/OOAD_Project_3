package com.OOAD;

import java.util.ArrayList;

// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3

public class Cashier extends ObservableEmployee {

//    public final static String STACK_BY_WIDTH = "width";
//    public final static String STACK_BY_HEIGHT = "height";


///////////////////////////////////////////////////// v

    // adding this:
//    public final static String BART_STACKS = "widest";

    int numCustomers;
    int numCookiesToBuy; // number of cookies a customer wants to buy
    int chanceToBuyCookies; // chance that cookies can be bought by customer
    int customerGeneralChanceCookies; // chance of customers ACTUALLY buying cookies
    int gamesPurchased;
    int chanceToBuyGame;


    int buyMonopolyToken;
    int buySpecialCards;
    int buyMousetrapSpares;
    int buyGloomhavenMiniatures;
    int numPiecesBought;


/////////////////////////////////////////////////// ^


    int damageChance; //integer percentage chance of damage for vacuuming
//    String stackMethod;
    Stacker stackMethod; // how does this cashier stack games

    public Cashier(String name, int dmgChance, Stacker stack, Announcer a)
    {
        super(name,a);
        this.damageChance = dmgChance;
        this.stackMethod = stack;

    }

    public void checkForNewGames(Store store) {
        // Check for game arrivals, move them to inventory
        for (Game g:store.games) {
            if (g.countOrdered > 0) {
                g.countInventory += g.countOrdered;
                g.countOrdered = 0; // clear this after they're in inventory
            }
        }
    }

    public void countTheMoney(Store store) {
        //say what the starting cash is
        announcer.makeAnnouncement(name+" counts the money.");
        announcer.makeAnnouncement(name+" sees "+Utility.asDollar(store.registerCash)+" in the register");

        // if the money is less than 100, add 1000
        if (store.registerCash < 100) {
            store.registerCash += 1000;
            store.registerAdds += 1;
            announcer.makeAnnouncement(name+" added $1000 to the register, now at $"+store.registerCash);
        }
    }

    public void vacuumTheStore(Store store) {
        announcer.makeAnnouncement(name+" vacuums the store");
        int damageCheck = Utility.rndFromRange(1,100);
        if (damageCheck<=damageChance) {
            announcer.makeAnnouncement(name+" broke a game!");
            store.breakARandomGame();
        }
        else {
            announcer.makeAnnouncement(name+" did not break a game!");
        }
    }

    public void stackTheGames(ArrayList<Game> games) {

        // Strategy is implemented



        // I should assign a method to the Cashier when I initialize it
        // and avoid this if statement block

//        String measure = "";
//        if (this.stackMethod.equals(STACK_BY_WIDTH)) {
//            Stacker.stackByWidth(games);
//            measure = ", game width =";
//        }
//        if (this.stackMethod.equals(STACK_BY_HEIGHT)) {
//            Stacker.stackByHeight(games);
//            measure = ", pile height =";
//        }
//
//
//
/////////////////////////////////////////////////////////////////////////////////////////////// v
//
//        // For now, lets get a working stacking method for Bart
//        if (this.stackMethod.equals(BART_STACKS)) {
//            Stacker.stackByWidestWeird(games);
//            measure = ", game width =";
//        }
//
///////////////////////////////////////////////////////////////////////////////////////////// ^
//
//
//
//
//        int i = 0;
//        for (Game g:games) {
//            g.shelfPosition = i;
//            System.out.println(name+" stacked "+g.name+" on shelf "+g.shelfPosition+measure+g.shelfMeasure);
//            i += 1;
//        }

        this.stackMethod.Stack(games);


    }




////////////////////////////////////////////////////////////////////////////////// v

/*
5) Modify the Open The Store Cashier event as follows.

The number of customers arriving each day is 1 plus a random variate from a Poisson distribution with mean 3
(this will result in random numbers from 1 to about 6 or 7 with a rare spike to 10 or so).

Before each normal customer decides to buy games (as in Project 2), they may decide to buy cookies.
If they buy cookies, they will decide to buy 1 to 3 cookies (randomly), adding the price of cookies to the Cash Register
money and reducing the store cookie inventory.   *** you determine the percent chance of buying cookies

If they buy cookies, the chance they will buy a game increases by 20%. If they want to buy,
for instance, 3 cookies, and only 2 remain, they will buy those remaining 2.

If there are no cookies for a customer to buy, the chance a customer will buy a game decreases by 10%.

The working Cashier should announce any cookie sales (via Guy).








6) When a customer visits, there is a 1% chance that the customer is the Cookie Monster in disguise.

If there are any cookies in the store, the Cookie Monster will eat all the cookies in the store without
paying for them and damage 1 to 6 games (randomly).

If there are no cookies in the store, the Cookie Monster will sadly leave the store, taking no action.

The working Cashier should announce any Cookie Monster events (via Guy).

The logic for damaging games should be delegated and referred to by both this logic and the logic for Vacuum the Store.

*/


// *** should be able to access the poisson function in Utility.java with Utility.getPoissonRandom(double mean)



    public void openTheStore(Store store, Baker baker) // need baker as a parameter to be able to access baker methods
    {
        numCustomers = Utility.getPoissonRandom(3);  // does this already factor in the range specified in the requirements?

        chanceToBuyCookies = Utility.rndFromRange(1,100); // general chance of a customer being able to buy cookies that day


        announcer.makeAnnouncement(numCustomers + " customers have arrived at the store.");




        /// !!!!!!!!!!!!!! TODO IMPLEMENT THE COOKIE MONSTER



        for (int i = 1; i < numCustomers + 1; i++) // give *each* customer a chance of buying cookies and games
        {
            chanceToBuyGame = 20; // every normal customer always starts off with a 20% chance to buy a game
            gamesPurchased = 0;
            numCookiesToBuy = Utility.rndFromRange(1,3); // number of cookies a customer wants to buy
            customerGeneralChanceCookies = Utility.rndFromRange(10,50); // the chance a customer ACTUALLY wants to buy cookies

            if (store.numCookiesAvailable == 0) // regardless if they want to buy or not, they're upset they don't have the option
            {
                // there are no cookies for the customer to buy, which makes them angry; -10% chance of buying a game

                announcer.makeAnnouncement("Sadly, there are no cookies available for customers to buy.");

                chanceToBuyGame -= 10;
            }

                // if the chance to want to buy is <= to the chance of buying cookies AND there are cookies available
            else if (customerGeneralChanceCookies <= chanceToBuyCookies && store.numCookiesAvailable > 0)
            {
                // buy some cookies! customers are 20% more likely to buy a game


                if (numCookiesToBuy > store.numCookiesAvailable)
                {
                    numCookiesToBuy = store.numCookiesAvailable; // chance the num cookies that the customer wants to buy to what's left in store
                    announcer.makeAnnouncement("Customer " + i + " took the last " + store.numCookiesAvailable + " package(s) of cookies.");

                    store.numCookiesAvailable = 0;

                }

                else
                {
                    store.numCookiesAvailable -= numCookiesToBuy;
                }

                store.registerCash += (baker.cookiePrice * numCookiesToBuy);   // money made from cookie sales

                announcer.makeAnnouncement("Customer " + i + " bought " + numCookiesToBuy + " package(s) of cookies for a total of " + Utility.asDollar(baker.cookiePrice * numCookiesToBuy) + ".");

                chanceToBuyGame += 20;

            }



            // Now customers will look at the games




            // CREDIT TO BRUCE MONTGOMERY for original implementation for customers buying a game:
            for (Game g:store.games)
            {
                if (gamesPurchased <= 1)
                {   // two game purchase limit
                    if (Utility.rndFromRange(1,100) <= chanceToBuyGame)
                    {
                        //buying this game if it's on the shelf
                        if (g.countInventory > 0) {
                            gamesPurchased += 1;
                            store.registerCash += g.price;
                            g.countInventory -= 1;
                            g.countSold += 1;


                            announcer.makeAnnouncement(name + " sold " + g.name + " to customer " + i + " for " + Utility.asDollar(g.price));

                            /*
                                a. Monopoly – someone buying a Monopoly game will optionally add 1 Special Tokens pack to
                                    their purchase 50% of the time
                                b. For all Card Games – someone buying a Card game will optionally add 1 to 6 Special Cards to
                                    their purchase 20% of the time
                                c. Mousetrap – someone buying a Mousetrap game will optionally add 1 to 2 Spare Parts to their
                                    purchase 30% of the time
                                d. Gloomhaven – someone buying Gloomhaven will optionally add 1 to 4 Custom Miniatures to
                                    their purchase 20% of the time
                            */


                            if (g.name == "Monopoly")
                            {
                                buyMonopolyToken = Utility.rndFromRange(1,100);

                                if (buyMonopolyToken <= 50)
                                {
                                    new MonopolyDecorator(new Monopoly("Monopoly"), "Monopoly Special Token", Utility.rndFromRange(2,5), 1);
                                    announcer.makeAnnouncement("Customer " + i + " also decided to purchase a special token for Monopoly!");

                                    // announcer.makeAnnouncement("Customer " + i + " also decided to purchase a special token for Monopoly for a total of " + MonopolyDecorator.getGamePrice());

                                }

                            }

                            else if (g.genre == "Card")     // is Card good?
                            {
                                buySpecialCards = Utility.rndFromRange(1,100);

                                numPiecesBought = Utility.rndFromRange(1,6);

                                if (buySpecialCards <= 20)
                                {
                                    new CardDecorator(new CardGame(g.name), "Special Cards", Utility.rndFromRange(4,10), numPiecesBought);
                                    announcer.makeAnnouncement("Customer " + i + " also decided to purchase " + numPiecesBought + " Special Card(s)!");
                                }
                            }


                            else if (g.name == "Mousetrap")
                            {
                                buyMousetrapSpares = Utility.rndFromRange(1,100);

                                numPiecesBought = Utility.rndFromRange(1,2);

                                if (buyMousetrapSpares <= 30)
                                {
                                    new MousetrapDecorator(new Mousetrap("Mousetrap"), "Mousetrap Spare Parts", Utility.rndFromRange(5,10), numPiecesBought);
                                    announcer.makeAnnouncement("Customer " + i + " also decided to purchase " + numPiecesBought + " Spare Part(s)!");
                                }
                            }


                            else if (g.name == "Gloomhaven")
                            {
                                buyGloomhavenMiniatures = Utility.rndFromRange(1,100);

                                numPiecesBought = Utility.rndFromRange(1,4);

                                if (buyGloomhavenMiniatures <= 20)
                                {
                                    new GloomDecorator(new Gloomhaven("Gloomhaven"), "Gloomhaven Custom Miniatures", Utility.rndFromRange(10,20),numPiecesBought);
                                    announcer.makeAnnouncement("Customer " + i + " also decided to purchase " + numPiecesBought + " Custom Miniature(s)!");
                                }
                            }



                        }
                    }
                }

                    chanceToBuyGame -= 2;
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////////// ^

    // the following openTheStore function is the professor's original code that we decided to preserve

//    public void openTheStore(Store store) {
//        int customerCount = Utility.rndFromRange(0,4);
//        System.out.println(name+" sees "+customerCount+" customers coming in the store!");
//        for (int c = 1; c <= customerCount ; c++) {
//            int purchaseCount = 0;
//            int chanceOfPurchase = 20;
//            for (Game g:store.games) {
//                if (purchaseCount <= 1) {   // two game purchase limit
//                    if (Utility.rndFromRange(1,100)<=chanceOfPurchase) {
//                        //buying this game if it's on the shelf
//                        if (g.countInventory > 0) {
//                            purchaseCount += 1;
//                            store.registerCash += g.price;
//                            g.countInventory -= 1;
//                            g.countSold += 1;
//                            System.out.println(name + " sold " + g.name + " to customer " + c + " for " + Utility.asDollar(g.price));
//                        }
//                    }
//                    chanceOfPurchase -= 2;
//                }
//            }
//        }
//    }




/*

7) Modify the Order New Games Cashier event as follows. If there are no cookies in the store at the end of
a day, increase the number of cookie packages Gonger delivers the next day by 1. If there are cookies in
the store at the end of the day, have Gonger deliver 1 less package of cookies the next day (with a
minimum of 1 package delivered).

*/

    public void orderNewGames(Store store, Baker baker)
    {
        double cost = 0;
        for (Game g:store.games) {
            if (g.countInventory == 0) {
                g.countOrdered = 3; // always order 3
                cost += g.countOrdered * g.price / 2; //pay for the order
                announcer.makeAnnouncement(name+" ordering new copies of "+g.name);
            }
        }
        if (cost != 0) {
            store.registerCash -= cost;
            announcer.makeAnnouncement(name+" ordered new games for "+Utility.asDollar(cost));
        }
        else announcer.makeAnnouncement(name + " did not order any games");

/////////////////////////////////////////////////////////////////// v
        if (store.numCookiesAvailable == 0)
        {
            baker.numPacksOfCookies += 1; // Gonger needs to increase the number of cookie packages that need to be delivered
        }

        else // there are cookies available
        {
            if (baker.numPacksOfCookies > 1) // if Gonger normally drops off more than one pack of cookies, and there are still cookies available by the end of the day, then he needs to deliver 1 less pack the next day
            {
                baker.numPacksOfCookies -= 1;
            }
        }


/////////////////////////////////////////////////////////////////// ^

    }

    public void closeTheStore(int day) {
        announcer.makeAnnouncement(name + " is closing the store");
        leaveTheStore();
    }
}