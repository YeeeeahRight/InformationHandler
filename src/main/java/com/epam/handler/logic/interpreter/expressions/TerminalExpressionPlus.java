package com.epam.handler.logic.interpreter.expressions;

import com.epam.handler.logic.interpreter.Context;

public class TerminalExpressionPlus implements MathExpression {

    @Override
    public void interpret(Context context) {
        int firstNumber = context.popValue();
        int secondNumber = context.popValue();
        context.pushValue(firstNumber + secondNumber);
    }
}
