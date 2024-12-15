import model.Card;
import model.Deck;
import game.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

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

        while (true) {
            System.out.print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            System.out.println("1. Start Game");
            System.out.println("2. How To Play");
            System.out.println("3. View Leaderboard");
            System.out.println("4. Quit Game");
            System.out.print("-> ");


            Game game = new Game();
            String choice = scan.nextLine();

            switch (choice) {
                case "1": {
                    game.startGame();
                    break;
                }
                case "2": {

                    System.out.print("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                    System.out.println("Game Rules:");
                    //game.gameRules();
                    System.out.println("\nplaceholder\n");
                    System.out.print("Press enter to go back to main menu: ");
                    scan.nextLine();
                    break;
                }
                case "3": {
                    System.out.print("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                    System.out.println("Leaderboard:");
                    // leaderboard
                    System.out.println("\nleaderboard placeholder\n");
                    System.out.print("Press enter to go back to main menu: ");
                    scan.nextLine();
                    break;
                }
                case "4": {
                    System.out.println("\nThanks for playing make15 :)");
                    scan.close();
                    return;
                }
                default:
                    System.out.println("Invalid input, please enter a number between 1-3! ");
            }
        }
    }
    /*
    TODO: deck amount selector, 1-3 decks for longer games
    TODO: leaderboard
    TODO: replay
     */
}
