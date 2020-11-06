package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.enums.TextType;

public class WordParser extends AbstractParser {

    public WordParser() {
        super(null);
    }

    @Override
    public TextComponent parse(String text) {
        char firstSymbol = text.charAt(0);
        int lastSymbolIndex = text.length() - 1;
        char lastSymbol = text.charAt(lastSymbolIndex);
        TextType textType;
        if (firstSymbol == '[' && lastSymbol == ']') {
            textType = TextType.EXPRESSION;
        } else {
            textType = TextType.WORD;
        }
        return new TextLeaf(textType, text);
    }
}
