
abstract class Vehicle {
    String name;
    double rate;
    boolean available = true;

    Vehicle(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {
    StandardCar(String name) {
        super(name, 50);
    }

    double calculateCharge(int days) {
        return rate * days;
    }
}

class LuxuryCar extends Vehicle {
    LuxuryCar(String name) {
        super(name, 100);
    }

    double calculateCharge(int days) {
        return rate * days;
    }
}

class Rental {
    Vehicle vehicle;
    int days;
    boolean active = true;

    Rental(Vehicle vehicle, int days) {
        this.vehicle = vehicle;
        this.days = days;
    }

    void returnVehicle() {
        if (active) {
            active = false;
            vehicle.available = true;
            System.out.println(vehicle.name + " returned. Now available.");
        }
    }
}

class RentalService {
    Rental rent(Vehicle v, int days) {
        if (!v.available) {
            System.out.println(v.name + " is not available.");
            return null;
        }

        if (days <= 0) {
            System.out.println("Invalid rental duration.");
            return null;
        }

        v.available = false;
        Rental r = new Rental(v, days);

        System.out.printf("%s rented for %d days. Total charge: $%.2f%n",
                v.name, days, v.calculateCharge(days));

        return r;
    }
}

public class Problem2 {
    public static void main(String[] args) {
        RentalService service = new RentalService();

        Vehicle v1 = new LuxuryCar("Luxury Car A");
        Vehicle v2 = new StandardCar("Standard Car B");

        Rental r1 = service.rent(v1, 3);
        Rental r2 = service.rent(v2, 5);

        if (r1 != null) {
            r1.returnVehicle();
        }
    }
}
