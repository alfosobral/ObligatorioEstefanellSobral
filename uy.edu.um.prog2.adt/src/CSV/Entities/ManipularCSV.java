package CSV.Entities;

import TADs.Hash.Hash;
import TADs.LinkedList.LinkedList;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ManipularCSV {
    private BufferedReader reader;
    private String texto;
    private String cancion;
    private String[] atributos;
    private Hash<String, LinkedList<String>> hashTablaRelaciones = new Hash<>(5);
    private Hash<String, Song> hashCanciones = new Hash<>(5);

    public void readFile(String fileName){
        try {
            String keyActual = "****";
            String paisActual = "GLB";
            String fechaActual = "2024-05-13";
            int counter = 0;
            reader = new BufferedReader(new FileReader(fileName));
            while ((cancion = reader.readLine()) != null){
                atributos = cancion.split("\",\"|\",|,\"");
                for (int i = 0; i < atributos.length; i++) {
                    atributos[i] = atributos[i].replaceAll("\"", "");
                }
                if (counter > 0 && atributos.length > 2 && !atributos[0].equals("7hDoxkN20lLb06zifzYnD2") && !atributos[0].equals("11IqNbLOD4s4nVYSuEttFR")) {
                    if (atributos[6].isEmpty()) {
                        atributos[6] = paisActual;
                    }
                    if (atributos[7].isEmpty()) {
                        atributos[7] = fechaActual;
                    }
                    //int dailyRank = Integer.parseInt(atributos[3]);
                    Double tempo = Double.parseDouble(atributos[23]);

                    Song s = new Song(atributos[0], atributos[1], atributos[2], tempo);
                    if (!hashCanciones.contains(atributos[0])) {
                        hashCanciones.add(atributos[0], s);
                    }
                    String key = atributos[6] + atributos[7];
                    if (!key.equals(keyActual)) {
                        this.createNewList(key);
                        this.hashTablaRelaciones.search(key).addLast(atributos[0]);
                        keyActual = key;
                    } else {
                        this.hashTablaRelaciones.search(key).addLast(atributos[0]);
                    }
                } else if (atributos[0].equals("11IqNbLOD4s4nVYSuEttFR")) {
                    if (!hashCanciones.contains(atributos[0])) {
                        //int dailyRank = Integer.parseInt(atributos[4]);
                        double tempo = Double.parseDouble(atributos[24]);
                        Song s = new Song(atributos[0], atributos[1], atributos[2], tempo);
                        hashCanciones.add(atributos[0], s);
                    }
                } else if (atributos[0].equals("7hDoxkN20lLb06zifzYnD2")) {
                    atributos[1] = "Ishq - From ; Lost Found";
                    if (!hashCanciones.contains(atributos[0])) {
                        //int daily_rank = Integer.parseInt(atributos[3]);
                        double tempo = Double.parseDouble(atributos[23]);
                        Song s = new Song(atributos[0], atributos[1], atributos[2], tempo);
                        hashCanciones.add(atributos[0], s);
                    }
                    String key = atributos[6] + atributos[7];
                    if (key.equals(keyActual)) {
                        this.hashTablaRelaciones.search(key).addLast(atributos[0]);
                    } else {
                        this.createNewList(key);
                        hashTablaRelaciones.search(key).addLast(atributos[0]);
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
        LinkedList<String > top = new LinkedList<>();
        hashTablaRelaciones.add(key, top);
    }

    public Hash<String, LinkedList<String>> getHashTablaRelaciones() {
        return hashTablaRelaciones;
    }

    public Hash<String, Song> getHashCanciones() {
        return hashCanciones;
    }
}
