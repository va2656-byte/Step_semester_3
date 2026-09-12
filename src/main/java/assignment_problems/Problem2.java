import java.util.*;

class DeliverySlot {
    private String orderId;
    private String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    public boolean isPeakHour() {
        return timeSlot.equals("12:00-13:00") ||
               timeSlot.equals("13:00-14:00") ||
               timeSlot.equals("19:00-20:00") ||
               timeSlot.equals("20:00-21:00");
    }

    public void display() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Time Slot: " + timeSlot);
        System.out.println("Peak Hour: " + isPeakHour());
    }
}

public class Problem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter order ID: ");
        String orderId = sc.nextLine();

        System.out.print("Do you want a scheduled slot? (yes/no): ");
        String choice = sc.nextLine();

        DeliverySlot slot;

        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter time slot: ");
            String timeSlot = sc.nextLine();

            slot = new DeliverySlot(orderId, timeSlot);
        } else {
            slot = new DeliverySlot(orderId);
        }

        System.out.println();
        slot.display();

        sc.close();
    }
}