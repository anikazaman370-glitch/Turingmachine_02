import javax.swing.*;
import java.awt.*;

public class TuringMachineBinaryDivision extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JButton runButton;

    public TuringMachineBinaryDivision() {
        setTitle("Turing Machine for Binary Division (e.g., 110/10)");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputPanel.add(new JLabel("Enter binary division (e.g., 110/10):"), BorderLayout.NORTH);
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

        runButton.addActionListener(e -> simulateDivision(inputField.getText().trim()));

        setVisible(true);
    }

    private void simulateDivision(String input) {
        outputArea.setText("");

        if (!input.matches("[01]+/[01]+")) {
            outputArea.setText("❌ Invalid input. Use format like 110/10");
            return;
        }

        // Split operands
        String[] parts = input.split("/");
        String dividendBin = parts[0];
        String divisorBin = parts[1];

        outputArea.append("Dividend : " + dividendBin + "\n");
        outputArea.append("Divisor  : " + divisorBin + "\n");

        int dividend = Integer.parseInt(dividendBin, 2);
        int divisor = Integer.parseInt(divisorBin, 2);

        if (divisor == 0) {
            outputArea.append("❌ Division by zero is undefined.");
            return;
        }

        if (dividend % divisor != 0) {
            outputArea.append("❌ Not divisible: Remainder exists.");
            return;
        }

        int quotient = dividend / divisor;
        String quotientBin = Integer.toBinaryString(quotient);

        // Display step-by-step
        outputArea.append("\nStep-by-step (Decimal):\n");
        outputArea.append(dividend + " ÷ " + divisor + " = " + quotient + "\n");

        outputArea.append("\nResult (Binary) : " + quotientBin + " ✅");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TuringMachineBinaryDivision::new);
    }
}
