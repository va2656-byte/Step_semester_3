import java.util.*;

final class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    static int processedCount;

    static {
        processedCount = 0;
    }

    public DischargeSummary(
            String patientId,
            String[] medicationCodes) {

        this.patientId = patientId;

        if (medicationCodes == null) {
            throw new IllegalArgumentException(
                    "Medication list cannot be null");
        }

        this.medicationCodes =
                new String[medicationCodes.length];

        for (int i = 0;
             i < medicationCodes.length;
             i++) {

            String code =
                    medicationCodes[i];

            if (code == null ||
                    !code.matches("MED-[A-Z]")) {

                throw new IllegalArgumentException(
                        "Invalid medication code");
            }

            this.medicationCodes[i] = code;
        }
    }

    public String[] getMedicationCodes() {

        return medicationCodes.clone();
    }

    public DischargeSummary
    withCorrectedMedication(
            int index,
            String newCode) {

        if (index < 0 ||
                index >= medicationCodes.length) {

            throw new IndexOutOfBoundsException(
                    "Invalid index");
        }

        if (newCode == null ||
                !newCode.matches("MED-[A-Z]")) {

            throw new IllegalArgumentException(
                    "Invalid medication code");
        }

        String[] newCodes =
                medicationCodes.clone();

        newCodes[index] = newCode;

        return new DischargeSummary(
                patientId,
                newCodes);
    }
}

class CriticalCareDischargeSummary
        extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);

        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}

public class Problem5 {

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof
                    CriticalCareDischargeSummary) {

                criticalCare++;

            } else {

                routine++;
            }
        }

        return processed +
                " processed | " +
                nullSkipped +
                " null skipped | " +
                criticalCare +
                " critical-care | " +
                routine +
                " routine";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter number of discharge summaries: ");

        int n = sc.nextInt();
        sc.nextLine();

        DischargeSummary[] summaries =
                new DischargeSummary[n];

        for (int i = 0; i < n; i++) {

            System.out.println(
                    "\nSummary " + (i + 1));

            System.out.print(
                    "Enter type (routine/critical/null): ");

            String type = sc.nextLine();

            if (type.equalsIgnoreCase("null")) {

                summaries[i] = null;
                continue;
            }

            System.out.print(
                    "Enter patient ID: ");

            String patientId =
                    sc.nextLine();

            System.out.print(
                    "Enter number of medication codes: ");

            int m = sc.nextInt();
            sc.nextLine();

            String[] codes =
                    new String[m];

            for (int j = 0; j < m; j++) {

                System.out.print(
                        "Enter medication code " +
                        (j + 1) + ": ");

                codes[j] =
                        sc.nextLine();
            }

            try {

                if (type.equalsIgnoreCase(
                        "critical")) {

                    System.out.print(
                            "Enter ICU days: ");

                    int icuDays =
                            sc.nextInt();
                    sc.nextLine();

                    summaries[i] =
                            new CriticalCareDischargeSummary(
                                    patientId,
                                    codes,
                                    icuDays);

                } else {

                    summaries[i] =
                            new DischargeSummary(
                                    patientId,
                                    codes);
                }

                System.out.println(
                        "Summary created successfully.");

            } catch (Exception e) {

                System.out.println(
                        "Construction rejected.");

                i--;
            }
        }

        System.out.println(
                "\nNightly Batch Result:");

        System.out.println(
                processNightlyBatch(summaries));

        sc.close();
    }
}