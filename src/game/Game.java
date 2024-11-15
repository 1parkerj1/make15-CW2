package game;

import model.Card;
import model.Player;
import model.Deck;

import java.util.Scanner;

public class Game {

    private Player player;
    private Deck deck;
    private int score, totalScore;
    private Card computerCard;

    public Game (String name) {
        deck = new Deck();
        player = new Player(name);
        player.setHand(deck);
        totalScore = 0;
        score = 0;

    }

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        player.setHand(deck);

        while (!deck.isEmpty()) {
            computerCard = deck.deal();
            if ( computerCard == null) {
                System.out.println("The deck is empty");
                // not sure what to do here...
                // maybe need to create a new deck if com runs out of cards
                break;
            }
            System.out.println("Computer's Card: " + computerCard);

            System.out.println("Your Hand: ");
            System.out.println(player.showHand());
            System.out.println("----------------------------------");
            System.out.print("Select a card from your hand (1-5): ");

            // chatgpt made this shit i have no idea
            int choice;
            try {
                choice = Integer.parseInt(scan.nextLine()) - 1;
                if (choice < 0 || choice >= player.getHand().size()) {
                    System.out.println("Invalid Choice");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input (Enter a number between 1-5)");
                continue;
            }

            Card selectedCard = player.getHand().get(choice);

            if (selectedCard.getRankVal() + computerCard.getRankVal() == 15) {
                System.out.println("\nYou made 15!!\t+1 point :)");
                player.setScore(score++);
                player.getHand().remove(selectedCard);
                computerCard = deck.deal();
                player.addCard(deck.deal());


            } else if (selectedCard.getSuit().equals(computerCard.getSuit())) {
                System.out.println("\nYou played a card of the same suit\tno points awarded :|");
                player.getHand().remove(selectedCard);
                player.addCard(deck.deal());

            } else {
                System.out.println("\nYou cannot play this card, try again...");
                break;
            }

            System.out.println("\nFinal score: " + player.getScore());
            scan.close();

        }

        // show the total score at the end of the game
        // also make it so the user can choose to play again with the same name or a new game
        System.out.println("---------------------\nGAME OVER :/");
        System.out.println("\nplay again?");
    }
}
