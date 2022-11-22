public class Personnage extends EntiteMobile {
    static int saved;
    public Personnage(Direction dir) { super(dir); saved = 0; }

    @Override
    public void action(Case courante, Case cible) {
        if (courante instanceof Sortie) {
            ((Sortie) courante).vide(); saved++; return;
        }
        if (cible.estLibre()) {
            ((CaseTraversable) cible).entre(this);
            ((CaseTraversable) courante).vide(); return;
        }
        if (cible instanceof CaseTraversable) {
            if (((CaseTraversable) cible).getContenu() instanceof Obstacle) {
                ((CaseTraversable) cible).getContenu().resistance--;
                if (((CaseTraversable) cible).getContenu().resistance <= 0)
                    ((CaseTraversable) cible).vide();
                return;
            }
        }
        this.dir = Direction.random();
    }

    @Override
    String toString(String background) {
        return background.charAt(0) + switch (dir) {
            case nord -> "^";
            case sud -> "v";
            case est -> ">";
            case ouest -> "<";
        } + background.charAt(2);
    }
}
