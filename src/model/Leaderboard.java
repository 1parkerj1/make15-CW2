package model;

import java.io.*;
import java.util.*;

/**
 * leaderboard ADT
 */
public class Leaderboard {

    private static final int MAX_ENTRIES = 5;
    private final List<Entry> entries;
    private static final String LEADERBOARD = "leaderboard.txt";

    /**
     * Entry helper class, specifies the make of an entry
     * name, score
     */
    private static class Entry {
        String name;
        int score;

        /**
         * constructor for an entry
         * @param name player name
         * @param score player score
         */
        public Entry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        /**
         * name getter
         *
         * @return player name
         */
        public String getName() {
            return name;
        }
    }

    /**
     * constructs a leaderboard object and initialised the list of entries
     */
    public Leaderboard() {
        this.entries = new ArrayList<>();
        readFromFile();
    }

    /**
     * checks if a given score qualifies as a high score.
     * high score qualifies if it is greater than 0 and either the leaderboard
     * has fewer than 5 entries or the score is greater than the lowest
     * high score on the leaderboard
     *
     * @param score the score to check
     * @return true if the score qualifies as a high score false otherwise
     */
    public boolean isHighScore(int score) {
        if (score <= 0) return false;
        return entries.size() < MAX_ENTRIES || score > entries.getLast().score;
    }

    /**
     * adds a new high score entry to the leaderboard if it qualifies
     * the leaderboard is updated, sorted and truncated to the max number of entries (5)
     *
     * @param playerName the name of the player
     * @param score the score achieved by the player
     * @return true if the entry was added to the leaderboard false otherwise
     */
    public boolean addEntry(String playerName, int score) {
        if (score <= 0) {
            return false;
        }
        Entry entry = new Entry(playerName, score);

        // this is so sweeet omg
        // checks if the new score is higher than the last one in the file (10th one)
        // if bigger than it gets put into the file using the sorting algorithm :)
        if (isHighScore(entry.score)) {
            entries.add(entry);
            insertionSort();
            if (entries.size() > MAX_ENTRIES) {
                entries.removeLast();
            }
            writeToFile();
            return true;
        }
        return false;
    }

    /**
     * super cool insertion sort algorithm
     * sorts the entries in the leaderboard in descending order by score
     * using the insertion sort algorithm
     */
    private void insertionSort() {
        for (int i = 1; i < entries.size(); i++) {
            Entry key = entries.get(i);
            int j = i - 1;

            while (j >= 0 && entries.get(j).score < key.score) {
                entries.set(j + 1, entries.get(j));
                j--;
            }
            entries.set(j + 1, key);
        }
    }

    /**
     * writes the current leaderboard entries to a file
     * each entry is stored as a line in the format "name,score"
     */
    private void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEADERBOARD))) {
            for (Entry entry : entries) {
                writer.write(entry.name + "," + entry.score);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving leaderboard: " + e.getMessage());
        }
    }

    /**
     * reads leaderboard entries from a file and populates the entries list
     * if the file does not exist, it is created
     * each line in the file should be in the format "name,score"
     */
    private void readFromFile() {
        File file = new File(LEADERBOARD);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating leaderboard file: " + e.getMessage());
            }
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(LEADERBOARD))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    entries.add(new Entry(name, score));
                }
            }
        } catch (IOException e) {
            System.out.println("Error Loading leaderboard: " + e.getMessage());
        }
    }

    /**
     * display the current leaderboard in formatted table
     * if no scores are present, displays a placeholder message
     */
    public void display() {
        System.out.print("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        System.out.println("Leaderboard:\n");
        System.out.println("+------------------+----------+");
        System.out.println("| Name             | Score    |");
        System.out.println("+------------------+----------+");
        if (entries.isEmpty()) {
            System.out.printf("| %-27s |%n", "     No Highscores xD    ");
            System.out.println("+------------------+----------+");
        } else {
            for (Entry entry : entries) {
                System.out.printf("| %-16s | %-8d |%n", entry.name.trim(), entry.score);
                System.out.println("+------------------+----------+");
            }
        }
        System.out.print("\nPress enter to go back to main menu: ");
    }

    /**
     * returns the list of leaderboard entries
     *
     * @return list of leaderboard entries
     */
    public List<Entry> getEntries() {
        return entries;
    }
}
