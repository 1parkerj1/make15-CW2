package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final String[] RANKS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] SUITS = {"2","3","4","5","6", "7","8","9","10", "Jack","Queen","King","Ace"};

    private final List<Card> deckCards;

    // constructor for creating and shuffling the deck
    public Deck(int deckNum) {
        if (deckNum <= 0) {
            throw new IllegalArgumentException("Number of decks must be greater than 0");
        }
        this.deckCards = new ArrayList<>();
        createDeck(deckNum);
        Collections.shuffle(deckCards);
    }

    // create a new non-shuffled deck
    private List<Card> createDeck(int deckNum) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < deckNum; i++) {
            for (String suit : RANKS) {
                for (String rank : SUITS) {
                    deckCards.add(new Card(suit, rank));
                }
            }
        }
        return cards;
    }

    // shuffle deck
    public void shuffleDeck() {
        Collections.shuffle(deckCards);
    }

    // removes the card from the top of the deck
    public Card deal() {
        if (!isEmpty()) {
            return deckCards.removeFirst();
        }
        return null;
    }

    // reset and shuffle the deck
    public void resetDeck(int deckNum) {
        if (deckNum <= 0) {
            throw new IllegalArgumentException("Number of decks must be greater than 0");
        }
        deckCards.clear();
        createDeck(deckNum);
        shuffleDeck();
    }

    public boolean isEmpty() {
        return deckCards.isEmpty();
    }

    public int getRemainingCards() {
        return deckCards.size();
    }

    public List<Card> getDeckCards() {
        return Collections.unmodifiableList(deckCards);
    }
}
