import java.io.*;
import java.util.*;

public class StudentMarksManagement {

    static final String FILE_NAME = "students.txt";
    static Scanner sc = new Scanner(System.in);

    // Add Student Record
    public static void addStudent() throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME, true);
        BufferedWriter bw = new BufferedWriter(fw);

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Marks: ");
        String marks = sc.nextLine();

        bw.write(id + "," + name + "," + marks);
        bw.newLine();
        bw.close();

        System.out.println("Student record added successfully.");
    }

    // Display All Records
    public static void displayStudents() throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No records found.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        System.out.println("\nStudent Records");
        System.out.println("----------------------------");

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            System.out.println("ID: " + data[0] +
                    " | Name: " + data[1] +
                    " | Marks: " + data[2]);
        }

        br.close();
    }

    // Update Student Marks
    public static void updateMarks() throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No records found.");
            return;
        }

        System.out.print("Enter Student ID to update: ");
        String searchId = sc.nextLine();

        File tempFile = new File("temp.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");

            if (data[0].equals(searchId)) {
                System.out.print("Enter New Marks: ");
                String newMarks = sc.nextLine();
                bw.write(data[0] + "," + data[1] + "," + newMarks);
                found = true;
            } else {
                bw.write(line);
            }
            bw.newLine();
        }

        br.close();
        bw.close();

        file.delete();
        tempFile.renameTo(file);

        if (found)
            System.out.println("Marks updated successfully.");
        else
            System.out.println("Student ID not found.");
    }

    // Main Menu
    public static void main(String[] args) throws IOException {

        int choice;

        do {
            System.out.println("\n===== Student Marks File Management =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Update Marks");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    updateMarks();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
