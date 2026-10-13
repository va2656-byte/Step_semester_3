
import java.util.*;

interface PricingPlan {
    double calculatePrice(double price);
    String getName();
}

class DayScholarPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price;
    }

    public String getName() {
        return "Day Scholar";
    }
}

class HostellerPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.90;
    }

    public String getName() {
        return "Hosteller";
    }
}

class StaffPlan implements PricingPlan {
    public double calculatePrice(double price) {
        return price * 0.80;
    }

    public String getName() {
        return "Staff";
    }
}

class Transaction {
    String description;
    double amount;

    Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }
}

class Purchase {
    String itemName;
    double amount;
    boolean refunded = false;

    Purchase(String itemName, double amount) {
        this.itemName = itemName;
        this.amount = amount;
    }
}

class SmartCard {
    private String cardId;
    private PricingPlan plan;
    private double balance = 0;
    private boolean blocked = false;

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Purchase> purchases = new ArrayList<>();

    SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
    }

    public String getCardId() {
        return cardId;
    }

    public double getBalance() {
        return balance;
    }

    public void block() {
        blocked = true;
        System.out.println(cardId + " blocked.");
    }

    public void unblock() {
        blocked = false;
        System.out.println(cardId + " unblocked.");
    }

    public void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up rejected: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println("Top-up rejected: Minimum top-up is ₹100.00.");
            return;
        }

        if (balance + amount > 5000) {
            System.out.println("Top-up rejected: Maximum balance is ₹5000.00.");
            return;
        }

        balance += amount;
        transactions.add(new Transaction("Top-up", amount));

        System.out.printf("%s topped up with ₹%.2f.%n", cardId, amount);
        System.out.printf("Balance: ₹%.2f%n", balance);
    }

    public Purchase purchase(String itemName, double originalPrice) {
        if (blocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return null;
        }

        if (originalPrice <= 0) {
            System.out.println("Purchase failed: Invalid item price.");
            return null;
        }

        double charged = plan.calculatePrice(originalPrice);

        if (balance < charged) {
            System.out.printf(
                    "Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                    charged, balance);
            return null;
        }

        balance -= charged;
        transactions.add(new Transaction(itemName, -charged));

        Purchase p = new Purchase(itemName, charged);
        purchases.add(p);

        System.out.printf("%s purchased for ₹%.2f.%n", itemName, charged);
        System.out.printf("Balance: ₹%.2f%n", balance);

        return p;
    }

    public void refund(Purchase purchase) {
        if (purchase == null || !purchases.contains(purchase)) {
            System.out.println("Refund rejected: Purchase not found.");
            return;
        }

        if (purchase.refunded) {
            System.out.println("Refund rejected: " + purchase.itemName
                    + " has already been refunded.");
            return;
        }

        if (balance + purchase.amount > 5000) {
            System.out.println("Refund rejected: Maximum balance would be exceeded.");
            return;
        }

        purchase.refunded = true;
        balance += purchase.amount;

        transactions.add(new Transaction(
                "Refund: " + purchase.itemName, purchase.amount));

        System.out.printf("Refund of ₹%.2f for %s processed.%n",
                purchase.amount, purchase.itemName);
        System.out.printf("Balance: ₹%.2f%n", balance);
    }

    public void miniStatement() {
        System.out.print("Mini-statement for " + cardId + ": ");

        for (int i = 0; i < transactions.size(); i++) {
            double amount = transactions.get(i).amount;

            if (amount >= 0) {
                System.out.printf("+%.2f", amount);
            } else {
                System.out.printf("-%.2f", Math.abs(amount));
            }

            if (i < transactions.size() - 1) {
                System.out.print(", ");
            }
        }

        System.out.printf(" = ₹%.2f%n", balance);
    }

    public boolean checkBalanceInvariant() {
        double total = 0;

        for (Transaction t : transactions) {
            total += t.amount;
        }

        return Math.abs(total - balance) < 0.001 && balance >= 0;
    }
}

public class Problem5 {
    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());

        card.topUp(500);

        Purchase thali = card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Large Order", 400);

        card.refund(thali);
        card.refund(thali);

        card.miniStatement();

        System.out.println("Balance invariant valid: "
                + card.checkBalanceInvariant());
    }
}
