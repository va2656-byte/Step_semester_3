import java.util.*;

class PatientRecord {
    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode,
                         double vitalsScore, String facilityName) {

        if (patientId == null || patientId.trim().isEmpty()
                || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid patient ID");
        }

        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

class AccessRuleEngine {

    static String classifyAccess(String fieldModifier,
                                  String accessorContext) {

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {

            String result =
                    classifyAccess(attempt[0], attempt[1]);

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed +
               " | Denied: " + denied;
    }
}

public class Problem1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of access attempts: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {

            System.out.println("\nAttempt " + (i + 1));

            System.out.print(
                    "Enter modifier (private/default/protected/public): ");
            attempts[i][0] = sc.nextLine();

            System.out.print(
                    "Enter context: ");
            attempts[i][1] = sc.nextLine();
        }

        System.out.println(
                AccessRuleEngine.summarizeBatch(attempts));

        System.out.println("\nPatient Record Test");

        System.out.print("Enter patient ID: ");
        String patientId = sc.nextLine();

        System.out.print("Enter ward code: ");
        String wardCode = sc.nextLine();

        System.out.print("Enter vitals score: ");
        double vitalsScore = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter facility name: ");
        String facilityName = sc.nextLine();

        try {

            PatientRecord patient =
                    new PatientRecord(
                            patientId,
                            wardCode,
                            vitalsScore,
                            facilityName);

            System.out.println("Patient record created successfully.");

        } catch (Exception e) {
            System.out.println("Construction rejected");
        }

        sc.close();
    }
}