//Estas librerias son necesarias para poder imprimir los emojis de la consola
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

public class App {
    public static Scanner teclado = new Scanner(System.in);
    public static Controlador controlador;

    //Estos dos atributos funcionan como banderas, para saber si el juego y la ronda deben continuar respectivamente.
    public static boolean continuar_juego = true;
    public static boolean continuar_ronda = true;

    //Se mantiene el nombre de los dos jugadores como atributos, para un acceso mas facil al momento
    //de pedir la opcion que el juagdor desea realizar
    public static String nombre_jugador1;
    public static String nombre_jugador2;

    //Este mensaje obtiene el nombre de ambos jugadores de la sesion de juego, se usa 1 vez por ciclo del programa
    public static void mensajeBienvenida(){
        System.out.println("\u00A1" + "Bienvenido al juego de memoria! Antes de empezar, debe ingresar el nombre de quienes jugaran (max 2 personas):");
        
        System.out.println("\nIngrese el nombre del primer jugador:");
        nombre_jugador1 = teclado.nextLine();

        System.out.println("\nIngrese el nombre del segundo jugador:");
        nombre_jugador2 = teclado.nextLine();
    }

    public static void iniciarRonda(){
        //En cada ronda se pide un tamano de tablero diferente, y con ello se crea un nuevo controlador del juego
        int tamano_tablero = 0;
        boolean tablero_valido = false;

        System.out.println("\nUna nueva ronda va a comenzar");
        while (!tablero_valido) {
            System.out.println("Ingrese el tamano del tablero en donde quiere jugar (debe ser de 2, 4 o 6):");
            tamano_tablero = teclado.nextInt();
            teclado.nextLine();

            if (tamano_tablero == 2 || tamano_tablero == 4 || tamano_tablero == 6){
                tablero_valido = true;
            }
            else{
                System.out.println("El tamano del tablero debe ser 2, 4 o 6. Intentelo de nuevo");
            }
        }
        
        controlador = new Controlador(tamano_tablero, nombre_jugador1, nombre_jugador2);
        continuar_ronda = true;

        System.out.println("\u00A1" + "La ronda esta por comenzar!\n");
    }

    //Este metodo unicamente le muestra a los jugadores que opciones tienen, y recoge la opcion que los jugadores desean realizar
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

    //Este metodo devuelve el numero al que equivale una letra, basandonos en su codigo ASCII
    private static int convertirLetraANumero(char letra){
        return Character.getNumericValue(letra) - 10;
    }

    //Este metodo verifica que la coordenada que el jugador ingreso corresponda a una de las del tablero
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

    //En este metodo, el jugador actual ingresa las coordenadas de 2 tarjetas. Se comparan esas tarjetas, y se le dice al jugador si puede o no
    //seguir la ronda. Al final, se muestra tambien el tablero actual, con las tarjetas ya reveladas de antes y las tarjetas que el jugador
    //intento adivinar
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

    //Este metodo muestra las estadisticas del jugador actual
    public static void consultarInfo(){
        System.out.println(controlador.mostrarInfoJugador());
    }

    //Este metodo se usa 1 vez cuando se termina una ronda y no se quiere continuar. El metodo muestra las estadisticas de ambos jugadores,
    //y menciona quien de los dos es el ganador de la sesion de juego
    public static void mensajeDespedida(){
        System.out.println("El juego de memoria ha terminado. He aqui los resultados finales: ");
        System.out.println(controlador.mostrarResultadosJugadores());
        System.out.println("Gracias por jugar. Esperamos verlo de nuevo");
    }

    //Este metodo se usa al terminarse una ronda. Se pregunta si se quiere continuar o no, en cuyo caso se da una nueva ronda o el mensaje
    //de despedida
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

