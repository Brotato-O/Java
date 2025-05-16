package GUI.view;

import BLL.CustomerBLL;
import DTO.CustomerDTO;
import GUI.controller.CustomerController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import main.main;

public class CustomerManagement extends JPanel {

    Dimension d = new Dimension(Integer.MAX_VALUE, 25);
    int height = main.height;
    int width = main.width;

    // Fields
    private JPanel headerContent, jlabelRight;
    private JTable tableCus;
    private JButton xuatExcelBtn,nhapExcelBtn,btnThem,btnSua,btnXoa,btnLamMoi;
    private JTextField txtMaKH, txtTen, txtEmail, txtSDT, txtNgaySinh;
    private JRadioButton rdoNam, rdoNu;
    private JTable table;
    private DefaultTableModel model;
    private JComboBox<String> cboTimKiem;
    private JTextField txtTimKiem;
    private JButton btnTimKiem;

    public CustomerManagement() {
        initialize();
        new CustomerController(this);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // Set up the main panel layout
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(700, 200));

        JLabel titelLabel = new JLabel("THÔNG TIN KHÁCH HÀNG");
        titelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titelLabel, BorderLayout.NORTH);

        JPanel middleJPanel = new JPanel();
        add(middleJPanel, BorderLayout.CENTER);
        middleJPanel.setLayout(new BorderLayout(0, 0));

        headerContent = new JPanel();
        middleJPanel.add(headerContent, BorderLayout.NORTH);
        headerContent.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        String[] columnNames = { "Mã KH", "Tên KH", "SĐT", "Email", "Phái", "Ngày Sinh" };
        CustomerBLL customerBLL = new CustomerBLL();
        ArrayList<CustomerDTO> listKH = customerBLL.getDSKH();
        model = new DefaultTableModel(columnNames, 0);
        for (CustomerDTO kh : listKH) {
            Object[] row = { 
                kh.getMaKH(), 
                kh.getTenKH(), 
                kh.getSDT(), 
                kh.getEmail(), 
                kh.getGioiTinh(), 
                kh.getNgaySinh() };
            model.addRow(row);
        }
        

        // Tạo bảng có tiêu đề
        tableCus = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableCus);
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        middleJPanel.add(scrollPane, BorderLayout.CENTER);

        jlabelRight = new JPanel();
        middleJPanel.add(jlabelRight, BorderLayout.EAST);
        jlabelRight.setPreferredSize(new Dimension(150, 80)); // Để tránh bị kéo dài

        // Chuyển sang FlowLayout để tránh nút bị kéo dãn
        jlabelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        xuatExcelBtn = new JButton("Xuất Excel");
        nhapExcelBtn = new JButton("Nhập Excel");

        // Đặt kích thước nhỏ hơn cho các nút
        xuatExcelBtn.setPreferredSize(new Dimension(120, 30));
        nhapExcelBtn.setPreferredSize(new Dimension(120, 30));

        xuatExcelBtn.setBackground(new java.awt.Color(0, 153, 255)); // Xanh dương
        xuatExcelBtn.setForeground(new java.awt.Color(255, 255, 255)); // Chữ trắng
        nhapExcelBtn.setBackground(new java.awt.Color(0, 204, 102)); // Xanh lá cây
        nhapExcelBtn.setForeground(new java.awt.Color(255, 255, 255)); // Chữ trắng


        jlabelRight.add(xuatExcelBtn);
        jlabelRight.add(nhapExcelBtn);

        JPanel footerJPanel = new JPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("NHẬP THÔNG TIN");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        footerJPanel.setBorder(titledBorder);
        footerJPanel.setLayout(new GridLayout(1, 2, 10, 0));

        // Đang code phần bên trái cái form
        JPanel footerLeft = new JPanel();
        footerJPanel.add(footerLeft, BorderLayout.WEST); // Thêm footerLeft vào footerJPanel
        footerLeft.setLayout(new BorderLayout());

        JPanel pnlInput = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Row 1
        addLabel(pnlInput, "Mã KH:", gbc, 0, 0);
        txtMaKH = addTextField(pnlInput, gbc, 1, 0);
        txtMaKH.setEditable(true);
        addLabel(pnlInput, "Phái:", gbc, 2, 0);
        JPanel pnlGender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.rdoNam = new JRadioButton("Nam");
        this.rdoNu = new JRadioButton("Nữ");
        ButtonGroup bgGioiTinh = new ButtonGroup();
        bgGioiTinh.add(rdoNam);
        bgGioiTinh.add(rdoNu);
        pnlGender.add(rdoNam);
        pnlGender.add(rdoNu);
        gbc.gridx = 3;
        pnlInput.add(pnlGender, gbc);

        // Row 3
        addLabel(pnlInput, "Tên:", gbc, 0, 2);
        txtTen = addTextField(pnlInput, gbc, 1, 2);
        addLabel(pnlInput, "Ngày sinh:", gbc, 2, 2);
        txtNgaySinh = new JTextField(15);
        gbc.gridx = 3;
        gbc.gridy = 2;
        pnlInput.add(txtNgaySinh, gbc);

        // Row 4
        addLabel(pnlInput, "Email:", gbc, 0, 3);
        txtEmail = addTextField(pnlInput, gbc, 1, 3);

        // Row 5
        addLabel(pnlInput, "SĐT:", gbc, 0, 4);
        txtSDT = addTextField(pnlInput, gbc, 1, 4);

        footerLeft.add(pnlInput, BorderLayout.WEST);

        JPanel jplEven = new JPanel();
        jplEven.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        footerLeft.add(jplEven, BorderLayout.SOUTH);
        btnThem = new JButton("THÊM");
        btnXoa = new JButton("XÓA");
        btnSua = new JButton("SỬA");
        btnLamMoi = new JButton("LÀM MỚI");
        jplEven.add(btnThem);
        jplEven.add(btnXoa);
        jplEven.add(btnSua);
        jplEven.add(btnLamMoi);

