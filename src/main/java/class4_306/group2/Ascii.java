package class4_306.group2;

public class Ascii {
    private static String[] gallows = {
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
        return gallows[index];
    }
}