package com.OOAD;

import java.util.ArrayList;

public class Cashier extends Employee {

    public final static String STACK_BY_WIDTH = "width";
    public final static String STACK_BY_HEIGHT = "height";

    int damageChance; //integer percentage chance of damage for vacuuming
    String stackMethod; // how does this cashier stack games

    public Cashier(String name) {
        super(name);
        damageChance = 0;
        stackMethod = STACK_BY_WIDTH;
    }

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
            measure = ", game width=";
        }
        if (this.stackMethod.equals(STACK_BY_HEIGHT)) {
            Stacker.stackByHeight(games);
            measure = ", pile height=";
        }
        int i = 0;
        for (Game g:games) {
            g.shelfPosition = i;
            System.out.println(name+" stacked "+g.name+" on shelf "+g.shelfPosition+measure+g.shelfMeasure);
            i += 1;
        }
    }

    public void openTheStore(Store store) {
        int customerCount = Utility.rndFromRange(0,4);
        System.out.println(name+" sees "+customerCount+" customers coming in the store!");
        for (int c = 1; c <= customerCount ; c++) {
            int purchaseCount = 0;
            int chanceOfPurchase = 20;
            for (Game g:store.games) {
                if (purchaseCount <= 1) {   // two game purchase limit
                    if (Utility.rndFromRange(1,100)<=chanceOfPurchase) {
                        //buying this game if it's on the shelf
                        if (g.countInventory > 0) {
                            purchaseCount += 1;
                            store.registerCash += g.price;
                            g.countInventory -= 1;
                            g.countSold += 1;
                            System.out.println(name + " sold " + g.name + " to customer " + c + " for " + Utility.asDollar(g.price));
                        }
                    }
                    chanceOfPurchase -= 2;
                }
            }
        }
    }

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