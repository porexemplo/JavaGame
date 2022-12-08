import javax.swing.*;
import java.awt.*;

public class PanelComponent extends JPanel {
    private final Terrain terrain;
    private final int tailleCase = 24;

    public PanelComponent(Terrain t) {
        int hauteur = t.getHauteur();
        int largeur = t.getLargeur();
        this.terrain = t;

        setBackground(Color.white);
        setPreferredSize(new Dimension(hauteur * tailleCase, largeur * tailleCase));
    }

    public void draw(Graphics g){

        for (Case[] cases : this.terrain.getCarte()) {
            for (Case c : cases){
                if (c instanceof CaseIntraversable){
                    g.setColor(Color.BLACK);
                    g.fillRect(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);
                }
                if (c instanceof Sortie){
                    g.setColor(Color.blue);
                    g.fillRect(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);
                    g.setColor(Color.white);
                    g.fillOval(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);
                }

                if ( c instanceof CaseTraversable){
                    if (((CaseTraversable)c).getContenu() instanceof Obstacle){
                        g.setColor(Color.gray);
                        g.fillOval(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);


                    }
                    if (((CaseTraversable)c).getContenu() instanceof Personnage){
                        g.setColor(Color.green);
                        g.fillOval(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);
                        g.setColor(Color.black);
                        g.fillOval(c.lig*tailleCase, c.col*tailleCase+(tailleCase/4),(tailleCase/3),(tailleCase/3));

                    }
                    if (((CaseTraversable)c).getContenu() instanceof Monstre){
                        g.setColor(Color.red);
                        g.fillOval(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);
                        g.setColor(Color.black);
                        g.fillOval(c.lig*tailleCase, c.col*tailleCase+(tailleCase/4),(tailleCase/3),(tailleCase/3));

                    }

                }
            }
        }
    }

    public void setPlayerDirection(Direction dir) { terrain.Joueur.dir = dir; }

    public void drawJoueur(Graphics g){
        g.setColor(Color.pink);
        g.fillOval(this.terrain.Joueur.col*tailleCase, this.terrain.Joueur.lig*tailleCase, tailleCase, tailleCase);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        drawJoueur(g);
    }
    public void addMovement(){
        movePlayer();
        terrain.getCarte()[terrain.Joueur.lig][terrain.Joueur.col] = new CaseLibre(terrain.Joueur.lig, terrain.Joueur.col, terrain.Joueur);
        int lig = terrain.Joueur.lig;
        int col = terrain.Joueur.col;
       /*  if (terrain.getCarte()[lig][col] instanceof CaseIntraversable){
            
    } */
}

    public void movePlayer(){
        switch (terrain.Joueur.dir) {
            case nord -> terrain.Joueur.lig = terrain.Joueur.lig - 1;
            case sud -> terrain.Joueur.lig = terrain.Joueur.lig + 1;
            case ouest -> terrain.Joueur.col = terrain.Joueur.col - 1;
            case est -> terrain.Joueur.col = terrain.Joueur.col + 1;
        }
    }
}
