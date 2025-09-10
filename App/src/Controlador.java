public class Controlador {
    private Tablero tablero;
    private final Jugador jugador_1;
    private final Jugador jugador_2;
    /**
    La forma en que se sabe quien debe jugar el turno es por medio de una referencia al "jugador activo".
    Cada vez que se intenta adivinar una carta, el jugador activo gana un punto o se cambia la referencia 
    de quien es el jugador activo.
    **/
    private Jugador jugador_activo;
    private Consola consola;

    public Controlador(int tamano_tablero, String nombre_jugador1, String nombre_jugador2){
        this.tablero = new Tablero(tamano_tablero);
        this.consola = new Consola(tablero);
        this.jugador_1 = new Jugador(nombre_jugador1);
        this.jugador_2 = new Jugador(nombre_jugador2);
        jugador_activo = jugador_1;
    }

    public void imprimirTablero(){
        consola.mostrarTablero();
    }

    //En este metodo se ingresan dos coordenadas, y se verifica si ambas son pares o no. En caso de ser pares, el jugador
    //activo gana un punto. De lo contrario, se cambia quien es el jugador activo.
    public boolean jugarTurno(int X1, int Y1, int X2, int Y2){
        boolean resultadoParejas;
        
        try{
            resultadoParejas = tablero.emparejarTarjetas(X1, Y1, X2, Y2);
        } 
        catch (Exception e){
            throw e;
        }

        if (resultadoParejas){
            jugador_activo.sumarPunto();
            return true;
        }
        else{
            cambiarTurno();
            return false;  
        }
    }

    private void cambiarTurno(){
        if (jugador_activo == jugador_1){
            jugador_activo = jugador_2;
        }
        else{
            jugador_activo = jugador_1;
        }
    }

    //Este metodo muestra la informacion del jugador activo, es decir, muestra su nombre, ronda ganadas y puntos de esta ronda
    public String mostrarInfoJugador(){
        return "Estadisticas de " + jugador_activo.getNombre() + ":"
                + "\n" + jugador_activo.getNombre() + " ha ganado " + jugador_activo.getPuntos_ronda() + " puntos en esta ronda."
                + "\n" + jugador_activo.getNombre() + " ha ganado " + jugador_activo.getRondas_ganadas() + " rondas en esta sesion de juego.";
    }

    public String mostrarResultadosJugadores(){
        //Este metodo se usa cuando el juego acaba, con lo que se muestra las estadisticas de ambos jugadores y se menciona quien gano
        //la sesion de juego
        String mensaje_ganador;
        if (jugador_1.getRondas_ganadas() > jugador_2.getRondas_ganadas()) {
            mensaje_ganador = "¡" + jugador_1.getNombre() + " ha ganado el juego de memoria!";
        }
        else if (jugador_1.getRondas_ganadas() < jugador_2.getRondas_ganadas()) {
            mensaje_ganador = "¡" + jugador_2.getNombre() + " ha ganado el juego de memoria!";
        }
        else{
            mensaje_ganador = "Ambos jugadores han quedado en empate";
        }


        return jugador_1.getNombre() + " ha ganado " + jugador_1.getRondas_ganadas() + " rondas\n" +
                jugador_2.getNombre() + " ha ganado " + jugador_2.getRondas_ganadas() + " rondas\n" +
                mensaje_ganador;         
    }

    public boolean rondaTermino(){
        if (tablero.getPares_pendientes() != 0) {
            return false;
        }
        else{
            jugador_1.resultadoRonda(jugador_1.getRondas_ganadas() > jugador_2.getRondas_ganadas());
            jugador_2.resultadoRonda(jugador_1.getRondas_ganadas() < jugador_2.getRondas_ganadas());
            return true;
        }
    }

    public String getNombreJugadorActivo(){
        return jugador_activo.getNombre();
    }
}
