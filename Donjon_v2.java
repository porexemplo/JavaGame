import javax.swing.Timer;
import java.io.FileNotFoundException;

public class Donjon_v2 {
    public static void main(String[] args) {
//        Noise: 0 -> No free space; 99% -> No obstacles
        Generator gen = null;
        try {
            gen = new Generator(15, .75, 3, 4);
        } catch (FileNotFoundException e) {
            System.out.println("File not Found !");
        }
        assert gen != null;
        gen.generateMap();
        gen.printMap();


        int tempo = 130;
        Jeu jeu = new Jeu("output.txt");
        FenetreJeu graphic = new FenetreJeu(jeu.terrain);
        Timer timer = new Timer(tempo, e -> {
            jeu.tour();
            graphic.repaint();

            if (Terrain.isFinished) { graphic.ecranFinal(jeu.sortis); }
        });
        timer.setInitialDelay(0);
        timer.start();
    }
}