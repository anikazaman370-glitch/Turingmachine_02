import javax.swing.*;
import java.awt.*;

public class TuringMachineBinaryAddition extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton runButton;

    public TuringMachineBinaryAddition() {
        setTitle("Turing Machine for Binary Addition (e.g., 101+11)");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputPanel.add(new JLabel("Enter binary addition (e.g., 101+11):"), BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);

        // Output area
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Button
        JPanel buttonPanel = new JPanel();
        runButton = new JButton("Run Turing Machine");
        buttonPanel.add(runButton);

        // Add to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        runButton.addActionListener(e -> simulateTM(inputField.getText().trim()));

        setVisible(true);
    }

    private void simulateTM(String input) {
        outputArea.setText("");

        // Validate input
        if (!input.matches("[01]+\\+[01]+")) {
            outputArea.setText("Invalid input. Please enter format like 101+11");
            return;
        }

        // Split input
        String[] parts = input.split("\\+");
        String a = parts[0];
        String b = parts[1];

        outputArea.append("Input A: " + a + "\n");
        outputArea.append("Input B: " + b + "\n");

        // Pad shorter number with leading 0s
        int maxLen = Math.max(a.length(), b.length());
        a = String.format("%" + maxLen + "s", a).replace(' ', '0');
        b = String.format("%" + maxLen + "s", b).replace(' ', '0');

        // Simulate addition from right to left
        StringBuilder result = new StringBuilder();
        int carry = 0;

        for (int i = maxLen - 1; i >= 0; i--) {
            int bitA = a.charAt(i) - '0';
            int bitB = b.charAt(i) - '0';
            int sum = bitA + bitB + carry;

            result.append(sum % 2);
            carry = sum / 2;

            outputArea.append("Adding: " + bitA + " + " + bitB + " + carry → " + (sum % 2) + " (new carry: " + carry + ")\n");
        }

        if (carry != 0) {
            result.append('1');
            outputArea.append("Final carry: 1\n");
        }

        outputArea.append("Output: " + result.reverse().toString() + " ✅");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TuringMachineBinaryAddition::new);
    }
}
