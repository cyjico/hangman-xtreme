package class4_306.group2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Leaderboard {
    private String filename;
    private LeaderboardEntry[] entries = new LeaderboardEntry[10];

    public Leaderboard(String filename) {
        this.filename = filename;
    }

    public boolean tryAddEntry(String name, int score) {
        // Assumes it starts from the largest and descending to the smallest (or null).
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null || score > entries[i].getScore()) {
                // Shift everything down before replacing.
                for (int j = entries.length - 1; j > i; j--) {
                    entries[j] = entries[j - 1];
                }

                entries[i] = new LeaderboardEntry(name, score);
                return true;
            }
        }

        return false;
    }

    public void load() throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader(filename));

        for (int i = 0; i < entries.length && scan.hasNextLine(); i++) {
            String line = scan.nextLine();

            // In our file, we store scores as "<NAME>|<SCORE>"
            entries[i] = new LeaderboardEntry(
                    line.substring(0, line.indexOf("|")),
                    Integer.parseInt(line.substring(line.indexOf("|") + 1)));
        }

        scan.close();
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter(filename);

        // In our file, we will store scores as "<NAME>|<SCORE>"
        for (LeaderboardEntry entry : entries) {
            if (entry != null) {
                fw.write(entry.getName());
                fw.write('|');
                fw.write(Integer.toString(entry.getScore()));
                fw.write('\n');
            }
        }

        fw.close();
    }

    public void displayEntries() {
        for (LeaderboardEntry entry : entries) {
            if (entry == null) {
                System.out.println(String.format("%-15s %15s", "_____", "_____"));
                continue;
            }

            System.out.println(String.format("%-15s %15d", entry.getName(), entry.getScore()));
        }
    }
}
