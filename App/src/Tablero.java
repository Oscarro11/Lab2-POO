import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private final int tamano;
    private final Random RNG = new Random();
    private ArrayList<DistribuidorTarjeta> lista_distribuidores = new ArrayList<DistribuidorTarjeta>();
    private Tarjeta[][] celdas;  
    private Tarjeta tarjeta_revelada_1;
    private Tarjeta tarjeta_revelada_2;
    private int pares_pendientes;

    public Tablero(int tamano){
        this.tamano = tamano;
        this.pares_pendientes = (tamano * tamano)/2;
        this.celdas = new Tarjeta[tamano][tamano];
        generarTarjetas();
    }

    private Tarjeta asignarTarjeta(){
        int pos_tarjeta_asignar = RNG.nextInt(lista_distribuidores.size());
        DistribuidorTarjeta distribuidor_de_tarjeta = lista_distribuidores.get(pos_tarjeta_asignar);

        Tarjeta tarjeta_a_asignar = distribuidor_de_tarjeta.darTarjeta();

        if (distribuidor_de_tarjeta.getCont_tarjetas() == 0){
            lista_distribuidores.remove(pos_tarjeta_asignar);
        }

        return tarjeta_a_asignar;
    }

    private void generarTarjetas(){
        for (int i=0; i<pares_pendientes; i++){
           lista_distribuidores.add(new DistribuidorTarjeta(new Tarjeta(ListaEmojis.getUnusedEmojiCode())));
        }

        for (int i=0; i<celdas.length; i++){
            for (int j=0; j<celdas.length; j++){
                celdas[i][j] = asignarTarjeta();
            }
        }
    }

    private Tarjeta evaluarTarjeta(int X, int Y, boolean can_be_already_revealed){
        if (Y < 0 || Y > tamano || X < 0 || X > tamano){
            throw new ArrayIndexOutOfBoundsException("Las coordenadas x=" + X + " y y=" + Y + " no se encuentran dentro del tablero.");
        }

        else if (!can_be_already_revealed){
                if (celdas[Y][X].getIs_matched()){
                    throw new InvalidParameterException("La tarjeta en las coordenadas x=" + X  + " y="+ Y + " ya ha sido revelada.");
            }
        }
        return celdas[Y][X];
    }

    public String getCodigoCelda(int X, int Y){
        if (celdas[Y][X].getIs_matched() || celdas[Y][X] == tarjeta_revelada_1 || celdas[Y][X] == tarjeta_revelada_2){
            return evaluarTarjeta(X, Y, true).getEmoji_code();
        }
        else{
            return ListaEmojis.getCodigoEmojiOculto();
        }
    }

    public boolean emparejarTarjetas(int X1, int Y1, int X2, int Y2){
        if (X1 == X2 && Y1 == Y2) {
            throw new InvalidParameterException("No puede evaluar una tarjeta consigo misma.");
        }
        else{
            Tarjeta tarjeta1;
            Tarjeta tarjeta2;
            
            try {
                tarjeta1 = evaluarTarjeta(X1, Y1, false);
                tarjeta2 = evaluarTarjeta(X2, Y2, false);
                
            } catch (Exception e) {
                throw e;
            }

            tarjeta_revelada_1 = null;
            tarjeta_revelada_2 = null;

            if (tarjeta1.getEmoji_code().equals(tarjeta2.getEmoji_code())) {
                tarjeta1.setIs_matched(true);
                tarjeta2.setIs_matched(true);
                pares_pendientes += -1;
                return true;
            }
            else{
                tarjeta_revelada_1 = tarjeta1;
                tarjeta_revelada_2 = tarjeta2;
                return false;
            }
        }
    }

    public int getTamano() {
        return tamano;
    }

    public int getPares_pendientes() {
        return pares_pendientes;
    }
}
