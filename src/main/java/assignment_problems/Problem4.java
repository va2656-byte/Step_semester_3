
import java.util.*;

interface CreditPolicy {
    int getCreditLimit();
    String getType();
}

class RegularPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 24;
    }

    public String getType() {
        return "Regular";
    }
}

class HonorsPolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 28;
    }

    public String getType() {
        return "Honors";
    }
}

class ExchangePolicy implements CreditPolicy {
    public int getCreditLimit() {
        return 20;
    }

    public String getType() {
        return "Exchange";
    }
}

class Student {
    String name;
    CreditPolicy policy;
    int currentCredits;

    Student(String name, CreditPolicy policy, int currentCredits) {
        this.name = name;
        this.policy = policy;
        this.currentCredits = currentCredits;
    }

    boolean canAddCredits(int credits) {
        return currentCredits + credits <= policy.getCreditLimit();
    }

    void addCredits(int credits) {
        currentCredits += credits;
    }

    void removeCredits(int credits) {
        currentCredits -= credits;
    }
}

class Elective {
    String name;
    int credits;
    int capacity;

    private ArrayList<Student> enrolled = new ArrayList<>();
    private Queue<Student> waitlist = new LinkedList<>();

    Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    boolean contains(Student s) {
        for (Student student : enrolled) {
            if (student == s) {
                return true;
            }
        }

        for (Student student : waitlist) {
            if (student == s) {
                return true;
            }
        }

        return false;
    }

    boolean isFull() {
        return enrolled.size() >= capacity;
    }

    boolean enroll(Student s) {
        if (contains(s)) {
            System.out.println(s.name
                    + " is already enrolled or waitlisted.");
            return false;
        }

        if (!s.canAddCredits(credits)) {
            System.out.println("Enrollment failed: " + s.name
                    + " would exceed the " + s.policy.getType()
                    + " credit limit (" + (s.currentCredits + credits)
                    + "/" + s.policy.getCreditLimit() + ").");
            return false;
        }

        if (isFull()) {
            waitlist.add(s);
            System.out.println(name + " is full.");
            System.out.println(s.name + " added to waitlist (position "
                    + waitlist.size() + ").");
            return false;
        }

        enrolled.add(s);
        s.addCredits(credits);

        System.out.println(s.name + " enrolled in " + name
                + " (credits: " + s.currentCredits + "/"
                + s.policy.getCreditLimit() + ").");

        return true;
    }

    void drop(Student s) {
        if (!enrolled.remove(s)) {
            System.out.println(s.name + " is not enrolled in " + name + ".");
            return;
        }

        s.removeCredits(credits);

        System.out.println(s.name + " dropped " + name
                + " (credits: " + s.currentCredits + "/"
                + s.policy.getCreditLimit() + ").");

        promoteNext();
    }

    private void promoteNext() {
        int count = waitlist.size();

        for (int i = 0; i < count; i++) {
            Student s = waitlist.poll();

            if (s == null) {
                break;
            }

            if (!s.canAddCredits(credits)) {
                System.out.println("Promotion skipped: " + s.name
                        + " would exceed the " + s.policy.getType()
                        + " credit limit.");
                waitlist.add(s);
                continue;
            }

            if (isFull()) {
                waitlist.add(s);
                continue;
            }

            enrolled.add(s);
            s.addCredits(credits);

            System.out.println(s.name + " promoted from waitlist and enrolled in "
                    + name + " (credits: " + s.currentCredits + "/"
                    + s.policy.getCreditLimit() + ").");

            break;
        }
    }
}

class EnrollmentService {
    void enroll(Student student, Elective elective) {
        elective.enroll(student);
    }

    void drop(Student student, Elective elective) {
        elective.drop(student);
    }
}

public class Problem4 {
    public static void main(String[] args) {
        Elective elective = new Elective("Cloud Computing", 4, 2);
        EnrollmentService service = new EnrollmentService();

        Student asha = new Student("Asha", new RegularPolicy(), 20);
        Student ravi = new Student("Ravi", new HonorsPolicy(), 22);
        Student neha = new Student("Neha", new ExchangePolicy(), 12);
        Student kiran = new Student("Kiran", new RegularPolicy(), 22);

        service.enroll(asha, elective);
        service.enroll(ravi, elective);
        service.enroll(neha, elective);
        service.enroll(kiran, elective);

        service.drop(asha, elective);
    }
}
