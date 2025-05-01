package class4_306.group2;

import class4_306.group2.Difficulties.Difficulty;
import java.io.FileNotFoundException;

public class Session {
    private TextBank textBank;
    private float pointsMultiplier;
    private int totalPoints;

    public Session(Difficulty difficulty) throws FileNotFoundException {
        textBank = new TextBank(difficulty.getFilename());
        pointsMultiplier = difficulty.getMultiplier();
    }

    public void start() {
        Round round;
        while (true) {
            round = new Round(textBank.getRandomText());
            if (!round.start()) {
                break;
            }

            int roundPoints = Math.round(round.getPoints() * pointsMultiplier);
            totalPoints += roundPoints;

            System.out.println();
            System.out.println("You earned " + roundPoints + ".");
            System.out.println("You earned a total of " + totalPoints + ".");
            System.out.println("Press enter to continue to the next round.");
            
            Utility.scanner.nextLine();
            
        }
        
        System.out.println("");
        System.out.println("You lost!");
        System.out.println("You earned a total of " + totalPoints + ".");
        System.out.println("Press enter to continue.");

        Utility.scanner.nextLine();
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
