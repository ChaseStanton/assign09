package assign09;

import java.util.Random;

public class HashFunctionExperiment {
    public static void main(String[] args) {
        int[] n = new int[]{1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};

        for (int size : n) {
            long badHashStart = System.nanoTime();
            HashTable<StudentBadHash, Double> badHashTable = generateBadHashTable(size);
            long badHashEnd = System.nanoTime();

            long mediumHashStart = System.nanoTime();
            HashTable<StudentMediumHash, Double> mediumHashTable = generateMediumHashTable(size);
            long mediumHashEnd = System.nanoTime();

            long goodHashStart = System.nanoTime();
            HashTable<StudentGoodHash, Double> goodHashTable = generateGoodHashTable(size);
            long goodHashEnd = System.nanoTime();


            // Do something with the hash tables, or print some information
            System.out.println("Size: " + size);
            System.out.println("Bad Hash Table Time: " + (badHashEnd - badHashStart));
            System.out.println("Bad Hash Table Collisions: " + badHashTable.collisions );
            System.out.println("Medium Hash Table Time: " + (mediumHashEnd - mediumHashStart));
            System.out.println("Medium Hash Table Collisions: " + mediumHashTable.collisions );
            System.out.println("Good Hash Table Time: " + (goodHashEnd - goodHashStart));
            System.out.println("Good Hash Table Collisions: " + goodHashTable.collisions );
            System.out.println("-----------------------");
        }
    }

    public static HashTable<StudentBadHash, Double> generateBadHashTable(int size){
        Random random = new Random();
        HashTable<StudentBadHash, Double> bad = new HashTable<StudentBadHash, Double>();
        for (int i = 0; i < size; i ++){ 
            String firstName = generateRandomString(random, random.nextInt(5));
            String lastName = generateRandomString(random, random.nextInt(7));
            double gpa = generateRandomDouble(random, 1.0, 4.0);
            int uid = generateRandomInt(random, 1000000, 1999999);
            bad.put(new StudentBadHash(uid, firstName, lastName), gpa);
        }
        return bad;
    }

    public static HashTable<StudentMediumHash, Double> generateMediumHashTable(int size){
        Random random = new Random();
        HashTable<StudentMediumHash, Double> medium = new HashTable<StudentMediumHash, Double>();
        for (int i = 0; i < size; i ++){ 
            String firstName = generateRandomString(random, random.nextInt(5));
            String lastName = generateRandomString(random, random.nextInt(7));
            double gpa = generateRandomDouble(random, 1.0, 4.0);
            int uid = generateRandomInt(random, 1000000, 1999999);
            medium.put(new StudentMediumHash(uid, firstName, lastName), gpa);
        }
        return medium;
    }

    public static HashTable<StudentGoodHash, Double> generateGoodHashTable(int size){
        Random random = new Random();
        HashTable<StudentGoodHash, Double> good = new HashTable<StudentGoodHash, Double>();
        for (int i = 0; i < size; i ++){ 
            String firstName = generateRandomString(random, random.nextInt(5));
            String lastName = generateRandomString(random, random.nextInt(7));
            double gpa = generateRandomDouble(random, 1.0, 4.0);
            int uid = generateRandomInt(random, 1000000, 1999999);
            good.put(new StudentGoodHash(uid, firstName, lastName), gpa);
        }
        return good;
    }

    private static String generateRandomString(Random random, int length) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // ASCII values for uppercase letters (A-Z) are in the range 65 to 90
            // ASCII values for lowercase letters (a-z) are in the range 97 to 122
            int randomAsciiValue = random.nextInt(26); // Choose a random value between 0 and 25
            char randomChar = (char) ('a' + randomAsciiValue); // Convert ASCII value to character

            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    private static double generateRandomDouble(Random random, double min, double max) {
        // Calculate the range
        double range = max - min;

        // Generate a random value in the range and add it to the minimum value
        double randomValue = random.nextDouble() * range + min;

        // Round to the tenth decimal place
        randomValue = Math.round(randomValue * 1e10) / 1e10;

        return randomValue;
    }

    private static int generateRandomInt(Random random, int min, int max) {
        // Calculate the range
        int range = max - min;

        // Generate a random value in the range and add it to the minimum value
        int randomValue = random.nextInt(range) + min;

        return randomValue;
    }
}
