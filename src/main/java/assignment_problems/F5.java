class Employee {
    private String empId;
    private String empName;
    private double salary;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(String empId, String empName,
                    double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(String empId, String empName,
                   double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        if (getSalary() < stipendCap) {
            return getSalary();
        } else {
            return stipendCap;
        }
    }
}

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
        }
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = null;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {
            ManagerEmployee manager =
                    (ManagerEmployee) employee;

            pay = manager.effectiveSalary();

        } else if (employee instanceof InternEmployee) {
            InternEmployee intern =
                    (InternEmployee) employee;

            pay = intern.effectiveSalary();

        } else {
            pay = employee.getSalary();
        }

        String slotStatus;

        if (slot == null) {
            slotStatus = "no parking assigned";
        } else {
            slotStatus = slot.slotNo;
        }

        return name + " | Pay: Rs "
                + pay
                + " | Slot: " + slotStatus;
    }
}

public class F5 {

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        return null;
    }

    static void safeAllot(ParkingSlot[] slots,
                           CompanyEmployeeRecord record) {

        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(record.empId);
            record.slot = slot;
        }
    }

    public static void main(String[] args) {

        ParkingSlot[] slots = {
            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };

        CompanyEmployeeRecord divya =
                new CompanyEmployeeRecord(
                        "Divya",
                        "E101",
                        new ManagerEmployee(
                                "E101",
                                "Divya",
                                70000,
                                8000));

        CompanyEmployeeRecord karan =
                new CompanyEmployeeRecord(
                        "Karan",
                        "E102",
                        new Employee(
                                "E102",
                                "Karan",
                                40000));

        CompanyEmployeeRecord meera =
                new CompanyEmployeeRecord(
                        "Meera",
                        "E103",
                        new InternEmployee(
                                "E103",
                                "Meera",
                                12000,
                                10000));

        safeAllot(slots, divya);
        safeAllot(slots, karan);
        safeAllot(slots, meera);

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());

        System.out.println("Total records: "
                + CompanyEmployeeRecord.totalRecords);
    }
}