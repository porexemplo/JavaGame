import javax.swing.*;
import java.awt.*;

public class FenetreJeu extends JPanel {
    private Terrain terrain;
    private int tailleCase = 24;
    private int hauteur, largeur;
    private JFrame frame;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;

        setBackground(Color.white);
        setPreferredSize(new Dimension(largeur * tailleCase, hauteur * tailleCase));

        JFrame frame = new JFrame("Donjon");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }
    public void draw(Graphics g){
       /*  for (int i =0; i < hauteur*tailleCase; i++){
            g.drawLine(i*tailleCase, 0 , i*tailleCase, largeur*tailleCase);
            g.drawLine(0, i*tailleCase , largeur*tailleCase, i*tailleCase);
        }
        */

        for (Case[] cases : this.terrain.getCarte()) {
            for (Case c : cases){
                if (c instanceof CaseIntraversable){
                    g.setColor(Color.BLACK);
                    g.fillRect(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);
                }
                if (c instanceof Sortie){
                    g.setColor(Color.blue);
                    g.fillRect(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);
                }

                if ( c instanceof CaseTraversable){
                    if (((CaseTraversable)c).getContenu() instanceof Obstacle){
                        g.setColor(Color.gray);
                        g.fillOval(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);
                        
                    }
                    if (((CaseTraversable)c).getContenu() instanceof Personnage){
                        g.setColor(Color.green);
                        g.fillOval(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);

                    }
                    if (((CaseTraversable)c).getContenu() instanceof Monstre){
                        g.setColor(Color.red);
                        g.fillOval(c.lig*tailleCase, c.col*tailleCase, tailleCase, tailleCase);

                    }
                }
            }
        
        }
            


                    

        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }
}