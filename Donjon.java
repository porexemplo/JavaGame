import javax.swing.Timer;

public class Donjon {
    public static void main(String[] args) {
        int tempo = 130;
        Jeu jeu = new Jeu("laby2.txt");
        FenetreJeu graphic = new FenetreJeu(jeu.terrain);
        Timer timer = new Timer(tempo, e -> {
            jeu.tour();
            graphic.repaint();
            
            if (Jeu.isFinished) { graphic.ecranFinal(jeu.sortis); }
        });
        timer.setInitialDelay(0);
        timer.start();
    }
}