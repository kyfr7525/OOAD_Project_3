package com.OOAD;


// our addition of the Decorator Pattern

/*
 // TODO
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
//    int chanceToBuyExtras;
//    int buyHowMany;
//    double priceForExtras;

    public GamesDecorator(String name)
    {
        super(name);

    }

    public void buyExtras()
    {

    }
}

//class MonopolyDecorator extends GamesDecorator
//{
//    public MonopolyDecorator(Game game)
//    {
////        super(game);
//        this.game = game;
//    }
//}
