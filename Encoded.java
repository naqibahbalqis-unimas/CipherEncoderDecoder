// Contributed by Siti Norlie Yana (101059) & Putri Naqibah Balqis Binti Zainol (100805)

public class Encoded {
    private String inputText;
    private int charCount;
    private String resultText;
    private final String groupID = "G06/SE-G7";

    // Constructor
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

    // Method to validate input: only uppercase letters, digits, and spaces allowed
    public boolean validateInput(String inputText) {
        return inputText.matches("[A-Z0-9 ]+");
    }

    // Method to count non-space characters
    public int countCharacters(String inputText) {
        return (int) inputText.chars().filter(c -> c != ' ').count();
    }

    // Method to generate a group shift from the groupID using hashCode
    public int generateShift() {
        int groupShift = Math.abs(groupID.hashCode()) % 10 + 1;
        return groupShift + charCount; // Final shift = group shift + character count
    }

    // Method to apply a Caesar-style cipher with final shift (Encode or Decode)
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

    // Method to encode the text
    public String encode() {
        int shift = generateShift();
        resultText = applyCipher(inputText, shift, false);
        return resultText;
    }

    // Method to decode the text
    public String decode() {
        int shift = generateShift();
        resultText = applyCipher(inputText, shift, true);
        return resultText;
    }

    // Method to print the encoded or decoded result
    public void printResult(boolean isDecoding) {
        int shift = generateShift();
        resultText = applyCipher(inputText, shift, isDecoding);

        if (isDecoding) {
            System.out.println("Decoded Text: " + resultText);
        } else {
            System.out.println("Encoded Text: " + resultText);
        }
        System.out.println("Final Shift: " + shift);
    }

    // Getter for result text (for GUI usage)
    public String getResultText() {
        return resultText;
    }

    // Getter for final shift (for GUI usage)
    public int getFinalShift() {
        return generateShift();
    }

}