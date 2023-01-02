package fr.cotedazur.univ.polytech.startingpoint;

public class Main {
    public static final Plateau plateau = new Plateau();
    public static final GestionnairePossibilitePlateau GESTIONNAIRE_PLATEAU = new GestionnairePossibilitePlateau(plateau);
    public static void main(String... args) {
        System.out.println("Hello World !!!");
    }
}
