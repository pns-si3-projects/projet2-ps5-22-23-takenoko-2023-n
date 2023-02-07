package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.IStringConverter;

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
        throw new IllegalArgumentException("Le mode de jeu demandé n'existe pas");
    }
}
