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
     * @return {@code true} if the player wins, {@code false} otherwise.
     */
    public boolean start() {
        String handleGuessRes = "";
        while (livesLeft > 0) {
            displayStatus();

            handleGuessRes = handleGuess(promptForGuess());
            if (handleGuessRes.length() == 0) {
                System.out.println("Congratulations! You guessed the word: " + target);
                return true;
            }
        }

        System.out.println();

        System.out.println(handleGuessRes + "The word was: " + target);
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

    /**
     * Processes a user's guess (single letter or full word).
     *
     * @param guess Validated and capitalized user input.
     * @return Feedback string (empty if successful, message if otherwise).
     */
    private String handleGuess(String guess) {
        if (guess.length() > 1) {
            // User is guessing the whole word/sentence.
            if (!guess.trim().equalsIgnoreCase(targetUpperCased)) {
                System.out.println(Main.FLAVOUR_TEXT_BANK.getRandomText());

                livesLeft = 0;
                return "Wrong word (automatic loss). ";
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
            return "";
        } else {
            // User is guessing a character.
            char letter = guess.charAt(0);
            int idx = letter - 'A';

            guessed[idx] = true;
            if (targetUpperCased.indexOf(letter) >= 0) {
                points++;
            } else {
                System.out.println(Main.FLAVOUR_TEXT_BANK.getRandomText());

                livesLeft--;
            }

            // Check if the target was fully guessed.
            for (int i = 0; i < targetUpperCased.length(); i++) {
                char c = targetUpperCased.charAt(i);

                if (Character.isLetter(c) && !guessed[c - 'A']) {
                    return Ascii.getGallows(0) + "\nOut of lives. ";
                }
            }

            return "";
        }
    }

    public int getPoints() {
        return points;
    }
}
