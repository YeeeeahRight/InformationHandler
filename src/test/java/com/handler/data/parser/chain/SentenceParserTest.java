package com.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.data.parser.chain.SentenceParser;
import com.epam.handler.data.parser.chain.WordParser;
import com.epam.handler.enums.TextType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SentenceParserTest {
    private final static String FIRST_WORD = "Sentence";
    private final static String SECOND_WORD = "[12+]";
    private final static String THIRD_WORD = "size.";
    private final static String SENTENCE = FIRST_WORD + " " + SECOND_WORD + " " + THIRD_WORD;

    private final static TextLeaf FIRST_LEAF = new TextLeaf(TextType.WORD, "Sentence");
    private final static TextLeaf SECOND_LEAF = new TextLeaf(TextType.EXPRESSION, "[12+]");
    private final static TextLeaf THIRD_LEAF = new TextLeaf(TextType.WORD, "size.");
    private final static TextComposite SENTENCE_COMPOSITE = new TextComposite();

    private final WordParser wordParser = Mockito.mock(WordParser.class);
    private final SentenceParser sentenceParser = new SentenceParser(wordParser);

    @Before
    public void initialize() {
        SENTENCE_COMPOSITE.addChild(FIRST_LEAF);
        SENTENCE_COMPOSITE.addChild(SECOND_LEAF);
        SENTENCE_COMPOSITE.addChild(THIRD_LEAF);
    }

    @Test
    public void testParseShouldParseSentenceWhenDataIsExist() {
        //given
        TextComponent sentenceComponent;
        TextComposite actualSentenceComposite;
        Mockito.when(wordParser.parse(FIRST_WORD)).thenReturn(FIRST_LEAF);
        Mockito.when(wordParser.parse(SECOND_WORD)).thenReturn(SECOND_LEAF);
        Mockito.when(wordParser.parse(THIRD_WORD)).thenReturn(THIRD_LEAF);
        //when
        sentenceComponent = sentenceParser.parse(SENTENCE);
        actualSentenceComposite = ((TextComposite)sentenceComponent);
        //then
        Assert.assertEquals(SENTENCE_COMPOSITE, actualSentenceComposite);
    }
}
