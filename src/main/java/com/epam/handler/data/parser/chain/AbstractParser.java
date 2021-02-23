package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;
import com.epam.handler.data.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractParser implements Parser {
    private final AbstractParser nextParser;

    protected AbstractParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    protected AbstractParser getNextParser() {
        return nextParser;
    }

    public abstract TextComponent parse(String text);

    protected TextComposite parseComposite(String text, String regex) {
        TextComposite textComposite = new TextComposite();
        List<TextComponent> subComponents = matchSubComponents(text, regex);
        textComposite.addChildren(subComponents);
        return textComposite;
    }

    private List<TextComponent> matchSubComponents(String text, String regex) {
        List<TextComponent> subComponents = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            AbstractParser nextParser = getNextParser();
            String matchedData = matcher.group();
            TextComponent textComponent = nextParser.parse(matchedData);
            subComponents.add(textComponent);
        }
        return subComponents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractParser)) {
            return false;
        }

        AbstractParser that = (AbstractParser) o;

        return getNextParser() != null ? getNextParser().equals(that.getNextParser()) : that.getNextParser() == null;
    }

    @Override
    public int hashCode() {
        return getNextParser() != null ? getNextParser().hashCode() : 0;
    }
}
