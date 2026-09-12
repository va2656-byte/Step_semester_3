import java.util.*;

class BusTicketAccount {

    protected String bookingId;
    protected double ticketFare;

    static int accountCount;

    static {
        accountCount = 0;
    }

    public BusTicketAccount(
        String bookingId,
        double ticketFare) {

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;

        accountCount++;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(
        int minutesLate) {

        if (minutesLate < 0)
            throw new IllegalArgumentException(
                "Minutes late cannot be negative"
            );

        if (minutesLate == 0)
            return 0.0;

        return ticketFare *
               0.01 *
               minutesLate;
    }
}

class Sleeper extends BusTicketAccount {

    public Sleeper(
        String bookingId,
        double ticketFare) {

        super(bookingId, ticketFare);
    }
}

public class Problem5 {

    static void processBatch(
        BusTicketAccount[] accounts,
        double[] amounts,
        int[] minutesLateArray) {

        int limit =
            Math.min(
                accounts.length,
                Math.min(
                    amounts.length,
                    minutesLateArray.length
                )
            );

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;

        double grandTotal = 0.0;

        for (int i = 0; i < limit; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double penalty =
                accounts[i].calculatePenalty(
                    minutesLateArray[i]
                );

            grandTotal += penalty;
            processed++;

            if (accounts[i] instanceof Sleeper) {
                sleeperCount++;
            } else {
                regularCount++;
            }
        }

        System.out.println(
            processed + " processed"
        );

        System.out.println(
            nullSkipped + " null skipped"
        );

        System.out.println(
            sleeperCount + " sleeper"
        );

        System.out.println(
            regularCount + " regular"
        );

        System.out.println(
            "Grand total penalties = Rs "
            + grandTotal
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of accounts: ");
        int n = sc.nextInt();
        sc.nextLine();

        BusTicketAccount[] accounts =
            new BusTicketAccount[n];

        double[] amounts =
            new double[n];

        int[] minutesLate =
            new int[n];

        for (int i = 0; i < n; i++) {

            System.out.println(
                "\nAccount " + (i + 1)
            );

            System.out.print(
                "Enter account type (regular/sleeper/null): "
            );

            String type = sc.nextLine();

            if (type.equalsIgnoreCase("null")) {

                accounts[i] = null;

            } else {

                System.out.print(
                    "Enter booking ID: "
                );

                String id = sc.nextLine();

                System.out.print(
                    "Enter ticket fare: "
                );

                double fare = sc.nextDouble();

                System.out.print(
                    "Enter amount: "
                );

                amounts[i] = sc.nextDouble();

                System.out.print(
                    "Enter minutes late: "
                );

                minutesLate[i] = sc.nextInt();

                sc.nextLine();

                if (type.equalsIgnoreCase("sleeper")) {

                    accounts[i] =
                        new Sleeper(id, fare);

                } else {

                    accounts[i] =
                        new BusTicketAccount(
                            id,
                            fare
                        );
                }
            }
        }

        processBatch(
            accounts,
            amounts,
            minutesLate
        );

        sc.close();
    }
}