import java.nio.charset.StandardCharsets;

//Esta clase se usa para imprimir el tablero en la terminal powershell. Se tiene por una clase aparte para poder 
//realizar cambios a la vista del tablero si asi se desea.
public class Consola {
    private Tablero tablero;

    public Consola(Tablero tablero){
        this.tablero = tablero;
    }

    public void mostrarTablero(){
        //Esta linea es necesaria para que se puedan imprimir los emojis a la terminal
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));

        //Se muestra un codigo de numeros, los cuales se usaran para identificar las coordenadas de las tarjetas en el tablero
        System.out.print("\n");
        System.out.printf("%-3s", "");
        for (int i=0; i<tablero.getTamano(); i++){
            System.out.printf("%-3s", i+1);
        }
        System.out.print("\n");
        
        //Se recorre la matriz de tablero, mostrando una letra que sirve de coordenada y el emoji que pertenece a cada celda
        for (int i=0; i<tablero.getTamano(); i++){
            System.out.print((char) ('A' + (i)) + " ");
            for (int j=0; j<tablero.getTamano(); j++){
                System.out.printf("%-3s", tablero.getCodigoCelda(j, i));
            }
            System.out.print("\n");
        }
    }   
}
