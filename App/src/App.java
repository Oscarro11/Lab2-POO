import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.out.println("\u2764\uFE0F");
    }
}
