package com.epam.handler.data.reader;

import com.epam.handler.exceptions.TextReaderException;

public interface TextReader {
    String read(String filePath) throws TextReaderException;
}
