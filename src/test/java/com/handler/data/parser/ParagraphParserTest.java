package com.handler.data.parser;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.data.parser.chain.ParagraphParser;
import com.epam.handler.data.parser.chain.SentenceParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class ParagraphParserTest {
    private static final String SENTENCE_STRING = "Hi, user!";
    private static final String VALID_TEXT = SENTENCE_STRING + SENTENCE_STRING;
    private static final TextComponent SENTENCE = TextLeaf.createWord(SENTENCE_STRING);
    private static final List<TextComponent> SENTENCES = Arrays.asList(SENTENCE, SENTENCE);
    private static final TextComponent EXPECTED_COMPONENT = new TextComposite();

    @BeforeClass
    public static void initializeComponent() {
        ((TextComposite)EXPECTED_COMPONENT).addChildren(SENTENCES);
    }

    @Test
    public void testParseShouldReturnCorrectCorrectComponentWhenTextIsValid() {
        //given
        SentenceParser sentenceParser = Mockito.mock(SentenceParser.class);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        when(sentenceParser.parse(SENTENCE_STRING)).thenReturn(SENTENCE);
        //when
        TextComponent actualComponent = paragraphParser.parse(VALID_TEXT);
        //then
        Assert.assertEquals(EXPECTED_COMPONENT, actualComponent);
    }
}