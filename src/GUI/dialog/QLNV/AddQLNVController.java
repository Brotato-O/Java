package GUI.dialog.QLNV;

import GUI.controller.EmpController;

public class AddQLNVController {
    private EmpController mainController = null;

    // Phương thức để thiết lập EmpController
    public void setMainController(EmpController mainController) {
        this.mainController = mainController;
    }

    // Phương thức ví dụ để tương tác với EmpController
    public void notifyMainController() {
        if (mainController != null) {
            // Gọi phương thức refreshTable() từ EmpController
            mainController.refreshTable();
        }
    }
}
