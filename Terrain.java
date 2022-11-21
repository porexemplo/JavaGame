import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Terrain {

    private int hauteur, largeur;
    private Case[][] carte;

    /* Initialisation d'un terrain à partir de la description donnée par
       un fichier texte. Format du fichier de description :
       - hauteur et largeur sur la première ligne
       - puis dessin du terrain (un caractère == une case) avec le codage
         suivant
         '#' pour un mur
         ' ' (espace) pour une case libre
         'o' pour une sortie
         '@' pour une case libre contenant un obstacle
         '^', 'v', '>', '<' pour une case libre contenant un personnage
         'm', 'w', '»', '«' pour une case libre contenant un monstre
    */
    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            this.carte = new Case[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    cc = switch (ch) {
                        case '#' -> new CaseIntraversable(l, c);
                        case ' ' -> new CaseLibre(l, c);
                        case 'o' -> new Sortie(l, c);
                        case '@': cc = new CaseLibre(l, c, new Obstacle()); break;
                        case '^': case '>': case 'v': case '<':
                            cc = new CaseLibre(l, c, new Personnage(Direction.ofChar(ch)));
                            break;
                        case 'm': case '»': case 'w': case '«':
                            cc = new CaseLibre(l, c, new Monstre(Direction.ofChar(ch)));
                            break;
                        default -> null;
                    };
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void print() {
        for (Case[] cases : carte)
            for (Case c : cases)
                System.out.println(c);
    }
}
