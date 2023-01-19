package fr.cotedazur.univ.polytech.startingpoint.objectif;

import fr.cotedazur.univ.polytech.startingpoint.Position;
import fr.cotedazur.univ.polytech.startingpoint.parcelle.ParcelleCouleur;

import java.util.Objects;


/**
 * Classe représentant le motif d'un objectif Parcelle
 * @author equipe N
 */
public class Motif {
    private final ParcelleCouleur[] tabParcelles;

    /**
     * Constructeur par défaut
     * @param parcellesCouleurs liste de Parcelles pour le motifs
     * @throws MotifNonValideException Renvoie cette erreur si 2 parcelles ne sont pas voisines
     */
    public Motif(ParcelleCouleur... parcellesCouleurs) throws MotifNonValideException {
        if(parcellesCouleurs.length < 2 || parcellesCouleurs.length > 4) throw new IllegalArgumentException("Trop d'arguments en paramètres");
        tabParcelles = parcellesCouleurs;
        if(!checkMotifsParcelle()) throw new MotifNonValideException();
    }

    /**
     * Méthode qui permet de vérifier si c'est un Motif
     * @return <code> true </code> si c'est un motif
     */
    public boolean checkMotifsParcelle(){
        int count = 0;
        for (int i = 0; i < tabParcelles.length; i++) {
            for (int j = 0; j < tabParcelles.length; j++) {
                Position positionParcelleI = tabParcelles[i].position();
                Position positionParcelleJ = tabParcelles[j].position();

                if (i != j && isVoisin(positionParcelleI,positionParcelleJ)) {
                    count++;
                    break;
                }

            }
        }
        return count == tabParcelles.length;
    }

    /**
     * Verifie si 2 position de parcelles sont voisines
     * @param positionParcelleCible position de la parcelle principale
     * @param positionParcelleVoisine position de la parcelle possible d'être voisine
     * @return <code> true </code> si c'est un voisin
     */
    private boolean isVoisin(Position positionParcelleCible, Position positionParcelleVoisine){
        int xV = positionParcelleVoisine.getX();
        int yV = positionParcelleVoisine.getY();
        int xC = positionParcelleCible.getX();
        int yC = positionParcelleCible.getY();

        if ((yV == yC) &&  (xV - 2 == xC || xV+2 == xC)) return true;
        else if ((yV - 1 == yC) && (xV - 1 == xC || xV + 1 == xC)) return true;
        else if ((yV + 1 == yC) && (xV - 1 == xC || xV + 1 == xC)) return true;
        return false;
    }

    /**
     * Verifie si la parcelle se trouve a la même position que celle dans l'autre motifs mais avec une distance de <code> differencePosition </code>
     * @param positionParcelleMotif La position de la parcelle où se situe dans le motif-ci
     * @param positionParcelleOtherMotif La position de la parcelle où se situe dans l'autre motif
     * @param differencePositionX la distance en X calculé auparavant entre les 2 motifs
     * @param differencePositionY la distance en Y calculé auparavant entre les 2 motifs
     * @return <code> true </code> si les parcelles sont à la même position mais pas au même endroit
     */
    private boolean comparePosition(Position positionParcelleMotif, Position positionParcelleOtherMotif, int differencePositionX, int differencePositionY) {
        int xM = positionParcelleMotif.getX();
        int yM = positionParcelleMotif.getY();
        int xOM = positionParcelleOtherMotif.getX();
        int yOM = positionParcelleOtherMotif.getY();

        return Math.abs(xM - xOM) == differencePositionX && Math.abs(yM - yOM) == differencePositionY;
    }

    /**
     * Trie le motifs pour pouvoir le comparer
     * @return renvoie une copie du motif trie
     */
    private ParcelleCouleur[] sortMotif(){
        ParcelleCouleur[] motifParcelle = new ParcelleCouleur[tabParcelles.length];
        for(int i = 0 ; i < tabParcelles.length - 1 ; i++) {
            ParcelleCouleur parcelleMin = tabParcelles[i];

            for(int j = i + 1; j < tabParcelles.length ;j++) {
                Position positionParcelleMin = parcelleMin.position();
                Position positionParcelleJ = tabParcelles[j].position();

                if(positionParcelleMin.compareTo(positionParcelleJ) > 0){
                    parcelleMin = tabParcelles[j];
                }
            }

            motifParcelle[i] = parcelleMin;
        }
        return motifParcelle;
    }

    /**
     * Compare les 2 motifs pour le equals
     * @param otherMotif Le motif à comparer
     * @return <code> true </code> si le motif est le même
     */
    public boolean compareMotif(Motif otherMotif){
        if(otherMotif.tabParcelles.length != tabParcelles.length) return false;

        ParcelleCouleur[] motifActuel = sortMotif();
        ParcelleCouleur[] motifOther = otherMotif.sortMotif();
        int differencePositionX = Math.abs(motifActuel[0].position().getX() - motifOther[0].position().getY());
        int differencePositionY = Math.abs(motifActuel[0].position().getY() - motifOther[0].position().getY());

        for(int i = 1; i< motifActuel.length;i++){
            Position positionMotifActuel = motifActuel[i].position();
            Position positionMotifOther = motifOther[i].position();
            if(!comparePosition(positionMotifActuel,positionMotifOther,differencePositionX,differencePositionY)){
                return false;
            }
        }
        return true;
    }

    /**
     * Renvoie une copie du tableau de Parcelle du motif
     * @return une copie du tableau de Parcelle du motif
     */
    public ParcelleCouleur[] getTableauParcelles() {
        ParcelleCouleur[] copyParcelleCouleur = new ParcelleCouleur[tabParcelles.length];
        for(int i = 0; i < tabParcelles.length; i++) {
            copyParcelleCouleur[i] = tabParcelles[i];
        }
        return tabParcelles;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        else if(o == null || o.getClass() != this.getClass()) return false;
        else {
            Motif motifOther = (Motif) o;
            return compareMotif(motifOther);
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(tabParcelles);
    }
}
