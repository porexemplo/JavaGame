import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;

public class FenetreJeu<ActionEvent> extends JPanel implements ActionListener {
    private Terrain terrain;
    private int tailleCase = 24;
    private int hauteur, largeur;
    private JFrame frame;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur()+2;
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

        this.addKeyListener(new MyKyAdapter());
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
    
    
                    
          public void drawJoueuer(Graphics g){
            g.setColor(Color.pink);
            g.fillOval(this.terrain.Joueur.lig*tailleCase, this.terrain.Joueur.col*tailleCase, tailleCase, tailleCase);
           

          }
        
        
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        drawJoueuer(g);

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
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("hey");
        repaint();
        
    }
     public void move(){
        switch(terrain.Joueur.dir){
            case nord: terrain.Joueur.lig = terrain.Joueur.lig - 24; break ;
            case sud: terrain.Joueur.lig = terrain.Joueur.lig + 24; break ;
            case ouest: terrain.Joueur.col = terrain.Joueur.col - 24; break ;
            case est: terrain.Joueur.col = terrain.Joueur.col + 24; break ;
        }
    } 

    public class MyKyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){ 
 
             switch(e.getKeyCode()){
                case KeyEvent.VK_UP: 
                        terrain.Joueur.dir = Direction.nord;
                        move();
                        repaint();
                        break;
                    

                case KeyEvent.VK_DOWN: 
                    terrain.Joueur.dir = Direction.sud;
                        move();
                        repaint();
                        break;
             }
}

}
}