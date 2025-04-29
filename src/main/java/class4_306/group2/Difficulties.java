package class4_306.group2;

public class Difficulties {
    public static class Difficulty {
        private float multiplier;

        public Difficulty(float multiplier) {
            this.multiplier = multiplier;
        }

        public float getMultiplier() {
            return multiplier;
        }
    }

    public static final Difficulty easy = new Difficulty(0.5f);
    public static final Difficulty medium = new Difficulty(1f);
    public static final Difficulty hard = new Difficulty(2f);
    public static final Difficulty demon = new Difficulty(2.5f);
}
