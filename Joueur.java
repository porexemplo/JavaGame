public class Joueur extends Entite{
    protected Direction dir;
    public Joueur() { resistance = 50; dir = Direction.nord; }

    @Override
    String toString(String background) { return "H"; }

    public void setDirection(Direction dir) { this.dir = dir; }

    public void movePlayer(Case courante, Case cible) {
        System.out.println("CALLED MOVE PLAYER");
        if (cible instanceof CaseTraversable) {
            if (((CaseTraversable) cible).getContenu() instanceof Monstre)
                ((CaseTraversable) cible).getContenu().resistance--;
            else if (((CaseTraversable) cible).getContenu() instanceof Obstacle)
                ((CaseTraversable) cible).getContenu().resistance--;
            else if (((CaseTraversable) cible).getContenu() instanceof Personnage)
                return;
            else {
                ((CaseTraversable) cible).entre(((CaseTraversable) courante).getContenu());
                ((CaseTraversable) courante).vide();
            }
        }
    }
}
