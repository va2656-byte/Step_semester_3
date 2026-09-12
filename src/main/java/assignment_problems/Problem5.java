import java.util.*;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static int processedCount;

    static {
        processedCount = 0;
    }

    public LoanReceipt(
            String memberId,
            String[] bookIds) {

        this.memberId = memberId;

        if (bookIds == null) {
            throw new IllegalArgumentException(
                "Book ID list cannot be null"
            );
        }

        this.bookIds =
            new String[bookIds.length];

        for (int i = 0; i < bookIds.length; i++) {

            String id = bookIds[i];

            if (id == null ||
                !id.matches("BK-\\d{3}")) {

                throw new IllegalArgumentException(
                    "Invalid book ID"
                );
            }

            this.bookIds[i] = id;
        }
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        if (index < 0 ||
            index >= bookIds.length) {

            throw new IndexOutOfBoundsException(
                "Invalid index"
            );
        }

        if (newId == null ||
            !newId.matches("BK-\\d{3}")) {

            throw new IllegalArgumentException(
                "Invalid book ID"
            );
        }

        String[] newBookIds =
            bookIds.clone();

        newBookIds[index] = newId;

        return new LoanReceipt(
            memberId,
            newBookIds
        );
    }
}

class ReferenceOnlyLoanReceipt
        extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

public class Problem5 {

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof
                    ReferenceOnlyLoanReceipt) {

                referenceOnly++;

            } else {

                regular++;
            }
        }

        return processed +
            " processed | " +
            nullSkipped +
            " null skipped | " +
            referenceOnly +
            " reference-only | " +
            regular +
            " regular";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
            "Enter number of receipts: "
        );

        int n = sc.nextInt();
        sc.nextLine();

        LoanReceipt[] receipts =
            new LoanReceipt[n];

        for (int i = 0; i < n; i++) {

            System.out.println(
                "\nReceipt " + (i + 1)
            );

            System.out.print(
                "Enter type (regular/reference/null): "
            );

            String type = sc.nextLine();

            if (type.equalsIgnoreCase("null")) {

                receipts[i] = null;
                continue;
            }

            System.out.print(
                "Enter member ID: "
            );

            String memberId = sc.nextLine();

            System.out.print(
                "Enter number of book IDs: "
            );

            int m = sc.nextInt();
            sc.nextLine();

            String[] bookIds =
                new String[m];

            for (int j = 0; j < m; j++) {

                System.out.print(
                    "Enter book ID " +
                    (j + 1) + ": "
                );

                bookIds[j] =
                    sc.nextLine();
            }

            try {

                if (type.equalsIgnoreCase(
                        "reference")) {

                    System.out.print(
                        "Enter room number: "
                    );

                    String room =
                        sc.nextLine();

                    receipts[i] =
                        new ReferenceOnlyLoanReceipt(
                            memberId,
                            bookIds,
                            room
                        );

                } else {

                    receipts[i] =
                        new LoanReceipt(
                            memberId,
                            bookIds
                        );
                }

                System.out.println(
                    "Receipt created successfully."
                );

            } catch (Exception e) {

                System.out.println(
                    "Construction rejected."
                );

                i--;
            }
        }

        System.out.println(
            "\nNightly Circulation Result:"
        );

        System.out.println(
            processNightlyCirculation(receipts)
        );

        sc.close();
    }
}