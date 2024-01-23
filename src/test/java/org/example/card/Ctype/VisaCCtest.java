package org.example.card.Ctype;

import org.example.card.Ctype.CreditCard;
import org.example.card.Ctype.VisaCC;
import org.junit.Test;

import static org.junit.Assert.*;

public class VisaCCtest {

    @Test
    public void testVisaCCValidity() {
        CreditCard visaCard = new VisaCC("4111111111111111", "Visa");
        assertTrue(visaCard.isValid());
        assertEquals("Visa", visaCard.getCardType());

        CreditCard anotherVisaCard = new VisaCC("4912123456789100", "Visa");
        assertTrue(anotherVisaCard.isValid());
        assertEquals("Visa", anotherVisaCard.getCardType());

        CreditCard invalidVisaCard = new VisaCC("4111111", "Visa");
        assertFalse(invalidVisaCard.isValid());
        assertEquals("Invalid not Visa", invalidVisaCard.getCardType());
    }

}
