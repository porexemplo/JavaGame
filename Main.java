public class Main {
    public static void main(String[] args) {
        Generator gen = new Generator(10, .5);
        gen.generateMap();
        gen.printMap();
    }
}
