import java.util.Random;

/* Directions cardinales */
public enum Direction {
    nord,
    sud,
    est,
    ouest;

    /* Lie le symbole représentant une créature et sa direction */
    public static Direction ofChar(Character d) {
        return switch (d) {
            case '^', 'm' -> Direction.nord;
            case 'v', 'w' -> Direction.sud;
            case '>', '»' -> Direction.est;
            case '<', '«' -> Direction.ouest;
            default -> null;
        };
    }

    /* Tirage d'une direction aléatoire */
    public static Direction random() {
        Random rnd = new Random();
        return switch (rnd.nextInt(4)) {
            case 0 -> Direction.nord;
            case 1 -> Direction.sud;
            case 2 -> Direction.est;
            default -> Direction.ouest;
        };
    }
}
