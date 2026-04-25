/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entity;

/**
 *
 * @author giang
 */
import java.util.Date;

public class BacSi {
    private int id_bs;
    private String hoten;
    private String chuyenmon;
    private String gioitinh;
    private Date ngaysinh;
    private String sdt;
    private String email;
    private String diachi;
    private String ghichu;
    private String trangthai;

    public BacSi() {}
    
    
    @Override
    public String toString() {
        return this.hoten;
    }


    // getter & setter
    public int getId_bs() { return id_bs; }
    public void setId_bs(int id_bs) { this.id_bs = id_bs; }

    public String getHoten() { return hoten; }
    public void setHoten(String hoten) { this.hoten = hoten; }

    public String getChuyenmon() { return chuyenmon; }
    public void setChuyenmon(String chuyenmon) { this.chuyenmon = chuyenmon; }

    public String getGioitinh() { return gioitinh; }
    public void setGioitinh(String gioitinh) { this.gioitinh = gioitinh; }

    public Date getNgaysinh() { return ngaysinh; }
    public void setNgaysinh(Date ngaysinh) { this.ngaysinh = ngaysinh; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDiachi() { return diachi; }
    public void setDiachi(String diachi) { this.diachi = diachi; }

    public String getGhichu() { return ghichu; }
    public void setGhichu(String ghichu) { this.ghichu = ghichu; }

    public String getTrangthai() { return trangthai; }
    public void setTrangthai(String trangthai) { this.trangthai = trangthai; }

    
}


