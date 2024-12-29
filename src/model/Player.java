package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * player class
 */
public class Player {

    private static final int HAND_SIZE = 5;

    private List<Card> hand;
    private int score;
    private String name;


    /**
     * constructs a new player instance with
     * empty hand, score of 0, no name
     */
    public Player() {
        this.hand = new ArrayList<>();
        this.score = 0;
        this.name = null;
    }


    // name getters/setters

    /**
     * returns the players name
     *
     * @return the players name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the players name
     *
     * @param name players name (must not be null)
     * @throws IllegalArgumentException if the name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null");
        }
        this.name = name;
    }

    // score getters/setters

    /**
     * returns the player's current score
     *
     * @return the player's current score
     */
    public int getScore() {
        return score;
    }

    /**
     * sets the player's score
     *
     * @param score the new score (must not be negative)
     * @throws IllegalArgumentException if the score is negative
     */
    public void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        this.score = score;
    }

    // hand getters/setters

    /**
     * return the player's current hand as an unmodifiable list
     *
     * @return player's current hand as an unmodifiable list
     */
    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    /**
     * set the player's hand
     *
     * @param hand the new hand (must not be null)
     * @throws IllegalArgumentException if the hand is null
     */
    public void setHand(List<Card> hand) {
        if (hand == null) {
            throw new IllegalArgumentException("hand cannot be null");
        }
        this.hand = new ArrayList<>(hand);
    }


    /**
     * deals a new hand to the player by drawing cards from the specified deck
     * clears the player's existing hand and fills it with new cards
     *
     * @param deck the deck to draw cards from (must not be null)
     * @throws IllegalArgumentException if the deck is null
     */
    public void dealHand(Deck deck) {
        if (deck == null) {
            throw new IllegalArgumentException("Deck cannot be null");
        }
        hand.clear();
        for (int i = 0; i < HAND_SIZE; i++) {
            Card card = deck.deal();
            if (card != null) {
                hand.add(card);
            }
        }
    }

    /**
     * adds a new card to the player's hand at a specified position
     *
     * @param newCard the card to add (must not be null)
     * @param selectedPos the position to insert the card
     * @throws IllegalArgumentException if the card is null
     * @throws IllegalStateException if the hand is already full
     * @throws IndexOutOfBoundsException if the position is out of bounds
     */
    public void addCard(Card newCard, int selectedPos) {
        if (newCard == null) {
            throw new IllegalArgumentException("Card cannot be null ");
        }
        if (hand.size() >= HAND_SIZE) {
            throw new IllegalStateException("Hand is already full ");
        }
        if (selectedPos < 0 || selectedPos > hand.size()) {
            throw new IndexOutOfBoundsException("Selected position is out of bounds ");
        }
        hand.add(selectedPos, newCard);
    }

    /**
     * removes a specified card from the player's hand
     *
     * @param playerCard the card to remove (must not be null and must exist in hand)
     * @throws IllegalArgumentException if the card is null
     * @throws IllegalStateException if the card is not found in the hand
     */
    public void removeCard(Card playerCard) {
        if (playerCard == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        if(!hand.remove(playerCard)) {
            throw new IllegalStateException("Card not found in hand");
        }
    }

    /**
     * display the player's hand with a delay between each card
     */
    public void showHand(){
        for (int i = 0; i < HAND_SIZE; i++) {
            System.out.println((i + 1) + ") " + hand.get(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * unused for now
     * checks if the player has any valid moves available given the computer's card
     * move is valid if it forms a sum of 15 with the computer's card and or matches it's suit
     *
     * @param computerCard the card played by the computer (must not be null)
     * @return true if the player has a valid move false otherwise
     * @throws IllegalArgumentException if the computer card is null
     */
    public boolean hasMoves(Card computerCard) {
        if (computerCard == null) {
            throw new IllegalArgumentException("Computer card cannot be null");
        }
        for (Card card : hand) {
            if (card.getRankVal() + computerCard.getRankVal() == 15 || card.getSuit().equals(computerCard.getSuit())) {
                return true;
            }
        }
        return false;
    }

}
