import gui.StudentForm;
import gui.AdminPanel;
import gui.MeritListViewer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] options = {"Student", "Admin", "Merit List"};
        int choice = JOptionPane.showOptionDialog(null, "Login as:", "Select Role",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) new StudentForm();
        else if (choice == 1) new AdminPanel();
        else if (choice == 2) new MeritListViewer(); // 👈 Add this line
    }
}
