package fr.cotedazur.univ.polytech.startingpoint;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class WriteCSV {
    // Définition des attributs

    private static final Path cheminFichier = FileSystems.getDefault().getPath("stats", "data.csv");


    // Définition d'un constructeur privé pour éviter les instanciations

    private WriteCSV() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    public static void ecrireCSV(List<JoueurStats> joueurStatsList) {
        CSVWriter writer;
        try {
            writer = new CSVWriter(new FileWriter(cheminFichier.toFile()));
        } catch (IOException e) {
            throw new AssertionError(e);
        }

        String[] entete = ("Joueurs,nb parties gagnées,% parties gagnées" +
                ",nb parties perdues,% parties perdues" +
                ",nb parties nulles,% parties nulles,score moyen")
                        .split(",");
        writer.writeNext(entete, false);

        for (JoueurStats joueurStats : joueurStatsList) {
            writer.writeNext(joueurStats.envoieStatistiques(), false);
        }

        //close the writer
        try {
            writer.close();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
