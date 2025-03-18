/**
 * Linear probing implementation of the hashing function
 *
 * @author Mevludin Causevic
 */
public class LinearProbing extends Hashtable{
    /**
     * Constructs a new hash table with linear probing.
     * @param capacity The size of the hash table
     * @param loadFactor The maximum load factor
     */
    public LinearProbing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    public int h(Object key, int probe) {

        int hash = positiveMod(key.hashCode(), capacity);
        return positiveMod(hash + probe, capacity);
    }
}
