package class4_306.group2;

public class Main {
    public static TextBank FLAVOUR_TEXT_BANK;

    public static void main(String[] args) throws Exception {
        FLAVOUR_TEXT_BANK = new TextBank(".textbank_flavour.txt");

        Game game = new Game();
        game.start();
    }
}