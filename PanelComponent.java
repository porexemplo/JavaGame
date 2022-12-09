import javax.swing.*;
import java.awt.*;

public class PanelComponent extends JPanel {
    private final Terrain terrain;
    private final int tailleCase = 24;

    public PanelComponent(Terrain t) {
        this.terrain = t;
        setBackground(Color.white);
        setPreferredSize(new Dimension(terrain.getHauteur() * tailleCase, terrain.getLargeur() * tailleCase));
    }

    public void draw(Graphics g) {
        for (Case[] cases : this.terrain.getCarte()) {
            for (Case c : cases) {
                if (c instanceof CaseIntraversable) {
                    g.setColor(Color.BLACK);
                    g.fillRect(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                }
                if (c instanceof Sortie) {
                    g.setColor(Color.blue);
                    g.fillRect(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                    g.setColor(Color.white);
                    g.fillOval(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                }

                if (c instanceof CaseTraversable) {
                    if (((CaseTraversable) c).getContenu() instanceof Obstacle) {
                        g.setColor(Color.gray);
                        g.fillOval(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);


                    }
                    if (((CaseTraversable) c).getContenu() instanceof Personnage) {
                        g.setColor(Color.green);
                        g.fillOval(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                        g.setColor(Color.black);
                        Direction dir = ((EntiteMobile) ((CaseTraversable) c).getContenu()).getDirection();
                        drawLittleEye(g, c, dir);

                    }
                    if (((CaseTraversable) c).getContenu() instanceof Monstre) {
                        g.setColor(Color.red);
                        g.fillOval(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                        g.setColor(Color.black);
                        Direction dir = ((EntiteMobile) ((CaseTraversable) c).getContenu()).getDirection();
                        drawLittleEye(g, c, dir);
                    }
                    if (((CaseTraversable) c).getContenu() instanceof  Joueur) {
                        g.setColor(Color.pink);
                        g.fillOval(c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase);
                    }
                }
            }
        }
    }

    public void drawLittleEye(Graphics g, Case c, Direction dir) {
        switch (dir) {
            case est ->
                    g.fillOval(c.col * tailleCase + ((tailleCase / 2) + 3), c.lig * tailleCase + ((tailleCase / 2) - 4), (tailleCase / 3), (tailleCase / 3));
            case ouest ->
                    g.fillOval(c.col * tailleCase, c.lig * tailleCase + (tailleCase / 4), (tailleCase / 3), (tailleCase / 3));
            case sud ->
                    g.fillOval(c.col * tailleCase + (tailleCase / 4), c.lig * tailleCase, (tailleCase / 3), (tailleCase / 3));
            case nord ->
                    g.fillOval(c.col * tailleCase + ((tailleCase / 3)), c.lig * tailleCase + ((tailleCase / 2) + 2), (tailleCase / 3), (tailleCase / 3));
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
