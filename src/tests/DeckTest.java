package tests;

import model.Card;
import model.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(1);
    }

    @Test
    void shuffleDeck() {
        List<Card> originalOrder = new ArrayList<>(deck.getDeckCards()); // Copy the original order
        deck.shuffleDeck();
        List<Card> shuffledOrder = deck.getDeckCards();

        // If the shuffled deck is equal to the original deck, shuffle again
        if (originalOrder.equals(shuffledOrder)) {
            deck.shuffleDeck();
            shuffledOrder = deck.getDeckCards();
        }

        // Verify that the shuffled order is not equal to the original order
        assertNotEquals(originalOrder, shuffledOrder, "Shuffling should change the order of cards");

        // Ensure that all cards are still present after shuffling
        assertEquals(originalOrder.size(), shuffledOrder.size(), "Shuffled deck should contain the same number of cards");
        assertTrue(originalOrder.containsAll(shuffledOrder), "Shuffled deck should contain the same cards as the original deck");
    }

    @Test
    void deal() {
        int init = deck.getRemainingCards();
        Card dealtCard = deck.deal();

        assertNotEquals(dealtCard, "dealt card should not be null");
        assertEquals(init - 1, deck.getRemainingCards(), "deck size should decrease by 1");

        for (int i = 0; i < init - 1; i++) {
            deck.deal();
        }
        assertTrue(deck.isEmpty(), "deck should be empty after dealing all cards");
        assertNull(deck.deal(), "dealing from an empty deck should return null");
    }

    @Test
    void testDeckNotEmptyInitially() {
        Deck deck = new Deck(1);
        assertFalse(deck.isEmpty(), "A newly created deck with cards should not be empty");
    }

    @Test
    void testDeckEmptyAfterDealingAllCards() {
        Deck deck = new Deck(1);

        while (!deck.isEmpty()) {
            deck.deal();
        }
        assertTrue(deck.isEmpty(), "The deck should be empty after dealing all cards");
    }

    @Test
    void getRemainingCards() {
        int initialSize = deck.getRemainingCards();
        deck.deal();

        assertEquals(initialSize - 1, deck.getRemainingCards(), "Remaining cards should decrease after dealing a card");

        while (deck.getRemainingCards() > 0) {
            deck.deal();
        }
        assertEquals(0, deck.getRemainingCards(), "Remaining cards should be 0 when the deck is empty");
    }

    @Test
    void getDeckCards() {
        List<Card> cards = deck.getDeckCards();

        assertEquals(52, cards.size(), "Deck should initially contain 52 cards");

        assertThrows(UnsupportedOperationException.class, () -> cards.remove(0), "Deck cards list should be unmodifiable");
    }

    @Test
    void clear() {
        deck.clear();

        assertTrue(deck.isEmpty(), "Deck should be empty after clearing");
        assertEquals(0, deck.getRemainingCards(), "Remaining cards should be 0 after clearing");
    }
}