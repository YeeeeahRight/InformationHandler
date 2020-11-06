package com.epam.handler.data.model;

import com.epam.handler.enums.TextType;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private final List<TextComponent> textComponents = new ArrayList<>();

    @Override
    public TextType getTextType() {
        return TextType.COMPOSITE;
    }

    public List<TextComponent> getAllChild() {
        return textComponents;
    }

    public void addChild(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public String toString() {
        return "TextComposite{" + textComponents + '}';
    }
}
