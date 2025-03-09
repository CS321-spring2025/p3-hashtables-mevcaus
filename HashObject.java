/**
 * HashObject contains a generic key object, frequency count, and probe count.
 * HashObject has explicit methods to increment the frequency and probe count.
 *
 * @author Mevludin Causevic
 */
public class HashObject {
    private Object key;
    private int frequency;
    private int probeCount;

    /**
     * Constructs a new HashObject with the given key.
     * @param key The key for this hash object
     */
    public HashObject(Object key) {
        this.key = key;
        this.frequency = 0;
        this.probeCount = 0;
    }

    /**
     * Gets the key
     * @return The key
     */
    public Object getKey() {
        return key;
    }

    /**
     * Gets the frequency
     * @return The frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Gets the probe count
     * @return The probe count
     */
    public int getProbeCount() {
        return probeCount;
    }

    /**
     * Increments the frequency
     */
    public void incrementFrequency() {
        frequency++;
    }

    /**
     * Increments the probe count
     */
    public void incrementProbeCount() {
        probeCount++;
    }

    /**
     * Checks if this HashObject is equal to another HashObject by comparing their keys.
     * @param obj The HashObject to compare to
     * @return true if the keys are equal, false otherwise
     */
    public boolean equals(HashObject obj) {
        return this.key.equals(obj.getKey());
    }

    /**
     * Returns a string representation of the HashObject
     * @return A string
     */
    public String toString() {
        // not sure what format to use yet.
        return "";
    }
}
