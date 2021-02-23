package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import org.apache.log4j.Logger;

public class ParagraphParser extends AbstractParser {
    private static final Logger LOGGER = Logger.getLogger(TextParser.class);
    private static final String SENTENCE_PATTERN = "[^!?.]+[!?.](\\.{2})?([^\\n\\w]*\\n)?";

    public ParagraphParser(AbstractParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite paragraphComposite = parseComposite(text, SENTENCE_PATTERN);
        LOGGER.info("Parsed paragraph: " + paragraphComposite);
        return paragraphComposite;
    }
}
