import org.example.Calculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecimalCalculationTests {
    private Calculator calculator;
    private JTextField textField;

    @BeforeEach
    public void setUp() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            calculator = new Calculator();
            Calculator.main(new String[]{});
            textField = calculator.getTextField();
        });

    }

    public void clickEquals() {
        HashMap<String, JButton> buttons = calculator.getButtons();
        ActionEvent actionEvent = new ActionEvent(buttons.get("oEq"), ActionEvent.ACTION_PERFORMED, "=");
        buttons.get("oEq").doClick();
        calculator.actionPerformed(actionEvent);
    }

    public void addText(String calc) {
        textField.setText(calc);
    }

    @AfterEach
    public void tearDown() {
        textField = null;
    }

    @Test
    public void testDecAdd() throws InterruptedException, InvocationTargetException {
        setUp();

        SwingUtilities.invokeAndWait(() -> addText("2.25+2.5"));

        SwingUtilities.invokeAndWait(this::clickEquals);

        SwingUtilities.invokeAndWait(() -> {
            textField = calculator.getTextField();
            assertEquals("4.75", textField.getText());
        });
    }

    @Test
    public void testDecSubtract() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("2.75-2.5"));

        SwingUtilities.invokeAndWait(this::clickEquals);

        SwingUtilities.invokeAndWait(() -> {
            textField = calculator.getTextField();
            assertEquals("0.25", textField.getText());
        });
    }

    @Test
    public void testDecMultiply() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("2.75×2.5"));

        SwingUtilities.invokeAndWait(this::clickEquals);

        SwingUtilities.invokeAndWait(() -> {
            textField = calculator.getTextField();
            assertEquals("6.875", textField.getText());
        });
    }

    @Test
    public void testDecDivide() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("6.8÷2.5"));

        SwingUtilities.invokeAndWait(this::clickEquals);

        SwingUtilities.invokeAndWait(() -> {
            textField = calculator.getTextField();
            assertEquals("2.72", textField.getText());
        });
    }
}
