class EventTicket {
    protected double basePrice;
    protected double amountPaid;

    private static int ticketsIssued = 1000;
    private final String ticketId;

    public EventTicket(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Invalid base price");
        }

        this.basePrice = basePrice;
        amountPaid = 0;

        ticketsIssued++;
        ticketId = "TCK-" + ticketsIssued;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    public String getTicketId() {
        return ticketId;
    }

    public static int getTicketsIssued() {
        return ticketsIssued - 1000;
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'F') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))
                || !Character.isDigit(code.charAt(2))
                || !Character.isDigit(code.charAt(3))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(4))
                && Character.isLetter(code.charAt(4));
    }

    public static String processNightlySettlement(
            EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] == null) {
                nullSkipped++;
            } else {
                processed++;

                if (tickets[i] instanceof GroupTicket) {
                    group++;
                } else {
                    individual++;
                }
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }
}

class GroupTicket extends EventTicket {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);

        if (groupSize <= 0) {
            throw new IllegalArgumentException("Invalid group size");
        }

        this.groupSize = groupSize;
    }
}

public class Problem5 {
    public static void main(String[] args) {
        EventTicket t1 = new EventTicket(500);

        System.out.println(t1.getTicketId());
        System.out.println(EventTicket.getTicketsIssued());

        System.out.println(
                EventTicket.isValidPromoCode("F123A"));
        System.out.println(
                EventTicket.isValidPromoCode("F12A"));
        System.out.println(
                EventTicket.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(t1.getBalanceDue());

        EventTicket[] tickets = {
            new GroupTicket(2000, 5),
            null,
            new EventTicket(500)
        };

        System.out.println(
                EventTicket.processNightlySettlement(tickets));
    }
}