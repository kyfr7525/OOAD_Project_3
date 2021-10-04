package com.OOAD;

public class Game {
    String name;
    double price;  //price in dollars - from $5 to $100
    int height; //game dimensions
    int width;
    int length;
    int countInventory;  //how many in inventory - starts at 3
    int shelfPosition; //where on shelf?
    public int shelfMeasure; //value to look at for sorting
    int countSold; //how many sold?
    int countOrdered; // for ordering new games transactions

    public Game(String name) {
        this.name = name;
        price = 1.0;
        height = 12;
        width = 12;
        length = 12;
        countInventory = 3;
        shelfPosition = 0;  // we'll treat 0 as unassigned
        countSold = 0;
        countOrdered = 0;
    }

    // I will need to make copies of game objects if they get damaged
    // for that, I'm going to use a copy constructor (instead of cloning)
    // https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java
    // and pick the stuff I want to copy in
    public Game(Game another) {
        this.name = another.name;
        this.price = another.price;
        this.height = another.height;
        this.width = another.width;
        this.length = another.length;
        this.countInventory = another.countInventory;
        this.shelfPosition = another.shelfPosition;
        this.shelfMeasure = another.shelfMeasure;
        this.countSold = another.countSold;
        this.countOrdered = another.countOrdered;
    }
}

// I'm using the subclasses to make different sizes and prices for types of games
// The board games are bigger and pricy, etc.

// Mousetrap, Candyland, Connect Four
class KidsGame extends Game {
    public KidsGame(String name) {
        super(name);
        height = Utility.rndFromRange(1,3);
        width = Utility.rndFromRange(6,12);
        length = Utility.rndFromRange(12,24);
        price = Utility.rndFromRange(8,20);
    }
}

// Magic, Pokémon, Netrunner
class CardGame extends Game {
    public CardGame(String name) {
        super(name);
        height = Utility.rndFromRange(1,3);
        width = Utility.rndFromRange(4,6);
        length = Utility.rndFromRange(4,12);
        price = Utility.rndFromRange(20,40);
    }
}

// Monopoly, Clue, Life
class FamilyGame extends Game {
    public FamilyGame(String name) {
        super(name);
        height = Utility.rndFromRange(1,3);
        width = Utility.rndFromRange(6,12);
        length = Utility.rndFromRange(12,24);
        price = Utility.rndFromRange(8,20);
    }
}

// Catan, Risk, Gloomhaven
class BoardGame extends Game {
    public BoardGame(String name) {
        super(name);
        height = Utility.rndFromRange(3,6);
        width = Utility.rndFromRange(8,24);
        length = Utility.rndFromRange(12,36);
        price = Utility.rndFromRange(40,100);
    }
}