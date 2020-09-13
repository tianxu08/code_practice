package mj_linkedin;

public interface IntervalLinkedIn {

	 /**
     * Adds an interval [from, to] into internal structure.
     */
    void addInterval(int from, int to);


    /**
     * Returns a total length covered by intervals.
     * If several intervals intersect, intersection should be counted only once.
     * Example:
     *
     * addInterval(3, 6)
     * addInterval(8, 9)
     * addInterval(1, 5)
     *
     * getTotalCoveredLength() -> 6
     * i.e. [1,5] and [3,6] intersect and give a total covered interval [1,6]
     * [1,6] and [8,9] don't intersect so total covered length is a sum for both intervals, that is 6.
     *
     *                   _________
     *                                               ___
     *     ____________
     *
     * 0  1    2    3    4    5   6   7    8    9    10
     *
     */
    int getTotalCoveredLength();
}
