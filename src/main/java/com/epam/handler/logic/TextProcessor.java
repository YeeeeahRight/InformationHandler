package com.epam.handler.logic;

import com.epam.handler.data.model.LeafType;
import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.logic.interpreter.ExpressionCalculator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextProcessor {
    private static final Logger LOGGER = Logger.getLogger(TextProcessor.class);

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
        LOGGER.info("Restored: " + dataBuilder.toString());
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
        int result = expressionCalculator.calculate(expressionValue);
        String stringResult = Integer.toString(result);
        return TextLeaf.createWord(stringResult);
    }

    public TextComponent sortParagraphsBySentenceAmount(TextComponent text) {
        LOGGER.info("Sorting paragraphs by sentence amount...");
        List<TextComponent> sortedParagraphs = new ArrayList<>(text.getChildren());
        sortedParagraphs.sort(Comparator.comparingInt(sortedParagraph -> sortedParagraph.getChildren().size()));
        TextComposite textComposite = new TextComposite();
        textComposite.addChildren(sortedParagraphs);
        LOGGER.info("Paragraphs have been sorted!");
        return textComposite;
    }

    public TextComponent sortWordsInSentences(TextComponent text) {
        LOGGER.info("Sorting words in sentences...");
        List<TextComponent> paragraphs = new ArrayList<>();
        for (TextComponent paragraph : text.getChildren()) {
            LOGGER.info("Sorting words in paragraph: " + paragraph);
            List<TextComponent> sentences = new ArrayList<>();
            for (TextComponent sentence : paragraph.getChildren()) {
                LOGGER.info("Sorting words in sentence: " + sentence);
                List<TextComponent> words = new ArrayList<>(sentence.getChildren());
                sortWords(words);
                TextComposite sortedSentence = new TextComposite();
                sortedSentence.addChildren(words);
                sentences.add(sortedSentence);
                LOGGER.info("Words in this sentence are sorted: " + sortedSentence);
            }
            TextComposite sortedParagraph = new TextComposite();
            sortedParagraph.addChildren(sentences);
            paragraphs.add(sortedParagraph);
            LOGGER.info("Words in this paragraph are sorted: " + sortedParagraph);
        }
        TextComposite textComposite = new TextComposite();
        textComposite.addChildren(paragraphs);
        LOGGER.info("Words have been sorted!");
        return textComposite;
    }

    private void sortWords(List<TextComponent> words) {
        words.sort(Comparator.comparingInt(lexeme -> {
            String lexemeValue = ((TextLeaf) lexeme).getData();
            return lexemeValue.length();
        }));
    }
}
