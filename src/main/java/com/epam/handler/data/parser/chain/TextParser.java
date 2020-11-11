package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import org.apache.log4j.Logger;

public class TextParser extends AbstractParser {
    private static final Logger LOGGER = Logger.getLogger(TextParser.class);
    private static final String PARAGRAPH_SPLITERATOR = "[^\n]+\n?";

    public TextParser(AbstractParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite textComposite = parseComposite(text, PARAGRAPH_SPLITERATOR);
        LOGGER.info("Parsed text: " + textComposite);
        return textComposite;
    }
}
