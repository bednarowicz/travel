package pl.selenium.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerTest {
    public static void main(String[] args){
        Logger logger = LogManager.getLogger();
        logger.info("info");
        logger.error("error");
        logger.warn("warn");
        logger.debug("Debug");
        logger.fatal("fatal");
        logger.trace("Trace");
    }
}
