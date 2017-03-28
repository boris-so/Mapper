package ru.boris.mapper.lists.service;

public class WGS84Point
{
    private float lat, lon;

    public WGS84Point(float lat, float lon) throws IllegalArgumentException
    {
        if (Math.abs(lat) > 90 || Math.abs(lon) > 180)
            throw new IllegalArgumentException("Degree is out of range");

        this.lat = lat;
        this.lon = lon;
    }

    public float getLat()
    {
        return this.lat;
    }
    public float getLon()
    {
        return this.lon;
    }
}
