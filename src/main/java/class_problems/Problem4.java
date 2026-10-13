
public class Problem4 {
    static String findBook(String[][] catalog, String targetIsbn) {
        int left = 0;
        int right = catalog.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (catalog[mid][0].equals(targetIsbn)) {
                return catalog[mid][1];
            } else if (catalog[mid][0].compareTo(targetIsbn) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        String[][] catalog = {
            {"0001112223", "Intro to Algebra"},
            {"0002223334", "Beginning Python"}
        };

        System.out.println(findBook(catalog, "0002223334"));
        System.out.println(findBook(catalog, "0009998887"));
    }
}
