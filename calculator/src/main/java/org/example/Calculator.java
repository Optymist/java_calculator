package org.example;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

class Calculator extends JFrame implements ActionListener {
    private static int buttonSizeX = 50;
    private static int buttonSizeY = 50;

    private static HashMap<String, JButton> buttons = new HashMap<>();

    static JFrame frame;
    static JTextField textField;

    String s0, s1, s2;

    Calculator() {
        frame = new JFrame("Calculator");
        textField = new JTextField(20);
        s0 = s1 = s2 = "";
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        Calculator calculator = new Calculator();

        textField.setEditable(false);
        textField.setBounds(10, 10, 180, 30);

        for (int i = 0; i < 10; i++) {
            buttons.put("n" + i, new JButton(String.valueOf(i)));
        }

        //        addNonNumberButtonsToMap();

        addActionListeners(calculator);
        addNumBounds();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(textField);

        addToPanel(panel);

        panel.setBackground(Color.BLUE);

        frame.add(panel);
        frame.setSize(200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

//    private static void addNonNumberButtonsToMap() {
//        buttons.put("oEq", new JButton("="));
//        buttons.put("oP", new JButton("+"));
//        buttons.put("oM", new JButton("-"));
//        buttons.put("oT", new JButton("*"));
//        buttons.put("oD", new JButton("/"));
//        buttons.put("clear", new JButton("C"));
//        buttons.put("dec", new JButton("."));
//    }

    private static void addActionListeners(Calculator calculator) {
        for (JButton button : buttons.values()) {
            button.addActionListener(calculator);
        }
    }

    private static void addToPanel(JPanel panel) {
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



    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}