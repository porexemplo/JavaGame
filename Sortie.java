public class Sortie extends CaseTraversable {
    public Sortie(int lig, int col) { super(lig, col); }
    public Sortie(int lig, int col, Entite e) { super(lig, col, e); }

    public String toString() {
        return contenu == null ? "( )" : contenu.toString("( )");
    }
}
