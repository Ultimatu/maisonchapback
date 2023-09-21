package com.tonde.maisonchapback.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {

    private static final Logger logger = LoggerFactory.getLogger(CustomLogger.class);

    private CustomLogger() {
        // Empêche l'instanciation de cette classe
        throw new IllegalStateException("Erreur interne du serveur");
    }

    public static Logger getLogger(Class<?> callingClass) {
        return LoggerFactory.getLogger(callingClass);
    }

    public static void log(String type, String message) {

        switch (type) {
            case "INFO" -> logger.info(message);
            case "DEBUG" -> logger.debug(message);
            case "WARN" -> logger.warn(message);
            case "ERROR" -> logger.error(message);
            default -> logger.info("Message non reconnu");
        }
    }
}
