package domain;

import java.util.ArrayList;
import java.util.Collections;

public class Logic {

    private int[][] integer2DArray;
    private int dimension;
    private int previousX;
    private int previousY;
    private int pairsRight;
    private boolean same;
    private ArrayList<Integer> foundPairs;
    private StringBuilder pairSb;
    private int penalty;
    private final boolean[][] boolean2DArray;

    public Logic(int dimension) {
        this.pairSb = new StringBuilder("Found: ");
        this.foundPairs = new ArrayList<>();
        this.previousX = -2;
        this.previousY = -2;
        this.dimension = dimension;
        this.integer2DArray = createRectangular2DIntegerArray();
        this.boolean2DArray = new boolean[dimension][dimension];
    }

    public int getCardCheckedPenalty() {
        return penalty;
    }

    public boolean foundAllPairs() {
        return foundPairs.size() == dimension * dimension / 2;
    }

    public int getIntegerFromInteger2DArray(int x, int y) {
        return integer2DArray[y][x];
    }

    public ArrayList<Integer> createCards() {
        ArrayList<Integer> integerList = new ArrayList<>();

        for (int i = 1; integerList.size() < dimension * dimension; i++) {
            integerList.add(i);
            integerList.add(i);
        }

        return integerList;
    }

    public int[][] createRectangular2DIntegerArray() {
        ArrayList<Integer> cardList = createCards();
        Collections.shuffle(cardList);

        int[][] newInteger2DArray = new int[dimension][dimension];
        int i = 0;
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                newInteger2DArray[y][x] = cardList.get(i);
                i++;
            }
        }
        return newInteger2DArray;
    }

    public boolean identicalCardToPreviousButNotSame(int x, int y) {
        if (sameAsPrevious(x, y)) {
            return false;
        }
        if (this.previousX >= 0 && this.previousY >= 0) {
            int succ = this.integer2DArray[y][x];
            int prev = this.integer2DArray[previousY][previousX];
            if (succ == prev) {
                changePreviousXY(-1, -1);
                this.foundPairs.add(succ);
                pairSb.append(succ + ", ");
                return true;
            } else if (boolean2DArray[y][x]) {
                penalty++;
            }
        }
        this.boolean2DArray[y][x] = true;
        changePreviousXY(x, y);
        return false;
    }

    private void changePreviousXY(int x, int y) {
        this.previousX = x;
        this.previousY = y;
    }

    public String foundPairsString() {
        return pairSb.toString();
    }

    public boolean sameAsPrevious(int x, int y) {
        if (this.previousX == x && this.previousY == y) {
            return true;
        }
        return false;
    }

}
