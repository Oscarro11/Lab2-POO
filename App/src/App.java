import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    public static Scanner teclado = new Scanner(System.in);
    public static Controlador controlador;

    public static boolean continuar_juego = true;
    public static boolean continuar_ronda = true;

    public static String nombre_jugador1;
    public static String nombre_jugador2;


    public static void mensajeBienvenida(){
        System.out.println("\u00A1" + "Bienvenido al juego de memoria! Antes de empezar, debe ingresar el nombre de quienes jugaran (max 2 personas):");
        
        System.out.println("\nIngrese el nombre del primer jugador:");
        nombre_jugador1 = teclado.nextLine();

        System.out.println("\nIngrese el nombre del segundo jugador:");
        nombre_jugador2 = teclado.nextLine();
    }

    public static void iniciarRonda(){
        int tamano_tablero;

        System.out.println("\nUna nueva ronda va a comenzar");
        System.out.println("Ingrese el tamano del tablero en donde quiere jugar (el tamano del tablero debe se par):");
        tamano_tablero = teclado.nextInt();
        teclado.nextLine();

        controlador = new Controlador(tamano_tablero, nombre_jugador1, nombre_jugador2);
        continuar_ronda = true;

        System.out.println("\u00A1" + "La ronda esta por comenzar!\n");
    }

    public static void realizarTurno(){
        String opcion;
        System.out.print("\nEs el turno de " + controlador.getNombreJugadorActivo() + "...\n" +
                                "\u00BF" +"Que deseas hacer? (escribe el numero de la opcion que deseas realizar) \n" +
                                "1. Adivinar pareja de tarjetas \n" +
                                "2. Ver el tablero actual \n" +
                                "3. Consultar mis estadisticas\n");
        opcion = teclado.nextLine().strip();

        switch (opcion) {
            case "1":
                adivinarParejas();
                break;
        
            case "2":
                revisarTablero();
                break;

            case "3":
                consultarInfo();
                break;

            default:
                System.out.println("La opcion ingresada no le corresponde a ninguna del menu. Intentelo de nuevo");
                break;
        }
    }

    private static int convertirLetraANumero(char letra){
        return Character.getNumericValue(letra) - 10;
    }

    private static boolean revisarCoordenada(String coordenada){

        if (coordenada.length() != 2){
            return false;
        }

        else if (!Character.isUpperCase(coordenada.charAt(0)) || !Character.isDigit(coordenada.charAt(1))) {
            return false;
        }
        else{
            return true;
        }
    }

    public static void adivinarParejas(){
        String coordenadas_tarjeta_1 = "";
        String coordenadas_tarjeta_2 = "";
        boolean tarjeta_valida;
        
        tarjeta_valida = false;
        while (!tarjeta_valida) {
            System.out.println("\nIngrese las coordenadas de la primera tarjeta a revelar.");
            coordenadas_tarjeta_1 = teclado.nextLine();
            if (!revisarCoordenada(coordenadas_tarjeta_1)){
                System.out.println("Las coordenadas de las tarjetas deben ser [letra mayuscula][numero]. Intentelo de nuevo");
            }
            else{
                tarjeta_valida = true;
            }
        }

        tarjeta_valida = false;
        while (!tarjeta_valida) {
            System.out.println("\nIngrese las coordenadas de la segunda tarjeta a revelar.");
            coordenadas_tarjeta_2 = teclado.nextLine();
            if (!revisarCoordenada(coordenadas_tarjeta_2)){
                System.out.println("Las coordenadas de las tarjetas deben ser [letra mayuscula][numero]. Intentelo de nuevo");
            }
            else{
                tarjeta_valida = true;
            }
        }

        int coordenada_x_1 = Character.getNumericValue(coordenadas_tarjeta_1.charAt(1) - 1);
        int coordenada_y_1 = convertirLetraANumero(coordenadas_tarjeta_1.charAt(0));
        int coordenada_x_2 = Character.getNumericValue(coordenadas_tarjeta_2.charAt(1) - 1);
        int coordenada_y_2 = convertirLetraANumero(coordenadas_tarjeta_2.charAt(0));
        
        try{
            if (controlador.jugarTurno(coordenada_x_1, coordenada_y_1, coordenada_x_2, coordenada_y_2)){
            System.out.println("Has encontrado una pareja, por lo que ganas un punto y puedes continuar tu turno");
            }
            else{
                System.out.println("No encontraste a una pareja, por lo que le toca al proximo jugador");
            }

            revisarTablero();
        }
        catch (Exception e){
            System.out.println(e.getMessage() + " Intentelo de nuevo");
        }
        
        
        if (controlador.rondaTermino()) {
            continuar_ronda = false;
        }
    }

    public static void revisarTablero(){
        controlador.imprimirTablero();
    }

    public static void consultarInfo(){
        System.out.println(controlador.mostrarInfoJugador());
    }

    public static void mensajeDespedida(){
        System.out.println("El juego de memoria ha terminado. He aqui los resultados finales: ");
        System.out.println(controlador.mostrarResultadosJugadores());
        System.out.println("Gracias por jugar. Esperamos verlo de nuevo");
    }

    public static void rondaTerminada(){
        System.out.println("\nLa ronda se ha acabado. ¿Desea jugar una nueva ronda, o terminar la sesion de juego? (Y/N)");
        String desea_continuar_juego = teclado.nextLine().strip().toUpperCase();

        switch (desea_continuar_juego) {
            case "Y":
                continuar_juego = true;
                break;
        
            case "N":
                continuar_juego = false;
                break;

            default:
                System.out.println("La opcion ingresada no es Y ni N. Intentelo de nuevo");
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        mensajeBienvenida();

        while (continuar_juego) {
            iniciarRonda();

            while (continuar_ronda) {
                realizarTurno();
            }
            rondaTerminada();
        }
        
        mensajeDespedida();
    }
}

