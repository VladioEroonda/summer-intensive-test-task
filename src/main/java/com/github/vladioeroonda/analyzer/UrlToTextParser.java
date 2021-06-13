package com.github.vladioeroonda.analyzer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UrlToTextParser implements IParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlToTextParser.class);

    @Override
    public String parse(String urlSource) {
        Document document = null;

        try {
            document = Jsoup.connect(urlSource).get();
        } catch (IOException e) {
            LOGGER.error("Exception was thrown: " + e + " msg: " + e.getMessage());
        }

        if (document == null) {
            LOGGER.error("Exception: Unable to parse by url " + urlSource);
            throw new IllegalStateException("Unable to parse by url " + urlSource);
        }
        return document.body().text();
    }
}
