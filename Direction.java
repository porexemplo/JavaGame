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
        int r = rnd.nextInt(4);
        switch (r) {
            case 0:  return Direction.nord;
            case 1:  return Direction.sud;
            case 2:  return Direction.est;
            default: return Direction.ouest;
        }
    }

    public static Direction sensContraire (Direction dir){
        switch (dir){
            case nord : return sud;
            case sud : return nord;
            case est : return ouest;
            case ouest : return est;
            default: return ouest;
        }
    }
}
