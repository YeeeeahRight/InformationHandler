package com.handler.logic.interpreter.expression;

import com.epam.handler.logic.interpreter.ExpressionCalculator;
import org.junit.Assert;
import org.junit.Test;

public class ExpressionCalculatorTest {
    private static final String PLUS_EXPRESSION = "7 5 +";
    private static final String MINUS_EXPRESSION = "7 5 -";
    private static final String MULTIPLY_EXPRESSION = "7 5 *";
    private static final String DIVIDE_EXPRESSION = "10 5 /";
    private static final int EXPECTED_PLUS_RESULT = 12;
    private static final int EXPECTED_MINUS_RESULT = 2;
    private static final int EXPECTED_MULTIPLY_RESULT = 35;
    private static final int EXPECTED_DIVIDE_RESULT = 2;

    private final ExpressionCalculator expressionCalculator = new ExpressionCalculator();

    @Test
    public void testCalculateShouldReturnCorrectValueWhenUsingMultiply() {
        //given
        int result;
        //when
        result = expressionCalculator.calculate(MULTIPLY_EXPRESSION);
        //then
        Assert.assertEquals(EXPECTED_MULTIPLY_RESULT, result);
    }

    @Test
    public void testCalculateShouldReturnCorrectValueWhenUsingPlus() {
        //given
        int result;
        //when
        result = expressionCalculator.calculate(PLUS_EXPRESSION);
        //then
        Assert.assertEquals(EXPECTED_PLUS_RESULT, result);
    }

    @Test
    public void testCalculateShouldReturnCorrectValueWhenUsingMinus() {
        //given
        int result;
        //when
        result = expressionCalculator.calculate(MINUS_EXPRESSION);
        //then
        Assert.assertEquals(EXPECTED_MINUS_RESULT, result);
    }

    @Test
    public void testCalculateShouldReturnCorrectValueWhenUsingDivide() {
        //given
        int result;
        //when
        result = expressionCalculator.calculate(DIVIDE_EXPRESSION);
        //then
        Assert.assertEquals(EXPECTED_DIVIDE_RESULT, result);
    }
}
