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

    /**
     * GameBoard with integers as cards. Contains the logic required in the
     * gameStage
     *
     * @see ui.GameStage
     * @param dimension the rectangular dimensions of the table of cards. 2,4 or
     * 6
     */
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

    /**
     * A method for creating a list of card objects to be used in the card Array
     *
     * @return
     */
    protected ArrayList<Card> createCards() {
        ArrayList<Card> cardList = new ArrayList<>();

        for (int i = 1; cardList.size() < dimension * dimension; i++) {
            cardList.add(new Card(i));
            cardList.add(new Card(i));
        }

        return cardList;
    }

    private Card[][] createRectangular2DCardArray() {
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

    /**
     * Checks if the cards in the previously and currently specified different
     * coordinates are correspondin by card number
     *
     * @param x
     * @param y
     * @return true if match
     * @return false if different or the same coordinate is entered
     */
    public boolean matchingCardInDifferentCoordinate(int x, int y) {
        if (sameAsPrevious(x, y)) {
            return false;
        }
        if (this.previousX >= 0 && this.previousY >= 0) {
            Card succeedingCard = this.card2DArray[y][x];
            int succ = succeedingCard.getCardNumber();
            Card previousCard = this.card2DArray[previousY][previousX];
            int prev = previousCard.getCardNumber();
            if (succ == prev) {
                changePreviousXY(-1, -1);
                this.foundPairs.add(succ);
                pairSb.append(succeedingCard.getCardName() + ", ");
                return true;
            } else if (card2DArray[y][x].hasBeenCheckedBefore()) {
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

    /**
     *
     * @return String containing all the pairs' names that have been discovered,
     * in order of discovery
     */
    public String foundPairsString() {
        return pairSb.toString();
    }

    private boolean sameAsPrevious(int x, int y) {
        if (this.previousX == x && this.previousY == y) {
            return true;
        }
        return false;
    }

}
