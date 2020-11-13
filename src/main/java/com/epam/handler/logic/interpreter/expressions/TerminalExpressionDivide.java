package com.epam.handler.logic.interpreter.expressions;

import com.epam.handler.logic.interpreter.Context;

public class TerminalExpressionDivide implements MathExpression {

    @Override
    public void interpret(Context context) {
        int secondNumber = context.popValue();
        int firstNumber = context.popValue();
        context.pushValue(firstNumber / secondNumber);
    }
}
