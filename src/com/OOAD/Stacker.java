package com.OOAD;

import java.util.ArrayList;
import java.util.Comparator;


// Using all code provided by Bruce Montgomery for Project 2 to have a solid base for project 3

// Array sorting approach from:
// https://howtodoinjava.com/java/collections/arraylist/arraylist-sort-objects-by-field/

public interface Stacker {

    static void stackByWidth(ArrayList<Game> games) {
        // Width is of a single game
        for (Game g:games) g.shelfMeasure = g.width;
        games.sort(new Sorter());
    }

    static void stackByHeight(ArrayList<Game> games) {
        // Height source is the whole stack of games
        for (Game g:games) g.shelfMeasure = g.height*g.countInventory;
        games.sort(new Sorter());
    }
}

class Sorter implements Comparator<Game> {
    @Override
    public int compare(Game o1, Game o2) {
        Integer o2m = o2.shelfMeasure;
        Integer o1m = o1.shelfMeasure;
        return o2m.compareTo(o1m);
    }
}




