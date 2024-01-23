package org.example.card;

import static org.junit.Assert.assertEquals;

import org.example.card.Ctype.CreditCard;
import org.junit.Test;

public class CardFactoryImpTest {
    @Test
    public void testCreateCard() {
        CreditCard actualCreateCardResult = (new CardFactoryImp()).createCard("42", "Card Type");
        assertEquals("42", actualCreateCardResult.getCardNumber());
        assertEquals("Invalid: less than 13 digits", actualCreateCardResult.getCardType());
    }

    @Test
    public void testCreateCard3() {
        CreditCard actualCreateCardResult = (new CardFactoryImp()).createCard("foo", "foo");
        assertEquals("Invalid: non-numeric characters", actualCreateCardResult.getCardType());
        assertEquals("foo", actualCreateCardResult.getCardNumber());
    }

    @Test
    public void testCreateCard4() {
        CreditCard actualCreateCardResult = (new CardFactoryImp()).createCard("9", "Card Type");
        assertEquals("9", actualCreateCardResult.getCardNumber());
        assertEquals("Invalid: less than 13 digits", actualCreateCardResult.getCardType());
    }

    @Test
    public void testCreateCard5() {
        CreditCard actualCreateCardResult = (new CardFactoryImp()).createCard("6011", "Card Type");
        assertEquals("6011", actualCreateCardResult.getCardNumber());
        assertEquals("Invalid: less than 13 digits", actualCreateCardResult.getCardType());
    }

    @Test
    public void testCreateCard6() {
        CreditCard actualCreateCardResult = (new CardFactoryImp()).createCard("", "Card Type");
        assertEquals("", actualCreateCardResult.getCardNumber());
        assertEquals("Invalid: empty/null card number", actualCreateCardResult.getCardType());
    }
}
