package gui;

import db.DBConnection;
import util.CSVExporter;
import util.PDFExporter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MeritListViewer extends JFrame {
    private JComboBox<String> courseCombo;
    private JTable table;
    private DefaultTableModel model;

    public MeritListViewer() {
        setTitle("Merit List Viewer");
        setSize(700, 500);
        setLayout(new BorderLayout());

        // Top panel for dropdown and buttons
        JPanel topPanel = new JPanel(new FlowLayout());
        courseCombo = new JComboBox<>();
        JButton loadButton = new JButton("Load Merit List");
        JButton exportCSV = new JButton("Export CSV");
        JButton exportPDF = new JButton("Export PDF");

        topPanel.add(new JLabel("Select Course:"));
        topPanel.add(courseCombo);
        topPanel.add(loadButton);
        topPanel.add(exportCSV);
        topPanel.add(exportPDF);
        add(topPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new String[]{"App ID", "Name", "Course", "Marks", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load courses in combo box
        loadCourses();

        // Button actions
        loadButton.addActionListener(e -> loadMeritList());

        exportCSV.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                CSVExporter.exportToCSV(table, path + ".csv");
            }
        });

        exportPDF.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                PDFExporter.exportToPDF(table, path + ".pdf");
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadCourses() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT course_name FROM Courses";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            courseCombo.removeAllItems();
            while (rs.next()) {
                courseCombo.addItem(rs.getString("course_name"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading courses: " + ex.getMessage());
        }
    }

    private void loadMeritList() {
        String selectedCourse = (String) courseCombo.getSelectedItem();
        if (selectedCourse == null) {
            JOptionPane.showMessageDialog(this, "Please select a course");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            String sql = """
                    SELECT a.application_id, s.name, c.course_name, s.marks, a.status
                    FROM Applications a
                    JOIN Students s ON a.student_id = s.student_id
                    JOIN Courses c ON a.course_id = c.course_id
                    WHERE c.course_name = ? AND a.status = 'APPROVED' AND s.marks >= c.cutoff_marks
                    ORDER BY s.marks DESC
                    """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, selectedCourse);
            ResultSet rs = ps.executeQuery();

            model.setRowCount(0); // Clear previous rows
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("application_id"),
                        rs.getString("name"),
                        rs.getString("course_name"),
                        rs.getInt("marks"),
                        rs.getString("status")
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading merit list: " + ex.getMessage());
        }
    }
}
