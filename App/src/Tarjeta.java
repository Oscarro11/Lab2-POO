public class Tarjeta{
    private final String emoji_code;
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