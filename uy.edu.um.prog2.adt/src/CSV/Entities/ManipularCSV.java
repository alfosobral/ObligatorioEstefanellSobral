package CSV.Entities;

import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.Hash.Hash;
import TADs.LinkedList.LinkedList;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static CSV.Metodos.Metodos.getUsedMemory;

public class ManipularCSV {
    private String texto;
    private String[] atributos;
    private Hash<String, LinkedList<String>> hashTablaRelaciones = new Hash<>(5);
    private Hash<String, Song> hashCanciones = new Hash<>(5);

    public void readFile(String fileName) {
        long memoryBefore = getUsedMemory();
        String keyActual = "****";
        String paisActual = "GLB";
        String fechaActual = "2024-05-13";
        int counter = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                atributos = linea.split("\",\"|\",|,\"");
                for (int i = 0; i < atributos.length; i++) {
                    atributos[i] = atributos[i].trim().replaceAll("\"", "");
                }

                if (counter > 0 && atributos.length > 2) {
                    processLine(atributos, keyActual, paisActual, fechaActual);
                    keyActual = atributos[6] + atributos[7];
                }
                counter++;
            }
        } catch (IOException | InvalidHashKey e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        long memoryAfter = getUsedMemory();
        long memoryUsed = memoryAfter - memoryBefore;
        System.out.println("Memoria utilizada: " + memoryUsed + " bytes");
    }

    private void processLine(String[] atributos, String keyActual, String paisActual, String fechaActual) throws InvalidHashKey {
        if (atributos[0].equals("7hDoxkN20lLb06zifzYnD2")) {
            atributos[1] = "Ishq - From ; Lost Found";
        }

        if (atributos[6].isEmpty()) {
            atributos[6] = paisActual;
        }
        if (atributos[7].isEmpty()) {
            atributos[7] = fechaActual;
        }

        double tempo = parseTempo(atributos);
        Song s = new Song(atributos[0], atributos[1], atributos[2], tempo);

        if (!hashCanciones.contains(atributos[0])) {
            hashCanciones.add(atributos[0], s);
        }

        String key = atributos[6] + atributos[7];
        if (!key.equals(keyActual)) {
            createNewList(key);
            keyActual = key;
        }
        hashTablaRelaciones.search(key).addLast(atributos[0]);

    }

    private double parseTempo(String[] atributos) {
        try {
            if (atributos[0].equals("11IqNbLOD4s4nVYSuEttFR")) {
                return Double.parseDouble(atributos[24]);
            } else {
                return Double.parseDouble(atributos[23]);
            }
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void createNewList(String key) {
        LinkedList<String> top = new LinkedList<>();
        hashTablaRelaciones.add(key, top);
    }

    public Hash<String, LinkedList<String>> getHashTablaRelaciones() {
        return hashTablaRelaciones;
    }

    public Hash<String, Song> getHashCanciones() {
        return hashCanciones;
    }
}
