public class Obstacle extends Entite {

    public Obstacle() { resistance = 5; }
    public Obstacle(int r) { resistance = r; }

    @Override
    String toString(String background) {
        if (resistance == 2) return "@" + background.charAt(0) + "@";
        if (resistance == 1) return background.charAt(0) + "@" + background.charAt(2);
        return "@@@";
    }
}
