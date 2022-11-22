public class Monstre extends EntiteMobile {

    public Monstre(Direction dir) { super(dir); }

    @Override
    public void action(Case courante, Case cible) {
        if (cible.estLibre()) {
            ((CaseTraversable) cible).entre(this);
            ((CaseTraversable) courante).vide(); return;
        }
        if (cible instanceof CaseTraversable) {
            if (
                    ((CaseTraversable) cible).getContenu() instanceof Personnage ||
                    ((CaseTraversable) cible).getContenu() instanceof Obstacle
            ) {
                ((CaseTraversable) cible).contenu.resistance--;
                if (((CaseTraversable) cible).getContenu().resistance <= 0)
                    ((CaseTraversable) cible).vide();
            }
            return;
        }
        this.dir = Direction.random();
    }

    @Override
    String toString(String background) {
        return background.charAt(0) + switch (dir) {
            case nord -> "m";
            case sud -> "w";
            case est -> "»";
            case ouest -> "«";
        } + background.charAt(2);
    }
}
