public class Joueur extends Entite{
    protected Direction dir;
    public Joueur() { resistance = 50; dir = Direction.nord; }

    @Override
    String toString(String background) { return "H"; }

    public void setDirection(Direction dir) { this.dir = dir; }

    public void movePlayer(Case courante, Case cible) {
        System.out.println("CALLED MOVE PLAYER");
        if (cible instanceof CaseLibre) {
            if (((CaseLibre) cible).getContenu() instanceof Monstre)
                ((CaseLibre) cible).getContenu().resistance--;
            else if (((CaseLibre) cible).getContenu() instanceof Obstacle)
                ((CaseLibre) cible).getContenu().resistance--;
            else if (((CaseLibre) cible).getContenu() instanceof Personnage)
                return;
            else {
                ((CaseTraversable) cible).entre(((CaseTraversable) courante).getContenu());
                ((CaseTraversable) courante).vide();
            }
        }
    }
}
