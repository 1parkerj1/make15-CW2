import model.Card;
import model.Deck;
import game.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Deck deck = new Deck();
//
//        System.out.println("dealing first 5 cards");
//
//
//
//        for (int i = 0; i < 52; i++) {
//            Card card = deck.deal();
//            if (card != null) {
//                System.out.println(card);
//            } else {
//                System.out.println("The deck is empty");
//                System.out.println(deck);
//            }
//        }
//

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
        System.out.println("                 `--'  `\"'------'  '---'                                                 ");

        System.out.print("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\nPress Enter to Start :)\n");
        scan.nextLine();

        Game game = new Game();
        game.startGame();

        scan.close();
    }

    /*
    TODO: deck amount selector, 1-3 decks for longer games
    TODO: leaderboard
    TODO: replay
     */
}
