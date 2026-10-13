
import java.util.*;

interface ScoringRule {
    double calculate(Score score);
}

class InnovationScoring implements ScoringRule {
    public double calculate(Score s) {
        return s.idea * 0.5 + s.execution * 0.3
                + s.presentation * 0.2;
    }
}

class OpenScoring implements ScoringRule {
    public double calculate(Score s) {
        return (s.idea + s.execution + s.presentation) / 3.0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Track {
    String name;
    ScoringRule rule;

    Track(String name, ScoringRule rule) {
        this.name = name;
        this.rule = rule;
    }
}

class Score {
    int idea, execution, presentation;

    Score(int idea, int execution, int presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }

    boolean isValid() {
        return idea >= 0 && idea <= 10
                && execution >= 0 && execution <= 10
                && presentation >= 0 && presentation <= 10;
    }
}

class Project {
    String name;
    Team team;
    Score score;

    Project(String name, Team team) {
        this.name = name;
        this.team = team;
    }
}

class Team {
    String name;
    ArrayList<Student> members;
    Track track;
    Project project;

    Team(String name, ArrayList<Student> members, Track track) {
        this.name = name;
        this.members = members;
        this.track = track;
    }
}

class Hackathon {
    String name;
    String state = "Open";
    ArrayList<Team> teams = new ArrayList<>();

    Hackathon(String name) {
        this.name = name;
    }

    boolean registerTeam(Team team) {
        if (!state.equals("Open")) {
            System.out.println("Registration failed: Hackathon is not open.");
            return false;
        }

        if (team.members.size() < 2 || team.members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return false;
        }

        for (Team existing : teams) {
            for (Student s : team.members) {
                for (Student old : existing.members) {
                    if (s.name.equalsIgnoreCase(old.name)) {
                        System.out.println("Registration failed: "
                                + s.name + " already belongs to a team.");
                        return false;
                    }
                }
            }
        }

        teams.add(team);
        System.out.println("Team " + team.name + " registered ("
                + team.members.size() + " members, "
                + team.track.name + " track).");
        return true;
    }

    void startJudging() {
        if (state.equals("Open")) {
            state = "Judging";
        }
    }

    void publishResults() {
        state = "Published";

        for (Team t : teams) {
            if (t.project != null && t.project.score != null) {
                System.out.printf("Final score: %.2f%n",
                        t.track.rule.calculate(t.project.score));
            }
        }

        System.out.println("Results published.");
    }
}

class Judge {
    void scoreProject(Hackathon h, Project p, Score score) {
        if (h.state.equals("Published")) {
            System.out.println(
                    "Rescore rejected: Results have already been published.");
            return;
        }

        if (!h.state.equals("Judging")) {
            System.out.println("Scoring rejected: Judging has not started.");
            return;
        }

        if (!score.isValid()) {
            System.out.println("Invalid score: Ratings must be between 0 and 10.");
            return;
        }

        p.score = score;
        System.out.println("Score recorded for '" + p.name + "'.");
    }
}

class ProjectService {
    void submit(Hackathon h, Team team, String projectName) {
        if (!h.state.equals("Open") && !h.state.equals("Judging")) {
            System.out.println("Submission failed: Results are published.");
            return;
        }

        if (!h.teams.contains(team)) {
            System.out.println("Submission failed: Team is not registered.");
            return;
        }

        if (team.project != null) {
            System.out.println("Submission failed: Team can submit only one project.");
            return;
        }

        team.project = new Project(projectName, team);
        System.out.println("Project '" + projectName
                + "' submitted by " + team.name + ".");
    }
}

public class Problem1 {
    public static void main(String[] args) {
        Hackathon h = new Hackathon("Code Sprint");

        Track innovation = new Track("Innovation", new InnovationScoring());
        Track open = new Track("Open", new OpenScoring());

        ArrayList<Student> members = new ArrayList<>();
        members.add(new Student("Asha"));
        members.add(new Student("Ravi"));
        members.add(new Student("Neha"));

        Team t1 = new Team("ByteBusters", members, innovation);
        h.registerTeam(t1);

        ArrayList<Student> oneMember = new ArrayList<>();
        oneMember.add(new Student("Kiran"));

        Team t2 = new Team("SoloCoder", oneMember, open);
        h.registerTeam(t2);

        ProjectService service = new ProjectService();
        service.submit(h, t1, "SmartAttend");

        h.startJudging();

        Judge judge = new Judge();
        Project p = t1.project;

        judge.scoreProject(h, p, new Score(8, 7, 9));
        h.publishResults();

        judge.scoreProject(h, p, new Score(10, 7, 9));
    }
}
