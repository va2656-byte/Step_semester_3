class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        } else {
            System.out.println("Payment rejected");
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
        }
    }
}

class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo,
               HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null;

        totalStudents++;
    }

    String fullStatus() {

        String roomStatus;

        if (room == null) {
            roomStatus = "unallotted";
        } else {
            roomStatus = room.roomNo;
        }

        return name + " | Due: Rs "
                + feeAccount.getDue()
                + " | Room: " + roomStatus;
    }
}

public class F5 {

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        for (HostelRoom room : rooms) {
            if (room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static void safeAllot(HostelRoom[] rooms,
                           SrmStudent student) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(student.name);
            student.room = room;
        } else {
            System.out.println("No rooms available for "
                    + student.name);
        }
    }

    public static void main(String[] args) {

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        SrmStudent ravi =
                new SrmStudent(
                        "Ravi", "101",
                        new HostelFeeAccount(
                                "101", 200000, 60000));

        SrmStudent anitha =
                new SrmStudent(
                        "Anitha", "102",
                        new HostelFeeAccount(
                                "102", 200000, 20000));

        SrmStudent karthik =
                new SrmStudent(
                        "Karthik", "103",
                        new HostelFeeAccount(
                                "103", 200000, 0));

        safeAllot(rooms, ravi);
        safeAllot(rooms, anitha);
        safeAllot(rooms, karthik);

        ravi.feeAccount.pay(10000);
        anitha.feeAccount.pay(-5000);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println("Total students: "
                + SrmStudent.totalStudents);
    }
}