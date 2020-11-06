package com.epam.handler.data.parser.chain;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.parser.Parser;

public abstract class AbstractParser implements Parser {
    private final AbstractParser nextParser;

    public AbstractParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    public AbstractParser getNextParser() {
        return nextParser;
    }

    public abstract TextComponent parse(String text);

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
