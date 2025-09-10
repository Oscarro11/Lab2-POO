import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//Esta clase esta disenada para dar codigos de emojis, sin necesidad de crear un objto para ello.
public class ListaEmojis {
    //Los emojis se dan en forma de codigos de escape Unicode, con el formato \\uXXXX\\uXXXX. 
    //En este proyecto se usaron emojis de animales, tanto para hacer mas visual el juego de memoria
    //como para asegurarnos de que todos los emojis cubren el mismo espacio en la terminal.
    private static final String[] emojis = {"\uD83D\uDC36", "\uD83D\uDC31", "\uD83D\uDC2D", "\uD83D\uDC39", "\uD83D\uDC30", "\uD83D\uDC3B",
                                            "\uD83E\uDD81", "\uD83D\uDC2F", "\uD83D\uDC28", "\uD83D\uDC3C", "\uD83D\uDC2E", "\uD83D\uDC34",
                                            "\uD83D\uDC35", "\uD83D\uDC12", "\uD83D\uDC11", "\uD83D\uDC10", "\uD83D\uDC0F", "\uD83D\uDC2A",
                                            "\uD83D\uDC2B", "\uD83D\uDC0E", "\uD83D\uDC17", "\uD83D\uDC16", "\uD83D\uDC37", "\uD83D\uDC38",
                                            "\uD83D\uDC0D", "\uD83D\uDC22", "\uD83D\uDC20", "\uD83D\uDC1F", "\uD83D\uDC19", "\uD83D\uDC21",
                                            "\uD83D\uDC33", "\uD83D\uDC0B", "\uD83D\uDC2C", "\uD83D\uDC27", "\uD83D\uDC25", "\uD83D\uDC23"}; 

    //El emoji oculto se representa con un candado, debido a que tiene el mismo tamano que los emojis de animales
    private static final String codigo_emoji_oculto = "\uD83D\uDD12";
    private static ArrayList<String> emojis_sin_usar = new ArrayList<>(Arrays.asList(emojis));
    private static final Random RNG = new Random();

    //Este metodo da el codigo aleatorio de un emoji
    public static String getEmojiCode(){
        return emojis[RNG.nextInt(emojis.length)];
    } 

    //Este metodo da el codigo aleatorio de un emoji que no se ha usado en el sistema
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
