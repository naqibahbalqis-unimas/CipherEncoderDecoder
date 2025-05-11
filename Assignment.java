/*
 * Assignment.java
 * Putri Naqibah Balqis binti Zainol (100805)
 * Role: Logic Developer
 * Contribution: Created the main entry point to initialize the GUI.
 */

import javax.swing.SwingUtilities;

public class Assignment {
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> GUI.createAndShowGUI());
    }
}
