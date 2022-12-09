public abstract class EntiteMobile extends Entite {
    protected Direction dir;
    protected int resistance;

    public EntiteMobile(Direction dir) {
        this.dir = dir;
        resistance = 5;
    }
    
    public Direction getDirection(){ return this.dir;}
    
    public abstract void action(Case courante, Case cible);
}
