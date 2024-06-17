package CSV.Metodos;

import CSV.Entities.ManipularCSV;
import CSV.Entities.Song;
import CSV.Exceptions.InvalidCountry;
import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.Hash.Hash;
import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import TADs.LinkedList.LinkedList;
import TADs.Tree.BinaryTree;
import TADs.Tree.Exceptions.EmptyTree;
import TADs.Tree.Exceptions.InvalidKey;
import TADs.Tree.Node;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Metodos implements MisMetodos{
    public String[] auxList;
    private ManipularCSV datos;
    String[] codPaises = {"GLB", "ZA", "VN", "VE", "UY", "US", "UA", "TW", "TR", "TH", "SV", "SK", "SG", "SE", "SA", "RO", "PY", "PT", "PL", "PK", "PH", "PE", "PA", "NZ", "NO",
            "NL", "NI", "NG", "MY", "MX", "MA", "LV", "LU", "LT", "KZ", "KR", "JP", "IT", "IS", "IN", "IL", "IE", "ID", "HU", "HN", "HK", "GT", "GR", "GB", "FR", "FI", "ES", "EG", "EE",
            "EC", "DO", "DK", "DE", "CZ", "CR", "CO", "CL", "CH", "CA", "BY", "BR", "BO",
            "BG", "BE", "AU", "AT", "AR", "AE"};
    
    public Metodos() {
        datos = new ManipularCSV();
        datos.readFile("C:\\Users\\Alfonso\\OneDrive\\Escritorio\\Exceel obligatorio chingado.csv");
    }

    @Override
    public void opcion1(String pais, String dia) throws InvalidCountry, InvalidHashKey, EmptyList, InvalidIndex {
        System.out.println();
        System.out.println("ESTE ES EL TOP 10");
        String key = pais + dia;
        LinkedList<String> idSongs = datos.getHashTablaRelaciones().search(key);
        for (int i = 0; i < 10; i++) {
            Song s = datos.getHashCanciones().search(idSongs.get(i));
            System.out.println((i + 1) + " - " + s.getName() + ", " + s.getArtists());
        }
    }
    @Override
    public void opcion2(String dia) throws InvalidHashKey, EmptyList, InvalidIndex, InvalidKey, EmptyTree {
        LinkedList<String> listaTodasLasCanciones = new LinkedList<>();
        Hash<String, Integer> contadorCanciones = new Hash<>(5);
        BinaryTree<Integer, String> top5Canciones = new BinaryTree<>();

        for (int i = 0; i < codPaises.length; i++) {
            String key = codPaises[i] + dia;
            if (i == 0) {
                listaTodasLasCanciones = datos.getHashTablaRelaciones().search(key);
            } else {
                listaTodasLasCanciones.appendLists(datos.getHashTablaRelaciones().search(key));
            }
        }
        for (int i = 0; i < listaTodasLasCanciones.size(); i++) {
            String songID = listaTodasLasCanciones.get(i);
            if (!contadorCanciones.contains(songID)) {
                contadorCanciones.add(songID, 1);
            } else {
                int contadorActual = contadorCanciones.search(songID);
                contadorCanciones.remove(songID);
                contadorCanciones.add(songID, contadorActual + 1);
            }
        }
        for (int i = 0; i < contadorCanciones.getSize(); i++) {
            if (contadorCanciones.getArray()[i] != null) {
                top5Canciones.add(contadorCanciones.getArray()[i].getValue(), contadorCanciones.getArray()[i].getKey());
            }
        }
        System.out.println();
        System.out.println("ESTAS SON LAS 5 CANCIONES CON MAS APARICIONES EN TOPS 50:");
        for (int i = 0; i < 5; i++) {
            String songID = top5Canciones.getMax().getData();
            Song s = datos.getHashCanciones().search(songID);
            top5Canciones.delete(top5Canciones.getMax().getKey());
            System.out.println((i + 1) + " - " + s.getName() + ", " + s.getArtists() + " (" + top5Canciones.getMax().getKey() + " apariciones)");
        }
    }
    @Override
    public void opcion3(String inicio, String fin) throws InvalidHashKey, EmptyList, InvalidIndex, InvalidKey, EmptyTree {
        Date fechaIni = convertStringToDate(inicio);
        Date fechaFin = convertStringToDate(fin);
        LinkedList<String> listaTodasLasCanciones = new LinkedList<>();
        Hash<String, Integer> contadorArtistas = new Hash<>(5);
        BinaryTree<Integer, String> top7Artistas = new BinaryTree<>();

        if (!fechaIni.equals(fechaFin)) {
            while (!fechaIni.equals(fechaFin)) {
                String fecha = convertirFecha(fechaIni);
                for (int i = 0; i < codPaises.length; i++) {
                    String key = codPaises[i] + fecha;
                    if (listaTodasLasCanciones.isEmpty()) {
                        listaTodasLasCanciones = datos.getHashTablaRelaciones().search(key);
                    } else {
                        listaTodasLasCanciones.appendLists(datos.getHashTablaRelaciones().search(key));
                    }
                }
                for (int i = 0; i < listaTodasLasCanciones.size(); i++) {
                    Song s = datos.getHashCanciones().search(listaTodasLasCanciones.get(i));
                    String artist = s.getArtists();
                    if (!contadorArtistas.contains(artist)) {
                        contadorArtistas.add(s.getArtists(), 1);
                    } else {
                        int contadorActual = contadorArtistas.search(artist);
                        contadorArtistas.remove(artist);
                        contadorArtistas.add(artist, contadorActual + 1);
                    }
                }
                fechaIni = aumentarDia(fechaIni);
            }
        } else {
            for (int i = 0; i < codPaises.length; i++) {
                String key = codPaises[i] + fechaFin;
                if (listaTodasLasCanciones.isEmpty()) {
                    listaTodasLasCanciones = datos.getHashTablaRelaciones().search(key);
                } else {
                    listaTodasLasCanciones.appendLists(datos.getHashTablaRelaciones().search(key));
                }
            }
            for (int i = 0; i < listaTodasLasCanciones.size(); i++) {
                Song s = datos.getHashCanciones().search(listaTodasLasCanciones.get(i));
                String artist = s.getArtists();
                if (!contadorArtistas.contains(artist)) {
                    contadorArtistas.add(s.getArtists(), 1);
                } else {
                    int contadorActual = contadorArtistas.search(artist);
                    contadorArtistas.remove(artist);
                    contadorArtistas.add(artist, contadorActual + 1);
                }
            }
        }
        for (int i = 0; i < contadorArtistas.getSize(); i++) {
            if (contadorArtistas.getArray()[i] != null) {
                top7Artistas.add(contadorArtistas.getArray()[i].getValue(), contadorArtistas.getArray()[i].getKey());
            }
        }

        System.out.println();
        System.out.println("ESTE ES EL TOP 7 ARTISTAS CON MAS REPRODUCCIONES:");
        for (int i = 0; i < 7; i++) {
            String artist = top7Artistas.getMax().getData();
            top7Artistas.delete(top7Artistas.getMax().getKey());
            System.out.println((i + 1) + " - " + artist + " (" + top7Artistas.getMax().getKey() + " apariciones)");
        }
    }

    @Override
    public int opcion4(String artista, Date dia) {
        return 0;
    }

    @Override
    public int opcion5(Date inicio, Date fin) {
        return 0;
    }

    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Formato de fecha inválido: " + e.getMessage());
        }
        return date;
    }

    public static String convertirFecha(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(fecha);
    }

    public static Date aumentarDia(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DAY_OF_MONTH, 1);
        return calendario.getTime();
    }
}
