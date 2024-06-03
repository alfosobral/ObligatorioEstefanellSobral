package CSV;

import CSV.Exceptions.InvalidCountry;

import java.util.Date;

public interface MisMetodos {
    public void opcion1 (String pais, String  dia, ManipularCSV file) throws InvalidCountry;
    public void opcion2 (Date dia);
    public void opcion3 (Date inicio, Date fin);
    public int opcion4 (String artista, Date dia);
    public int opcion5 (Date inicio, Date fin);
    }

