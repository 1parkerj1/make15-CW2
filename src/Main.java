import model.Card;
import model.Deck;
import game.Game;

import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        printBanner();

        while (true) {
            printMainMenu();
            String choice = scan.nextLine();

            switch (choice) {
                case "1": {
                    mainStartGame();
                    break;
                }
                case "2": {
                    printRules();
                    scan.close();
                    break;
                }
                case "3": {
                    printLeaderboard();
                    scan.close();
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
        System.out.print("\nSelect the number of decks you'd like to play with (1-3): ");
        int deckNum = getDeckNum();
        Game game = new Game(deckNum);
        game.startGame();
    }

    private static int getDeckNum() {
        while (true) {
            try {
                int deckNum = Integer.parseInt(scan.nextLine());
                if (deckNum >= 1 && deckNum <= 3) {
                    return deckNum;
                }
                System.out.println("Invalid input, please enter a number between 1-3! ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input, Please enter a valid number: ");
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
        System.out.println("\nMake 15 is a one-player card game played against the computer.");
        System.out.println("\nRules:");
        System.out.println("1. The game uses one (or many) standard shuffled deck(s) of playing cards.");
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
        System.out.println("   - The deck is empty\n");
        System.out.print("Press enter to go back to main menu: ");
        scan.nextLine();
    }
    // temp
    public static void printLeaderboard() {
        System.out.print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        System.out.println("Leaderboard:");
        System.out.format("+---------+----------+%n");
        System.out.format("| Name    | Score    |%n");
        System.out.format("+---------+----------+%n");
        String leftAlignment = "| %-7s | %-8s |%n";
        // for the name and score I need to read it from the file
        // in the player class ill have a way to write to the file
        System.out.format(leftAlignment, "name", "score");
        System.out.format("+---------+----------+%n");
        System.out.print("Press enter to go back to main menu: ");
        scan.nextLine();
    }

    /*
    TODO: leaderboard
    TODO: replay
     */
}
