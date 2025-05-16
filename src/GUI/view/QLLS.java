package GUI.view;

import BLL.BLLQLLS;
import DTO.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import main.main;

public class QLLS extends JPanel{
    private ArrayList <LoaiSach> list = new ArrayList<>();
    private BLLQLLS bllqlls = new BLLQLLS();
    Dimension d= new Dimension(Integer.MAX_VALUE, 25);
    int height= main.height;
    int width= main.width;
    private JTable tableHD= new JTable();
    private DefaultTableModel modelHD= new DefaultTableModel();
    private JTable tableHD1= new JTable();
    private DefaultTableModel modelHD1= new DefaultTableModel();     
	JTextField txt_MaLoaiSachQLLS = new JTextField();		
	JTextField txt_TenLoaiSachQLLS = new JTextField();		
	JTextField txt_DoTuoiQLLS = new JTextField();
    JButton btnThemLoaiSachQLLS = new JButton("Thêm");
    JButton btnXoaLoaiSachQLLS = new JButton("Xóa");
    JButton btnSuaLoaiSachQLLS = new JButton("Sửa");
    JButton btnTimKiemQLLS2 = new JButton("Tìm kiếm");
    JTextField txtMaTheLoaiSachQLLS2 = new JTextField();
	JTextField txtTenTheLoaiSachQLLS2 = new JTextField();
	JTextField txtDoTuoiQLLS2 = new JTextField();
    JTextField txtDoTuoiQLLS3 = new JTextField();

