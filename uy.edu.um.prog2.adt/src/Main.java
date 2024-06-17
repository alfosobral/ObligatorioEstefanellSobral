import CSV.Entities.Checker;
import CSV.Exceptions.InvalidCountry;
import CSV.Metodos.Metodos;
import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import TADs.Queue.Exceptions.EmptyQueue;
import TADs.Tree.Exceptions.EmptyTree;
import TADs.Tree.Exceptions.InvalidKey;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, EmptyList, InvalidIndex, InvalidCountry, InvalidHashKey, EmptyTree, InvalidKey, EmptyQueue {
        boolean programaFuncionando = true;
        System.out.println("Cargando base de datos");
//        for (int i = 0; i < 3; i++) {
//            System.out.println(".");
//            Thread.sleep(1000);
//        }
        Metodos metodos = new Metodos();
        Scanner input = new Scanner(System.in);
        Checker checker = new Checker();
        System.out.println("Bienvenido al consultor de rankings de Spotify!");
        System.out.println("A continuacion se listan las opciones para realizar consultas:");
        System.out.println();
        while (programaFuncionando) {
            boolean opcionValida = false;
                while (!opcionValida) {
                System.out.println();
                System.out.println("1 - Top 10 canciones en un país en un día dado");
                System.out.println("2 - Top 5 canciones que aparecen en más top 50 en un día dado");
                System.out.println("3 - Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado");
                System.out.println("4 - Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada");
                System.out.println("5 - Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas");
                System.out.println("6 - Cerrar programa");
                System.out.println("Por favor, ingrese que opcion desea consultar: ");
                int opcion = input.nextInt();
                switch (opcion) {
                    case 1:
                        opcionValida = true;
                        boolean opcion1 = true;
                        while (opcion1) {
                            boolean paisValido = false;
                            String pais = null;
                            String fecha = null;
                            while (!paisValido) {
                                System.out.println();
                                System.out.println("Ingrese un pais a continuacion: ");
                                pais = input.next();
                                pais = checker.checkCountry(pais);
                                if (pais != null) {
                                    paisValido = true;
                                } else {
                                    System.out.println("ERROR! Ingrese un pais valido");
                                }
                            }
                            boolean fechaValida = false;
                            while (!fechaValida) {
                                System.out.println();
                                System.out.println("Ingrese una fecha a continuacion (YYYY-MM-DD): ");
                                fecha = input.next();
                                if (checker.checkDate(fecha)) {
                                    fechaValida = true;
                                }
                            }
                            metodos.opcion1(pais, fecha);
                            opcion1 = false;
                            }
                        break;
                    case 2:
                        opcionValida = true;
                        boolean opcion2 = true;
                        while (opcion2) {
                            String fecha = null;
                            boolean fechaValida = false;
                            while (!fechaValida) {
                                System.out.println();
                                System.out.println("Ingrese una fecha a continuacion (YYYY-MM-DD): ");
                                fecha = input.next();
                                if (checker.checkDate(fecha)) {
                                    fechaValida = true;
                                }
                            }
                            metodos.opcion2(fecha);
                            opcion2 = false;
                        }
                        break;
                    case 3:
                        opcionValida = true;
                        boolean opcion3 = true;
                        while (opcion3) {
                            String fechaInicio = null;
                            String fechaFin = null;
                            boolean fechaInicioValida = false;
                            boolean fechaFinValida = false;
                            while (!fechaInicioValida) {
                                System.out.println();
                                System.out.println("Ingrese una fecha de inicio a continuacion (YYYY-MM-DD): ");
                                fechaInicio = input.next();
                                if (checker.checkDate(fechaInicio)) {
                                    fechaInicioValida = true;
                                }
                            }
                            while (!fechaFinValida) {
                                System.out.println();
                                System.out.println("Ingrese una fecha de finalizacion a continuacion (YYYY-MM-DD): ");
                                fechaFin = input.next();
                                if (checker.checkDate(fechaFin)) {
                                    fechaFinValida = true;
                                }
                            }
                            metodos.opcion3(fechaInicio, fechaFin);
                            opcion3 = false;
                        }
                        break;
                    case 4:
                        opcionValida = true;
                        boolean opcion4 = true;
                        while (opcion4) {}
                        break;
                    case 5:
                        opcionValida = true;
                        boolean opcion5 = true;
                        while (opcion5) {}
                        break;
                    case 6:
                        opcionValida = true;
                        programaFuncionando = false;
                        break;
                    default:
                        System.out.println("ERROR! Ingrese un numero del 1 - 6");
                        break;
                }
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