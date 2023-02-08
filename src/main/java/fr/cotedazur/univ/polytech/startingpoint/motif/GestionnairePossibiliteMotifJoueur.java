package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleDisponible;

import java.util.Optional;

/**
 * Classe qui permet de gérer les possibilités de Motifs possible
 * @author equipe N
 */
public class GestionnairePossibiliteMotif {

    private GestionnairePossibiliteMotif() {
        throw new IllegalStateException();
    }

    /**
     * Renvoie le nombre de Parcelle qui ressemble au motif dans l'objectif Parcelle
     * @param tableauParcellePlateau le tableau des Parcelles du Plateau
     * @param parcelleACheck La parcelle avec laquelle on veut essayer de faire un motif
     * @param motifAFaire La liste des Parcelles pour faire le motifs
     * @return Le nombre de Parcelle qui ressemble au motif
     */
    private static int nombreParcelleMotif(Parcelle[] tableauParcellePlateau, Parcelle parcelleACheck, Parcelle[] motifAFaire) {
        Position positionEtang = new Position(0, 0);
        if(parcelleACheck.getPosition().equals(positionEtang)) return 0;

        int differenceX = parcelleACheck.getPosition().getX() - motifAFaire[0].getPosition().getX();
        int differenceY = parcelleACheck.getPosition().getY() - motifAFaire[0].getPosition().getY();
        int nombreParcelleProcheMotif = 1;


        for (int i = 1; i < motifAFaire.length; i++) {
            Position positionMotif = motifAFaire[i].getPosition();
            Position positionACheck = new Position(positionMotif.getX() + differenceX, positionMotif.getY() + differenceY);

            for (Parcelle parcellePlateau : tableauParcellePlateau) {
                if ( positionEtang.equals(positionACheck) ) return 0; // impossible de le faire avec l'Etang dans le motif
                if ( parcellePlateau.getPosition().equals(positionACheck) && !parcelleACheck.getPosition().equals(positionACheck) ) {
                    nombreParcelleProcheMotif++;
                    break;
                }
            }
        }
        return nombreParcelleProcheMotif;
    }

    /**
     * Renvoie la Parcelle qui peut s'approcher de l'objectif à faire
     * @param tableauParcellePlateau Le tableau des Parcelles posées sur le plateau
     * @param parcellesMotif Le tableau de parcelles du motifs
     * @return Renvoie la Parcelle qui peut s'approcher de l'objectif à faire
     */
    public static Parcelle getParcellePlusProcheObjectif(Parcelle[] tableauParcellePlateau, Parcelle[] parcellesMotif) {
        int maxNombreParcelleMotif = 0;
        Parcelle parcelleMaxMotif = new Etang();

        for (Parcelle parcellePlateau : tableauParcellePlateau) {
            int nombreParcelle = nombreParcelleMotif(tableauParcellePlateau, parcellePlateau, parcellesMotif);

            if (nombreParcelle == parcellesMotif.length) return parcellePlateau;
            else if (nombreParcelle > maxNombreParcelleMotif) {
                parcelleMaxMotif = parcellePlateau;
                maxNombreParcelleMotif = nombreParcelle;
            }
        }
        return parcelleMaxMotif;
    }

    /*public static Parcelle[] getMotifAFaire(Parcelle[] tableauParcellesPlateau, Parcelle parcelleMotifAFaire, Parcelle[] motifAFaire){
        int differenceX = parcelleMotifAFaire.getPosition().getX() - motifAFaire[0].getPosition().getX();
        int differenceY = parcelleMotifAFaire.getPosition().getY() - motifAFaire[0].getPosition().getY();
        Parcelle[] motifRessemblantAuMotifAFaire = new Parcelle[motifAFaire.length];
        motifRessemblantAuMotifAFaire[0] = parcelleMotifAFaire;

        for (int i = 1; i < motifAFaire.length; i++) {
            Position positionMotif = motifAFaire[i].getPosition();
            Position positionACheck = new Position(positionMotif.getX() + differenceX, positionMotif.getY() + differenceY);
            Parcelle parcelleTrouve = null;

            for (Parcelle parcellePlateau : tableauParcellesPlateau) {
                if (parcellePlateau.getPosition().equals(positionACheck)) {
                    parcelleTrouve = parcellePlateau;
                    break;
                }
            }

            if (parcelleTrouve == null) parcelleTrouve = new ParcelleDisponible(positionACheck);
            motifRessemblantAuMotifAFaire[i] = parcelleTrouve;
        }
        return motifRessemblantAuMotifAFaire;
    }*/

