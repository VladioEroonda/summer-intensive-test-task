import com.github.vladioeroonda.analyzer.TargetPage;
import com.github.vladioeroonda.analyzer.TextToWordsBySymbolsSplitter;
import com.github.vladioeroonda.analyzer.UrlToTextParser;
import com.github.vladioeroonda.analyzer.WordsCountAnalyzer;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String log4jConfPath = "src/main/resources/log4j.properties";

    private static final String PAGE_FOR_ANALYZE= "https://www.simbirsoft.com/";

    public static void main(String[] args) {
        loggerConfig();

        TargetPage page = new TargetPage(
                PAGE_FOR_ANALYZE,
                new UrlToTextParser(),
                new TextToWordsBySymbolsSplitter("[\\s,|\\,|\\.|\\!|\\?|\\;|\\:\\[\\]\\(|\\)|\n|\r|\t|\"]"),
                new WordsCountAnalyzer()
                );

        page.savePageToDisk();

        Map<String, Integer> result = page.analyze();

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

    }

    private static void loggerConfig(){
        PropertyConfigurator.configure(log4jConfPath);
        LOGGER.debug("App starts, logger was configured");
    }
}
