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

        System.out.println("WELCOME TO MAKE 15 :)\n━━━━━━━━━━━━━━━━━━━━━");

        // add validation to name
        System.out.print("Enter Your Name: ");
        String name = scan.nextLine();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━");

        Game game = new Game(name);
        game.startGame();
    }
}
