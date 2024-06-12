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

public class ManipularCSV {
    private BufferedReader reader;
    private String cancion;
    public String[] atributes;
    private Hash<String, Song> hashCanciones = new Hash<>(5);
    private Hash<String, LinkedList<String>> tablaExcel = new Hash<>(5);
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
                atributes = cancion.split(" , ");
                if (counter > 0 && atributes.length > 2) {
                    if (atributes[6].isEmpty()) {
                        atributes[6] = paisActual;
                    }
                    if (atributes[7].isEmpty()) {
                        atributes[7] = fechaActual;
                    }
                    if (!paisActual.equals(atributes[6])) {
                        paisActual = atributes[6];
                    }
                    if (!fechaActual.equals(atributes[7])) {
                        fechaActual = atributes[7];
                    }
                    if (!hashCanciones.contains(atributes[0])) {
                        Song s = new Song(atributes[0], atributes[1], atributes[2], atributes[3], atributes[23]);
                        this.hashCanciones.add(atributes[0], s);
                    }

                    String key = atributes[6] + atributes[7];

                    if (key.equals(keyActual)) {
                        this.tablaExcel.search(key).addLast(atributes[0]);
                    } else {
                        this.createNewList(key);
                        this.tablaExcel.search(key).addLast(atributes[0]);
                        keyActual = key;
                    }

                }
                counter++;
            }
            System.out.println(hashCanciones.getCounter());
            reader.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }

    public void createNewList(String key) {
        LinkedList<String> top = new LinkedList<>();
        tablaExcel.add(key, top);
    }

    public void opcion1(String pais, String dia) throws InvalidHashKey, EmptyList, InvalidIndex {
        String key = pais + dia;
        LinkedList<String> top50 = this.tablaExcel.search(key);
        for (int i = 0; i < 10; i++) {
            Song s = this.hashCanciones.search(top50.get(i));
            System.out.println((i + 1) + " - " + s.getName() + ", " + s.getArtists());
        }

    }
}
