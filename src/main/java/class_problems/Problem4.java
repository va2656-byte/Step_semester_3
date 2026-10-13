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

    public double getBalanceDue() {
        return basePrice - amountPaid;
    }

    public String printTicket() {
        return "Standard | Balance: " + getBalanceDue();
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice,
                          String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    @Override
    public String printTicket() {
        return "Workshop | Track: " + track
                + " | Balance: " + getBalanceDue();
    }
}

public class Problem4 {
    static String batchPrint(EventTicket[] tickets) {
        StringBuilder report = new StringBuilder();

        for (int i = 0; i < tickets.length; i++) {
            report.append(tickets[i].printTicket()).append(" | ");

            if (tickets[i] instanceof WorkshopTicket) {
                WorkshopTicket w = (WorkshopTicket) tickets[i];

                report.append("[Track via downcast: ")
                      .append(w.getTrack())
                      .append("] | ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {
        EventTicket standard =
                new EventTicket("STU1", 500);

        WorkshopTicket workshop =
                new WorkshopTicket("STU2", 1200, "AI/ML");

        EventTicket[] tickets = {standard, workshop};

        System.out.println(batchPrint(tickets));

        EventTicket plain = new EventTicket("STU3", 500);

        if (plain instanceof WorkshopTicket) {
            WorkshopTicket bad = (WorkshopTicket) plain;
            System.out.println(bad.getTrack());
        } else {
            System.out.println("Ticket is not a workshop ticket");
        }
    }
}