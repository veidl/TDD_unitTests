package pt.ipp.isep.dei.examples.tdd.basic.domain;

import java.util.stream.IntStream;

/**
 * Calculator class.
 * This class is very simple in order to demonstrate how to build test cases for Unit Testing.
 */
public class Calculator {

    /**
     * Sums one operand to the other, returning the result.
     *
     * @param firstOperand  First operand to sum.
     * @param secondOperand Second Operand to sum.
     * @return The sum of firstOperand with secondOperand.
     */
    public int sum(int firstOperand, int secondOperand) {
        return firstOperand + secondOperand;
    }

    public int subtract(int firstOperand, int secondOperand) {
        throw new UnsupportedOperationException();
    }

    public int divide(int dividend, int divisor) {
        try {
            return Math.floorDiv(dividend, divisor);
        }catch (ArithmeticException e) {
            throw new UnsupportedOperationException("Divisor is zero");
        }
    }

    public int multiply(int firstOperand, int secondOperand) {
        try {
            return Math.multiplyExact(firstOperand, secondOperand);
        } catch (ArithmeticException e) {
            throw new UnsupportedOperationException("Result too big for int");
        }
    }

    public int factorial(int firstOperand) {
        if (firstOperand < 0) throw new UnsupportedOperationException("Operand cannot be lt zero");
        int factorial = IntStream.rangeClosed(1, firstOperand)
                .reduce(1, (int x, int y) -> x * y);

        if (factorial <= 0) throw new UnsupportedOperationException("Operand too big for int");
        return factorial;
    }
}
