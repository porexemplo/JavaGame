import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    Terrain terrain;
    int sortis;
    static boolean isFinished = true;

    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donné en paramètre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
        this.sortis = 0;
        
    }

    public void tour() {
        ArrayList<CaseTraversable> entityCases = new ArrayList<>();
        for (Case[] cases : terrain.getCarte()) {
            for (Case c : cases) {
                if (c instanceof CaseTraversable && ! c.estLibre()) {
                    if (((CaseTraversable)c).getContenu() instanceof Obstacle) continue;
                    if (((CaseTraversable)c).getContenu() instanceof Joueur) continue;
                    if (((CaseTraversable)c).getContenu() instanceof Personnage) {isFinished = false;}
                    if (((CaseTraversable)c).getContenu() instanceof EntiteMobile)
                    entityCases.add(((CaseTraversable) c));
                    
                }
            }
        }
        for (CaseTraversable e: entityCases){
            if ((EntiteMobile) e.getContenu()== null) continue;
            ((EntiteMobile) e.getContenu()).action(e,terrain.getNextCase(e));

        }

    }

    public static void main(String[] args) throws InterruptedException {
        Jeu j = new Jeu("laby1.txt");
        j.tour();
        while(!isFinished){
            Thread.sleep(500);
            j.terrain.print();
            j.tour();
        }
    }
}
