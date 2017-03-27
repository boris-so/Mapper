package ru.boris.tst.mapper;


import org.junit.Test;
import ru.boris.mapper.lists.List10km;

import static org.junit.Assert.assertEquals;
import static ru.boris.mapper.lists.LatZones.*;

public class Test10kmCalc
{
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


    @Test (expected = IllegalArgumentException.class) public void much1()
    {
        new List10km(V, 1).latOfs(2);
    }

    @Test (expected = IllegalArgumentException.class) public void much2()
    {
        new List10km(xV, 1).latOfs(2);
    }

    @Test (expected = IllegalArgumentException.class) public void much3()
    {
        new List10km(xB, 1).latOfs(-25);
    }

    @Test (expected = IllegalArgumentException.class) public void much4()
    {
        new List10km(B, 1).latOfs(-25);
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
