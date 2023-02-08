package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.Parameter;

/**
 * Permet de renvoyer les options données dans les arguments du Main
 */
public class ArgumentsMain {
    @Parameter(converter = ArgumentConverter.class, description = "Mode de jeu", arity = 0)
    private ArgumentPossibleMain argument;

    public ArgumentPossibleMain getArgument() {
        return argument;
    }
}
