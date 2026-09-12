import java.util.*;

class AccessChecker2 {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (fieldModifier.equals("private")) {

            return accessorContext.equals("SAME_CLASS")
                    ? "ALLOWED"
                    : "DENIED";
        }

        if (fieldModifier.equals("default")) {

            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED"
                    : "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String describeContext(
            String accessorContext) {

        String[] words =
            accessorContext.toLowerCase().split("_");

        String result = "";

        for (String word : words) {

            result +=
                Character.toUpperCase(word.charAt(0)) +
                word.substring(1) +
                " ";
        }

        return result.trim();
    }
}

public class Problem2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of attempts: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.println("\nAttempt " + (i + 1));

            System.out.print("Enter modifier: ");
            String modifier = sc.nextLine();

            System.out.print("Enter context: ");
            String context = sc.nextLine();

            String result =
                AccessChecker2.classifyAccess(
                    modifier,
                    context
                );

            System.out.println(
                "Result: " + result
            );

            System.out.println(
                "Context: " +
                AccessChecker2.describeContext(context)
            );
        }

        sc.close();
    }
}