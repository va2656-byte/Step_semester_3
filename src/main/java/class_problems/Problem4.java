import java.util.*;

final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(
        double minimumPenaltyPercent) {

        this.minimumPenaltyPercent =
            minimumPenaltyPercent;
    }

    public final double calculatePenalty(
        double ticketFare,
        int minutesLate) {

        if (ticketFare < 0)
            throw new IllegalArgumentException(
                "Ticket fare cannot be negative"
            );

        if (minutesLate < 0)
            throw new IllegalArgumentException(
                "Minutes late cannot be negative"
            );

        if (minutesLate == 0)
            return 0.0;

        double penalty = 0.0;

        int firstTier =
            Math.min(minutesLate, 5);

        penalty +=
            ticketFare * 0.005 * firstTier;

        if (minutesLate > 5) {

            int secondTier =
                Math.min(minutesLate - 5, 10);

            penalty +=
                ticketFare * 0.01 * secondTier;
        }

        if (minutesLate > 15) {

            int thirdTier =
                minutesLate - 15;

            penalty +=
                ticketFare * 0.02 * thirdTier;
        }

        double minimumPenalty =
            ticketFare *
            minimumPenaltyPercent / 100.0;

        return Math.max(
            penalty,
            minimumPenalty
        );
    }
}

public class Problem4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter minimum penalty percentage: ");
        double minimumPercent = sc.nextDouble();

        System.out.print("Enter ticket fare: ");
        double fare = sc.nextDouble();

        System.out.print("Enter minutes late: ");
        int minutesLate = sc.nextInt();

        try {

            BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(
                    minimumPercent
                );

            double penalty =
                calculator.calculatePenalty(
                    fare,
                    minutesLate
                );

            System.out.println(
                "Penalty: Rs " + penalty
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        sc.close();
    }
}