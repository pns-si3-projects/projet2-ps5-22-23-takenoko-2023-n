package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

public class MotifNonValideException extends Exception{
    public MotifNonValideException(){
        super("Il y a des parcelles qui ne sont pas voisines");
    }
}
