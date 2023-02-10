package fr.cotedazur.univ.polytech.startingpoint;

/**
 * Représente les arguments possibles pour le Main.
 * @author équipe N
 */
public enum ArgumentPossibleMain {
    // Définition des attributs

    THOUSANDS("--2thousands") {
        @Override
        public boolean isThousands() {
            return true;
        }
    },
    DEMO("--demo") {
        @Override
        public boolean isDemo() {
            return true;
        }
    },
    CSV("--csv") {
        @Override
        public boolean isCsv() {
            return true;
        }
    };
    private final String mode;


    // Définition des contructeurs

    /**
     * Construit l'argument de mode de jeu demandé
     * @param mode le mode de jeu demandé
     */
    ArgumentPossibleMain(String mode) {
        this.mode = mode;
    }


    // Accesseurs

    /**
     * Renvoie si le mode de jeu demandé est "2 thousands"
     * @return {@code true} si le mode de jeu demandé est "2 thousands"
     */
    public boolean isThousands() {
        return false;
    }

    /**
     * Renvoie si le mode de jeu demandé est "demo"
     * @return {@code true} si le mode de jeu demandé est "demo"
     */
    public boolean isDemo() {
        return false;
    }

    /**
     * Renvoie si le mode de jeu demandé est "csv"
     * @return {@code true} si le mode de jeu demandé est "csv"
     */
    public boolean isCsv() {
        return false;
    }


    @Override
    public String toString() {
        return mode;
    }
}
