/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entity;

import java.sql.Date;

/**
 *
 * @author giang
 */
public class NhanVien {
    private int id_nv;
    private String hoten;
    private Date ngaysinh;
    private String gioitinh;
    private String diachi;
    private String sdt;
    private String email;
    private String chucvu;
    private String trangthai;
    private String taikhoan;
    private String matkhau;

    public NhanVien() {}

    public NhanVien(int id_nv, String hoten, Date ngaysinh, String gioitinh, String diachi,
                    String sdt, String email, String chucvu, String trangthai, String taikhoan, String matkhau) {
        this.id_nv = id_nv;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.chucvu = chucvu;
        this.trangthai = trangthai;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
    }

    // Getter & Setter
    public int getId_nv() { return id_nv; }
    public void setId_nv(int id_nv) { this.id_nv = id_nv; }

    public String getHoten() { return hoten; }
    public void setHoten(String hoten) { this.hoten = hoten; }

    public Date getNgaysinh() { return ngaysinh; }
    public void setNgaysinh(Date ngaysinh) { this.ngaysinh = ngaysinh; }

    public String getGioitinh() { return gioitinh; }
    public void setGioitinh(String gioitinh) { this.gioitinh = gioitinh; }

    public String getDiachi() { return diachi; }
    public void setDiachi(String diachi) { this.diachi = diachi; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getChucvu() { return chucvu; }
    public void setChucvu(String chucvu) { this.chucvu = chucvu; }

    public String getTrangthai() { return trangthai; }
    public void setTrangthai(String trangthai) { this.trangthai = trangthai; }

    public String getTaikhoan() { return taikhoan; }
    public void setTaikhoan(String taikhoan) { this.taikhoan = taikhoan; }

    public String getMatkhau() { return matkhau; }
    public void setMatkhau(String matkhau) { this.matkhau = matkhau; }
}
