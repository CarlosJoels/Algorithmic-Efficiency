import java.util.Random;

public class AnalyzerTwo {

    public static void main(String[] args) {
        int[] array100 = generateData(100);
        int[] array1000 = generateData(1000);

        System.out.println("====== TEST WITH 100 DATA POINTS ======");
        processAndMeasure(array100, 10);

        System.out.println("\n====== TEST WITH 1000 DATA POINTS ======");
        processAndMeasure(array1000, 10);
    }

    public static void processAndMeasure(int[] data, int chunkSize) {
        int numberOfGroups = data.length / chunkSize;
        long operations = 0;

        long startTime = System.nanoTime();

        int[][] splitArrays = new int[numberOfGroups][chunkSize];
        int globalIndex = 0;

        for (int i = 0; i < numberOfGroups; i++) {
            for (int j = 0; j < chunkSize; j++) {
                splitArrays[i][j] = data[globalIndex];
                globalIndex++;
                operations++;
            }
        }

        int[] sums = new int[numberOfGroups];
        for (int i = 0; i < numberOfGroups; i++) {
            int currentSum = 0;
            for (int j = 0; j < chunkSize; j++) {
                currentSum += splitArrays[i][j];
                operations++;
            }
            sums[i] = currentSum;
        }

        int repeatedCount = 0;
        boolean[] alreadyCounted = new boolean[numberOfGroups];

        for (int i = 0; i < sums.length; i++) {
            if (!alreadyCounted[i]) {
                boolean hasDuplicate = false;
                for (int j = i + 1; j < sums.length; j++) {
                    operations++;
                    if ((sums[i] - sums[j]) == 0 && !alreadyCounted[j]) {
                        hasDuplicate = true;
                        alreadyCounted[j] = true;
                    }
                }
                if (hasDuplicate) {
                    repeatedCount++;
                }
            }
        }

        long endTime = System.nanoTime();
        double totalTimeMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("Resulting groups analyzed: " + numberOfGroups);
        System.out.println("Total operations performed: " + operations);
        System.out.println("Total groups with duplicates: " + repeatedCount);
        System.out.println("Execution time: " + totalTimeMs + " milliseconds.");
    }

    public static int[] generateData(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10) + 1;
        }
        return array;
    }
}