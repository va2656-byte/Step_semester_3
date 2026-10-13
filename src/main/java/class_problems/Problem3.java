import java.util.Arrays;

class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

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

    protected void applyLateFee(double amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public double getBalanceDue() {
        double total = 0;

        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }

        return basePrice - amountPaid + total;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice,
                          String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        WorkshopTicket w =
                new WorkshopTicket("STU2", 1200, "AI/ML");

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;

        System.out.println(
                Arrays.toString(w.getLateFeeHistory()));
    }
}