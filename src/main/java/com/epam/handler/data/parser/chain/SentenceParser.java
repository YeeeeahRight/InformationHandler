package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import org.apache.log4j.Logger;

public class SentenceParser extends AbstractParser {
    private static final Logger LOGGER = Logger.getLogger(TextParser.class);
    private static final String LEAF_PATTERN = "\\[[^]\\[]*]|[^ \\[\\]]+\n?"; //matches words and expressions in one time

    public SentenceParser(AbstractParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite sentenceComposite = parseComposite(text, LEAF_PATTERN);
        LOGGER.info("Parsed sentence: " + sentenceComposite);
        return sentenceComposite;
    }
}
