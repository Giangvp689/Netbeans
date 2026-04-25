/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entity;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author giang
 */
public class AccountEntity {
    private int idTk;
    private String username;
    private String passwordHash;
    private String loaiTk;
    private int idRef;
    private String trangThai;
    
      // ===== THÊM =====
    private String tenThamChieu;

    public String getTenThamChieu() {
        return tenThamChieu;
    }

    public void setTenThamChieu(String tenThamChieu) {
        this.tenThamChieu = tenThamChieu;
    }



    public int getIdTk() {
        return idTk;
    }

    public void setIdTk(int idTk) {
        this.idTk = idTk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getLoaiTk() {
        return loaiTk;
    }

    public void setLoaiTk(String loaiTk) {
        this.loaiTk = loaiTk;
    }

    public int getIdRef() {
        return idRef;
    }

    public void setIdRef(int idRef) {
        this.idRef = idRef;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }


   
    
}
