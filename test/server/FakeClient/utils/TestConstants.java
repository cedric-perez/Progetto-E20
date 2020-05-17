package server.FakeClient.utils;

public class TestConstants {
    // Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Strings for tests
    public static final String TEST_TITLE = (ANSI_BLUE + "####### TESTING %s #######" + ANSI_RESET);
    public static final String TEST_SUCCESS = (ANSI_GREEN + "[OK] Test passed" + ANSI_RESET);
    public static final String TEST_FAIL = (ANSI_RED + "[FAIL] Test failed: %s" + ANSI_RESET);

}
