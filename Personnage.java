public class Personnage extends EntiteMobile {
    public Personnage(Direction dir) { super(dir); }
    @Override
    String toString(String background) {
        return background.charAt(0) + switch (dir) {
            case nord -> "^";
            case sud -> "v";
            case est -> ">";
            case ouest -> "<";
        } + background.charAt(2);
    }
}
