/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import DTO.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 *
 * @author ADMIN
 */
public class QLBH extends JPanel{
    Dimension d= new Dimension(100, 25);
    public JTextField maHD= new JTextField();
    public JTextField maNV= new JTextField();
    public JButton ctMaNV= new JButton("...");
    public JTextField maKH= new JTextField();
    public JButton ctMaKH= new JButton("...");
    public JTextField ngayLap= new JTextField();
    public JButton taoHD= new JButton("TẠO HÓA ĐƠN");
    public ArrayList <GG> list = new ArrayList<>();
    public JTextField maSach= new JTextField();
    public JButton ctMaSach= new JButton("...");
    public JButton ctMAGG= new JButton("...");
    public JTextField tenSach= new JTextField();
    public JTextField soLg= new JTextField();
    public JTextField donGia= new JTextField();
    public JTextField maGG= new JTextField();
    public JButton them= new JButton("Thêm");
    public BLLQLGG bllqlgg = new BLLQLGG();
    public JButton xacNhan= new JButton("Xác nhận");
    public JButton huy= new JButton("Hủy");
    public JTable table=new JTable();
    public JTextField thanhTien= new JTextField();
    public JTextField giamGia= new JTextField();
    public JTextField tongTien= new JTextField();
    public JComboBox phuongThuc= new JComboBox();
    public JButton sua= new JButton("Sửa");
    public JButton xoa= new JButton("Xóa");
    public ArrayList<CTHD> listCTHD = new ArrayList<>();
    DefaultTableModel model= (DefaultTableModel) table.getModel();
    float total = 0;
    public CTHDBLL cthdbll = new CTHDBLL();
    public QLBH(){
        setLayout(new BorderLayout());
        JPanel header= new JPanel();
        header.setPreferredSize(new Dimension(100, 50));
        header.add(new JLabel("QUẢN LÝ BÁN HÀNG"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header, BorderLayout.NORTH);
        JPanel container= new JPanel();
        
        container.setLayout(new BorderLayout());
        container.add(HD(), BorderLayout.NORTH);
        
        JPanel bottom= new JPanel();
        bottom.setLayout(new BorderLayout());
        Border bd= BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder ttBd= new TitledBorder("Thông tin hóa đơn");
        ttBd.setTitleColor(Color.GREEN);
        ttBd.setTitleJustification(TitledBorder.CENTER);
        ttBd.setTitleFont(new Font("Arial", Font.PLAIN, 22));
        bd= BorderFactory.createTitledBorder(ttBd);
        bottom.setBorder(bd);
        
        bottom.add(left(), BorderLayout.WEST);
        bottom.add(right());
        
        container.add(bottom, BorderLayout.CENTER);
        add(container, BorderLayout.CENTER);
    ctMaSach.addActionListener(event -> {
        ctSach();
    });
    ctMAGG.addActionListener(event -> {
        ctGG();
    });
    //lỗi do dùng khác database
    ctMaNV.addActionListener(event -> {
        ctNV();
    });
    taoHD.addActionListener(even -> {

    });
    soLg.addFocusListener(new FocusAdapter(){
        @Override
            public void focusLost(FocusEvent e){
                BLLQLGG bllqlgg= new BLLQLGG();
                ArrayList<GG> gg= bllqlgg.getAllGGByBook(maSach.getText());
                float s= 0;
                System.out.println(gg.size());
                for (int i=0; i< gg.size(); i++){
                    s+= gg.get(i).getLuongGiam();
                }
                try{
                    s= s* Float.parseFloat(soLg.getText());
                }
                catch(Exception e1){
                    return;
                }
                maGG.setText(String.valueOf(s));
            }
        });
    them.addActionListener(even -> {
        if (soLg.getText().trim().isEmpty() || donGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ số lượng, đơn giá và mã giảm giá.");
            return;
        }
        CTHD cthd = new CTHD();
        cthd.setMaSach(maSach.getText());
        //hiện tại đang cho thêm cố định vào HD001
        cthd.setMaHD(maHD.getText());
        try{
            cthd.setSoLuong(Integer.parseInt(soLg.getText()));
            int soluong = Integer.parseInt(soLg.getText());
            float dongia = Float.parseFloat(donGia.getText());
            float a= Float.parseFloat(maGG.getText());
            
            cthd.setGiamGia(a);
            cthd.setGiaTien(Float.parseFloat(donGia.getText()));
            cthd.setTongTien((float)soluong*dongia);
            float tt = (float)soluong * dongia;
            cthd.setThanhTien(tt-a);
            float tt1= tt-a;
            total = total + tt1;
            thanhTien.setText(String.valueOf(total));
            int check= 0;
            for (int i=0; i< listCTHD.size(); i++){
                if (listCTHD.get(i).getMaSach().equalsIgnoreCase(maSach.getText())){
                    listCTHD.get(i).setSoLuong(listCTHD.get(i).getSoLuong()+ soluong) ;
                    listCTHD.get(i).setTongTien(listCTHD.get(i).getTongTien()+ tt) ;
                    listCTHD.get(i).setGiamGia(listCTHD.get(i).getGiamGia()+ a) ;
                    listCTHD.get(i).setThanhTien(listCTHD.get(i).getThanhTien()+ tt1) ;
                    check= 1;
                    break;
                }
            }
            if (check==0) listCTHD.add(cthd);
            model.setRowCount(0);
            for (int i=0; i< listCTHD.size(); i++){
                model.addRow(new Object[]{
                listCTHD.get(i).getMaSach(),
                listCTHD.get(i).getSoLuong(),
                listCTHD.get(i).getGiaTien(),
                listCTHD.get(i).getTongTien(),
                listCTHD.get(i).getGiamGia(),
                listCTHD.get(i).getThanhTien()
                });
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    });
    xacNhan.addActionListener(even -> {
        if (listCTHD.size() ==0){
            JOptionPane.showMessageDialog(null, "NHập chitiethoadon");
            return;
        }
        HDBLL hdbll= new HDBLL();
        String maHD= this.maHD.getText();
        String maNV= this.maNV.getText();
        String maKH= this.maKH.getText();
        String ngayLap= this.ngayLap.getText();
        String phuongThuc= this.phuongThuc.getSelectedItem().toString();
        HD hd= new HD(maHD, "NV001", "KH001", "2000-02-02", phuongThuc, 0, 0, 0, 0);
        HDBLL hdbll1= new HDBLL();
        int rs= hdbll1.add(hd);
        if (rs == 0) JOptionPane.showMessageDialog(null, "Không dc để trống");
        else if (rs == -1) JOptionPane.showMessageDialog(null, "HD dã tồn tại");
        else if (rs == -2) JOptionPane.showMessageDialog(null, "Nhập đúng định dạng yyyy-MM-dd");
        else{
            if (cthdbll.addall(listCTHD)){
                total = 0 ;
                thanhTien.setText(String.valueOf(total));
                for (int i=0; i<listCTHD.size(); i++){
                    new HDBLL().updateTongTienAdd(listCTHD.get(i));
                }
                model.setRowCount(0);
                listCTHD.clear();
                JOptionPane.showMessageDialog(container, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(container, "Đã xảy ra lỗi khi thêm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    xoa.addActionListener(even -> {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
        listCTHD.remove(selectedRow);
        float tt = Float.parseFloat(table.getValueAt(selectedRow, 5).toString());
        total = total - tt;
        thanhTien.setText(String.valueOf(total));
        showTable();
    }
});
    sua.addActionListener(even -> {
        int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
        // Lấy dữ liệu từ các JTextField
        String soLuongStr = soLg.getText();
        String donGiaStr = donGia.getText();
        String maGiam = maGG.getText();
        int soluong = Integer.parseInt(soLg.getText());
        float dongia = Float.parseFloat(donGia.getText());
        float a;
        float tt = (float)soluong * dongia;

        try {
            CTHD cthd = listCTHD.get(selectedRow);
            cthd.setMaSach(maSach.getText());
        //hiện tại đang cho thêm cố định vào HD001
        cthd.setMaHD("HD001");
        cthd.setSoLuong(Integer.parseInt(soLg.getText()));
        if (maGG.getText().trim().isEmpty()) {
            cthd.setGiamGia(0);
            a=0;
        }else {
            cthd.setGiamGia(Float.parseFloat(maGG.getText()));
            a= Float.parseFloat(maGG.getText());
        }
        cthd.setGiaTien(Float.parseFloat(donGia.getText()));
        cthd.setTongTien((float)soluong*dongia);
        cthd.setThanhTien(tt-a);
        float tt1= tt-a;
        float tt2 = Float.parseFloat(table.getValueAt(selectedRow, 5).toString());
        total = total - tt2;
        total = total + tt1;
        thanhTien.setText(String.valueOf(total));
        table.setValueAt(maSach.getText().trim(), selectedRow, 0);
        table.setValueAt(soLuongStr, selectedRow, 1);
        table.setValueAt(donGiaStr, selectedRow, 2);
        table.setValueAt(tt, selectedRow, 3);
        table.setValueAt(maGiam, selectedRow, 4);
        table.setValueAt(tt1, selectedRow, 5);
            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Số lượng hoặc đơn giá không hợp lệ!");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để sửa.");
    }
    });
    huy.addActionListener(even -> {
        table.clearSelection();
        maSach.setText("");
        soLg.setText("");
        donGia.setText("");
        maGG.setText("");
        tenSach.setText("");
    });
    table.getSelectionModel().addListSelectionListener(e -> {
    
        if (!e.getValueIsAdjusting()) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                
                maSach.setText(table.getValueAt(selectedRow, 0).toString());
                soLg.setText(table.getValueAt(selectedRow, 1).toString());
                donGia.setText(table.getValueAt(selectedRow, 2).toString());
                tenSach.setText("");
                maGG.setText(table.getValueAt(selectedRow, 4).toString());

               
            }
        }
    });
    }
   
    public JPanel HD(){
        JPanel p= new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JPanel top= new JPanel();
        top.setLayout(new GridLayout(1, 3));
        JPanel[] left= new JPanel[5];
        for (int i=0; i< 5; i++){
            left[i]= new JPanel();
            left[i].setLayout(new FlowLayout(FlowLayout.RIGHT));
        }
        left[0].add(new JLabel("Mã HD"));
        left[1].add(new JLabel("Mã KH"));
        left[2].add(new JLabel("Mã NV"));
        left[3].add(new JLabel("Ngày lập"));
        left[4].add(new JLabel("Phương thức"));
        DefaultComboBoxModel model= (DefaultComboBoxModel) phuongThuc.getModel();
        model.addElement("Tien mat");
        model.addElement("Chuyen khoan");
        model.addElement("Quet the");
        
        JPanel[] container= new JPanel[5];
        for (int i=0; i< 5; i++){
            container[i]= new JPanel();
            container[i].setLayout(new GridLayout(1, 2));
        }
        JPanel[] right= new JPanel[2];
        for (int i=0; i< 2; i++){
            right[i]= new JPanel();
            right[i].setLayout(new BoxLayout(right[i], BoxLayout.X_AXIS));
        }
        right[0].add(maNV);
        right[0].add(ctMaNV);
        right[1].add(maKH);
        right[1].add(ctMaKH);
        
        for(int i=0; i< 3; i++)
            top.add(container[i]);
        
        container[0].add(left[0]);
        container[0].add(maHD);
        container[1].add(left[1]);
        container[1].add(right[0]);
        container[2].add(left[2]);
        container[2].add(right[1]);
        
        container[3].add(left[3]);
        container[3].add(ngayLap);
        container[4].add(left[4]);
        container[4].add(phuongThuc);
        
        JPanel bottom= new JPanel();
//        bottom.setLayout(new GridLayout(1, 2));
        bottom.add(container[3]);
        bottom.add(container[4]);
//        bottom.add(taoHD);
        
        JPanel temp= new JPanel();
        temp.add(new JLabel("----------------------------------------------------------------------------------------------------------------------------------------------------------"));
        temp.setLayout(new FlowLayout());
        
        p.add(top);
        p.add(bottom);
        p.add(temp);
        maNV.setEditable(false);
        maKH.setEditable(false);
        return p;
    }
    
    public JPanel left(){
        JPanel p= new JPanel();
        JPanel out= new JPanel();
        out.setLayout(new BoxLayout(out, BoxLayout.Y_AXIS));
        JPanel img= new JPanel();
        img.setSize(300, 200);
        img.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel[] input= new JPanel[5];
        out.add(img);
        ImageIcon icon= new ImageIcon(getClass().getResource("../../img/search.png"));
        Image img1= icon.getImage();
        img1= img1.getScaledInstance(300, 200, Image.SCALE_AREA_AVERAGING);
        JLabel label = new JLabel(new ImageIcon(img1));
        img.add(label);
        for(int i=0; i< 5; i++){
            input[i]= new JPanel();
            input[i].setLayout(new GridLayout(1, 2));
            out.add(input[i]);
        }
        JPanel temp= new JPanel();
        temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));
        temp.add(maSach);
        temp.add(ctMaSach);
        maSach.setEditable(false);
        JPanel temp1= new JPanel();
        temp1.setLayout(new BoxLayout(temp1, BoxLayout.X_AXIS));
        temp1.add(maGG);
        temp1.add(ctMAGG);
        maGG.setEditable(false);
        input[0].add(new JLabel("Mã sách"));
        input[0].add(temp);
        input[1].add(new JLabel("Tên sách"));
        input[1].add(tenSach);
        tenSach.setEditable(false);
        input[2].add(new JLabel("Số lượng"));
        input[2].add(soLg);
        input[3].add(new JLabel("Đơn giá"));
        input[3].add(donGia);
        donGia.setEditable(false);
        input[4].add(new JLabel("Giảm giá"));
        input[4].add(temp1);
        out.add(them);
        
        p.add(out);
        return p;
    }
    
    public JPanel right(){
        JPanel p= new JPanel();
        p.setLayout(new BorderLayout());
        
        DefaultTableModel model= (DefaultTableModel) table.getModel();
        String[] column= new String[]{"Mã sách", "Số lượng", "Đơn giá", "Tổng tiền", "Giảm giá", "Thành tiền"};
        model.setColumnIdentifiers(column);
        showTable();
        JScrollPane pane= new JScrollPane(table);
        p.add(pane);
        
        JPanel bottom= new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        JPanel up= new JPanel();
        up.setLayout(new GridLayout(1, 6));
        up.add(new JLabel("Tổng tiền"));
        up.add(tongTien);
        up.add(new JLabel("Giảm giá"));
        up.add(giamGia);
        up.add(new JLabel("Thành tiền"));
        up.add(thanhTien);
        bottom.add(up);
        JPanel down= new JPanel();
        down.setLayout(new GridLayout(1, 4));
        down.add(xacNhan);
        down.add(huy);
        down.add(sua);
        down.add(xoa);
        bottom.add(down);
        p.add(bottom, BorderLayout.SOUTH);
        
        return p;
    }
    private void ctSach(){
    JFrame fr1 = new JFrame();
        fr1.dispose();
		fr1.setSize(400, 550);
        fr1.setLayout(new BorderLayout());
       
    JPanel tbQLS= new JPanel();
    ArrayList<Book> list1 = new ArrayList<>() ;  
    DefaultTableModel modelHD= new DefaultTableModel();
    JTable tableHD= new JTable();
        list1 = new BLLQLS().getAllBooks();

       
        tbQLS.setLayout(new BoxLayout(tbQLS, BoxLayout.Y_AXIS));
    String[] colums= {"MÃ SÁCH","TÊN SÁCH", "TÊN NXB","MÃ THỂ LOẠI", "TÊN TÁC GIẢ", "NĂM XUẤT BẢN", "SỐ LƯỢNG", "ĐƠN GIÁ","HÌNH ẢNH"};
        modelHD.setColumnIdentifiers(colums);
        modelHD.setRowCount(0); 
        for (Book s : list1) {
            modelHD.addRow(new Object[]{
                s.getMaSach(),
                s.getTenSach(),
                s.getMaNCC(),        
                s.getMaLoai(),
                s.getMaTacGia(),
                s.getNamXB(),                
                s.getSoLuong(),
                s.getDonGia(),
                s.getHA(),
                ""                   
            });
        }
        tableHD.setModel(modelHD);
    JPanel pTable= new JPanel(new BorderLayout());
    JScrollPane p1= new JScrollPane(tableHD);
        pTable.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pTable.add(p1);
        tbQLS.add(pTable);
    JButton chon = new JButton("Chọn");

        fr1.add(tbQLS,BorderLayout.NORTH);
        fr1.add(chon, BorderLayout.CENTER);
        fr1.setVisible(true);
        chon.addActionListener(even -> {
            int selectedRow = tableHD.getSelectedRow();
            if (selectedRow >= 0) {
                String maSach1 = tableHD.getValueAt(selectedRow, 0).toString(); 
                maSach.setText(tableHD.getValueAt(selectedRow, 0).toString());
                tenSach.setText(tableHD.getValueAt(selectedRow,1).toString());
                donGia.setText(tableHD.getValueAt(selectedRow, 7).toString());
                list = bllqlgg.getOneGG(maSach1);
                BLLQLGG bllqlgg= new BLLQLGG();
                ArrayList<GG> gg= bllqlgg.getAllGGByBook(maSach.getText());
                float s= 0;
                System.out.println(gg.size());
                for (int i=0; i< gg.size(); i++){
                    s+= gg.get(i).getLuongGiam();
                }
                try{
                    s= s* Float.parseFloat(soLg.getText());
                }
                catch(Exception e){
                    return;
                }
                maGG.setText(String.valueOf(s));
                fr1.dispose(); 
            } else {
                JOptionPane.showMessageDialog(fr1, "Vui lòng chọn một dòng sách!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        
    }
    private void ctGG(){
        JFrame fr1 = new JFrame();
            fr1.dispose();
            fr1.setSize(400, 550);
            fr1.setLayout(new BorderLayout());
           
        JPanel tbQLS= new JPanel();
    
        DefaultTableModel modelHD= new DefaultTableModel();
        JTable tableHD= new JTable();
            tbQLS.setLayout(new BoxLayout(tbQLS, BoxLayout.Y_AXIS));
        String[] colums= {"MÃ GIẢM GIÁ","TÊN GIẢM GIÁ", "LƯỢNG GIẢM","NGÀY BẮT ĐẦU", "NGÀY KẾT THÚC"};
            modelHD.setColumnIdentifiers(colums);
            modelHD.setRowCount(0); 
            for (GG s : list) {
                modelHD.addRow(new Object[]{
                    s.getMaGG(),
                    s.getTenGG(),
                    s.getLuongGiam(),        
                    s.getNgayBD(),
                    s.getNgayKT(),
                    ""                   
                });
            }
            tableHD.setModel(modelHD);
        JPanel pTable= new JPanel(new BorderLayout());
        JScrollPane p1= new JScrollPane(tableHD);
            pTable.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            pTable.add(p1);
            tbQLS.add(pTable);
        JButton chon = new JButton("Chọn");
    
            fr1.add(tbQLS,BorderLayout.NORTH);
            fr1.add(chon, BorderLayout.CENTER);
            fr1.setVisible(true);
            chon.addActionListener(even -> {
                int selectedRow = tableHD.getSelectedRow();
                if (selectedRow >= 0) {
                maGG.setText(tableHD.getValueAt(selectedRow, 2).toString());
    
                    fr1.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(fr1, "Vui lòng chọn giảm giá!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            });
            
        }
    private void ctNV(){
            JFrame fr1 = new JFrame();
                fr1.dispose();
                fr1.setSize(400, 550);
                fr1.setLayout(new BorderLayout());
               
            JPanel tbQLS= new JPanel();
            EmpBLL empBLL = new EmpBLL();
            ArrayList<EmployeeManagementDTO> listNV = empBLL.getDS();
            DefaultTableModel modelHD= new DefaultTableModel();
            JTable tableHD= new JTable();
                tbQLS.setLayout(new BoxLayout(tbQLS, BoxLayout.Y_AXIS));
            String[] colums= {"Mã NV", "Tên nhân viên", "Số điện thoại", "Email", "Phái", "Chức vụ", "Lương",
				"Ngày sinh"};
                modelHD.setColumnIdentifiers(colums);
                modelHD.setRowCount(0); 
                for (EmployeeManagementDTO nv : listNV) {
                    modelHD.addRow(new Object[]{
                        nv.getId_emp(), 
					nv.getName_emp(), 
					nv.getPhone_emp(), 
					nv.getEmail_emp(),
					nv.getGender_emp(),
					nv.getPosition_emp(),
					nv.getSalary_emp(),
					nv.getBirth_date(),
                        ""                   
                    });
                }
                tableHD.setModel(modelHD);
            JPanel pTable= new JPanel(new BorderLayout());
            JScrollPane p1= new JScrollPane(tableHD);
                pTable.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                pTable.add(p1);
                tbQLS.add(pTable);
            JButton chon = new JButton("Chọn");
        
                fr1.add(tbQLS,BorderLayout.NORTH);
                fr1.add(chon, BorderLayout.CENTER);
                fr1.setVisible(true);
                chon.addActionListener(even -> {
                    int selectedRow = tableHD.getSelectedRow();
                    if (selectedRow >= 0) {
                   maNV.setText(tableHD.getValueAt(selectedRow, 0).toString());
        
                        fr1.dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(fr1, "Vui lòng chọn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                });
                
            }
    public void showTable() {
    model.setRowCount(0); 
    for (CTHD s : listCTHD) {
     model.addRow(new Object[]{
    s.getMaSach(),
    s.getSoLuong(),
    s.getGiaTien(),        
    s.getTongTien(),
    s.getGiamGia(),
    s.getThanhTien(),                
    ""                   
      });
     }
  }   
}
