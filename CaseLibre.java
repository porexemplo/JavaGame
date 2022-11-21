public class CaseLibre extends CaseTraversable {
    public CaseLibre(int lig, int col) { super(lig, col); }
    public CaseLibre(int lig, int col, Entite e) { super(lig, col, e); }

    public String toString() {
        return contenu == null ? "   " : contenu.toString();
    }
}
