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
public class BenhNhan {
    private int id_bn;
    private String hoten;
    private String gioitinh;
    private Date ngaysinh;
    private String sdt;
    private String diachi;
    private String nghenghiep;
    private String dantoc;
    private String nguoithan;
    private String ghichu;
    private Date ngaydangky;

    // Constructor rỗng
    public BenhNhan() {}

    // Constructor đầy đủ
    public BenhNhan(int id_bn, String hoten, String gioitinh, Date ngaysinh,
                    String sdt, String diachi, String nghenghiep, String dantoc,
                    String nguoithan, String ghichu, Date ngaydangky) {
        this.id_bn = id_bn;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.diachi = diachi;
        this.nghenghiep = nghenghiep;
        this.dantoc = dantoc;
        this.nguoithan = nguoithan;
        this.ghichu = ghichu;
        this.ngaydangky = ngaydangky;
    }
    @Override
public String toString() {
    return this.getHoten(); // hiển thị tên
}


    // Getter và Setter
    public int getId_bn() { return id_bn; }
    public void setId_bn(int id_bn) { this.id_bn = id_bn; }

    public String getHoten() { return hoten; }
    public void setHoten(String hoten) { this.hoten = hoten; }

    public String getGioitinh() { return gioitinh; }
    public void setGioitinh(String gioitinh) { this.gioitinh = gioitinh; }

    public Date getNgaysinh() { return ngaysinh; }
    public void setNgaysinh(Date ngaysinh) { this.ngaysinh = ngaysinh; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getDiachi() { return diachi; }
    public void setDiachi(String diachi) { this.diachi = diachi; }

    public String getNghenghiep() { return nghenghiep; }
    public void setNghenghiep(String nghenghiep) { this.nghenghiep = nghenghiep; }

    public String getDantoc() { return dantoc; }
    public void setDantoc(String dantoc) { this.dantoc = dantoc; }

    public String getNguoithan() { return nguoithan; }
    public void setNguoithan(String nguoithan) { this.nguoithan = nguoithan; }

    public String getGhichu() { return ghichu; }
    public void setGhichu(String ghichu) { this.ghichu = ghichu; }

    public Date getNgaydangky() { return ngaydangky; }
    public void setNgaydangky(Date ngaydangky) { this.ngaydangky = ngaydangky; }
}