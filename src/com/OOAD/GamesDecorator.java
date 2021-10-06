package com.OOAD;


// our addition of the Decorator Pattern

/*

    Use a Decorator pattern implementation to support the following optional purchases and additional cost for
    these games:
        a. Monopoly – someone buying a Monopoly game will optionally add 1 Special Tokens pack to their purchase 50% of the time
        b. For all Card Games – someone buying a Card game will optionally add 1 to 6 Special Cards to their purchase 20% of the time
        c. Mousetrap – someone buying a Mousetrap game will optionally add 1 to 2 Spare Parts to their purchase 30% of the time
        d. Gloomhaven – someone buying Gloomhaven will optionally add 1 to 4 Custom Miniatures to their purchase 20% of the time

    You can determine the cost of each added feature that the customer will pay the store.

*/




public abstract class GamesDecorator extends Game
{
    Game game;

    double priceForExtras;
    int numberOfPieces;

    public GamesDecorator(Game game)
    {
        super(game); // calling the constructor in game
        this.game = game;  // the game it is wrapping

    }

    @Override
    public double getGamePrice()
    {
        return this.game.getGamePrice() + (priceForExtras * numberOfPieces); // get the base price and add the cost of extra
    }
}


class MonopolyDecorator extends GamesDecorator
{

    public MonopolyDecorator(Game game, String name, double price, int numPieces)
    {
        super(game);
        this.game = game;
        this.priceForExtras = price;
        this.name = name;
        this.numberOfPieces = numPieces;
    }
}

class CardDecorator extends GamesDecorator
{

    public CardDecorator(Game game, String name, double price, int numPieces)
    {
        super(game);
        this.game = game;
        this.priceForExtras = price;
        this.name = name;
        this.numberOfPieces = numPieces;
    }
}

class MousetrapDecorator extends GamesDecorator
{

    public MousetrapDecorator(Game game, String name, double price, int numPieces)
    {
        super(game);
        this.game = game;
        this.priceForExtras = price;
        this.name = name;
        this.numberOfPieces = numPieces;
    }
}

class GloomDecorator extends GamesDecorator
{

    public GloomDecorator(Game game, String name, double price, int numPieces)
    {
        super(game);
        this.game = game;
        this.priceForExtras = price;
        this.name = name;
        this.numberOfPieces = numPieces;
    }
}