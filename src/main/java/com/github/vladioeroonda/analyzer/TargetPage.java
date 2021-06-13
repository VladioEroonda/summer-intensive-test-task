package com.github.vladioeroonda.analyzer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TargetPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(TargetPage.class);
    private static final String PATH_TO_SAVE_FOLDER = "C:/MyDownloadedPages";
    private static final String SAVED_PAGE_NAME = "page.html";

    private String sourceUrl;
    private IParser parser;
    private ISplitter splitter;
    private IAnalyzer analyzer;

    public TargetPage(String sourceUrl, IParser parser, ISplitter delimiter, IAnalyzer analyzer) {
        this.sourceUrl = sourceUrl;
        this.parser = parser;
        this.splitter = delimiter;
        this.analyzer = analyzer;
    }

    public void savePageToDisk(){

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            URL target = new URL(sourceUrl);
            reader = new BufferedReader(new InputStreamReader(target.openStream()));

            File saveFolder = createDirectory(); //абсолютный путь созданной папки

            String pageFilePath =  saveFolder.getPath();

            File savedPageFile = new File(
                    pageFilePath
                            + File.separator
                            + SAVED_PAGE_NAME
            );

            LOGGER.debug("Downloaded page file will be saved as: " + savedPageFile.getPath());

            writer = new BufferedWriter(new FileWriter(savedPageFile));

            String tempString;

            while ((tempString = reader.readLine()) != null) {
                writer.write(tempString);
            }
            LOGGER.debug(savedPageFile.exists() ? "File was successfully created" : "File was not created");

            LOGGER.info("File size: " + savedPageFile.length() + " bytes");

        } catch (MalformedURLException e) {
            LOGGER.error("Exception was thrown: " + e + " msg: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("Exception was thrown: " + e + " msg: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                LOGGER.error("Exception was thrown: " + e + " msg: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private File createDirectory() throws FileNotFoundException {
        String originalFolderName = generateNameForFolder();

        LOGGER.debug("Generated name for folder: " + originalFolderName);

        File saveDirectory = new File(PATH_TO_SAVE_FOLDER + File.separator + originalFolderName);
        boolean isDirectoryCreated = saveDirectory.mkdirs();

        LOGGER.debug(isDirectoryCreated? "Created directory path: " + saveDirectory : "Directory was not created");

        if (!isDirectoryCreated) {
            LOGGER.error("Directory was not created");
            throw new FileNotFoundException("Directory was not created");
        }

        return saveDirectory;
    }

    private String generateNameForFolder(){
        LocalDateTime thatMoment = LocalDateTime.now();
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return thatMoment.format(formatter);
    }

    public Map<String, Integer> analyze(){

        String pureText = parser.parse(sourceUrl);
        LOGGER.info("Text is blank : " + pureText.isBlank());
        LOGGER.info("Text's size: " + pureText.length() + " symbols");

        String[] textAfterSplit = splitter.split(pureText);
        LOGGER.debug("Array size after splitting text: " + textAfterSplit.length);

        Map<String, Integer> result = analyzer.analyzeText(textAfterSplit);
        LOGGER.debug("Result map size: " + result.size());

        return result;

    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public IParser getParser() {
        return parser;
    }

    public void setParser(IParser parser) {
        this.parser = parser;
    }

    public ISplitter getDelimiter() {
        return splitter;
    }

    public void setDelimiter(ISplitter delimiter) {
        this.splitter = delimiter;
    }

    public IAnalyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(IAnalyzer analyzer) {
        this.analyzer = analyzer;
    }
}
