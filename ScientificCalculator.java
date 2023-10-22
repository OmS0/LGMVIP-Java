import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField displayTextField;
    private String currentNumber;
    private String operator;
    private double result;
    private boolean isResultDisplayed;

    public ScientificCalculator() {
        setTitle(" SCIENTIFIC CALCULATOR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayTextField = new JTextField();
        displayTextField.setEditable(false);
        displayTextField.setFont(new Font("Centaur", Font.BOLD, 30)); // Set the desired font size for the result
        add(displayTextField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 5));

        String[] buttonLabels = {
                "7", "8", "9", "/", "C",
                "4", "5", "6", "*", "AC",
                "1", "2", "3", "-", "M",
                "0", ".", "=", "+", "MR",
                "sin", "cos", "tan", "sqrt", "!",
                "x^y", "log", "ln", "pi", "MR+"
        };

        Font buttonFont = new Font("Centaur", Font.BOLD, 20); // Set the desired font size and style for the buttons

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(buttonFont);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setSize(420, 500); // Set the desired dimensions for the frame
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
                if (isResultDisplayed) {
                    displayTextField.setText("");
                    isResultDisplayed = false;
                }
                appendNumber(command);
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                setOperator(command);
                break;
            case "=":
                calculate();
                break;
            case "sin":
                calculateSin();
                break;
            case "cos":
                calculateCos();
                break;
            case "tan":
                calculateTan();
                break;
            case "sqrt":
                calculateSqrt();
                break;
            case "!":
                calculateFactorial();
                break;
            case "x^y":
                setOperator("^");
                break;
            case "log":
                calculateLog();
                break;
            case "ln":
                calculateLn();
                break;
            case "pi":
                displayTextField.setText(String.valueOf(Math.PI));
                break;
            case "C":
                clearEntry();
                break;
            case "AC":
                clearAll();
                break;
            case "M":
                saveToMemory();
                break;
            case "MR":
                recallMemory();
                break;
            case "MR+":
                addMemory();
                break;
        }
    }

    private void appendNumber(String number) {
        currentNumber = displayTextField.getText() + number;
        displayTextField.setText(currentNumber);
    }

    private void setOperator(String operator) {
        this.operator = operator;
        result = Double.parseDouble(displayTextField.getText());
        displayTextField.setText("");
    }

    private void calculate() {
        double secondNumber = Double.parseDouble(displayTextField.getText());
        double answer = 0.0;
        String calculation = "";

        switch (operator) {
            case "+":
                answer = result + secondNumber;
                calculation = result + " + " + secondNumber;
                break;
            case "-":
                answer = result - secondNumber;
                calculation = result + " - " + secondNumber;
                break;
            case "*":
                answer = result * secondNumber;
                calculation = result + " * " + secondNumber;
                break;
            case "/":
                answer = result / secondNumber;
                calculation = result + " / " + secondNumber;
                break;
            case "^":
                answer = Math.pow(result, secondNumber);
                calculation = result + " ^ " + secondNumber;
                break;
        }

        displayTextField.setText(calculation + " = " + answer);
        isResultDisplayed = true;
    }

    private void calculateSin() {
        double number = Double.parseDouble(displayTextField.getText());
        double answer = Math.sin(number);
        displayTextField.setText("sin(" + number + ") = " + answer);
        isResultDisplayed = true;
    }

    private void calculateCos() {
        double number = Double.parseDouble(displayTextField.getText());
        double answer = Math.cos(number);
        displayTextField.setText("cos(" + number + ") = " + answer);
        isResultDisplayed = true;
    }

    private void calculateTan() {
        double number = Double.parseDouble(displayTextField.getText());
        double answer = Math.tan(number);
        displayTextField.setText("tan(" + number + ") = " + answer);
        isResultDisplayed = true;
    }

    private void calculateSqrt() {
        double number = Double.parseDouble(displayTextField.getText());
        double answer = Math.sqrt(number);
        displayTextField.setText("sqrt(" + number + ") = " + answer);
        isResultDisplayed = true;
    }

    private void calculateFactorial() {
        int number = Integer.parseInt(displayTextField.getText());
        int answer = factorial(number);
        displayTextField.setText(number + "! = " + answer);
        isResultDisplayed = true;
    }

    private int factorial(int n) {
        if (n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }

    private void calculateLog() {
        double number = Double.parseDouble(displayTextField.getText());
        double answer = Math.log10(number);
        displayTextField.setText("log(" + number + ") = " + answer);
        isResultDisplayed = true;
    }

    private void calculateLn() {
        double number = Double.parseDouble(displayTextField.getText());
        double answer = Math.log(number);
        displayTextField.setText("ln(" + number + ") = " + answer);
        isResultDisplayed = true;
    }

    private void clearEntry() {
        displayTextField.setText("");
    }

    private void clearAll() {
        displayTextField.setText("");
        currentNumber = "";
        operator = "";
        result = 0.0;
    }

    private void saveToMemory() {
        double memory = Double.parseDouble(displayTextField.getText());
        this.result = memory;
    }

    private void recallMemory() {
        displayTextField.setText(String.valueOf(result));
    }

    private void addMemory() {
        double memory = Double.parseDouble(displayTextField.getText());
        this.result += memory;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScientificCalculator();
            }
        });
    }
}