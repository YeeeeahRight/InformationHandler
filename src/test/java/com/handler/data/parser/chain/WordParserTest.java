package com.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextLeaf;
import com.epam.handler.data.parser.chain.WordParser;
import com.epam.handler.enums.LeafType;
import org.junit.Assert;
import org.junit.Test;

public class WordParserTest {
    private final static String WORD_DATA = "Word";
    private final static TextLeaf WORD_LEAF = new TextLeaf(LeafType.WORD, WORD_DATA);
    private final static String EXPRESSION_DATA = "[32+]";
    private final static String SPACES_EXPRESSION_DATA = "[3 2 +]";
    private final static TextLeaf EXPRESSION_LEAF = new TextLeaf(LeafType.EXPRESSION, EXPRESSION_DATA);

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

    @Test
    public void testParseShouldReturnExpressionLeafWhenDataIsExpressionWithSpaces() {
        //given
        TextComponent textComponent;
        TextLeaf actualLeaf;
        //when
        textComponent = wordParser.parse(SPACES_EXPRESSION_DATA);
        actualLeaf = ((TextLeaf)textComponent);
        //then
        Assert.assertEquals(EXPRESSION_LEAF, actualLeaf);
    }
}
