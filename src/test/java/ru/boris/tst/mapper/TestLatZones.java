package ru.boris.tst.mapper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ru.boris.mapper.lists.LatZones.*;

public class TestLatZones
{
    @Test public void zoneToLetter()
    {
        // Northern
        assertEquals(getLetter(1),      A);
        assertEquals(getLetter(14),     N);
        assertEquals(getLetter(22),     V);
        // Southern
        assertEquals(getLetter(-1),     xA);
        assertEquals(getLetter(-14),    xN);
        assertEquals(getLetter(-22),    xV);
    }

    @Test public void letterToZone()
    {
        assertEquals(getNumber(A), 1);
        assertEquals(getNumber(M), 13);
        assertEquals(getNumber(V), 22);

        assertEquals(getNumber(xA), -1);
        assertEquals(getNumber(xM), -13);
        assertEquals(getNumber(xV), -22);
    }

    @Test public void calcSouthern()
    {
        assertEquals(A.add(1), B);
        assertEquals(B.add(5), G);
        assertEquals(G.add(-5), B);
        assertEquals(B.add(-1), A);
        assertEquals(A.add(-1), xA);
        assertEquals(F.add(-6), xA);
        assertEquals(M.add(-33), xU);
    }

    @Test public void calcNorthern()
    {
        assertEquals(xA.add(1), xB);
        assertEquals(xN.add(7), xU);
        assertEquals(xB.add(-1), xA);
        assertEquals(xA.add(-1), A);
        assertEquals(xE.add(-5), A);
        assertEquals(xO.add(-35), U);
    }


    @Test (expected = IllegalArgumentException.class) public void much1()
    {
        V.add(2);
    }

    @Test (expected = IllegalArgumentException.class) public void much2()
    {
        xV.add(2);
    }

    @Test (expected = IllegalArgumentException.class) public void much3()
    {
        xB.add(-25);
    }

    @Test (expected = IllegalArgumentException.class) public void much4()
    {
        B.add(-25);
    }

    @Test (expected = IllegalArgumentException.class) public void less()
    {
        getLetter(-23);
    }

    @Test (expected = IllegalArgumentException.class) public void zero()
    {
        getLetter(0);
    }

    @Test (expected = IllegalArgumentException.class) public void more()
    {
        getLetter(23);
    }
}
