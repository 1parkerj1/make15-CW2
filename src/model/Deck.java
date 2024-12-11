package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final String[] RANKS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] SUITS = {"2","3","4","5","6", "7","8","9","10", "Jack","Queen","King","Ace"};

    private List<Card> deckCards;

    // constructor for creating and shuffling the deck
    public Deck() {
        createDeck();
        Collections.shuffle(deckCards);
    }

    // create a new non-shuffled deck
    public void createDeck() {
        deckCards = new ArrayList<>();
        for (String suit : RANKS) {
            for (String rank : SUITS) {
                deckCards.add(new Card(suit, rank));
            }
        }
    }

    // removes the card from the top of the deck
    public Card deal() {
        if (!deckCards.isEmpty()) {
            return deckCards.removeFirst();
        }
        return null;
    }

    // reset and shuffle the deck
    public void resetDeck() {
        deckCards.clear();
        createDeck();
        Collections.shuffle(deckCards);
    }

    public boolean isEmpty() {
        return deckCards.isEmpty();
    }

    public int getRemainingCards() {
        return deckCards.size();
    }

}
