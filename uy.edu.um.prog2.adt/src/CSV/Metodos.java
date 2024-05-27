package CSV;

import CSV.Exceptions.InvalidCountry;

import java.util.Date;

public class Metodos implements MisMetodos{
    @Override
    public void opcion1(String pais, Date dia) throws InvalidCountry {

    }

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
