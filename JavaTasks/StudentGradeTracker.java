import java.util.Scanner;
public class StudentGradeTracker {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        String[] names = new String[n];
        int[] grades = new int[n];
        int sum = 0;
        int highest = 0;
        int lowest = 100;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            names[i] = sc.next();
            System.out.print("Enter grade: ");
            grades[i] = sc.nextInt();
            sum += grades[i];
            if (grades[i] > highest) {
                highest = grades[i];
            }
            if (grades[i] < lowest) {
                lowest = grades[i];
            }
        }
        double average = (double) sum / n;
        System.out.println("\nStudent Report");
        System.out.println("----------------------");
        for (int i = 0; i < n; i++) {
            System.out.println(names[i] + " : " + grades[i]);
        }
        System.out.println("----------------------");
        System.out.println("Average Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);
        sc.close();
    }
}