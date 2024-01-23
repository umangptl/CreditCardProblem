package org.example.card.Ctype;

import org.example.card.Ctype.CreditCard;
import org.example.card.Ctype.InvalidCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvalidCardtest {

    @Test
    public void testInvalidCCValidity() {
        CreditCard invalidCC = new InvalidCard("", "");
        assertTrue(invalidCC.isValid());
        assertEquals("Invalid: empty/null card number", invalidCC.getCardType());

        CreditCard invalidCC2 = new InvalidCard("adjndwjkcndn", "");
        assertTrue(invalidCC2.isValid());
        assertEquals("Invalid: non-numeric characters", invalidCC2.getCardType());

        CreditCard invalidCC3 = new InvalidCard("0000000000000000000000000", "");
        assertTrue(invalidCC3.isValid());
        assertEquals("Invalid: more than 19 digits", invalidCC3.getCardType());

        CreditCard invalidCC4 = new InvalidCard("9090909090", "");
        assertTrue(invalidCC4.isValid());
        assertEquals("Invalid: less than 13 digits", invalidCC4.getCardType());

        CreditCard invalidCC5 = new InvalidCard("3601112345678789", "");
        assertFalse(invalidCC5.isValid());
        assertEquals("Invalid: not a possible card number", invalidCC5.getCardType());

    }
}
