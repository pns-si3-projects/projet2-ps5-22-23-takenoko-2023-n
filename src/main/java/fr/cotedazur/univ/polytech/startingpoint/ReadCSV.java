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
import java.util.List;

public class ReadCSV {
    // Définition des attributs

    private static final Path cheminFichier = FileSystems.getDefault().getPath("stats", "data.csv");


    // Définition d'un constructeur privé pour éviter les instanciations

    private ReadCSV() {
        throw new IllegalStateException("Utility class");
    }


    // Méthodes d'utilisation

    public static String read() {
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();
        CSVReader csvReader;
        try {
            csvReader = new CSVReaderBuilder(new FileReader(cheminFichier.toFile()))
                    .withCSVParser(csvParser)
                    .build();
        } catch (FileNotFoundException e) {
            throw new AssertionError(e);
        }

        //Read CSV line by line and use the string array as you want
        try {
            List<String[]> listString = csvReader.readAll();

            if (listString.size() > 1) {
                String[] chaine = listString.get(listString.size() - 1);
                csvReader.close();
                return chaine[chaine.length - 1];
            }
            csvReader.close();

        } catch (CsvException | IOException e) {
            throw new AssertionError(e);
        }
        return "0";
    }
}
