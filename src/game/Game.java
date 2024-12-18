package game;

import model.Card;
import model.Player;
import model.Deck;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Game {

    private final Player player;
    private final Deck deck;
    private Card computerCard;

    private String endReason;
    private String playerName;
    private int score, roundCount, selectedCardPos, deckNum;
    private Queue<String> replayQueue;


    public Game(int deckNum) {
        if (deckNum <= 0) {
            throw new IllegalArgumentException("Number of decks must be greater than 0");
        }
        this.deck = new Deck(deckNum);
        this.player = new Player();
        this.roundCount = 0;

        // less important stuff
        score = 0;
        replayQueue = new LinkedList<>();

        initialiseGame();
    }

    private void initialiseGame() {
        // cards in game after decks init
        System.out.println("TEST: " + getRemainingCards());
        player.setHand(deck);
        computerCard = deck.deal();
        if(computerCard == null) {
            throw new IllegalStateException("Deck is empty");
        }
    }

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        while (!deck.isEmpty()) {
            if (!playRound(scan)) {
                endReason = "\nPlayer could not make a valid move!";
                break;
            }
        }
        if (deck.isEmpty()) {
            endReason = "\nThe deck is empty!";
        }
        endGame(scan);
    }

    public boolean playRound(Scanner scan) {
        if (computerCard == null) {
            endReason = "The deck is empty!";
            return false;
        }

//        if (roundCount == 2) {
//            System.out.println("TEST: deck # after 2 rounds: " + getRemainingCards());
//            System.out.println("TEST: deck after 2 rounds: " + getDeckCards());
//        }

        System.out.println("\nComputer's card: " + computerCard);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Your hand: ");
        player.showHand();

        Card playerCard = getPlayerChoice(scan);
        if (playerCard == null) {
            endReason = "\nPlayer quit the game!";
            return false;
        }

        roundCount++;
        System.out.println("TEST: end of round #" + roundCount + "; cards left: " + getRemainingCards());
        return cardSelection(playerCard, computerCard);
    }

    private boolean cardSelection(Card playerCard, Card computerCard) {
        if (playerCard.getRankVal() + computerCard.getRankVal() == 15) {
            System.out.println("\nYou made 15 :)\n+1 point");
            this.computerCard = deck.deal();
            player.setScore(score++);
            player.removeCard(playerCard);
            player.addCard(deck.deal(), selectedCardPos);
            return true;
        } else if (playerCard.getSuit().equals(computerCard.getSuit())) {
            System.out.println("\nPlayed same suit. Card swapped :)");
            this.computerCard = deck.deal();
            player.removeCard(playerCard);
            player.addCard(deck.deal(), selectedCardPos);
            return true;
        }
        endReason = "No valid moves!";
        return false;
    }

    private Card getPlayerChoice(Scanner scan) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("Select a card from your hand (1-5): ");
        int handSize = player.getHand().size();
        int choice = validateInput(scan, handSize);
        if (choice == 0) {
            return null;
        }
        selectedCardPos = choice - 1;
        return player.getHand().get(choice - 1);
    }

    // validate player input
    private int validateInput(Scanner scan, int numOptions) {
        while (true) {
            try {
                int choice = Integer.parseInt(scan.nextLine());
                if (choice >= 1 && choice <= numOptions) {
                    return choice;
                }
                System.out.print("\nInvalid choice, please choose again! ");
            } catch (NumberFormatException e) {
                System.out.print("\nInvalid input, please enter a number between 1-5! ");
            }
        }
    }

    // ends the game showing the final score
    private void endGame(Scanner scan) {
        roundCount = 0;
        System.out.println("\n━━━━━━━━━━━━━\nGAME OVER :/\n━━━━━━━━━━━━━");
        System.out.println(endReason);
        System.out.println("Final score: " + player.getScore());

//        System.out.println("━━━━━━━━━━━━━━━━━━━\nWould you like to see the replay? (y/n)");
//        if (scan.nextLine().equalsIgnoreCase("Y")) {
//            showReplay();
//        }

    }

    // test method for showing how many cards are currently in the deck
    public List<Card> getDeckCards() {
        return deck.getDeckCards();
    }

    public int getRemainingCards() {
        return getDeckCards().size();
    }

    public int getScore() {
        return this.player.getScore();
    }

}

