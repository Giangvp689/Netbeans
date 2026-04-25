package Model.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Entity: HoaDon
 * Đại diện cho hóa đơn thanh toán của 1 phiên khám (BenhAn)
 * Có thể bao gồm cả chi phí thuốc và dịch vụ.
 * 
 * @author giang
 */
public class HoaDon {
    private int id_hd;
    private String mahd;
    private Integer id_ba;
    private Integer id_bn;
    private Integer id_nv_lap;
    private Timestamp ngaylap;
    private BigDecimal tongtien;
    private String trangthai;
    private String ghichu;

    // Thông tin phụ hiển thị (không bắt buộc lưu DB)
    private String tenBenhNhan;
    private String tenNhanVien;

    public HoaDon() {}

    public HoaDon(int id_hd, String mahd, Integer id_ba, Integer id_bn, Integer id_nv_lap, 
                  Timestamp ngaylap, BigDecimal tongtien, String trangthai, String ghichu) {
        this.id_hd = id_hd;
        this.mahd = mahd;
        this.id_ba = id_ba;
        this.id_bn = id_bn;
        this.id_nv_lap = id_nv_lap;
        this.ngaylap = ngaylap;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
    }

    public int getId_hd() {
        return id_hd;
    }

    public void setId_hd(int id_hd) {
        this.id_hd = id_hd;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public Integer getId_ba() {
        return id_ba;
    }

    public void setId_ba(Integer id_ba) {
        this.id_ba = id_ba;
    }

    public Integer getId_bn() {
        return id_bn;
    }

    public void setId_bn(Integer id_bn) {
        this.id_bn = id_bn;
    }

    public Integer getId_nv_lap() {
        return id_nv_lap;
    }

    public void setId_nv_lap(Integer id_nv_lap) {
        this.id_nv_lap = id_nv_lap;
    }

    public Timestamp getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Timestamp ngaylap) {
        this.ngaylap = ngaylap;
    }

    public BigDecimal getTongtien() {
        return tongtien;
    }

    public void setTongtien(BigDecimal tongtien) {
        this.tongtien = tongtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }
}
