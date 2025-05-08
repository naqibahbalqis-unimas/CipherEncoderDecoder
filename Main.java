import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Example Input
            String testInput = "HELLO WORLD";
            Encoded encoder = new Encoded(testInput);

            // Encode
            String encoded = encoder.encode();
            System.out.println("Original: " + testInput);
            System.out.println("Encoded: " + encoded);
            System.out.println("Shift used: " + encoder.getFinalShift());

            // Decode
            Encoded decoder = new Encoded(encoded);
            String decoded = decoder.decode();
            System.out.println("\nDecoding: " + encoded);
            System.out.println("Decoded: " + decoded);
            System.out.println("Shift used: " + decoder.getFinalShift());

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}