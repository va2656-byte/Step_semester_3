class BrokenStudent {
    static String name;
    static String regNo;
    static int attendance;

    BrokenStudent(String name, String regNo, int attendance) {
        BrokenStudent.name = name;
        BrokenStudent.regNo = regNo;
        BrokenStudent.attendance = attendance;
    }
}

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    static String university = "SRMIST";
    static int admissionCount = 0;

    SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        admissionCount++;
        this.regNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: "
                + admissionCount);
    }
}

public class F4 {
    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenStudent s1 =
                new BrokenStudent("Ravi", "101", 82);

        BrokenStudent s2 =
                new BrokenStudent("Meera", "102", 75);

        System.out.println(s1.name);
        System.out.println(s2.name);

        System.out.println();

        System.out.println("Fixed version:");

        SrmStudent student1 =
                new SrmStudent("Ravi", 82);

        SrmStudent student2 =
                new SrmStudent("Meera", 75);

        student1.printIdCard();
        student2.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}