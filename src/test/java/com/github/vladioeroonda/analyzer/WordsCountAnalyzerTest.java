package com.github.vladioeroonda.analyzer;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class WordsCountAnalyzerTest {
    private WordsCountAnalyzer counter = new WordsCountAnalyzer();

    @Test
    public void shouldBeValue4ForKeyEUROPEValue2ForKeyTESTValue1ForKeyJUNIT() {
        //Given
        String[] wordList = {"TEST", "EUROPE", "EUROPE", "EUROPE", "EUROPE", "TEST", "JUNIT"};
        //When
        Map<String, Integer> result = counter.analyzeText(wordList);
        //Then
        int europeExpected = 4;
        int testExpected = 2;
        int junitExpected = 1;

        assertEquals(europeExpected, result.get("EUROPE").intValue());
        assertEquals(junitExpected, result.get("JUNIT").intValue());
        assertEquals(testExpected, result.get("TEST").intValue());
    }

    @Test
    public void mapSizeShouldBe5() {
        //Given
        String[] wordList = {"TEST", "JAVA", "TEST", "JUNIT", "ASSERTS", "JAVA", "JAVA", "JUSTAWORD"};
        //When
        Map<String, Integer> result = counter.analyzeText(wordList);
        //Then
        int expectedSize = 5;

        assertEquals(expectedSize, result.size());
    }
}