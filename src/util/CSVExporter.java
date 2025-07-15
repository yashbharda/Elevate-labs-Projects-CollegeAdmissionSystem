package util;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.*;

public class CSVExporter {
    public static void exportToCSV(JTable table, String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            TableModel model = table.getModel();

            // Write header
            for (int i = 0; i < model.getColumnCount(); i++) {
                fw.write(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) fw.write(",");
            }
            fw.write("\n");

            // Write rows
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    fw.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) fw.write(",");
                }
                fw.write("\n");
            }

            JOptionPane.showMessageDialog(null, "Exported to CSV: " + filePath);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error exporting CSV: " + e.getMessage());
        }
    }
}
