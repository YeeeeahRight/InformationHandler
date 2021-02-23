package com.handler.logic;

import com.epam.handler.data.model.TextComponent;
import com.epam.handler.logic.TextProcessor;
import com.epam.handler.logic.interpreter.ExpressionCalculator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TextProcessorTest {
    private final ExpressionCalculator expressionCalculator = Mockito.mock(ExpressionCalculator.class);
    private final TextProcessor processor = new TextProcessor(expressionCalculator);

    @Test
    public void testRestoreTextShouldReturnRestoredTextWhenComponentIsText() {
        //given
        TextComponent actualComponent = TextProcessorHelper.getTextForRestore();
        String expectedString = TextProcessorHelper.getExpectedRestoredText();
        //when
        String actualString = processor.restoreText(actualComponent);
        //then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void testSortParagraphsBySentenceAmountShouldReturnSortedTextWhenComponentIsUnsortedText() {
        //given
        TextComponent actualComponent = TextProcessorHelper.getUnsortedTextForParagraphSorting();
        TextComponent expectedSortedText = TextProcessorHelper.getSortedTextForParagraphSorting();
        //when
        TextComponent actualSortedComponent = processor.sortParagraphsBySentenceAmount(actualComponent);
        //then
        Assert.assertEquals(expectedSortedText, actualSortedComponent);
    }

    @Test
    public void testSortWordsInSentencesShouldReturnSortedTextWhenComponentIsUnsortedText() {
        //given
        TextComponent actualComponent = TextProcessorHelper.getUnsortedTextForWordsSorting();
        TextComponent expectedSortedText = TextProcessorHelper.getSortedTextForWordsSorting();
        //when
        TextComponent actualSortedComponent = processor.sortWordsInSentences(actualComponent);
        //then
        Assert.assertEquals(expectedSortedText, actualSortedComponent);
    }
}
