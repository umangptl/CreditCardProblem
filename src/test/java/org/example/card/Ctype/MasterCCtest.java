package org.example.card.Ctype;

import org.example.card.Ctype.CreditCard;
import org.example.card.Ctype.MasterCC;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MasterCCtest {
    @Test
    public void testVisaCCValidity() {
        CreditCard MasterCard = new MasterCC("5111111111111111", "MasterCard");
        assertTrue(MasterCard.isValid());
        assertEquals("MasterCard", MasterCard.getCardType());

        CreditCard anotherMasterCard = new MasterCC("5212123456789100", "MasterCard");
        assertTrue(anotherMasterCard.isValid());
        assertEquals("MasterCard", anotherMasterCard.getCardType());

        CreditCard invalidMasterCard = new MasterCC("4111111", "MasterCard");
        assertFalse(invalidMasterCard.isValid());
        assertEquals("Invalid not MasterCard", invalidMasterCard.getCardType());
    }
}
