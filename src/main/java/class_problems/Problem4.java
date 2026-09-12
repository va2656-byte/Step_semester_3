import java.util.*;

class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(
            String patientId,
            String name) {

        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
        this.lockerPin = null;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {

        if (patientId == null) {
            patientId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(
            boolean discharged) {

        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {

        if (pin != null &&
                pin.matches("\\d{4,6}")) {

            lockerPin = pin;
        }
    }
}

public class Problem4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter patient name: ");
        String name = sc.nextLine();

        PatientProfile profile =
                new PatientProfile(name);

        System.out.println(
                "Patient ID: " +
                profile.getPatientId());

        System.out.print(
                "Enter patient ID: ");

        String id = sc.nextLine();

        profile.setPatientId(id);

        System.out.println(
                "Patient ID after first set: " +
                profile.getPatientId());

        System.out.print(
                "Enter another patient ID: ");

        String secondId = sc.nextLine();

        profile.setPatientId(secondId);

        System.out.println(
                "Patient ID after second set: " +
                profile.getPatientId());

        System.out.print(
                "Is patient discharged? (true/false): ");

        boolean discharged =
                sc.nextBoolean();

        profile.setDischarged(discharged);

        System.out.println(
                "Discharged: " +
                profile.isDischarged());

        System.out.print(
                "Enter locker PIN: ");

        String pin = sc.next();

        profile.setLockerPin(pin);

        System.out.println(
                "Locker PIN stored successfully.");

        sc.close();
    }
}