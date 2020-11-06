package com.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.data.parser.chain.ParagraphParser;
import com.epam.handler.data.parser.chain.SentenceParser;
import com.epam.handler.data.parser.chain.WordParser;
import com.epam.handler.enums.TextType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ParagraphParserText {
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
    private final static String PARAGRAPH = FIRST_SENTENCE + SECOND_SENTENCE;
    private final static TextComposite PARAGRAPH_COMPOSITE = new TextComposite();

    private final SentenceParser sentenceParser = Mockito.mock(SentenceParser.class);
    private final ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

    @Before
    public void initialize() {
        FIRST_SENTENCE_COMPOSITE.addChild(FIRST_LEAF);
        FIRST_SENTENCE_COMPOSITE.addChild(SECOND_LEAF);
        FIRST_SENTENCE_COMPOSITE.addChild(THIRD_LEAF);
        PARAGRAPH_COMPOSITE.addChild(FIRST_SENTENCE_COMPOSITE);

        SECOND_SENTENCE_COMPOSITE.addChild(SECOND_LEAF);
        SECOND_SENTENCE_COMPOSITE.addChild(FIRST_LEAF);
        SECOND_SENTENCE_COMPOSITE.addChild(THIRD_LEAF);
        PARAGRAPH_COMPOSITE.addChild(SECOND_SENTENCE_COMPOSITE);
    }

    @Test
    public void testParseShouldParseParagraphWhenDataIsExist() {
        //given
        TextComponent paragraphComponent;
        TextComposite actualParagraphComposite;
        Mockito.when(paragraphParser.parse(FIRST_SENTENCE)).thenReturn(FIRST_SENTENCE_COMPOSITE);
        Mockito.when(paragraphParser.parse(SECOND_SENTENCE)).thenReturn(SECOND_SENTENCE_COMPOSITE);
        //when
        paragraphComponent = paragraphParser.parse(PARAGRAPH);
        actualParagraphComposite = ((TextComposite)paragraphComponent);
        //then
        Assert.assertEquals(PARAGRAPH_COMPOSITE, actualParagraphComposite);
    }
}
