package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @BeforeAll
    @Disabled
    public static void classSetUp() {
        //HACK: for demonstration purposes only
        System.out.println(
                "This is a CalculatorTest class method and takes place before any @Test is executed");
    }

    @AfterAll
    @Disabled
    public static void classTearDown() {
        //HACK: for demonstration purposes only
        System.out.println(
                "This is a CalculatorTest class method and takes place after all @Test are executed");
    }

    @BeforeEach
    @Disabled
    public void setUp() {
        //HACK: for demonstration purposes only
        System.out.println(
                "\tThis call takes place before each @Test is executed");
    }

    @AfterEach
    @Disabled
    public void tearDown() {
        //HACK: for demonstration purposes only
        System.out.println(
                "\tThis call takes place after each @Test is executed");
    }

    @Test
    @Disabled
    public void failingTest() {
        fail("a disabled failing test");
    }

    /**
     * Test to ensure two positive numbers are summed correctly.<p>
     * <p>
     * For demonstration purposes the Arrange/Act/Assert syntax is used:<p>
     * Arrange: one positive number (three) and another positive number (two).<p>
     * Act: sum both numbers (three and two).<p>
     * Assert: the result is five.
     */
    @Test
    @Disabled
    public void ensureThreePlusTwoEqualsFive() {

        //HACK: for demonstration purposes only
        System.out.println("\t\tExecuting " + new Object() {
        }.getClass().getEnclosingMethod().getName() + " Test");

        // Arrange
        int expectedResult = 5;
        int firsOperand = 3;
        int secondOperand = 2;
        int result = 3;

        // Act
        result = new Calculator().sum(firsOperand, secondOperand);

        // Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test to ensure positive and negative numbers are summed correctly.<p>
     * <p>
     * For demonstration purposes the Arrange/Act/Assert syntax is used:<p>
     * Arranje a positive number (three) and a negative number (minus two)<p>
     * Act I sum three to minus two<p>
     * Assert the sum result should be one.
     */
    @Test
    @Disabled
    public void ensureThreePlusMinusTwoEqualsOne() {
        //HACK: for demonstration purposes only
        System.out.println("\t\tExecuting " + new Object() {
        }.getClass().getEnclosingMethod().getName() + " Test");

        // Arrange
        int firsOperand = 3;
        int secondOperand = -2;
        int expectedResult = 1;
        int result = 3;

        // Act
        result = new Calculator().sum(firsOperand, secondOperand);

        // Assert
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @CsvSource({"2,2,4", "6,3,9", "-2,2,0", "0,0,0", "20,-400,-380", "0,1,1", "-1,-1,-2", "-1,1,0"})
    public void ensureAdditionIsCalculatedCorrectly(int a, int b, int expectedResult) {
        int actualResult = calculator.sum(a, b);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({"2000000000,200000000", "1000000000,2000000000", "-1000000000,-2000000000"})
    public void ensureAdditionOfTwoBigNumbersCauseUnsupportedOperationException(int a, int b) {
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> calculator.sum(a, b));
        Assertions.assertEquals("Result too big for int", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"2,2,0", "6,3,3", "0,2,-2", "0,0,0", "4,24,-20", "0,1,-1", "-1,-1,0", "-1,1,-2"})
    public void ensureSubtractionIsCalculatedCorrectly(int a, int b, int expectedResult) {
        int actualResult = calculator.subtract(a, b);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({"-2000000000,200000000", "-1000000000,2000000000", "1000000000,-2000000000"})
    public void ensureSubtractionOfTwoBigNumbersCauseUnsupportedOperationException(int a, int b) {
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> calculator.subtract(a, b));
        Assertions.assertEquals("Result too big for int", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"2,2,1", "6,3,2", "17039,88,193", "0,1,0", "4,24,0", "34684565,1,34684565", "-10,5,-2", "-1347544,42,-32085"})
    public void ensureDivisionIsCalculatedCorrectly(int a, int b, int expectedResult) {
        int actualResult = calculator.divide(a, b);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void ensureDivisionThrowsUnsupportedOperationExceptionWhenDivisorIsZero() {
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> calculator.divide(11, 0));
        Assertions.assertEquals("Divisor is zero", exception.getMessage());

    }

    @ParameterizedTest
    @CsvSource({"2,2,4", "3,6,18", "10,10,100", "0,1,0", "4,24,96", "7,5040,35280", "2,-2,-4", "-100,50,-5000", "0,0,0", "564,0,0"})
    public void ensureMultiplicationIsCalculatedCorrectly(int a, int b, int expectedResult) {
        int actualResult = calculator.multiply(a, b);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({"2000000000,200000000", "-300000000,6000000", "-1000000000,-200000000"})
    public void ensureMultiplicationOfTwoBigNumbersCauseUnsupportedOperationException(int a, int b) {
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> calculator.multiply(a, b));
        Assertions.assertEquals("Result too big for int", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1000, -66, -33, -21345678})
    public void ensureFactorialOfNegativeNumberThrowsUnsupportedOperationException(int input) {
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> calculator.factorial(input));
        Assertions.assertEquals("Operand cannot be lt zero", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"2,2", "3,6", "10,3628800", "0,1", "4,24", "7,5040", "12,479001600"})
    public void ensureFactorialIsCalculatedCorrectly(int input, int expectedResult) {
        int actualResult = calculator.factorial(input);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1000, 66, 33, 21345678})
    public void ensureFactorialThrowsUnsupportedOperationExceptionForBigFactor(int input) {
        UnsupportedOperationException exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> calculator.factorial(input));
        Assertions.assertEquals("Operand too big for int", exception.getMessage());
    }

}



