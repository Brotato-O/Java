package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import main.main;



public class QLS extends JPanel{
    Dimension d= new Dimension(Integer.MAX_VALUE, 25);
    int height= main.height;
    int width= main.width;
    JTextField txtMaSach = new JTextField();
    private JComboBox txtMaNXB = new  JComboBox();
    private JComboBox txtMaTheLoai = new JComboBox();
    private JComboBox txtMaTacGia = new JComboBox();
    JTextField txtTenSach = new JTextField();
    JTextField txtNamXuatBan = new JTextField();
    JTextField txtSoLuong = new JTextField();
    JTextField txtDonGia = new JTextField();
    JButton btnThem= new JButton("Thêm");
    JButton btnSua= new JButton("Sửa");
    JButton btnXoa= new JButton("Xóa");
    JButton btnLamMoi= new JButton("Làm mới");
    JButton inputExcel = new JButton("Nhập Excel");
    JButton outputExcel = new  JButton("Xuất Excel");
    
    JTextField txtMaSach1 = new JTextField();
    private JComboBox txtMaNXB1 = new JComboBox();;
    private JComboBox txtMaTheLoai1 = new JComboBox();;
    private JComboBox txtMaTacGia1 = new JComboBox();;
    JTextField txtTenSach1 = new JTextField();
    JTextField txtKhoangGiaTu = new JTextField();
    JTextField txtKhoangGiaDen = new JTextField();
    JTextField txtNXBTu = new JTextField();
    JTextField txtNXBDen = new JTextField();
    private JButton tim= new JButton();
    private JTable tableHD= new JTable();
    private DefaultTableModel modelHD= new DefaultTableModel();
    JTextField txtDonGiaThapNhat = new JTextField();
	JTextField txtDonGiaCaoNhat = new JTextField();
	JTextField txtSoLoaiSach = new JTextField();
	JTextField txtTongSoSach = new JTextField();
    JButton btnThongKe = new JButton("Thống Kê");
    Font font = new Font("Arial", Font.BOLD, 14);
//    SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
//         JSpinner spinner = new JSpinner(model);

//         // Định dạng hiển thị ngày tháng
//         JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
//         spinner.setEditor(editor);
    public QLS(){

        setLayout(new BorderLayout());
        JPanel header= new JPanel();
        header.setPreferredSize(new Dimension(100, 50));
        header.add(new JLabel("QUẢN LÝ HÓA ĐƠN"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header, BorderLayout.NORTH);
        JPanel container= new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(inputFieldsQLS());
        container.add(findFields());
        container.add(tbQLS());
        container.add(statsQLS());
        add(container, BorderLayout.CENTER);
        
    }
    public JPanel inputFieldsQLS(){
        JPanel QLS = new JPanel(new GridLayout(1, 5 , 10, 0));
        QLS.setPreferredSize(new Dimension(0, (int)(0.6*height)));
        QLS.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); 

        
        JPanel txtQLS= new JPanel(new GridLayout(8,2));
       
    	     txtQLS.add(new JLabel ("Mã Sách: "));
             txtQLS.add(txtMaSach);
    	     txtQLS.add( new JLabel ("Mã NXB: "));
             txtQLS.add(txtMaNXB);
             txtQLS.add(new JLabel ("Mã Thể Loại: "));
			 txtQLS.add(txtMaTheLoai);
             txtQLS.add(new JLabel ("Mã Tác Giả: "));
             txtQLS.add(txtMaTacGia);
             txtQLS.add( new JLabel ("Tên Sách: "));
             txtQLS.add(txtTenSach);
    	     txtQLS.add( new JLabel ("Năm Xuất Bản: "));
             txtQLS.add(txtNamXuatBan);
             txtQLS.add(new JLabel ("Số Lượng: "));
             txtQLS.add(txtSoLuong);
             txtQLS.add(new JLabel ("Đơn Giá: "));
             txtQLS.add(txtDonGia);
          
             JPanel btnQLS = new JPanel( new GridLayout(4,1,2,10));
             
		
    	     btnQLS.add(btnThem);
    	     btnThem.setForeground(Color.WHITE);
    	     btnThem.setBackground(Color.CYAN);
             btnQLS.add(btnSua);
    	     btnSua.setForeground(Color.WHITE);
    	     btnSua.setBackground(Color.blue);
             btnQLS.add(btnXoa);
    	     btnXoa.setForeground(Color.WHITE);
    	     btnXoa.setBackground(Color.red);
             btnQLS.add(btnLamMoi);
    	     btnLamMoi.setForeground(Color.WHITE);
    	     btnLamMoi.setBackground(Color.green);
    	     

             JPanel imgQLS = new JPanel();
             
    	     imgQLS.setBackground(Color.gray);
    	     JPanel inOutExcel = new JPanel( new GridLayout(4,1,2,10));
             inOutExcel.setPreferredSize(new Dimension((int)(0.2*width), 0));
    	    inOutExcel.add(inputExcel);
            inOutExcel.add(outputExcel);
      

        QLS.add(txtQLS);
        QLS.add(btnQLS);
        QLS.add(imgQLS);
        QLS.add(inOutExcel);
        setLabelFont(QLS,font);

        return QLS;
    }
    	
