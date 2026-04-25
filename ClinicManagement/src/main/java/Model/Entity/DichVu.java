package Model.Entity;

import java.math.BigDecimal;

/**
 * Entity: DichVu
 * Lưu thông tin dịch vụ trong phòng khám
 * @author giang
 */
public class DichVu {
    private int id_dv;
    private String madv;
    private String tendv;
    private String loai;
    private double dongia;
    private String mota;
    private String trangthai;

    public DichVu() {}

    public DichVu(int id_dv, String madv, String tendv, String loai, double dongia, String mota, String trangthai) {
        this.id_dv = id_dv;
        this.madv = madv;
        this.tendv = tendv;
        this.loai = loai;
        this.dongia = dongia;
        this.mota = mota;
        this.trangthai = trangthai;
    }

    public int getId_dv() {
        return id_dv;
    }

    public void setId_dv(int id_dv) {
        this.id_dv = id_dv;
    }

    public String getMadv() {
        return madv;
    }

    public void setMadv(String madv) {
        this.madv = madv;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
