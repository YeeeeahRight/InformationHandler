package com.handler.data.reader;

import com.epam.handler.data.reader.FileTextReader;
import com.epam.handler.data.reader.TextReader;
import com.epam.handler.exceptions.TextReaderException;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileTextReaderTest {
    private static final String LINE_SEPARATOR = "\r\n";
    private static final String VALID_FILE_PATH = "src/test/resources/text.txt";
    private static final String INVALID_FILE_PATH = "src/test/resources/invalid.txt";

    private final TextReader fileDataReader = new FileTextReader();

    @Test
    public void testReadTextShouldReadWhenFileIsExist() throws TextReaderException, IOException {
        //given
        String expectedData = readFileLines(VALID_FILE_PATH);
        //when
        String actualData = fileDataReader.read(VALID_FILE_PATH);
        //then
        Assert.assertEquals(expectedData, actualData);
    }

    private String readFileLines(String filePath) throws IOException {
        StringBuilder lines = new StringBuilder();
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
        String line = fileReader.readLine();
        while (line != null) {
            lines.append(line);
            if (fileReader.ready()) {
                lines.append(LINE_SEPARATOR);
            }
            line = fileReader.readLine();
        }
        return lines.toString();
    }

    @Test(expected = TextReaderException.class)//then
    public void testReadTextShouldThrowExceptionWhenFileIsNotExist() throws TextReaderException {
        //when
        fileDataReader.read(INVALID_FILE_PATH);
    }
}
