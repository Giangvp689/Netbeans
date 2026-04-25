package Model.Entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Entity: Thuoc
 * Đại diện cho thuốc trong kho nhà thuốc
 * @author giang
 */
public class Thuoc {
    private int id_thuoc;
    private String tenthuoc;
    private String loai;
    private int soluongton;
    private String donvitinh;
    private BigDecimal giaban;
    private Date hansudung;
    private String ghichu;

    public Thuoc() {
    }

    public Thuoc(int id_thuoc, String tenthuoc, String loai, int soluongton, 
                 String donvitinh, BigDecimal giaban, Date hansudung, String ghichu) {
        this.id_thuoc = id_thuoc;
        this.tenthuoc = tenthuoc;
        this.loai = loai;
        this.soluongton = soluongton;
        this.donvitinh = donvitinh;
        this.giaban = giaban;
        this.hansudung = hansudung;
        this.ghichu = ghichu;
    }

    public int getId_thuoc() {
        return id_thuoc;
    }

    public void setId_thuoc(int id_thuoc) {
        this.id_thuoc = id_thuoc;
    }


    public String getTenthuoc() {
        return tenthuoc;
    }

    public void setTenthuoc(String tenthuoc) {
        this.tenthuoc = tenthuoc;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public String getDonvitinh() {
        return donvitinh;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    public BigDecimal getGiaban() {
        return giaban;
    }

    public void setGiaban(BigDecimal giaban) {
        this.giaban = giaban;
    }

    public Date getHansudung() {
        return hansudung;
    }

    public void setHansudung(Date hansudung) {
        this.hansudung = hansudung;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
