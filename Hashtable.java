/**
 * Basic HashTable implementation for HashObjects
 *
 * @author Mevludin Causevic
 */
public abstract class Hashtable {
    protected int size;
    protected int capacity;
    protected double loadFactor;
    protected HashObject[] table;
    protected int numDuplicates;
    protected int numProbes;

    /**
     * Constructs a new hash table with the given capacity and load factor.
     * @param capacity The size of the hash table array
     * @param loadFactor The maximum load factor
     */
    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.table = new HashObject[capacity];
        this.numDuplicates = 0;
        this.numProbes = 0;
    }


    /**
     * Abstract hash function implemented by subclasses
     * @param key The key to hash
     * @param probe The probe number
     * @return The hash index
     */
    public abstract int h(Object key, int probe);

    /**
     * Inserts a key into the hash table.
     * @param key The key to insert
     * @return true if insertion was successful, false otherwise
     */
    public boolean insert(Object key) {
        if ((double) size / capacity >= loadFactor) {
//            System.out.println("Hash table already at loadFactor: " + loadFactor);
            return false;
        }

        int probe = 0;

        while (probe < capacity) {
            int index = h(key, probe);

            if (table[index] == null || table[index].isDeleted()) {
                table[index] = new HashObject(key);
                table[index].setProbeCount(probe + 1);
                size++;
                numProbes += probe + 1;
                return true;
            } else if (table[index].getKey().equals(key)) {
                table[index].incrementFrequency();
                numDuplicates++;
                return true;
            }

            probe++;
        }

    return false;
    }

    /**
     * Searches for a key in the hash table.
     * @param key The key to search for
     * @return The HashObject if found, null if not
     */
    public HashObject search(Object key) {

        int probe = 0;

        while (probe < capacity) {
            int index = h(key, probe);

            if (table[index] == null) {
                return null;
            } else if (table[index].getKey().equals(key) && !table[index].isDeleted()) {
                return table[index];
            }

            probe++;
        }

        return null;
    }

    /**
     * Calculates the positive modulo of a dividend and divisor.
     * This ensures that the result is always non-negative, which is
     * necessary for hash table indices.
     *
     * @param dividend The number to divide
     * @param divisor The number to divide by
     * @return The positive remainder of the division
     * @uathor cs321 instructors
     */
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) quotient += divisor;
        return quotient;
    }

    /**
     * Gets the current number of elements in the hash table.
     * This does not include duplicates.
     *
     * @return The number of unique elements in the hash table
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets the number of duplicate keys encountered during insertions.
     * A duplicate occurs when a key that already exists in the table
     * is inserted again.
     *
     * @return The number of duplicate keys encountered
     */
    public int getNumDuplicates() {
        return this.numDuplicates;
    }

    /**
     * Gets the total number of probes performed during all insertions.
     * This is used to calculate the average number of probes per insertion.
     *
     * @return The total number of probes performed
     */
    public int getNumProbes() {
        return this.numProbes;
    }

    /**
     * Gets the hash table array.
     * This provides direct access to the internal array of HashObjects for printing to console/log.
     *
     * @return The hash table array
     */
    public HashObject[] getTable() {
        return this.table;
    }
}
