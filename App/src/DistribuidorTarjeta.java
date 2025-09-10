public class DistribuidorTarjeta{
    //El distribuidor de tarjetas puede dar un maximo de 2 tarjetas, debido a que es
    //un juego de memoria de parejas
    private int cont_tarjetas = 2;
    private final Tarjeta tarjeta;

    public DistribuidorTarjeta(Tarjeta tarjeta_a_distribuir){
        this.tarjeta = tarjeta_a_distribuir;
    }

    public Tarjeta darTarjeta(){
        this.cont_tarjetas += -1;
        return clonarTarjeta(tarjeta);
    }

    //Cada tarjeta que el distribuidor da como objeto debe ser una nueva instancia, para
    //asegurarse de que al revisar una tarjeta no se revise su pareja al mismo tiempo.
    private Tarjeta clonarTarjeta(Tarjeta tarjeta_original){
        return new Tarjeta(tarjeta_original.getEmoji_code());
    }

    public int getCont_tarjetas() {
        return cont_tarjetas;
    }

}   
