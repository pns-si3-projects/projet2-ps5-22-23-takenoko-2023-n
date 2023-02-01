package fr.cotedazur.univ.polytech.startingpoint.plateau;

import fr.cotedazur.univ.polytech.startingpoint.Couleur;
import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleDisponible;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleNonExistanteException;
import fr.cotedazur.univ.polytech.startingpoint.motif.GestionnairePossibiliteMotif;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Classe qui permet de renvoyer les déplacements possibles sur le plateau
 * @author equipe N
 */
public class GestionnairePossibilitePlateau {
    // Définition des attributs
    private final Plateau plateau;


    // Définition des constructeurs
    /**
     * Constructeur par défaut
     * @param plateau est le plateau du jeu
     */
    public GestionnairePossibilitePlateau(Plateau plateau) {
        this.plateau = plateau;
    }


    // Méthodes d'utilisation
    /**
     * Renvoie la parcelle voisine à l'indiceTab dans le tableau de voisines
     * @param parcelle la parcelle où on souhaite obtenir son voisin
     * @param indiceTab la position dans le tableau des voisins
     * @return la parcelle voisine à l'indiceTab dans le tableau de voisines
     */
    private Parcelle getSuivantParcelle(Parcelle parcelle, int indiceTab){
        try {
            return plateau.getTableauVoisines(parcelle)[indiceTab];
        }
        catch (ParcelleNonExistanteException pNEE){
            assert false : "Les parcelles doivent être dans le plateau";
            return null;
        }
    }

    /**
     * Renvoie la liste de positions des parcelles dans une des 6 orientations dans le tableau des voisins
     * @param indiceTabVoisin l'indice dans le tableau des voisins pour choisir l'orientation de récupération des parcelles
     * @param parcellePersonnage la parcelle où se situe le personnage
     * @return la liste de positions des parcelles dans une des 6 orientations dans le tableau des voisins
     */
    private List<Position> getPossibleDeplacement(int indiceTabVoisin, Parcelle parcellePersonnage){
        List<Position> listParcelleOrientation = new ArrayList<>();
        Parcelle parcelleDeplace = getSuivantParcelle(parcellePersonnage,indiceTabVoisin);

        while (parcelleDeplace.getClass() != ParcelleDisponible.class) {
            listParcelleOrientation.add(parcelleDeplace.position());
            parcelleDeplace = getSuivantParcelle(parcelleDeplace,indiceTabVoisin);
            assert parcelleDeplace != null: "La parcelle ne doit pas être vide (vu que c'est des parcelles dans le plateau et que le while verifie à chaque fois";
        }

        return listParcelleOrientation;
    }

    /**
     * Renvoie la liste de position de déplacement possible en diagonale droite sur le plateau
     * @param positionPersonnage la position du personnage
     * @return la liste de position de déplacement possible
     */
    public List<Position> deplacementPossiblePersonnageDiagonaleDroite(Position positionPersonnage) {
        Optional<Parcelle> optPositionParcellePersonnage = plateau.getParcelle(positionPersonnage);
        List<Position> listPositionDiagonale = new ArrayList<>();

        if ( optPositionParcellePersonnage.isPresent() ) {
            listPositionDiagonale.addAll(getPossibleDeplacement(0,optPositionParcellePersonnage.get()));
            listPositionDiagonale.addAll(getPossibleDeplacement(3, optPositionParcellePersonnage.get()));
        }

        else {
            assert false : "Le personnage doit pas être en dehors du plateau";
        }
        return listPositionDiagonale;
    }

    /**
     * Renvoie la liste de position de déplacement possible en diagonale gauche sur le plateau
     * @param positionPersonnage la position du personnage
     * @return la liste de position de déplacement possible
     */
    public List<Position> deplacementPossiblePersonnageDiagonaleGauche(Position positionPersonnage) {
        Optional<Parcelle> optPositionParcellePersonnage = plateau.getParcelle(positionPersonnage);
        List<Position> listPositionDiagonale = new ArrayList<>();

        if ( optPositionParcellePersonnage.isPresent() ) {
            listPositionDiagonale.addAll(getPossibleDeplacement(2,optPositionParcellePersonnage.get()));
            listPositionDiagonale.addAll(getPossibleDeplacement(5, optPositionParcellePersonnage.get()));
        }

        else {
            assert false : "Le personnage doit pas être en dehors du plateau";
        }
        return listPositionDiagonale;
    }