    /**
     * Renvoie le nombre de Parcelle Couleur dans le motif
     * @param tabParcelleMotifRessemblant Tableau de parcelle avec lesquels on a créer le motif
     * @return le nombre de Parcelle Couleur dans le motif
     */
    protected static int countParcelleCouleurMotif(Parcelle[] tabParcelleMotifRessemblant) {
        int count = 0;
        for (Parcelle parcelleMotif : tabParcelleMotifRessemblant) {
            if (parcelleMotif.getClass() == ParcelleCouleur.class) {
                count++;
            }
        }
        return count;
    }

    /**
     * Renvoie la position si elle existe dans le tableau de Position Disponible
     * @param tableauPositionDisponible Le tableau des Positions Disponible dans le plateau
     * @param positionParcelleATrouver La position de la parcelle a trouver dans le tableau
     * @return la position si elle existe dans le tableau de Position Disponible
     */
    private static Optional<Position> cherchePositionPossibilite(Position[] tableauPositionDisponible, Position positionParcelleATrouver){
        for(Position positionDisponible : tableauPositionDisponible) {
            if ( positionDisponible.equals(positionParcelleATrouver) ) return Optional.of(positionDisponible);
        }
        return Optional.empty();
    }

    /**
     * Cherche dans le motif crée (qui est proche du motif à faire), la position qui peut completer le motif (en vérifiant si elle est dans le tableau des positionsPossible)
     * @param tableauPositionDisponible Le tableau contenant toute les positions Disponibles du plateau
     * @param motifParcelle Le motif crée avec la Parcelle qui est la plus proche pour faire l'objectif
     * @return la position qui peut completer le motif
     */
    public static Optional<Position> cherchePositionPossibilitePourFaireMotif(Position[] tableauPositionDisponible, Parcelle[] motifParcelle){
        for (int i = 1; i < motifParcelle.length; i++) {
            if (motifParcelle[i].getClass() == ParcelleDisponible.class) {
                Optional<Position> positionPossible = cherchePositionPossibilite(tableauPositionDisponible, motifParcelle[i].getPosition());
                if (positionPossible.isPresent()) return positionPossible;
            }
        }
        return Optional.empty();
    }

    /**
     * Renvoie une position en fonction de l'indice du tableau des voisins et de la position qu'on souhaite ajouter
     * @param indiceVoisin L'indice du Voisin de la position qu'on souhaite ajouter
     * @param positionSource Position de la parcelleSource à laquelle on cherche son voisin
     * @return une position en fonction de l'indice du tableau des voisins
     */
    private static Position positionTab(int indiceVoisin, Position positionSource){
        int x = positionSource.getX();
        int y = positionSource.getY();
        return switch (indiceVoisin) {
            case 0 -> new Position(x + 1,y + 1);
            case 1 -> new Position( x + 2,y);
            case 2 -> new Position(x + 1,y - 1);
            case 3 -> new Position(x - 1, y - 1);
            case 4 -> new Position(x - 2, y);
            case 5 -> new Position(x - 1 ,y + 1);
            default -> null;
        };
    }


    /**
     * Renvoie la position plus proche de la position qu'on veut poser sur le plateau
     * @param positionDisponible Le tableau des position disponible sur le Plateau
     * @param positionMotifAPoser La position du Motif qu'on veut poser
     * @return La position plus proche de la position qu'on veut poser sur le plateau
     */
    private static Position cherchePositionPlusProcheParcelleAPoser(Position[] positionDisponible, Position positionMotifAPoser) {
        for (int i = 0; i < 6; i++) {
            Position positionATrouver = positionTab(i, positionMotifAPoser);
            if ( cherchePositionPossibilite(positionDisponible, positionATrouver).isPresent() ) return positionATrouver;
        }
        return null; // Impossible
    }

