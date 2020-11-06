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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TextLeaf)) {
            return false;
        }

        TextLeaf textLeaf = (TextLeaf) o;

        if (getTextType() != textLeaf.getTextType()) {
            return false;
        }
        return getData() != null ? getData().equals(textLeaf.getData()) : textLeaf.getData() == null;
    }

    @Override
    public int hashCode() {
        int result = getTextType() != null ? getTextType().hashCode() : 0;
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }
}
