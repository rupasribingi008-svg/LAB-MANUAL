import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends Frame implements ActionListener {

    Label l1, l2, l3;
    TextField t1, t2, t3;
    Button b1, b2, b3, b4;

    SimpleCalculator() {
        // Labels
        l1 = new Label("First Number:");
        l2 = new Label("Second Number:");
        l3 = new Label("Result:");

        // Text Fields
        t1 = new TextField();
        t2 = new TextField();
        t3 = new TextField();
        t3.setEditable(false);

        // Buttons
        b1 = new Button("Add");
        b2 = new Button("Subtract");
        b3 = new Button("Multiply");
        b4 = new Button("Divide");

        // Set Layout
        setLayout(new GridLayout(4, 2, 10, 10));

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b1);
        add(b2);
        add(b3);
        add(b4);

        add(l3);
        add(t3);

        // Register Event Handlers
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        // Window Closing Event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setTitle("Simple AWT Calculator");
        setSize(350, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(t1.getText());
            double num2 = Double.parseDouble(t2.getText());
            double result = 0;

            if (e.getSource() == b1)
                result = num1 + num2;
            else if (e.getSource() == b2)
                result = num1 - num2;
            else if (e.getSource() == b3)
                result = num1 * num2;
            else if (e.getSource() == b4) {
                if (num2 == 0) {
                    t3.setText("Cannot divide by zero");
                    return;
                }
                result = num1 / num2;
            }

            t3.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {
            t3.setText("Invalid Input");
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
