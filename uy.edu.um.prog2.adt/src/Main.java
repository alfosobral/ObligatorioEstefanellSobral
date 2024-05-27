import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean programaFuncionando = true;
        for (int i = 0; i < 3; i++) {
            System.out.println(".");
            Thread.sleep(1000);
        }

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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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