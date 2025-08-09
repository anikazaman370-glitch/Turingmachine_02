import javax.swing.*;
import java.awt.*;

public class TuringMachineSimulator extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton runButton;

    public TuringMachineSimulator() {
        setTitle("Turing Machine for L = {0^n1^n | n ≥ 1}");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputPanel.add(new JLabel("Enter input (only 0s and 1s):"), BorderLayout.NORTH);
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

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button listener
        runButton.addActionListener(e -> simulateTM(inputField.getText().trim()));

        setVisible(true);
    }

    private void simulateTM(String input) {
        outputArea.setText(""); // Clear previous output

        if (!input.matches("[01]+")) {
            outputArea.setText("Invalid input: Only 0s and 1s allowed.");
            return;
        }

        char[] tape = input.toCharArray();
        int head = 0;

        while (true) {
            // Step 1: Find the first unmarked '0'
            while (head < tape.length && (tape[head] == 'X' || tape[head] == 'Y' || tape[head] == '1')) {
                head++;
            }

            if (head == tape.length) break; // all symbols processed

            if (tape[head] == '0') {
                tape[head] = 'X'; // mark the 0
                outputArea.append("Marked 0 at index " + head + "\n");

                // Find the rightmost unmarked 1
                int temp = tape.length - 1;
                while (temp >= 0 && (tape[temp] == 'Y' || tape[temp] == 'X' || tape[temp] == '0')) {
                    temp--;
                }

                if (temp >= 0 && tape[temp] == '1') {
                    tape[temp] = 'Y'; // mark the 1
                    outputArea.append("Marked 1 at index " + temp + "\n");
                } else {
                    outputArea.append("No matching 1 for 0 → Rejected\n");
                    return;
                }

                head = 0; // Reset head for next scan
            } else {
                outputArea.append("Unexpected symbol at index " + head + " → Rejected\n");
                return;
            }
        }

        // Check if all symbols are marked correctly
        for (char c : tape) {
            if (c != 'X' && c != 'Y') {
                outputArea.append("Unprocessed symbol found → Rejected\n");
                return;
            }
        }

        outputArea.append("All symbols matched! ✅ Accepted\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TuringMachineSimulator::new);
    }
}




