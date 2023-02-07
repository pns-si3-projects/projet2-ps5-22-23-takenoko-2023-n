package fr.cotedazur.univ.polytech.startingpoint;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Permet de formatter l'affichage des loggers
 * @author equipe N
 */
public class LoggerFormatter extends Formatter {
    @Override
    public String format(LogRecord logRecord) {
        return logRecord.getMessage() + "\n";
    }
}
