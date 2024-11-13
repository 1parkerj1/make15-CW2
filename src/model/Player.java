package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> hand;
    private int score, gamesWon;
    private String name;



    // constructor to initialise the player with cards
    public Player(List<Card> initialHand, String name) {
        this.hand = new ArrayList<>(initialHand);
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

    // score getter
    public int getScore() {
        return score;
    }

    // shows the player's hand
    public void showHand() {
        String possessive;

        if (this.name.endsWith("s")) {
            possessive = this.name + "'";
        } else {
            possessive = this.name + "'s";
        }
        System.out.println(this.name + "'s" + hand);
    }

    // play a card that makes 15
    public Card playCard(Card computerCard, Deck deck) {
        for (Card card : hand) {
            if (card.getRankVal() + computerCard.getRankVal() == 15) {
                score++;
                hand.remove(card);
                addCard(deck.deal());
                return card;
            }
        }

        for (Card card : hand) {
            if (card.getSuit().equals(computerCard.getSuit())) {
                hand.remove(card);
                addCard(deck.deal());
                return card;
            }
        }

        return hand.isEmpty() ? null : hand.remove(0);
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
