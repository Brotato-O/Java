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
import java.awt.event.*;
import java.util.*;

public class ThongKeBanSach extends JPanel {
    JComboBox<String> cbbNam = new JComboBox<>();

    public ThongKeBanSach() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

        add(cbbNam);

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
        ArrayList<CTHD> danhSachCTHD = new CTHDBLL().selectAll();
        ArrayList<SachThongKe> thongKe = new ArrayList<>();

        // Lọc hóa đơn đúng năm
        ArrayList<String> maHDTrongNam = new ArrayList<>();
        for (int i = 0; i < danhSachHD.size(); i++) {
            String ngayLap = danhSachHD.get(i).getNgayLap();
            String nam = ngayLap.substring(0, 4);
            if (nam.equals(namDuocChon)) {
                maHDTrongNam.add(danhSachHD.get(i).getMaHD());
            }
        }

        // Gộp số lượng theo mã sách
        for (int i = 0; i < danhSachCTHD.size(); i++) {
            CTHD ct = danhSachCTHD.get(i);
            if (maHDTrongNam.contains(ct.getMaHD())) {
                String maSach = ct.getMaSach();
                int soLuong = ct.getSoLuong();

                boolean daTonTai = false;
                for (int j = 0; j < thongKe.size(); j++) {
                    if (thongKe.get(j).maSach.equals(maSach)) {
                        thongKe.get(j).tongSoLuong += soLuong;
                        daTonTai = true;
                        break;
                    }
                }
                if (!daTonTai) {
                    thongKe.add(new SachThongKe(maSach, soLuong));
                }
            }
        }

        // Sắp xếp giảm dần theo tổng số lượng
        for (int i = 0; i < thongKe.size() - 1; i++) {
            for (int j = i + 1; j < thongKe.size(); j++) {
                if (thongKe.get(i).tongSoLuong < thongKe.get(j).tongSoLuong) {
                    SachThongKe temp = thongKe.get(i);
                    thongKe.set(i, thongKe.get(j));
                    thongKe.set(j, temp);
                }
            }
        }

        // Lấy top 5 (hoặc ít hơn nếu không đủ)
        int soLuongHienThi = Math.min(5, thongKe.size());
        for (int i = 0; i < soLuongHienThi; i++) {
            dataset.addValue(thongKe.get(i).tongSoLuong, "Số lượng bán", thongKe.get(i).maSach);
        }

        // Hiển thị biểu đồ
        JFreeChart barChart = ChartFactory.createBarChart(
            "Top 5 sách bán chạy năm " + namDuocChon,
            "Mã sách",
            "Số lượng",
            dataset
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 400));

        removeAll();
        add(cbbNam);
        add(chartPanel);
        revalidate();
        repaint();
    }

    // Lớp phụ để lưu thống kê sách
    class SachThongKe {
        String maSach;
        int tongSoLuong;

        public SachThongKe(String maSach, int tongSoLuong) {
            this.maSach = maSach;
            this.tongSoLuong = tongSoLuong;
        }
    }


}
