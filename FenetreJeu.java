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
                panel.setPlayerDirection(Direction.nord); panel.movePlayer();
            }
            case KeyEvent.VK_DOWN -> {
                panel.setPlayerDirection(Direction.sud); panel.movePlayer();
            }
            case KeyEvent.VK_LEFT -> {
                panel.setPlayerDirection(Direction.ouest); panel.movePlayer();
            }
            case KeyEvent.VK_RIGHT -> {
                panel.setPlayerDirection(Direction.est); panel.movePlayer();
            }
        }
        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}