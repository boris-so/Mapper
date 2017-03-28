package ru.boris.mapper;

import ru.boris.mapper.lists.service.LatZones;

public class Main
{

    public static void main(String[] args)
    {
        System.out.println(LatZones.A.hashCode());
        System.out.println(LatZones.A.hashCode());
        System.out.println(LatZones.B.hashCode());
        System.out.println(LatZones.B.hashCode());
    }
}
