package org.example.card;

import org.example.card.Ctype.CreditCard;

public interface CardFactory {
    CreditCard createCard (String card_number, String card_type);
}