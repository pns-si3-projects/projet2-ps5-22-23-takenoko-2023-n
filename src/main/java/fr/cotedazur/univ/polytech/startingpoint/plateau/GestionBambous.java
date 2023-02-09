package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.pieces.Bambou;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestionBambous {
    // Définition d'un constructeur privé pour éviter les instanciations

    private GestionBambous() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Cherche un bambou à une certaine position
     * @param bambouList la liste de bambou posés sur le plateau
     * @param position position à laquelle on cherche le bambou
     * @return un optional de bambou si trouvé, sinon un optional vide
     */
    public static Optional<Bambou> chercheBambou(@NotNull Bambou[] bambouList, Position position){
        for (Bambou bambou : bambouList){
            if (bambou.getPosition().equals(position)) return Optional.of(bambou);
        }
        return Optional.empty();
    }

    /**
     * Renvoie la liste des positions où le bambou a au moins une section de bambou
     * @param listePosition la liste des positions de déplacement possible
     * @param plateau le plateau du jeu
     * @param max si on vérifie pour des bambous de taille maximum
     * @return la liste des positions de déplacement
     */
    public static List<Position> positionAvecBambou(List<Position> listePosition, Plateau plateau, boolean max) {
        List<Position> positions = new ArrayList<>();
        for (Position position : listePosition) {
            Optional<Bambou> optBambou = GestionBambous.chercheBambou(plateau.getBambous(), position);
            if (optBambou.isPresent()) {
                Bambou bambou = optBambou.get();
                if ((max && !bambou.isTailleMaximum()) || (!max && !bambou.isEmpty())) {
                    positions.add(position);
                }
            }
        }
        return positions;
    }

    /**
     * Renvoie la position de parcelle de la couleur recherchée
     * @param plateau le plateau du jeu
     * @param deplacementPossible la liste des positions de déplacement possible
     * @param positionsAvecBambou la liste des positions de déplacement possible avec bambou
     * @param couleur la couleur recherchée
     * @return la position de parcelle de la couleur recherchée
     */
    public static Position positionVoulueCouleur(Plateau plateau, List<Position> deplacementPossible,
                                                 List<Position> positionsAvecBambou, Couleur couleur) {
        Position positionVoulue;

        if (positionsAvecBambou.isEmpty()) {
            positionVoulue = deplacementPossible.get(0);
        } else {
            positionVoulue = positionsAvecBambou.get(0);
        }

        for (Position position : positionsAvecBambou) {
            Optional<Parcelle> parcelleOptional = GestionParcelles.chercheParcelle(plateau.getParcelles(), position);
            if (parcelleOptional.isEmpty()) {
                throw new AssertionError("Parcelle introuvable");
            }

            ParcelleCouleur parcelleCouleur = (ParcelleCouleur) parcelleOptional.get();
            if (parcelleCouleur.getCouleur().equals(couleur)) {
                positionVoulue = parcelleCouleur.getPosition();
                break;
            }
        }

        return positionVoulue;
    }
}
