package game;

import model.Card;
import model.Player;
import model.Deck;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Game {

    private Player player;
    private Deck deck;
    private int score, roundCount;
    private Card computerCard;
    private Queue<String> replayQueue;
    private String end;

    public Game() {
        deck = new Deck();
        player = new Player();
        player.setHand(deck);
        roundCount = 0;
        score = 0;
        replayQueue = new LinkedList<>();
    }

    public void startGame() {
        Scanner scan = new Scanner(System.in);

        while (!deck.isEmpty()) {
            if (!playRound(scan)) {
                end = "Player could not make a valid move!";
                break;
            }
        }

        if (deck.isEmpty()) {
            end = "The deck is empty!";
        }

        endGame(scan);
    }

    public boolean playRound(Scanner scan) {
        computerCard = deck.deal();
        if (computerCard == null) {
            end = "The deck is empty!";
            return false;
        }
        System.out.println("\nComputer's card: " + computerCard + "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Your hand: ");
        player.showHand(deck);

        Card playerCard = getPlayerChoice(scan);
        if (playerCard == null) {
            end = "Player quit the game!";
            return false;
        }

        roundCount++;
        return cardSelection(playerCard, computerCard);
    }

    private boolean cardSelection(Card playerCard, Card computerCard) {
        if (playerCard.getRankVal() + computerCard.getRankVal() == 15) {
            System.out.println("\nYou made 15 :)\n+1 point");
            score++;
            player.getHand().remove(playerCard);
            computerCard = deck.deal();
            player.addCard(deck.deal());
            return true;
        } else if (playerCard.getSuit().equals(computerCard.getSuit())) {
            System.out.println("\nPlayed same suit. Card swapped :)");
            computerCard = playerCard;
            player.getHand().remove(playerCard);
            player.addCard(deck.deal());
            return true;
        }
        end = "No valid moves!";
        return false;
    }

    private Card getPlayerChoice(Scanner scan) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("Select a card from your hand (1-5): ");
        int handSize = player.getHand().size() + 1;
        int choice = validateInput(scan, handSize);
        if (choice == 0) {
            return null;
        }
        return player.getHand().get(choice - 1);
    }

    private int validateInput(Scanner scan, int numOptions) {
        while (true) {
            try {
                int choice = Integer.parseInt(scan.nextLine());
                if (choice >= 0 && choice <= numOptions) {
                    return choice;
                }
                System.out.println("Invalid choice, please choose again!");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number between 0-5!");
            }
        }
    }

    private void endGame(Scanner scan) {
        roundCount = 0;
        System.out.println(end);
        System.out.println("━━━━━━━━━━━━\nGAME OVER :/\n━━━━━━━━━━━━\n");
        System.out.println("\nFinal score: " + player.getScore());

//        System.out.println("━━━━━━━━━━━━━━━━━━━\nWould you like to see the replay? (y/n)");
//        if (scan.nextLine().equalsIgnoreCase("Y")) {
//            showReplay();
//        }

    }
}

