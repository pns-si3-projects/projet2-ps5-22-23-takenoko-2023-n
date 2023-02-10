package fr.cotedazur.univ.polytech.startingpoint;

import com.beust.jcommander.JCommander;
import com.opencsv.CSVReader;
import fr.cotedazur.univ.polytech.startingpoint.jeu.AfficheurJeu;
import fr.cotedazur.univ.polytech.startingpoint.jeu.MaitreDuJeu;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Joueur;
import fr.cotedazur.univ.polytech.startingpoint.joueur.Strategie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    // Définition des attributs

    private static final Logger LOGGER = Logger.getLogger(Main.class.getPackageName());
    private static final String NOM_JOUEUR_COMPLET = "Joueur complet";
    private static final String NOM_JOUEUR_PARCELLE = "Joueur parcelle";
    private static final String NOM_JOUEUR_JARDINIER = "Joueur jardinier";
    private static final String NOM_JOUEUR_PANDA = "Joueur panda";
    private static final Joueur joueurComplet = new Joueur(NOM_JOUEUR_COMPLET, Strategie.StrategiePossible.COMPLET);
    private static final Joueur joueurParcelle = new Joueur(NOM_JOUEUR_PARCELLE, Strategie.StrategiePossible.PARCELLE);
    private static final Joueur joueurJardinier = new Joueur(NOM_JOUEUR_JARDINIER, Strategie.StrategiePossible.JARDINIER);
    private static final Joueur joueurPanda = new Joueur(NOM_JOUEUR_PANDA, Strategie.StrategiePossible.PANDA);


    // Méthode d'exécution

    public static void main(String... args) {
        ArgumentPossibleMain argumentMain;

        if (args.length > 0) {
            ArgumentsMain argsMain = new ArgumentsMain();
            JCommander.newBuilder()
                    .addObject(argsMain)
                    .build()
                    .parse(args);
            argumentMain = argsMain.getArgument();
        }
        else {
            argumentMain = ArgumentPossibleMain.DEMO;
        }

        configureLogger(argumentMain);
        appliqueModeJeu(argumentMain);
    }


    // Méthodes d'utilisation

    /**
     * Permet de configurer tous les loggers (format et level affiché)
     */
    public static void configureLogger(ArgumentPossibleMain argumentMain) {
        LOGGER.setUseParentHandlers(false);

        Handler handler = new ConsoleHandler();
        handler.setFormatter(new LoggerFormatter());
        LOGGER.addHandler(handler);

        if (argumentMain.isThousands()) {
            LOGGER.setLevel(Level.WARNING);
        } else {
            LOGGER.setLevel(Level.INFO);
        }
    }

    /**
     * Applique le mode de jeu demandé
     * @param argumentMain le mode de jeu demandé
     */
    private static void appliqueModeJeu(ArgumentPossibleMain argumentMain) {
        switch (argumentMain) {
            case THOUSANDS -> joue2Thousands();
            case DEMO -> joueDemo();
            case CSV -> joueCSV();
        }
    }

    /**
     * Joue le mode de jeu de 2 × 1000 parties
     * 1000 parties entre le meilleur bot et les autres
     * 1000 parties entre le meilleur bot et lui-même
     */
    private static void joue2Thousands() {
        LOGGER.warning("Début mode 2thousands");
        for (int i=0; i<20000; i++) {
            MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueurComplet, joueurPanda, joueurJardinier, joueurParcelle);
            maitreDuJeu.jeu();
            String nbJeu = Integer.toString(i+1);
            LOGGER.warning(nbJeu);
        }
    }

    /**
     * Joue une partie de demo entre plusieurs bots
     */
    private static void joueDemo() {
        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueurComplet, joueurPanda, joueurJardinier, joueurParcelle);
        maitreDuJeu.jeu();
        AfficheurJeu.etatJeu(maitreDuJeu);
    }

    /**
     * Joue un nombre limité de parties en enregistrant des statistiques dans un fichier CSV
     */
    private static void joueCSV() {
        List<String> nomJoueurs = initialiseNomsJoueurs();
        List<JoueurStats> joueurStatsList = initialiseJoueurStatsList(nomJoueurs);
        Optional<CSVReader> optCsvReader = ReadCSV.initialiseLectureCSV();
        if (optCsvReader.isPresent()) {
            joueurStatsList = ReadCSV.litCSV(optCsvReader.get(), nomJoueurs);

            if (joueurStatsList.size() != nomJoueurs.size()) {
                joueurStatsList = initialiseJoueurStatsList(nomJoueurs);
            }
        }

        List<Joueur> joueurs = new ArrayList<>(4);
        joueurs.add(joueurComplet);
        joueurs.add(joueurPanda);
        joueurs.add(joueurParcelle);
        joueurs.add(joueurJardinier);

        MaitreDuJeu maitreDuJeu = new MaitreDuJeu(joueurs.get(0), joueurs.get(1), joueurs.get(2), joueurs.get(3));
        maitreDuJeu.jeu();
        AfficheurJeu.etatJeu(maitreDuJeu);

        ajouteDonneesJoueurs(joueurStatsList, joueurs);
        for (JoueurStats joueurStats : joueurStatsList) {
            String donneesJoueur = Arrays.toString(joueurStats.envoieStatistiques());
            LOGGER.info(donneesJoueur);
        }
    }

    /**
     * Ajoute les données aux JoueurStats
     * @param joueurStatsList la liste des JoueurStats
     * @param joueurs la liste des joueurs
     */
    private static void ajouteDonneesJoueurs(List<JoueurStats> joueurStatsList, List<Joueur> joueurs) {
        for (int i=0; i<joueurStatsList.size(); i++) {
            joueurStatsList.get(i).ajoutePartie(JoueurStats.EtatPartie.GAGNEE, (double) joueurs.get(i).nombrePoints());
        }
    }

    /**
     * Initialise la liste de noms des joueurs
     * @return la liste de noms des joueurs
     */
    private static List<String> initialiseNomsJoueurs() {
        List<String> nomsJoueurs = new ArrayList<>(4);

        nomsJoueurs.add(NOM_JOUEUR_COMPLET);
        nomsJoueurs.add(NOM_JOUEUR_PANDA);
        nomsJoueurs.add(NOM_JOUEUR_JARDINIER);
        nomsJoueurs.add(NOM_JOUEUR_PANDA);
        return nomsJoueurs;
    }

    /**
     * Initialise la liste de JoueurStats avec un nom par défaut
     * @param nomsJoueurs la liste des noms des joueurs
     * @return la liste des JoueurStats initialisés
     */
    private static List<JoueurStats> initialiseJoueurStatsList(List<String> nomsJoueurs) {
        List<JoueurStats> joueurStatsList = new ArrayList<>(nomsJoueurs.size());

        for (String nom : nomsJoueurs) {
            joueurStatsList.add(new JoueurStats(nom));
        }
        return joueurStatsList;
    }
}
