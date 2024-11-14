import org.example.Calculator;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class NegativeCalculationTests {
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
    public void testNegativeAddNegative() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("-2+-2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("-4.0", textField.getText()));
    }

    @Test
    public void testNegativeAddPositive() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("-2+2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("0.0", textField.getText()));
    }

    @Test
    public void testPositiveAddNegative() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("-2+-2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("-4.0", textField.getText()));
    }

    @Test
    public void testNegativeSubtractNegative() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("-2--2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("0.0", textField.getText()));
    }

    @Test
    public void testPositiveSubtractNegative() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("2--2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("4.0", textField.getText()));
    }

    @Test
    public void testNegativeSubtractPositive() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("-2-2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("-4.0", textField.getText()));
    }

    @Test
    public void testNegativeMultiplyNegative() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("-2×-2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("4.0", textField.getText()));
    }

    @Test
    public void testNegativeMultiplyPositive() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("-4×2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("-8.0", textField.getText()));
    }

    @Test
    public void testNegativeDividePositive() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("-2÷2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("-1.0", textField.getText()));
    }

    @Test
    public void testNegativeDivideNegative() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("-4÷-2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("2.0", textField.getText()));
    }
}
