package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private static final int HAND_SIZE = 5;

    private List<Card> hand;
    private int score;
    private String name;


    // constructor to initialise the player with cards
    public Player() {
        this.hand = new ArrayList<>();
        this.score = 0;
        this.name = null;
    }


    // name getters/setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null");
        }
        this.name = name;
    }

    // score getters/setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        this.score = score;
    }

    // hand getters/setters
    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public void setHand(List<Card> hand) {
        if (hand == null) {
            throw new IllegalArgumentException("hand cannot be null");
        }
        this.hand = new ArrayList<>(hand);
    }

    // deal hand :)
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

    // add new card to player's hand
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

    // remove card from player's hand
    public void removeCard(Card playerCard) {
        if (playerCard == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }
        if(!hand.remove(playerCard)) {
            throw new IllegalStateException("Card not found in hand");
        }
    }

    // show players hand with nice animation (kinda)
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

    // checks if the player has any moves
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
