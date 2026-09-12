import java.util.*;

final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(
        double orderValue,
        int delayMinutes) {

        if (orderValue < 0) {
            throw new IllegalArgumentException(
                "Order value cannot be negative"
            );
        }

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
            orderValue *
            minimumSurgePercent / 100.0;

        return Math.max(fee, minimumFee);
    }
}

public class Problem4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
            "Enter minimum surge percentage: "
        );

        double minimumPercent = sc.nextDouble();

        System.out.print("Enter order value: ");
        double orderValue = sc.nextDouble();

        System.out.print("Enter delay minutes: ");
        int delayMinutes = sc.nextInt();

        try {

            SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(minimumPercent);

            double fee =
                calculator.calculateSurgeFee(
                    orderValue,
                    delayMinutes
                );

            System.out.println(
                "Surge Fee: Rs " + fee
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}