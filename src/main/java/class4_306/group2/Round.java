package class4_306.group2;

public class Round {
    private String target;
    private String targetUpperCased;
    private boolean[] guessed = new boolean[26];
    private int livesLeft = 6;
    private int points = 0;

    public Round(String target) {
        this.target = target;
        this.targetUpperCased = target.toUpperCase();
    }

    /**
     * Starts the round.
     * 
     * @return true if the player wins, false otherwise.
     */
    public boolean start() {
        while (livesLeft > 0) {
            displayStatus();

            String guess = promptForGuess();
            if (handleGuess(guess)) {
                System.out.println("Congratulations! You guessed the word: " + target);
                return true;
            }
        }

        System.out.println("The word was: " + target);
        return false;
    }

    private void displayStatus() {
        System.out.println();

        System.out.println("Enter a letter or guess the full word:");
        System.out.println(Ascii.getGallows(livesLeft));
        System.out.println(livesLeft + " lives remaining.");

        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);

            if (!Character.isLetter(c) || guessed[targetUpperCased.charAt(i) - 'A']) {
                System.out.print(c);
            } else {
                System.out.print("_");
            }
        }

        System.out.println();

        for (int i = 0; i < guessed.length; i++) {
            System.out.print(guessed[i] ? "█ " : (char) ('A' + i) + " ");

            if (i == 13) {
                System.out.print("\n ");
            }
        }
        System.out.println();

        System.out.println("(debug): target is " + target);
    }

    private String promptForGuess() {
        String guess;

        while (true) {
            guess = Utility.scanner.nextLine().trim().toUpperCase();

            if (guess.isEmpty()) {
                System.out.println("Your guess cannot be empty.");
                continue;
            } else if (guess.length() == 1) {
                if (!Character.isLetter(guess.charAt(0))) {
                    System.out.println("Invalid guess. Use letters (A-Z).");
                    continue;
                } else if (guessed[guess.charAt(0) - 'A']) {
                    System.out.println("You've already guessed " + guess.charAt(0) + ".");
                    continue;
                }
            } else if (guess.length() > 1 && !Utility.isStrictlyNaturalLanguageLike(guess)) {
                System.out.println("Invalid guess. Use letters (A-Z), spaces, or punctuation.");
                continue;
            }

            break;
        }

        return guess;
    }

    private boolean handleGuess(String guess) {
        if (guess.length() > 1) {
            // User is guessing the whole word/sentence.
            if (!guess.equals(targetUpperCased)) {
                livesLeft = 0;
                System.out.print("Wrong word (automatic loss). ");
                return false;
            }

            boolean[] encountered = new boolean[26];
            int missing = 0;

            for (int i = 0; i < targetUpperCased.length(); i++) {
                char c = targetUpperCased.charAt(i);

                if (Character.isLetter(c)) {
                    int j = c - 'A';

                    if (!encountered[j] && !guessed[j]) {
                        missing++;
                        encountered[j] = true;
                    }
                }
            }

            points += (missing - 1) * 1.5f;
            return true;
        } else {
            // User is guessing a character.
            char letter = guess.charAt(0);
            int idx = letter - 'A';

            guessed[idx] = true;
            if (targetUpperCased.indexOf(letter) >= 0) {
                points++;
            } else {
                livesLeft--;
            }

            // Check if the target was fully guessed.
            for (int i = 0; i < targetUpperCased.length(); i++) {
                char c = targetUpperCased.charAt(i);

                if (Character.isLetter(c) && !guessed[c - 'A']) {
                    if (livesLeft == 0) {
                        System.out.println(Ascii.getGallows(0));
                        System.out.print("Out of lives. ");
                    }

                    return false;
                }
            }

            return true;
        }
    }

    public int getPoints() {
        return points;
    }

    private static class Ascii {
        private static String[] _gallows = {
                """
                         +---+
                         |   |
                         O   |
                        /|\\  |
                        / \\  |
                             |
                        """,
                """
                         +---+
                         |   |
                         O   |
                        /|\\  |
                        /    |
                             |
                        """,
                """
                         +---+
                         |   |
                         O   |
                        /|\\  |
                             |
                             |
                        """,
                """
                         +---+
                         |   |
                         O   |
                        /|   |
                             |
                             |
                        """,
                """
                         +---+
                         |   |
                         O   |
                         |   |
                             |
                             |
                        """,
                """
                         +---+
                         |   |
                         O   |
                             |
                             |
                             |
                        """,
                """
                         +---+
                         |   |
                             |
                             |
                             |
                             |
                        """
        };

        public static String getGallows(int index) {
            return _gallows[index];
        }
    }
}
