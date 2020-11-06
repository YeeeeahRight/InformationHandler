package com.handler.data.reader;

import com.epam.handler.data.reader.FileTextReader;
import com.epam.handler.exceptions.TextReaderException;
import org.junit.Assert;
import org.junit.Test;

public class FileTextReaderTest {
    private static final String FILE_DATA = "Hye. My name is Sasha!\r\nNext paragraph... OK!\r\nBye.";
    private static final String VALID_FILE_PATH = "src/test/resources/text.txt";
    private static final String INVALID_FILE_PATH = "src/test/resources/invalid.txt";

    private final FileTextReader fileDataReader = new FileTextReader();

    @Test
    public void testReadTextShouldReadWhenFileIsExist() throws TextReaderException {
        //given
        String actualData;
        //when
        actualData = fileDataReader.readText(VALID_FILE_PATH);
        //then
        Assert.assertEquals(FILE_DATA, actualData);
    }

    @Test(expected = TextReaderException.class)//then
    public void testReadTextShouldThrowExceptionWhenFileIsNotExist() throws TextReaderException {
        //whe
        fileDataReader.readText(INVALID_FILE_PATH);
    }
}
