package utils;

import java.util.*;

public class InputUtils {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtils() {

    }

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

    public static Scanner getSCANNER() {
        return SCANNER;
    }

}
