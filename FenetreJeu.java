import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreJeu<ActionEvent> extends JFrame implements KeyListener {
    PanelComponent panel;

    public FenetreJeu(Terrain t) {
        setTitle("Donjon");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new PanelComponent(t);

        addKeyListener(this);
        add(panel);

        pack();
        setVisible(true);
    }
 
    public void ecranFinal(int n) {
        remove(panel);
        JLabel label = new JLabel("Score " + n);
        setFont(new Font("Verdana", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        getContentPane().add(label);
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> {
                ((Joueur) panel.terrain.current.getContenu()).setDirection(Direction.nord);
            }
            case KeyEvent.VK_DOWN -> {
                ((Joueur) panel.terrain.current.getContenu()).setDirection(Direction.sud);
            }
            case KeyEvent.VK_LEFT -> {
                ((Joueur) panel.terrain.current.getContenu()).setDirection(Direction.ouest);
            }
            case KeyEvent.VK_RIGHT -> {
                ((Joueur) panel.terrain.current.getContenu()).setDirection(Direction.est);
            }
            case KeyEvent.VK_SPACE -> {
                if (panel.terrain.current instanceof Sortie) Terrain.isFinished = true;
                return;
            }
        }
        Case nextCase = panel.terrain.getNextCase(panel.terrain.current);
        ((Joueur) panel.terrain.current.getContenu()).movePlayer(panel.terrain.current, nextCase);
        if (panel.terrain.current.getContenu() == null) panel.terrain.current = (CaseTraversable) nextCase;
        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}