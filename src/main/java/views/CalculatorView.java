package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton derivativeButton;
    private JButton integrationButton;

    private JTextField firstPolynomial;
    private JTextField secondPolynomial;

    private JTextArea resultPolynomial;
    private JTextArea remainderPolynomial;

    public CalculatorView() {
        this.setBounds(100, 100, 300, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        addButton = new JButton("+");
        addButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        addButton.setBounds(49, 225, 50, 50);
        this.getContentPane().add(addButton);

        subtractButton = new JButton("-");
        subtractButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        subtractButton.setBounds(116, 225, 50, 50);
        this.getContentPane().add(subtractButton);

        multiplyButton = new JButton("x");
        multiplyButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        multiplyButton.setBounds(183, 225, 50, 50);
        this.getContentPane().add(multiplyButton);

        divideButton = new JButton("/");
        divideButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        divideButton.setBounds(49, 292, 50, 50);
        this.getContentPane().add(divideButton);

        derivativeButton = new JButton("der");
        derivativeButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        derivativeButton.setBounds(116, 292, 51, 50);
        this.getContentPane().add(derivativeButton);

        integrationButton = new JButton("int");
        integrationButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        integrationButton.setBounds(183, 292, 50, 50);
        this.getContentPane().add(integrationButton);

        firstPolynomial = new JTextField();
        firstPolynomial.setBounds(30, 50, 230, 25);
        this.getContentPane().add(firstPolynomial);
        firstPolynomial.setColumns(10);

        secondPolynomial = new JTextField();
        secondPolynomial.setBounds(30, 100, 230, 25);
        this.getContentPane().add(secondPolynomial);
        secondPolynomial.setColumns(10);

        resultPolynomial = new JTextArea();
        resultPolynomial.setBounds(30, 150, 230, 35);
        resultPolynomial.setLineWrap(true);
        this.getContentPane().add(resultPolynomial);

        remainderPolynomial = new JTextArea();
        remainderPolynomial.setBounds(30, 190, 230, 25);
        remainderPolynomial.setLineWrap(true);
        this.getContentPane().add(remainderPolynomial);

        JLabel firstPolynomialLabel = new JLabel("First polynomial:");
        firstPolynomialLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        firstPolynomialLabel.setBounds(30, 20, 250, 30);
        this.getContentPane().add(firstPolynomialLabel);

        JLabel secondPolynomialLabel = new JLabel("Second polynomial:");
        secondPolynomialLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        secondPolynomialLabel.setBounds(30, 70, 250, 30);
        this.getContentPane().add(secondPolynomialLabel);

        JLabel resultLabel = new JLabel("Result and remainder:");
        resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        resultLabel.setBounds(30, 120, 250, 30);
        this.getContentPane().add(resultLabel);

        this.setVisible(true);
    }

    public String getFirstPolynomial() {
        return firstPolynomial.getText();
    }

    public void setFirstPolynomial(JTextField firstPolynomial) {
        this.firstPolynomial = firstPolynomial;
    }

    public String getSecondPolynomial() {
        return secondPolynomial.getText();
    }

    public void setSecondPolynomial(JTextField secondPolynomial) {
        this.secondPolynomial = secondPolynomial;
    }

    public JTextArea getResultPolynomial() {
        return resultPolynomial;
    }

    public void setResultPolynomial(String resultPolynomial) {
        this.resultPolynomial.setText(resultPolynomial);
    }

    public void setRemainderPolynomial(String remainderPolynomial) {
        this.remainderPolynomial.setText(remainderPolynomial);
    }

    public void addAddListener(ActionListener action) {
        addButton.addActionListener(action);
    }

    public void addSubtractListener(ActionListener action) {
        subtractButton.addActionListener(action);
    }

    public void addMultiplyListener(ActionListener action) {
        multiplyButton.addActionListener(action);
    }

    public void addDerivativeListener(ActionListener action) {
        derivativeButton.addActionListener(action);
    }

    public void addIntegralListener(ActionListener action) {
        integrationButton.addActionListener(action);
    }

    public void addDivisionListener(ActionListener action) {
        divideButton.addActionListener(action);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ALERT", JOptionPane.ERROR_MESSAGE);
        refresh();
    }

    public void refresh() {
        firstPolynomial.setText(null);
        secondPolynomial.setText(null);
    }

    public void refreshBoxes() {
        resultPolynomial.setText(null);
        remainderPolynomial.setText(null);
    }

    public void refreshSecondPolynomial() {
        secondPolynomial.setText(null);
    }
}
