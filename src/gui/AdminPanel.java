package gui;
import db.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class AdminPanel extends JFrame {
    JTable table;
    DefaultTableModel model;

    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(600, 400);
        model = new DefaultTableModel(new String[]{"App ID", "Name", "Course", "Marks", "Status"}, 0);
        table = new JTable(model);
        JButton refresh = new JButton("Load Applications");
        JButton approve = new JButton("Approve");
        JButton reject = new JButton("Reject");

        refresh.addActionListener(e -> loadApplications());
        approve.addActionListener(e -> updateStatus("APPROVED"));
        reject.addActionListener(e -> updateStatus("REJECTED"));

        add(new JScrollPane(table), "Center");
        JPanel panel = new JPanel();
        panel.add(refresh); panel.add(approve); panel.add(reject);
        add(panel, "South");
        setVisible(true);
    }

    private void loadApplications() {
        try (Connection con = DBConnection.getConnection()) {
            model.setRowCount(0);
            String query = """
                SELECT a.application_id, s.name, c.course_name, s.marks, a.status
                FROM Applications a
                JOIN Students s ON a.student_id = s.student_id
                JOIN Courses c ON a.course_id = c.course_id
            """;
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5)
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateStatus(String newStatus) {
        int row = table.getSelectedRow();
        if (row == -1) return;
        int appId = (int) model.getValueAt(row, 0);
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE Applications SET status=? WHERE application_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newStatus);
            ps.setInt(2, appId);
            ps.executeUpdate();
            loadApplications();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
