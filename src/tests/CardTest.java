package tests;

import model.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getSuit() {
        Card card = new Card("Hearts", "5");
        assertEquals("Hearts", card.getSuit(), "getSuit() should return the correct suit");
    }

    @Test
    void getRank() {
        Card card = new Card("Spades", "King");
        assertEquals("King", card.getRank(), "getRank() should return the correct rank");
    }

    @Test
    void getRankVal() {
        // Test number ranks
        Card card = new Card("Clubs", "7");
        assertEquals(7, card.getRankVal(), "getRankVal() should return 7 for rank '7'");

        // Test face cards
        Card jackCard = new Card("Diamonds", "Jack");
        assertEquals(11, jackCard.getRankVal(), "getRankVal() should return 11 for 'Jack'");

        Card queenCard = new Card("Hearts", "Queen");
        assertEquals(11, queenCard.getRankVal(), "getRankVal() should return 11 for 'Queen'");

        Card kingCard = new Card("Spades", "King");
        assertEquals(11, kingCard.getRankVal(), "getRankVal() should return 11 for 'King'");

        // Test Ace
        Card aceCard = new Card("Hearts", "Ace");
        assertEquals(12, aceCard.getRankVal(), "getRankVal() should return 12 for 'Ace'");

        // Test invalid rank format (should throw an exception)
        Card invalidCard = new Card("Clubs", "Invalid");
        assertThrows(NumberFormatException.class, invalidCard::getRankVal, "Invalid rank should throw NumberFormatException");
    }

    @Test
    void testToString() {
        Card card = new Card("Hearts", "10");
        assertEquals("10 of Hearts", card.toString(), "toString() should return '10 of Hearts'");

        Card aceCard = new Card("Spades", "Ace");
        assertEquals("Ace of Spades", aceCard.toString(), "toString() should return 'Ace of Spades'");
    }
}
