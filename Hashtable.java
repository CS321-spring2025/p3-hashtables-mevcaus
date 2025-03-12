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
            System.out.println("Hash table already at loadFactor: " + loadFactor);
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
            probe++;
            if (table[index] == null) {
                return null;
            } else if (!table[index].getKey().equals(key)) {
                index = h(key, probe + 1);
            } else if (table[index].getKey().equals(key) && !table[index].isDeleted()) {
                return table[index];
            }

            probe++;
        }

        return null;
    }

    /**
     * changes any negative modular value to positive
     * @param dividend The integer to be divided
     * @param divisor The integer to divide by
     * @return The positive remainder.
     */
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0) quotient += divisor;
        return quotient;
    }
}
