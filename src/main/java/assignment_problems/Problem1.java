import java.util.*;

class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode,
                         double finesOwed, String displayName) {

        if (membershipId == null ||
            membershipId.trim().isEmpty() ||
            membershipId.trim().length() < 4) {

            throw new IllegalArgumentException(
                "Invalid membership ID"
            );
        }

        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

class AccessChecker {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(
            String[][] attempts) {

        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;

        for (String[] attempt : attempts) {

            String modifier = attempt[0];
            String context = attempt[1];

            String result =
                classifyAccess(modifier, context);

            if (modifier.equals("private")) {

                if (result.equals("ALLOWED"))
                    privateAllowed++;
                else
                    privateDenied++;

            } else if (modifier.equals("default")) {

                if (result.equals("ALLOWED"))
                    defaultAllowed++;
                else
                    defaultDenied++;

            } else if (modifier.equals("protected")) {

                if (result.equals("ALLOWED"))
                    protectedAllowed++;
                else
                    protectedDenied++;

            } else if (modifier.equals("public")) {

                if (result.equals("ALLOWED"))
                    publicAllowed++;
                else
                    publicDenied++;
            }
        }

        return "private: " + privateAllowed +
               " allowed / " + privateDenied + " denied | " +

               "default: " + defaultAllowed +
               " allowed / " + defaultDenied + " denied | " +

               "protected: " + protectedAllowed +
               " allowed / " + protectedDenied + " denied | " +

               "public: " + publicAllowed +
               " allowed / " + publicDenied + " denied";
    }
}

public class Problem1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of attempts: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {

            System.out.println("\nAttempt " + (i + 1));

            System.out.print(
                "Enter modifier (private/default/protected/public): "
            );

            attempts[i][0] = sc.nextLine();

            System.out.print(
                "Enter context (SAME_CLASS/SAME_PACKAGE/DIFFERENT_PACKAGE): "
            );

            attempts[i][1] = sc.nextLine();
        }

        System.out.println(
            AccessChecker.summarizeByModifier(attempts)
        );

        System.out.println("\nLibrary Member Test");

        System.out.print("Enter membership ID: ");
        String id = sc.nextLine();

        System.out.print("Enter branch code: ");
        String branch = sc.nextLine();

        System.out.print("Enter fines owed: ");
        double fines = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter display name: ");
        String name = sc.nextLine();

        try {

            LibraryMember member =
                new LibraryMember(
                    id, branch, fines, name
                );

            System.out.println(
                "Library member created successfully."
            );

        } catch (Exception e) {

            System.out.println(
                "Construction rejected"
            );
        }

        sc.close();
    }
}