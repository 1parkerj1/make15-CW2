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
    private int score, roundCount, selectedCardPos;
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
                end = "\nPlayer could not make a valid move!";
                break;
            }
        }

        if (deck.isEmpty()) {
            end = "\nThe deck is empty!";
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
            end = "\nPlayer quit the game!";
            return false;
        }

        roundCount++;
        return cardSelection(playerCard, computerCard);
    }

    private boolean cardSelection(Card playerCard, Card computerCard) {
        if (playerCard.getRankVal() + computerCard.getRankVal() == 15) {
            System.out.println("\nYou made 15 :)\n+1 point");
            player.setScore(score++);
            player.getHand().remove(playerCard);
            computerCard = deck.deal();
            player.addCard(deck.deal(), selectedCardPos);
            return true;
        } else if (playerCard.getSuit().equals(computerCard.getSuit())) {
            System.out.println("\nPlayed same suit. Card swapped :)");
            computerCard = playerCard;
            player.getHand().remove(playerCard);
            player.addCard(deck.deal(), selectedCardPos);
            return true;
        }
        end = "No valid moves!";
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
                System.out.print("\nInvalid input, please enter a number between 0-5! ");
            }
        }
    }

    private void endGame(Scanner scan) {
        roundCount = 0;
        System.out.println(end);
        System.out.println("━━━━━━━━━━━━━\nGAME OVER :/\n━━━━━━━━━━━━━\n");
        System.out.println("Final score: " + player.getScore());

//        System.out.println("━━━━━━━━━━━━━━━━━━━\nWould you like to see the replay? (y/n)");
//        if (scan.nextLine().equalsIgnoreCase("Y")) {
//            showReplay();
//        }

    }
}

