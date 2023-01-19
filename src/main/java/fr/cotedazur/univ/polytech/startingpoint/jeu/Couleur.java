package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Enum représentant les couleurs possibles
 * @author équipe N
 */
public enum Couleur {
    // Définition des attributs
    VERT("vert") {
        @Override
        public boolean isVert() {
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
     * Contructeur par défaut
     * @param couleur est la couleur demandée
     */
    Couleur(String couleur) {
        if (couleur.equalsIgnoreCase("vert")
                || couleur.equalsIgnoreCase("rose")
                || couleur.equalsIgnoreCase("jaune")) {
            this.couleur = couleur;
        } else {
            throw new IllegalArgumentException("La couleur donnée ne correspond pas aux possibilités de couleur (vert, rose, jaune)");
        }
    }


    // Accesseurs et méthode toString
    /**
     * Renvoie si la couleur est verte
     * @return <code>true</code> si la couleur est verte, <code>false</code> sinon
     */
    public boolean isVert() {
        return false;
    }

    /**
     * Renvoie si la couleur est rose
     * @return <code>true</code> si la couleur est rose, <code>false</code> sinon
     */
    public boolean isRose() {
        return false;
    }

    /**
     * Renvoie si la couleur est jaune
     * @return <code>true</code> si la couleur est jaune, <code>false</code> sinon
     */
    public boolean isJaune() {
        return false;
    }

    /**
     * Renvoie la couleur
     * @return la couleur
     */
    public String getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return couleur;
    }
}
