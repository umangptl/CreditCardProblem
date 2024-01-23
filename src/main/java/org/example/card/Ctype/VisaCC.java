package org.example.card.Ctype;

public class VisaCC extends CreditCard {
    public VisaCC(String cardNumber, String cardType) {
        super(cardNumber, cardType);
        if(isValid()){
            this.setCardType("Visa");
        }
        else{
            this.setCardType("Invalid not Visa");
        }
    }

    @Override
    public boolean isValid() {
        String cardNumber = getCardNumber();
        int cardLength = cardNumber.length();
        char firstDigit = cardNumber.charAt(0);

        if (cardLength == 13 || cardLength == 16 && firstDigit == '4') {
            return true;
        }
        return false;
    }
}