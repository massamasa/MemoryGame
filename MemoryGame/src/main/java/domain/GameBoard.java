package domain;

import java.util.ArrayList;
import java.util.Collections;

public class GameBoard {

    private int[][] integer2DArray;
    protected int dimension;
    private int previousX;
    private int previousY;
    private int pairsRight;
    private boolean same;
    private ArrayList<Integer> foundPairs;
    private StringBuilder pairSb;
    private int penalty;
    private final Card[][] card2DArray;

    public GameBoard(int dimension) {
        this.pairSb = new StringBuilder("Found: ");
        this.foundPairs = new ArrayList<>();
        this.previousX = -2;
        this.previousY = -2;
        this.dimension = dimension;
        this.card2DArray = createRectangular2DCardArray();
    }

    public int getCardCheckedPenalty() {
        return penalty;
    }

    public boolean foundAllPairs() {
        return foundPairs.size() == dimension * dimension / 2;
    }

    public int getCardIntegerFromCard2DArray(int x, int y) {
        return card2DArray[y][x].getCardNumber();
    }

    public String getCardNameFromCard2DArray(int x, int y) {
        return card2DArray[y][x].getCardName();
    }

    public ArrayList<Card> createCards() {
        ArrayList<Card> cardList = new ArrayList<>();

        for (int i = 1; cardList.size() < dimension * dimension; i++) {
            cardList.add(new Card(i));
            cardList.add(new Card(i));
        }

        return cardList;
    }

    public Card[][] createRectangular2DCardArray() {
        ArrayList<Card> cardList = createCards();
        Collections.shuffle(cardList);

        Card[][] newCard2DArray = new Card[dimension][dimension];
        int i = 0;
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                newCard2DArray[y][x] = cardList.get(i);
                i++;
            }
        }
        return newCard2DArray;
    }

    public boolean identicalCardToPreviousButNotSame(int x, int y) {
        if (sameAsPrevious(x, y)) {
            return false;
        }
        if (this.previousX >= 0 && this.previousY >= 0) {
            int succ = this.card2DArray[y][x].getCardNumber();
            int prev = this.card2DArray[previousY][previousX].getCardNumber();
            if (succ == prev) {
                changePreviousXY(-1, -1);
                this.foundPairs.add(succ);
                pairSb.append(succ + ", ");
                return true;
            } else if (card2DArray[y][x].isChecked()) {
                penalty++;
            }
        }
        this.card2DArray[y][x].setChecked(true);
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
