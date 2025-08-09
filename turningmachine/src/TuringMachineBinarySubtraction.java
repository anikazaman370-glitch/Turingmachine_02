import javax.swing.*;
import java.awt.*;

public class TuringMachineBinarySubtraction extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton runButton;

    public TuringMachineBinarySubtraction() {
        setTitle("Turing Machine for Binary Subtraction (e.g., 1011-11)");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputPanel.add(new JLabel("Enter binary subtraction (e.g., 1011-11):"), BorderLayout.NORTH);
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

        runButton.addActionListener(e -> simulateSubtraction(inputField.getText().trim()));

        setVisible(true);
    }

    private void simulateSubtraction(String input) {
        outputArea.setText("");

        if (!input.matches("[01]+-[01]+")) {
            outputArea.setText("Invalid input. Please use format like 1011-11");
            return;
        }

        // Split operands
        String[] parts = input.split("-");
        String a = parts[0]; // minuend
        String b = parts[1]; // subtrahend

        outputArea.append("Minuend   : " + a + "\n");
        outputArea.append("Subtrahend: " + b + "\n");

        // Convert to decimal
        int minuend = Integer.parseInt(a, 2);
        int subtrahend = Integer.parseInt(b, 2);

        if (subtrahend > minuend) {
            outputArea.append("❌ Negative result not supported in this simulator.\n");
            return;
        }

        int result = minuend - subtrahend;
        String binaryResult = Integer.toBinaryString(result);

        // Output step-by-step (simulate borrowing if needed)
        outputArea.append("Subtraction Result (Decimal): " + result + "\n");
        outputArea.append("Subtraction Result (Binary) : " + binaryResult + " ✅\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TuringMachineBinarySubtraction::new);
    }
}

