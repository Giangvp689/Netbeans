package Model.Entity;

import java.math.BigDecimal;

/**
 * Entity: ChiTiet_DichVu
 * Lưu thông tin dịch vụ được chỉ định trong 1 bệnh án
 * (liên kết giữa bảng BenhAn và DichVu)
 * @author giang
 */
public class ChiTiet_DichVu {
    private int id_ctdv;
    private int id_ba;
    private int id_dv;
    private int soluong;
    private double dongia;

    // Có thể thêm thông tin phụ để hiển thị (không bắt buộc)
    private String tenDichVu;

    public ChiTiet_DichVu() {}

    public ChiTiet_DichVu(int id_ctdv, int id_ba, int id_dv, int soluong, double dongia) {
        this.id_ctdv = id_ctdv;
        this.id_ba = id_ba;
        this.id_dv = id_dv;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getId_ctdv() {
        return id_ctdv;
    }

    public void setId_ctdv(int id_ctdv) {
        this.id_ctdv = id_ctdv;
    }

    public int getId_ba() {
        return id_ba;
    }

    public void setId_ba(int id_ba) {
        this.id_ba = id_ba;
    }

    public int getId_dv() {
        return id_dv;
    }

    public void setId_dv(int id_dv) {
        this.id_dv = id_dv;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }
}
