import model.Card;
import model.Deck;




public class main {
    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("dealing first 5 cards");

        for (int i = 0; i < 52; i++) {
            Card card = deck.deal();
            if (card != null) {
                System.out.println(card);
            } else {
                System.out.println("The deck is empty");
            }
        }

    }
}
