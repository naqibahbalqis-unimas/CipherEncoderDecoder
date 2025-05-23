# Cipher Encoder/Decoder

## Project Description

The Cipher Encoder/Decoder is a Java application that implements a Caesar-style cipher with a dynamic shift calculated from a group ID. It features encoding and decoding functions that transform uppercase text and digits using a unique group shift, derived from a hashing mechanism. The application also includes a graphical user interface (GUI) for easy interaction.

## Features

- Encode and decode uppercase letters and digits with a Caesar-style cipher.
- Dynamic shift calculation using group ID ("G06/SE-G7").
- Error handling for invalid input (e.g., lowercase, special characters).
- GUI for user-friendly encoding and decoding.
- Clipboard support to copy encoded/decoded text.

## Installation

1. Ensure you have Java 17 installed:
   ```
   java -version
   ```
2. Run the JAR file:
   ```
   java -jar Cipher_Encoder_Decoder.jar
   ```

## Usage

1. Enter the text to encode or decode in the input field.
2. Click 'Encode' to encrypt the text or 'Decode' to decrypt it.
3. View the result in the output area.
4. Use the 'Copy' button to copy the result to the clipboard.

## Code Structure

- **Assignment.java:** Entry point that initializes the GUI.
- **GUI.java:** Main GUI class to interact with users.
- **Encoded.java:** Core logic for encoding and decoding text.
- **CipherTest.java:** Unit tests for encoding/decoding accuracy.

## Project Structure

```
project
├───.vscode
│   └───launch.json
├───bin
│   ├───Assignment.class
│   ├───CipherTest.class
│   ├───Encoded.class
│   └───GUI.class
├───lib
│   ├───hamcrest-core-1.3.jar
│   └───junit-4.13.2.jar
├───Assignment.java
├───CipherTest.java
├───Encoded.java
├───GUI.java
├───manifest.txt
└───README.md
```

## Dependencies

- **JUnit 4.13.2:** Unit testing framework
- **Hamcrest Core 1.3:** Assertion library for testing

## Contributions

- **Putri Naqibah Balqis:** Logic Developer, GUI Enhancements, Main entry point.
- **Calvin Joseph Param:** GUI Developer.
- **Siti Norlie Yana:** Backend Developer, Initial class structure.
- **Rawaidatul Aliah:** Unit Testing and Verification.
