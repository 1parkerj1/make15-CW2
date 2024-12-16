package game;

import model.Card;
import model.Player;
import model.Deck;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Game extends Deck{

    private Player player;
    private Deck deck;
    private Card computerCard;
    private String endReason;
    private int score, roundCount, selectedCardPos;
    private Queue<String> replayQueue;


    public Game() {
        deck = new Deck();
        System.out.println("\nTEST: DECK BEFORE: " + getDeckCards());
        System.out.println("TEST: DECK # BEFORE: " + getRemainingCards());
        player = new Player();
        player.setHand(deck);
        computerCard = deck.deal();

        // less important stuff
        roundCount = 0;
        score = 0;
        replayQueue = new LinkedList<>();
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
        player.showHand(deck);

        Card playerCard = getPlayerChoice(scan);
        if (playerCard == null) {
            endReason = "\nPlayer quit the game!";
            return false;
        }

        roundCount++;
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

    private void endGame(Scanner scan) {
        roundCount = 0;
        System.out.println(endReason);
        System.out.println("━━━━━━━━━━━━━\nGAME OVER :/\n━━━━━━━━━━━━━\n");
        System.out.println("Final score: " + player.getScore());

//        System.out.println("━━━━━━━━━━━━━━━━━━━\nWould you like to see the replay? (y/n)");
//        if (scan.nextLine().equalsIgnoreCase("Y")) {
//            showReplay();
//        }

    }

    public List<Card> getDeckCards() {
        return deck.getDeckCards();
    }

}

