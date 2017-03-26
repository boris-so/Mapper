package ru.boris.mapper.lists;

public enum LatZones
{
    // Northern
    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,
    // Southern
    xA, xB, xC, xD, xE, xF, xG, xH, xI, xJ, xK, xL, xM, xN, xO, xP, xQ, xR, xS, xT, xU, xV;

    /**
     * Zones on hemisphere.
     */
    private static final int MAX_ZONE = 22;

    /**
     * Returns letter representation by given number.
     * @param n number to represent in letter.
     * @return  zone letter
     * @throws IllegalArgumentException given number is out of range.
     */
    public static LatZones getLetter(final int n) throws IllegalArgumentException
    {
        if (Math.abs(n) > MAX_ZONE || n == 0) throw new IllegalArgumentException("Zone does not exists!");

        return A;
    }

    /**
     * Returns zone number by given letter.
     * @param z letter to be converted to number.
     * @return number between -22 (southern) and +22 (northern), excluding zero.
     */
    public static int getNumber(final LatZones z)
    {
        return 0;
    }
}
