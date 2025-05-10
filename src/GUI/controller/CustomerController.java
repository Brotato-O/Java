package GUI.controller;

import BLL.CustomerBLL;
import DTO.CustomerDTO;
import GUI.dialog.QLKH.AddQLKHController;
import GUI.dialog.QLKH.AddQLKHDialog;
import GUI.dialog.QLKH.ImportQLKHController;
import GUI.dialog.QLKH.ImportQLKHDialog;
import GUI.view.CustomerManagement;
import excel.XuLyFileExcel;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;


public class CustomerController {

    private CustomerManagement view = null;
    private JTable table = null;
    private JButton btnThem,btnXoa,btnSua,btnLamMoi,btnSearch,btnNhapExcel,btnXuatExcel;
    private CustomerBLL customerBLL = new CustomerBLL();

    public CustomerController(CustomerManagement view) {
        this.view = view;
        this.table = this.view.getTable();

        this.btnThem = this.view.getBtnThem();
        this.btnXoa = this.view.getBtnXoa();
        this.btnSua = this.view.getBtnSua();
        this.btnLamMoi = this.view.getBtnLamMoi();
        this.btnSearch = this.view.getBtnSearch();
        this.btnNhapExcel = this.view.getNhapExcelBtn();
        this.btnXuatExcel = this.view.getXuatExcelBtn();

        clickRowTable();
        clickBtnThem();
        clickBtnXoa();
        clickBtnSua();
        clickBtnLamMoi();
        clickBtnSearch();
        btnClickExportExcel();
        btnClickImportExcel();
        
    }

    private void clickRowTable() {
        if (this.table != null) {
            this.table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String maNV = table.getValueAt(row, 0).toString();
                        CustomerDTO customer = customerBLL.getKH(maNV);
                        if (customer != null) {
                            view.getTFMaKH().setText(customer.getMaKH());
                            view.getTFTenKH().setText(customer.getTenKH());
                            view.getTFEmail().setText(customer.getEmail());
                            view.getTFSDT().setText(customer.getSDT());
                            view.getTFNgaySinh().setText(customer.getNgaySinh());
                            if (customer.getGioiTinh().equals("Nam")) {
                                view.getRdoNam().setSelected(true);
                                view.getRdoNu().setSelected(false);
                            } else {
                                view.getRdoNam().setSelected(false);
                                view.getRdoNu().setSelected(true);
                            }
                            view.getTFMaKH().setEditable(false);
                        }
                    }
                }
            });
        } else {
            System.out.println("Table is null!");
        }
    }

    private void clickBtnThem() {
        btnThem.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view);
            AddQLKHDialog addQLKHDialog = new AddQLKHDialog(frame);
            AddQLKHController addQLKHController = new AddQLKHController(addQLKHDialog,CustomerController.this);
            addQLKHDialog.setVisible(true);
        });
    }

    private void clickBtnLamMoi() {
        btnLamMoi.addActionListener(e-> {
            refreshTable();
        });
    }

    public void refreshTable() {
        try {
			ArrayList<CustomerDTO> listCus = customerBLL.getDSKH();
			loadDataModel(listCus);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    private void loadDataModel(ArrayList<CustomerDTO> list) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0); // Xóa toàn bộ dữ liệu cũ trong bảng
			for (CustomerDTO cus : list) {
				model.addRow(new Object[]{
					cus.getMaKH(),
                    cus.getTenKH(),
                    cus.getSDT(),
                    cus.getEmail(),
                    cus.getGioiTinh(),
                    cus.getNgaySinh()
				});
			}
	}

    private void clickBtnXoa() {
        btnXoa.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                // Hiển thị hộp thoại xác nhận và lưu kết quả
                int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "Bạn có chắc chắn muốn xóa không?",
                    "Xóa khách hàng",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
    
                // Kiểm tra nếu người dùng chọn "Yes"
                if (confirm == JOptionPane.YES_OPTION) {
                    String maKH = table.getValueAt(row, 0).toString();
                    customerBLL.deleteKH(maKH);
                    refreshTable();
                    JOptionPane.showMessageDialog(view, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một dòng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void clickBtnSua() {
        btnSua.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String maKH = table.getValueAt(row, 0).toString();
                String tenKH = view.getTFTenKH().getText();
                String sdt = view.getTFSDT().getText();
                String email = view.getTFEmail().getText();
                String gioiTinh = view.getRdoNam().isSelected() ? "Nam" : "Nu";
                String ngaySinh = view.getTFNgaySinh().getText();

                CustomerDTO customer = new CustomerDTO(maKH, tenKH, sdt, email, gioiTinh, ngaySinh);
                customerBLL.updateKH(customer);
                refreshTable();
                JOptionPane.showMessageDialog(view, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một dòng để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void clickBtnSearch() {
        btnSearch.addActionListener(e -> {
            String selectedItem = (String) view.getCboTimKiem().getSelectedItem();
            String searchText = view.getTxtTimKiem().getText();
        
            ArrayList<CustomerDTO> listCus = customerBLL.searchKH(selectedItem, searchText);
            loadDataModel(listCus);
        });
    }

    private void btnClickExportExcel() {
		btnXuatExcel.addActionListener(e -> {
			try {
				XuLyFileExcel.xuatExcel(table);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(view, "Xuất file thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

    private void btnClickImportExcel() {
    btnNhapExcel.addActionListener(e -> {
        // Sử dụng SwingWorker để thực hiện tác vụ nặng
        SwingWorker<DefaultTableModel, Void> worker = new SwingWorker<>() {
            @Override
            protected DefaultTableModel doInBackground() throws Exception {
                // Tác vụ nặng: Đọc file Excel và xử lý dữ liệu
                DefaultTableModel modelEx = new DefaultTableModel();
                System.out.println("Bắt đầu đọc file Excel...");
                XuLyFileExcel.nhapExcel(modelEx); // Đọc dữ liệu từ file Excel
                System.out.println("Đọc file Excel thành công!");

                // Đặt tiêu đề cột
                String[] columnNames = {"Mã KH", "Tên KH", "SĐT", "Email", "Giới tính", "Ngày sinh"};
                modelEx.setColumnIdentifiers(columnNames);

                System.out.println("Hoàn thành xử lý dữ liệu từ file Excel!");
                return modelEx;
            }

            @Override
            protected void done() {
                try {
                    // Lấy kết quả từ doInBackground()
                    DefaultTableModel modelEx = get();

                    // Hiển thị dialog trên EDT
                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
                    ImportQLKHDialog importQLKHDialog = new ImportQLKHDialog(parentFrame, modelEx);

                    // Khởi tạo controller cho dialog và truyền chính class này vào
                    ImportQLKHController importController = new ImportQLKHController(importQLKHDialog, modelEx, CustomerController.this);

                    importQLKHDialog.setVisible(true);

                    System.out.println("Hiển thị dialog thành công!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Nhập file thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        };

        // Thực thi SwingWorker
        worker.execute();
    });
}

}
