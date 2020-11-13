package com.epam.handler.logic.interpreter;

import com.epam.handler.logic.interpreter.expressions.*;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpressionCalculator {
    private static final Logger LOGGER = Logger.getLogger(ExpressionCalculator.class);
    private static final int FIRST_CHAR_INDEX = 0;
    private static final int SECOND_CHAR_INDEX = 1;
    private static final String EXPRESSION_SEPARATOR = " ";

    public int calculate(String expression) {
        LOGGER.info("Calculating this expression: " + expression);
        expression = removeExpressionBrackets(expression);
        List<MathExpression> mathExpressions = parse(expression);
        Context context = new Context();
        for (MathExpression terminal : mathExpressions) {
            terminal.interpret(context);
        }
        int result = context.popValue();
        LOGGER.info("Result = " + result);
        return result;
    }

    private String removeExpressionBrackets(String expressionValue) {
        int lastCharIndex = expressionValue.length() - 1;
        return expressionValue.substring(SECOND_CHAR_INDEX, lastCharIndex); // [second, last)
    }

    private List<MathExpression> parse(String expressionString) {
        List<MathExpression> expressionList = new ArrayList<>();
        String[] expressions = expressionString.split(EXPRESSION_SEPARATOR);
        for (String lexeme : expressions) {
            if (lexeme.isEmpty()) {
                continue;
            }
            ExpressionType expressionType = getExpressionType(lexeme);
            switch (expressionType) {
                case PLUS:
                    expressionList.add(new TerminalExpressionPlus());
                    break;
                case MINUS:
                    expressionList.add(new TerminalExpressionMinus());
                    break;
                case MULTIPLY:
                    expressionList.add(new TerminalExpressionMultiply());
                    break;
                case DIVIDE:
                    expressionList.add(new TerminalExpressionDivide());
                    break;
                case NOT_TERMINAL:
                    Scanner scanner = new Scanner(lexeme);
                    if (scanner.hasNextInt()) {
                        int number = scanner.nextInt();
                        expressionList.add(new NotTerminalExpressionNumber(number));
                    }
                    break; //for future
            }
        }
        return expressionList;
    }

    private ExpressionType getExpressionType(String expressionString) {
        ExpressionType expressionType;
        char firstExpressionChar = expressionString.charAt(FIRST_CHAR_INDEX);
        switch (firstExpressionChar) {
            case '+':
                expressionType = ExpressionType.PLUS;
                break;
            case '-':
                expressionType = ExpressionType.MINUS;
                break;
            case '/':
                expressionType =ExpressionType.DIVIDE;
                break;
            case '*':
                expressionType = ExpressionType.MULTIPLY;
                break;
            default:
                expressionType = ExpressionType.NOT_TERMINAL;
        }
        if (expressionString.length() > 1 &&
                (expressionType == ExpressionType.PLUS || expressionType == ExpressionType.MINUS))  {
            expressionType = ExpressionType.NOT_TERMINAL;
        }
        return expressionType;
    }
}

