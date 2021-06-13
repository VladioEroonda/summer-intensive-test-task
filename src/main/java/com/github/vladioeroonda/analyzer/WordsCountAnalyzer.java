package com.github.vladioeroonda.analyzer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordsCountAnalyzer implements IAnalyzer{

    @Override
    public Map<String, Integer> analyzeText(String[] dataForAnalyze) {

        Map<String, Integer> result = new HashMap<>();
        Arrays.sort(dataForAnalyze); //Сортируем в нат порядке, чтоб легче искать

        int tempCounter = 1;

        for (int i = 0; i < dataForAnalyze.length; i++) {
            if (dataForAnalyze[i].length() > 0) {
                if (i == dataForAnalyze.length - 1) {
                    result.put(dataForAnalyze[i], tempCounter);
                    break;
                }

                if (dataForAnalyze[i].equals(dataForAnalyze[i + 1])) {
                    ++tempCounter;
                } else {
                    result.put(dataForAnalyze[i], tempCounter);
                    tempCounter = 1;
                }
            }
        }

        return result;
    }
}
