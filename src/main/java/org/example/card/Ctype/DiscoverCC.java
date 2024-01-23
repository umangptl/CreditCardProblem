package org.example.card.Ctype;

public class DiscoverCC extends CreditCard {
    public DiscoverCC(String cardNumber, String cardType) {
        super(cardNumber, cardType);
        if(isValid()){
            this.setCardType("Discover");
        }
        else{
            this.setCardType("Invalid not Discover");
        }
    }

    @Override
    public boolean isValid() {
        String cardNumber = getCardNumber();
        int cardLength = cardNumber.length();
        String firstFourDigits = cardNumber.substring(0, 4);

        if (cardLength == 16 && firstFourDigits.equals("6011")) {
            return true;
        }
        return false;
    }

}