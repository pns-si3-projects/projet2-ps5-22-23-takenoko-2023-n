package fr.cotedazur.univ.polytech.startingpoint;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class WriteCSV {
    private static final Path cheminFichier = FileSystems.getDefault().getPath("stats", "data.csv");

    public static void main(String[] args) {
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(cheminFichier.toFile()));
        } catch (IOException e) {
            throw new AssertionError(e);
        }

        //Create record
        String[] str = "test,encore,jsp,coucou".split(",");

        //Write the record to file
        writer.writeNext(str, false);

        //close the writer
        try {
            writer.close();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
