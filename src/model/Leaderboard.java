package model;

import java.io.*;
import java.util.*;

public class Leaderboard {

    private static final int MAX_ENTRIES = 5;
    private final List<Entry> entries;
    private static final String LEADERBOARD = "leaderboard.txt";

    // easier to just do inner class rather than new file
    private static class Entry {
        String name;
        int score;

        public Entry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }
    }

    public Leaderboard() {
        this.entries = new ArrayList<>();
        readFromFile();
    }

    public boolean isHighScore(int score) {
        if (score <= 0) return false;
        return entries.size() < MAX_ENTRIES || score > entries.getLast().score;
    }

    // add new entry only if high score
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

    // super kewl insertion sort mr gpt helped with this one ngl
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

    public List<Entry> getEntries() {
        return entries;
    }
}
