
import java.util.*;

interface ShippingType {
    double calculateCharge(double weight);
}

class StandardShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + 10 * weight;
    }
}

class ExpressShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 80 + 15 * weight;
    }
}

class FragileShipping implements ShippingType {
    private ShippingType standard = new StandardShipping();

    public double calculateCharge(double weight) {
        return standard.calculateCharge(weight) + 50;
    }
}

interface NotificationChannel {
    void notify(String parcelId, String status);
}

class SmsChannel implements NotificationChannel {
    public void notify(String id, String status) {
        System.out.println("[SMS] " + id + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String id, String status) {
        System.out.println("[Email] " + id + " is now " + status + ".");
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Parcel {
    String id;
    Customer customer;
    double weight;
    ShippingType shipping;
    String status = "BOOKED";
    ArrayList<NotificationChannel> channels = new ArrayList<>();

    Parcel(String id, Customer customer, double weight,
           ShippingType shipping) {
        this.id = id;
        this.customer = customer;
        this.weight = weight;
        this.shipping = shipping;
    }

    void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(id, status);
        }
    }

    boolean updateStatus(String newStatus) {
        String expected = "";

        switch (status) {
            case "BOOKED":
                expected = "PICKED_UP";
                break;
            case "PICKED_UP":
                expected = "IN_TRANSIT";
                break;
            case "IN_TRANSIT":
                expected = "OUT_FOR_DELIVERY";
                break;
            case "OUT_FOR_DELIVERY":
                expected = "DELIVERED";
                break;
            default:
                break;
        }

        if (!newStatus.equals(expected)) {
            System.out.println("Invalid transition: " + status + " → "
                    + newStatus + " is not allowed.");
            return false;
        }

        status = newStatus;
        notifyChannels();
        return true;
    }

    void cancel() {
        if (!status.equals("BOOKED")) {
            System.out.println("Cancellation failed: " + id
                    + " can be cancelled only while BOOKED.");
            return;
        }

        status = "CANCELLED";
        notifyChannels();
        System.out.println("Parcel " + id + " cancelled.");
    }
}

class ParcelService {
    Parcel book(String id, Customer customer, double weight,
                ShippingType shipping) {
        if (weight <= 0) {
            System.out.println("Booking failed: Weight must be positive.");
            return null;
        }

        Parcel p = new Parcel(id, customer, weight, shipping);

        System.out.println("Parcel " + id + " booked ("
                + shipping.getClass().getSimpleName().replace("Shipping", "")
                + ", " + weight + " kg).");

        System.out.printf("Charge: ₹%.2f%n",
                shipping.calculateCharge(weight));

        p.notifyChannels();
        return p;
    }
}

public class Problem2 {
    public static void main(String[] args) {
        Customer c = new Customer("Asha");
        ParcelService service = new ParcelService();

        Parcel p = service.book(
                "P101", c, 2, new ExpressShipping());

        p.subscribe(new SmsChannel());
        p.subscribe(new EmailChannel());

        // Send the initial BOOKED update to newly subscribed channels.
        p.notifyChannels();

        p.updateStatus("PICKED_UP");
        p.cancel();
        p.updateStatus("IN_TRANSIT");
        p.updateStatus("DELIVERED");
    }
}
