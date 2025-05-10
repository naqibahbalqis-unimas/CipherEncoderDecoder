//Unit tests - Accuracy of encoding and decoding

public class CipherTest {

    public static void main(String[] args) {
        testEncodingDecoding("HELLO WORLD");
        testEncodingDecoding("1234567890");
        testEncodingDecoding("A1B2C3D4E5");
        testEncodingDecoding("TEST CASE WITH SPACE");
        testInvalidInput("hello");             // lowercase
        testInvalidInput("INVALID!@#");        // symbols
        testEmptyInput();                      // null or empty
    }

    private static void testEncodingDecoding(String input) {
        try {
            Encoded encoder = new Encoded(input);
            String encoded = encoder.encode();
            Encoded decoder = new Encoded(encoded);
            String decoded = decoder.decode();
            boolean pass = input.equals(decoded);

            System.out.println("Input         : " + input);
            System.out.println("Encoded       : " + encoded);
            System.out.println("Decoded       : " + decoded);
            System.out.println("Shift Used    : " + encoder.getFinalShift());
            System.out.println(pass ? "PASS\n" : "FAIL\n");

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    private static void testInvalidInput(String input) {
        try {
            new Encoded(input);
            System.out.println("FAIL: Invalid input accepted - " + input);
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Caught invalid input - " + input);
        }
    }

    private static void testEmptyInput() {
        try {
            new Encoded(null);
            System.out.println("FAIL: Null input accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Null input rejected");
        }

        try {
            new Encoded("");
            System.out.println("FAIL: Empty string accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Empty string rejected");
        }
    }
}
