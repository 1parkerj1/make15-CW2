import game.Game;
import model.Leaderboard;

import java.util.Scanner;

public class Make15 {

    private static final Scanner scan = new Scanner(System.in);
    private static final Leaderboard leaderboard = new Leaderboard();

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

    private static void mainStartGame() {
        System.out.println("\nStarting make15...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int deckNum = getDeckNum();

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
        if (scan.nextLine().equalsIgnoreCase("Y")) {
            game.getReplayQueue().viewReplay();
        }
        game.getReplayQueue().clear();

    }

    private static int getDeckNum() {
        while (true) {
            System.out.print("\nSelect the number of decks you'd like to play with (1-3): ");
            String input = scan.nextLine().trim();
            if(input.isEmpty()) {
                return 1;
            }
            try {
                int deckNum = Integer.parseInt(input);
                if (deckNum >= 1 && deckNum <= 3) {
                    return deckNum;
                }
                System.out.println("Invalid input, please enter a number between 1-3! ");
            } catch (NumberFormatException e) {
                System.out.print("\nInvalid input, Please enter a valid number: ");
            }
        }
    }

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
    public static void printMainMenu() {
        System.out.print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        System.out.println("1. Start Game");
        System.out.println("2. How To Play");
        System.out.println("3. View Leaderboard");
        System.out.println("4. Quit Game");
        System.out.print("-> ");
    }
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
        System.out.println("6. If you cannot make 15 but have a card of the same suit as the computer's card:");
        System.out.println("   - You can play it to continue the game, but no points are scored");
        System.out.println("   - This card will be replaced with a new one from the deck");
        System.out.println("7. The game ends if:");
        System.out.println("   - You cannot make 15 or play a card of the same suit");
        System.out.print("\nPress enter to go back to main menu: ");
        scan.nextLine();
    }

    /*
    TODO: replay
    */
}