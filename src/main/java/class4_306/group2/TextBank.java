package class4_306.group2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TextBank {
    private ArrayList<String> textSafe = new ArrayList<String>();

    public TextBank(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader(filename));

        while (scan.hasNextLine()) {
            textSafe.add(scan.nextLine());
        }
        textSafe.trimToSize();

        scan.close();
    }

    public String getRandomText() {
        return textSafe.get((int) Math.round(Math.random() * (textSafe.size() - 1)));
    }
}
