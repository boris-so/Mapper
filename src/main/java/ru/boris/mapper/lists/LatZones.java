package ru.boris.mapper.lists;


import com.sun.istack.internal.NotNull;

/**
 * Describes latitude nomenclature zones: latin letters from A to V by 4 degree length.
 * For southern hemisphere prefix 'x' added.<br />
 * Also defines functions provides some calculations between zones.
 */
public enum LatZones
{
    // ================================
    // !!!IMPORTANT!!!
    // ================================
    // DON`T CHANGE CONSTANT ORDER!


    // Northern
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,
    // Southern
    xA, xB, xC, xD, xE, xF, xG, xH, xI, xJ, xK, xL, xM, xN, xO, xP, xQ, xR, xS, xT, xU, xV;

    /**
     * Zones on hemisphere.
     */
    public static final int MAX_ZONE = 22;

    /**
     * Returns letter representation by given number.
     * @param n number to represent in letter.
     * @return  zone letter
     * @throws IllegalArgumentException given number is out of range.
     */
    public static LatZones getLetter(final int n) throws IllegalArgumentException
    {
        if (Math.abs(n) > MAX_ZONE || n == 0) throw new IllegalArgumentException("Zone does not exist");

        if (n > 0)  return LatZones.values()[n-1];
        else        return LatZones.values()[Math.abs(n) + MAX_ZONE - 1];
    }

    /**
     * Returns zone number by given letter.
     * @param z letter to be converted to number.
     * @return number between -22 (southern) and +22 (northern), excluding zero.
     */
    public static int getNumber(@NotNull final LatZones z)
    {
        final int n = z.ordinal();
        if (z.toString().length() == 2) return MAX_ZONE - n - 1;
        else                            return n + 1;
    }


    public LatZones add(final int n) throws IllegalArgumentException
    {
        final int z = getNumber(this);

        if (n >= 0)                                             // summary, hemisphere is not changed
        {
            if (Math.abs(z) + Math.abs(n) > MAX_ZONE) throw new IllegalArgumentException("Zone out of bounds");
            final int sum = Math.abs(z) + n;
            return getLetter(z < 0 ? -sum : sum);
        }
        else                                                    // subtraction, hemisphere may change
        {
            if (Math.abs(z) - n < -MAX_ZONE) throw new IllegalArgumentException("Zone out of bounds");
            final int sub = Math.abs(z) - Math.abs(n);
            if (sub <= 0)                                        // hemis changed. Correct by 1 because there is
                return getLetter(z < 0 ? -sub + 1 : sub - 1);     // no zero zone by nomenclature.
            else
                return getLetter(z < 0 ? -sub : sub);
        }
    }
}
