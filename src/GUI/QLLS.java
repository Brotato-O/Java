package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import main.main;
import java.awt.*;


public class QLLS extends JPanel{
            Dimension d= new Dimension(Integer.MAX_VALUE, 25);
            int height= main.height;
            int width= main.width;
            private JButton tim= new JButton();
            private JTable tableHD= new JTable();
            private DefaultTableModel modelHD= new DefaultTableModel();
            
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
                add(container, BorderLayout.CENTER);

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
            findQLLS.add(txtDoTuoiQLLS2);
            txtDoTuoiQLLS2.setPreferredSize(new Dimension(100,30));
			findQLLS.add(new JLabel ("Độ Tuổi: "));
			findQLLS.add(txtTenTheLoaiSachQLLS2);
            txtTenTheLoaiSachQLLS2.setPreferredSize(new Dimension(100,30));
			findQLLS.add(btnTimKiemQLLS2);
			return findQLLS;
    }
    public JPanel tbQLLS(){
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
}
