package ru.boris.mapper.lists;

import ru.boris.mapper.lists.service.LatZones;
import ru.boris.mapper.lists.service.WGS84Point;

import static ru.boris.mapper.lists.service.LatZones.fromNum;

public class List10km
{
    public static final int MAX_ZONE = 60;
    public static final int MIN_ZONE = 1;

    private LatZones lat;
    /**
     * Longitude nomenclature zone (between 1 and 60)
     */
    private int lon;


    /**
     * Create list with given latitude and longitude zones.
     * @param lat row
     * @param lon column
     * @throws IllegalArgumentException if longitude zone is out of [{@link List10km#MIN_ZONE MIN_ZONE};
     * {@link List10km#MAX_ZONE MAX_ZONE}]
     */
    public List10km(LatZones lat, int lon) throws IllegalArgumentException
    {
        if (lon < MIN_ZONE || lon > MAX_ZONE)
            throw new IllegalArgumentException("Longitude zone is out of range [1; 60]");

        this.lat = lat;
        this.lon = lon;
    }

    public List10km(final WGS84Point p)
    {
        // todo: 4
    }

    // region Calculations
    /**
     * Add (or subtract) some latitude zones for current list.
     * @param n Zone count to be added.
     * @return  New list with n latitude zones offset.
     * @throws IllegalArgumentException if offset is too big for current zone.
     */
    public List10km latOfs(final int n) throws IllegalArgumentException
    {
        final int z = this.lat.getNumber();

        if (n >= 0)                                             // summary, hemisphere is not changed
        {
            if (Math.abs(z) + Math.abs(n) > LatZones.MAX_ZONE) throw new IllegalArgumentException("Zone out of bounds");
            final int sum = Math.abs(z) + n;
            return new List10km(fromNum(z < 0 ? -sum : sum), this.lon);
        }
        else                                                    // subtraction, hemisphere may change
        {
            if (Math.abs(z) - n < -LatZones.MAX_ZONE) throw new IllegalArgumentException("Zone out of bounds");
            final int sub = Math.abs(z) - Math.abs(n);
            if (sub <= 0)                                        // hemis changed. Correct by 1 because there is
                return new List10km(fromNum(z < 0 ? -sub + 1 : sub - 1), this.lon);     // no zero zone by nomenclature.
            else
                return new List10km(fromNum(z < 0 ? -sub : sub), this.lon);
        }
    }

    /**
     * Calculates offset in longitude zones (total 60 by nomenclature)
     * @param n ofset value.
     * @return another list with given offset.
     * @throws IllegalArgumentException if offset is more than 59 (offset 59 is a full row).
     */
    public List10km lonOfs(final int n) throws IllegalArgumentException
    {
        if (Math.abs(n) >= MAX_ZONE) throw new IllegalArgumentException("Offset is out of range (max - 59)");

        int res = this.lon + n;
        if (res > 60) return new List10km(this.lat, res - 60);
        if (res < 1)  return new List10km(this.lat, 60 + res);
        return new List10km(this.lat, res);
    }

    public List10km diagonalOfs(final int lat, final int lon) throws IllegalArgumentException
    {
        return this.latOfs(lat).lonOfs(lon);
    }

    public List10km[][] getArea(final List10km from, final List10km to)
    {
        // todo: 2
        return new List10km[0][0];
    }

    // endregion


    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof List10km)) return false;

        List10km list10km = (List10km) o;

        return lon == list10km.lon && lat == list10km.lat;
    }

    @Override public int hashCode()
    {
        int result = lat.hashCode();
        result = 31 * result + lon;
        return result;
    }

    @Override public String toString()
    {
        return this.lat.toString() + "-" + this.lon;
    }
}
