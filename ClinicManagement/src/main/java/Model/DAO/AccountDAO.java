/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.DBConnection;
import Model.Entity.AccountEntity;
import View.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giang
 */
public class AccountDAO {
    public List<AccountEntity> getAll() {
    List<AccountEntity> list = new ArrayList<>();

    String sql = """
        SELECT 
            tk.id_tk,
            tk.username,
            tk.loai_tk,
            tk.id_ref,
            tk.trangthai,
            CASE 
                WHEN tk.loai_tk = 'NHANVIEN' THEN nv.hoten
                WHEN tk.loai_tk = 'BACSI' THEN bs.hoten
                ELSE NULL
            END AS ten_tham_chieu
        FROM TaiKhoan tk
        LEFT JOIN NhanVien nv ON tk.loai_tk = 'NHANVIEN' AND tk.id_ref = nv.id_nv
        LEFT JOIN BacSi bs ON tk.loai_tk = 'BACSI' AND tk.id_ref = bs.id_bs
    """;

    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            AccountEntity a = new AccountEntity();
            a.setIdTk(rs.getInt("id_tk"));
            a.setUsername(rs.getString("username"));
            a.setLoaiTk(rs.getString("loai_tk"));
            a.setIdRef(rs.getInt("id_ref"));
            a.setTrangThai(rs.getString("trangthai"));
            a.setTenThamChieu(rs.getString("ten_tham_chieu")); // 🔥

            list.add(a);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    // ================== INSERT ==================
   public boolean insert(AccountEntity a) {
    String sql = """
        INSERT INTO TaiKhoan(username, password_hash, loai_tk, id_ref, trangthai)
        VALUES (?,?,?,?,N'Hoạt động')
    """;

    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, a.getUsername());
        ps.setString(2, a.getPasswordHash());
        ps.setString(3, a.getLoaiTk());

        if (a.getIdRef() == 0) {
            ps.setNull(4, java.sql.Types.INTEGER);
        } else {
            ps.setInt(4, a.getIdRef());
        }

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    // ================== UPDATE ==================
    public boolean update(AccountEntity a) {
        String sql = """
            UPDATE TaiKhoan
            SET password_hash = ?, loai_tk = ?, id_ref = ?, trangthai = ?
            WHERE id_tk = ?
        """;

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, a.getPasswordHash());
            ps.setString(2, a.getLoaiTk());
            ps.setInt(3, a.getIdRef());
            ps.setString(4, a.getTrangThai());
            ps.setInt(5, a.getIdTk());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================== DELETE ==================
    public boolean delete(int idTk) {
        String sql = "DELETE FROM TaiKhoan WHERE id_tk = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idTk);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // ================== SEARCH (REALTIME) ==================
public List<AccountEntity> search(String keyword) {
    List<AccountEntity> list = new ArrayList<>();
     String sql = """
        SELECT 
            tk.id_tk,
            tk.username,
            tk.loai_tk,
            tk.id_ref,
            tk.trangthai,
            CASE 
                WHEN tk.loai_tk = 'NHANVIEN' THEN nv.hoten
                WHEN tk.loai_tk = 'BACSI' THEN bs.hoten
                ELSE NULL
            END AS ten_tham_chieu
        FROM TaiKhoan tk
        LEFT JOIN NhanVien nv ON tk.loai_tk = 'NHANVIEN' AND tk.id_ref = nv.id_nv
        LEFT JOIN BacSi bs ON tk.loai_tk = 'BACSI' AND tk.id_ref = bs.id_bs
        WHERE tk.username LIKE ? OR nv.hoten LIKE ? OR bs.hoten LIKE ?
    """;

    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        ps.setString(3, "%" + keyword + "%");


        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            AccountEntity a = new AccountEntity();
            a.setIdTk(rs.getInt("id_tk"));
            a.setUsername(rs.getString("username"));
            a.setPasswordHash(rs.getString("password_hash"));
            a.setLoaiTk(rs.getString("loai_tk"));
            a.setIdRef(rs.getInt("id_ref"));
            a.setTrangThai(rs.getString("trangthai"));
            list.add(a);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
