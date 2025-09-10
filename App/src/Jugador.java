public class Jugador {
    private final String nombre;
    private int puntos_ronda = 0;
    private int rondas_ganadas = 0;

    public Jugador(String nombre){
        this.nombre = nombre;
    }

    public void sumarPunto(){
        this.puntos_ronda += 1;
    }

    private void reiniciarPuntos(){
        this.puntos_ronda = 0;
    }

    //Este metodo le asigna 1 ronda ganada al jugador, y reinicia sus puntos de ronda
    public void resultadoRonda(boolean gano_ronda){
        if (gano_ronda) {
            this.rondas_ganadas += 1;
        }
        reiniciarPuntos();
    }


    public int getPuntos_ronda() {
        return puntos_ronda;
    }
    public int getRondas_ganadas() {
        return rondas_ganadas;
    }
    public String getNombre() {
        return nombre;
    }
}