btnThem.setBackground(new java.awt.Color(0, 153, 255));     // Xanh dương
btnThem.setForeground(new java.awt.Color(255, 255, 255));   // Trắng

btnXoa.setBackground(new java.awt.Color(255, 51, 51));       // Đỏ
btnXoa.setForeground(new java.awt.Color(255, 255, 255));     // Trắng

btnSua.setBackground(new java.awt.Color(255, 153, 0));       // Cam
btnSua.setForeground(new java.awt.Color(255, 255, 255));     // Trắng

btnLamMoi.setBackground(new java.awt.Color(0, 204, 102));    // Xanh lá
btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));  // Trắng


        // Phần bên phải footer
        JPanel footerRight = new JPanel();
        TitledBorder searchBorder = BorderFactory.createTitledBorder("TÌM KIẾM");
        searchBorder.setTitleJustification(TitledBorder.CENTER);
        footerRight.setBorder(searchBorder);
        footerRight.setLayout(new GridLayout(4, 1, 5, 5));

        // Dòng 1: ComboBox tìm kiếm
        JPanel pnlSearchRow1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cboTimKiem = new JComboBox<>(new String[] { "Mã Khách Hàng","Tên Khách Hàng" });
        cboTimKiem.setPreferredSize(new Dimension(200, 25));
        pnlSearchRow1.add(cboTimKiem);

        // Dòng 3: TextField tìm kiếm
        JPanel pnlSearchRow3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(200, 25));
        pnlSearchRow3.add(txtTimKiem);

        // Dòng 4: Nút tìm kiếm
        JPanel pnlSearchRow4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnTimKiem = new JButton("Tìm kiếm");
        pnlSearchRow4.add(btnTimKiem);

btnTimKiem.setBackground(new java.awt.Color(51, 102, 255));  // Xanh tím nhạt
btnTimKiem.setForeground(new java.awt.Color(255, 255, 255)); // Trắng

        footerRight.add(pnlSearchRow1);
        footerRight.add(pnlSearchRow3);
        footerRight.add(pnlSearchRow4);

        footerJPanel.add(footerRight);

        add(footerJPanel, BorderLayout.SOUTH);
    }

    private void addLabel(JPanel panel, String text, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(new JLabel(text), gbc);
    }

    private JTextField addTextField(JPanel panel, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        JTextField textField = new JTextField(15);
        panel.add(textField, gbc);
        return textField;
    }

    public JTable getTable() {
        return tableCus;
    }

    public JTextField getTFMaKH() {
        return txtMaKH;
    }

    public JTextField getTFTenKH() {
        return txtTen;
    }

    public JTextField getTFEmail() {
        return txtEmail;
    }

    public JTextField getTFSDT() {
        return txtSDT;
    }

    public JTextField getTFNgaySinh() {
        return txtNgaySinh;
    }

    public JRadioButton getRdoNam() {
        return rdoNam;
    }

    public JRadioButton getRdoNu() {
        return rdoNu;
    }

    public JButton getBtnThem() {
        return btnThem;
    }

    public JButton getBtnXoa() {
        return btnXoa;
    }

    public JButton getBtnSua() {
        return btnSua;
    }

    public JButton getBtnLamMoi() {
        return btnLamMoi;
    }

    public JButton getXuatExcelBtn() {
        return xuatExcelBtn;
    }

    public JButton getNhapExcelBtn() {
        return nhapExcelBtn;
    }

    public JComboBox<String> getCboTimKiem() {
        return cboTimKiem;
    }

    public JTextField getTxtTimKiem() {
        return txtTimKiem;
    }

    public JButton getBtnSearch() {
        return btnTimKiem; 
    }

}