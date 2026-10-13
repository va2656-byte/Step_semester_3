
import java.util.*;

public class Problem3 {
    static long countPeriods(int[] transactions, int k) {
        Map<Long, Integer> frequency = new HashMap<>();
        frequency.put(0L, 1);

        long sum = 0;
        long count = 0;

        for (int value : transactions) {
            sum += value;

            long needed = sum - k;

            count += frequency.getOrDefault(needed, 0);

            frequency.put(sum, frequency.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};

        System.out.println(countPeriods(transactions, 7));

        int[] transactions2 = {1, 2, 3};
        System.out.println(countPeriods(transactions2, 10));
    }
}
