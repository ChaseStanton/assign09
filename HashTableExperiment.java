package assign09;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HashTableExperiment {
    public static void main(String[] args) {
        // Initialize your custom HashTable and Java's HashMap
        HashTable<String, Integer> customHashTable = new HashTable<>();
        Map<String, Integer> javaHashMap = new HashMap<>();

        // Iterate through different dataset sizes
        for (int datasetSize = 1000; datasetSize <= 10000; datasetSize += 1000) {
            Random random = new Random();

            // Insertion (Put) Operation
            double customInsertionTime = measureInsertionTime(customHashTable, datasetSize, random) / 1_000_000.0;
            double javaInsertionTime = measureInsertionTime(javaHashMap, datasetSize, random) / 1_000_000.0;

            // Retrieval (Get) Operation
            double customRetrievalTime = measureRetrievalTime(customHashTable, datasetSize, random) / 1_000_000.0;
            double javaRetrievalTime = measureRetrievalTime(javaHashMap, datasetSize, random) / 1_000_000.0;

            // Print the results
            System.out.println("Dataset Size: " + datasetSize);
            System.out.println("Custom HashTable Insertion Time: " + customInsertionTime + " milliseconds");
            System.out.println("Java HashMap Insertion Time: " + javaInsertionTime + " milliseconds");
            System.out.println("Custom HashTable Retrieval Time: " + customRetrievalTime + " milliseconds");
            System.out.println("Java HashMap Retrieval Time: " + javaRetrievalTime + " milliseconds");
            System.out.println("---------------------------------------------");
        }
    }

    private static long measureInsertionTime(HashTable<String, Integer> map, int datasetSize, Random random) {
        long startTime = System.nanoTime();
        for (int i = 0; i < datasetSize; i++) {
            String key = ("Key" + random.nextInt(datasetSize)); // Adjust the key type if needed
            Integer value = random.nextInt(); // Adjust the value type if needed
            map.put(key, value);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureInsertionTime(Map<String, Integer> map, int datasetSize, Random random) {
        long startTime = System.nanoTime();
        for (int i = 0; i < datasetSize; i++) {
            String key = "Key" + random.nextInt(datasetSize);
            int value = random.nextInt();
            map.put(key, value);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureRetrievalTime(Map<String, Integer> map, int datasetSize, Random random) {
        long startTime = System.nanoTime();
        for (int i = 0; i < datasetSize; i++) {
            String key = "Key" + random.nextInt(datasetSize);
            map.get(key);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    private static long measureRetrievalTime(HashTable<String, Integer> map, int datasetSize, Random random) {
        long startTime = System.nanoTime();
        for (int i = 0; i < datasetSize; i++) {
            String key = "Key" + random.nextInt(datasetSize);
            map.get(key);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
