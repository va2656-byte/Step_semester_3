
import java.util.*;

interface Question {
    String getText();
    boolean checkAnswer(String answer);
}

class MCQQuestion implements Question {
    private String text;
    private String correctAnswer;

    MCQQuestion(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion implements Question {
    private String text;
    private String correctAnswer;

    TrueFalseQuestion(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    String title;
    ArrayList<Question> questions = new ArrayList<>();

    Examination(String title) {
        this.title = title;
    }

    void addQuestion(Question q) {
        questions.add(q);
    }
}

class Student {
    String name;
    ArrayList<Attempt> attempts = new ArrayList<>();

    Student(String name) {
        this.name = name;
    }

    Attempt startExam(Examination exam) {
        for (Attempt a : attempts) {
            if (a.exam == exam) {
                throw new IllegalStateException(
                    "Attempt already exists for this examination");
            }
        }

        Attempt a = new Attempt(this, exam);
        attempts.add(a);

        System.out.println("Examination '" + exam.title
                + "' started by " + name + ".");

        return a;
    }
}

class Attempt {
    Student student;
    Examination exam;
    Map<Integer, String> answers = new HashMap<>();
    boolean submitted = false;

    Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
    }

    void answerQuestion(int number, String answer) {
        if (submitted) {
            throw new IllegalStateException("Attempt already submitted");
        }

        if (number < 1 || number > exam.questions.size()) {
            throw new IllegalArgumentException("Invalid question number");
        }

        answers.put(number - 1, answer);

        System.out.println("Question " + number
                + " answered with '" + answer + "'.");
    }

    void submit() {
        if (submitted) {
            throw new IllegalStateException("Attempt already submitted");
        }

        submitted = true;

        System.out.println("Examination '" + exam.title
                + "' submitted successfully.");

        int score = 0;

        for (int i = 0; i < exam.questions.size(); i++) {
            String answer = answers.get(i);

            if (answer != null &&
                    exam.questions.get(i).checkAnswer(answer)) {
                score++;
            }
        }

        System.out.println("Result for '" + exam.title
                + "' attempt: " + score + "/"
                + exam.questions.size() + " correct");
    }
}

public class Problem1 {
    public static void main(String[] args) {
        Examination exam = new Examination("Math Quiz");

        exam.addQuestion(new MCQQuestion("2 + 2?", "A"));
        exam.addQuestion(new MCQQuestion("3 + 3?", "B"));

        Student s = new Student("Student");

        Attempt a = s.startExam(exam);
        a.answerQuestion(1, "A");
        a.answerQuestion(2, "C");
        a.submit();
    }
}
