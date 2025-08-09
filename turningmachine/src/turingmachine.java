import javax.swing.*;
import java.awt.*;

public class turingmachine extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton runButton;

    public turingmachine() {
        setTitle("TM for L = {a^n b^m a^(n+m) | n,m ≥ 1}");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputPanel.add(new JLabel("Enter a string (only a’s and b’s):"), BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);

        // Output area
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Button panel
        JPanel buttonPanel = new JPanel();
        runButton = new JButton("Run Turing Machine");
        buttonPanel.add(runButton);

        // Add components
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        runButton.addActionListener(e -> simulateTM(inputField.getText().trim()));

        setVisible(true);
    }

    private void simulateTM(String input) {
        outputArea.setText("");

        if (!input.matches("[ab]+")) {
            outputArea.setText("❌ Invalid input: Only 'a' and 'b' allowed.");
            return;
        }

        char[] tape = input.toCharArray();
        int state = 0;
        int aCount = 0;
        int bCount = 0;
        int i = 0;

        // Phase 1: Count leading a's (n)
        while (i < tape.length && tape[i] == 'a') {
            aCount++;
            tape[i] = 'X'; // mark processed a
            outputArea.append("Marked 'a' at " + i + "\n");
            i++;
        }

        // Phase 2: Count b's (m)
        while (i < tape.length && tape[i] == 'b') {
            bCount++;
            tape[i] = 'Y'; // mark processed b
            outputArea.append("Marked 'b' at " + i + "\n");
            i++;
        }

        // Phase 3: Count final a's (must be exactly n + m)
        int finalACount = 0;
        while (i < tape.length && tape[i] == 'a') {
            finalACount++;
            outputArea.append("Counted trailing 'a' at " + i + "\n");
            i++;
        }

        // Reject if any other characters left
        if (i != tape.length) {
            outputArea.append("❌ Extra characters found → Rejected\n");
            return;
        }

        // Final checks
        if (aCount >= 1 && bCount >= 1 && finalACount == (aCount + bCount)) {
            outputArea.append("\nAccepted ✅\n");
        } else {
            outputArea.append("\nRejected ❌: Pattern mismatch\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(turingmachine::new);
    }
}

