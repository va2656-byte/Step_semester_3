
import java.util.ArrayList;

interface IPaymentMethod {
    boolean pay(double amount);
    String getName();
}

class CreditCardPayment implements IPaymentMethod {
    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment implements IPaymentMethod {
    public boolean pay(double amount) {
        return false;
    }

    public String getName() {
        return "Digital Wallet";
    }
}

class CashOnDelivery implements IPaymentMethod {
    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Cash on Delivery";
    }
}

class FoodItem {
    String name;
    double price;

    FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class LineItem {
    FoodItem item;
    int quantity;

    LineItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotal() {
        return item.price * quantity;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class FoodOrder {
    private static int nextId = 122;

    int orderId;
    Customer customer;
    ArrayList<LineItem> items = new ArrayList<>();
    String status = "Pending Payment";

    FoodOrder(Customer customer) {
        this.customer = customer;
        orderId = ++nextId;
        System.out.println("Order created.");
    }

    void addItem(FoodItem item, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        items.add(new LineItem(item, quantity));

        System.out.println("Added " + item.name
                + " (Qty " + quantity + ")");
    }

    double getTotal() {
        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    void placeOrder(IPaymentMethod payment) {
        if (items.isEmpty()) {
            System.out.println(
                    "Cannot place order: Order must contain at least one item.");
            return;
        }

        System.out.println("Order placed successfully.");

        if (payment.pay(getTotal())) {
            status = "Paid";

            System.out.println("Payment via " + payment.getName()
                    + " successful.");
            System.out.println("Order status: " + status);
            System.out.println("Notification: Order #" + orderId
                    + " placed and paid.");
        } else {
            System.out.println("Payment via " + payment.getName()
                    + " failed.");
            System.out.println("Order status: " + status);
            System.out.println("Notification: Order #" + orderId
                    + " placed, awaiting payment.");
        }
    }
}

public class Problem5 {
    public static void main(String[] args) {
        Customer c = new Customer("Alex");

        FoodOrder o1 = new FoodOrder(c);

        o1.addItem(new FoodItem("Pizza", 12), 2);
        o1.addItem(new FoodItem("Soda", 2), 1);
        o1.placeOrder(new CreditCardPayment());

        FoodOrder o2 = new FoodOrder(c);

        o2.addItem(new FoodItem("Burger", 8), 1);
        o2.placeOrder(new DigitalWalletPayment());
    }
}
