public abstract class EntiteMobile extends Entite {
    protected Direction dir;
    protected int resistance;
    public EntiteMobile(Direction dir) {
        this.dir = dir;
        resistance = 1;
    }
    public abstract void action(Case courante, Case cible);
}
