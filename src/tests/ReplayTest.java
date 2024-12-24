package tests;

import model.Replay;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ReplayTest {

    @Test
    void addMessage() {
        Replay replay = new Replay();
        replay.addMessage("TEST: you made 15 :)");
        replay.addMessage("TEST: +1 point");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        replay.viewReplay();

        System.setOut(originalOut);
        String output = outputStream.toString();

        assertTrue(output.contains("\nWatching replay: "));
        assertTrue(output.contains("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        assertTrue(output.contains("TEST: you made 15 :)"));
        assertTrue(output.contains("TEST: +1 point"));
        assertTrue(output.contains("\nReplay Finished :) Thanks for playing"));
    }

    @Test
    void viewReplay() {
        Replay replay = new Replay();
        replay.addMessage("TEST: you made 15 :)");
        replay.addMessage("TEST: +1 point");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        replay.viewReplay();

        System.setOut(originalOut);

        String output = outputStream.toString();
        assertTrue(output.contains("\nWatching replay: "));
        assertTrue(output.contains("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        assertTrue(output.contains("TEST: you made 15 :)"));
        assertTrue(output.contains("TEST: +1 point"));
        assertTrue(output.contains("\nReplay Finished :) Thanks for playing"));
    }

    @Test
    void clear() {
        Replay replay = new Replay();
        replay.addMessage("TEST: you made 15 :)");
        replay.addMessage("TEST: +1 point");

        replay.clear();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));


        replay.viewReplay();

        System.setOut(originalOut);

        String output = outputStream.toString();
        assertTrue(output.contains("\nWatching replay: "));
        assertTrue(output.contains("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        assertFalse(output.contains("TEST: you made 15 :)"));
        assertFalse(output.contains("TEST: +1 point"));
        assertTrue(replay.isEmpty());
        assertTrue(output.contains("\nReplay Finished :) Thanks for playing"));
    }
}