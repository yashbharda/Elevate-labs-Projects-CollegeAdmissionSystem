package util;

import javax.swing.*;
import javax.swing.table.TableModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

public class PDFExporter {
    public static void exportToPDF(JTable table, String filePath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());

            // Header
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(new PdfPCell(new Phrase(table.getColumnName(i))));
            }

            // Rows
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    pdfTable.addCell(table.getValueAt(i, j).toString());
                }
            }

            document.add(new Paragraph("Admission List"));
            document.add(new Paragraph(" "));
            document.add(pdfTable);
            document.close();

            JOptionPane.showMessageDialog(null, "Exported to PDF: " + filePath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error exporting PDF: " + e.getMessage());
        }
    }
}
