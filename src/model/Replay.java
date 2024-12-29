package model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * replay functionality for the game
 * recording game events and allows them to be replayed
 */
public class Replay{

    private final Queue<String> gameEvents;

    /**
     * constructs a new replay instance with an empty queue of game events
     */
    public Replay() {
        this.gameEvents = new LinkedList<>();
    }

    /**
     * add a message to the replay queue
     *
     * @param message message the game vent message to add
     */
    public void addMessage(String message) {
        this.gameEvents.add(message);
    }

    /**
     * display the replay of all the recorded game events
     * each event is printed with a brief delay to simulate a replay
     */
    public void viewReplay() {
        System.out.println("\nWatching replay: ");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        while (!gameEvents.isEmpty()) {
            System.out.println(gameEvents.poll());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("\nReplay Finished :) Thanks for playing");
    }

    /**
     * clears all the game events from the queue
     */
    public void clear() {
        gameEvents.clear();
    }

    /**
     * checks if the replay queue is empty
     *
     * @return true if there are no game events in the queue false otherwise
     */
    public boolean isEmpty() {
        return this.gameEvents.isEmpty();
    }
}
