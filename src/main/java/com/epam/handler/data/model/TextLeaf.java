package com.epam.handler.data.model;

import java.util.ArrayList;
import java.util.List;

public class TextLeaf implements TextComponent {
    private final LeafType leafType;
    private final String data;

    private TextLeaf(LeafType leafType, String data) {
        this.leafType = leafType;
        this.data = data;
    }

    public static TextLeaf createWord(String value)  {
        return new TextLeaf(LeafType.WORD, value);
    }

    public static TextLeaf createExpression(String value) {
        return new TextLeaf(LeafType.EXPRESSION, value);
    }

    public LeafType getLeafType() {
        return leafType;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return data;
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

        if (getLeafType() != textLeaf.getLeafType()) {
            return false;
        }
        return getData() != null ? getData().equals(textLeaf.getData()) : textLeaf.getData() == null;
    }

    @Override
    public int hashCode() {
        int result = getLeafType() != null ? getLeafType().hashCode() : 0;
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        return result;
    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(); //return empty list
    }
}
