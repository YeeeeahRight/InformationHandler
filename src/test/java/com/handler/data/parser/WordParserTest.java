package com.handler.data.parser;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.data.parser.chain.WordParser;
import org.junit.Assert;
import org.junit.Test;

public class WordParserTest {
    private final static String WORD_DATA = "Word";
    private final static TextLeaf WORD_LEAF = TextLeaf.createWord(WORD_DATA);
    private final static String EXPRESSION_DATA = "[3 2 +]";
    private final static TextLeaf EXPRESSION_LEAF = TextLeaf.createExpression(EXPRESSION_DATA);

    private final WordParser wordParser = new WordParser();

    @Test
    public void testParseShouldReturnWordLeafWhenDataIsWord() {
        //given
        TextComponent textComponent;
        TextLeaf actualLeaf;
        //when
        textComponent = wordParser.parse(WORD_DATA);
        actualLeaf = ((TextLeaf)textComponent);
        //then
        Assert.assertEquals(WORD_LEAF, actualLeaf);
    }

    @Test
    public void testParseShouldReturnExpressionLeafWhenDataIsExpression() {
        //given
        TextComponent textComponent;
        TextLeaf actualLeaf;
        //when
        textComponent = wordParser.parse(EXPRESSION_DATA);
        actualLeaf = ((TextLeaf)textComponent);
        //then
        Assert.assertEquals(EXPRESSION_LEAF, actualLeaf);
    }
}
