package CSV;

import CSV.Exceptions.InvalidCountry;

import java.util.Date;

public class pruebaCSV {
    public static void main(String[] args) throws InvalidCountry {
        ManipularCSV file = new ManipularCSV();
        file.readFile("C:\\Users\\Usuario\\Desktop\\Obligatorio Exceel.csv");
        file.opcion1("ZA", "13-5-2024");

    }


}
