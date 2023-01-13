package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;

public class AjoutCouleurException extends Exception{
    public AjoutCouleurException(Couleur couleurBambou, Couleur couleurSectionBambouAAdd){
        super("La couleur du bambou ("+couleurBambou+") est pas la même couleur que la section de Bambou à ajouter ("+couleurSectionBambouAAdd+")");
    }
}
