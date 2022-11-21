public class Monstre extends EntiteMobile {

    public Monstre(Direction dir) { super(dir); }

    @Override
    String toString(String background) {
        return background.charAt(0) + switch (dir) {
            case nord -> "m";
            case sud -> "w";
            case est -> "»";
            case ouest -> "«";
        } + background.charAt(2);
    }
}
