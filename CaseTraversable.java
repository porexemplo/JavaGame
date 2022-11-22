public class CaseTraversable extends Case {
    protected Entite contenu;

    @Override
    boolean estLibre() { return contenu == null; }

    public CaseTraversable(int lig, int col) { super(lig, col); contenu = null; }
    public CaseTraversable(int lig, int col, Entite e) { super(lig, col); contenu = e; }

    public Entite getContenu() { return contenu; }
    public void vide() { contenu = null; }
    public void entre(Entite e) { contenu = e; }
}
