package org.example.card.Ctype;

import org.example.card.Ctype.CreditCard;
import org.example.card.Ctype.DiscoverCC;
import org.example.card.Ctype.VisaCC;
import org.junit.Test;

import static org.junit.Assert.*;

public class DisoverCCtest {

    @Test
    public void testDiscoverCCValidity() {
        CreditCard discoverCC = new DiscoverCC("6011111111111117", "Discover");
        assertTrue(discoverCC.isValid());
        assertEquals("Discover", discoverCC.getCardType());

        CreditCard discoverCC2 = new DiscoverCC("6011000990139424", "Discover");
        assertTrue(discoverCC2.isValid());
        assertEquals("Discover", discoverCC2.getCardType());

        CreditCard discoverCC3 = new DiscoverCC("978734493671000", "Discover");
        assertFalse(discoverCC3.isValid());
        assertEquals("Invalid not Discover", discoverCC3.getCardType());


    }
}
