package fr.cotedazur.univ.polytech.startingpoint;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

public class ReadCSV {
    private static final Path cheminFichier = FileSystems.getDefault().getPath("stats", "data.csv");

    public static void main(String[] args) {
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
        String[] nextLine;
        try {
            while ((nextLine = csvReader.readNext()) != null) {
                System.out.println(Arrays.toString(nextLine));
            }
        } catch (CsvValidationException | IOException e) {
            throw new AssertionError(e);
        }

    }
}
