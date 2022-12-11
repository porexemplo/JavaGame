import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        Noise: 0 -> No free space; 99% -> No obstacles
        Generator gen = null;
        try {
            gen = new Generator(15, .75, 3, 4);
        } catch (FileNotFoundException e) {
            System.out.println("File not Found !");
        }
        assert gen != null;
        gen.generateMap();
        gen.printMap();
    }
}
