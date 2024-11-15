package game;

import model.Card;
import model.Player;
import model.Deck;

import java.util.Scanner;

public class Game {


    private Player player;
    private Deck deck;
    private int totalScore;

    public Game (String name) {
        deck = new Deck();
        player = new Player(name);
        player.setHand(deck);
        totalScore = 0;
    }

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        System.out.println("WELCOME TO MAKE 15 :)\n---------------------\n");

        while (!deck.isEmpty()) {
            playRound();
        }

        // show the total score at the end of the game

        System.out.println("---------------------\nGAME OVER :/");
        System.out.println("\nplay again?");


    }

    public void playRound() {
        Card computerCard = deck.deal();

        while (!player.hasMoves(computerCard)) {

            System.out.println("\nComputer plays: " + computerCard.toString());
            System.out.println("Your hand: " + player.showHand());
            // loop through hand and add 1) 2) 3)
            // maybe change the showhand method

            System.out.println("----------------------------------");

            System.out.println("select a card from your hand (1-5)");


        }

    }



}
