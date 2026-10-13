class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid attendee ID");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        amountPaid = 0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    public static String registerBatch(String[] attendeeIds,
                                       double basePrice) {
        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < attendeeIds.length; i++) {
            try {
                new EventTicket(attendeeIds[i], basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered
                + " | Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice,
                          String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}

public class Problem1 {
    public static void main(String[] args) {
        WorkshopTicket w =
                new WorkshopTicket("STU2", 1200, "AI/ML");

        w.pay(500);
        System.out.println(w.getBalanceDue());

        System.out.println(EventTicket.registerBatch(
                new String[]{"STU1", "ST1", "STU2", " ", "STU3"},
                500));

        try {
            new EventTicket("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }
    }
}