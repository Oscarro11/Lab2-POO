import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    public static Tablero tablero = new Tablero(4);
    public static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));
    }
}

