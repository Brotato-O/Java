package excel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XuLyFileExcel {

    public static void xuatExcel(JTable tbl) {
        try {
            TableModel dtm = tbl.getModel();

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Lưu vào");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getPath();
                if (!path.contains(".xlsx")) {
                    path += ".xlsx";
                }

                XSSFWorkbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Sheet 1");

                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < dtm.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(dtm.getColumnName(i));
                }

                for (int i = 0; i < dtm.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < dtm.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(dtm.getValueAt(i, j).toString());
                    }
                }

                FileOutputStream fileOut = new FileOutputStream(path);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

                JOptionPane.showMessageDialog(null, "Xuất file thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xuất file thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void nhapExcel(JTable tbl) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Chọn file");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
            chooser.setFileFilter(fnef);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File fileSelected = chooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(fileSelected);
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                Sheet sheet = wb.getSheetAt(0);

                DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
                dtm.setRowCount(0);

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    List<Object> list = new ArrayList<>();
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        list.add(cell.toString());
                    }
                    dtm.addRow(list.toArray());
                }

                wb.close();
                JOptionPane.showMessageDialog(null, "Nhập file thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nhập file thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        // Tạo một JTable mẫu để kiểm tra
        JTable table = new JTable(new DefaultTableModel(
            new Object[][] {
                {"1", "John", "Doe"},
                {"2", "Jane", "Smith"}
            },
            new String[] {"ID", "First Name", "Last Name"}
        ));
    
        // Gọi phương thức xuatExcel để kiểm tra
        xuatExcel(table);
    
        // Gọi phương thức nhapExcel để kiểm tra
        nhapExcel(table);
    }
}