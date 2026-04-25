package Model.Entity;

import java.sql.Date;
import java.sql.Time;

/**
 * Entity: HenKham
 * Lưu lịch hẹn khám lại cho bệnh nhân
 * @author giang
 */
public class HenKham {
    private int id_hen;
    private Integer id_ba; // có thể null
    private int id_bn;
    private Integer id_bs; // có thể null
    private Date ngayhen;
    private Time giohen;
    private String ghichu;
    private String trangthai;
    private Date created_at;

    public HenKham() {
    }

    public HenKham(int id_hen, Integer id_ba, int id_bn, Integer id_bs, Date ngayhen, Time giohen, String ghichu, String trangthai, Date created_at) {
        this.id_hen = id_hen;
        this.id_ba = id_ba;
        this.id_bn = id_bn;
        this.id_bs = id_bs;
        this.ngayhen = ngayhen;
        this.giohen = giohen;
        this.ghichu = ghichu;
        this.trangthai = trangthai;
        this.created_at = created_at;
    }

    public int getId_hen() {
        return id_hen;
    }

    public void setId_hen(int id_hen) {
        this.id_hen = id_hen;
    }

    public Integer getId_ba() {
        return id_ba;
    }

    public void setId_ba(Integer id_ba) {
        this.id_ba = id_ba;
    }

    public int getId_bn() {
        return id_bn;
    }

    public void setId_bn(int id_bn) {
        this.id_bn = id_bn;
    }

    public Integer getId_bs() {
        return id_bs;
    }

    public void setId_bs(Integer id_bs) {
        this.id_bs = id_bs;
    }

    public Date getNgayhen() {
        return ngayhen;
    }

    public void setNgayhen(Date ngayhen) {
        this.ngayhen = ngayhen;
    }

    public Time getGiohen() {
        return giohen;
    }

    public void setGiohen(Time giohen) {
        this.giohen = giohen;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
