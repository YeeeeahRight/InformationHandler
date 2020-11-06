package com.epam.handler.data.reader;

import com.epam.handler.exceptions.TextReaderException;
import org.apache.log4j.Logger;

import java.io.CharArrayWriter;
import java.io.FileReader;
import java.io.IOException;

public class FileTextReader {
    private static final Logger LOGGER = Logger.getLogger(FileTextReader.class);

    public String readText(String filePath) throws TextReaderException {
        FileReader fileReader = null;
        CharArrayWriter charArray = new CharArrayWriter();
        try {
            fileReader = new FileReader(filePath);
            char[] charBuffer = new char[1024 * 4]; //reading 8kB in one time
            int currentCharsAmount;
            while (fileReader.ready()) {
                currentCharsAmount = fileReader.read(charBuffer);
                charArray.write(charBuffer, 0, currentCharsAmount);
            }
        } catch (IOException e) {
            throw new TextReaderException("Something wrong with file text reading.", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return charArray.toString();
    }
}
