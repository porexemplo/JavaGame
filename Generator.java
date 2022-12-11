import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
    protected static class Cell {
        protected int x, y;
        protected boolean isVisited;

        public Cell(int x, int y) { this.x = x; this.y = y; isVisited = false; }
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

        public boolean isInside(int x, int y) { return x < size && y < size && x > 0 && y > 0; }

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

    static void shuffleArray(int[] intArray) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = intArray.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = intArray[index];
            intArray[index] = intArray[i];
            intArray[i] = temp;
        }
    }

    public boolean addNext(Cell cell) {
        ArrayList<Cell> neighbours = output.getCellNeighboors(cell);
        int[] indexArray = new int[neighbours.size()];
        for (int i = 0; i < neighbours.size(); i++) indexArray[i] = i;

        shuffleArray(indexArray);
        for (int i : indexArray) {
            if (neighbours.get(i).isVisited) continue;
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
    }

    private void resetCurrentPosition() { currentPosition = visitedCells.size()-1; }

    private Cell getPrevious() { return visitedCells.get(currentPosition--); }

    public Generator(int size, double noise) {
        output = new Map(size, noise);
        visitedCells = new ArrayList<>();
        currentPosition = 0;
    }

    public void printMap() {
        for (Cell[] line : output.cellArray) {
            for (Cell cell : line) {
                if (cell.isVisited) System.out.print("=");
                else System.out.print("0");
            }
            System.out.println();
        }
    }
}
