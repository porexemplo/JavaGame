public class Joueur extends Entite{
    protected Direction dir;
    public Joueur() { resistance = 5; dir = Direction.nord; }

    @Override
    String toString(String background) { return "H"; }

    public void setDirection(Direction dir) { this.dir = dir; }

    public void movePlayer(Case courante, Case cible) {
        if (cible instanceof CaseTraversable) {
            if (((CaseTraversable) cible).getContenu() instanceof Monstre){
                this.resistance--;
                ((CaseTraversable) cible).getContenu().resistance--;
                if (((CaseTraversable) cible).getContenu().resistance <= 0)
                    ((CaseTraversable) cible).vide();
            }

            else if (((CaseTraversable) cible).getContenu() instanceof Obstacle){
                this.resistance--;
                ((CaseTraversable) cible).getContenu().resistance--;
                if (((CaseTraversable) cible).getContenu().resistance <= 0)
                    ((CaseTraversable) cible).vide();

            }

            else if (((CaseTraversable) cible).getContenu() instanceof Personnage)
                return;
            else {
                ((CaseTraversable) cible).entre(((CaseTraversable) courante).getContenu());
                ((CaseTraversable) courante).vide();
            }
        }
    }
}
