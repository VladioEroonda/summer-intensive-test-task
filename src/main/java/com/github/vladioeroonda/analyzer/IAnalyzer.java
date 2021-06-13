package com.github.vladioeroonda.analyzer;

import java.util.Map;

public interface IAnalyzer {
    Map<String, Integer> analyzeText(String[] dataForAnalyze);
}
