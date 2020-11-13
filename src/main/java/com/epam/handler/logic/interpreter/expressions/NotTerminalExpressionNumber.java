package com.epam.handler.logic.interpreter.expressions;

import com.epam.handler.logic.interpreter.Context;

public class NotTerminalExpressionNumber implements MathExpression {
    private final int number;

    public NotTerminalExpressionNumber(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
