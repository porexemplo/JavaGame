public class Joueur  extends Entite{
    public int lig,col;
    public Direction dir;
    public Joueur(int l, int c) { resistance = 3; lig = l; col = c; }
    public Joueur(int l, int c,int r) { resistance = r; lig = l; col = c;}
    @Override
    String toString(String background) {
        return "H";
    }

}
