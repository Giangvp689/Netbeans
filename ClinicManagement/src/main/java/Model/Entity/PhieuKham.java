/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entity;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author giang
 */
public class PhieuKham {
    private int id_pk;
    private Integer id_lich;
    private Integer id_bn;
    private String hoten_nv;
    private String sdt_nv;
    private Integer id_bs;
    private String loaikham;
    private String phongkham;
    private Integer id_nv_don;
    private Timestamp ngaykhamban;
    private Time giokham;
    private String trangthai;
    private String ghichu;

    public PhieuKham() {}

    public PhieuKham(int id_pk, Integer id_lich, Integer id_bn, String hoten_nv, String sdt_nv,
                     Integer id_bs, String loaikham, String phongkham, Integer id_nv_don,
                     Timestamp ngaykhamban, Time giokham, String trangthai, String ghichu) {
        this.id_pk = id_pk;

        this.id_lich = id_lich;
        this.id_bn = id_bn;
        this.hoten_nv = hoten_nv;
        this.sdt_nv = sdt_nv;
        this.id_bs = id_bs;
        this.loaikham = loaikham;
        this.phongkham = phongkham;
        this.id_nv_don = id_nv_don;
        this.ngaykhamban = ngaykhamban;
        this.giokham = giokham;
        this.trangthai = trangthai;
        this.ghichu = ghichu;
    }

    // ===== Getter & Setter =====
    public int getId_pk() { return id_pk; }
    public void setId_pk(int id_pk) { this.id_pk = id_pk; }


    public Integer getId_lich() { return id_lich; }
    public void setId_lich(Integer id_lich) { this.id_lich = id_lich; }

    public Integer getId_bn() { return id_bn; }
    public void setId_bn(Integer id_bn) { this.id_bn = id_bn; }

    public String getHoten_nv() { return hoten_nv; }
    public void setHoten_nv(String hoten_nv) { this.hoten_nv = hoten_nv; }

    public String getSdt_nv() { return sdt_nv; }
    public void setSdt_nv(String sdt_nv) { this.sdt_nv = sdt_nv; }

    public Integer getId_bs() { return id_bs; }
    public void setId_bs(Integer id_bs) { this.id_bs = id_bs; }

    public String getLoaikham() { return loaikham; }
    public void setLoaikham(String loaikham) { this.loaikham = loaikham; }

    public String getPhongkham() { return phongkham; }
    public void setPhongkham(String phongkham) { this.phongkham = phongkham; }

    public Integer getId_nv_don() { return id_nv_don; }
    public void setId_nv_don(Integer id_nv_don) { this.id_nv_don = id_nv_don; }

    public Timestamp getNgaykhamban() { return ngaykhamban; }
    public void setNgaykhamban(Timestamp ngaykhamban) { this.ngaykhamban = ngaykhamban; }

    public Time getGiokham() { return giokham; }
    public void setGiokham(Time giokham) { this.giokham = giokham; }

    public String getTrangthai() { return trangthai; }
    public void setTrangthai(String trangthai) { this.trangthai = trangthai; }

    public String getGhichu() { return ghichu; }
    public void setGhichu(String ghichu) { this.ghichu = ghichu; }
}
