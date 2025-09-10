import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private final int tamano;
    private final Random RNG = new Random();
    //La lista de distribuidores se usa únicamente para asignar las Tarjetas al tablero cuando este se crea.
    private ArrayList<DistribuidorTarjeta> lista_distribuidores = new ArrayList<DistribuidorTarjeta>();
    //El tablero esta compuesto de una martiz cuadrada conformada por una lista de listas, en donde cada espacio tiene una Tarjeta
    private Tarjeta[][] celdas;
    //Estas dos referencias se usan para saber que cartas deben mostrarse por parte del ultimo turno, pero que no se han emparejado (aun pueden dar puntos)  
    private Tarjeta tarjeta_revelada_1;
    private Tarjeta tarjeta_revelada_2;
    //Este atributo indica cuantos pares de tarjetas faltan para completar el tablero, y con ello terminar la ronda.
    private int pares_pendientes;

    public Tablero(int tamano){
        this.tamano = tamano;
        //Este calculo de pares pendientes funciona unicamente con la matriz cuadrada, si se desease una rectangular debe anadirse otra dimension de alto o largo
        this.pares_pendientes = (tamano * tamano)/2;
        this.celdas = new Tarjeta[tamano][tamano];
        generarTarjetas();
    }

    //Con este metodo se busca asignar una tarjeta a una celda. Para ello se obtiene una tarjeta de un dispensador, el cual 
    //al quedarse con 0 tarjetas se elimina. Como cada dispensador tiene 2 tarjetas, nos aseguramos que en la matriz cada tarjeta
    //tenga un par
    private Tarjeta asignarTarjeta(){
        int pos_tarjeta_asignar = RNG.nextInt(lista_distribuidores.size());
        DistribuidorTarjeta distribuidor_de_tarjeta = lista_distribuidores.get(pos_tarjeta_asignar);

        Tarjeta tarjeta_a_asignar = distribuidor_de_tarjeta.darTarjeta();

        if (distribuidor_de_tarjeta.getCont_tarjetas() == 0){
            lista_distribuidores.remove(pos_tarjeta_asignar);
        }

        return tarjeta_a_asignar;
    }
 
    //Este metodo se encarga de generar los distribuidores de tarjetas, y despues los usa para asignar las tarjetas al tablero
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

    //Este metodo se encarga de validar si una tarjeta puede o no usarse para obtener puntos. En caso de que pueda usarse, se regresa la tarjeta en si.
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

    //Este metodo se encarga de dar el codigo correspondiente a una tarjeta para que pueda visualizarse (solo cuando
    //la tarjeta se esta intentando adivinar o si ya ha dado puntos). Si la tarjeta sigue oculta, se regresa un
    //codigo generico que represente su estado de oculto.
    public String getCodigoCelda(int X, int Y){
        if (celdas[Y][X].getIs_matched() || celdas[Y][X] == tarjeta_revelada_1 || celdas[Y][X] == tarjeta_revelada_2){
            return evaluarTarjeta(X, Y, true).getEmoji_code();
        }
        else{
            return ListaEmojis.getCodigoEmojiOculto();
        }
    }

    //Este metodo busca dos tarjetas en el tablero, y verifica si pueden dar puntos o no. En caso de poder dar puntos,
    //verifica si las tarjetas son un par o no. Dependiendo de ello, cambia las tarjetas a visibles o a ya emparejadas.
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
