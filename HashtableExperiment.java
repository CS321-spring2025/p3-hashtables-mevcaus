import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * HashTableExperiment is a program that tests and compares the performance of
 * two hash table implementations: Linear Probing and Double Hashing.
 * It can use three different data sources: random integers, dates, or words from a file.
 * The program measures and reports statistics such as the number of elements inserted,
 * number of duplicates, and average number of probes required for insertions.
 *
 * @author Mevludin Causevic
 */
public class HashtableExperiment {

    /**
     * Main method that parses command line arguments and runs the hash table experiment.
     *
     * @param args Command line arguments:
     *             args[0] - data source type
     *             args[1] - load factor
     *             args[2] - debug level
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            printUsage();
            return;
        }
        int dataType = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        int debugLevel;
        if (args.length == 2) {
            debugLevel = 0;
        } else {
            debugLevel = Integer.parseInt(args[2]);
        }
        int tableSize = TwinPrimeGenerator.generateTwinPrime(95500, 96000);

        LinearProbing linearTable = new LinearProbing(tableSize, loadFactor);
        DoubleHashing doubleTable = new DoubleHashing(tableSize, loadFactor);

        if (dataType == 1) {
            handleIntegers( linearTable, doubleTable);
        } else if (dataType == 2) {
            handleDates(linearTable, doubleTable);
        } else if (dataType == 3) {
            handleStrings(linearTable, doubleTable);
        }

        printStatistics(linearTable, doubleTable, debugLevel, tableSize, loadFactor, getDataString(dataType));
    }

    /**
     * Handles the insertion of random integers into both hash tables until one becomes full.
     *
     * @param lt The LinearProbing hash table
     * @param dt The DoubleHashing hash table
     */
    private static void handleIntegers( LinearProbing lt, DoubleHashing dt) {
        Random rand = new Random();
        Integer key = rand.nextInt();
        boolean isFull = false;
        while (!isFull) {
            if (!lt.insert(key)) isFull = true;
            if (!dt.insert(key)) isFull = true;
            key = rand.nextInt();
        }
    }

    /**
     * Handles the insertion of Date objects into both hash tables until one becomes full.
     * Each date is 1 second (1000ms) apart from the previous one.
     *
     * @param lt The LinearProbing hash table
     * @param dt The DoubleHashing hash table
     */
    private static void handleDates(LinearProbing lt, DoubleHashing dt) {
        long current = new Date().getTime();
        boolean isFull = false;
        while (!isFull) {
            Date key = new Date(current);
            if (!lt.insert(key)) isFull = true;
            if (!dt.insert(key)) isFull = true;
            current += 1000;
        }

    }

    /**
     * Handles the insertion of strings from a word list file into both hash tables
     * until one becomes full or the end of the file is reached.
     *
     * @param lt The LinearProbing hash table
     * @param dt The DoubleHashing hash table
     * @throws RuntimeException if the word list file cannot be found
     */
    private static void handleStrings(LinearProbing lt, DoubleHashing dt) {
        try {
            Scanner sc = new Scanner(new File("word-list.txt"));
            boolean isFull = false;
            while (!isFull) {
                String key = sc.nextLine();
                if (!lt.insert(key)) isFull = true;
                if (!dt.insert(key)) isFull = true;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Prints statistics about the hash table experiment and handles debug output.
     *
     * @param lt The LinearProbing hash table
     * @param dt The DoubleHashing hash table
     * @param debugLevel The debug level
     * @param tableSize The size of the hash tables
     * @param loadFactor The load factor used for the experiment
     * @param dataString A string describing the data source used
     */
    private static void printStatistics(LinearProbing lt, DoubleHashing dt, int debugLevel, int tableSize, double loadFactor, String dataString) {
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("HashtableExperiment: Found a twin prime table capacity: " + tableSize);
        System.out.println("HashtableExperiment: Input: " + dataString + "   Loadfactor:" + loadFactor);
        System.out.println();

        System.out.println("         Using Linear Probing");
        System.out.println("HashtableExperiment: size of hash table is " + lt.getSize());
        System.out.println("        Inserted " + (lt.getSize() + lt.getNumDuplicates()) + " elements, of which " + lt.getNumDuplicates() + " were duplicates");
        System.out.println("        Avg. no. of probes = " + df.format((double)lt.getNumProbes() / (double)lt.getSize()));
        if (debugLevel == 1) {
            System.out.println("HashtableExperiment: Saved dump of hash table");
            dumpToFile("linear-dump.txt", lt.getTable());
        } else if (debugLevel == 2) {
            printDetailedTableContents(lt.getTable());
        }

        System.out.println();
        System.out.println("        Using Double Hashing");
        System.out.println("HashtableExperiment: size of hash table is " + dt.getSize());
        System.out.println("        Inserted " + (dt.getSize() + dt.getNumDuplicates()) + " elements, of which " + dt.getNumDuplicates() + " were duplicates");
        System.out.println("        Avg. no. of probes = " + df.format((double)dt.getNumProbes() / (double)dt.getSize()));

        if (debugLevel == 1) {
            System.out.println("HashtableExperiment: Saved dump of hash table");
            dumpToFile("double-dump.txt", dt.getTable());
        } else if (debugLevel == 2) {
            printDetailedTableContents(dt.getTable());
        }
    }

    /**
     * Converts the data type code to a descriptive string.
     *
     * @param dataType The data type code
     * @return A string describing the data source
     */
    private static String getDataString(int dataType) {
        String dataString;
        switch (dataType) {
            case 1:
                dataString = "Random Numbers";
                break;
            case 2:
                dataString = "Dates";
                break;
            case 3:
                dataString = "Word-List";
                break;
            default:
                dataString = "Invalid data type input";
                break;
        }
        return dataString;
    }

    /**
     * Prints usage information for the program.
     */
    private static void printUsage() {
        System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
        System.out.println("<dataSource>: 1 ==> random numbers");
        System.out.println("              2 ==> date value as a long");
        System.out.println("              3 ==> word list");
        System.out.println("<loadFactor>: The ratio of objects to table size,");
        System.out.println("                denoted by alpha = n/m");
        System.out.println("<debugLevel>: 0 ==> print summary of experiment");
        System.out.println("              1 ==> save the two hash tables to a file at the end");
        System.out.println("              2 ==> print debugging output for each insert");
    }

    /**
     * Dumps the contents of a hash table to a file.
     * Only non-null entries are written to the file.
     *
     * @param fileName The name of the file to write to
     * @param table The hash table array to dump
     * @throws RuntimeException if the file cannot be created or written to
     */
    private static void dumpToFile(String fileName, HashObject[] table) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            for (int i = 0; i < table.length; i++) {
                HashObject current = table[i];
                if (current != null) {
                    out.println("table[" + i + "]: " + current.toString());
                }
            }
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints detailed information about each element in the hash table to the console.
     * For each non-null entry, prints the index, the HashObject's string representation,
     * and whether it was a successful insertion or a duplicate.
     *
     * @param table The hash table array to print
     */
    private static void printDetailedTableContents(HashObject[] table) {
        for (int i = 0; i < table.length; i++) {
            HashObject current = table[i];
            if (current != null) {
                System.out.println("table[" + i + "]:" + current.toString());
            }
        }
    }
}
