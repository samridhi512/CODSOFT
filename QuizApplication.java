import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApplication extends JFrame implements ActionListener {
    private ArrayList<Question> questions;
    private int index = 0, score = 0, timeLeft = 10;

    private JLabel questionLabel, timerLabel;
    private JRadioButton[] radioButtons;
    private ButtonGroup group;
    private JButton nextButton;
    private Timer timer;

    public QuizApplication() {
        setTitle("Quiz Application with Timer");
        setSize(600, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        questions = loadQuestions();

        questionLabel = new JLabel();
        timerLabel = new JLabel("Time Left: " + timeLeft + "s", SwingConstants.RIGHT);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(questionLabel, BorderLayout.WEST);
        topPanel.add(timerLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        radioButtons = new JRadioButton[4];
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton();
            group.add(radioButtons[i]);
            optionsPanel.add(radioButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");
            if (timeLeft == 0) {
                timer.stop();
                checkAnswer();
            }
        });

        loadQuestion();
        timer.start();
        setVisible(true);
    }

    private void loadQuestion() {
        if (index < questions.size()) {
            Question q = questions.get(index);
            questionLabel.setText("Q" + (index + 1) + ": " + q.question);
            for (int i = 0; i < 4; i++) {
                radioButtons[i].setText(q.options[i]);
            }
            group.clearSelection();
            timeLeft = 10;
            timer.restart();
        } else {
            saveScore();
            JOptionPane.showMessageDialog(this, "Quiz Over! Your Score: " + score + "/" + questions.size());
            System.exit(0);
        }
    }

    private void checkAnswer() {
        Question q = questions.get(index);
        for (int i = 0; i < 4; i++) {
            if (radioButtons[i].isSelected() && i == q.correctAnswer) {
                score++;
            }
        }
        index++;
        loadQuestion();
    }

    private void saveScore() {
        try (FileWriter writer = new FileWriter("quiz_scores.txt", true)) {
            writer.write("Score: " + score + "/" + questions.size() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Question> loadQuestions() {
        ArrayList<Question> list = new ArrayList<>();
        list.add(new Question("What is the capital of France?",
                new String[] { "Paris", "London", "Berlin", "Rome" }, 0)); 
        list.add(new Question("Which programming language is used for Android development?",
                new String[] { "Java", "Python", "C++", "Swift" }, 0));
        list.add(new Question("What does JVM stand for?",
                new String[] { "Java Virtual Machine", "Java Visual Manager", "Just Virtual Memory",
                        "None of the above" },
                0)); 
        list.add(new Question("Who developed Python?",
                new String[] { "Guido van Rossum", "Dennis Ritchie", "Bjarne Stroustrup", "James Gosling" }, 0)); 
                                                                                                                  
                                                                                                                  
                                                                                                                  
        list.add(new Question("What is the largest planet in our solar system?",
                new String[] { "Jupiter", "Saturn", "Mars", "Earth" }, 0)); 
        list.add(new Question("What does HTML stand for?",
                new String[] { "Hyper Text Markup Language", "Hyper Transfer Markup Language",
                        "Hyper Text Machine Learning", "Hyper Transfer Main Logic" },
                0)); 
        list.add(new Question("Which company developed the C programming language?",
                new String[] { "Bell Labs", "Microsoft", "IBM", "Google" }, 0)); 
        list.add(new Question("What is the speed of light?",
                new String[] { "299,792,458 m/s", "150,000,000 m/s", "3,000,000 m/s", "30,000,000 m/s" }, 0)); 
                                                                                                               
                                                                                                             
        list.add(new Question("Who is known as the father of computers?",
                new String[] { "Charles Babbage", "Alan Turing", "John von Neumann", "Tim Berners-Lee" }, 0)); 
                                                                                                               
                                                                                                               
        list.add(new Question("Which of the following is NOT an operating system?",
                new String[] { "Windows", "Linux", "Python", "macOS" }, 2)); 

        return list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.stop();
        checkAnswer();
    }

    public static void main(String[] args) {
        new QuizApplication();
    }
}
