import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread.sleep;

public class Jeu {
    Terrain terrain;
    int sortis;
    static boolean isFinnished = true;

    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donné en paramètre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
        this.sortis = 0;
    }

    public void tour() {
        Random random = new Random();
        ArrayList<CaseTraversable> entityCases = new ArrayList<>();
        for (Case[] cases : terrain.getCarte()) {
            for (Case c : cases) {
                if (c instanceof CaseTraversable && ! c.estLibre()) {
                    if (((CaseTraversable)c).getContenu() instanceof Obstacle) continue;
                    if (((CaseTraversable)c).getContenu() instanceof Personnage) isFinnished = false;
                    entityCases.add(((CaseTraversable) c));
                    
                }
            }
        }
        int i = random.nextInt(entityCases.size());

        ((EntiteMobile) entityCases.get(i).getContenu()).action(entityCases.get(i),
                terrain.getNextCase(entityCases.get(i)));
    }

    public static void main(String[] args) {
        Jeu j = new Jeu("laby1.txt");
        while(!isFinnished){
            j.tour();
        
            j.terrain.print();
        }
    }
}
