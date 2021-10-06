package com.OOAD;

// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3

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

    // adding genre to help with decorators in Cashier
    String genre;


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



    public double getGamePrice()
    {
        return this.price;
    }



}



/////////////////////////////////////////////////////////////////////////////////////////////////

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
        genre = "Kids";
    }
}


class Mousetrap extends KidsGame
{
    public Mousetrap(String name)
    {
        super(name);
    }
}

class Candyland extends KidsGame
{
    public Candyland(String name)
    {
        super(name);
    }
}

class ConnectFour extends KidsGame
{
    public ConnectFour(String name)
    {
        super(name);
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////



// Magic, Pokémon, Netrunner
class CardGame extends Game {
    public CardGame(String name) {
        super(name);
        height = Utility.rndFromRange(1,3);
        width = Utility.rndFromRange(4,6);
        length = Utility.rndFromRange(4,12);
        price = Utility.rndFromRange(20,40);
        genre = "Card";
    }
}


class Magic extends CardGame
{
    public Magic(String name)
    {
        super(name);
    }
}


class Pokemon extends CardGame
{
    public Pokemon(String name)
    {
        super(name);
    }
}


class Netrunner extends CardGame
{
    public Netrunner(String name)
    {
        super(name);
    }
}



/////////////////////////////////////////////////////////////////////////////////////////////////
// Monopoly, Clue, Life
class FamilyGame extends Game {
    public FamilyGame(String name) {
        super(name);
        height = Utility.rndFromRange(1,3);
        width = Utility.rndFromRange(6,12);
        length = Utility.rndFromRange(12,24);
        price = Utility.rndFromRange(8,20);

        genre = "Family";
    }
}



class Monopoly extends FamilyGame
{
    public Monopoly(String name)
    {
        super(name);
    }
}


class Clue extends FamilyGame
{
    public Clue(String name)
    {
        super(name);
    }
}


class Life extends FamilyGame
{
    public Life(String name)
    {
        super(name);
    }
}




/////////////////////////////////////////////////////////////////////////////////////////////////

// Catan, Risk, Gloomhaven
class BoardGame extends Game {
    public BoardGame(String name) {
        super(name);
        height = Utility.rndFromRange(3,6);
        width = Utility.rndFromRange(8,24);
        length = Utility.rndFromRange(12,36);
        price = Utility.rndFromRange(40,100);
        genre = "Board";
    }
}




class Catan extends BoardGame
{
    public Catan(String name)
    {
        super(name);
    }
}



class Risk extends BoardGame
{
    public Risk(String name)
    {
        super(name);
    }
}



class Gloomhaven extends BoardGame
{
    public Gloomhaven(String name)
    {
        super(name);
    }
}


