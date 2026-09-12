import java.util.*;

class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {

        if (copiesTotal <= 0) {
            throw new IllegalArgumentException(
                "Copies total must be positive"
            );
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class Problem3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total copies: ");
        int total = sc.nextInt();

        try {

            BookInventory book =
                new BookInventory(total);

            System.out.println(
                "Available copies: " +
                book.getCopiesAvailable()
            );

            System.out.print(
                "Enter number of checkouts: "
            );

            int checkout = sc.nextInt();

            for (int i = 0; i < checkout; i++) {
                book.checkOut();
            }

            System.out.println(
                "After checkout: " +
                book.getCopiesAvailable()
            );

            System.out.print(
                "Enter number of check-ins: "
            );

            int checkin = sc.nextInt();

            for (int i = 0; i < checkin; i++) {
                book.checkIn();
            }

            System.out.println(
                "After check-in: " +
                book.getCopiesAvailable()
            );

        } catch (Exception e) {

            System.out.println(
                "Construction rejected"
            );
        }

        sc.close();
    }
}