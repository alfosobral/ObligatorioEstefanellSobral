package CSV.Metodos;

import CSV.Entities.Checker;
import CSV.Entities.ManipularCSV;
import CSV.Entities.Song;
import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.Hash.Hash;
import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import TADs.LinkedList.LinkedList;
import TADs.Tree.BinaryTree;
import TADs.Tree.Exceptions.EmptyTree;
import TADs.Tree.Exceptions.InvalidKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;


public class Metodos implements MisMetodos{
    private Scanner input = new Scanner(System.in);;
    private Checker checker = new Checker();
    private ManipularCSV datos;


    String[] codPaises = {"GLB", "ZA", "VN", "VE", "UY", "US", "UA", "TW", "TR", "TH", "SV", "SK", "SG", "SE", "SA", "RO", "PY", "PT", "PL", "PK", "PH", "PE", "PA", "NZ", "NO",
            "NL", "NI", "NG", "MY", "MX", "MA", "LV", "LU", "LT", "KZ", "KR", "JP", "IT", "IS", "IN", "IL", "IE", "ID", "HU", "HN", "HK", "GT", "GR", "GB", "FR", "FI", "ES", "EG", "EE",
            "EC", "DO", "DK", "DE", "CZ", "CR", "CO", "CL", "CH", "CA", "BY", "BR", "BO",
            "BG", "BE", "AU", "AT", "AR", "AE"};
    
    public Metodos(String direccion) {
        datos = new ManipularCSV();
        datos.readFile(direccion);
    }

