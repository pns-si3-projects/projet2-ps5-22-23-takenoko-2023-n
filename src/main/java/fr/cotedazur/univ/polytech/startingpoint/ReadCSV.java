package fr.cotedazur.univ.polytech.startingpoint;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReadCSV {
    // Définition des attributs

    private static final Path cheminFichier = FileSystems.getDefault().getPath("stats", "data.csv");


    // Définition d'un constructeur privé pour éviter les instanciations

    private ReadCSV() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    /**
     * Initialise la lecture du fichier CSV
     * @return {@code Optional<CSVReader>} si le fichier existe, {@code Optional.empty} sinon
     */
    public static Optional<CSVReader> initialiseLectureCSV() {
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader;
        try {
            csvReader = new CSVReaderBuilder(new FileReader(cheminFichier.toFile()))
                    .withSkipLines(1)
                    .withCSVParser(csvParser)
                    .build();
            return Optional.of(csvReader);
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }

    /**
     * Lit le fichier par le CSVReader donné et renvoie les données
     * @param csvReader le CSVReader innitialisé pour lire les données du fichier de stats
     * @return une liste de JoueurStats contenant les données du fichier de stats
     */
    public static List<JoueurStats> litCSV(CSVReader csvReader, List<String> nomsJoueurs) {
        List<JoueurStats> joueurStatsList = new ArrayList<>();

        //Read CSV line by line and use the string array as you want
        try {
            List<String[]> listString = csvReader.readAll();

            for (String[] ligne : listString) {
                if (ligne.length == JoueurStats.NOMBRE_DONNEES) {
                    joueurStatsList.add(JoueurStats.joueurAvecStatistiques(ligne)); // Ajoute les données de la ligne
                }
            }
            csvReader.close();
        }
        catch (CsvException | IOException e) {
            throw new AssertionError(e);
        }

        joueurStatsList = verifieNomsJoueurs(joueurStatsList, nomsJoueurs);
        return joueurStatsList;
    }

    /**
     * Renvoie la liste des JoueurStats qui ont un nom demandé
     * @param joueurStatsList la liste des JoueurStats
     * @param nomsJoueurs la liste des noms de joueurs demandés
     * @return la liste des JoueurStats qui ont un nom demandé
     */
    private static List<JoueurStats> verifieNomsJoueurs(List<JoueurStats> joueurStatsList, List<String> nomsJoueurs) {
        List<JoueurStats> joueursStats = new ArrayList<>();

        for (String nomJoueur: nomsJoueurs) {
            for (JoueurStats joueurStats : joueurStatsList) {
                if (joueurStats.getNomJoueur().equals(nomJoueur)) {
                    joueursStats.add(joueurStats);
                    break;
                }
            }
        }
        return joueursStats;
    }
}
