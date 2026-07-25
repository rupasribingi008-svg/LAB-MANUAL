import java.sql.*;
import java.util.Scanner;

public class StudentDBMS {

    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASSWORD = "password";

    static Scanner sc = new Scanner(System.in);

    // Create
    static void addStudent(Connection con) throws SQLException {
        String sql = "INSERT INTO student VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        System.out.print("Enter ID: ");
        ps.setInt(1, sc.nextInt());
        sc.nextLine();

        System.out.print("Enter Name: ");
        ps.setString(2, sc.nextLine());

        System.out.print("Enter Course: ");
        ps.setString(3, sc.nextLine());

        System.out.print("Enter Marks: ");
        ps.setDouble(4, sc.nextDouble());

        ps.executeUpdate();
        System.out.println("Student added successfully.");
    }

    // Read
    static void viewStudents(Connection con) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM student");

        System.out.println("\nID\tName\tCourse\tMarks");

        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + "\t" +
                rs.getString("name") + "\t" +
                rs.getString("course") + "\t" +
                rs.getDouble("marks")
            );
        }
    }

    // Update
    static void updateStudent(Connection con) throws SQLException {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        System.out.print("Enter New Marks: ");
        double marks = sc.nextDouble();

        PreparedStatement ps = con.prepareStatement(
                "UPDATE student SET marks=? WHERE id=?");

        ps.setDouble(1, marks);
        ps.setInt(2, id);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Student updated successfully.");
        else
            System.out.println("Student not found.");
    }

    // Delete
    static void deleteStudent(Connection con) throws SQLException {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        PreparedStatement ps = con.prepareStatement(
                "DELETE FROM student WHERE id=?");

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Student deleted successfully.");
        else
            System.out.println("Student not found.");
    }

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            int choice;

            do {
                System.out.println("\n===== Student Database Management System =====");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addStudent(con);
                        break;
                    case 2:
                        viewStudents(con);
                        break;
                    case 3:
                        updateStudent(con);
                        break;
                    case 4:
                        deleteStudent(con);
                        break;
                    case 5:
                        System.out.println("Program terminated.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

            } while (choice != 5);

            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
