package model;

public class Card {

    private String[] ranks = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private String[] suits = {"2","3","4","5","6", "7","8","9","10", "Jack","Queen","King","Ace"};

    private final String rank;
    private final String suit;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getRankVal() {
        return switch (rank) {
            case "Jack", "Queen", "King" -> 11;
            case "Ace" -> 12;
            default -> Integer.parseInt(rank);
        };
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
