class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        amountPaid = 0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public static String registerBatch(String[] bibNumbers,
                                       double entryFee) {
        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < bibNumbers.length; i++) {
            try {
                new RaceEntry(bibNumbers[i], entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered
                + " | Rejected: " + rejected;
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee,
                       String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
}

public class Problem1 {
    public static void main(String[] args) {
        RunnerEntry r =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        r.pay(30);
        System.out.println(r.getBalanceDue());

        System.out.println(RaceEntry.registerBatch(
                new String[]{"BIB1", "B1", "BIB2"}, 80));

        try {
            new RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Construction rejected");
        }
    }
}