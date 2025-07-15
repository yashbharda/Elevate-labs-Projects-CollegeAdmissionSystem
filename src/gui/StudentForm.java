package gui;
import db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentForm extends JFrame {
    public StudentForm() {
        setTitle("Student Registration");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));

        JTextField name = new JTextField();
        JTextField dob = new JTextField();
        JTextField email = new JTextField();
        JTextField marks = new JTextField();
        JTextField category = new JTextField();
        JTextField courseId = new JTextField();

        add(new JLabel("Name:")); add(name);
        add(new JLabel("DOB (YYYY-MM-DD):")); add(dob);
        add(new JLabel("Email:")); add(email);
        add(new JLabel("Marks:")); add(marks);
        add(new JLabel("Category:")); add(category);
        add(new JLabel("Course ID:")); add(courseId);

        JButton register = new JButton("Register");
        register.addActionListener(e -> {
            try (Connection con = DBConnection.getConnection()) {
                String sql1 = "INSERT INTO Students (name, dob, email, marks, category) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
                ps1.setString(1, name.getText());
                ps1.setDate(2, Date.valueOf(dob.getText()));
                ps1.setString(3, email.getText());
                ps1.setInt(4, Integer.parseInt(marks.getText()));
                ps1.setString(5, category.getText());
                ps1.executeUpdate();
                ResultSet rs = ps1.getGeneratedKeys();
                if (rs.next()) {
                    int studentId = rs.getInt(1);
                    String sql2 = "INSERT INTO Applications (student_id, course_id, status) VALUES (?, ?, 'PENDING')";
                    PreparedStatement ps2 = con.prepareStatement(sql2);
                    ps2.setInt(1, studentId);
                    ps2.setInt(2, Integer.parseInt(courseId.getText()));
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Application submitted!");
                    String[] options = {"Student", "Admin"};
                    int choice = JOptionPane.showOptionDialog(null, "Login as:", "Select Role",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    if (choice == 0) new StudentForm();
                    else new AdminPanel();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        add(register);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
