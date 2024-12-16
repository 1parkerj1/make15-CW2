package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> hand;
    private int score;
    private String name;


    // constructor to initialise the player with cards
    public Player() {
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    // getters/setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // hand getters/setters
    public List<Card> getHand() {
        return hand;
    }

    public void setHand(Deck deck) {
        hand.clear();
        for (int i = 0; i < 5; i++) {
            Card card = deck.deal();
            if (card != null) {
                hand.add(card);
            }
        }
    }

    // add new card to player's hand
    public void addCard(Card newCard, int selectedPos) {
        try {
            if (hand.size() < 5 && newCard != null) {
                hand.add(selectedPos, newCard);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // remove card from player's hand
    public void removeCard(Card playerCard) {
        this.hand.remove(playerCard);
    }

    // show players hand with nice animation (kinda)
    public void showHand(Deck deck){
        StringBuilder handString = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
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
        for (Card card : hand) {
            if (card.getRankVal() + computerCard.getRankVal() == 15 || card.getSuit().equals(computerCard.getSuit())) {
                return true;
            }
        }
        return false;
    }

}
