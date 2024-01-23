package org.example.card.Ctype;

public class AmExCC extends CreditCard {
    public AmExCC(String cardNumber, String cardType) {
        super(cardNumber, cardType);
        if(isValid()){
            this.setCardType("American Express");
        }
        else{
            this.setCardType("Invalid not American Express");
        }
    }

    @Override
    public boolean isValid() {
        String cardNumber = getCardNumber();
        int cardLength = cardNumber.length();
        char firstDigit = cardNumber.charAt(0);
        char secondDigit = cardNumber.charAt(1);

        if (cardLength == 15 && firstDigit == '3' && (secondDigit == '4' || secondDigit == '7')) {
            return true;
        }
        return false;
    }

}