import CSV.Entities.Checker;
import CSV.Metodos.Metodos;
import TADs.Hash.Exceptions.InvalidHashKey;
import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;
import TADs.Queue.Exceptions.EmptyQueue;
import TADs.Tree.Exceptions.EmptyTree;
import TADs.Tree.Exceptions.InvalidKey;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException, EmptyList, InvalidIndex, InvalidHashKey, EmptyTree, InvalidKey, EmptyQueue {
        boolean programaFuncionando = true;

        Scanner input = new Scanner(System.in);
        Checker checker = new Checker();
        String direccion = null;
        boolean direccionValida = false;
        while (!direccionValida) {
            System.out.println();
            System.out.println("Ingrese la ruta de acceso a su archivo csv");
            direccion = input.nextLine();
            if (direccion != null) {
                direccionValida = true;
                direccion = direccion.replaceAll("\"", "").trim();
            } else {
                System.out.println();
                System.out.println("Error! Ingrese una direccion correcta.");
            }
        }

        System.out.println();
        System.out.println("Cargando base de datos");
        for (int i = 0; i < 3; i++) {
            System.out.println(".");
            Thread.sleep(333);
        }

        Metodos metodos = new Metodos(direccion);

        System.out.println();
        System.out.println("Bienvenido al consultor de rankings de Spotify!");
        System.out.println("A continuacion se listan las opciones para realizar consultas:");
        int opcion = 0;
        while (programaFuncionando) {
            System.out.println();
            System.out.println("1 - Top 10 canciones en un país en un día dado");
            System.out.println("2 - Top 5 canciones que aparecen en más top 50 en un día dado");
            System.out.println("3 - Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado");
            System.out.println("4 - Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada");
            System.out.println("5 - Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas");
            System.out.println("6 - Cerrar programa");
            System.out.println();
            boolean opcionValida = false;
                while (!opcionValida && opcion != 6) {
                    System.out.println("Por favor, ingrese que opcion desea consultar: ");
                    opcion = input.nextInt();
                    input.nextLine();
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
                                    pais = input.nextLine();
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
                                boolean respuestaValida = false;
                                while (!respuestaValida) {
                                    System.out.println();
                                    System.out.println("Desea volver al menu? s/n");
                                    String respuesta = input.next();
                                    if (respuesta.equals("s")) {
                                        opcion1 = false;
                                        respuestaValida = true;
                                    } else if (respuesta.equals("n")) {
                                        opcion = 6;
                                        opcionValida = false;
                                        opcion1 = false;
                                        respuestaValida = true;
                                        programaFuncionando = false;
                                    } else {
                                        System.out.println("Error! Ingrese una opcion valida.");
                                    }
                                }
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
                                boolean respuestaValida = false;
                                while (!respuestaValida) {
                                    System.out.println();
                                    System.out.println("Desea volver al menu? s/n");
                                    String respuesta = input.next();
                                    if (respuesta.equals("s")) {
                                        opcion2 = false;
                                        respuestaValida = true;
                                    } else if (respuesta.equals("n")) {
                                        opcion = 6;
                                        opcionValida = false;
                                        opcion2 = false;
                                        respuestaValida = true;
                                        programaFuncionando = false;
                                    } else {
                                        System.out.println("Error! Ingrese una opcion valida.");
                                    }
                                }
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
                                    if (checker.checkDate(fechaFin) && (checker.convertStringToDate(fechaFin).after(checker.convertStringToDate(fechaInicio)) || checker.convertStringToDate(fechaFin).equals(checker.convertStringToDate(fechaInicio))) ){
                                        fechaFinValida = true;
                                    } else {
                                        System.out.println();
                                        System.out.println("Error! Ingrese una fecha valida (mayor a la fecha inicial).");
                                    }
                                }
                                metodos.opcion3(checker.convertStringToDate(fechaInicio), checker.convertStringToDate(fechaFin));
                                boolean respuestaValida = false;
                                while (!respuestaValida) {
                                    System.out.println();
                                    System.out.println("Desea volver al menu? s/n");
                                    String respuesta = input.next();
                                    if (respuesta.equals("s")) {
                                        opcion3 = false;
                                        respuestaValida = true;
                                    } else if (respuesta.equals("n")) {
                                        opcion = 6;
                                        opcionValida = false;
                                        opcion3 = false;
                                        respuestaValida = true;
                                        programaFuncionando = false;
                                    } else {
                                        System.out.println("Error! Ingrese una opcion valida.");
                                    }
                                }
                            }
                            break;
                        case 4:
                            opcionValida = true;
                            boolean opcion4 = true;
                            while (opcion4) {
                                String artista = null;
                                String fecha = null;
                                boolean artistaValido = false;
                                boolean fechaValida = false;
                                while (!artistaValido) {
                                    System.out.println();
                                    System.out.println("Ingrese un artista: ");
                                    artista = input.nextLine();
                                    if (artista != null) {
                                        artistaValido = true;
                                    } else {
                                        System.out.println("Error! Ingrese algo valido.");
                                    }
                                }
                                while (!fechaValida) {
                                    System.out.println();
                                    System.out.println("Ingrese una fecha a continuacion (YYYY-MM-DD): ");
                                    fecha = input.next();
                                    if (checker.checkDate(fecha)) {
                                        fechaValida = true;
                                    } else {
                                        System.out.println("Error! Ingrese una fecha valida.");
                                    }
                                }
                                metodos.opcion4(artista, fecha);
                                boolean respuestaValida = false;
                                while (!respuestaValida) {
                                    System.out.println();
                                    System.out.println("Desea volver al menu? s/n");
                                    String respuesta = input.next();
                                    if (respuesta.equals("s")) {
                                        opcion4 = false;
                                        respuestaValida = true;
                                    } else if (respuesta.equals("n")) {
                                        opcion = 6;
                                        opcionValida = false;
                                        opcion4 = false;
                                        respuestaValida = true;
                                        programaFuncionando = false;
                                    } else {
                                        System.out.println("Error! Ingrese una opcion valida.");
                                    }
                                }
                            }
                            break;
                        case 5:
                            opcionValida = true;
                            boolean opcion5 = true;
                            while (opcion5) {
                                String fechaInicio = null;
                                String fechaFin = null;
                                String tempoInicio = null;
                                String tempoFin = null;
                                boolean fechaInicioValida = false;
                                boolean fechaFinValida = false;
                                boolean tempoInicioValido = false;
                                boolean tempoFinValido = false;
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
                                    if (checker.checkDate(fechaFin) && (checker.convertStringToDate(fechaFin).after(checker.convertStringToDate(fechaInicio)) || checker.convertStringToDate(fechaFin).equals(checker.convertStringToDate(fechaInicio))) ) {
                                        fechaFinValida = true;
                                    } else {
                                        System.out.println();
                                        System.out.println("Error! Ingrese una fecha valida (mayor a la fecha inicial).");
                                    }
                                }
                                input.nextLine();
                                while (!tempoInicioValido) {
                                    System.out.println();
                                    System.out.println("Ingrese el tempo menor del tango (XXX.XXX): ");
                                    tempoInicio = input.nextLine();
                                    if (checker.checkTempo(tempoInicio) != 0) {
                                        tempoInicioValido = true;
                                    } else {
                                        System.out.println();
                                        System.out.println("Error! Ingrese un tempo valido.");
                                    }
                                }
                                while (!tempoFinValido) {
                                    System.out.println();
                                    System.out.println("Ingrese el tempo mayor del tango (XXX.XXX): ");
                                    tempoFin = input.nextLine();
                                    if (checker.checkTempo(tempoFin) != 0) {
                                        tempoFinValido = true;
                                    } else {
                                        System.out.println();
                                        System.out.println("Error! Ingrese un tempo valido.");
                                    }
                                }
                                metodos.opcion5(checker.convertStringToDate(fechaInicio), checker.convertStringToDate(fechaFin), checker.checkTempo(tempoInicio),checker.checkTempo(tempoFin));
                                boolean respuestaValida = false;
                                while (!respuestaValida) {
                                    System.out.println();
                                    System.out.println("Desea volver al menu? s/n");
                                    String respuesta = input.next();
                                    if (respuesta.equals("s")) {
                                        opcion5 = false;
                                        respuestaValida = true;
                                    } else if (respuesta.equals("n")) {
                                        opcion = 6;
                                        opcionValida = false;
                                        opcion5 = false;
                                        respuestaValida = true;
                                        programaFuncionando = false;
                                    } else {
                                        System.out.println("Error! Ingrese una opcion valida.");
                                    }
                                }
                            }
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
        System.out.println();
        System.out.println("Apagando sistema.");
        for (int i = 0; i < 3; i++) {
            System.out.println(".");
            Thread.sleep(333);
        }
        System.out.println("Finalizado.");


    }
}