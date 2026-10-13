
import java.util.HashSet;
import java.util.Objects;

class Customer {
    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Customer)) {
            return false;
        }

        Customer c = (Customer) obj;
        return id == c.id && name.equals(c.name);
    }

    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class Problem2 {
    public static void main(String[] args) {
        HashSet<Customer> customers = new HashSet<>();

        System.out.println(customers.add(new Customer(101, "Asha")));
        System.out.println(customers.add(new Customer(101, "Asha")));
        System.out.println(customers.add(new Customer(102, "Ravi")));

        System.out.println("Unique count: " + customers.size());
        System.out.println("Contains: " +
            customers.contains(new Customer(101, "Asha")));
    }
}
