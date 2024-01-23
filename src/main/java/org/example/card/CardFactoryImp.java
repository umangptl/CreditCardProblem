package org.example.card;

import org.example.card.Ctype.*;

public class CardFactoryImp implements CardFactory {
    public CreditCard createCard(String cardNumber, String cardType) {

            // Identify Invalid cards rules first
        if (cardNumber == null || cardNumber.isEmpty()|| cardNumber.isBlank()) {
            return new InvalidCard(cardNumber,"Invalid: empty/null card number");
        }else if (!cardNumber.matches("^[0-9]+$")) {
            return new InvalidCard(cardNumber, "Invalid: non-numeric characters");
        }else if (cardNumber.length() > 19) {
            return new InvalidCard(cardNumber, "Invalid: more than 19 digits");
        }else if (cardNumber.length() < 13) {
            return new InvalidCard(cardNumber, "Invalid: less than 13 digits");
        }

        try {
            // Identify valid cards rules
            if (cardNumber.length() == 16 && (cardNumber.charAt(0) == '5' && cardNumber.charAt(1) >= '1' && cardNumber.charAt(1) <= '5')) {
                return new MasterCC(cardNumber, cardType);
            }
            else if (cardNumber.length() == 16 && cardNumber.startsWith("6011")) {
                return new DiscoverCC(cardNumber, cardType);
            }
            else if ((cardNumber.length() == 16 || cardNumber.length() == 13) && cardNumber.charAt(0) == '4') {
                    return new VisaCC(cardNumber, cardType);
            }
            else if (cardNumber.length() == 15 && cardNumber.charAt(0) == '3' && (cardNumber.charAt(1) == '4' || cardNumber.charAt(1) == '7')) {
                    return new AmExCC(cardNumber, cardType);
            }

        } catch (Exception e) {
            // Handle any unexpected errors while processing the card
            return new InvalidCard(cardNumber, "Invalid: error while processing card");
        }

        // Default case for an invalid card number
        return new InvalidCard(cardNumber, "Invalid: not a possible card number");
    }
}
