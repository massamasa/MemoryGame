/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author mattiost
 */
public class Card {
    private boolean checked;
    private boolean found;
    private int cardNumber;


    public int getCardNumber() {
        return cardNumber;
    }

    public Card(int cardNumber) {
        this.cardNumber = cardNumber;
        this.checked = false;
        this.found = false;
    }
    
    public boolean isChecked() {
        return checked;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean hasBeenFound) {
        this.found = hasBeenFound;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    
    
}
