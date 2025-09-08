public class DistribuidorTarjeta{
    private int cont_tarjetas = 2;
    private final Tarjeta tarjeta;

    public DistribuidorTarjeta(Tarjeta tarjeta_a_distribuir){
        this.tarjeta = tarjeta_a_distribuir;
    }

    public Tarjeta darTarjeta(){
        this.cont_tarjetas += -1;
        return clonarTarjeta(tarjeta);
    }

    private Tarjeta clonarTarjeta(Tarjeta tarjeta_original){
        return new Tarjeta(tarjeta_original.getEmoji_code());
    }

    public int getCont_tarjetas() {
        return cont_tarjetas;
    }

}   
