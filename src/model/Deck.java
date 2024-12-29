package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck ADT
 */
public class Deck {

    private static final String[] RANKS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] SUITS = {"2","3","4","5","6", "7","8","9","10", "Jack","Queen","King","Ace"};

    private final List<Card> deckCards;

    /**
     * constructs a deck with the specified number of decks
     * creates and shuffles the deck
     *
     * @param deckNum the number of standard decks to include in this deck
     * @throws IllegalArgumentException if deckNum is less than or equal to 0
     */
    public Deck(int deckNum) {
        if (deckNum <= 0) {
            throw new IllegalArgumentException("Number of decks must be greater than 0");
        }
        this.deckCards = new ArrayList<>();
        createDeck(deckNum);
        Collections.shuffle(deckCards);
    }

    /**
     * creates a new non-shuffled deck with the specified number of decks
     *
     * @param deckNum the number of standard decks to include (standard deck = 52)
     * @throws IllegalArgumentException if deckNum is less than or equal to 0
     */
    private void createDeck(int deckNum) {
        if (deckNum <= 0) {
            throw new IllegalArgumentException("Number of decks must be greater than 0");
        }
        for (int i = 0; i < deckNum; i++) {
            for (String suit : RANKS) {
                for (String rank : SUITS) {
                    deckCards.add(new Card(suit, rank));
                }
            }
        }
    }

    /**
     * shuffles the cards in the deck randomly
     */
    public void shuffleDeck() {
        Collections.shuffle(deckCards);
    }

    /**
     * deals the card from the top of the deck, removing it from the deck
     *
     * @return the card dealt or null if the deck is empty
     */
    public Card deal() {
        if (!isEmpty()) {
            return deckCards.removeFirst();
        }
        return null;
    }

    /**
     * resets the deck to its original state with the specified number of decks
     * clears the current deck, recreates it and shuffles it
     *
     * @param deckNum the number of standard decks to include (standard deck = 52)
     * @throws IllegalArgumentException if deckNum is less than or equal to 0
     */
    public void resetDeck(int deckNum) {
        if (deckNum <= 0) {
            throw new IllegalArgumentException("Number of decks must be greater than 0");
        }
        deckCards.clear();
        createDeck(deckNum);
        shuffleDeck();
    }

    /**
     * checks if the deck is empty
     *
     * @return true if the deck is empty false otherwise
     */
    public boolean isEmpty() {
        return deckCards.isEmpty();
    }

    /**
     * returns the number of cards remaining in the deck
     *
     * @return the number of cards remaining
     */
    public int getRemainingCards() {
        return deckCards.size();
    }

    /**
     * returns an unmodifiable view of cards in the deck
     *
     * @return an unmodifiable list of the cards in the deck
     */
    public List<Card> getDeckCards() {
        return Collections.unmodifiableList(deckCards);
    }

    /**
     * clears all cards from the deck
     */
    public void clear() {
        deckCards.clear();
    }
}
