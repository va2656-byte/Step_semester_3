import java.util.*;

class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMember() {
        this(null, null);
    }

    public LibraryMember(String name) {
        this(null, name);
    }

    public LibraryMember(
            String membershipId,
            String name) {

        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
        this.securityAnswer = null;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(
            boolean premium) {

        this.premiumMember = premium;
    }

    public void setSecurityAnswer(
            String answer) {

        if (answer != null &&
            !answer.trim().isEmpty()) {

            securityAnswer = answer;
        }
    }
}

public class Problem4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter member name: ");
        String name = sc.nextLine();

        LibraryMember member =
            new LibraryMember(name);

        System.out.println(
            "Initial membership ID: " +
            member.getMembershipId()
        );

        System.out.print(
            "Enter membership ID: "
        );

        String id = sc.nextLine();

        member.setMembershipId(id);

        System.out.println(
            "Membership ID: " +
            member.getMembershipId()
        );

        System.out.print(
            "Enter another membership ID: "
        );

        String secondId = sc.nextLine();

        member.setMembershipId(secondId);

        System.out.println(
            "After second attempt: " +
            member.getMembershipId()
        );

        System.out.print(
            "Is premium member? (true/false): "
        );

        boolean premium = sc.nextBoolean();
        sc.nextLine();

        member.setPremiumMember(premium);

        System.out.println(
            "Premium member: " +
            member.isPremiumMember()
        );

        System.out.print(
            "Enter security answer: "
        );

        String answer = sc.nextLine();

        member.setSecurityAnswer(answer);

        System.out.println(
            "Security answer stored successfully."
        );

        sc.close();
    }
}