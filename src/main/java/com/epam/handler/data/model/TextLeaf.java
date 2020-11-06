package com.epam.handler.data.model;

import com.epam.handler.enums.TextType;

public class TextLeaf implements TextComponent {
    private final TextType textType;
    private final String data;

    public TextLeaf(TextType textType,String data) {
        this.textType = textType;
        this.data = data;
    }

    @Override
    public TextType getTextType() {
        return textType;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "TextLeaf{type = " + textType + ", data = " + data + '}';
    }
}
