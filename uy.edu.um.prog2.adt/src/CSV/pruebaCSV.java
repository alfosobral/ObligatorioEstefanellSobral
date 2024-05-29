package CSV;

import CSV.Exceptions.InvalidCountry;
import TADs.Hash.Exceptions.InvalidHashKey;

import java.io.FileNotFoundException;
import java.util.Date;

import static CSV.MisMetodos.opcion1;

public class pruebaCSV {
    public static void main(String[] args) throws InvalidCountry, FileNotFoundException {
        ManipularCSV file = new ManipularCSV();
        Metodos metodos = new Metodos();
        file.readFile("C:\\Users\\Usuario\\Desktop\\Obligatorio Exceel.csv");
        file.printLine();
        //metodos.opcion1("ZA", "13-5-2024", file);

    }


}
