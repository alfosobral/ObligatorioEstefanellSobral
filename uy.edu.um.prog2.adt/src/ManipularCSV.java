import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ManipularCSV {
    private BufferedReader reader;
    private String line;
    private String parts[];

    public void readFile(String fileName){
        try {
            reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null){
            parts = line.split(",");
            //printLine();
            System.out.println();
            }
            reader.close();
            line = null;
            parts = null;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    public void printLine(){
        for (int i=0; i<parts.length;i++){
            System.out.println(parts[i] + "  |  ");
        }
    }

}
