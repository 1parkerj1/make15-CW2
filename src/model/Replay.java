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
     * clears all the game events from the queue
     */
    public void clear() {
        gameEvents.clear();
    }

    /**
     * retrieve the next event in the replay log
     *
     * @return the next event, or null if no events remain
     */
    public String getNextEvent() {
        return gameEvents.poll();
    }

    /**
     * check if there are any events remaining in the log :)
     *
     * @return true if there are events, false otherwise
     */
    public boolean hasEvents() {
        return !gameEvents.isEmpty();
    }
}
