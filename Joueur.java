public class Joueur extends Entite{
    
    public Joueur() { this.resistance = 50; }

    @Override
    String toString(String background) { return "H"; }

    public void action(Case cible) {
        if (cible instanceof CaseLibre) {
            if (((CaseLibre) cible).getContenu() instanceof Monstre)
                ((CaseLibre) cible).getContenu().resistance--;
            if (((CaseLibre) cible).getContenu() instanceof Obstacle) {
                ((CaseLibre) cible).getContenu().resistance--;
            }
        }
    }

    public void movePlayer()
}
