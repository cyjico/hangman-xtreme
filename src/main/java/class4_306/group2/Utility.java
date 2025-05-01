package class4_306.group2;

import java.util.Scanner;

public class Utility {
    public static Scanner scanner = new Scanner(System.in);

    public static boolean isPunctuation(char c) {
        return "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".indexOf(c) != -1;
    }

    public static boolean isWhitespace(char c) {
        return c == ' ';
    }

    public static boolean isNaturalLanguageLike(char c) {
        return Character.isLetter(c) || isPunctuation(c) || isWhitespace(c);
    }

    public static boolean isStrictlyNaturalLanguageLike(String text) {
        for (int i = 0; i < text.length(); i++)
            if (!isNaturalLanguageLike(text.charAt(i)))
                return false;

        return true;
    }
}
