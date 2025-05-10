import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;

public class JavaAssignmentGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JavaAssignmentGUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("G06/SE-G7 - Cipher Encoder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 450);

        // Panels
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        JPanel resultPanel = new JPanel(new BorderLayout());
        JPanel buttonRow = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Components
        JLabel title = new JLabel("Enter text to encode or decode:", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel infoLabel = new JLabel("Valid Input: UPPERCASE letters (A-Z), digits (0-9), and spaces");
        infoLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        infoLabel.setForeground(Color.GRAY);

        JTextField inputField = new JTextField();
        ((AbstractDocument) inputField.getDocument()).setDocumentFilter(new UppercaseDocumentFilter());
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        inputField.setToolTipText("Enter UPPERCASE letters (A-Z), digits (0-9), and spaces");

        JButton encodeButton = new JButton("Encode");
        encodeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        encodeButton.setBackground(Color.decode("#4CAF50"));
        encodeButton.setForeground(Color.white);

        JButton decodeButton = new JButton("Decode");
        decodeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        decodeButton.setBackground(Color.decode("#4CAF50"));
        decodeButton.setForeground(Color.white);

        JButton copyButton = new JButton("Copy Output");
        copyButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        copyButton.setBackground(Color.decode("#607D8B"));
        copyButton.setForeground(Color.white);

        JButton clearButton = new JButton("Clear Output");
        clearButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        clearButton.setBackground(Color.decode("#9E9E9E"));
        clearButton.setForeground(Color.white);

        JButton clearInputButton = new JButton("Clear Input");
        clearInputButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        clearInputButton.setBackground(Color.decode("#9E9E9E"));
        clearInputButton.setForeground(Color.white);

        JTextArea resultArea = new JTextArea(6, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createTitledBorder("Output"));

        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Layout setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(title, gbc);
        gbc.gridy++;
        inputPanel.add(infoLabel, gbc);
        gbc.gridy++;
        inputPanel.add(inputField, gbc);

        buttonRow.add(encodeButton);
        buttonRow.add(decodeButton);

        gbc.gridy++;
        inputPanel.add(buttonRow, gbc);

        bottomButtons.add(copyButton);
        bottomButtons.add(clearButton);
        bottomButtons.add(clearInputButton);
        gbc.gridy++;
        inputPanel.add(bottomButtons, gbc);

        // Add panels
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);

        // Action Listeners
        encodeButton.addActionListener((ActionEvent e) -> {
            String text = inputField.getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter some text.", "Input Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Encoded encoder = new Encoded(text);
                String encodedText = encoder.encode();
                int shift = encoder.getFinalShift();
                resultArea.setText("Encoded Text: " + encodedText +
                        "\nFinal Shift: " + shift);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        decodeButton.addActionListener((ActionEvent e) -> {
            String text = inputField.getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter some text.", "Input Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Encoded decoder = new Encoded(text);
                String decodedText = decoder.decode();
                int shift = decoder.getFinalShift();
                resultArea.setText("Decoded Text: " + decodedText +
                        "\nFinal Shift: " + shift);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearInputButton.addActionListener((ActionEvent e) -> {
            inputField.setText("");
        });

        copyButton.addActionListener((ActionEvent e) -> {
            String output = resultArea.getText();
            if (!output.isEmpty()) {
                // Extract only the encoded or decoded text from the result area
                String[] lines = output.split("\n");
                String encodedText = "";
                for (String line : lines) {
                    if (line.startsWith("Encoded Text: ") || line.startsWith("Decoded Text: ")) {
                        encodedText = line.replaceFirst("Encoded Text: ", "").replaceFirst("Decoded Text: ", "");
                        break;
                    }
                }

                if (!encodedText.isEmpty()) {
                    StringSelection selection = new StringSelection(encodedText);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, null);
                    JOptionPane.showMessageDialog(frame, "Encoded/Decoded text copied to clipboard.", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "No encoded or decoded text found.", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Nothing to copy.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        clearButton.addActionListener((ActionEvent e) -> {
            resultArea.setText("");
        });
    }

    private static class UppercaseDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                throws BadLocationException {
            fb.insertString(offset, text.toUpperCase(), attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            fb.replace(offset, length, text.toUpperCase(), attrs);
        }
    }
}