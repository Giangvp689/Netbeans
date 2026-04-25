/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.Entity.BacSi;
import Model.DBConnection;
import java.sql.*;
import java.util.*;

public class BacSiDAO {

    //Lấy tất cả bác sĩ
    public List<BacSi> getAllBacSi() {
    List<BacSi> list = new ArrayList<>();
    String sql = "SELECT * FROM BacSi";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            BacSi bs = new BacSi();
            bs.setId_bs(rs.getInt("id_bs"));
            bs.setHoten(rs.getString("hoten"));
            bs.setChuyenmon(rs.getString("chuyenmon"));
            bs.setGioitinh(rs.getString("gioitinh"));
            bs.setNgaysinh(rs.getDate("ngaysinh"));
            bs.setSdt(rs.getString("sdt"));
            bs.setEmail(rs.getString("email"));
            bs.setDiachi(rs.getString("diachi"));
            bs.setGhichu(rs.getString("ghichu"));
            bs.setTrangthai(rs.getString("trangthai"));
            list.add(bs);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // Thêm mới bác sĩ
    public boolean insertBacSi(BacSi bs) {
    String sqlBacSi = "INSERT INTO BacSi(hoten, chuyenmon, gioitinh, ngaysinh, sdt, email, diachi, ghichu, trangthai) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)";
    String sqlLichLamViec = "INSERT INTO LichLamViec(id_bs, ngaybatdau, ngayketthuc, thu, giobatdau, gioketthuc, ghichu) "
                          + "VALUES(?,?,?,?,?,?,?)";

    try (Connection conn = DBConnection.getConnection()) {
        // Bắt đầu transaction
        conn.setAutoCommit(false);

        // Thêm bác sĩ
        try (PreparedStatement ps1 = conn.prepareStatement(sqlBacSi, Statement.RETURN_GENERATED_KEYS)) {
            ps1.setString(1, bs.getHoten());
            ps1.setString(2, bs.getChuyenmon());
            ps1.setString(3, bs.getGioitinh());
            if (bs.getNgaysinh() != null) {
                ps1.setDate(4, new java.sql.Date(bs.getNgaysinh().getTime()));
            } else {
                ps1.setDate(4, null);
            }
            ps1.setString(5, bs.getSdt());
            ps1.setString(6, bs.getEmail());
            ps1.setString(7, bs.getDiachi());
            ps1.setString(8, bs.getGhichu());
            ps1.setString(9, bs.getTrangthai());

            int affected = ps1.executeUpdate();
            if (affected == 0) throw new SQLException("Thêm bác sĩ thất bại!");

            // Lấy id_bs vừa tạo
            ResultSet rs = ps1.getGeneratedKeys();
            int id_bs = 0;
            if (rs.next()) {
                id_bs = rs.getInt(1);
            } else {
                throw new SQLException("Không lấy được ID bác sĩ mới.");
            }

            // Thêm lịch làm việc mặc định (ví dụ)
            try (PreparedStatement ps2 = conn.prepareStatement(sqlLichLamViec)) {
                ps2.setInt(1, id_bs);
                ps2.setDate(2, new java.sql.Date(System.currentTimeMillis())); // ngày bắt đầu
                ps2.setDate(3, new java.sql.Date(System.currentTimeMillis())); // ngày kết thúc
                ps2.setString(4, "Thứ 2;Thứ 3;Thứ 4;Thứ 5;Thứ 6;Thứ 7;Chủ nhật"); // thu
                ps2.setTime(5, java.sql.Time.valueOf("08:00:00")); // giờ bắt đầu
                ps2.setTime(6, java.sql.Time.valueOf("17:00:00")); // giờ kết thúc
                ps2.setString(7, "Lịch mặc định");

                ps2.executeUpdate();
            }

            // Commit nếu cả 2 thành công
            conn.commit();
            return true;
        } catch (Exception e) {
            conn.rollback(); // rollback nếu có lỗi
            e.printStackTrace();
            return false;
        } finally {
            conn.setAutoCommit(true);
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


    //  Cập nhật bác sĩ
    public boolean updateBacSi(BacSi bs) {
        String sql = "UPDATE BacSi SET hoten=?, chuyenmon=?, gioitinh=?, ngaysinh=?, sdt=?, email=?, diachi=?, ghichu=?, trangthai=? "
                   + "WHERE id_bs=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bs.getHoten());
            ps.setString(2, bs.getChuyenmon());
            ps.setString(3, bs.getGioitinh());
            if (bs.getNgaysinh() != null) {
                ps.setDate(4, new java.sql.Date(bs.getNgaysinh().getTime()));
            } else {
                ps.setDate(4, null);
            }
            ps.setString(5, bs.getSdt());
            ps.setString(6, bs.getEmail());
            ps.setString(7, bs.getDiachi());
            ps.setString(8, bs.getGhichu());
            ps.setString(9, bs.getTrangthai());
            ps.setInt(10, bs.getId_bs());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //  Xóa bác sĩ theo id
    public boolean deleteBacSi(int id_bs) {
        String sql = "DELETE FROM BacSi WHERE id_bs=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_bs);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm bác sĩ theo tên, sdt, chuyên môn
    public List<BacSi> searchBacSi(String keyword) {
        List<BacSi> list = new ArrayList<>();
        String sql = "SELECT * FROM BacSi WHERE hoten LIKE ? OR sdt LIKE ? OR chuyenmon LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BacSi bs = new BacSi();
                bs.setId_bs(rs.getInt("id_bs"));
                bs.setHoten(rs.getString("hoten"));
                bs.setChuyenmon(rs.getString("chuyenmon"));
                bs.setGioitinh(rs.getString("gioitinh"));
                bs.setNgaysinh(rs.getDate("ngaysinh"));
                bs.setSdt(rs.getString("sdt"));
                bs.setEmail(rs.getString("email"));
                bs.setDiachi(rs.getString("diachi"));
                bs.setGhichu(rs.getString("ghichu"));
                bs.setTrangthai(rs.getString("trangthai"));
                list.add(bs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    public int insertBacSiReturnID(BacSi bs) {
    String sql = "INSERT INTO BacSi(hoten, chuyenmon, gioitinh, ngaysinh, sdt, email, diachi, ghichu, trangthai) "
               + "VALUES(?,?,?,?,?,?,?,?,?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, bs.getHoten());
        ps.setString(2, bs.getChuyenmon());
        ps.setString(3, bs.getGioitinh());
        if (bs.getNgaysinh() != null) {
            ps.setDate(4, new java.sql.Date(bs.getNgaysinh().getTime()));
        } else {
            ps.setDate(4, null);
        }
        ps.setString(5, bs.getSdt());
        ps.setString(6, bs.getEmail());
        ps.setString(7, bs.getDiachi());
        ps.setString(8, bs.getGhichu());
        ps.setString(9, bs.getTrangthai());

        int affected = ps.executeUpdate();
        if (affected == 0) return -1;

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return -1;
}
    
    public BacSi getBacSiById(int id) {
    String sql = "SELECT * FROM BacSi WHERE id_bs = ?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            BacSi bs = new BacSi();
            bs.setId_bs(rs.getInt("id_bs"));
            bs.setHoten(rs.getString("hoten"));
            bs.setChuyenmon(rs.getString("chuyenmon"));
            bs.setTrangthai(rs.getString("trangthai"));
            return bs;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    public BacSi getBacSiByTen(String ten) {
    String sql = "SELECT * FROM BacSi WHERE hoten = ?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, ten);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            BacSi bs = new BacSi();
            bs.setId_bs(rs.getInt("id_bs"));
            bs.setHoten(rs.getString("hoten"));
            bs.setChuyenmon(rs.getString("chuyenmon"));
            bs.setTrangthai(rs.getString("trangthai"));
            return bs;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}


