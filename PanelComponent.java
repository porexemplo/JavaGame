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
    public Terrain getTerrain(){
        return this.terrain;
    }

    public void draw(Graphics g){

        for (Case[] cases : this.terrain.getCarte()) {
            for (Case c : cases){
                if (c instanceof CaseIntraversable){
                    g.setColor(Color.BLACK);
                    g.fillRect(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                }
                if (c instanceof Sortie){
                    g.setColor(Color.blue);
                    g.fillRect(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                    g.setColor(Color.white);
                    g.fillOval(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                }

                if ( c instanceof CaseTraversable){
                    if (((CaseTraversable)c).getContenu() instanceof Obstacle){
                        g.setColor(Color.gray);
                        g.fillOval(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);


                    }
                    if (((CaseTraversable)c).getContenu() instanceof Personnage){
                        g.setColor(Color.green);
                        g.fillOval(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                        g.setColor(Color.black);
                        Direction dir = ((EntiteMobile)((CaseTraversable)c).getContenu()).getDirection();
                        drawLittleEye(g, c,dir ); 

                    }
                    if (((CaseTraversable)c).getContenu() instanceof Monstre){
                        g.setColor(Color.red);
                        g.fillOval(c.col*tailleCase, c.lig*tailleCase, tailleCase, tailleCase);
                        g.setColor(Color.black);
                        Direction dir = ((EntiteMobile)((CaseTraversable)c).getContenu()).getDirection();
                        drawLittleEye(g, c,dir );
                        

                    }

                }
            }
        }
    }

    // ouest g.fillOval(c.lig*tailleCase, c.col*tailleCase+(tailleCase/4),(tailleCase/3),(tailleCase/3));
    //nord  g.fillOval(c.lig*tailleCase+(tailleCase/4), c.col*tailleCase,(tailleCase/3),(tailleCase/3))
    //sud g.fillOval(c.lig*tailleCase+((tailleCase/3)), c.col*tailleCase+((tailleCase/2)+2),(tailleCase/3),(tailleCase/3));
    //est  g.fillOval(c.lig*tailleCase+((tailleCase/2)+3), c.col*tailleCase+((tailleCase/2)-4),(tailleCase/3),(tailleCase/3));
    
    public void drawLittleEye(Graphics g, Case c, Direction dir){
            switch (dir) {
                case est -> g.fillOval(c.col*tailleCase+((tailleCase/2)+3), c.lig*tailleCase+((tailleCase/2)-4),(tailleCase/3),(tailleCase/3));
                case ouest ->g.fillOval(c.col*tailleCase, c.lig*tailleCase+(tailleCase/4),(tailleCase/3),(tailleCase/3));
                case sud -> g.fillOval(c.col*tailleCase+(tailleCase/4), c.lig*tailleCase,(tailleCase/3),(tailleCase/3));
                case nord -> g.fillOval(c.col*tailleCase+((tailleCase/3)), c.lig*tailleCase+((tailleCase/2)+2),(tailleCase/3),(tailleCase/3));
            }
          
    }
    //public void setPlayerDirection(Direction dir) { terrain.Joueur.dir = dir; }

    public void drawJoueuer(Graphics g){
        g.setColor(Color.pink);
        g.fillOval(this.terrain.Joueur.col*tailleCase, this.terrain.Joueur.lig*tailleCase, tailleCase, tailleCase);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        drawJoueuer(g);
    }

    public void actionJoueur(Case courante, Case cible){
        if ( cible instanceof CaseIntraversable){
            return;
        }
        if ( cible instanceof CaseTraversable){
            if ((((CaseTraversable) cible).getContenu() instanceof Obstacle) || (((CaseTraversable) cible).getContenu() instanceof Monstre)) {
                return;
            }
        }
        if ( cible.estLibre()){
            cible = courante;
            ((CaseTraversable) courante).vide();
        }
}

     public void movePlayer(Direction dir){

        switch (dir) {
            case nord -> {
                if ( (terrain.getCarte()[terrain.Joueur.lig-1][terrain.Joueur.col] instanceof CaseIntraversable)){
                    terrain.Joueur.lig = terrain.Joueur.lig;
                    }
                if (terrain.getCarte()[terrain.Joueur.lig-1][terrain.Joueur.col].estLibre()){
                    
                    terrain.Joueur.lig = terrain.Joueur.lig - 1;
                    terrain.getCarte()[terrain.Joueur.lig-1][terrain.Joueur.col]=(terrain.Joueur);
                    ((CaseTraversable) terrain.getCarte()[terrain.Joueur.lig][terrain.Joueur.col]).vide();
                }

            }
            case sud -> {
                if (terrain.getCarte()[terrain.Joueur.lig+1][terrain.Joueur.col].estLibre()){
                    
                    terrain.Joueur.lig = terrain.Joueur.lig + 1;
                    terrain.getCarte()[terrain.Joueur.lig+1][terrain.Joueur.col]=(terrain.Joueur);
                    ((CaseTraversable) terrain.getCarte()[terrain.Joueur.lig][terrain.Joueur.col]).vide();
                }

            }
            case ouest -> {
                if (terrain.getCarte()[terrain.Joueur.lig][terrain.Joueur.col-1].estLibre()){
                    
                    terrain.Joueur.col = terrain.Joueur.col-1 ;
                    terrain.getCarte()[terrain.Joueur.lig][terrain.Joueur.col-1]=(terrain.Joueur);
                    ((CaseTraversable) terrain.getCarte()[terrain.Joueur.lig][terrain.Joueur.col-1]).vide();
                }

            }
            case est -> {
                if (terrain.getCarte()[terrain.Joueur.lig][terrain.Joueur.col+1].estLibre()){
                    
                    terrain.Joueur.col = terrain.Joueur.col + 1;
                    terrain.getCarte()[terrain.Joueur.lig][terrain.Joueur.col+1]=(terrain.Joueur);
                    ((CaseTraversable) terrain.getCarte()[terrain.Joueur.lig][terrain.Joueur.col+1]).vide();
                }

            }
            
        }
    } 


}
