
public class Problem4 {
    static int lowerBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prices[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static int upperBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prices[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};

        int low = lowerBound(prices, 150);
        int high = upperBound(prices, 300);

        System.out.println("Lower bound index " + low);
        System.out.println("Upper bound index " + high);
        System.out.println("Count " + (high - low));
    }
}
