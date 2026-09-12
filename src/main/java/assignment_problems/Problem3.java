import java.util.*;

class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(
        String canteenCode,
        String canteenName,
        int trustScore) {

        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(
        String canteenCode,
        String canteenName) {

        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {

        if (this.trustScore != other.trustScore) {
            return Integer.compare(
                other.trustScore,
                this.trustScore
            );
        }

        int codeResult =
            this.canteenCode.compareToIgnoreCase(
                other.canteenCode
            );

        if (codeResult != 0) {
            return codeResult;
        }

        return Integer.compare(
            this.canteenName.length(),
            other.canteenName.length()
        );
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {

        Canteen[] result = canteens.clone();

        for (int i = 0; i < result.length - 1; i++) {

            for (int j = 0;
                 j < result.length - i - 1;
                 j++) {

                if (result[j].compareTo(result[j + 1]) > 0) {

                    Canteen temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public void display() {
        System.out.println(
            canteenCode + " | " +
            canteenName + " | Trust Score: " +
            trustScore
        );
    }
}

public class Problem3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of canteens: ");
        int n = sc.nextInt();
        sc.nextLine();

        Canteen[] canteens = new Canteen[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nCanteen " + (i + 1));

            System.out.print("Enter canteen code: ");
            String code = sc.nextLine();

            System.out.print("Enter canteen name: ");
            String name = sc.nextLine();

            System.out.print(
                "Do you want to enter trust score? (yes/no): "
            );

            String choice = sc.nextLine();

            if (choice.equalsIgnoreCase("yes")) {

                System.out.print("Enter trust score: ");
                int score = sc.nextInt();
                sc.nextLine();

                canteens[i] =
                    new Canteen(code, name, score);

            } else {

                canteens[i] =
                    new Canteen(code, name);
            }
        }

        Canteen[] ranked =
            Canteen.rankCanteens(canteens);

        System.out.println("\nRanked Canteens:");

        for (Canteen canteen : ranked) {
            canteen.display();
        }

        sc.close();
    }
}