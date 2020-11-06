package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {
    private static final Logger LOGGER = Logger.getLogger(TextParser.class);
    private static final String SENTENCE_PATTERN = "[^!?.]+[!?.](\\.{2})?";

    public ParagraphParser(AbstractParser successor) {
        super(successor);
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite paragraphComposite = new TextComposite();
        Pattern sentencePattern = Pattern.compile(SENTENCE_PATTERN);
        Matcher sentenceMatcher = sentencePattern.matcher(text);
        while (sentenceMatcher.find()) {
            AbstractParser nextParser = getNextParser();
            String sentence = sentenceMatcher.group();
            TextComponent sentenceComposite = nextParser.parse(sentence);
            paragraphComposite.addChild(sentenceComposite);
            LOGGER.info("Parsed sentence: " + sentenceComposite);
        }
        return paragraphComposite;
    }
}
