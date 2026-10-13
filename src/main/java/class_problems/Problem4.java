
abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract int getAnnualLeaveLimit();
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    int getAnnualLeaveLimit() {
        return 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    int getAnnualLeaveLimit() {
        return 15;
    }
}

class ContractEmployee extends Employee {
    ContractEmployee(String name) {
        super(name);
    }

    int getAnnualLeaveLimit() {
        return 10;
    }
}

class LeaveRequest {
    Employee employee;
    String startDate;
    String endDate;
    private String status = "Pending";

    LeaveRequest(Employee employee, String startDate, String endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    String getStatus() {
        return status;
    }

    void review(String decision) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change status: " + status
                    + " request cannot revert to Pending.");
            return;
        }

        if (decision.equals("Approved") || decision.equals("Rejected")) {
            status = decision;

            System.out.println("Leave request for " + employee.name
                    + " " + status.toLowerCase() + ".");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Invalid review decision.");
        }
    }
}

class LeaveManager {
    LeaveRequest submit(Employee e, String start, String end) {
        LeaveRequest request = new LeaveRequest(e, start, end);

        System.out.println("Leave request submitted by " + e.name
                + " for " + start + " to " + end + ".");
        System.out.println("Status: " + request.getStatus());

        return request;
    }
}

public class Problem4 {
    public static void main(String[] args) {
        LeaveManager manager = new LeaveManager();

        Employee e1 = new FullTimeEmployee("John Doe");

        LeaveRequest r1 = manager.submit(
                e1, "2024-10-10", "2024-10-12");

        r1.review("Approved");

        Employee e2 = new PartTimeEmployee("Jane Smith");

        manager.submit(
                e2, "2024-11-01", "2024-11-05");

        r1.review("Pending");
    }
}
