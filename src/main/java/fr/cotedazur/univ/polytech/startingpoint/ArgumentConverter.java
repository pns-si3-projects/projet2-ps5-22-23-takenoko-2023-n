package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.IStringConverter;

/**
 * Permet de convertir les options du Main
 */
public class ArgumentConverter implements IStringConverter<ArgumentPossibleMain> {
    @Override
    public ArgumentPossibleMain convert(String value) {
        if (value.equals("--2thousands")) {
            return ArgumentPossibleMain.THOUSANDS;
        }
        if (value.equals("--demo")) {
            return ArgumentPossibleMain.DEMO;
        }
        if (value.equals("--csv")) {
            return ArgumentPossibleMain.CSV;
        }
        return ArgumentPossibleMain.PRESENTATION;
    }
}
