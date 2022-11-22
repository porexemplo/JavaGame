public class Jeu {
    Terrain terrain;
    int sortis;

    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donné en paramètre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
        this.sortis = 0;
    }

    public void tour() {
        // TODO: Implement void tour()
    }

    public static void main(String[] args) {
        Jeu j = new Jeu("laby1.txt");
        j.terrain.print();
    }
}
