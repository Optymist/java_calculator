import org.example.Calculator;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleCalculationTests {
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
    public void testSimpleAdd() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("2+2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("4.0", textField.getText()));
    }

    @Test
    public void testSimpleSubtract() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("2-2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("0.0", textField.getText()));
    }

    @Test
    public void testSimpleMultiply() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("2×2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("4.0", textField.getText()));
    }

    @Test
    public void testSimpleDivide() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("2÷2"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("1.0", textField.getText()));
    }

    @Test
    public void testDivideByZero() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("2÷0"));

        SwingUtilities.invokeAndWait(() -> {
            assertThrows(RuntimeException.class, this::clickEquals);
            assertEquals("Can't ÷ by 0!", textField.getText());
        });
    }

    @Test
    public void testPower() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("2^5"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("32.0", textField.getText()));
    }

    @Test
    public void testSquareRoot() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> addText("√64"));
        SwingUtilities.invokeAndWait(this::clickEquals);
        SwingUtilities.invokeAndWait(() -> assertEquals("8.0", textField.getText()));
    }
}
