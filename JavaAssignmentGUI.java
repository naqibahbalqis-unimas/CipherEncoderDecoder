import javax.swing.*;
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
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridBagLayout());
        JPanel resultPanel = new JPanel(new BorderLayout());
        JPanel buttonRow = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Components
        JLabel title = new JLabel("Enter text to encode or decode:", JLabel.LEFT);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));

        JTextField inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 16));

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
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(title, gbc);

        gbc.gridy++;
        inputPanel.add(inputField, gbc);

        buttonRow.add(encodeButton);
        buttonRow.add(decodeButton);

        gbc.gridy++;
        inputPanel.add(buttonRow, gbc);

        bottomButtons.add(copyButton);
        bottomButtons.add(clearButton);

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
                JOptionPane.showMessageDialog(frame, "Please enter some text.", "Input Required", JOptionPane.WARNING_MESSAGE);
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
                JOptionPane.showMessageDialog(frame, "Please enter some text.", "Input Required", JOptionPane.WARNING_MESSAGE);
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

        copyButton.addActionListener((ActionEvent e) -> {
            String output = resultArea.getText();
            if (!output.isEmpty()) {
                StringSelection selection = new StringSelection(output);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, null);
                JOptionPane.showMessageDialog(frame, "Output copied to clipboard.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Nothing to copy.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        clearButton.addActionListener((ActionEvent e) -> {
            resultArea.setText("");
        });
    }
}