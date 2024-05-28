package CSV;

import CSV.Exceptions.InvalidCountry;
import TADs.Hash.Hash;
import TADs.LinkedList.CircularList;
import TADs.LinkedList.LinkedList;
import TADs.Tree.BinaryTree;

import javax.management.MBeanNotificationInfo;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class ManipularCSV {
    private BufferedReader reader;
    private String line;
    private String parts[];
    public String auxList[];
    public String atributes[];
    private Hash<String, Song> hashMetodo1 = new Hash<>(5);
    public BufferedReader getReader() {
        return reader;
    }

    public void readFile(String fileName){
        try {
            String paisActual = null;
            String fecha = null;
            reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null){
            parts = line.split(" ; ");
            atributes = parts[0].split(",");
            if (atributes[6] == null) {
                atributes[6] = "Global";
            }
            Song s = new Song(atributes[0], atributes[5], atributes[1], atributes[2], 0, atributes[6], 0);
            hashMetodo1.add(atributes[0], s);
//            printLine();
//            System.out.println();
            }
            reader.close();
            line = null;
            parts = null;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    public void opcion1(String pais, String dia) throws InvalidCountry {
        for (String part : parts) {
            for (int j = 0; j < 24; j++) {
                auxList[j] = Arrays.toString(part.split(";"));
            }
            while (auxList[8].equals(dia)) {
                System.out.println(part);
            }
        }

    }

//    public List<String[]> pais(List<String[]>list, int max){
//        String[] pais;
//        for (int i = 0; i<max; i++){
//            List =
//        }
//    }
//
//    public void Lista_Fechas(){
//        String fecha1;
//        String fecha2;
//        for()
//    }


    public void printLine(){
        for (int i=0; i<parts.length;i++){
            System.out.println(parts[i] + "  |  ");
        }
    }

}
