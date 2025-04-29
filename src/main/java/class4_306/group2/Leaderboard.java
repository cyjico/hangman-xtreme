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
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null || score > entries[i].getScore()) {
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

            entries[i] = new LeaderboardEntry(
                    line.substring(0, line.indexOf("§")),
                    Integer.parseInt(line.substring(line.indexOf("§") + 1)));
        }

        scan.close();
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter(filename);

        for (LeaderboardEntry entry : entries) {
            fw.write(entry.getName() + "§" + entry.getScore());
        }

        fw.close();
    }
}
