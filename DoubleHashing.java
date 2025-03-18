/**
 * Double hashing implementation of the hashing function
 *
 * @author Mevludin Causevic
 */
public class DoubleHashing extends Hashtable{

    /**
     * Constructs a new hash table with double hashing.
     * @param capacity The size of the hash table
     * @param loadFactor The maximum load factor
     */
    public DoubleHashing(int capacity, double loadFactor) {
        super(capacity, loadFactor);
    }

    @Override
    public int h(Object key, int probe) {
        int primaryHash = positiveMod(key.hashCode(), capacity);
        int secondaryHash = 1 + positiveMod(key.hashCode(), capacity - 2);
        return positiveMod(primaryHash + probe * secondaryHash, capacity);
    }
}
