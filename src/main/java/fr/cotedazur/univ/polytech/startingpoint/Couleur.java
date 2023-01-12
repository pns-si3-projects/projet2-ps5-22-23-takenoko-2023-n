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


    // Accesseurs
    public boolean isVert() {
        return false;
    }

    public boolean isRose() {
        return false;
    }

    public boolean isJaune() {
        return false;
    }

    public String getCouleur() {
        return couleur;
    }
}
