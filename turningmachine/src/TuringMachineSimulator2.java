import javax.swing.*;
import java.awt.*;

public class TuringMachineSimulator2 extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton runButton;

    public TuringMachineSimulator2() {
        setTitle("Turing Machine for L = 0(1)*0");
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

        if (!input.matches("[01]+")) {
            outputArea.setText("Invalid input: Only 0s and 1s allowed.");
            return;
        }

        if (input.length() < 2) {
            outputArea.append("Too short. Must start and end with 0. → Rejected\n");
            return;
        }

        if (input.charAt(0) != '0') {
            outputArea.append("Doesn't start with 0. → Rejected\n");
            return;
        }

        if (input.charAt(input.length() - 1) != '0') {
            outputArea.append("Doesn't end with 0. → Rejected\n");
            return;
        }

        // Now check the middle section (should be all 1s, can be empty)
        String middle = input.substring(1, input.length() - 1);
        if (middle.isEmpty() || middle.matches("1*")) {
            outputArea.append("Accepted ✅\n");
        } else {
            outputArea.append("Middle contains invalid characters. → Rejected ❌\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TuringMachineSimulator2::new);
    }
}
