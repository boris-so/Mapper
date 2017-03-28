package ru.boris.tst.mapper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ru.boris.mapper.lists.service.LatZones.*;

public class TestLatZones
{
    @Test public void zoneToLetter()
    {
        // Northern
        assertEquals(fromNum(1),        A);
        assertEquals(fromNum(14),       N);
        assertEquals(fromNum(22),       V);
        // Southern
        assertEquals(fromNum(-1),       xA);
        assertEquals(fromNum(-14),      xN);
        assertEquals(fromNum(-22),      xV);
    }

    @Test public void letterToZone()
    {
        assertEquals(A.getNumber(),     1);
        assertEquals(M.getNumber(),     13);
        assertEquals(V.getNumber(),     22);

        assertEquals(xA.getNumber(),    -1);
        assertEquals(xM.getNumber(),    -13);
        assertEquals(xV.getNumber(),    -22);
    }

    @Test public void between()
    {
        assertEquals(A.to(D), 3);
        assertEquals(U.to(J), 11);

        assertEquals(xD.to(xN), 10);
        assertEquals(xT.to(xN), 6);

        assertEquals(H.to(xH), 15);
        assertEquals(xV.to(V), 43);
    }


    @Test (expected = IllegalArgumentException.class) public void less()
    {
        fromNum(-23);
    }

    @Test (expected = IllegalArgumentException.class) public void zero()
    {
        fromNum(0);
    }

    @Test (expected = IllegalArgumentException.class) public void more()
    {
        fromNum(23);
    }
}
