package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

class Calculator extends JFrame implements ActionListener {
    private static final int buttonSizeX = 50;
    private static final int buttonSizeY = 50;

    private static final HashMap<String, JButton> buttons = new HashMap<>();

    private static JFrame frame;
    private static JTextField textField;
    private static final JPanel panel = new JPanel();

    String inputToCalculate;

    Calculator() {
        frame = new JFrame("Calculator");
        textField = new JTextField(30);
        textField.setToolTipText("Type here...");
        inputToCalculate = "";
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        Calculator calculator = new Calculator();

        textField.setBounds(10, 10, 180, 30);

        for (int i = 0; i < 10; i++) {
            buttons.put("n" + i, new JButton(String.valueOf(i)));
        }

        addNonNumberButtonsToMap();

        addActionListeners(calculator);
        addNumBounds();
        addNonNumBounds();

        panel.setLayout(null);
        panel.add(textField);

        addToPanel();

        panel.setBackground(Color.BLUE);

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

    private static void addNonNumberButtonsToMap() {
        buttons.put("oP", new JButton("+"));
        buttons.put("oM", new JButton("-"));
        buttons.put("oT", new JButton("×"));
        buttons.put("oD", new JButton("÷"));
        buttons.put("dec", new JButton("."));
        buttons.put("clear", new JButton("C"));
        buttons.put("oEq", new JButton("="));
        buttons.put("del", new JButton("<-"));
        buttons.put("colour", new JButton("Colour"));
    }

    private static void addActionListeners(Calculator calculator) {
        for (JButton button : buttons.values()) {
            button.addActionListener(calculator);
        }
    }

    private static void addToPanel() {
        for (JButton button : buttons.values()) {
            panel.add(button);
        }
    }

    private static void addNumBounds() {
        int startX = 10;
        int startY = 50;
        int gap = 5;
        for (int i = 1; i <= 9; i++) {
            int col = (i-1)%3;
            int row = (i-1)/3;
            buttons.get("n" + i).setBounds(startX + col * (buttonSizeX+gap), startY + row * (buttonSizeY + gap),
                    buttonSizeX, buttonSizeY);
        }

        buttons.get("n0").setBounds(startX + buttonSizeX + gap, startY + 3 * (buttonSizeY+gap), buttonSizeX, buttonSizeY);
    }

    private static void addNonNumBounds() {
        int gap = 5;
        int firstColX = 200;
        int secColX = firstColX + 50 + gap;
        int firstRowY = 50;
        int secRowY = firstRowY + firstRowY + gap;
        int thirdRowY = secRowY + firstRowY + gap;
        int fourthRowY = thirdRowY + firstRowY + gap;

        buttons.get("colour").setBounds(5, fourthRowY + firstRowY + gap*2, buttonSizeX+50, buttonSizeY);
        buttons.get("del").setBounds(firstColX, firstRowY-40, buttonSizeX, buttonSizeY-15);
        buttons.get("oP").setBounds(firstColX, firstRowY, buttonSizeX, buttonSizeY);
        buttons.get("oM").setBounds(secColX, firstRowY, buttonSizeX, buttonSizeY);
        buttons.get("oT").setBounds(firstColX, secRowY, buttonSizeX, buttonSizeY);
        buttons.get("oD").setBounds(secColX, secRowY, buttonSizeX, buttonSizeY);
        buttons.get("dec").setBounds(firstColX, thirdRowY, buttonSizeX, buttonSizeY);
        buttons.get("clear").setBounds(secColX, thirdRowY, buttonSizeX, buttonSizeY);
        buttons.get("oEq").setBounds(firstColX+gap, fourthRowY, buttonSizeX*2, buttonSizeY);
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String s = actionEvent.getActionCommand();
        inputToCalculate = textField.getText();

        switch (s) {
            case "clear":
                textField.setText("");
                break;
            case "<-":
                if (!inputToCalculate.isEmpty()) {
                    inputToCalculate = inputToCalculate.substring(0, inputToCalculate.length()-1);
                    textField.setText(inputToCalculate);
                }
                break;
            case "=":
                calculate();
                break;
            case "Colour":
                Color newColour = JColorChooser.showDialog(frame, "Choose colour", panel.getBackground());
                if (newColour != null) {
                    panel.setBackground(newColour);
                }
                break;
            default:
                inputToCalculate += s;
                textField.setText(inputToCalculate);
        }
    }

    private void calculate() {
        JOptionPane.showMessageDialog(frame, "Calculating: " + inputToCalculate);
    }
}