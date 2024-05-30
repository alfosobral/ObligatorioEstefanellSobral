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
    private String texto;
    private String cancion;
    public String auxList[];
    public String[] atributes;
    private Hash<String, LinkedList<Song>> hashMetodo1 = new Hash<>(5);
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
                    String key = atributes[6] + atributes[7];
                    Song s = new Song(atributes[0], atributes[7], atributes[1], atributes[2], atributes[3], atributes[6], atributes[23]);
                    if (key.equals(keyActual)) {
                        this.hashMetodo1.serch(key).addLast(s);
                    } else {
                        this.createNewList(key);
                        this.hashMetodo1.serch(key).addLast(s);
                        keyActual = key;
                    }

                }
                counter++;
            }
            reader.close();
            texto = null;
            cancion = null;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }

    public void createNewList(String key) {
        LinkedList<Song> top = new LinkedList<>();
        hashMetodo1.add(key, top);
    }
    public void opcion1(String pais, String dia) throws InvalidCountry, InvalidHashKey, EmptyList, InvalidIndex {
        String key = pais + dia;
        LinkedList<Song> top50 = hashMetodo1.serch(key);
        for(int i = 0; i < 10; i++) {
            Song s = top50.get(i);
            System.out.println(s.getDaily_rank() + " - " + s.getName() + ", " + s.getArtists());
        }

    }


}
