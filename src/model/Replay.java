package model;

import java.util.LinkedList;
import java.util.Queue;

public class Replay{

    private final Queue<String> gameEvents;

    public Replay() {
        this.gameEvents = new LinkedList<>();
    }

    public void addMessage(String message) {
        this.gameEvents.add(message);
    }

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

    public void clear() {
        gameEvents.clear();
    }

    public boolean isEmpty() {
        return this.gameEvents.isEmpty();
    }
}
