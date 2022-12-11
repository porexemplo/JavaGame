import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    protected static class Cell {
        protected int x, y;
        protected boolean isVisited;

        /* Content description
        * 0 -> Empty
        * 1 -> Joueur
        * 2 -> Personnage
        * 3 -> Monstre
        * 4 -> Sortie
        */
        int content;

        public Cell(int x, int y) { this.x = x; this.y = y; isVisited = false; content = 0; }

        public void setContent(int content) { this.content = content; }
        public void visit() { isVisited = true; }
    }

    protected static class Map {
        protected int size;
        protected double noise;
        protected  Cell[][] cellArray;

        public Map(int size, double noise) {
            this.size = size; this.noise = noise;
            cellArray = new Cell[size][size];
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    cellArray[i][j] = new Cell(i, j);
        }
        public Cell getCell(int x, int y) { return cellArray[x][y]; }

        public Cell getRandomCell() {
            Random r = new Random();
            int x = r.nextInt(0, size-1),
                y = r.nextInt(0, size-1);
            return getCell(x, y);
        }

        public boolean isInside(int x, int y) { return x < size && y < size && x >= 0 && y >= 0; }

        public ArrayList<Cell> getCellNeighboors(Cell cell) {
            int x = cell.x, y = cell.y;
            ArrayList<Cell> neighbours = new ArrayList<>();
            if (isInside(x + 1, y)) neighbours.add(getCell(x + 1, y));
            if (isInside(x - 1, y)) neighbours.add(getCell(x - 1, y));
            if (isInside(x, y + 1)) neighbours.add(getCell(x, y + 1));
            if (isInside(x, y - 1)) neighbours.add(getCell(x, y - 1));
            return neighbours;
        }
    }

    private Map output;
    protected ArrayList<Cell> visitedCells;
    protected int currentPosition;
    protected int personaCount, monsterCount;
    PrintStream out = new PrintStream(new FileOutputStream("output.txt"));

    static int[] shuffleArray(int[] intArray) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = intArray.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = intArray[index];
            intArray[index] = intArray[i];
            intArray[i] = temp;
        }
        return intArray;
    }

    public boolean addNext(Cell cell) {
        ArrayList<Cell> neighbours = output.getCellNeighboors(cell);
        int[] indexArray = new int[neighbours.size()];
        for (int i = 0; i < neighbours.size(); i++) indexArray[i] = i;

        shuffleArray(indexArray);
        for (int i : indexArray) {
            if (neighbours.get(i).isVisited) {
                continue;
            }
            neighbours.get(i).visit(); visitedCells.add(neighbours.get(i)); currentPosition++; return true;
        }
        return false;
    }

    public void generateMap() {
        Cell currentCell = output.getRandomCell();
        currentCell.visit();
        for (int i = 0; i < (output.noise*output.size*output.size); i++) {
            while (! addNext(currentCell))
                currentCell = getPrevious();
            resetCurrentPosition();
        }
        setCellsContent();
    }

    private void resetCurrentPosition() { currentPosition = visitedCells.size()-1; }

    private Cell getPrevious() { return visitedCells.get(--currentPosition); }

    public Generator(int size, double noise, int monsterCount, int personaCount) throws FileNotFoundException {
        output = new Map(size, noise);
        visitedCells = new ArrayList<>();
        currentPosition = 0;
        this.monsterCount = monsterCount; this.personaCount = personaCount;
    }

    public void setCellsContent() {
        int[] indexArray = new int[monsterCount+personaCount+2];
        for (int i = 0; i < indexArray.length; i++) indexArray[i] = i;
        shuffleArray(indexArray);
        int gamerIndex = indexArray[0];
        int[] monsterIndexArray = Arrays.copyOfRange(indexArray, 1, 1+monsterCount);
        int[] personaIndexArray = Arrays.copyOfRange(indexArray, monsterCount+1, personaCount+monsterCount+1);
        int sortieIndex = indexArray[monsterCount+personaCount+1];
        visitedCells.get(gamerIndex).setContent(1);
        for (int i = 0; i < personaCount; i++)
            visitedCells.get(personaIndexArray[i]).setContent(2);
        for (int i = 0; i < monsterCount; i++)
            visitedCells.get(monsterIndexArray[i]).setContent(3);
        visitedCells.get(sortieIndex).setContent(4);
    }

    public void printMap() {
        System.setOut(out); Random rnd = new Random();
        System.out.println((output.size+2) + " " + (output.size+2));
        System.out.println(new String(new char[output.size+2]).replace('\0', '#'));
        for (Cell[] line : output.cellArray) {
            System.out.print("#");
            for (Cell cell : line) {
                if (cell.isVisited) {
                    switch (cell.content) {
                        case 0 -> System.out.print(" ");
                        case 1 -> System.out.print("H");
                        case 2 -> System.out.print("m");
                        case 3 -> System.out.print("<");
                        case 4 -> System.out.print("o");
                    }
                }
                else System.out.print("#");
            }
            System.out.println('#');
        }
        System.out.println(new String(new char[output.size+2]).replace('\0', '#'));
    }
}
