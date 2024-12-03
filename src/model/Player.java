package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> hand;
    private int score, gamesWon;
    private String name;


    // constructor to initialise the player with cards
    public Player(String name) {
        this.hand = new ArrayList<>();
        this.score = 0;
        this.gamesWon = 0;
        this.name = name;
    }

    // name getters/setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // games won getters/setters
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    // score getters/setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // hand getters/setters
    public void setHand(Deck deck) {
        hand.clear();
        for (int i = 0; i < 5; i++) {
            Card card = deck.deal();
            if (card != null) {
                hand.add(card);
            }
        }
    }

    public List<Card> getHand() {
        return hand;
    }

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

    // add new card to player's hand
    public void addCard(Card newCard) {
        if (hand.size() < 5) {
            hand.add(newCard);
        }
    }

    public boolean hasMoves(Card computerCard) {
        boolean make15 = true;

        for (Card card : hand) {
            if (card.getRankVal() + computerCard.getRankVal() != 15) {
                make15 = false;
            }
        }

        return make15 || hand.stream().anyMatch(card -> card.getSuit().equals(computerCard.getSuit()));
    }


}
