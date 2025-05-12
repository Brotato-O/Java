package GUI.view;

import BLL.CTHDBLL;
import BLL.HDBLL;
import DTO.CTHD;
import DTO.HD;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;



import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;

public class ThongKeTongChi extends JPanel {
    JComboBox<String> cbbNam = new JComboBox<>();

    public ThongKeTongChi() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel header= new JPanel();
        header.setPreferredSize(new Dimension(100, 50));
        header.add(new JLabel("THỐNG KÊ TỔNG CHI THEO KHÁCH"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header);

        // Lấy model để thêm dữ liệu năm
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cbbNam.getModel();
        Set<String> set = new HashSet<>();  //ko biết là gì nhưng sẽ ôn sau =)))))

        HDBLL pnbll = new HDBLL();
        ArrayList<HD> pnList = pnbll.selectAll();

        // Lấy các năm duy nhất từ danh sách phiếu nhập
        for (int i=0; i< pnList.size(); i++) {
            String ngayNhap = pnList.get(i).getNgayLap(); 
            String nam = ngayNhap.substring(0, 4);
            set.add(nam);
        }

        ArrayList<String> listNam = new ArrayList<>(set);
        Collections.sort(listNam); // =VVVVVVVv
        for (String nam : listNam) {
            model.addElement(nam);
        }

        JPanel temp= new JPanel();
        temp.add(new JLabel("Năm:"));
        temp.add(cbbNam);
        add(temp);

        // Gọi biểu đồ ngay khi khởi tạo nếu có năm
        if (!listNam.isEmpty()) {
            thongKeTop5SachBanChay(listNam.get(0));
        }

        // Khi chọn năm khác
        cbbNam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nam = (String) cbbNam.getSelectedItem();
                thongKeTop5SachBanChay(nam);
            }
        });
    }

    // Phương thức thống kê số lượng phiếu nhập theo quý
    public void thongKeTop5SachBanChay(String namDuocChon) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ArrayList<HD> danhSachHD = new HDBLL().selectAll();
        ArrayList<HD> maSach = new ArrayList<>();
        ArrayList<KHThongKe> khachHang = new ArrayList<>();
        
        for(int i=0; i< danhSachHD.size();i++){
            HD hd = danhSachHD.get(i);
            String ngayLap = hd.getNgayLap();
            String nam = ngayLap.substring(0, 4);
            if(nam.equals(namDuocChon)){
                maSach.add(hd);
            }
        }

        for(int i=0; i< maSach.size(); i++){
            HD hd= maSach.get(i);
            String maKH = hd.getMaKH();
            float tongTien= hd.getTongTien();
            boolean tonTai = false;
            for(int j=0; j< khachHang.size();j++){
                KHThongKe kh= khachHang.get(j);
                if(kh.maKH.equals(maKH)){
                    kh.tongTien += tongTien;
                    tonTai = true;
                    break;
                }
            }
            if(!tonTai){
                khachHang.add(new KHThongKe(maKH, tongTien));
            }

        }

        for (int i=0; i< khachHang.size()-1; i++)
            for (int j=i+1; j< khachHang.size(); j++){
                if(khachHang.get(i).tongTien < khachHang.get(j).tongTien){
                    KHThongKe temp = khachHang.get(i);
                    khachHang.set(i, khachHang.get(j));
                    khachHang.set(j, temp);
                }
            }

        int soLuongHienThi = Math.min(5, khachHang.size());
        for (int i = 0; i < soLuongHienThi; i++) {
            dataset.addValue(khachHang.get(i).tongTien, "Số lượng bán", khachHang.get(i).maKH);
        }

        // Hiển thị biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
            "Top 5 Khách hàng vjp " + namDuocChon,
            "Mã KH",
            "Tổng tiền",
            dataset
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));

        removeAll();
        JPanel header= new JPanel();
        header.setPreferredSize(new Dimension(100, 50));
        header.add(new JLabel("THỐNG KÊ TỔNG CHI THEO KHÁCH"){{setFont(new Font("Arial", Font.BOLD, 26));}});
        add(header);
        JPanel temp= new JPanel();
        temp.add(new JLabel("Năm:"));
        temp.add(cbbNam);
        add(temp);
        add(chartPanel);
        revalidate();
        repaint();
    }

    // Lớp phụ để lưu thống kê sách
    class KHThongKe {
        String maKH;
        float tongTien;

        public KHThongKe(String maKH, float tongTien) {
            this.maKH = maKH;
            this.tongTien = tongTien;
        }
    }


}
