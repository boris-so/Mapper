package ru.boris.mapper.lists;

import static ru.boris.mapper.lists.LatZones.fromNum;

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

    public List10km lonOfs(final int n) throws IllegalArgumentException
    {
        return null;
    }

    public List10km diagonalOfs(final int lat, final int lon) throws IllegalArgumentException
    {
        return this.latOfs(lat).lonOfs(lon);
    }
    // endregion


    @Override public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        List10km other = (List10km) obj;
        return this.lon == other.lon && this.lat == other.lat;
    }

    @Override public String toString()
    {
        return this.lat.toString() + "-" + this.lon;
    }
}
