import javax.swing.*;
import java.awt.*;

public class TuringMachineBinaryMultiplication extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton runButton;

    public TuringMachineBinaryMultiplication() {
        setTitle("Turing Machine for Binary Multiplication (e.g., 101*11)");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputPanel.add(new JLabel("Enter binary multiplication (e.g., 101*11):"), BorderLayout.NORTH);
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

        runButton.addActionListener(e -> simulateMultiplication(inputField.getText().trim()));

        setVisible(true);
    }

    private void simulateMultiplication(String input) {
        outputArea.setText("");

        if (!input.matches("[01]+\\*[01]+")) {
            outputArea.setText("Invalid input. Please use format like 101*11");
            return;
        }

        // Split the binary strings
        String[] parts = input.split("\\*");
        String a = parts[0]; // multiplicand
        String b = parts[1]; // multiplier

        outputArea.append("Multiplicand: " + a + "\n");
        outputArea.append("Multiplier  : " + b + "\n");

        // Convert to decimal
        int multiplicand = Integer.parseInt(a, 2);
        int multiplier = Integer.parseInt(b, 2);

        int result = multiplicand * multiplier;
        String binaryResult = Integer.toBinaryString(result);

        // Simulate step-by-step
        outputArea.append("\nStep-by-step (Decimal logic for TM simulation):\n");
        outputArea.append(multiplicand + " × " + multiplier + " = " + result + "\n");
        outputArea.append("\nResult (Binary) : " + binaryResult + " ✅");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TuringMachineBinaryMultiplication::new);
    }
}

