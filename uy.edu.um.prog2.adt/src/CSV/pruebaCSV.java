package CSV;

import CSV.Exceptions.InvalidCountry;
import TADs.Hash.Exceptions.InvalidHashKey;

import java.io.FileNotFoundException;
import java.util.Date;

<<<<<<< HEAD
//public class pruebaCSV {
//    public static void main(String[] args) throws InvalidCountry {
//        ManipularCSV file = new ManipularCSV();
//        file.readFile("C:\\Users\\Usuario\\Desktop\\Obligatorio Exceel.csv");
//        file.opcion1("ZA", "13-5-2024");
//
//    }
=======
import static CSV.MisMetodos.opcion1;

public class pruebaCSV {
    public static void main(String[] args) throws InvalidCountry, FileNotFoundException {
        try {
            ManipularCSV file = new ManipularCSV();
            Metodos metodos = new Metodos();
            file.readFile("C:\\Users\\Usuario\\Desktop\\Obligatorio Exceel.csv");
            //file.printLine();
            metodos.opcion1("ZA", "13-5-2024", file);
        } catch (InvalidCountry e) {
            System.err.println(e.getMessage());
        }

    }
>>>>>>> 83dc2f4ce9d4d127c07b8175f06ca61139c6a99e


