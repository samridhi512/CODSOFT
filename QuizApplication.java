import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame implements ActionListener {
    private String[] questions = {
        "What is the capital of France?",
        "Which programming language is used for Android development?",
        "What does JVM stand for?",
        "Who developed the Python programming language?",
        "What is the largest planet in our solar system?",
        "Which data structure follows the FIFO principle?",
        "Who invented the World Wide Web?",
        "Which is the fastest land animal?",
        "What does HTML stand for?",
        "Which is the longest river in the world?"
    };

    private String[][] options = {
        {"Paris", "London", "Berlin", "Rome"},
        {"Java", "Python", "C++", "Swift"},
        {"Java Virtual Machine", "Java Visual Manager", "Just Virtual Memory", "None of the above"},
        {"Guido van Rossum", "Dennis Ritchie", "Bjarne Stroustrup", "James Gosling"},
        {"Jupiter", "Saturn", "Mars", "Earth"},
        {"Stack", "Queue", "Array", "Graph"},
        {"Tim Berners-Lee", "Bill Gates", "Steve Jobs", "Linus Torvalds"},
        {"Cheetah", "Tiger", "Lion", "Leopard"},
        {"Hyper Text Markup Language", "High Transfer Machine Learning", "Hyper Tool Multi Language", "None of the above"},
        {"Nile", "Amazon", "Yangtze", "Mississippi"}
    };

    private int[] answers = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}; 
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

        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft + "s");
                if (timeLeft == 0) {
                    timer.stop();
                    checkAnswer();
                }
            }
        });

        loadQuestion();
        timer.start();

        setVisible(true);
    }

    private void loadQuestion() {
        if (index < questions.length) {
            questionLabel.setText("Q" + (index + 1) + ": " + questions[index]);
            for (int i = 0; i < 4; i++) {
                radioButtons[i].setText(options[index][i]);
            }
            group.clearSelection();
            timeLeft = 10; 
            if (timer != null) {
                timer.restart();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Over! Your Score: " + score + "/" + questions.length);
            System.exit(0);
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (radioButtons[i].isSelected() && i == answers[index]) {
                score++;
            }
        }
        index++;
        loadQuestion();
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
