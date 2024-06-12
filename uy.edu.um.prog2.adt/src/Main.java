import CSV.Exceptions.InvalidCountry;
import CSV.ManipularCSV;
import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.Hash.Hash;
import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws InterruptedException, EmptyList, InvalidIndex, InvalidCountry, InvalidHashKey {
        boolean programaFuncionando = true;
        System.out.println("Cargando base de datos");
//        for (int i = 0; i < 3; i++) {
//            System.out.println(".");
//            Thread.sleep(1000);
//        }
        ManipularCSV mcvs = new ManipularCSV();
        mcvs.readFile("C:\\Users\\Usuario\\Desktop\\Exceel obligatorio chingado.csv");
        //"C:\\Users\\Alfonso\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv"
        //"C:\\Users\\Usuario\\Desktop\\Exceel obligatorio chingado.csv"
        Scanner input = new Scanner(System.in);
        System.out.println("Bienvenido al consultor de rankings de Spotify!");
        System.out.println("A continuacion se listan las opciones para realizar consultas:");
        System.out.println();
        System.out.println("1 - Top 10 canciones en un país en un día dado");
        System.out.println("2 - Top 5 canciones que aparecen en más top 50 en un día dado");
        System.out.println("3 - Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado");
        System.out.println("4 - Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada");
        System.out.println("5 - Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas");
        System.out.println("6 - Cerrar programa");
        while (programaFuncionando) {
            System.out.println();
            System.out.println("Por favor, ingrese que opcion desea consultar: ");
            int opcion = (int)(input.nextInt());
            switch (opcion) {
                case 1:
                    boolean opcion1 = true;
                    while (opcion1) {
                        boolean paisValido = false;
                        String pais = null;
                        String fecha = null;
                        while (!paisValido) {
                            System.out.println();
                            System.out.println("Ingrese un pais a continuacion: ");
                            pais = input.next();
                            //if (hashPaises.contains(pais))
                            paisValido = true;
                        }
                        boolean fechaValida = false;
                        while (!fechaValida) {
                            System.out.println();
                            System.out.println("Ingrese una fecha a continuacion (DD/MM/YYYY): ");
                            fecha = input.next();
                            //if (fecha valida)
                            fechaValida= true;
                        }
                        mcvs.opcion1(pais, fecha);
                        }
                    break;
                case 2:
                    boolean opcion2 = true;
                    while (opcion2) {
                        String fecha = null;
                        boolean fechaValida = false;
                        while (!fechaValida) {
                            System.out.println();
                            System.out.println("Ingrese una fecha a continuacion (DD/MM/YYYY): ");
                            fecha = input.next();
                            //if (fecha valida)
                            fechaValida= true;
                        }
                    }
                    break;
                case 3:
                    boolean opcion3 = true;
                    while (opcion3) {}
                    break;
                case 4:
                    boolean opcion4 = true;
                    while (opcion4) {}
                    break;
                case 5:
                    boolean opcion5 = true;
                    while (opcion5) {}
                    break;
                case 6:
                    programaFuncionando = false;
                    break;
                default:
                    System.out.println("ERROR! Ingrese un numero del 1 - 6");
                    break;
            }

        }
        System.out.println("Apagando sistema.");
        for (int i = 0; i < 3; i++) {
            System.out.println(".");
            Thread.sleep(1000);
        }
        System.out.println("Finalizado.");


            }
        }
