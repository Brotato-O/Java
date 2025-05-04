package GUI.dialog.QLKH;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ImportQLKHDialog extends JDialog {

    private JButton btnThemTatCa = new JButton("Thêm tất cả");
    private JButton btnHuy = new JButton("Hủy");
    public DefaultTableModel model;

    public ImportQLKHDialog(Frame frame, DefaultTableModel modelEx) {
        super(frame, "Nhập Excel", true);
        this.model = modelEx; // Gán model được truyền từ CustomerController
        setSize(700, 400);
        this.setLayout(new BorderLayout());
        add(addMiddle(), BorderLayout.CENTER);
        add(addFooter(), BorderLayout.SOUTH);

        setLocationRelativeTo(frame);
    }

    private JPanel addMiddle() {
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel addFooter() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(btnThemTatCa);
        panel.add(btnHuy);
        return panel;
    }

    public JButton getBtnThemTatCa() {
        return this.btnThemTatCa;
    }

    public JButton getBtnHuy() {
        return this.btnHuy;
    }
}