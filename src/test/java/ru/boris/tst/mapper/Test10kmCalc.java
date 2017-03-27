package ru.boris.tst.mapper;


import org.junit.Test;
import ru.boris.mapper.lists.List10km;

import static org.junit.Assert.assertEquals;
import static ru.boris.mapper.lists.LatZones.*;

public class Test10kmCalc
{
    @Test public void calcSouthern()
    {
        assertEquals(new List10km(A, 1).addLatOfs(1),       B);
        assertEquals(new List10km(B, 1).addLatOfs(5),       G);
        assertEquals(new List10km(G, 1).addLatOfs(-5),      B);
        assertEquals(new List10km(B, 1).addLatOfs(-1),      A);
        assertEquals(new List10km(A, 1).addLatOfs(-1),      xA);
        assertEquals(new List10km(F, 1).addLatOfs(-6),      xA);
        assertEquals(new List10km(M, 1).addLatOfs(-33),     xU);
    }

    @Test public void calcNorthern()
    {
        assertEquals(new List10km(xA, 1).addLatOfs(1),      xB);
        assertEquals(new List10km(xN, 1).addLatOfs(7),      xU);
        assertEquals(new List10km(xB, 1).addLatOfs(-1),     xA);
        assertEquals(new List10km(xA, 1).addLatOfs(-1),     A);
        assertEquals(new List10km(xE, 1).addLatOfs(-5),     A);
        assertEquals(new List10km(xO, 1).addLatOfs(-35),    U);
    }


    @Test (expected = IllegalArgumentException.class) public void much1()
    {
        new List10km(V, 1).addLatOfs(2);
    }

    @Test (expected = IllegalArgumentException.class) public void much2()
    {
        new List10km(xV, 1).addLatOfs(2);
    }

    @Test (expected = IllegalArgumentException.class) public void much3()
    {
        new List10km(xB, 1).addLatOfs(-25);
    }

    @Test (expected = IllegalArgumentException.class) public void much4()
    {
        new List10km(B, 1).addLatOfs(-25);
    }

    @Test (expected = IllegalArgumentException.class) public void below()
    {
        new List10km(B, 0);
    }

    @Test (expected = IllegalArgumentException.class) public void above()
    {
        new List10km(B, 61);
    }



}
