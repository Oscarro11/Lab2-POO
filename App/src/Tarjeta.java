public class Tarjeta{
    //El codigo de emoji usa el formato de Unicode escape, en el formato \\uXXXX\\uXXXX
    private final String emoji_code;
    //Este atributo se usa para saber si la tarjeta aun puede dar puntos o no.
    private boolean is_matched = false;

    public Tarjeta(String emoji_code){
        this.emoji_code = emoji_code;
    }

    public String getEmoji_code() {
        return emoji_code;
    }
    public boolean getIs_matched(){
        return is_matched;  
    }

    public void setIs_matched(boolean is_matched) {
        this.is_matched = is_matched;
    }
}