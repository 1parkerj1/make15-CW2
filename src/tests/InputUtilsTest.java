package tests;

import org.junit.jupiter.api.Test;
import utils.InputUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputUtilsTest {

    @Test
    void testGetYorNValidInput() {
        String simulatedInput = "Y\n"; // Simulate valid input
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            String result = InputUtils.getYorN();
            assertEquals("Y", result, "Expected 'Y' for valid input.");
        } finally {
            System.setIn(originalIn); // Restore original System.in
        }
    }

    @Test
    void testGetSCANNER() {
        assertNotNull(InputUtils.getSCANNER(), "Expected non-null Scanner instance.");
    }
}