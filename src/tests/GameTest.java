package tests;

import game.Game;
import model.Card;
import model.Deck;
import model.Replay;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void startGame() {
        int deckNum = 1;
        Game game = new Game(deckNum);

        assertNotNull(game.getDeckCards());
        // since hand and computer card size equal 6 the deck should have 46


        assertEquals(46, game.getRemainingCards(), "Deck should be 46 cards");
        assertEquals(5, game.getHand().size(), "hand size should be 5");

    }

    @Test
    void startGame0Decks() {
        int deckNum = 0;
        try {
            Game game = new Game(deckNum);
        } catch (IllegalArgumentException e) {
            assertEquals("Number of decks must be greater than 0", e.getMessage());
        }
    }

    @Test
    void getDeckCards() {
        int deckNum = 1;
        Game game = new Game(deckNum);

        List<Card> deckCards = game.getDeckCards();

        assertNotNull(deckCards, "Deck cards should not be null");
        assertEquals(46, deckCards.size(), "Deck should be 46 cards");
    }

    @Test
    void getReplayQueue() {
        int deckNum = 1;
        Game game = new Game(deckNum);

        Replay replay = game.getReplayQueue();
        assertNotNull(replay, "Replay queue should not be null.");
        assertTrue(replay.isEmpty(), "Replay queue should be empty initially");
    }
}