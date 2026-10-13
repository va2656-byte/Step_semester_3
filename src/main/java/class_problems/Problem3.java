public class Problem3 {

    interface Auditable {
        String auditRecord();
    }

    static abstract class StaffMember {
        private double baseSalary;
        protected double bonusRate;

        StaffMember(double baseSalary) {
            this(baseSalary, 0.10);
        }

        StaffMember(double baseSalary, double bonusRate) {
            if (baseSalary < 0 || bonusRate < 0) {
                throw new IllegalArgumentException();
            }
            this.baseSalary = baseSalary;
            this.bonusRate = bonusRate;
        }

        public abstract double calculateBonus();

        public double getSalary() {
            return baseSalary;
        }

        public void setSalary(double baseSalary) {
            if (baseSalary >= 0) {
                this.baseSalary = baseSalary;
            }
        }
    }

    static class TeamLead extends StaffMember implements Auditable {
        private int teamSize;

        TeamLead(double baseSalary, int teamSize) {
            super(baseSalary);
            this.teamSize = teamSize;
        }

        TeamLead(double baseSalary, double bonusRate, int teamSize) {
            super(baseSalary, bonusRate);
            this.teamSize = teamSize;
        }

        public double calculateBonus() {
            return getSalary() * bonusRate;
        }

        public String auditRecord() {
            return "TeamLead audit: " + teamSize
                    + " team members, salary $" + getSalary();
        }
    }

    static String getAuditIfApplicable(StaffMember s) {
        if (s instanceof Auditable) {
            Auditable a = (Auditable) s;
            return a.auditRecord();
        }
        return "No audit required";
    }

    public static void main(String[] args) {
        TeamLead t = new TeamLead(60000, 5);
        System.out.println(t.calculateBonus());

        TeamLead t2 = new TeamLead(60000, 0.20, 5);
        System.out.println(t2.calculateBonus());

        t.setSalary(-5000);
        System.out.println(t.getSalary());

        StaffMember ref = t;
        System.out.println(getAuditIfApplicable(ref));
    }
}