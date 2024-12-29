package tests;

import model.Card;
import model.Deck;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void getName() {
        player.setName("Parker");
        assertEquals("Parker", player.getName(), "player name should be 'Parker'");
    }

    @Test
    void setName() {
        player.setName("Parker");
        assertEquals("Parker", player.getName(), "player name should be 'Parker'");

        Exception e = assertThrows(IllegalArgumentException.class, () -> player.setName(null));
        assertEquals("Player name cannot be null", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> player.setName(""));
        assertEquals("Player name cannot be null", e.getMessage());
    }

    @Test
    void getScore() {
        player.setScore(5);
        assertEquals(5, player.getScore(), "player score should be 10");
    }

    @Test
    void setScore() {
        player.setScore(3);
        assertEquals(3, player.getScore(), "player score should be 3");

        Exception e = assertThrows(IllegalArgumentException.class, () -> player.setScore(-1));
        assertEquals("Score cannot be negative", e.getMessage());
    }

    @Test
    void getHand() {
        List<Card> hand = player.getHand();
        assertNotNull(hand, "hand should not be null");
        assertTrue(hand.isEmpty(), "hand should be empty initially");
    }

    @Test
    void setHand() {
        Deck deck = new Deck(1);
        List<Card> cards = deck.getDeckCards().subList(0, 5);
        player.setHand(cards);

        assertEquals(5, player.getHand().size(), "hand size should be 5");
        assertEquals(cards, player.getHand());

        Exception e = assertThrows(IllegalArgumentException.class, () -> player.setHand(null));
        assertEquals("hand cannot be null", e.getMessage());
    }

    @Test
    void addCard() {
        Card card = new Card("Spades", "King");
        player.addCard(card, 0);
        assertEquals(1, player.getHand().size(), "hand should have 1 card after adding");
        assertEquals(card, player.getHand().getFirst(), "card should match the added card " + card);


    }

    @Test
    void dealHand() {
        Deck deck = new Deck(1);
        player.dealHand(deck);

        assertEquals(5, player.getHand().size(), "Hand size should be 5 after dealing");

        for (Card card : player.getHand()) {
            assertNotNull(card, "Card in hand should not be null");
        }

        assertEquals(47, deck.getDeckCards().size(), "Deck size should decrease by 5 after dealing a hand");
        assertEquals(5, player.getHand().stream().distinct().count(), "Hand should contain unique cards");
    }

    @Test
    void removeCard() {
        Card card = new Card("Diamonds", "Ace");
        player.addCard(card, 0);
        player.removeCard(card);

        assertTrue(player.getHand().isEmpty(), "Hand should be empty after removing the only card");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> player.removeCard(null));
        assertEquals("Card cannot be null", exception.getMessage());

        exception = assertThrows(IllegalStateException.class, () -> player.removeCard(card));
        assertEquals("Card not found in hand", exception.getMessage());
    }

    @Test
    void showHand() {
        int deckNum = 1;
        Deck deck = new Deck(deckNum);

        player.dealHand(deck);
        assertDoesNotThrow(() -> player.showHand());
    }
}