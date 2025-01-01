package model;

/**
 * Card ADT
 */
public class Card {

    private static final String[] RANKS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] SUITS = {"2","3","4","5","6", "7","8","9","10", "Jack","Queen","King","Ace"};

    private final String rank;
    private final String suit;

    /**
     * card constructor to generate a new card with suit and rank
     *
     * @param suit - suit of the card
     * @param rank - rank of the card
     */
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * returns the suit of the card
     *
     * @return the suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * returns the rank of the card
     *
     * @return the rank of the card
     */
    public String getRank() {
        return rank;
    }

    /**
     * defines the numeric rank value of the card
     * normal cards = their value
     * face cards (picture cards) = 11
     * aces = 12
     *
     * @return the numeric rank value of the card type
     */
    public int getRankVal() {
        return switch (rank) {
            case "Jack", "Queen", "King" -> 11;
            case "Ace" -> 12;
            default -> Integer.parseInt(rank);
        };
    }

    /**
     * returns the string representation of a card
     * 'rank' of 'suit'
     *
     * @return the string representation of a card
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
