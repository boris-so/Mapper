package ru.boris.tst.mapper;


import org.junit.Test;
import ru.boris.mapper.lists.List10km;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.boris.mapper.lists.service.LatZones.*;


public class Test10kmCalc
{
    /**
     * Helper to get ability to test multiple exceptions within one method.
     * @param act some action that must throw an exception.
     */
    static void assertWasException(final Consumer<Void> act)
    {
        boolean wasException = false;
        try { act.accept(null); }
        catch (IllegalArgumentException ex) { wasException = true; }
        assertTrue("Exception was NOT thrown", wasException);
    }


    @Test public void calcSouthern()
    {
        assertEquals(new List10km(A, 1).latOfs(1),       new List10km(B,  1));
        assertEquals(new List10km(B, 1).latOfs(5),       new List10km(G,  1));
        assertEquals(new List10km(G, 1).latOfs(-5),      new List10km(B,  1));
        assertEquals(new List10km(B, 1).latOfs(-1),      new List10km(A,  1));
        assertEquals(new List10km(A, 1).latOfs(-1),      new List10km(xA, 1));
        assertEquals(new List10km(F, 1).latOfs(-6),      new List10km(xA, 1));
        assertEquals(new List10km(M, 1).latOfs(-33),     new List10km(xU, 1));
    }

    @Test public void calcNorthern()
    {
        assertEquals(new List10km(xA, 1).latOfs(1),      new List10km(xB, 1));
        assertEquals(new List10km(xN, 1).latOfs(7),      new List10km(xU, 1));
        assertEquals(new List10km(xB, 1).latOfs(-1),     new List10km(xA, 1));
        assertEquals(new List10km(xA, 1).latOfs(-1),     new List10km(A,  1));
        assertEquals(new List10km(xE, 1).latOfs(-5),     new List10km(A,  1));
        assertEquals(new List10km(xO, 1).latOfs(-35),    new List10km(U,  1));
    }

    @Test public void calcLatitudes()
    {
        final List10km src = new List10km(A, 7);

        assertEquals(src.lonOfs(5), new List10km(A, 12));
        assertEquals(src.lonOfs(-20), new List10km(A, 47));
    }

    @Test public void exceptions()
    {
        // calculations
        assertWasException(aVoid -> new List10km(V, 1).latOfs(2));
        assertWasException(aVoid -> new List10km(xV, 1).latOfs(2));
        assertWasException(aVoid -> new List10km(xB, 1).latOfs(-25));
        assertWasException(aVoid -> new List10km(B, 1).latOfs(-25));
        assertWasException(aVoid -> new List10km(B, 0));
        assertWasException(aVoid -> new List10km(B, 61));

        assertWasException(aVoid -> new List10km(A, 7).lonOfs(-67));
    }
}
