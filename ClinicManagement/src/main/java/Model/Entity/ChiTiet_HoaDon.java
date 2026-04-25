package Model.Entity;

import java.math.BigDecimal;

/**
 * Entity: ChiTiet_HoaDon
 * 
 * Dòng chi tiết trong hóa đơn — có thể là thuốc hoặc dịch vụ.
 * 
 * @author giang
 */
public class ChiTiet_HoaDon {
    private int id_cthd;
    private int id_hd;
    private String loai_item;  // 'THUOC' hoặc 'DICHVU'
    private int id_ref;        // id_thuoc hoặc id_dv
    private int soluong;
    private BigDecimal dongia;
    private BigDecimal thanhtien;

    // Thông tin phụ (hiển thị)
    private String tenItem;

    public ChiTiet_HoaDon() {}

    public ChiTiet_HoaDon(int id_cthd, int id_hd, String loai_item, int id_ref,
                          int soluong, BigDecimal dongia, BigDecimal thanhtien) {
        this.id_cthd = id_cthd;
        this.id_hd = id_hd;
        this.loai_item = loai_item;
        this.id_ref = id_ref;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public int getId_cthd() {
        return id_cthd;
    }

    public void setId_cthd(int id_cthd) {
        this.id_cthd = id_cthd;
    }

    public int getId_hd() {
        return id_hd;
    }

    public void setId_hd(int id_hd) {
        this.id_hd = id_hd;
    }

    public String getLoai_item() {
        return loai_item;
    }

    public void setLoai_item(String loai_item) {
        this.loai_item = loai_item;
    }

    public int getId_ref() {
        return id_ref;
    }

    public void setId_ref(int id_ref) {
        this.id_ref = id_ref;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public BigDecimal getDongia() {
        return dongia;
    }

    public void setDongia(BigDecimal dongia) {
        this.dongia = dongia;
    }

    public BigDecimal getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(BigDecimal thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getTenItem() {
        return tenItem;
    }

    public void setTenItem(String tenItem) {
        this.tenItem = tenItem;
    }
}
