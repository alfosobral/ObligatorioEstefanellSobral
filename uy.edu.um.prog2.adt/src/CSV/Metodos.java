package CSV;

import CSV.Exceptions.InvalidCountry;


import java.util.*;


public class Metodos implements MisMetodos{
    public static void opcion1(String pais, String dia, ManipularCSV file) throws InvalidCountry {
        List<String[]> filteredList = new ArrayList<>();
        for (String line : file.getLines()) {
            String[] parts = line.split(";");
            System.out.println(parts.length);
            if (line != null && parts.length == 25 && parts[6].equals(pais) && parts[8].equals(dia)) {
//                if (parts[6] != null){
//                    filteredList.add(parts);
//                } else {
//                    parts[6] = "Mundial";
//                    filteredList.add(parts);
//                }
                System.out.println("LLego");
                filteredList.add(parts);
                for (int i=0; i<parts.length;i++){
                    System.out.println(parts[i]);
                }
            }
        }
        System.out.println("Top 10 canciones en " + pais + " el día " + dia + ":");
        for (int t=0; t<10; t++){
            System.out.println(filteredList.get(t)[3] + ", " + filteredList.get(t)[1] + " - " + filteredList.get(t)[2]);
        }
//        filteredList.sort(Comparator.comparingInt(parts -> Integer.parseInt(parts[3])));
//        System.out.println("Top 10 canciones en " + pais + " el día " + dia + ":");
//        for (int j = 0; j<Math.min(10, filteredList.size()) ; j++) {
//            String[] parts = filteredList.get(j);
//            String rank = parts[3];
//            String songName = parts[1];
//            String artist = parts[2];
//            System.out.println(rank + ", " + songName + " - " + artist);
//        }

    }

    @Override
    public void opcion2(Date dia) {

    }

    @Override
    public void opcion3(Date inicio, Date fin) {

    }

    @Override
    public int opcion4(String artista, Date dia) {
        return 0;
    }

    @Override
    public int opcion5(Date inicio, Date fin) {
        return 0;
    }
}
