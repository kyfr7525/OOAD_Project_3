package com.OOAD;

import java.util.ArrayList;

// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3


public class Store {

    double registerCash; //starts at $0
    int registerAdds; //starts at 0, incremented when we add $1000
    ArrayList<Cashier> cashiers;
    ArrayList<Game> games;
    Cashier activeCashier;
    ArrayList<Game> brokenGames;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
 Announcer announcer = new Announcer("Guy");


    double cookieFund; // starts at $0; this is how much money is spent on Gonger's cookies
    int numCookiesAvailable; // number of cookie packs available to sell
    Baker baker = new Baker("Gonger", announcer);
    CookieMonster cookieMonster = new CookieMonster("Cookie Monster");


///////////////////////////////////////////////////////////////////////////////////////////////////////////////




    public Store() {
        //initialize the cashiers
        cashiers = new ArrayList<Cashier>();
        cashiers.add(new Cashier("Burt",10, new stackByWidth(), announcer));
        cashiers.add(new Cashier("Ernie", 5, new stackByHeight(), announcer));




///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Bart is a new cashier, so we need to add him
        cashiers.add(new Cashier("Bart", 2, new stackByWidestWeird(), announcer));


///////////////////////////////////////////////////////////////////////////////////////////////////////////////





        //initialize the games
        games = new ArrayList<Game>();
        games.add(new KidsGame("Mousetrap"));
        games.add(new KidsGame("Candyland"));
        games.add(new KidsGame("Connect Four"));
        games.add(new CardGame("Magic"));
        games.add(new CardGame("Pokémon"));
        games.add(new CardGame("Netrunner"));
        games.add(new FamilyGame("Monopoly"));
        games.add(new FamilyGame("Clue"));
        games.add(new FamilyGame("Life"));
        games.add(new BoardGame("Catan"));
        games.add(new BoardGame("Risk"));
        games.add(new BoardGame("Gloomhaven"));

        //initialize a place for broken games
        brokenGames = new ArrayList<Game>();

    }

    public void startADay(int day) {


        // Announce Guy's arrival
        announcer.arriveAtStore(day);
        announcer.makeAnnouncement("");

        //pick a cashier for the day
        int n = Utility.rndFromRange(0,cashiers.size()-1);
        activeCashier = cashiers.get(n);

        //have the cashier do their things
        activeCashier.arriveAtStore(day);
        announcer.makeAnnouncement("");
        activeCashier.checkForNewGames(this);
//        announcer.makeAnnouncement("");
        activeCashier.countTheMoney(this);
        announcer.makeAnnouncement("");


        baker.cookieDelivery(this);

        activeCashier.countMoneyAgain(this);

        announcer.makeAnnouncement("");
        activeCashier.vacuumTheStore(this);
        announcer.makeAnnouncement("");
        activeCashier.stackTheGames(games);
//        announcer.makeAnnouncement("");
        activeCashier.openTheStore(this, baker, cookieMonster);    // adding baker as a parameter
        announcer.makeAnnouncement("");
        activeCashier.orderNewGames(this, baker);
        announcer.makeAnnouncement("");
        activeCashier.closeTheStore(day);



/////////////////////////////////////////////////////////////// v

        // Announce Guy's departure
        announcer.leaveTheStore();

/////////////////////////////////////////////////////////////// ^

    }

    public void summaryReport() {
        // per game type number in inventory, number sold, total sales
        announcer.makeAnnouncement("------------------------------------------------------------------------------------------------\n===Store Summary Report===");
        announcer.makeAnnouncement("Game sales:");
//        announcer.makeAnnouncement("Game\t\tInventory\t\tSold\t\tTotal $");
        for (Game g:games) {
            announcer.makeAnnouncement("Game: " + g.name + "\t\t Inventory: " + g.countInventory + "\t\t Sold: " + g.countSold + "\t\t Total Sales: " + Utility.asDollar(g.countSold*g.price));
        }
        // what's in damaged games
        announcer.makeAnnouncement("Broken games:");
        if (brokenGames.size()==0) announcer.makeAnnouncement("No games broken.");
        else {
//            announcer.makeAnnouncement("Game\t\tCount");
            for (Game g : brokenGames) {
                announcer.makeAnnouncement("Game: " + g.name+"\t\t Inventory: "+g.countInventory);
            }
        }

        announcer.makeAnnouncement("Total number of packages of cookies purchased: " + baker.totalCookiesSold);
        announcer.makeAnnouncement("Amount of money paid out to Gonger for his cookies: " + Utility.asDollar(baker.totalCookieMoney));
        announcer.makeAnnouncement("Number of cookie packages stolen by the Cookie Monster: " + cookieMonster.packsOfCookiesStolen);


        // final register count
        announcer.makeAnnouncement("Final register funds: "+Utility.asDollar(registerCash));
        // additions to register
        announcer.makeAnnouncement("Number of times money was added to register: "+Utility.asDollar(registerAdds*1000)+" ("+registerAdds+" adds)");
    }

    public void breakARandomGame() {
        //break a random game
        Game g;
        do {
            // find a random game with an inventory > 0
            int n = Utility.rndFromRange(1, games.size());
            g = games.get(n-1);
        } while (g.countInventory==0);
        //remove it from inventory
        g.countInventory -= 1;
        //place it in the brokenGames (if not there)
        Game foundbg = null;
        for (Game bg:brokenGames) {
            if (bg.name.equals(g.name)) {
                foundbg = bg;
                break;
            }
        }
        if (foundbg == null) {
            Game bg = new Game(g); //uses the copy constructor in Game
            bg.countInventory = 1;  //i'm going to use the countInventory to count broken games
            brokenGames.add(bg);
        }
        else {
            //The game is in the list of broken games, so just increase inventory count by 1
            foundbg.countInventory += 1;
        }
    }
}
