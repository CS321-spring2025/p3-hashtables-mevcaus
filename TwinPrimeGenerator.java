/**
 * Basic twin prime generator to generate the value for the size of a Hashtable
 *
 * @author Mevludin Causevic
 */
public class TwinPrimeGenerator {

    /**
     * Checks if a number is prime.
     * @param n The number to check
     * @return Boolean based on if n is prime
     */
    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a twin prime in the range [min, max].
     * @param min The minimum value in the range
     * @param max The maximum value in the range
     * @return The larger of the first twin prime pair found
     */
    public static int generateTwinPrime(int min, int max) {
        for (int i = min; i <= max; i++) {
            if (isPrime(i) && isPrime(i + 2)) {
                return i + 2;
            }
        }
        System.out.println("No twin prime found for given min and max");
        return 0;
    }

    /**
     * Main method just for testing
     * @param args should be empty
     */
    public static void main(String[] args) {
        int twinPrime = generateTwinPrime(95500, 96000);
        System.out.println("Twin prime found: " + (twinPrime - 2) + " " + twinPrime);
    }
}
