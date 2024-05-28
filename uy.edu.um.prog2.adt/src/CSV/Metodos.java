package CSV;

import CSV.Exceptions.InvalidCountry;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;


public class Metodos implements MisMetodos{
    public String[] auxList;
    public String[] top10;
    ManipularCSV file = new ManipularCSV();



    @Override
    public void opcion2(Date dia) {

    }

    @Override
    public void opcion3(Date inicio, Date fin) {

    }

    @Override
    public int opcion4(String artista, Date dia) {
        return 0;
    }

    @Override
    public int opcion5(Date inicio, Date fin) {
        return 0;
    }
}
