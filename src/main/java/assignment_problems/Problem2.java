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

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public void announce() {
        System.out.println("Race Entry | Bib: " + bibNumber
                + " | Balance: " + getBalanceDue());
    }
}

class RunnerEntry extends RaceEntry {
    protected String category;

    public RunnerEntry(String bibNumber, double entryFee,
                       String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public void announce() {
        System.out.println("Runner Entry | Bib: " + bibNumber
                + " | Category: " + category
                + " | Balance: " + getBalanceDue());
    }
}

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee,
                            String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public void announce() {
        System.out.println("Elite Runner | Bib: " + bibNumber
                + " | Category: " + category
                + " | Sponsor Bonus: " + sponsorBonus
                + " | Balance: " + getBalanceDue());
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee,
                          int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    public void announce() {
        System.out.println("Relay Team | Bib: " + bibNumber
                + " | Team Size: " + teamSize
                + " | Balance: " + getBalanceDue());
    }
}

public class Problem2 {
    static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else {
            return "Base or direct descendant";
        }
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;

        for (int i = 0; i < entries.length; i++) {
            total += entries[i].getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {
        RunnerEntry runner =
                new RunnerEntry("BIB2001", 80, "Open 10K");

        EliteRunnerEntry elite =
                new EliteRunnerEntry("BIB3001", 150,
                        "Elite Full Marathon", 500);

        RelayTeamEntry relay =
                new RelayTeamEntry("BIB4001", 300, 4);

        runner.announce();
        elite.announce();
        relay.announce();

        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(relay));

        RaceEntry[] entries = {runner, elite, relay};
        System.out.println(getTotalBalanceDue(entries));
    }
}