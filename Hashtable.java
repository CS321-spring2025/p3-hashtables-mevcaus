public abstract class Hashtable {
    protected int size;
    protected  int capacity;
    protected double loadFactor;

    public Hashtable(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    public abstract int h(Object key, int probe);
}
