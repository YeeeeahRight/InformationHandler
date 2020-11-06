package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import org.apache.log4j.Logger;

public class TextParser extends AbstractParser {
    private static final Logger LOGGER = Logger.getLogger(TextParser.class);
    private static final String PARAGRAPH_SPLITERATOR = "\r?\n";

    public TextParser(AbstractParser successor) {
        super(successor);
    }

    @Override
    public TextComponent parse(String text) {
        LOGGER.info("Parsing text...");
        TextComposite textComposite = new TextComposite();
        String[] paragraphs = text.split(PARAGRAPH_SPLITERATOR);
        for (String paragraph : paragraphs) {
            AbstractParser nextParser = getNextParser();
            TextComponent paragraphComposite = nextParser.parse(paragraph);
            textComposite.addChild(paragraphComposite);
            LOGGER.info("Parsed paragraph: " + paragraphComposite);
        }
        LOGGER.info("Text was parsed!");
        return textComposite;
    }
}
