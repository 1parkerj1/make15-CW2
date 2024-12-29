package game;

import model.Card;
import model.Player;
import model.Deck;
import model.Replay;
import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * main game logic for make15
 * manages the player, deck, game state and replay functionality
 */
public class Game {

    private final Player player;
    private final Deck deck;
    private Card computerCard;
    private final Replay replayQueue;

    private String endReason;
    private int roundCount, selectedCardPos;

    public static final String TEST = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    /**
     * game constructor with specified number of decks
     *
     * @param deckNum number of decks to init game with (must be greater than 0)
     * @throws IllegalArgumentException if deckNum is less than or equal to 0
     */
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

    /**
     * init game by dealing cards to the player and setting computer card
     */
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

    /**
     * Starts the main game loop, allowing player to play until the game ends
     */
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
        endGame();
    }

    /**
     * plays single round of the game, player selects card to play
     * against the computers card, updates game state and replay queue
     *
     * @param scan the scanner used to capture the players input
     * @return true if the player makes a valid move false otherwise
     */
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

    /**
     * processes the logic for a player's card selection during the round
     * handles scoring, deck updates, face card exchanges
     *
     * @param playerCard card chosen by player
     * @param computerCard current computer card
     * @return true if the move was valid false otherwise
     */
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

    /**
     * prompts the player to select a card from their hand
     *
     * @param scan the scanner used to capture input
     * @return the players card choice from their hand
     */
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

    /**
     * validates the player's input to ensure it's a valid choice
     *
     * @param scan the scanner use to capture input
     * @param numOptions the amount of valid options the player has
     * @return the validated choice, between one and numOptions
     */
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

    /**
     * displays the final results when the game ends
     * resets the round count and outputs the reason for the game ending
     */
    private void endGame() {
        roundCount = 0;
        System.out.println("\n━━━━━━━━━━━━━\nGAME OVER :/\n━━━━━━━━━━━━━\n");
        System.out.print(endReason);
        replayQueue.addMessage(endReason);
    }

    /**
     * returns a list of cards currently in the deck
     * formatted - 'rank' of 'suit'
     *
     * @return a list of cards in the deck
     */
    public List<Card> getDeckCards() {
        return deck.getDeckCards();
    }

    /**
     * returns the number of remaining cards
     * uses the getRemainingCards() method from the deck class
     *
     * @return the number of remaining cards
     */
    public int getRemainingCards()  {
        return deck.getRemainingCards();
    }

    /**
     * returns the player's score
     * uses the getScore() method from the player class
     *
     * @return the player's score
     */
    public int getScore() {
        return player.getScore();
    }

    /**
     * returns the player's current hand of cards
     *
     * @return the player's hand
     */
    public List<Card> getHand() {
        return this.player.getHand();
    }

    /**
     * returns the replay queue
     *
     * @return the replay queue
     */
    public Replay getReplayQueue() {
        return replayQueue;
    }
}

