package org.example.card.Ctype;

public class InvalidCard extends CreditCard {
    public InvalidCard(String cardNumber, String cardType) {
        super(cardNumber, cardType);
        if(isValid()){
            this.setCardType(cardType);
        }
        else{
            this.setCardType("Invalid: not a possible card number");
        }
    }

    @Override
    public boolean isValid() {
        String cardNumber = getCardNumber();
        int cardLength = cardNumber.length();
        if (cardLength == 0) {
            this.setCardType("Invalid: empty/null card number");
            return true;
        } else if (!cardNumber.matches("^[0-9]+$")) {
            this.setCardType("Invalid: non-numeric characters");
            return true;
        } else if (cardNumber.length() > 19) {
            this.setCardType("Invalid: more than 19 digits");
            return true;
        } else if (cardNumber.length() < 13) {
            this.setCardType("Invalid: less than 13 digits");
            return true;
        }
        return false;
    }

}