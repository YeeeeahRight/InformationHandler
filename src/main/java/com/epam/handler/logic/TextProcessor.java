package com.epam.handler.logic;

import com.epam.handler.data.model.LeafType;
import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.logic.interpreter.ExpressionCalculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextProcessor {
    private static final int SECOND_CHAR_INDEX = 1;
    private final ExpressionCalculator expressionCalculator;

    public TextProcessor(ExpressionCalculator expressionCalculator) {
        this.expressionCalculator = expressionCalculator;
    }

    public String restoreText(TextComponent textComponent) {
        List<TextComponent> children = textComponent.getChildren();
        if (children.isEmpty()) {
            TextLeaf textLeaf = (TextLeaf) textComponent;
            return textLeaf.getData() + " ";
        }
        StringBuilder dataBuilder = new StringBuilder();
        TextComposite textComposite = (TextComposite) textComponent;
        for (TextComponent component : textComposite.getChildren()) {
            String data = restoreText(component);
            dataBuilder.append(data);
        }
        return dataBuilder.toString();
    }

    public TextComponent calculateExpressions(TextComponent component) {
        List<TextComponent> children = component.getChildren();
        if (children.isEmpty()) {
            TextLeaf leaf = (TextLeaf) component;
            return calculateLeafExpression(leaf);
        }
        List<TextComponent> resolvedComponents = new ArrayList<>();
        for (TextComponent child : children) {
            TextComponent resolvedChild = calculateExpressions(child);
            resolvedComponents.add(resolvedChild);
        }
        TextComposite textComposite = new TextComposite();
        textComposite.addChildren(resolvedComponents);
        return textComposite;
    }

    private TextLeaf calculateLeafExpression(TextLeaf leaf) {
        LeafType leafType = leaf.getLeafType();
        if (leafType != LeafType.EXPRESSION) {
            return leaf;
        }
        String expressionValue = leaf.getData();
        expressionValue = removeExpressionBrackets(expressionValue);
        int result = expressionCalculator.calculate(expressionValue);
        String stringResult = Integer.toString(result);
        return TextLeaf.createWord(stringResult);
    }

    private String removeExpressionBrackets(String expressionValue) {
        int lastCharIndex = expressionValue.length() - 1;
        return expressionValue.substring(SECOND_CHAR_INDEX, lastCharIndex); // [second, last)
    }

    public TextComponent sortParagraphsBySentenceAmount(TextComponent text) {
        List<TextComponent> sortedParagraphs = new ArrayList<>(text.getChildren());
        sortedParagraphs.sort(Comparator.comparingInt(sortedParagraph -> sortedParagraph.getChildren().size()));
        TextComposite textComposite = new TextComposite();
        textComposite.addChildren(sortedParagraphs);
        return textComposite;
    }

    public TextComponent sortWordsInSentences(TextComponent text) {
        List<TextComponent> paragraphs = new ArrayList<>();
        for (TextComponent paragraph : text.getChildren()) {
            List<TextComponent> sentences = new ArrayList<>();
            for (TextComponent sentence : paragraph.getChildren()) {
                List<TextComponent> words = new ArrayList<>(sentence.getChildren());
                sortWords(words);
                TextComposite sentenceComposite = new TextComposite();
                sentenceComposite.addChildren(words);
                sentences.add(sentenceComposite);
            }
            TextComposite paragraphComposite = new TextComposite();
            paragraphComposite.addChildren(sentences);
            paragraphs.add(paragraphComposite);
        }
        TextComposite textComposite = new TextComposite();
        textComposite.addChildren(paragraphs);
        return textComposite;
    }

    private void sortWords(List<TextComponent> words) {
        words.sort(Comparator.comparingInt(lexeme -> {
            String lexemeValue = ((TextLeaf) lexeme).getData();
            return lexemeValue.length();
        }));
    }
}
