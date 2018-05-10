package domain;

import dao.CardNameArrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameBoard {

    protected int dimension;
    private int previousX;
    private int previousY;
    private int foundPairs;
    private StringBuilder pairSb;
    private int penalty;
    private final Card[][] card2DArray;
    protected long rndSeed;
    private Random random;

    /**
     * GameBoard with integers as cards. Contains the logic required in the
     * gameStage
     *
     * @see ui.GameStage
     * @param dimension rectangular dimensions of the table of cards.
     * @param randomSeed seed for generating the seemingly random Card array.
     * @param gameType Type of game signified by integer. Defaults to playing
     * with plain integers.
     */
    public GameBoard(int dimension, long randomSeed, int gameType) {
        initialiseVariables(dimension, randomSeed);
        if (gameType == 1) {
            this.card2DArray = createRectangular2DCardArray(new CardNameArrays().countryCodes());
        } else if (gameType == 2) {
            this.card2DArray = createRectangular2DCardArray(new CardNameArrays().emojiArray());
        } else {
            this.card2DArray = createRectangular2DCardArray();
        }
    }

    private void initialiseVariables(int dimension, long randomSeed) {
        this.pairSb = new StringBuilder("Found: ");
        this.foundPairs = 0;
        this.previousX = -2;
        this.previousY = -2;
        this.dimension = dimension;
        this.random = new Random(randomSeed);
    }

    public int getCardCheckedPenalty() {
        return penalty;
    }

    public boolean foundAllPairs() {
        return foundPairs >= dimension * dimension / 2;
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
     * @return ArrayList containing cards
     */
    private ArrayList<Card> cardList() {
        ArrayList<Card> cardList = new ArrayList<>();
        for (int i = 1; cardList.size() < dimension * dimension; i++) {
            cardList.add(new Card(i));
            cardList.add(new Card(i));
        }
        Collections.shuffle(cardList, random);
        return cardList;
    }

    private ArrayList<Card> cardList(String[] arrayWithCardNames) {
        ArrayList<Card> cardList = new ArrayList<>();
        String[] codes = arrayWithCardNames;
        Collections.shuffle(Arrays.asList(codes, rndSeed));
        for (int i = 1; cardList.size() < dimension * dimension; i++) {
            cardList.add(new Card(i, codes[i]));
            cardList.add(new Card(i, codes[i]));
        }
        Collections.shuffle(cardList, random);
        return cardList;
    }

    private Card[][] createRectangular2DCardArray(String[] arrayWithCardNames) {
        ArrayList<Card> cardList = cardList(arrayWithCardNames);
        Collections.shuffle(cardList, random);

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

    private Card[][] createRectangular2DCardArray() {
        ArrayList<Card> cardList = cardList();
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
     * coordinates are corresponding by card number. Adds to the penalty counter
     * if and only if 1. this card is not a match with the previous and 2. this
     * card has been checked before.
     *
     * @param x
     * @param y
     * @return true if match
     * @return false if different or the same coordinate is entered
     */
    public boolean matchingCardInDifferentCoordinate(int x, int y) {
        Card succeedingCard = this.card2DArray[y][x];
        if (sameCoordinatesAsPrevious(x, y)) {
            return false;
        }
        if (this.previousX >= 0 && this.previousY >= 0) {
            Card previousCard = this.card2DArray[previousY][previousX];
            if (succeedingCard.getCardNumber() == previousCard.getCardNumber()) {
                addToFoundPairs(succeedingCard, previousCard);
                return true;
            }
        }
        if (succeedingCard.hasBeenCheckedBefore()) {
            penalty++;
        } else {
            succeedingCard.setChecked();
        }
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

    private boolean sameCoordinatesAsPrevious(int x, int y) {
        if (this.previousX == x && this.previousY == y) {
            return true;
        }
        return false;
    }

    /**
     * @return the Card Array
     */
    public Card[][] getCard2DArray() {
        return card2DArray;
    }

    private void addToFoundPairs(Card succeedingCard, Card previousCard) {
        succeedingCard.setChecked();
        previousCard.setFound();
        succeedingCard.setFound();
        changePreviousXY(-1, -1);
        this.foundPairs++;
        this.pairSb.append(succeedingCard.getCardName()).append(", ");
    }

}
