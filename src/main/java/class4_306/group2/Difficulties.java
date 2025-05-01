package class4_306.group2;

public class Difficulties {
    public static class Difficulty {
        private String filename;
        private float multiplier;

        public Difficulty(String filename, float multiplier) {
            this.filename = filename;
            this.multiplier = multiplier;
        }

        public String getFilename() {
            return filename;
        }

        public float getMultiplier() {
            return multiplier;
        }
    }

    public static final Difficulty easy = new Difficulty("texbank_easy.txt", 0.5f);
    public static final Difficulty medium = new Difficulty("texbank_medium.txt", 1f);
    public static final Difficulty hard = new Difficulty("texbank_hard.txt", 2f);
    public static final Difficulty demon = new Difficulty("texbank_demon.txt", 2.5f);
}
