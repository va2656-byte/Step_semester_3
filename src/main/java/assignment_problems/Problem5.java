class RaceEntry {
    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    private static int bibCounter = 0;
    private final String entryCode;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid bib number");
        }

        if (entryFee <= 0) {
            throw new IllegalArgumentException("Invalid entry fee");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.amountPaid = 0;

        bibCounter++;
        entryCode = "ENTRY" + bibCounter;
    }

    public void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    public void pay(double amount, String mode) {
        pay(amount);
        System.out.println("Paying via " + mode);
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
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

    public static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) {
                nullSkipped++;
            } else {
                processed++;

                if (entries[i] instanceof RelayTeamEntry) {
                    relay++;
                } else {
                    individual++;
                }
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + relay + " relay | "
                + individual + " individual";
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

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee,
                            String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee,
                          int teamSize) {
        super(bibNumber, entryFee);

        if (teamSize <= 0) {
            throw new IllegalArgumentException("Invalid team size");
        }

        this.teamSize = teamSize;
    }
}

public class Problem5 {
    public static void main(String[] args) {
        System.out.println(
                RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(
                RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(
                RaceEntry.isValidDiscountCode("X123A"));

        EliteRunnerEntry elite =
                new EliteRunnerEntry("BIB3001", 150,
                        "Elite Full Marathon", 500);

        RelayTeamEntry relay =
                new RelayTeamEntry("BIB4001", 300, 4);

        elite.pay(10, "UPI");

        RaceEntry[] entries = {elite, null, relay};

        System.out.println(RaceEntry.settleNight(entries));
        System.out.println(elite.getEntryCode());
        System.out.println(relay.getEntryCode());
        System.out.println(RaceEntry.getBibCounter());
    }
}