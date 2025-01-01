package tests;

import model.Replay;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplayTest {

    @Test
    void addMessage() {
        Replay replay = new Replay();
        replay.addMessage("TEST: you made 15 :)");
        replay.addMessage("TEST: +1 point");

        assertEquals("TEST: you made 15 :)", replay.getNextEvent());
        assertEquals("TEST: +1 point", replay.getNextEvent());
        assertFalse(replay.hasEvents());
    }

    @Test
    void clear() {
        Replay replay = new Replay();
        replay.addMessage("TEST: you made 15 :)");
        replay.addMessage("TEST: +1 point");

        replay.clear();

        assertFalse(replay.hasEvents());
    }

    @Test
    void hasEvents() {
        Replay replay = new Replay();

        assertFalse(replay.hasEvents());

        replay.addMessage("TEST: you made 15 :)");
        assertTrue(replay.hasEvents());

        replay.getNextEvent();
        assertFalse(replay.hasEvents());
    }

    @Test
    void getNextEvent() {
        Replay replay = new Replay();
        replay.addMessage("TEST: you made 15 :)");
        replay.addMessage("TEST: +1 point");

        // Assert correct retrieval of events
        assertEquals("TEST: you made 15 :)", replay.getNextEvent());
        assertEquals("TEST: +1 point", replay.getNextEvent());

        // Assert that the log is now empty
        assertNull(replay.getNextEvent());
        assertFalse(replay.hasEvents());
    }
}
