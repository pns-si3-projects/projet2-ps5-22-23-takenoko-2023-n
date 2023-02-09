package fr.cotedazur.univ.polytech.startingpoint.motif;

import fr.cotedazur.univ.polytech.startingpoint.jeu.Position;
import fr.cotedazur.univ.polytech.startingpoint.objectif.ObjectifParcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.Parcelle;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;
import fr.cotedazur.univ.polytech.startingpoint.plateau.GestionParcelles;

import java.util.Optional;

public class GestionnairePossibiliteMotifVerification {
    private GestionnairePossibiliteMotifVerification() {
        throw new IllegalStateException();
    }

    public static boolean checkCouleurAndIrrigation(ParcelleCouleur[] tabParcelles, ParcelleCouleur[] motifAFaire) {
        for (int i = 0; i < motifAFaire.length; i++) {
            if (!tabParcelles[i].isIrriguee() || !tabParcelles[i].getCouleur().equals(motifAFaire[i].getCouleur())) {
                return false;
            }
        }
        return true;
    }

    public static Optional<ParcelleCouleur[]> getMotifAFaire(Parcelle[] tableauParcellesPlateau, ParcelleCouleur parcelleMotifAFaire, ParcelleCouleur[] motifAFaire){
        int differenceX = parcelleMotifAFaire.getPosition().getX() - motifAFaire[0].getPosition().getX();
        int differenceY = parcelleMotifAFaire.getPosition().getY() - motifAFaire[0].getPosition().getY();
        ParcelleCouleur[] motifRessemblantAuMotifAFaire = new ParcelleCouleur[motifAFaire.length];
        Position positionEtang = new Position();
        motifRessemblantAuMotifAFaire[0] = parcelleMotifAFaire;

        for (int i = 1; i < motifAFaire.length; i++) {
            Position positionMotif = motifAFaire[i].getPosition();
            Position positionACheck = new Position(positionMotif.getX() + differenceX, positionMotif.getY() + differenceY);

            if (positionACheck.equals(positionEtang)) {
                return Optional.empty();
            }

            Optional<Parcelle> optParcelle = GestionParcelles.chercheParcelle(tableauParcellesPlateau, positionACheck);

            if (optParcelle.isEmpty()) {
                return Optional.empty();
            }

            motifRessemblantAuMotifAFaire[i] = (ParcelleCouleur) optParcelle.get();
        }
        return Optional.of(motifRessemblantAuMotifAFaire);
    }

    public static boolean checkMotifInBoard(Parcelle[] tableauParcellesPlateau, ObjectifParcelle objectifParcelle) {
        Motif motifAFaire = objectifParcelle.getSchema();
        if (tableauParcellesPlateau.length < motifAFaire.getTableauParcelles().length) {
            return false;
        }

        Position positionEtang = new Position();
        ParcelleCouleur[][] allOrientation = motifAFaire.getOrientation();

        for (ParcelleCouleur[] orientation : allOrientation) {
            for (Parcelle parcelle : tableauParcellesPlateau) {
                if (!parcelle.getPosition().equals(positionEtang)) {
                    Optional<ParcelleCouleur[]> optionalMotifRessemblant = getMotifAFaire(tableauParcellesPlateau,
                            (ParcelleCouleur) parcelle, orientation);

                    if (optionalMotifRessemblant.isPresent() && checkCouleurAndIrrigation(optionalMotifRessemblant.get(), motifAFaire.tabParcelles)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
