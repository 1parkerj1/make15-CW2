import game.Game;
import model.Leaderboard;
import utils.InputUtils;

import java.util.Scanner;

/**
 * main class for make15
 * providing the entry point for the application and managing flow
 */
public class Make15 {

    private static final Scanner scan = InputUtils.getSCANNER();
    private static final Leaderboard leaderboard = new Leaderboard();

    /**
     * main method for make 15
     * handles the main game loop
     * start game, view how to play, view leaderboard, quit game
     *
     * @param args cmd-line args (unused)
     */
    public static void main(String[] args) {
        printBanner();

        while (true) {
            printMainMenu();
            String choice = scan.nextLine().trim();

            switch (choice) {
                case "1": {
                    mainStartGame();
                    break;
                }
                case "2": {
                    printRules();
                    break;
                }
                case "3": {
                    leaderboard.display();
                    scan.nextLine();
                    break;
                }
                case "4": {
                    System.out.println("\nThanks for playing make15 :)");
                    scan.close();
                    return;
                }
                default:
                    System.out.println("Invalid input, please enter a number between 1-4! ");
            }
        }
    }

    /**
     * starts a new game of Make15
     * prompting the player to select a number of decks and starts the game
     * handling leaderboard updates if the player achieves a high score
     * and prompts the player to view the game replay after the game ends
     */
    private static void mainStartGame() {
        int deckNum = getDeckNum();

        System.out.print("\nStarting make15...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Game game = new Game(deckNum);
        game.startGame();

        int finalScore = game.getScore();
        System.out.print(" | Final score: " + finalScore);

        if (leaderboard.isHighScore(finalScore)) {
            System.out.println("\nCongratulations! You made the leaderboard! (somehow...) ");
            String playerName = validatePlayerName();
            leaderboard.addEntry(playerName, finalScore);
        } else {
            System.out.println("\nGood effort! however you did not make the leaderboard... ");
        }

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("Would you like to see the replay? (y/n) ");
        String choice = InputUtils.getYorN();
        if (choice.equalsIgnoreCase("Y")) {
           displayReplay(game);
        }
        game.getReplayQueue().clear();
    }

    /**
     * displays the replay of the game
     *
     * @param game object whose replay is displayed
     */
    private static void displayReplay(Game game) {
        System.out.println("\nWatching replay: ");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        while (game.getReplayQueue().hasEvents()) {
            System.out.println(game.getReplayQueue().getNextEvent());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\nReplay Finished :) Thanks for playing");
    }

    /**
     * retrieves the number for the amount of decks the player wants to use
     * 1-3 decks allowed to be selected (just an extra feature I thought was needed)
     *
     * @return the number of decks chose by the player
     */
    private static int getDeckNum() {
        System.out.print("\nSelect the number of decks you'd like to play with (1-3): ");
        while (true) {
            String input = scan.nextLine().trim();
            if(input.isEmpty()) {
                return 1;
            }
            try {
                int deckNum = Integer.parseInt(input);
                if (deckNum >= 1 && deckNum <= 3) {
                    return deckNum;
                }
                System.out.print("\nInvalid input, please enter a number between 1-3! ");
            } catch (NumberFormatException e) {
                System.out.print("\nInvalid input, Please enter a valid number: ");
            }
        }
    }

    /**
     * prompts the player to enter their name and validates the input
     *
     * @return the validated player name :)
     */
    private static String validatePlayerName() {
        while (true) {
            System.out.print("\nEnter your name: ");
            String playerName = scan.nextLine().trim();

            if (playerName.isEmpty()) {
                System.out.print("Name cannot be empty\n");
            } else if (playerName.length() > 20) {
                System.out.print("\nName is too long (max 20 characters): ");
            } else {
                return playerName;
            }
        }
    }


    // display format methods :)

    /**
     * prints game banner
     */
    public static void printBanner() {
        System.out.println("                                                             __            .----------. ");
        System.out.println("                                                        ...-'  |`.        /          /  ");
        System.out.println(" __  __   ___                 .           __.....__     |      |  |      /   ______.'   ");
        System.out.println("|  |/  `.'   `.             .'|       .-''         '.   ....   |  |     /   /_          ");
        System.out.println("|   .-.  .-.   '          .'  |      /     .-''\"'-.  `.   -|   |  |    /      '''--.    ");
        System.out.println("|  |  |  |  |  |    __   <    |     /     /________\\   \\   |   |  |   '___          `.  ");
        System.out.println("|  |  |  |  |  | .:--.'.  |   | ____|                  |...'   `--'       `'.         | ");
        System.out.println("|  |  |  |  |  |/ |   \\ | |   | \\ .\\    .-------------'|         |`.        )        | ");
        System.out.println("|  |  |  |  |  |`\" __ | | |   |/  .  \\    '-.____...---.` --------\\ |......-'        /  ");
        System.out.println("|__|  |__|  |__| .'.''| | |    /\\  \\  `.             .'  `---------' \\          _..'`   ");
        System.out.println("                / /   | |_|   |  \\  \\   `''-...... -'                 '------'''        ");
        System.out.println("                \\ \\._,\\ '/'    \\  \\  \\                                                   ");
        System.out.println("                 `--'  `\"'------'  '---'                                                 \n");
    }

    /**
     * prints main menu options
     */
    public static void printMainMenu() {
        System.out.print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        System.out.println("1. Start Game");
        System.out.println("2. How To Play");
        System.out.println("3. View Leaderboard");
        System.out.println("4. Quit Game");
        System.out.print("-> ");
    }

    /**
     * prints the rules of the game
     */
    public static void printRules() {
        System.out.print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        System.out.println("How to Play:");
        System.out.println("\nRules: Make 15 is a one-player card game played against the computer.\n");
        System.out.println("1. The game uses a standard deck or decks of playing cards.");
        System.out.println("   - You can choose how many decks you would like to play with, the default is 1");
        System.out.println("2. You are dealt 5 cards at the start of the game.");
        System.out.println("3. In each round, the computer deals a card face-up from the deck.");
        System.out.println("4. The goal is to pick a card from your hand to make 15 with the computer's card.");
        System.out.println("   - Jack, Queen, King have a value of 11");
        System.out.println("   - The Ace has a value of 12");
        System.out.println("5. If you succeed in making 15:");
        System.out.println("   - You score 1 point");
        System.out.println("   - Your card is replaced with a new one from the deck");
        System.out.println("   - Prompt to exchange picture cards from your hand will be (CSV format)");
        System.out.println("6. If you cannot make 15 but have a card of the same suit as the computer's card:");
        System.out.println("   - You can play it to continue the game, but no points are scored");
        System.out.println("   - This card will be replaced with a new one from the deck");
        System.out.println("7. The game ends if:");
        System.out.println("   - You cannot make 15 or play a card of the same suit");
        System.out.print("\nPress enter to go back to main menu: ");
        scan.nextLine();
    }
}