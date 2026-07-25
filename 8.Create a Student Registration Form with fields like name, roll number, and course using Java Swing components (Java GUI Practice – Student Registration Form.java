import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistrationForm extends JFrame implements ActionListener {

    JLabel lblName, lblRoll, lblCourse;
    JTextField txtName, txtRoll;
    JComboBox<String> cmbCourse;
    JButton btnRegister, btnClear;

    StudentRegistrationForm() {

        // Labels
        lblName = new JLabel("Student Name:");
        lblRoll = new JLabel("Roll Number:");
        lblCourse = new JLabel("Course:");

        // Text Fields
        txtName = new JTextField(20);
        txtRoll = new JTextField(20);

        // Combo Box
        String courses[] = {"BCA", "B.Sc", "B.Com", "B.Tech", "MCA"};
        cmbCourse = new JComboBox<>(courses);

        // Buttons
        btnRegister = new JButton("Register");
        btnClear = new JButton("Clear");

        // Layout
        setLayout(new GridLayout(4, 2, 10, 10));

        add(lblName);
        add(txtName);

        add(lblRoll);
        add(txtRoll);

        add(lblCourse);
        add(cmbCourse);

        add(btnRegister);
        add(btnClear);

        // Event Handling
        btnRegister.addActionListener(this);

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtRoll.setText("");
                cmbCourse.setSelectedIndex(0);
            }
        });

        // Frame Properties
        setTitle("Student Registration Form");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = txtName.getText();
        String roll = txtRoll.getText();
        String course = (String) cmbCourse.getSelectedItem();

        JOptionPane.showMessageDialog(this,
                "Registration Successful!\n\n" +
                "Name: " + name +
                "\nRoll Number: " + roll +
                "\nCourse: " + course);
    }

    public static void main(String[] args) {
        new StudentRegistrationForm();
    }
}
