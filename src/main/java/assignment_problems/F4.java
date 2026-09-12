class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class LibraryMember {
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "SRM Central Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;

        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: "
                + memberCount);
    }
}

public class F4 {
    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember(
                        "Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
                new BrokenLibraryMember(
                        "Rohan", "LM-1002", 3);

        System.out.println(member1.name);
        System.out.println(member2.name);

        /*
         * name, memberId and booksIssued should not be static
         * because every library member has separate values.
         *
         * Since they are static in the broken version, all objects
         * share the same variables. Creating Rohan overwrites Aditi.
         */

        System.out.println();

        System.out.println("Fixed version:");

        LibraryMember student1 =
                new LibraryMember("Aditi", 2);

        LibraryMember student2 =
                new LibraryMember("Rohan", 3);

        student1.printMemberCard();
        student2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}