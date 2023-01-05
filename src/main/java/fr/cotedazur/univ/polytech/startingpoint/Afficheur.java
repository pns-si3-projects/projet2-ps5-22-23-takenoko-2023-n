package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Optional;

public class Afficheur {

    public void afficheGagnant(Optional<Joueur> joueur){
        if(joueur.isEmpty()){
            System.out.println("Il y a eu une égalité");
        }
        else {
            Joueur joueurGagnant = joueur.get();
            System.out.println("Le joueur gagnant est le joueur "+joueurGagnant.getNom()+" avec "+joueurGagnant.getPoints()+" Points");
        }
    }

    public void afficheAjoutParcelle(ParcelleCouleur parcelleCouleurAjoute){
        if(parcelleCouleurAjoute != null){
            System.out.println("La "+parcelleCouleurAjoute+" est pose");
        }
    }

    public void afficheDebutTour(int nombreDeTour){
        System.out.println("Tour numero"+nombreDeTour);
    }

    public void afficheFinTour(int nombreDeTour){
        System.out.println("Tour numéro"+nombreDeTour+" terminé");
        System.out.println("\n-------------------------------------------------\n");
    }

    public void afficheJoueurDebutTour(Joueur joueur){
        System.out.println("Au tour du joueur "+joueur.getNom());
    }

    public void afficheJoueurFinTour(Joueur joueur){
        System.out.println("Fin du tour du joueur "+joueur.getNom()+"\n");
    }

    public void affichePiocheCarte(Objectif objectif){
        String messageAAffiche;
        if(objectif.getClass() == ObjectifParcelle.class){
            messageAAffiche = "Parcelle";
        }
        else if(objectif.getClass() == ObjectifPanda.class){
            messageAAffiche = "Panda";
        }
        else {
            messageAAffiche = "Jardinier";
        }
        System.out.println("Objectif "+messageAAffiche+" pioché");
    }

    public void afficheObjectifValidé(Objectif objectif){
        System.out.println(objectif+" terminé");
    }
}
