package CSV;

import CSV.Exceptions.InvalidCountry;
import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import TADs.Tree.Exceptions.EmptyTree;
import TADs.Tree.Exceptions.InvalidKey;

import java.util.Date;

public interface MisMetodos {
    public void opcion1 (String pais, String  dia) throws InvalidCountry, InvalidHashKey, EmptyList, InvalidIndex;
    public void opcion2 (String dia) throws InvalidHashKey, EmptyList, InvalidIndex, InvalidKey, EmptyTree;
    public void opcion3 (String inicio, String fin) throws InvalidHashKey, EmptyList, InvalidIndex, InvalidKey, EmptyTree;
    public int opcion4 (String artista, Date dia);
    public int opcion5 (Date inicio, Date fin);
    }

