package fr.cotedazur.univ.polytech.startingpoint.jeu;

/**
 * Représente les couleurs possibles.
 * @author équipe N
 */
public enum Couleur {
    // Définition des attributs

    VERTE("verte") {
        @Override
        public boolean isVerte() {
            return true;
        }
    },
    ROSE("rose") {
        @Override
        public boolean isRose() {
            return true;
        }
    },
    JAUNE("jaune") {
        @Override
        public boolean isJaune() {
            return true;
        }
    };
    private final String couleur;


    // Définition des contructeurs

    /**
     * Construit la couleur demandée
     * @param couleur la couleur demandée
     */
    Couleur(String couleur) {
        this.couleur = couleur;
    }


    // Accesseurs

    /**
     * Renvoie la couleur
     * @return la couleur
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * Renvoie si la couleur est verte
     * @return {@code true} si la couleur est verte
     */
    public boolean isVerte() {
        return false;
    }

    /**
     * Renvoie si la couleur est rose
     * @return {@code true} si la couleur est rose
     */
    public boolean isRose() {
        return false;
    }

    /**
     * Renvoie si la couleur est jaune
     * @return {@code true} si la couleur est jaune
     */
    public boolean isJaune() {
        return false;
    }

    @Override
    public String toString() {
        return couleur;
    }
}
