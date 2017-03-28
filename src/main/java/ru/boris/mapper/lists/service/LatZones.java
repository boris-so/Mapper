package ru.boris.mapper.lists.service;


/**
 * Describes latitude nomenclature zones: latin letters from A to V by 4 degree length.
 * For southern hemisphere prefix 'x' added.
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
    public static LatZones fromNum(final int n) throws IllegalArgumentException
    {
        if (Math.abs(n) > MAX_ZONE || n == 0) throw new IllegalArgumentException("Zone does not exist");

        if (n > 0)  return LatZones.values()[n-1];
        else        return LatZones.values()[Math.abs(n) + MAX_ZONE - 1];
    }

    /**
     * Returns zone number by given letter.
     * @return number between -22 (southern) and +22 (northern), excluding zero.
     */
    public int getNumber()
    {
        final int n = this.ordinal();
        if (this.toString().length() == 2)  return MAX_ZONE - n - 1;
        else                                return n + 1;
    }

    public int to(final LatZones z)
    {
        if (this.toString().length() == z.toString().length())
            return Math.abs(this.ordinal() - z.ordinal());
        else
            return 0;
    }


}
