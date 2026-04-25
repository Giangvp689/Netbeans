package Model.Entity;

import java.sql.Date;

/**
 * Entity: DonThuoc
 * Đại diện cho đơn thuốc được lập trong 1 lần khám (BenhAn)
 * @author giang
 */
public class DonThuoc {
    private int id_dt;
    private int id_ba;
    private Integer id_bs;
    private Integer id_nv_lap;
    private Date ngaylap;
    private String ghichu;

    public DonThuoc() {
    }

    public DonThuoc(int id_dt, int id_ba, Integer id_bs, Integer id_nv_lap, Date ngaylap, String ghichu) {
        this.id_dt = id_dt;
        this.id_ba = id_ba;
        this.id_bs = id_bs;
        this.id_nv_lap = id_nv_lap;
        this.ngaylap = ngaylap;
        this.ghichu = ghichu;
    }

    public int getId_dt() {
        return id_dt;
    }

    public void setId_dt(int id_dt) {
        this.id_dt = id_dt;
    }

    public int getId_ba() {
        return id_ba;
    }

    public void setId_ba(int id_ba) {
        this.id_ba = id_ba;
    }

    public Integer getId_bs() {
        return id_bs;
    }

    public void setId_bs(Integer id_bs) {
        this.id_bs = id_bs;
    }

    public Integer getId_nv_lap() {
        return id_nv_lap;
    }

    public void setId_nv_lap(Integer id_nv_lap) {
        this.id_nv_lap = id_nv_lap;
    }

    public Date getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Date ngaylap) {
        this.ngaylap = ngaylap;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
