package CSV;

import CSV.Exceptions.InvalidCountry;
import TADs.Hash.Hash;
import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import TADs.LinkedList.LinkedList;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ManipularCSV {
    private BufferedReader reader;
    private String texto;
    private String cancion;
    public String[] atributes;
    private Hash<String, Song> hashCanciones = new Hash<>(5);
    private Hash<String, LinkedList<String>> tablaExcel = new Hash<>(5);
    private LinkedList<String> paises = new LinkedList<>();
    private LinkedList<Song> cancionesDistintas = new LinkedList<>();
    public BufferedReader getReader() {
        return reader;
    }

    public void readFile(String fileName){
        try {
            String keyActual = "****";
            String paisActual = "GLB";
            String fechaActual = "13/5/2024";
            int counter = 0;
            reader = new BufferedReader(new FileReader(fileName));
            while ((cancion = reader.readLine()) != null){
                cancion = cancion.replaceAll("\"", "");
                atributes = cancion.split(";");
                if (counter > 1 && atributes.length > 2) {
                    if (atributes[6].isEmpty()) {
                        atributes[6] = paisActual;
                    }
                    if (atributes[7].isEmpty()) {
                        atributes[7] = fechaActual;
                    }
                    if (!paises.contains(atributes[6])) {
                        paises.addLast(atributes[6]);
                    }
                    String key = atributes[6] + atributes[7];
                    Song s = new Song(atributes[0], atributes[7], atributes[1], atributes[2], atributes[3], atributes[6], atributes[23]);

                    if (key.equals(keyActual)) {
                        this.hashCanciones.serch(key).addLast(s);
                    } else {
                        this.createNewList(key);
                        this.hashCanciones.serch(key).addLast(s);
                        keyActual = key;
                    }

                }
                counter++;
            }
            System.out.println(hashCanciones.getCounter());
            reader.close();
            texto = null;
            cancion = null;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }

    public void createNewList(String key) {
        LinkedList<Song> top = new LinkedList<>();
        hashCanciones.add(key, top);
    }
    public void opcion1(String pais, String dia) throws InvalidCountry, InvalidHashKey, EmptyList, InvalidIndex {
        String key = pais + dia;
        LinkedList<Song> top50 = hashCanciones.serch(key);
        for(int i = 0; i < 10; i++) {
            Song s = top50.get(i);
            System.out.println(s.getDaily_rank() + " - " + s.getName() + ", " + s.getArtists());
        }

    }

    public void opcion2(String dia) throws EmptyList, InvalidIndex, InvalidHashKey {
        String key = "GLB" + dia;
        LinkedList<Song> top5Songs = new LinkedList<>();
        LinkedList<String> top5id = new LinkedList<>();
        int[] top5counter = new int[5];
        Arrays.fill(top5counter, 0);
        LinkedList<Song> listaGrande = new LinkedList<>();
        LinkedList<Song> top50 = this.hashCanciones.serch(key);
        for (int i = 0; i < 5; i++) {
            Song s = top50.get(i);
            top5Songs.addLast(s);
            top5id.addLast(s.getSpotify_id());
        }
        for (int i = 0; i < this.paises.size(); i++) {
            key = paises.get(i) + dia;
            top50 = this.hashCanciones.serch(key);
            listaGrande.mergeLists(top50);
        }
        for (int i = 0; i < listaGrande.size(); i++) {
            if (listaGrande.get(i).getSpotify_id().equals(top5id.get(0))) {
                top5counter[0]++;
            }
        }
        for (int i = 0; i < listaGrande.size(); i++) {
            if (listaGrande.get(i).getSpotify_id().equals(top5id.get(1))) {
                top5counter[1]++;
            }
        }
        for (int i = 0; i < listaGrande.size(); i++) {
            if (listaGrande.get(i).getSpotify_id().equals(top5id.get(2))) {
                top5counter[2]++;
            }
        }
        for (int i = 0; i < listaGrande.size(); i++) {
            if (listaGrande.get(i).getSpotify_id().equals(top5id.get(3))) {
                top5counter[3]++;
            }
        }
        for (int i = 0; i < listaGrande.size(); i++) {
            if (listaGrande.get(i).getSpotify_id().equals(top5id.get(4))) {
                top5counter[4]++;
            }

        }
        for (int i = 0; i < 5; i++) {
            Song s = top5Songs.get(i);
            System.out.println((i+1) + " - " + s.getName() + ", " + s.getArtists() + " " + "[aparecio en " + top5counter[i] + " top 50]");
        }

    }
}
