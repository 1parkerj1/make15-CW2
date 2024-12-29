package game;

import model.Card;
import model.Player;
import model.Deck;
import model.Replay;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private final Player player;
    private final Deck deck;
    private Card computerCard;
    private final Replay replayQueue;

    private String endReason;
    private int roundCount, selectedCardPos;

    public static final String TEST = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public Game(int deckNum) {
        if (deckNum <= 0) {
            throw new IllegalArgumentException("Number of decks must be greater than 0");
        }
        this.deck = new Deck(deckNum);
        this.player = new Player();
        this.roundCount = 0;

        // less important stuff

        this.replayQueue = new Replay();

        initialiseGame();
    }

    private void initialiseGame() {
        replayQueue.clear();
        // cards in game after decks init
        System.out.println("\n" + TEST + "TEST: " + deck.getRemainingCards() + " cards" + RESET);
        player.dealHand(deck);
        this.computerCard = deck.deal();
        if(computerCard == null) {
            throw new IllegalStateException("Deck is empty");
        }
    }

    public void startGame() {
        Scanner scan = InputUtils.getSCANNER();
        while (!deck.isEmpty()) {
            if (!playRound(scan)) {
                endReason = "Player could not make a valid move!";
                break;
            }
        }
        if (deck.isEmpty()) {
            endReason = "The deck is empty!";
        }
        endGame(scan);
    }

    public boolean playRound(Scanner scan) {
        if (computerCard == null) {
            endReason = "The deck is empty!";
            return false;
        }

        System.out.println("\nComputer's card: " + computerCard);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Your hand: ");
        player.showHand();

        Card playerCard = getPlayerChoice(scan);
        if (playerCard == null) {
            endReason = "Player quit the game!";
            return false;
        }

        roundCount++;
        replayQueue.addMessage("Round " + roundCount + ":\n");
        replayQueue.addMessage("Computer's Card: " + computerCard);
        replayQueue.addMessage("Player's hand: " + player.getHand());
        replayQueue.addMessage("Player chose: " + playerCard);

        boolean validMove = cardSelection(playerCard, computerCard);
        replayQueue.addMessage("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        System.out.println(TEST + "TEST: end of round #" + roundCount + " | cards left: " + deck.getRemainingCards() + RESET);
        return validMove;
    }

    private boolean cardSelection(Card playerCard, Card computerCard) {
        Scanner scan = InputUtils.getSCANNER();

        if (playerCard.getRankVal() + computerCard.getRankVal() == 15) {
            System.out.println("\nYou made 15 :)\n+1 point");

            List<Card> handSnapshot = new ArrayList<>(player.getHand());

            this.computerCard = deck.deal();

            player.setScore(player.getScore() + 1);
            player.removeCard(playerCard);
            player.addCard(deck.deal(), selectedCardPos);
            replayQueue.addMessage("Player made 15 and scored 1 point :)");

            System.out.print("\nWould you like to replace any face cards from your hand? (y/n) ");
            String choice = InputUtils.getYorN();

            if (choice.equals("Y")) {
                while (true) {
                    List<Card> pictureCards = new ArrayList<>();
                    List<Integer> snapshotIndices = new ArrayList<>();

                    for (int i = 0; i < handSnapshot.size(); i++) {
                        Card card = handSnapshot.get(i);
                        if (card.getRank().equals("Jack") || card.getRank().equals("Queen") || card.getRank().equals("King")) {
                            pictureCards.add(card);
                            snapshotIndices.add(i);
                        }
                    }

                    if (pictureCards.isEmpty()) {
                        System.out.println("You have no picture cards in your hand :/");
                        replayQueue.addMessage("Player had no picture cards to exchange :/");
                        break;
                    }

                    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                    System.out.println("Your face cards: ");
                    for (int i = 0; i < pictureCards.size(); i++) {
                        System.out.println((i + 1) + ") " + pictureCards.get(i));
                    }
                    System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                    System.out.println("Enter the number(s) of the card(s) you would like to replace;");
                    System.out.print("Separate with a comma, or press enter to skip: ");

                    String input = scan.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("No picture cards exchanged :)");
                        replayQueue.addMessage("Player chose not to exchange any picture cards :)");
                        break;
                    }

                    String[] indices = input.split(",");
                    boolean exchanged = false;
                    System.out.println(" ");
                    for (String i : indices) {
                        try {
                            int selectedIndex = Integer.parseInt(i.trim()) - 1;
                            if (selectedIndex >= 0 && selectedIndex < pictureCards.size()) {
                                int realIndex = snapshotIndices.get(selectedIndex);
                                Card toExchange = handSnapshot.get(realIndex);
                                player.removeCard(toExchange);

                                Card newCard = deck.deal();
                                if (newCard != null) {
                                    player.addCard(newCard, realIndex);
                                    System.out.println("Exchanged " + toExchange + " for " + newCard);
                                    replayQueue.addMessage("Player exchanged " + toExchange + " for " + newCard);
                                    exchanged = true;
                                } else {
                                    System.out.println("Deck is empty, no more cards to deal.");
                                    replayQueue.addMessage("Deck was empty, player could not exchange " + toExchange + ".");
                                }
                            } else {
                                System.out.println("Invalid choice: " + (selectedIndex + 1));
                            }
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println("Invalid input: " + i.trim());
                        }
                    }

                    if (!exchanged) {
                        System.out.println("No valid exchanges were made.");
                        replayQueue.addMessage("No valid exchanges were made.");
                    }
                    break;
                }
            }
            return true;

        } else if (playerCard.getSuit().equals(computerCard.getSuit())) {
            System.out.println("\nPlayed same suit. Cards exchanged :)");
            this.computerCard = deck.deal();
            player.removeCard(playerCard);
            Card newCard = deck.deal();
            player.addCard(newCard, selectedCardPos);
            replayQueue.addMessage("Player played same suit and exchanged " + playerCard + " for " + newCard);
            return true;
        }

        endReason = "No valid moves!";
        return false;
    }


    private Card getPlayerChoice(Scanner scan) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.print("Select a card from your hand (1-5): ");
        int handSize = player.getHand().size();
        int choice = validateInput(scan, handSize);
        if (choice == 0) {
            return null;
        }
        selectedCardPos = choice - 1;
        return player.getHand().get(choice - 1);
    }

    // validate player input
    private int validateInput(Scanner scan, int numOptions) {
        while (true) {
            try {
                int choice = Integer.parseInt(scan.nextLine());
                if (choice >= 1 && choice <= numOptions) {
                    return choice;
                }
                System.out.print("\nInvalid choice, please choose again! ");
            } catch (NumberFormatException e) {
                System.out.print("\nInvalid input, please enter a number between 1-5! ");
            }
        }
    }

    // ends the game showing the final score
    private void endGame(Scanner scan) {
        roundCount = 0;
        System.out.println("\n━━━━━━━━━━━━━\nGAME OVER :/\n━━━━━━━━━━━━━\n");
        System.out.print(endReason);
        replayQueue.addMessage(endReason);
    }

    // test method for showing how many cards are currently in the deck
    public List<Card> getDeckCards() {
        return deck.getDeckCards();
    }

    public int getRemainingCards()  {
        return deck.getRemainingCards();
    }

    public int getScore() {
        return player.getScore();
    }

    public List<Card> getHand() {
        return this.player.getHand();
    }

    public Replay getReplayQueue() {
        return replayQueue;
    }
}

