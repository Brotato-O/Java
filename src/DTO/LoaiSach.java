package DTO;

public class LoaiSach {
    private String maLoai;
    private String tenLoai;
    private int doTuoi;
    public LoaiSach() {
    }

    // Constructor đầy đủ tham số
    public LoaiSach(String maLoai, String tenLoai, int doTuoi) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.doTuoi = doTuoi;
    }

    // Getter và Setter cho maLoai
    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    // Getter và Setter cho tenLoai
    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    // Getter và Setter cho doTuoi
    public int getDoTuoi() {
        return doTuoi;
    }

    public void setDoTuoi(int doTuoi) {
        this.doTuoi = doTuoi;
    }
}
