package fr.cotedazur.univ.polytech.startingpoint;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
public class WriteCSV {
    private static final Path cheminFichier = FileSystems.getDefault().getPath("stats", "data.csv");

    public static void main(String[] args) {
        CSVWriter writer;
        try {
            writer = new CSVWriter(new FileWriter(cheminFichier.toFile(), true));
        } catch (IOException e) {
            throw new AssertionError(e);
        }
        String[] str;
        if(args[args.length - 1].equals(args[args.length - 2])) {
            //Create record
            str = "J1,J2,J3,J4,total,totalParties".split(",");
            writer.writeNext(str, false);
        }
        str = args;
        writer.writeNext(str, false);

        //close the writer
        try {
            writer.close();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
