package domain;

public class Card {

    private boolean checked;
    private boolean found;
    private int cardNumber;
    private String name;

    /**
     * Card object with the specified card number as its identifying integer and
     * name
     *
     * @param cardNumber identifying integer for logic purposes and also the
     * value shown on the face of the card
     */
    public Card(int cardNumber) {
        this.name = cardNumber + "";
        this.cardNumber = cardNumber;
        this.checked = false;
        this.found = false;
    }

    /**
     * Card object with the specified card number as its identifying integer and
     * a String as its name
     *
     * @param cardNumber identifying integer for logic purposes
     * @param name String shown on the face of the card
     */
    public Card(int cardNumber, String name) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.checked = false;
        this.found = false;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public boolean hasBeenCheckedBefore() {
        return checked;
    }

    public boolean hasBeenFound() {
        return found;
    }

    public void setFound(boolean hasBeenFound) {
        this.found = hasBeenFound;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getCardName() {
        return name;
    }

}
