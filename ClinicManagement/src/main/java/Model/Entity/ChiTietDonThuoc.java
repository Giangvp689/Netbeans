package Model.Entity;

import java.math.BigDecimal;

/**
 * Entity: ChiTietDonThuoc
 * Mỗi bản ghi đại diện cho 1 loại thuốc trong đơn thuốc
 * @author giang
 */
public class ChiTietDonThuoc {
   private int id_ctdt;
    private int id_dt;
    private int id_thuoc;

    // ❗ không phải cột DB – chỉ dùng hiển thị
    private String tenThuoc;

    private int soluong;
    private String lieudung;
    private BigDecimal dongia;

    // ===== getter / setter =====
    public int getId_ctdt() { return id_ctdt; }
    public void setId_ctdt(int id_ctdt) { this.id_ctdt = id_ctdt; }

    public int getId_dt() { return id_dt; }
    public void setId_dt(int id_dt) { this.id_dt = id_dt; }

    public int getId_thuoc() { return id_thuoc; }
    public void setId_thuoc(int id_thuoc) { this.id_thuoc = id_thuoc; }

    public String getTenThuoc() { return tenThuoc; }
    public void setTenThuoc(String tenThuoc) { this.tenThuoc = tenThuoc; }

    public int getSoluong() { return soluong; }
    public void setSoluong(int soluong) { this.soluong = soluong; }

    public String getLieudung() { return lieudung; }
    public void setLieudung(String lieudung) { this.lieudung = lieudung; }

    public BigDecimal getDongia() { return dongia; }
    public void setDongia(BigDecimal dongia) { this.dongia = dongia; }

    // ✅ Tổng tiền của dòng chi tiết
    public BigDecimal getThanhtien() {
        if (dongia == null) return BigDecimal.ZERO;
        return dongia.multiply(BigDecimal.valueOf(soluong));
    }
}
