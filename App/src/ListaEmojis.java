import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ListaEmojis {
    private static final String[] emojis = {"\uD83D\uDE00", "\uD83D\uDE01", "\uD83D\uDE09", "\uD83D\uDE05", "\uD83D\uDE0A", "\uD83D\uDE0D", 
                                    "\uD83D\uDE20", "\uD83D\uDE21", "\uD83D\uDE22", "\uD83D\uDE2D", "\uD83D\uDE28", "\uD83D\uDE2E",
                                    "\uD83D\uDE32", "\uD83D\uDE37", "\uD83D\uDC4D", "\uD83D\uDC4E", "\uD83D\uDC4C", "\u270C",
                                    "\uD83D\uDD90", "\uD83D\uDC4F", "\uD83D\uDE4F", "\u2764\uFE0F", "\uD83D\uDC9B", "\uD83D\uDC9A",
                                    "\uD83D\uDC99", "\uD83D\uDC9C", "\uD83D\uDC94", "\u2728", "\uD83C\uDF1F", "\u2B50",
                                    "\uD83D\uDC36", "\uD83D\uDC31", "\uD83D\uDC2E", "\uD83D\uDC37", "\uD83D\uDC35", "\uD83D\uDC3B"}; 
    private static final String codigo_emoji_oculto = "\u2B1C";
    private static ArrayList<String> emojis_sin_usar = new ArrayList<>(Arrays.asList(emojis));
    private static final Random RNG = new Random();

    public static String getEmojiCode(){
        return emojis[RNG.nextInt(emojis.length)];
    } 

    public static String getUnusedEmojiCode(){
        int code_position = RNG.nextInt(emojis_sin_usar.size());
        String code_to_return = emojis_sin_usar.get(code_position);
        emojis_sin_usar.remove(code_position);
        return code_to_return;
    }

    public static String getCodigoEmojiOculto() {
        return codigo_emoji_oculto;
    }
}
