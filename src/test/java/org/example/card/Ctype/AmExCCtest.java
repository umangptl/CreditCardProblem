package org.example.card.Ctype;

import org.example.card.Ctype.AmExCC;

import org.example.card.Ctype.CreditCard;
import org.junit.Test;
import static org.junit.Assert.*;

public class AmExCCtest {

    @Test
    public void testAmExCCValidity() {
        CreditCard amExCC = new AmExCC("378282246310005", "American Express");
        assertTrue(amExCC.isValid());
        assertEquals("American Express", amExCC.getCardType());

        CreditCard amExCC2 = new AmExCC("341449635398431", "American Express");
        assertTrue(amExCC2.isValid());
        assertEquals("American Express", amExCC2.getCardType());

        CreditCard amExCC3 = new AmExCC("978734493671000", "American Express");
        assertFalse(amExCC3.isValid());
        assertEquals("Invalid not American Express", amExCC3.getCardType());

    }

}
