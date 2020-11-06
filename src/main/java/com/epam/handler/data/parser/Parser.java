package com.epam.handler.data.parser;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.data.model.TextComposite;

public interface Parser {
    TextComponent parse(String text);
}
