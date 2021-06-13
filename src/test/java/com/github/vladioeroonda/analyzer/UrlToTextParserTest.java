package com.github.vladioeroonda.analyzer;

import org.junit.Test;

import static org.junit.Assert.*;

public class UrlToTextParserTest {
    private UrlToTextParser parser = new UrlToTextParser();

    @Test
    public void shouldBeNotNullWhenURLIsCorrect() {
        //Given
        String url = "http://www.ya.ru";
        //When
        String result = parser.parse(url);
        //Then

        assertNotNull(result);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenURLIncorrect() {
        //Given
        String url = "http://www.ya234.ru";
        //When
        String result = parser.parse(url);
        //Then
    }
}