import java.util.*;

class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name");
        }

        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    public void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order delivered successfully.");
        } else {
            System.out.println("Order was already delivered.");
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }
}

public class Problem1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of orders: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] orders = new String[n][2];

        for (int i = 0; i < n; i++) {
            System.out.println("\nOrder " + (i + 1));

            System.out.print("Enter student name: ");
            orders[i][0] = sc.nextLine();

            System.out.print("Enter dish name: ");
            orders[i][1] = sc.nextLine();
        }

        FoodOrder.processBatch(orders);

        System.out.print("\nEnter student name for delivery test: ");
        String name = sc.nextLine();

        System.out.print("Enter dish name: ");
        String dish = sc.nextLine();

        try {
            FoodOrder order = new FoodOrder(name, dish);

            order.markDelivered();
            order.markDelivered();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}