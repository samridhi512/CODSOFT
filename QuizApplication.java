import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect(int optionIndex) {
        return optionIndex == correctOptionIndex;
    }
}

public class QuizApplication {
    private List<QuizQuestion> quizQuestions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public QuizApplication() {
        initializeQuiz();
    }

    private void initializeQuiz() {
        quizQuestions = new ArrayList<>();

        quizQuestions.add(new QuizQuestion("Who is known as the 'Master Blaster' in Indian cricket?",
                List.of("Virat Kohli", "Sachin Tendulkar", "MS Dhoni", "Rohit Sharma"), 1));
        quizQuestions.add(new QuizQuestion("Who is the captain of the Indian women's cricket team?",
                List.of("Mithali Raj", "Smriti Mandhana", "Harmanpreet Kaur", "Jhulan Goswami"), 0));
        quizQuestions.add(new QuizQuestion("Which actress won the National Film Award for Best Actress in 2021?",
                List.of("Kangana Ranaut", "Alia Bhatt", "Vidya Balan", "Deepika Padukone"), 0));
        quizQuestions.add(new QuizQuestion("Who is the highest run-scorer in IPL history?",
                List.of("Virat Kohli", "Suresh Raina", "Rohit Sharma", "David Warner"), 2));
        quizQuestions.add(new QuizQuestion("Who won the Filmfare Award for Best Actor in 2021?",
                List.of("Ranbir Kapoor", "Akshay Kumar", "Rajkummar Rao", "Ayushmann Khurrana"), 1));

        quizQuestions.add(new QuizQuestion("Who was the leading wicket-taker in ICC T20 World Cup 2021?",
                List.of("Jasprit Bumrah", "Rashid Khan", "Adam Zampa", "Josh Hazlewood"), 1));
        quizQuestions.add(new QuizQuestion("Which team won the ICC Test Championship in 2021?",
                List.of("India", "New Zealand", "Australia", "England"), 1));
        quizQuestions.add(new QuizQuestion("Who scored the fastest century in IPL history?",
                List.of("Chris Gayle", "David Warner", "AB de Villiers", "KL Rahul"), 0));
        quizQuestions.add(new QuizQuestion("Who won the ICC Men's ODI Cricketer of the Year award in 2021?",
                List.of("Virat Kohli", "Babar Azam", "Rohit Sharma", "Josh Butler"), 1));
        quizQuestions.add(new QuizQuestion("Which Indian cricketer holds the record for most sixes in IPL history?",
                List.of("MS Dhoni", "Virat Kohli", "Rohit Sharma", "Chris Gayle"), 2));

        currentQuestionIndex = 0;
        score = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz! You will have 10 seconds to answer each question.");
        System.out.println("-------------------------------------------------------------");

        for (QuizQuestion question : quizQuestions) {
            displayQuestion(question);
            startTimer(20);

            int selectedOption = scanner.nextInt();
            timer.cancel();

            if (question.isCorrect(selectedOption - 1)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println(
                        "Incorrect! The correct answer was: " + question.getOptions().get(question.correctOptionIndex));
            }

            System.out.println("-------------------------------------------------------------");
            currentQuestionIndex++;
        }

        displayResult();
        scanner.close();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("Question " + (currentQuestionIndex + 1) + ": " + question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.print("Your answer (1-" + options.size() + "): ");
    }

    private void startTimer(int seconds) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                System.exit(0);
            }
        }, seconds * 1000);
    }

    private void displayResult() {
        System.out.println("Quiz Ended!");
        System.out.println("Your Score: " + score + "/" + quizQuestions.size());
        System.out.println("-------------------------------------------------------------");
    }

    public static void main(String[] args) {
        QuizApplication quizApp = new QuizApplication();
        quizApp.startQuiz();
    }
}
