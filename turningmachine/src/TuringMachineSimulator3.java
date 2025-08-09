import javax.swing.*;
import java.awt.*;

public class TuringMachineSimulator3 extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton runButton;

    public TuringMachineSimulator3() {
        setTitle("Turing Machine for L = 0ⁿ1ⁿ2ⁿ");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputPanel.add(new JLabel("Enter input (only 0s, 1s, and 2s):"), BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        runButton = new JButton("Run Turing Machine");
        buttonPanel.add(runButton);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Run button action
        runButton.addActionListener(e -> simulateTM(inputField.getText().trim()));

        setVisible(true);
    }

    private void simulateTM(String input) {
        outputArea.setText("");

        if (!input.matches("[012]+")) {
            outputArea.setText("Invalid input: Only 0s, 1s, and 2s allowed.");
            return;
        }

        char[] tape = input.toCharArray();
        int head = 0;

        // Simulate Turing Machine
        while (true) {
            // Step 1: Find unmarked 0 and mark it
            while (head < tape.length && (tape[head] == 'X' || tape[head] == 'Y' || tape[head] == 'Z' || tape[head] == '2')) {
                head++;
            }

            if (head == tape.length) break; // No more 0s

            if (tape[head] == '0') {
                tape[head] = 'X'; // mark 0
                outputArea.append("Marked 0 at index " + head + "\n");

                // Find next unmarked 1
                int i = head + 1;
                while (i < tape.length && (tape[i] == 'X' || tape[i] == 'Y' || tape[i] == '0')) i++;

                if (i < tape.length && tape[i] == '1') {
                    tape[i] = 'Y';
                    outputArea.append("Marked 1 at index " + i + "\n");
                } else {
                    outputArea.append("No matching 1 → Rejected ❌\n");
                    return;
                }

                // Find next unmarked 2
                int j = i + 1;
                while (j < tape.length && (tape[j] == 'X' || tape[j] == 'Y' || tape[j] == '0' || tape[j] == '1' || tape[j] == 'Z')) j++;

                if (j < tape.length && tape[j] == '2') {
                    tape[j] = 'Z';
                    outputArea.append("Marked 2 at index " + j + "\n");
                } else {
                    outputArea.append("No matching 2 → Rejected ❌\n");
                    return;
                }

                head = 0; // Reset for next triplet
            } else {
                outputArea.append("Unexpected symbol → Rejected ❌\n");
                return;
            }
        }

        // Final check: all should be marked as X, Y, Z
        for (char c : tape) {
            if (c != 'X' && c != 'Y' && c != 'Z') {
                outputArea.append("Unprocessed symbol → Rejected ❌\n");
                return;
            }
        }

        outputArea.append("All symbols matched: ✅ Accepted!\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TuringMachineSimulator3::new);
    }
}


