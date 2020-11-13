package com.handler.logic;


import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import com.epam.handler.data.model.TextLeaf;

import java.util.Arrays;

public class TextProcessorHelper {
    private static final TextLeaf FIRST_LEAF = TextLeaf.createWord("Goes ");
    private static final TextLeaf SECOND_LEAF = TextLeaf.createWord("[10 14 +] ");
    private static final TextLeaf THIRD_LEAF = TextLeaf.createWord("hour... ");

    public static TextComponent getTextForRestore() {
        TextComposite sentence = new TextComposite();
        sentence.addChildren(Arrays.asList(FIRST_LEAF, SECOND_LEAF, THIRD_LEAF));
        TextComposite paragraph = new TextComposite();
        paragraph.addChild(sentence);
        TextComposite text = new TextComposite();
        text.addChild(paragraph);
        return text;
    }

    public static TextComponent getUnsortedTextForWordsSorting() {
        TextComposite sentence = new TextComposite();
        sentence.addChildren(Arrays.asList(THIRD_LEAF, SECOND_LEAF, FIRST_LEAF));
        TextComposite paragraph = new TextComposite();
        paragraph.addChildren(Arrays.asList(sentence, sentence));
        TextComposite textComponent = new TextComposite();
        textComponent.addChild(paragraph);
        textComponent.addChild(paragraph);
        return textComponent;
    }

    public static TextComponent getSortedTextForWordsSorting() {
        TextComposite sentence = new TextComposite();
        sentence.addChildren(Arrays.asList(FIRST_LEAF, SECOND_LEAF, THIRD_LEAF));
        TextComposite paragraph = new TextComposite();
        paragraph.addChildren(Arrays.asList(sentence, sentence));
        TextComposite textComponent = new TextComposite();
        textComponent.addChild(paragraph);
        textComponent.addChild(paragraph);
        return textComponent;
    }

    public static TextComponent getUnsortedTextForParagraphSorting() {
        TextComposite sentence = new TextComposite();
        sentence.addChildren(Arrays.asList(THIRD_LEAF, SECOND_LEAF, FIRST_LEAF));
        TextComposite smallerParagraph = new TextComposite();
        smallerParagraph.addChildren(Arrays.asList(sentence, sentence));
        TextComposite largerParagraph = new TextComposite();
        largerParagraph.addChildren(Arrays.asList(sentence, sentence, sentence));
        TextComposite textComponent = new TextComposite();
        textComponent.addChild(largerParagraph);
        textComponent.addChild(smallerParagraph);
        return textComponent;

    }

    public static TextComponent getSortedTextForParagraphSorting() {
        TextComposite sentence = new TextComposite();
        sentence.addChildren(Arrays.asList(THIRD_LEAF, SECOND_LEAF, FIRST_LEAF));
        TextComposite smallerParagraph = new TextComposite();
        smallerParagraph.addChildren(Arrays.asList(sentence, sentence));
        TextComposite largerParagraph = new TextComposite();
        largerParagraph.addChildren(Arrays.asList(sentence, sentence, sentence));
        TextComposite textComponent = new TextComposite();
        textComponent.addChild(smallerParagraph);
        textComponent.addChild(largerParagraph);
        return textComponent;
    }

    public static String getExpectedRestoredText() {
        return "Goes [10 14 +]  hour...";
    }
}