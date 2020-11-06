package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {
    private static final Logger LOGGER = Logger.getLogger(TextParser.class);
    private static final String LEAF_PATTERN = "\\[[^]\\[]*]|[^\\s\\[\\]]+"; //matches words and expressions in one time

    public SentenceParser(AbstractParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite sentenceComposite = new TextComposite();
        Pattern leafPattern = Pattern.compile(LEAF_PATTERN);
        Matcher leafMatcher = leafPattern.matcher(text);
        while (leafMatcher.find()) {
            AbstractParser nextParser = getNextParser();
            String word = leafMatcher.group();
            TextComponent wordLeaf = nextParser.parse(word);
            sentenceComposite.addChild(wordLeaf);
            LOGGER.info("Parsed word: " + wordLeaf);
        }
        return sentenceComposite;
    }
}
