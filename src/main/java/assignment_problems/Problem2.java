
interface Exportable {
    String exportData();
}

class ExportCounter {
    static int totalExports = 0;
}

class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    public String exportData() {
        ExportCounter.totalExports++;
        return "Exported report: " + reportName;
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    public String exportData() {
        ExportCounter.totalExports++;
        return "Exported profile: " + username;
    }
}

public class Problem2 {
    static int getTotalExports() {
        return ExportCounter.totalExports;
    }

    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        System.out.println(r.exportData());
        System.out.println(u.exportData());

        exportAll(new Exportable[]{r, u});

        System.out.println(getTotalExports());
    }
}
