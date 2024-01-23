package org.example.card.Ctype;

public class MasterCC extends CreditCard {
    public MasterCC(String cardNumber, String cardType) {
        super(cardNumber, cardType);
        if(isValid()){
            this.setCardType("MasterCard");
        }
        else{
            this.setCardType("Invalid not MasterCard");
        }
    }

    @Override
    public boolean isValid() {
        String cardNumber = getCardNumber();
        int cardLength = cardNumber.length();
        char firstDigit = cardNumber.charAt(0);
        char secondDigit = cardNumber.charAt(1);

        if (cardLength == 16 && firstDigit == '5' && secondDigit >= '1' && secondDigit <= '5') {
            return true;
        }
        return false;
    }

}