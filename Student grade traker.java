import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numberOfStudents = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        String[] studentNames = new String[numberOfStudents];
        double[] studentGrades = new double[numberOfStudents];

        // Input student names and grades
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print("Enter name of Student " + (i + 1) + ": ");
            studentNames[i] = scanner.nextLine();

            System.out.print("Enter grade of " + studentNames[i] + ": ");
            studentGrades[i] = scanner.nextDouble();
            scanner.nextLine();
        }

        // Calculate stats
        double total = 0;
        double lowest = studentGrades[0];
        double highest = studentGrades[0];

        for (double grade : studentGrades) {
            total += grade;
            if (grade < lowest) lowest = grade;
            if (grade > highest) highest = grade;
        }

        double average = total / numberOfStudents;

        // Display summary
        System.out.println("\n----- STUDENT GRADE SUMMARY -----");
        System.out.println("Name\t\tGrade\tStatus\tLetter Grade");
        System.out.println("-----------------------------------------------");

        for (int i = 0; i < numberOfStudents; i++) {
            String status = (studentGrades[i] >= 35) ? "PASS" : "FAIL";

            String letterGrade;
            double g = studentGrades[i];

            if (g >= 90) letterGrade = "A";
            else if (g >= 75) letterGrade = "B";
            else if (g >= 60) letterGrade = "C";
            else if (g >= 35) letterGrade = "D";
            else letterGrade = "F";

            System.out.println(studentNames[i] + "\t\t" + studentGrades[i] + "\t" + status + "\t" + letterGrade);
        }

        System.out.println("\nClass Average Grade: " + average);
        System.out.println("Highest Grade in Class: " + highest);
        System.out.println("Lowest Grade in Class: " + lowest);

        scanner.close();
    }
}
