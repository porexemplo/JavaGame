import java.io.FileInputStream;
import java.io.IOException;
/* import java.sql.SQLOutput; */
import java.util.Scanner;

public class Terrain {

    private int hauteur, largeur;
    private Case[][] carte;
    static boolean isFinished = false;
    protected CaseTraversable current;

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
    public Case[][] getCarte(){
        return carte;
    }
    public int getHauteur() { return hauteur; }

    public int getLargeur() { return largeur; }

    public Case getNextCase(CaseTraversable c) {
        Direction dir;
        if (c.getContenu() instanceof EntiteMobile)
            dir = ((EntiteMobile) c.getContenu()).dir;
        else
            dir = ((Joueur) c.getContenu()).dir;

        return switch (dir) {
            case nord -> carte[c.lig - 1][c.col];
            case ouest -> carte[c.lig][c.col - 1];
            case est -> carte[c.lig][c.col + 1];
            case sud -> carte[c.lig + 1][c.col];
        };
    } 
    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.largeur = sc.nextInt();
            this.hauteur = sc.nextInt();
            sc.nextLine();
            this.carte = new Case[largeur][hauteur];
            for (int l=0; l<largeur; l++) {
                String line = sc.nextLine();
                for (int c=0; c < hauteur; c++) {
                    Case cc = null;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#' -> cc= new CaseIntraversable(l, c);
                        case ' ' -> cc= new CaseLibre(l, c);
                        case 'o' -> cc=new Sortie(l, c);
                        case '@' -> cc= new CaseLibre(l, c, new Obstacle());
                        case '^', '>', 'v', '<' ->
                        cc = new CaseLibre(l, c, new Personnage(Direction.ofChar(ch)));
                        case 'm', '»', 'w', '«' ->
                        cc = new CaseLibre(l, c, new Monstre(Direction.ofChar(ch)));
                        case 'H' -> {
                            cc = new CaseTraversable(l, c, new Joueur());
                            current = (CaseTraversable) cc;
                        }
                    };
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
   

    public void print() {
        for (Case[] cases : carte) {
            for (Case c : cases)
                System.out.print(c.toString());
            System.out.println();
        }
    }
}
