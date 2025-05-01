package class4_306.group2;

import class4_306.group2.Difficulties.Difficulty;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game {
    private Leaderboard leaderboard = new Leaderboard("leaderboard.txt");

    public Game() throws FileNotFoundException {
        leaderboard.load();
    }

    public void start() throws IOException {
        while (true) {
            System.out.println(
                    """
                            ▄ .▄ ▄▄▄·  ▐ ▄  ▄▄     ▌ ▄ ·.  ▄▄▄·  ▐ ▄     ▐▄  ▄ ▄▄▄▄▄▄▄▄  ▄▄▄ .  ▌ ▄ ·. ▄▄▄ .
                            ██ ▐█▐█ ▀█  █▌▐█▐█ ▀  ·██ ▐███ ▐█ ▀█  █▌▐█     █▌█▌  ██  ▀▄ █·▀▄.▀··██ ▐███ ▀▄.▀·
                            ██▀▐█▄█▀▀█ ▐█▐▐▌▄█ ▀█▄▐█ ▌▐▌▐█·▄█▀▀█ ▐█▐▐▌     ·██·  ▐█. ▐▀▀▄ ▐▀▀ ▄▐█ ▌▐▌▐█·▐▀▀ ▄
                            ██▌▐▀▐█  ▐▌██▐█▌▐█▄ ▐███ ██▌▐█▌▐█  ▐▌██▐█▌     ▐█·█▌ ▐█▌·▐█ █▌▐█▄▄▌██ ██▌▐█▌▐█▄▄▌
                            ▀▀▀ · ▀  ▀ ▀▀ █ ·▀▀▀▀ ▀▀  █ ▀▀▀ ▀  ▀ ▀▀ █      ▀▀ ▀▀ ▀▀▀ .▀  ▀ ▀▀▀ ▀▀  █ ▀▀▀ ▀▀▀
                            """);
            System.out.println("Press enter to start a new game. Type anything to quit.");

            if (Utility.scanner.nextLine().length() > 0) {
                break;
            }

            Session session = new Session(promptForDifficulty());
            session.start();

            System.out.println();

            leaderboard.tryAddEntry(promptForName(), session.getTotalPoints());
            leaderboard.save();

            System.out.println("LEADERBOARD");
            System.out.println("-".repeat(31));
            leaderboard.displayEntries();
            System.out.println("-".repeat(31));

            System.out.println();

            System.out.println("Press enter to go back to main menu.");
            Utility.scanner.nextLine();
        }
    }

    private String promptForName() {
        System.out.println("Your name? Only letters or digits. Maximum of 5 characters.");

        String name;
        while (true) {
            name = Utility.scanner.nextLine();

            if (name.length() == 0) {
                System.out.println("You must enter at least 1 character.");
                continue;
            } else if (name.length() > 5) {
                System.out.println("Invalid name. Maximum length is 5 characters.");
                continue;
            }

            boolean hasInvalidChar = false;
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isLetterOrDigit(name.charAt(i))) {
                    hasInvalidChar = true;
                    break;
                }
            }

            if (hasInvalidChar) {
                System.out.println("Invalid name. Use letters (A-Z) and digits (0-9).");
                continue;
            }

            break;
        }

        return name;
    }

    private Difficulty promptForDifficulty() {
        while (true) {
            System.out.println("Choose a difficulty:");
            System.out.println("");
            System.out.println("(1) . . . . Easy    0.5x");
            System.out.println("(2) . . . . Medium    1x");
            System.out.println("(3) . . . . Hard      2x");
            System.out.println("(4) . . . . Demon   2.5x");

            String chosen = Utility.scanner.nextLine();
            if (chosen.equals("1")) {
                return Difficulties.easy;
            } else if (chosen.equals("2")) {
                return Difficulties.medium;
            } else if (chosen.equals("3")) {
                return Difficulties.hard;
            } else if (chosen.equals("4")) {
                return Difficulties.demon;
            }
        }
    }
}
