package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private static final int HAND_SIZE = 5;

    private final List<Card> hand;
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

    // deal hand :)
    public void setHand(Deck deck) {
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
        try {
            if (hand.size() < HAND_SIZE && newCard != null) {
                hand.add(selectedPos, newCard);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        StringBuilder handString = new StringBuilder();
        for (int i = 0; i < HAND_SIZE; i++) {
            System.out.println((i + 1) + ") " + hand.get(i));
            handString.append(hand.get(i));
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
