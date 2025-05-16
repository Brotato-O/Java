import BLL.EmpBLL;

public class CheckEmployee {
    public static void main(String[] args) {
        EmpBLL empBLL = new EmpBLL();
        String maNV = "NV021";
        int status = empBLL.getEmployeeStatus(maNV);
        
        System.out.println("Kiểm tra nhân viên " + maNV + ":");
        if (status == -1) {
            System.out.println("Không tìm thấy nhân viên");
        } else {
            System.out.println("STATUS = " + status);
            System.out.println("Giải thích: ");
            if (status == 0) {
                System.out.println("Nhân viên đang hoạt động (sẽ hiển thị trong bảng)");
            } else if (status == 1) {
                System.out.println("Nhân viên đang bị khóa (không hiển thị trong bảng)");
            } else if (status == 2) {
                System.out.println("Nhân viên đã bị xóa (không hiển thị trong bảng)");
            }
        }
    }
} 