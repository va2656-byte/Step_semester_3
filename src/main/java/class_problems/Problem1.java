import java.util.*;

class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid passenger name");

        if (destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException("Invalid destination");

        if (!passengerName.matches("[a-zA-Z ]+"))
            throw new IllegalArgumentException("Invalid passenger name");

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    public void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
        }
    }

    static void processBatch(String[][] rawBookings) {
        ArrayList<String> accepted = new ArrayList<>();

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {
            try {
                BusTicket ticket =
                    new BusTicket(booking[0], booking[1]);

                String key = booking[0].trim().toLowerCase()
                           + "|" +
                           booking[1].trim().toLowerCase();

                if (accepted.contains(key)) {
                    duplicates++;
                } else {
                    accepted.add(key);
                    valid++;
                }

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
        System.out.println("Duplicates skipped: " + duplicates);
    }
}

public class Problem1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of bookings: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] bookings = new String[n][2];

        for (int i = 0; i < n; i++) {

            System.out.println("Booking " + (i + 1));

            System.out.print("Enter passenger name: ");
            bookings[i][0] = sc.nextLine();

            System.out.print("Enter destination: ");
            bookings[i][1] = sc.nextLine();
        }

        BusTicket.processBatch(bookings);

        sc.close();
    }
}