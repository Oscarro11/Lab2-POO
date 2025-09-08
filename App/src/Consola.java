import java.nio.charset.StandardCharsets;

public class Consola {
    private Tablero tablero;

    public Consola(Tablero tablero){
        this.tablero = tablero;
    }

    public void mostrarTablero(){
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.print("\n");
        System.out.printf("%-3s", "");
        for (int i=0; i<tablero.getTamano(); i++){
            System.out.printf("%-3s", i+1);
        }
        System.out.print("\n");
        
        for (int i=0; i<tablero.getTamano(); i++){
            System.out.print((char) ('A' + (i)) + " ");
            for (int j=0; j<tablero.getTamano(); j++){
                System.out.printf("%-3s", tablero.getCodigoCelda(j, i));
            }
            System.out.print("\n");
        }
    }   
}
