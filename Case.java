public abstract class Case {
    protected int lig, col;
    abstract boolean estLibre();

    public Case(int lig, int col) { this.lig = lig; this.col = col; }
}