    /**
     * Renvoie la liste de position de déplacement possible sur la même ligne du plateau
     * @param positionPersonnage la position du personnage
     * @return la liste de position de déplacement possible
     */
    public List<Position> deplacementPossiblePersonnageHorizontal(Position positionPersonnage) {
        Optional<Parcelle> optPositionParcellePersonnage = plateau.getParcelle(positionPersonnage);
        List<Position> listPositionDiagonale = new ArrayList<>();

        if ( optPositionParcellePersonnage.isPresent() ) {
            listPositionDiagonale.addAll(getPossibleDeplacement(1,optPositionParcellePersonnage.get()));
            listPositionDiagonale.addAll(getPossibleDeplacement(4, optPositionParcellePersonnage.get()));
        }

        else {
            assert false : "Le personnage doit pas être en dehors du plateau";
        }
        return listPositionDiagonale;
    }

    /**
     * Renvoie la liste des positions de déplacement filtrée par la couleur demandée de la parcelle
     * @param positionsDeplacement est la liste des positions de déplacement possibles
     * @param couleur est la couleur demandée
     * @return la liste des positions de déplacement filtrée
     */
    public List<Position> filtrePositionsParcelleCouleur(List<Position> positionsDeplacement, Couleur couleur) {
        List<Position> positionsFiltrees = new ArrayList<>();
        List<ParcelleCouleur> parcellesCouleur = plateau.getParcellesCouleur(couleur);
        for (ParcelleCouleur parcelleCouleur : parcellesCouleur) {
            Position positionParcelleCouleur = parcelleCouleur.position();
            if (positionsDeplacement.contains(positionParcelleCouleur)) {
                positionsFiltrees.add(positionParcelleCouleur);
            }
        }
        return positionsFiltrees;
    }

    /**
     * Renvoie la liste des positions de déplacement filtrée par la couleur demandée des bambous
     * @param positionsDeplacement est la liste des positions de déplacement possibles
     * @param couleur est la couleur demandée
     * @return la liste des positions de déplacement filtrée
     */
    public List<Position> filtrePositionsBambouCouleur(List<Position> positionsDeplacement, Couleur couleur) {
        List<Position> positionsFiltrees = new ArrayList<>();
        List<Position> positionsBambouCouleur = plateau.getBambousCouleur(couleur);
        for (Position position : positionsBambouCouleur) {
            if (positionsDeplacement.contains(position)) {
                positionsFiltrees.add(position);
            }
        }
        return positionsFiltrees;
    }

    /**
     * Renvoie une des "meilleurs" position possible pour créer un motif sur le Plateau
     * @param objectifParcelle L'objectif à réaliser
     * @return position possible pour créer un motif sur le Plateau
     */

    public Optional<Position> positionPossiblePrendrePourMotif(ObjectifParcelle objectifParcelle){
        Position[] positionDisponible = plateau.getPositionsDisponible();
        Parcelle[] parcellesMap = plateau.getParcelles();
        Parcelle parcellePlusProcheObjectif = GestionnairePossibiliteMotif.getParcellePlusProcheObjectif(parcellesMap,objectifParcelle);
        Parcelle[] motifAFaire = objectifParcelle.getMotif().getTableauParcelles();
        Parcelle[] motifParcelle = GestionnairePossibiliteMotif.getMotifAFaire(parcellesMap, parcellePlusProcheObjectif,motifAFaire);

        if(GestionnairePossibiliteMotif.checkMotifComplet(motifParcelle)) return Optional.empty();

        Optional<Position> positionARecuperer = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(positionDisponible, motifParcelle);
        if(positionARecuperer.isPresent()) return positionARecuperer;

        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(positionDisponible,motifParcelle);
        if(optPosition.isPresent()) return optPosition;
        else return Optional.of(positionDisponible[0]);
    }
}
