import org.example.Calculator;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ComplexCalculationTests {
    private static Calculator calculator;
    private static JTextField textField;

    @BeforeAll
    public static void setUp() throws InterruptedException, InvocationTargetException {
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
        textField.setText("");
    }

    @AfterAll
    public static void tearDownAll() {
        textField = null;
        calculator = null;
    }

    @Test
    public void testAddAndMultiply() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("5+7*3+5*2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("36.0", textField.getText()));
    }

    @Test
    public void testSubtractAndDivide() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("(5+5)/2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("5.0", textField.getText()));
    }

    @Test
    public void testBODMASlongEquation() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("5+(6-8)/2-6"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("-2.0", textField.getText()));
    }

    @Test
    public void testComplexPower() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("5+5^(6+2)"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("390630.0", textField.getText()));
    }

}
