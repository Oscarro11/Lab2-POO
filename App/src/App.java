import java.util.Scanner;

public class App {
    public static Tablero tablero = new Tablero(4);
    public static Scanner teclado = new Scanner(System.in);
    public static Consola consola = new Consola(tablero);

    public static void main(String[] args) throws Exception {
        consola.mostrarTablero();
    }
}

