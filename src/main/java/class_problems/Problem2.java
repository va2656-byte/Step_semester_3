import java.util.*;

class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {

        if (totalFare < 0)
            throw new IllegalArgumentException("Fare cannot be negative");

        if (passengerCount <= 0)
            throw new IllegalArgumentException("Passenger count must be positive");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 1);
    }

    public double[] fareBreakdown() {

        double[] result = new double[passengerCount];

        if (totalFare == 0)
            return result;

        double share =
            Math.floor((totalFare / passengerCount) * 100) / 100;

        for (int i = 0; i < passengerCount; i++) {
            result[i] = share;
        }

        double assigned = share * passengerCount;

        double remainder =
            Math.round((totalFare - assigned) * 100) / 100.0;

        result[passengerCount - 1] += remainder;

        return result;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}

public class Problem2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter trip ID: ");
        String tripId = sc.nextLine();

        System.out.print("Enter total fare: ");
        double fare = sc.nextDouble();

        System.out.print("Enter passenger count: ");
        int passengers = sc.nextInt();

        try {

            FareSplitter splitter =
                new FareSplitter(tripId, fare, passengers);

            double[] result = splitter.fareBreakdown();

            System.out.println("Fare breakdown:");

            for (int i = 0; i < result.length; i++) {
                System.out.printf(
                    "Passenger %d: %.2f%n",
                    i + 1,
                    result[i]
                );
            }

            System.out.print("Enter confirmed passengers: ");
            int confirmed = sc.nextInt();

            System.out.print("Enter expected passengers: ");
            int expected = sc.nextInt();

            System.out.println(
                "Confirmation overdue: " +
                splitter.isConfirmationOverdue(confirmed, expected)
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}