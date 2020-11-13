package com.epam.handler.data.parser;

import com.epam.handler.data.model.TextComponent;

public interface Parser {
    TextComponent parse(String text);
}
