package com.github.vladioeroonda.analyzer;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextToWordsBySymbolsSplitterTest {
    private TextToWordsBySymbolsSplitter splitter = new TextToWordsBySymbolsSplitter();

    @Test
    public void arraySizeShouldBe4WhenDelimiterIsDot() {
        //Given
        String textForSplit = "Hello.This.is.Test";
        //When
        splitter.setDelimiter("\\.");
        String[] result = splitter.split(textForSplit);
        //Then
        int expected = 4;

        assertEquals(expected, result.length);
    }
    @Test
    public void arraySizeShouldBe6WhenFewDelimiters() {
        //Given
        String textForSplit = "Hello!This.is,Another:one Test";
        //When
        splitter.setDelimiter("[\\s,|\\,|\\.|\\!|\\:]");
        String[] result = splitter.split(textForSplit);
        //Then
        int expected = 6;

        assertEquals(expected, result.length);
    }
}