    /**
     * Renvoie la position si elle existe et si aucune des positions du motifs crées avec la parcelle est possible
     * @param positionDisponible Le tableau de position Disponible sur le plateau
     * @param motifParcelle Le motif créer avec la Parcelle la plus proche de l'objectif
     * @return La position à ajouter au Plateau
     */
    public static Optional<Position> cherchePositionARecuperer(Position[] positionDisponible, Parcelle[] motifParcelle) {
        for (int i = 1; i < motifParcelle.length; i++) {
            if ( motifParcelle[i].getClass() == ParcelleDisponible.class ) {
                if (cherchePositionPossibilite(positionDisponible,motifParcelle[i].getPosition()).isPresent()) return Optional.of(motifParcelle[i].getPosition());
                return Optional.ofNullable(cherchePositionPlusProcheParcelleAPoser(positionDisponible, motifParcelle[i].getPosition()));
            }
        }
        return Optional.empty();
    }

    /**
     * Renvoie <code>true</code> si le motif est sur le board (méthode pour l'arbitre)
     * @param parcellesBoard Le tableau des parcelles sur le board
     * @param objectifParcelle L'objectif Parcelle à réaliser
     * @return <code>true</code> si le motif est sur le board
     */
    public static boolean checkMotifInBoard(Parcelle[] parcellesBoard, ObjectifParcelle objectifParcelle) {
        ParcelleCouleur[][] allOrientations = objectifParcelle.getSchema().getOrientation();

        for(ParcelleCouleur[] orientation : allOrientations) {
            Parcelle parcelleMax = getParcellePlusProcheObjectif(parcellesBoard, orientation);
            Parcelle[] motifRessemblantAuMotifParcelle = getMotifAFaire(parcellesBoard, parcelleMax, orientation);
            if ( countParcelleCouleurMotif(motifRessemblantAuMotifParcelle ) == orientation.length) { // Ici on vérifie qu'il y a que des parcelles couleurs et comme avec les méthodes précédentes on reproduit le même motif alors le motif est donc le même
                return true;
            }
        }

        return false;
    }

    /**
     * Renvoie une des "meilleurs" position possible pour créer un motif sur le Plateau
     * @param objectifParcelle L'objectif à réaliser
     * @return position possible pour créer un motif sur le Plateau
     */
    public static Optional<Position> positionPossiblePrendrePourMotif(Parcelle[] parcellesBoard, Position[] positionsDisponiblesBoard, ObjectifParcelle objectifParcelle) {
        ParcelleCouleur[][] allOrientations = objectifParcelle.getSchema().getOrientationForIA();
        int maxParcelleMotif = -1;
        Parcelle[] motifPlusRessemblant = new Parcelle[0];

        for (ParcelleCouleur[] orientation : allOrientations) {
            Parcelle parcelleMax = getParcellePlusProcheObjectif(parcellesBoard, orientation);
            Parcelle[] motifRessmblantAuMotifParcelle = getMotifAFaire(parcellesBoard, parcelleMax, orientation);
            int nombreParcelleCouleurMotif = countParcelleCouleurMotif(motifRessmblantAuMotifParcelle);

            if (nombreParcelleCouleurMotif > maxParcelleMotif) {
                if (nombreParcelleCouleurMotif == orientation.length) {
                    return Optional.empty();
                }

                motifPlusRessemblant = motifRessmblantAuMotifParcelle;
                maxParcelleMotif = nombreParcelleCouleurMotif;
            }
        }

        Optional<Position> positionARecuperer = GestionnairePossibiliteMotif.cherchePositionPossibilitePourFaireMotif(positionsDisponiblesBoard, motifPlusRessemblant);
        if(positionARecuperer.isPresent()) return positionARecuperer;

        Optional<Position> optPosition = GestionnairePossibiliteMotif.cherchePositionARecuperer(positionsDisponiblesBoard,motifPlusRessemblant);
        if(optPosition.isPresent()) return optPosition;
        else return Optional.of(positionsDisponiblesBoard[0]);
    }
}
