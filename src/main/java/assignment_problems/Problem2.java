
public class Problem2 {
    static int[] longestStreak(int[] costs, int budget) {
        int left = 0;
        int sum = 0;
        int maxLength = 0;
        int startIndex = -1;

        for (int right = 0; right < costs.length; right++) {
            sum += costs[right];

            while (sum > budget) {
                sum -= costs[left];
                left++;
            }

            int length = right - left + 1;

            if (length > maxLength) {
                maxLength = length;
                startIndex = left;
            }
        }

        return new int[]{maxLength, startIndex};
    }

    public static void main(String[] args) {
        int[] costs = {4, 2, 1, 7, 3, 1, 2, 1, 5};

        int[] result = longestStreak(costs, 8);
        System.out.println("(" + result[0] + ", " + result[1] + ")");

        int[] costs2 = {9, 10};
        int[] result2 = longestStreak(costs2, 8);
        System.out.println("(" + result2[0] + ", " + result2[1] + ")");
    }
}
