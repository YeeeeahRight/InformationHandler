package com.handler.data.parser.builder;

import com.epam.handler.data.parser.Parser;
import com.epam.handler.data.parser.builder.ParserChainBuilder;
import com.epam.handler.data.parser.chain.ParagraphParser;
import com.epam.handler.data.parser.chain.SentenceParser;
import com.epam.handler.data.parser.chain.TextParser;
import com.epam.handler.data.parser.chain.WordParser;
import org.junit.Assert;
import org.junit.Test;

//useless class????
public class ParserChainBuilderTest {
    private final static WordParser WORD_PARSER = new WordParser();
    private final static SentenceParser SENTENCE_PARSER = new SentenceParser(WORD_PARSER);
    private final static ParagraphParser PARAGRAPH_PARSER = new ParagraphParser(SENTENCE_PARSER);
    private final static Parser TEXT_PARSER = new TextParser(PARAGRAPH_PARSER);

    private final ParserChainBuilder parserChainBuilder = new ParserChainBuilder();

    @Test
    public void testBuildParserShouldReturnTextParserWhenWantParseText() {
        //given
        Parser actualParser;
        //when
        actualParser = parserChainBuilder.buildParser();
        //then
        Assert.assertEquals(TEXT_PARSER, actualParser);
    }
}
