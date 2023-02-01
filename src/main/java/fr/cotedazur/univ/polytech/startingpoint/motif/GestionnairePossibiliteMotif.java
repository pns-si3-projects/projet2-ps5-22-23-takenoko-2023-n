package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Etang;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleDisponible;

import java.util.Optional;

public class GestionnairePossibiliteMotif {

    /**
     * Renvoie le nombre de Parcelle qui ressemble au motif dans l'objectif Parcelle
     * @param tableauParcellePlateau le tableau des Parcelles du Plateau
     * @param parcelleACheck La parcelle avec laquelle on veut essayer de faire un motif
     * @param motifAFaire La liste des Parcelles pour faire le motifs
     * @return Le nombre de Parcelle qui ressemble au motif
     */
    private static int nombreParcelleMotif(Parcelle[] tableauParcellePlateau, Parcelle parcelleACheck, Parcelle[] motifAFaire){
        Etang etang = new Etang();
        if(parcelleACheck.equals(etang)) return 0;
        Position positionEtang = etang.position();
        int differenceX = parcelleACheck.position().getX() - motifAFaire[0].position().getX();
        int differenceY = parcelleACheck.position().getY() - motifAFaire[0].position().getY();
        int nombreParcelleProcheMotif = 1;

        for (int i = 1; i < motifAFaire.length; i++) {
            Position positionMotif = motifAFaire[i].position();
            Position positionACheck = new Position(positionMotif.getX() + differenceX, positionMotif.getY() + differenceY);

            for (Parcelle parcellePlateau : tableauParcellePlateau) {
                if (positionEtang.equals(positionACheck) ) return 0; // impossible de le faire avec l'Etang dans le motif
                if (parcellePlateau.position().equals(positionACheck) && !parcelleACheck.position().equals(positionACheck)){
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
     * @param objectifParcelle L'objectif Parcelle qu'on veut réaliser
     * @return Renvoie la Parcelle qui peut s'approcher de l'objectif à faire
     */
    public static Parcelle getParcellePlusProcheObjectif(Parcelle[] tableauParcellePlateau, ObjectifParcelle objectifParcelle){
        Parcelle[] listParcellePlateau = tableauParcellePlateau;
        Parcelle[] motifAFaire = objectifParcelle.getMotif().getTableauParcelles();
        int maxNombreParcelleMotif = 0;
        Parcelle parcelleMaxMotif = new Etang();

        for (Parcelle parcellePlateau : listParcellePlateau) {
            int nombreParcelle = nombreParcelleMotif(listParcellePlateau,parcellePlateau,motifAFaire);

            if (nombreParcelle == motifAFaire.length) return parcellePlateau;
            else if (nombreParcelle > maxNombreParcelleMotif) {
                parcelleMaxMotif = parcellePlateau;
                maxNombreParcelleMotif = nombreParcelle;
            }
        }
        return parcelleMaxMotif;
    }

    /**
     * Crée le Motif avec la Parcelle qui est la plus proche de finir l'objectif
     * @param tableauParcellesPlateau Tableau des parcelles du plateau
     * @param parcelleMotifAFaire Parcelle qui est la plus proche de finir l'objectif
     * @param motifAFaire Motif à faire avec la parcelle
     * @return le tableau du Motif ressemblant au motif de l'objectif parcelle
     */
    public static Parcelle[] getMotifAFaire(Parcelle[] tableauParcellesPlateau, Parcelle parcelleMotifAFaire, Parcelle[] motifAFaire){
        int differenceX = parcelleMotifAFaire.position().getX() - motifAFaire[0].position().getX();
        int differenceY = parcelleMotifAFaire.position().getY() - motifAFaire[0].position().getY();
        Parcelle[] motifRessemblantAuMotifAFaire = new Parcelle[motifAFaire.length];
        motifRessemblantAuMotifAFaire[0] = parcelleMotifAFaire;

        for (int i = 1; i < motifAFaire.length; i++) {
            Position positionMotif = motifAFaire[i].position();
            Position positionACheck = new Position(positionMotif.getX() + differenceX, positionMotif.getY() + differenceY);
            Parcelle parcelleTrouve = null;

            for (Parcelle parcellePlateau : tableauParcellesPlateau) {
                if (parcellePlateau.position().equals(positionACheck)) {
                    parcelleTrouve = parcellePlateau;
                    break;
                }
            }

            if (parcelleTrouve == null) parcelleTrouve = new ParcelleDisponible(positionACheck);
            motifRessemblantAuMotifAFaire[i] = parcelleTrouve;
        }
        return motifRessemblantAuMotifAFaire;
    }

    /**
     * Vérifie si le motif est complet
     * @param parcelleMotif Tableau de parcelle avec lesquels on a créer le motif
     * @return <code>true</code> si le motif est complet sinon <code>false</code>
     */
    public static boolean checkMotifComplet(Parcelle[] parcelleMotif){
        for(int i = 0; i < parcelleMotif.length; i++){
            if(parcelleMotif[i].getClass() != ParcelleCouleur.class) return false;
        }
        return true;
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
                Optional<Position> positionPossible = cherchePositionPossibilite(tableauPositionDisponible, motifParcelle[i].position());
                if (positionPossible.isPresent()) return positionPossible;
            }
        }
        return Optional.empty();
    }

    /**
     * Renvoie une position en fonction de l'indice du tableau des voisins et de la position qu'on souhaite ajouter
     * @param indiceVoisin L'indice du Voisin de la position qu'on souhaite ajouter
     * @param positionATrouver
     * @return une position
     */
    private static Position positionTab(int indiceVoisin, Position positionATrouver){
        int x = positionATrouver.getX();
        int y = positionATrouver.getY();
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
    public static Optional<Position> cherchePositionARecuperer(Position[] positionDisponible, Parcelle[] motifParcelle){
        for (int i = 1; i < motifParcelle.length; i++) {
            if ( motifParcelle[i].getClass() == ParcelleDisponible.class ) {
                if (cherchePositionPossibilite(positionDisponible,motifParcelle[i].position()).isPresent()) return Optional.of(motifParcelle[i].position());
                return Optional.ofNullable(cherchePositionPlusProcheParcelleAPoser(positionDisponible, motifParcelle[i].position()));
            }
        }
        return Optional.empty();
    }
}
