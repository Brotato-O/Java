package DTO;

public class CTGG {
    private String maSach;
    private String maGG;
    public CTGG() {
    }

    // Constructor có tham số
    public CTGG(String maSach, String maGG) {
        this.maSach = maSach;
        this.maGG = maGG;
    }

    // Getter cho maSach
    public String getMaSach() {
        return maSach;
    }

    // Setter cho maSach
    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    // Getter cho maGG
    public String getMaGG() {
        return maGG;
    }

    // Setter cho maGG
    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }
    
}
