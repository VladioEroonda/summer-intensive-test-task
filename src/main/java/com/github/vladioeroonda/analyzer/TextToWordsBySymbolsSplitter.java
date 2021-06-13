package com.github.vladioeroonda.analyzer;

public class TextToWordsBySymbolsSplitter implements ISplitter {
    private String delimiter;

    public TextToWordsBySymbolsSplitter(String delimiter) {
        this.delimiter = delimiter;
    }

    public TextToWordsBySymbolsSplitter() {
    }

    @Override
    public String[] split(String text) {
            return text.split(delimiter);
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
