package utils;

import java.util.*;

/**
 * used for input utilities that are repeated
 */
public class InputUtils {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * blank constructor
     */
    private InputUtils() {

    }

    /**
     * processes the user input into either Y or N
     *
     * @return the choice from the scanner after processed
     */
    public static String getYorN() {
        String choice;
        while (true) {
            choice = SCANNER.nextLine().trim().toUpperCase();
            if (choice.equals("Y") || choice.equals("N")) {
                return choice;
            }
            System.out.print("\nInvalid input, please enter (y or n) ");
        }
    }

    /**
     * provides access to the shared scanner instance
     * ensuring a single scanner is used throughout the application
     *
     * @return the shared scanner instance
     */
    public static Scanner getSCANNER() {
        return SCANNER;
    }

}
