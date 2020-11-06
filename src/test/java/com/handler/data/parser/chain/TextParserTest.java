package com.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.data.parser.chain.ParagraphParser;
import com.epam.handler.data.parser.chain.TextParser;
import com.epam.handler.enums.TextType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TextParserTest {
    private final static String FIRST_WORD = "Sentence";
    private final static String SECOND_WORD = "[12+]";
    private final static String THIRD_WORD = "size.";
    private final static String FIRST_SENTENCE = FIRST_WORD + " " + SECOND_WORD + " " + THIRD_WORD;
    private final static String SECOND_SENTENCE = SECOND_WORD + " " + FIRST_WORD + " " + THIRD_WORD;
    private final static TextLeaf FIRST_LEAF = new TextLeaf(TextType.WORD, "Sentence");
    private final static TextLeaf SECOND_LEAF = new TextLeaf(TextType.EXPRESSION, "[12+]");
    private final static TextLeaf THIRD_LEAF = new TextLeaf(TextType.WORD, "size.");
    private final static TextComposite FIRST_SENTENCE_COMPOSITE = new TextComposite();
    private final static TextComposite SECOND_SENTENCE_COMPOSITE = new TextComposite();
    private final static String FIRST_PARAGRAPH = FIRST_SENTENCE + SECOND_SENTENCE;
    private final static TextComposite FIRST_PARAGRAPH_COMPOSITE = new TextComposite();
    private final static String SECOND_PARAGRAPH = SECOND_SENTENCE + FIRST_SENTENCE;
    private final static TextComposite SECOND_PARAGRAPH_COMPOSITE = new TextComposite();
    private final static String TEXT = FIRST_PARAGRAPH + "\r\n" + SECOND_PARAGRAPH;
    private final static TextComposite TEXT_COMPOSITE = new TextComposite();

    private final ParagraphParser paragraphParser = Mockito.mock(ParagraphParser.class);
    private final TextParser textParser = new TextParser(paragraphParser);

    @Before
    public void initialize() {
        FIRST_SENTENCE_COMPOSITE.addChild(FIRST_LEAF);
        FIRST_SENTENCE_COMPOSITE.addChild(SECOND_LEAF);
        FIRST_SENTENCE_COMPOSITE.addChild(THIRD_LEAF);

        SECOND_SENTENCE_COMPOSITE.addChild(SECOND_LEAF);
        SECOND_SENTENCE_COMPOSITE.addChild(FIRST_LEAF);
        SECOND_SENTENCE_COMPOSITE.addChild(THIRD_LEAF);

        FIRST_PARAGRAPH_COMPOSITE.addChild(FIRST_SENTENCE_COMPOSITE);
        FIRST_PARAGRAPH_COMPOSITE.addChild(SECOND_SENTENCE_COMPOSITE);
        SECOND_PARAGRAPH_COMPOSITE.addChild(SECOND_SENTENCE_COMPOSITE);
        SECOND_PARAGRAPH_COMPOSITE.addChild(FIRST_SENTENCE_COMPOSITE);

        TEXT_COMPOSITE.addChild(FIRST_PARAGRAPH_COMPOSITE);
        TEXT_COMPOSITE.addChild(SECOND_PARAGRAPH_COMPOSITE);
    }

    @Test
    public void testParseShouldParseTextWhenDataIsExist() {
        //given
        TextComponent textComponent;
        TextComposite actualTextComposite;
        Mockito.when(paragraphParser.parse(FIRST_PARAGRAPH)).thenReturn(FIRST_PARAGRAPH_COMPOSITE);
        Mockito.when(paragraphParser.parse(SECOND_PARAGRAPH)).thenReturn(SECOND_PARAGRAPH_COMPOSITE);
        //when
        textComponent = textParser.parse(TEXT);
        actualTextComposite = ((TextComposite)textComponent);
        //then
        Assert.assertEquals(TEXT_COMPOSITE, actualTextComposite);
    }
}
