import java.util.*;

class DeliveryAccount {

    protected String studentId;
    protected double orderValue;

    static int accountCount;

    static {
        accountCount = 0;
    }

    public DeliveryAccount(
        String studentId,
        double orderValue) {

        this.studentId = studentId;
        this.orderValue = orderValue;

        accountCount++;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(
        int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                "Delay minutes cannot be negative"
            );
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        int firstTier =
            Math.min(delayMinutes, 5);

        fee += orderValue * 0.005 * firstTier;

        if (delayMinutes > 5) {

            int secondTier =
                Math.min(delayMinutes - 5, 10);

            fee += orderValue * 0.01 * secondTier;
        }

        if (delayMinutes > 15) {

            int thirdTier =
                delayMinutes - 15;

            fee += orderValue * 0.02 * thirdTier;
        }

        double minimumFee =
            orderValue * 0.01;

        return Math.max(fee, minimumFee);
    }

    public void processAccount(
        DeliveryAccount account,
        double amount,
        int delayMinutes) {

        double fee =
            account.calculateSurgeFee(delayMinutes);

        System.out.println(
            account.studentId +
            " | Surge Fee: Rs " +
            fee
        );
    }
}

class Premium extends DeliveryAccount {

    public Premium(
        String studentId,
        double orderValue) {

        super(studentId, orderValue);
    }
}

public class Problem5 {

    static void processBatch(
        DeliveryAccount[] accounts,
        double[] amounts,
        int[] delayMinutesArray) {

        int limit =
            Math.min(
                accounts.length,
                Math.min(
                    amounts.length,
                    delayMinutesArray.length
                )
            );

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;

        double grandTotal = 0.0;

        for (int i = 0; i < limit; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double fee =
                accounts[i].calculateSurgeFee(
                    delayMinutesArray[i]
                );

            grandTotal += fee;
            processed++;

            if (accounts[i] instanceof Premium) {
                premiumCount++;
            } else {
                regularCount++;
            }
        }

        System.out.println(
            "\n" + processed + " processed"
        );

        System.out.println(
            nullSkipped + " null skipped"
        );

        System.out.println(
            premiumCount + " premium"
        );

        System.out.println(
            regularCount + " regular"
        );

        System.out.println(
            "Grand total surge fees = Rs " +
            grandTotal
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of accounts: ");
        int n = sc.nextInt();
        sc.nextLine();

        DeliveryAccount[] accounts =
            new DeliveryAccount[n];

        double[] amounts =
            new double[n];

        int[] delays =
            new int[n];

        for (int i = 0; i < n; i++) {

            System.out.println(
                "\nAccount " + (i + 1)
            );

            System.out.print(
                "Enter type (regular/premium/null): "
            );

            String type = sc.nextLine();

            if (type.equalsIgnoreCase("null")) {

                accounts[i] = null;

                System.out.print(
                    "Enter amount: "
                );

                amounts[i] = sc.nextDouble();

                System.out.print(
                    "Enter delay minutes: "
                );

                delays[i] = sc.nextInt();
                sc.nextLine();

            } else {

                System.out.print(
                    "Enter student ID: "
                );

                String studentId = sc.nextLine();

                System.out.print(
                    "Enter order value: "
                );

                double orderValue =
                    sc.nextDouble();

                System.out.print(
                    "Enter amount: "
                );

                amounts[i] =
                    sc.nextDouble();

                System.out.print(
                    "Enter delay minutes: "
                );

                delays[i] =
                    sc.nextInt();

                sc.nextLine();

                if (type.equalsIgnoreCase("premium")) {

                    accounts[i] =
                        new Premium(
                            studentId,
                            orderValue
                        );

                } else {

                    accounts[i] =
                        new DeliveryAccount(
                            studentId,
                            orderValue
                        );
                }
            }
        }

        processBatch(
            accounts,
            amounts,
            delays
        );

        sc.close();
    }
}