     public QLLS(){
                setLayout(new BorderLayout());
                JPanel header= new JPanel();
                header.setPreferredSize(new Dimension(100, 50));
                header.add(new JLabel("QUẢN LÝ LOẠI SÁCH"){{setFont(new Font("Arial", Font.BOLD, 26));}});
                add(header, BorderLayout.NORTH);
                JPanel container= new JPanel();
                container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
                container.add(inputFieldsQLLS());
                container.add(findQLLS());
                container.add(tbQLLS());
                container.add(tbQLS());
                add(container, BorderLayout.CENTER);
    tableHD1.getSelectionModel().addListSelectionListener(e -> {
    
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = tableHD1.getSelectedRow();
                        if (selectedRow >= 0) {
                            String magg = tableHD1.getValueAt(selectedRow, 0).toString();
                            txt_MaLoaiSachQLLS.setText(tableHD1.getValueAt(selectedRow, 0).toString());
                            txt_TenLoaiSachQLLS.setText(tableHD1.getValueAt(selectedRow, 1).toString());
                            txt_DoTuoiQLLS.setText(tableHD1.getValueAt(selectedRow, 2).toString());
                            loadCTGG(magg);
                           
                        }
                    }
                });
    btnThemLoaiSachQLLS.addActionListener(event  ->{
                    if (!isInputValid(container)) return;
                    LoaiSach book = new LoaiSach();
                    book.setMaLoai(txt_MaLoaiSachQLLS.getText());
                    book.setTenLoai(txt_TenLoaiSachQLLS.getText());
                    book.setDoTuoi(Integer.parseInt(txt_DoTuoiQLLS.getText()));
                   
                    if(bllqlls.addLoai(book)){
                        JOptionPane.showMessageDialog(container, "thêm thành công");
                         list.add(book);
                         showTable();
                    }else JOptionPane.showMessageDialog(container, "Trùng mã sách");
                
                });
    btnSuaLoaiSachQLLS.addActionListener(event -> {
                    if (!isInputValid(container)) return;  
                    LoaiSach book = new LoaiSach();
                    book.setMaLoai(txt_MaLoaiSachQLLS.getText());
                    book.setTenLoai(txt_TenLoaiSachQLLS.getText());
                    book.setDoTuoi(Integer.parseInt(txt_DoTuoiQLLS.getText()));
                    if (bllqlls.updateLoai(book)) {
                        JOptionPane.showMessageDialog(container, "Cập nhật thành công");
                        list = bllqlls.getAllLoai();
                        showTable();
                    } else {
                        JOptionPane.showMessageDialog(container, "Cập nhật thất bại, mã sách không tồn tại");
                    }
                });          
    btnXoaLoaiSachQLLS.addActionListener(event  ->{
                    LoaiSach book = new LoaiSach();
                    book.setMaLoai(txt_MaLoaiSachQLLS.getText());
                    if(bllqlls.deleteLSSQL(book)){
                        int a = JOptionPane.showConfirmDialog(container, "Bạn có chắc muốn xóa chứ?","Xác nhận", JOptionPane.YES_NO_OPTION);
                        if(a==JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(container, "Xóa thành công");
                        int selectedRow = tableHD1.getSelectedRow();
                        if (selectedRow >= 0) {
                        list.remove(selectedRow);
                        showTable();
                        }
                    }
                    }else JOptionPane.showMessageDialog(container, "Xóa thất bại");
                
                });
    btnTimKiemQLLS2.addActionListener(even ->{
        ArrayList<LoaiSach> kq = timKiemLoai();
            list = kq;
            showTable();
        });
    }
    public JPanel inputFieldsQLLS(){

			JPanel QLLSCenter = new JPanel(new BorderLayout());
           QLLSCenter.setPreferredSize(new Dimension(0, (int)(0.2*height)));
			JPanel txtQLLSCenter = new JPanel();
			txtQLLSCenter.add (new JLabel("Mã Loại Sách"));
            txt_MaLoaiSachQLLS.setPreferredSize(new Dimension(100,30));
			txtQLLSCenter.add(txt_MaLoaiSachQLLS);
			txtQLLSCenter.add( new JLabel("Tên Loại Sách"));
            txt_TenLoaiSachQLLS.setPreferredSize(new Dimension(100,30));
			txtQLLSCenter.add(txt_TenLoaiSachQLLS);
			txtQLLSCenter.add(new JLabel("Độ tuổi"));
            txt_DoTuoiQLLS.setPreferredSize(new Dimension(100,30));
			txtQLLSCenter.add(txt_DoTuoiQLLS);

			JPanel btnQLLSCenter = new JPanel();
			btnQLLSCenter.add(btnThemLoaiSachQLLS);
			btnThemLoaiSachQLLS.setForeground(Color.WHITE);
    	     btnThemLoaiSachQLLS.setBackground(Color.CYAN);
			btnQLLSCenter.add(btnXoaLoaiSachQLLS);
			btnXoaLoaiSachQLLS.setForeground(Color.WHITE);
			btnXoaLoaiSachQLLS.setBackground(Color.red);
			btnQLLSCenter.add(btnSuaLoaiSachQLLS);
			btnSuaLoaiSachQLLS.setForeground(Color.WHITE);
			btnSuaLoaiSachQLLS.setBackground(Color.blue);

			QLLSCenter.add(txtQLLSCenter, BorderLayout.NORTH);
			QLLSCenter.add(btnQLLSCenter,BorderLayout.CENTER);
            return QLLSCenter;
    }
    public JPanel findQLLS(){
        
        JPanel findQLLS = new JPanel();
        findQLLS.setPreferredSize(new Dimension(0, (int)(0.2*height)));
			TitledBorder blackline1 = BorderFactory.createTitledBorder("Tìm kiếm");
			blackline1.setTitleJustification(TitledBorder.CENTER);
			findQLLS.setBorder(blackline1);
			findQLLS.add(new JLabel ("mã thể loại: "));
            findQLLS.add(txtMaTheLoaiSachQLLS2);
            txtMaTheLoaiSachQLLS2.setPreferredSize(new Dimension(100,30));
			findQLLS.add(new JLabel ("tên sách: "));
            findQLLS.add(txtTenTheLoaiSachQLLS2);
            txtTenTheLoaiSachQLLS2.setPreferredSize(new Dimension(100,30));
			findQLLS.add(new JLabel ("Độ Tuổi: "));
            findQLLS.add(new JLabel ("từ: "));
			findQLLS.add(txtDoTuoiQLLS2);
            findQLLS.add(new JLabel ("đến: "));
			findQLLS.add(txtDoTuoiQLLS3);
            txtDoTuoiQLLS2.setPreferredSize(new Dimension(100,30));
            txtDoTuoiQLLS3.setPreferredSize(new Dimension(100,30));
			findQLLS.add(btnTimKiemQLLS2);
			return findQLLS;
    }
    public JPanel tbQLLS(){
        JPanel tbQLS= new JPanel();
       // tbQLS.setPreferredSize(new Dimension(0, (int)(0.3*height)));
        tbQLS.setLayout(new BoxLayout(tbQLS, BoxLayout.Y_AXIS));
        list = bllqlls.getAllLoai();
        String[] colums= {"MÃ LOẠI", "TÊN LOẠI", "ĐỘ TUỔI"};
        modelHD1.setColumnIdentifiers(colums);
        showTable();
        tableHD1.setModel(modelHD1);
        JPanel pTable= new JPanel(new BorderLayout());
        JScrollPane p1= new JScrollPane(tableHD1);
        pTable.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pTable.add(p1);
        tbQLS.add(pTable);
        return tbQLS;
    }
    public JPanel tbQLS(){
        JPanel tbQLS= new JPanel();
       // tbQLS.setPreferredSize(new Dimension(0, (int)(0.3*height)));
        tbQLS.setLayout(new BoxLayout(tbQLS, BoxLayout.Y_AXIS));
        String[] colums= {"MÃ SÁCH", "TÊN SÁCH", "MÃ LOẠI", "MÃ TÁC GIẢ", "NĂM XB", "SỐ LƯỢNG","ĐƠN GIÁ"};
        modelHD.setColumnIdentifiers(colums);
        modelHD.setNumRows(20);
        tableHD.setModel(modelHD);
        JPanel pTable= new JPanel(new BorderLayout());
        JScrollPane p1= new JScrollPane(tableHD);
        pTable.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pTable.add(p1);
        tbQLS.add(pTable);
        return tbQLS;
    }
    public void showTable() {
        modelHD1.setRowCount(0); 
        for (LoaiSach s : list) {
            modelHD1.addRow(new Object[]{
                s.getMaLoai(),
                s.getTenLoai(),
                s.getDoTuoi(),        
                ""                   
            });
        }
    }
    
    private void loadCTGG(String magg) {
        ArrayList<Book> listBook = bllqlls.getAllBook(magg);
    
        // Xóa sạch dữ liệu cũ trong model
        modelHD.setRowCount(0);
    
        // Thêm lại dữ liệu mới
        for (Book s : listBook) {
                modelHD.addRow(new Object[]{
                    s.getMaSach(),
                    s.getTenSach(),       
                    s.getMaLoai(),
                    s.getMaTacGia(),
                    s.getNamXB(),                
                    s.getSoLuong(),
                    s.getDonGia(),
                    ""                   
                });
            }
    }
    private boolean isInputValid( JPanel container ) {
        if (
            txt_MaLoaiSachQLLS.getText().trim().isEmpty() ||
            txt_TenLoaiSachQLLS.getText().trim().isEmpty()) {
    
            JOptionPane.showMessageDialog(container, "Vui lòng nhập đầy đủ thông tin.");
            return false;
        }
    
        try {
            Integer.parseInt( txt_DoTuoiQLLS.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(container, "Năm xuất bản, số lượng, đơn giá phải là số hợp lệ.");
            return false;
        }
    
        return true;
    }
    public ArrayList<LoaiSach> timKiemLoai() {
        String maloai = txtMaTheLoaiSachQLLS2.getText().trim();
        String tenloai = txtTenTheLoaiSachQLLS2.getText().trim();
        String dotuoitu = txtDoTuoiQLLS2.getText().trim();
        String dotuoiden = txtDoTuoiQLLS3.getText().trim();
       
    
        ArrayList<LoaiSach> ketQua = new ArrayList<>();
        for (LoaiSach s : bllqlls.getAllLoai()) {
            if (!maloai.isEmpty() && !s.getMaLoai().contains(maloai)) continue;
            if (!tenloai.isEmpty() && !s.getTenLoai().toLowerCase().contains(tenloai.toLowerCase())) continue;
            if (!dotuoitu.isEmpty() && s.getDoTuoi() < Integer.parseInt(dotuoitu)) continue;
            if (!dotuoiden.isEmpty() && s.getDoTuoi() > Integer.parseInt(dotuoiden)) continue;
            ketQua.add(s);
        }
        return ketQua;
    }
}
