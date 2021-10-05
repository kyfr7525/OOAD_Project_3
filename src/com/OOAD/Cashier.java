package com.OOAD;

import java.util.ArrayList;

// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3

public class Cashier extends Employee {

    public final static String STACK_BY_WIDTH = "width";
    public final static String STACK_BY_HEIGHT = "height";


///////////////////////////////////////////////////// v

    // adding this:
    public final static String BART_STACKS = "widest";

    int numCustomers;
    int numCookies; // number of cookies a customer wants to buy
    int chanceToBuyCookies; // chance that cookies can be bought by customer
    int customerGeneralChanceCookies; // chance of customers ACTUALLY buying cookies
    int gamesPurchased;
    int chanceToBuyGame;



/////////////////////////////////////////////////// ^


    int damageChance; //integer percentage chance of damage for vacuuming
    String stackMethod; // how does this cashier stack games


    public Cashier(String name, int dmgChance, String method) {
        super(name);
        damageChance = dmgChance;
        stackMethod = method;
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
        System.out.println(name+" counts the money.");
        System.out.println(name+" sees "+Utility.asDollar(store.registerCash)+" in the register");

        // if the money is less than 100, add 1000
        if (store.registerCash < 100) {
            store.registerCash += 1000;
            store.registerAdds += 1;
            System.out.println(name+" added $1000 to the register, now at $"+store.registerCash);
        }
    }

    public void vacuumTheStore(Store store) {
        System.out.println(name+" vacuums the store");
        int damageCheck = Utility.rndFromRange(1,100);
        if (damageCheck<=damageChance) {
            System.out.println(name+" broke a game!");
            store.breakARandomGame();
        }
        else {
            System.out.println(name+" did not break a game!");
        }
    }

    public void stackTheGames(ArrayList<Game> games) {

        // TODO: This really should be done with Strategy
        // I should assign a method to the Cashier when I initialize it
        // and avoid this if statement block

        String measure = "";
        if (this.stackMethod.equals(STACK_BY_WIDTH)) {
            Stacker.stackByWidth(games);
            measure = ", game width =";
        }
        if (this.stackMethod.equals(STACK_BY_HEIGHT)) {
            Stacker.stackByHeight(games);
            measure = ", pile height =";
        }



///////////////////////////////////////////////////////////////////////////////////////////// v

        // TODO implement using strategy
        // For now, lets get a working stacking method for Bart
        if (this.stackMethod.equals(BART_STACKS)) {
            Stacker.stackByWidestWeird(games);
            measure = ", game width =";
        }

/////////////////////////////////////////////////////////////////////////////////////////// ^




        int i = 0;
        for (Game g:games) {
            g.shelfPosition = i;
            System.out.println(name+" stacked "+g.name+" on shelf "+g.shelfPosition+measure+g.shelfMeasure);
            i += 1;
        }
    }




////////////////////////////////////////////////////////////////////////////////// v

/*
Modify the Open The Store Cashier event as follows.

The number of customers arriving each day is 1 plus a random variate from a Poisson distribution with mean 3
(this will result in random numbers from 1 to about 6 or 7 with a rare spike to 10 or so).

Before each normal customer decides to buy games (as in Project 2), they may decide to buy cookies.
If they buy cookies, they will decide to buy 1 to 3 cookies (randomly), adding the price of cookies to the Cash Register
money and reducing the store cookie inventory.   *** you determine the percent chance of buying cookies

If they buy cookies, the chance they will buy a game increases by 20%. If they want to buy,
for instance, 3 cookies, and only 2 remain, they will buy those remaining 2.

If there are no cookies for a customer to buy, the chance a customer will buy a game decreases by 10%.

The working Cashier should announce any cookie sales (via Guy).
*/


// *** should be able to access the poisson function in Utility.java with Utility.getPoissonRandom(double mean)





    public void openTheStore(Store store)
    {
        numCustomers = Utility.getPoissonRandom(3);  // does this already factor in the range specified in the requirements?
        chanceToBuyCookies = Utility.rndFromRange(1,100); // general chance of a customer being able to buy cookies that day

        // NEED TO ANNOUNCE CUSTOMER COUNT AND COOKIE SALES

        for (int i = 0; i < numCustomers; i++) // give *each* customer a chance of buying cookies
        {
            numCookies = Utility.rndFromRange(1,3); // number of cookies a customer wants to buy
            customerGeneralChanceCookies = Utility.rndFromRange(10,50); // the chance a customer ACTUALLY wants to buy cookies

            if (customerGeneralChanceCookies <= chanceToBuyCookies && store.numCookiesAvailable > 0) // if the chance to want to buy is <= to the chance of buying cookies
            {
                // buy some cookies
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

    public void orderNewGames(Store store) {
        double cost = 0;
        for (Game g:store.games) {
            if (g.countInventory == 0) {
                g.countOrdered = 3; // always order 3
                cost += g.countOrdered * g.price / 2; //pay for the order
                System.out.println(name+" ordering new copies of "+g.name);
            }
        }
        if (cost != 0) {
            store.registerCash -= cost;
            System.out.println(name+" ordered new games for "+Utility.asDollar(cost));
        }
        else System.out.println(name + " did not order any games");
    }

    public void closeTheStore(int day) {
        System.out.println(name + " is closing the store");
        leaveTheStore(day);
    }
}