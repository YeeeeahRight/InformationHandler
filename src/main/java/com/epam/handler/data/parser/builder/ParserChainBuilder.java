package com.epam.handler.data.parser.builder;

import com.epam.handler.data.parser.Parser;
import com.epam.handler.data.parser.chain.WordParser;
import com.epam.handler.data.parser.chain.ParagraphParser;
import com.epam.handler.data.parser.chain.SentenceParser;
import com.epam.handler.data.parser.chain.TextParser;
import org.apache.log4j.Logger;

public class ParserChainBuilder {
    private static final Logger LOGGER = Logger.getLogger(TextParser.class);

    public Parser buildParser() {
        LOGGER.info("Creating parser for text...");
        WordParser wordParser = new WordParser();
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        LOGGER.info("Parser was created!");
        return new TextParser(paragraphParser);
    }
}
