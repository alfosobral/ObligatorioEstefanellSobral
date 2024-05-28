package CSV;

import CSV.Exceptions.InvalidCountry;
import TADs.Tree.BinaryTree;

import javax.management.MBeanNotificationInfo;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManipularCSV {
    private List<String> lines = new ArrayList<>();

    public void readFile(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }
        }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
        }
    }
    public List<String> getLines(){
        return lines;
    }

    public void printLine(){
        for (int i=0; i<lines.size();i++){
            System.out.println(lines.get(i) + "  |  ");
            System.out.println();
        }
    }

}
