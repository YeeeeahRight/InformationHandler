package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.enums.LeafType;

public class WordParser extends AbstractParser {
    public WordParser() {
        super(null);
    }

    @Override
    public TextComponent parse(String text) {
        char firstSymbol = text.charAt(0);
        int lastSymbolIndex = text.length() - 1;
        char lastSymbol = text.charAt(lastSymbolIndex);
        LeafType leafType;
        if (firstSymbol == '[' && lastSymbol == ']') {
            text = removeSpaces(text);
            leafType = LeafType.EXPRESSION;
        } else {
            leafType = LeafType.WORD;
        }
        return new TextLeaf(leafType, text);
    }

    private String removeSpaces(String expression) {
        StringBuilder validExpression = new StringBuilder();
        for (Character character : expression.toCharArray()) {
            if (character != ' ') {
                validExpression.append(character);
            }
        }
        return validExpression.toString();
    }
}
