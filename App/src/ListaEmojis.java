import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ListaEmojis {
    private static final String[] emojis = {"\uD83D\uDC36", "\uD83D\uDC31", "\uD83D\uDC2D", "\uD83D\uDC39", "\uD83D\uDC30", "\uD83D\uDC3B",
                                            "\uD83E\uDD81", "\uD83D\uDC2F", "\uD83D\uDC28", "\uD83D\uDC3C", "\uD83D\uDC2E", "\uD83D\uDC34",
                                            "\uD83D\uDC35", "\uD83D\uDC12", "\uD83D\uDC11", "\uD83D\uDC10", "\uD83D\uDC0F", "\uD83D\uDC2A",
                                            "\uD83D\uDC2B", "\uD83D\uDC0E", "\uD83D\uDC17", "\uD83D\uDC16", "\uD83D\uDC37", "\uD83D\uDC38",
                                            "\uD83D\uDC0D", "\uD83D\uDC22", "\uD83D\uDC20", "\uD83D\uDC1F", "\uD83D\uDC19", "\uD83D\uDC21",
                                            "\uD83D\uDC33", "\uD83D\uDC0B", "\uD83D\uDC2C", "\uD83D\uDC27", "\uD83D\uDC25", "\uD83D\uDC23"}; 

    private static final String codigo_emoji_oculto = "\uD83D\uDD12";
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
