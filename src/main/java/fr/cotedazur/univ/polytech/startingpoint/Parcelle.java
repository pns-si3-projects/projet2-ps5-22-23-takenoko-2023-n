package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Interface représentant une parcelle sur le plateau ou une ParcelleDisponible
 * @author equipe N
 */
public interface Parcelle extends Positionnable {
    /**
     * Renvoie si les 2 parcelles sont égales
     * @param o est l'objet à comparer avec <code>this</code>
     * @return <code>true</code> si les 2 parcelles ont les même valeurs d'attributs, <code>false</code> sinon
     */
    boolean equals(Object o);
}
