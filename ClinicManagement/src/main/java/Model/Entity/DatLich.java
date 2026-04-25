/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author giang
 */
public class DatLich {
    private int id_lich;
    private Integer id_bn;        // Có thể NULL
    private String hoten_nv;
    private String sdt_nv;
    private String gioitinh_nv;
    private Date ngaysinh_nv;
    private String ghichu;
    private int id_bs;
    private Date ngaykham;
    private Time giokham;
    private String trangthai;
    private Timestamp created_at;

    public DatLich() {}

    public DatLich(int id_lich, Integer id_bn, String hoten_nv, String sdt_nv, 
                   String gioitinh_nv, Date ngaysinh_nv, String ghichu, int id_bs,
                   Date ngaykham, Time giokham, String trangthai, Timestamp created_at) {
        this.id_lich = id_lich;
        this.id_bn = id_bn;
        this.hoten_nv = hoten_nv;
        this.sdt_nv = sdt_nv;
        this.gioitinh_nv = gioitinh_nv;
        this.ngaysinh_nv = ngaysinh_nv;
        this.ghichu = ghichu;
        this.id_bs = id_bs;
        this.ngaykham = ngaykham;
        this.giokham = giokham;
        this.trangthai = trangthai;
        this.created_at = created_at;
    }

    // ===== Getter & Setter =====
    public int getId_lich() { return id_lich; }
    public void setId_lich(int id_lich) { this.id_lich = id_lich; }

    public Integer getId_bn() { return id_bn; }
    public void setId_bn(Integer id_bn) { this.id_bn = id_bn; }

    public String getHoten_nv() { return hoten_nv; }
    public void setHoten_nv(String hoten_nv) { this.hoten_nv = hoten_nv; }

    public String getSdt_nv() { return sdt_nv; }
    public void setSdt_nv(String sdt_nv) { this.sdt_nv = sdt_nv; }

    public String getGioitinh_nv() { return gioitinh_nv; }
    public void setGioitinh_nv(String gioitinh_nv) { this.gioitinh_nv = gioitinh_nv; }

    public Date getNgaysinh_nv() { return ngaysinh_nv; }
    public void setNgaysinh_nv(Date ngaysinh_nv) { this.ngaysinh_nv = ngaysinh_nv; }

    public String getGhichu() { return ghichu; }
    public void setGhichu(String ghichu) { this.ghichu = ghichu; }

    public int getId_bs() { return id_bs; }
    public void setId_bs(int id_bs) { this.id_bs = id_bs; }

    public Date getNgaykham() { return ngaykham; }
    public void setNgaykham(Date ngaykham) { this.ngaykham = ngaykham; }

    public Time getGiokham() { return giokham; }
    public void setGiokham(Time giokham) { this.giokham = giokham; }

    public String getTrangthai() { return trangthai; }
    public void setTrangthai(String trangthai) { this.trangthai = trangthai; }

    public Timestamp getCreated_at() { return created_at; }
    public void setCreated_at(Timestamp created_at) { this.created_at = created_at; }
}
