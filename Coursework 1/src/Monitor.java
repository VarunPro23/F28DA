package F28DA_CW1;

/**
 * Interface your hash table has to implement.
 */
public interface Monitor {

	/**
	 * Returns the maximum authorised load factor.
	 */
	public float getMaxLoadFactor();

	/**
	 * Returns the current load factor.
	 */
	public float getLoadFactor();

	/**
	 * Returns the average number of probes.
	 */
	public float getAverageProbes();

}
