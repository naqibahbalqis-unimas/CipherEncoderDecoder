/*
 * Encoded.java
 * Putri Naqibah Balqis binti Zainol (100805)
 * Role: Logic Developer
 * Contribution: Implemented full logic, encoding, decoding, and shift calculation.
 * 
 * Siti Norlie Yana (101059) 
 * Role: Backend Developer
 * Contribution: Initial class structure and basic constructor.
 * 
 */

public class Encoded {
    private String inputText;
    private int charCount;
    private String resultText;
    private final String groupID = "G06/SE-G7";

    // Constructor to initialize the input text and validate it - Initial setup by
    // Sti Norlie Yana, enhanced by Putri Naqibah Balqis
    public Encoded(String inputText) {
        if (inputText == null) {
            throw new IllegalArgumentException("Input text cannot be null");
        }

        if (!validateInput(inputText)) {
            throw new IllegalArgumentException(
                    "Invalid input! Only uppercase letters, digits, and spaces are allowed.");
        }

        this.inputText = inputText;
        this.charCount = countCharacters(inputText); // Initialize character count
    }

    // Input validation method - Contributed by Putri Naqibah Balqis
    public boolean validateInput(String inputText) {
        return inputText.matches("[A-Z0-9 ]+");
    }

    // Method to count non-space characters - Contributed by Putri Naqibah Balqis
    public int countCharacters(String inputText) {
        return (int) inputText.chars().filter(c -> c != ' ').count();
    }

    // Method to generate a group shift from the groupID using hashCode -
    // Contributed by Putri Naqibah Balqis
    public int generateShift() {
        int groupShift = Math.abs(groupID.hashCode()) % 10 + 1;
        return groupShift + charCount; // Final shift = group shift + character count
    }

    // Method to apply a Caesar-style cipher with final shift (Encode or Decode) -
    // Contributed by Putri Naqibah Balqis
    public String applyCipher(String inputText, int shift, boolean isDecoding) {
        StringBuilder result = new StringBuilder();

        // Calculate the shifts for letters and digits
        int letterShift = shift % 26;
        int digitShift = shift % 10;

        // Adjust shifts for decoding
        if (isDecoding) {
            letterShift = (26 - letterShift) % 26;
            digitShift = (10 - digitShift) % 10;
        }

        for (char c : inputText.toCharArray()) {
            // Uppercase letters (A-Z)
            if (c >= 'A' && c <= 'Z') {
                char shifted = (char) ((c - 'A' + letterShift) % 26 + 'A');
                result.append(shifted);
            }
            // Digits (0-9)
            else if (c >= '0' && c <= '9') {
                char shifted = (char) ((c - '0' + digitShift) % 10 + '0');
                result.append(shifted);
            }
            // Spaces
            else if (c == ' ') {
                result.append(c);
            }
        }
        return result.toString();
    }

    // Method to encode the text - Contributed by Putri Naqibah Balqis
    public String encode() {
        int shift = generateShift();
        resultText = applyCipher(inputText, shift, false);
        return resultText;
    }

    // Method to decode the text - Contributed by Putri Naqibah Balqis
    public String decode() {
        int shift = generateShift();
        resultText = applyCipher(inputText, shift, true);
        return resultText;
    }

    // Getter for result text (for GUI usage) - Contributed by Putri Naqibah Balqis
    public String getResultText() {
        return resultText;
    }

    // Getter for final shift (for GUI usage) - Contributed by Putri Naqibah Balqis
    public int getFinalShift() {
        return generateShift();
    }

}