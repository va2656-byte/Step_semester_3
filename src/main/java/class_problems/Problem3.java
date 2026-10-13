
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

abstract class Room {
    String name;
    double rate;

    Room(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    abstract double calculatePrice(long days);
}

class StandardRoom extends Room {
    StandardRoom(String name) {
        super(name, 150);
    }

    double calculatePrice(long days) {
        return rate * days;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String name) {
        super(name, 200);
    }

    double calculatePrice(long days) {
        return rate * days;
    }
}

class Reservation {
    Room room;
    LocalDate start;
    LocalDate end;
    LocalDate cancelDeadline;
    boolean active = true;

    Reservation(Room room, LocalDate start, LocalDate end,
                LocalDate cancelDeadline) {
        this.room = room;
        this.start = start;
        this.end = end;
        this.cancelDeadline = cancelDeadline;
    }

    boolean overlaps(LocalDate s, LocalDate e) {
        return active && s.isBefore(end) && start.isBefore(e);
    }

    boolean cancel(LocalDate today) {
        if (!active || today.isAfter(cancelDeadline)) {
            return false;
        }

        active = false;
        return true;
    }
}

class BookingManager {
    ArrayList<Reservation> reservations = new ArrayList<>();

    Reservation book(Room room, LocalDate start, LocalDate end,
                     LocalDate cancelDeadline) {
        if (!start.isBefore(end)) {
            System.out.println("Invalid booking dates.");
            return null;
        }

        for (Reservation r : reservations) {
            if (r.room == room && r.overlaps(start, end)) {
                System.out.println("Booking failed: " + room.name
                        + " is not available for " + start + " to " + end);
                return null;
            }
        }

        Reservation r = new Reservation(
                room, start, end, cancelDeadline);
        reservations.add(r);

        long days = ChronoUnit.DAYS.between(start, end);

        System.out.println(room.name + " booked from " + start
                + " to " + end + ".");

        System.out.printf("Total price: $%.2f%n",
                room.calculatePrice(days));

        return r;
    }

    void cancel(Reservation r, LocalDate today) {
        if (r != null && r.cancel(today)) {
            System.out.println("Reservation for " + r.room.name
                    + " cancelled successfully.");
        } else {
            System.out.println("Cancellation failed.");
        }
    }
}

public class Problem3 {
    public static void main(String[] args) {
        BookingManager manager = new BookingManager();

        Room deluxe = new DeluxeRoom("Deluxe Room 101");
        Room standard = new StandardRoom("Standard Room 205");

        LocalDate s1 = LocalDate.parse("2024-12-01");
        LocalDate e1 = LocalDate.parse("2024-12-05");
        LocalDate s2 = LocalDate.parse("2024-12-03");
        LocalDate e2 = LocalDate.parse("2024-12-07");

        Reservation r1 = manager.book(
                deluxe, s1, e1, LocalDate.parse("2024-11-30"));

        manager.book(
                standard, s2, e2, LocalDate.parse("2024-12-02"));

        manager.book(
                deluxe, s2, e2, LocalDate.parse("2024-12-02"));

        manager.cancel(r1, LocalDate.parse("2024-11-29"));
    }
}