    @Override
    public void opcion1(String pais, String dia) throws InvalidHashKey, EmptyList, InvalidIndex {
        long memoryBefore = getUsedMemory();
        System.out.println();
        System.out.println("ESTE ES EL TOP 10");
        String key = pais + dia;
        LinkedList<String> idSongs = datos.getHashTablaRelaciones().search(key);
        for (int i = 0; i < 10; i++) {
            Song s = datos.getHashCanciones().search(idSongs.get(i));
            System.out.println((i + 1) + " - " + s.getName() + ", " + s.getArtists());
        }
        long memoryAfter = getUsedMemory();
        long memoryUsed = memoryAfter - memoryBefore;
        System.out.println("Memoria utilizada: " + memoryUsed + " bytes");

    }
    @Override
    public void opcion2(String dia) throws InvalidHashKey, EmptyList, InvalidIndex, InvalidKey, EmptyTree {
        long memoryBefore = getUsedMemory();
        LinkedList<String> listaTodasLasCanciones = new LinkedList<>();
        Hash<String, Integer> contadorCanciones = new Hash<>(5);
        BinaryTree<Integer, String> top5Canciones = new BinaryTree<>();

        for (String codigo : codPaises) {
            String key = codigo + dia;
            if (datos.getHashTablaRelaciones().contains(key)) {
                if (listaTodasLasCanciones.isEmpty()) {
                    listaTodasLasCanciones = datos.getHashTablaRelaciones().search(key);
                } else {
                    listaTodasLasCanciones.appendLists(datos.getHashTablaRelaciones().search(key));
                }
            }
        }
        for (int i = 0; i < listaTodasLasCanciones.size(); i++) {
            String songID = listaTodasLasCanciones.get(i);
            if (!contadorCanciones.contains(songID)) {
                contadorCanciones.add(songID, 1);
            } else {
                int contadorActual = contadorCanciones.search(songID);
                contadorCanciones.relace(songID, contadorActual + 1);
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
            System.out.println((i + 1) + " - " + s.getName() + ", " + s.getArtists() + " (" + top5Canciones.getMax().getKey() + " apariciones)");
            top5Canciones.delete(top5Canciones.getMax().getKey());
        }
        long memoryAfter = getUsedMemory();
        long memoryUsed = memoryAfter - memoryBefore;
        //System.out.println("Memoria utilizada: " + memoryUsed + " bytes");
    }
    @Override
    public void opcion3(Date fechaIni, Date fechaFin) throws InvalidHashKey, EmptyList, InvalidIndex, InvalidKey, EmptyTree {
        long memoryBefore = getUsedMemory();
        LinkedList<String> top50;
        Hash<String, Integer> contadorArtistas = new Hash<>(5);
        BinaryTree<Integer, String> top7Artistas = new BinaryTree<>();

            do {
                String fechaTemp = convertirFecha(fechaIni);
                for (String codigo : codPaises) {
                    String key = codigo + fechaTemp;
                    if (datos.getHashTablaRelaciones().contains(key)) {
                        top50 = datos.getHashTablaRelaciones().search(key);
                        for (int j = 0; j < top50.size(); j++) {
                            Song s = datos.getHashCanciones().search(top50.get(j));
                            String[] artists = s.getArtists().split(",");
                            for (String artista : artists) {
                                artista = artista.trim();
                                if (!contadorArtistas.contains(artista)) {
                                    contadorArtistas.add(artista, 1);
                                } else {
                                    int contadorActual = contadorArtistas.search(artista);
                                    contadorArtistas.relace(artista, contadorActual + 1);
                                }
                            }
                        }
                    }
                }
                fechaIni = aumentarDia(fechaIni);
            } while (fechaIni.before(fechaFin) || fechaIni.equals(fechaFin));


        for (int i = 0; i < contadorArtistas.getSize(); i++) {
            if (contadorArtistas.getArray()[i] != null) {
                top7Artistas.add(contadorArtistas.getArray()[i].getValue(), contadorArtistas.getArray()[i].getKey());
            }
        }

        System.out.println();
        System.out.println("ESTE ES EL TOP 7 ARTISTAS CON MAS APARICIONES:");
        for (int i = 0; i < 7; i++) {
            String artist = top7Artistas.getMax().getData();
            System.out.println((i + 1) + " - " + artist + " (" + top7Artistas.getMax().getKey() + " apariciones)");
            top7Artistas.delete(top7Artistas.getMax().getKey());
        }

        long memoryAfter = getUsedMemory();
        long memoryUsed = memoryAfter - memoryBefore;
        //System.out.println("Memoria utilizada: " + memoryUsed + " bytes");
    }
    @Override
    public void opcion4(String artistaEspecifico, String dia) throws InvalidHashKey, EmptyList, InvalidIndex {
        long memoryBefore = getUsedMemory();
        String artistaEspecificoLowerCase = artistaEspecifico.toLowerCase();
        LinkedList<String> top50 = new LinkedList<>();
        int counter = 0;

        for (String codigo : codPaises) {
            String key = codigo + dia;
            if (datos.getHashTablaRelaciones().contains(key)) {
                top50 = datos.getHashTablaRelaciones().search(key);
                for (int i = 0; i < top50.size(); i++) {
                    String artistaCancion = datos.getHashCanciones().search(top50.get(i)).getArtists();
                    String[] artistas = artistaCancion.split(",");
                    for (String artista : artistas) {
                        artista = artista.trim().toLowerCase();
                        if (artista.equals(artistaEspecificoLowerCase)) {
                            counter++;
                        }
                    }
                }
            }
        }
        if (counter > 0) {
            System.out.println();
            System.out.println("El artista " + artistaEspecifico + " tiene un total de " + counter + " aparicion(es) en los tops 50 del " + dia);
            boolean respuestaValida = false;
            String respuesta = null;
            while (!respuestaValida) {
                System.out.println();
                System.out.println("Desea hacer la busqueda por pais? s/n");
                respuesta = input.nextLine();
                if (respuesta.equals("s")) {
                    respuestaValida = true;
                    boolean paisValido = false;
                    String pais = null;
                    while (!paisValido) {
                        System.out.println();
                        System.out.println("Ingrese un pais a continuacion (codigo o nombre):  ");
                        pais = input.nextLine();
                        if (checker.checkCountry(pais) != null) {
                            paisValido = true;
                            String codPais = checker.checkCountry(pais);
                            int nuevoCounter = 0;
                            String key = codPais + dia;
                            if (datos.getHashTablaRelaciones().contains(key)) {
                                top50 = datos.getHashTablaRelaciones().search(key);
                                for (int i = 0; i < top50.size(); i++) {
                                    String artistaCancion = datos.getHashCanciones().search(top50.get(i)).getArtists();
                                    String[] artistas = artistaCancion.split(",");
                                    for (String artista : artistas) {
                                        artista = artista.trim().toLowerCase();
                                        if (artista.equals(artistaEspecificoLowerCase)) {
                                            nuevoCounter++;
                                        }
                                    }
                                }
                            }
                            if (nuevoCounter > 0) {
                                System.out.println();
                                System.out.println("El artista " + artistaEspecifico + " tiene un total de " + nuevoCounter + " aparicion(es) en los tops 50 del " + dia + " en " + pais);
                            } else {
                                System.out.println();
                                System.out.println("Lo sentimos, el artista no tiene apariciones para esa fecha en ese pais.");
                            }
                        } else {
                            System.out.println();
                            System.out.println("Error! Ingrese un pais valido.");
                        }
                    }

                } else if (respuesta.equals("n")) {
                    respuestaValida = true;
                } else {
                    System.out.println("Error! Ingrese un parametro valido (s/n)");
                    System.out.println();
                }
            }
        } else {
            System.out.println();
            System.out.println("Lo sentimos, el artista no tiene apariciones para esa fecha.");
        }

        long memoryAfter = getUsedMemory();
        long memoryUsed = memoryAfter - memoryBefore;
        //System.out.println("Memoria utilizada: " + memoryUsed + " bytes");
    }
    @Override
    public void opcion5(Date fechaIni, Date fechaFin, double tempoIni, double tempoFin) throws InvalidHashKey, EmptyList, InvalidIndex {
        long memoryBefore = getUsedMemory();
        LinkedList<String> top50;
        Date fechaIniO = fechaIni;
        LinkedList<String> canciones = new LinkedList<>();

        do {
            String fechaTemp = convertirFecha(fechaIni);
            for (String codigo : codPaises) {
                String key = codigo + fechaTemp;
                if (datos.getHashTablaRelaciones().contains(key)) {
                    top50 = datos.getHashTablaRelaciones().search(key);
                    for (int j = 0; j < top50.size(); j++) {
                        Song s = datos.getHashCanciones().search(top50.get(j));
                        if (s.getTempo() >= tempoIni && s.getTempo() <= tempoFin && !canciones.contains(s.getSpotify_id())) {
                            canciones.addLast(s.getSpotify_id());
                        }
                    }
                }
            }
            fechaIni = aumentarDia(fechaIni);
        } while (fechaIni.before(fechaFin) || fechaIni.equals(fechaFin));

        if (canciones.size() > 0) {
            System.out.println();
            System.out.println("Hay un total de " + canciones.size() + " canciones con tempo entre " + tempoIni + " y " + tempoFin + ", dentro de los top 50 entre el " + convertirFecha(fechaIniO) + " y el " + convertirFecha(fechaFin));
        } else {
            System.out.println();
            System.out.println("Lo sentimos, no hay canciones que cumplen esas caracteristicas.");
        }

        long memoryAfter = getUsedMemory();
        long memoryUsed = memoryAfter - memoryBefore;
        //System.out.println("Memoria utilizada: " + memoryUsed + " bytes");
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

    private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

    public static long getUsedMemory() {
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
        return heapMemoryUsage.getUsed();
    }
}
