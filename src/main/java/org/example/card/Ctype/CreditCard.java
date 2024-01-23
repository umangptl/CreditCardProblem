package org.example.card.Ctype;

public abstract class CreditCard {
    private final String cardNumber;
    private String cardType;

    public CreditCard(String cardNumber, String cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }
    public abstract boolean isValid();

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}