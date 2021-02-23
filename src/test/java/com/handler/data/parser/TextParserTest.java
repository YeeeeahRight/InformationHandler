package com.handler.data.parser;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.data.parser.chain.ParagraphParser;
import com.epam.handler.data.parser.chain.TextParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class TextParserTest {
    private static final String PARAGRAPH_TEXT = "Hi, user! Hi, user!";
    private static final String VALID_TEXT = PARAGRAPH_TEXT + "\n" + PARAGRAPH_TEXT;
    private static final TextComponent PARAGRAPH = TextLeaf.createWord(PARAGRAPH_TEXT);
    private static final List<TextComponent> PARAGRAPHS = Arrays.asList(PARAGRAPH, PARAGRAPH);
    private static final TextComponent EXPECTED_COMPONENT = new TextComposite();

    @BeforeClass
    public static void initializeComponent() {
        ((TextComposite)EXPECTED_COMPONENT).addChildren(PARAGRAPHS);
    }

    @Test
    public void testParseShouldReturnCorrectComponentWhenTextIsValid() {
        //given
        ParagraphParser paragraphParser = Mockito.mock(ParagraphParser.class);
        TextParser textParser = new TextParser(paragraphParser);
        when(textParser.parse(PARAGRAPH_TEXT)).thenReturn(PARAGRAPH);
        when(textParser.parse(PARAGRAPH_TEXT + "\n")).thenReturn(PARAGRAPH);
        //when
        TextComponent actualComponent = textParser.parse(VALID_TEXT);
        //then
        Assert.assertEquals(EXPECTED_COMPONENT, actualComponent);
    }
}