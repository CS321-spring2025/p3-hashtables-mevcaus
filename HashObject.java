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
    private boolean isDeleted;

    /**
     * Constructs a new HashObject with the given key.
     * @param key The key for this hash object
     */
    public HashObject(Object key) {
        this.key = key;
        this.frequency = 1;
        this.probeCount = 0;
        this.isDeleted = false;
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
     * sets probe count
     * @param probeCount probe count to set to
     */
    public void setProbeCount(int probeCount) {
        this.probeCount = probeCount;
    }

    /**
     * sets the deleted boolean to true
     */
    public void delete() {
        isDeleted = true;
    }

    /**
     * gets the deleted state
     * @return a boolean representing if the hash object was deleted
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Checks if this HashObject is equal to another HashObject by comparing their keys.
     * @param key The key to compare to
     * @return true if the keys are equal, false otherwise
     */
    public boolean equals(Object key) {
        return this.key.equals(key);
    }

    /**
     * Returns a string representation of the HashObject
     * @return A string
     */
    public String toString() {
        return key + " " + frequency + " " + probeCount;
    }
}
