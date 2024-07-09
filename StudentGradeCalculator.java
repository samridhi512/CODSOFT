import java.util.Scanner;

class Student {
    private String name;
    private int[] marks;
    private int totalMarks;
    private double averagePercentage;
    private char grade;

    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
        calculateTotalMarks();
        calculateAveragePercentage();
        calculateGrade();
    }

    private void calculateTotalMarks() {
        for (int mark : marks) {
            totalMarks += mark;
        }
    }

    private void calculateAveragePercentage() {
        averagePercentage = (double) totalMarks / marks.length;
    }

    private void calculateGrade() {
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
    }

    public void displayGradeReport() {
        System.out.println("Student Name: " + name);
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }
}

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();

        int[] marks = new int[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        Student student = new Student(name, marks);

        System.out.println("\nGenerating Grade Report...");
        System.out.println("===========================");
        student.displayGradeReport();

        scanner.close();
    }
}
