import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    Terrain terrain;
    int sortis;

    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donné en paramètre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
        this.sortis = 0;
    }

    public void tour(int hauteur,int largeur) {
        Random random = new Random();
        ArrayList<Case> entityCases = new ArrayList<>();
        for (Case[] cases : terrain.getCarte()) {
            for (Case c : cases) {
                if (c instanceof CaseTraversable && ! c.estLibre()) {
                    if (((CaseTraversable)c).getContenu() instanceof Obstacle) continue;
                    entityCases.add(c);
                }
            }
        }
        int rand = random.nextInt(entityCases.size());


        
    }

    public static void main(String[] args) {
        Jeu j = new Jeu("laby1.txt");
        j.terrain.print();
    }
}
