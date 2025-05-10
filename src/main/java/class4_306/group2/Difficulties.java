package class4_306.group2;

public class Difficulties {
    public static abstract class Difficulty {
        public abstract String getFilename();

        public abstract float getMultiplier();
    }

    public static class Easy extends Difficulty {
        @Override
        public String getFilename() {
            return "textbank_easy.txt";
        }

        @Override
        public float getMultiplier() {
            return 0.5f;
        }
    }

    public static final Difficulty easy = new Easy();

    public static class Medium extends Difficulty {
        @Override
        public String getFilename() {
            return "textbank_medium.txt";
        }

        @Override
        public float getMultiplier() {
            return 1f;
        }
    }

    public static final Difficulty medium = new Medium();

    public static class Hard extends Difficulty {
        @Override
        public String getFilename() {
            return "textbank_hard.txt";
        }

        @Override
        public float getMultiplier() {
            return 2f;
        }
    }

    public static final Difficulty hard = new Hard();

    public static class Demon extends Difficulty {
        @Override
        public String getFilename() {
            return "textbank_demon.txt";
        }

        @Override
        public float getMultiplier() {
            return 2.5f;
        }
    }

    public static final Difficulty demon = new Demon();
}
