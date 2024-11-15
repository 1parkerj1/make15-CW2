package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final String[] RANKS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] SUITS = {"2","3","4","5","6", "7","8","9","10", "Jack","Queen","King","Ace"};

    private List<Card> cards;

    // constructor for creating a shuffled deck
    public Deck() {
        createDeck();
        shuffle();
    }

    // create a new non-shuffled deck
    public void createDeck() {
        cards = new ArrayList<>();
        for (String suit : RANKS) {
            for (String rank : SUITS) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        if (!cards.isEmpty()) {
            return cards.removeFirst();
        }
        return null;
    }

    public void resetDeck() {
        cards.clear();

        for (String suit : SUITS) {
            for (String rank : RANKS) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffle();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

}
