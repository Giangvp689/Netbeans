/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entity;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author giang
 */
public class LichLamViec {
    private int id_llv;
    private int id_bs;
    private Date ngaybatdau;
    private Date ngayketthuc;
    private String thu;
    private Time giobatdau;
    private Time gioketthuc;
    private String ghichu;

    public LichLamViec() {}

    public LichLamViec(int id_llv, int id_bs, Date ngaybatdau, Date ngayketthuc, 
                       String thu, Time giobatdau, Time gioketthuc, String ghichu) {
        this.id_llv = id_llv;
        this.id_bs = id_bs;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
        this.thu = thu;
        this.giobatdau = giobatdau;
        this.gioketthuc = gioketthuc;
        this.ghichu = ghichu;
    }

    // Getter & Setter
    public int getId_llv() { return id_llv; }
    public void setId_llv(int id_llv) { this.id_llv = id_llv; }

    public int getId_bs() { return id_bs; }
    public void setId_bs(int id_bs) { this.id_bs = id_bs; }

    public Date getNgaybatdau() { return ngaybatdau; }
    public void setNgaybatdau(Date ngaybatdau) { this.ngaybatdau = ngaybatdau; }

    public Date getNgayketthuc() { return ngayketthuc; }
    public void setNgayketthuc(Date ngayketthuc) { this.ngayketthuc = ngayketthuc; }

    public String getThu() { return thu; }
    public void setThu(String thu) { this.thu = thu; }

    public Time getGiobatdau() { return giobatdau; }
    public void setGiobatdau(Time giobatdau) { this.giobatdau = giobatdau; }

    public Time getGioketthuc() { return gioketthuc; }
    public void setGioketthuc(Time gioketthuc) { this.gioketthuc = gioketthuc; }

    public String getGhichu() { return ghichu; }
    public void setGhichu(String ghichu) { this.ghichu = ghichu; }

    public void setGioVao(LocalTime gioBD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setGioRa(LocalTime gioKT) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}