/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.Entity.LichLamViec;
import Model.DBConnection;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 *
 * @author giang
 */
public class LichLamViecDAO {

    // 1️⃣ Lấy tất cả lịch làm việc
    public List<LichLamViec> getAllLichLamViec() {
        List<LichLamViec> list = new ArrayList<>();
        String sql = "SELECT * FROM LichLamViec ORDER BY id_llv";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LichLamViec llv = new LichLamViec();
                llv.setId_llv(rs.getInt("id_llv"));
                llv.setId_bs(rs.getInt("id_bs"));
                llv.setNgaybatdau(rs.getDate("ngaybatdau"));
                llv.setNgayketthuc(rs.getDate("ngayketthuc"));
                llv.setThu(rs.getString("thu"));
                llv.setGiobatdau(rs.getTime("giobatdau"));
                llv.setGioketthuc(rs.getTime("gioketthuc"));
                llv.setGhichu(rs.getString("ghichu"));
                list.add(llv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2️⃣ Thêm lịch làm việc mới
    public boolean insertLichLamViec(LichLamViec llv) {
        String sql = "INSERT INTO LichLamViec(id_bs, ngaybatdau, ngayketthuc, thu, giobatdau, gioketthuc, ghichu) "
                   + "VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, llv.getId_bs());
            ps.setDate(2, llv.getNgaybatdau());
            ps.setDate(3, llv.getNgayketthuc());
            ps.setString(4, llv.getThu());
            ps.setTime(5, llv.getGiobatdau());
            ps.setTime(6, llv.getGioketthuc());
            ps.setString(7, llv.getGhichu());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3️⃣ Cập nhật lịch làm việc
    public boolean updateLichLamViec(LichLamViec llv) {
        String sql = "UPDATE LichLamViec SET id_bs=?, ngaybatdau=?, ngayketthuc=?, thu=?, giobatdau=?, gioketthuc=?, ghichu=? "
                   + "WHERE id_llv=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, llv.getId_bs());
            ps.setDate(2, llv.getNgaybatdau());
            ps.setDate(3, llv.getNgayketthuc());
            ps.setString(4, llv.getThu());
            ps.setTime(5, llv.getGiobatdau());
            ps.setTime(6, llv.getGioketthuc());
            ps.setString(7, llv.getGhichu());
            ps.setInt(8, llv.getId_llv());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Xóa lịch làm việc theo id
    public boolean deleteLichLamViec(int id_llv) {
        String sql = "DELETE FROM LichLamViec WHERE id_llv=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_llv);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Tìm kiếm lịch làm việc theo tên thứ hoặc ghi chú
    public List<LichLamViec> searchLichLamViec(String keyword) {
        List<LichLamViec> list = new ArrayList<>();
        String sql = "SELECT * FROM LichLamViec WHERE thu LIKE ? OR ghichu LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichLamViec llv = new LichLamViec();
                llv.setId_llv(rs.getInt("id_llv"));
                llv.setId_bs(rs.getInt("id_bs"));
                llv.setNgaybatdau(rs.getDate("ngaybatdau"));
                llv.setNgayketthuc(rs.getDate("ngayketthuc"));
                llv.setThu(rs.getString("thu"));
                llv.setGiobatdau(rs.getTime("giobatdau"));
                llv.setGioketthuc(rs.getTime("gioketthuc"));
                llv.setGhichu(rs.getString("ghichu"));
                list.add(llv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6️⃣ Lấy lịch làm việc theo id bác sĩ
    public List<LichLamViec> getLichLamViecByBacSi(int id_bs) {
        List<LichLamViec> list = new ArrayList<>();
        String sql = "SELECT * FROM LichLamViec WHERE id_bs=? ORDER BY ngaybatdau";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_bs);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LichLamViec llv = new LichLamViec();
                llv.setId_llv(rs.getInt("id_llv"));
                llv.setId_bs(rs.getInt("id_bs"));
                llv.setNgaybatdau(rs.getDate("ngaybatdau"));
                llv.setNgayketthuc(rs.getDate("ngayketthuc"));
                llv.setThu(rs.getString("thu"));
                llv.setGiobatdau(rs.getTime("giobatdau"));
                llv.setGioketthuc(rs.getTime("gioketthuc"));
                llv.setGhichu(rs.getString("ghichu"));
                list.add(llv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
        // Xóa lịch làm việc theo id bác sĩ
    public boolean deleteLichByBacSi(int id_bs) {
        String sql = "DELETE FROM LichLamViec WHERE id_bs = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_bs);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public List<Object[]> getLichLamViec(int idBs, java.util.Date ngay) {
        List<Object[]> list = new ArrayList<>();

        String sql = """
            SELECT 
                bs.hoten,
                llv.thu,
                llv.giobatdau,
                llv.gioketthuc,
                llv.ghichu
            FROM LichLamViec llv
            JOIN BacSi bs ON llv.id_bs = bs.id_bs
            WHERE llv.id_bs = ?
              AND ? BETWEEN llv.ngaybatdau AND llv.ngayketthuc
              AND llv.thu LIKE ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idBs);
            ps.setDate(2, new java.sql.Date(ngay.getTime()));
            ps.setString(3, "%" + getThuTrongTuan(ngay) + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("hoten"),
                    rs.getString("thu"),
                    rs.getTime("giobatdau"),
                    rs.getTime("gioketthuc"),
                    rs.getString("ghichu")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ===== HÀM PHỤ: TÍNH THỨ =====
    private String getThuTrongTuan(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY: return "Thứ 2";
            case Calendar.TUESDAY: return "Thứ 3";
            case Calendar.WEDNESDAY: return "Thứ 4";
            case Calendar.THURSDAY: return "Thứ 5";
            case Calendar.FRIDAY: return "Thứ 6";
            case Calendar.SATURDAY: return "Thứ 7";
            case Calendar.SUNDAY: return "Chủ nhật";
        }
        return "";
    }
    
    public List<LichLamViec> findByBacSiAndNgay(int idBs, java.sql.Date ngay) {

    List<LichLamViec> list = new ArrayList<>();

    String sql = """
        SELECT *
        FROM LichLamViec
        WHERE id_bs = ?
          AND ? BETWEEN ngaybatdau AND ngayketthuc
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idBs);
        ps.setDate(2, ngay);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            LichLamViec llv = new LichLamViec();
            llv.setId_llv(rs.getInt("id_llv"));
            llv.setThu(rs.getString("thu"));
            llv.setGiobatdau(rs.getTime("giobatdau"));
            llv.setGioketthuc(rs.getTime("gioketthuc"));
            llv.setGhichu(rs.getString("ghichu"));
            list.add(llv);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


}
