import model.Card;
import model.Deck;
import game.Game;
public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("dealing first 5 cards");

        for (int i = 0; i < 52; i++) {
            Card card = deck.deal();
            if (card != null) {
                System.out.println(card);
            } else {
                System.out.println("The deck is empty");
                System.out.println(deck);
            }
        }


    }
}
