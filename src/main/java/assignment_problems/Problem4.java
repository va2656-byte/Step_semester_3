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
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber
                + " | Balance: " + getBalanceDue();
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee,
                       String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + bibNumber
                + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee,
                          int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + bibNumber
                + " | Team Size: " + teamSize
                + " | Balance: " + getBalanceDue();
    }
}

public class Problem4 {
    static String announceAll(RaceEntry[] entries) {
        StringBuilder report = new StringBuilder();

        for (int i = 0; i < entries.length; i++) {
            report.append(entries[i].announce()).append(" | ");

            if (entries[i] instanceof RelayTeamEntry) {
                RelayTeamEntry relay =
                        (RelayTeamEntry) entries[i];

                report.append("[Team size via downcast: ")
                      .append(relay.getTeamSize())
                      .append("] | ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {
        RunnerEntry runner =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        runner.pay(30);
        runner.pay(20);

        RelayTeamEntry relay =
                new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] fleet = {runner, relay};

        System.out.println(announceAll(fleet));

        RaceEntry plain = new RaceEntry("BIB5001", 50);

        if (plain instanceof RelayTeamEntry) {
            RelayTeamEntry bad = (RelayTeamEntry) plain;
            System.out.println(bad.getTeamSize());
        } else {
            System.out.println("Entry is not a relay team");
        }
    }
}