    public JPanel findFields(){
        JPanel findQLS= new JPanel();
        findQLS.setLayout(new BorderLayout());
        findQLS.setPreferredSize(new Dimension(0, (int)(0.2*height)));
        JPanel center= new JPanel();
        center.setLayout(new GridLayout(2, 4));
        center.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        JPanel[] t= new JPanel[10];
        JPanel[] c1= new JPanel[10];
        JPanel[] c2= new JPanel[10];
        for (int i=0; i< 10; i++)
            t[i]= new JPanel(new GridLayout(1,2));
        for (int i=0; i< 10; i++){
            c1[i]= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        }
        for (int i=0; i< 10; i++){
            c2[i]= new JPanel();
            c2[i].setLayout(new BoxLayout(c2[i], BoxLayout.Y_AXIS));
        }
        c1[0].add(new JLabel("Mã Sách"));
        c2[0].add(txtMaSach1);
        c1[1].add(new JLabel("Mã TG"));
        c2[1].add(txtMaTacGia1);
        c1[2].add(new JLabel("Tên Sách"));
        c2[2].add(txtTenSach1);
        c1[3].add(new JLabel("Mã NXB"));
        c2[3].add(txtMaNXB1);
        c1[4].add(new JLabel("Mã TL"));
        c2[4].add(txtMaTheLoai1);
        c1[5].add(new JLabel("Năm xuất bản từ"));
        c2[5].add(txtNXBTu);
        c1[6].add(new JLabel("Năm xuất bản đến"));
        c2[6].add(txtNXBDen);
        c1[7].add(new JLabel("Giá tiền từ"));
        c2[7].add(txtKhoangGiaTu);
        c1[8].add(new JLabel("Giá tiền đến"));
        c2[8].add(txtKhoangGiaDen);
        c1[9].add(new JLabel());
        c2[9].add(new JLabel());

        for (int i=0; i< 10; i++){
            t[i].add(c1[i]);
            t[i].add(c2[i]);
            center.add(t[i]);
        }
        findQLS.add(center, BorderLayout.CENTER);
        
        ImageIcon icon= new ImageIcon(getClass().getResource("../img/search.png"));
        Image img= icon.getImage();
        img= img.getScaledInstance(35, 35, Image.SCALE_AREA_AVERAGING);
        tim.setIcon(new ImageIcon(img));
        tim.setBackground(Color.GRAY);
        findQLS.add(tim, BorderLayout.EAST);
        
        Border thickBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK); //ko biết là gì nhưng sẽ ôn sau
        Border bd= BorderFactory.createLineBorder(Color.BLACK,5);
        TitledBorder ttBD= BorderFactory.createTitledBorder(thickBorder, "TÌM KIẾM HÓA ĐƠN");
        ttBD.setTitleJustification(TitledBorder.CENTER);
        ttBD.setTitleColor(Color.BLUE);
        ttBD.setTitleFont(new Font("Arial", Font.PLAIN, 22));
        bd= BorderFactory.createTitledBorder(ttBD);
        findQLS.setBorder(bd);
        
        setLabelFont(findQLS,font);
        return findQLS;
    }

    public JPanel tbQLS(){
        JPanel tbQLS= new JPanel();
       // tbQLS.setPreferredSize(new Dimension(0, (int)(0.3*height)));
        tbQLS.setLayout(new BoxLayout(tbQLS, BoxLayout.Y_AXIS));
        String[] colums= {"MÃ SÁCH", "MÃ NXB", "MÃ THỂ LOẠI", "MÃ TÁC GIẢ", "NĂM XUẤT BẢN", "SỐ LƯỢNG", "ĐƠN GIÁ","HÌNH ẢNH"};
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
    public JPanel statsQLS(){
        JPanel statsQLS = new JPanel(new FlowLayout());
        JPanel QLSTK= new JPanel( new GridLayout(2,2));
        statsQLS.add (btnThongKe);
        QLSTK.add( new JLabel("Đơn giá thấp nhất"));
        QLSTK.add(txtDonGiaThapNhat);
        QLSTK.add (new JLabel("Đơn giá cao nhất"));
        QLSTK.add(txtDonGiaCaoNhat);
        QLSTK.add( new JLabel("Số loại sách"));
        QLSTK.add(txtSoLoaiSach);
        QLSTK.add(new JLabel("Tổng số sách"));
        QLSTK.add(txtTongSoSach);
        statsQLS.add(QLSTK);
        setLabelFont(statsQLS,font);
        return statsQLS;
    }
    private void setLabelFont(JPanel panel, Font font) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(font);
            } else if (comp instanceof JPanel) {
                setLabelFont((JPanel) comp, font); // Đệ quy để áp dụng cho các panel con
            }
        }
    }
    
}
