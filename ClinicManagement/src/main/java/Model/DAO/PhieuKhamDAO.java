/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.Entity.PhieuKham;
import Model.DBConnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author giang
 */
public class PhieuKhamDAO {

    // 1️⃣ Lấy tất cả phiếu khám
    public List<PhieuKham> getAllPhieuKham() {
        List<PhieuKham> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuKham ORDER BY ngaykhamban DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PhieuKham pk = mapResultSet(rs);
                list.add(pk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2️⃣ Thêm phiếu khám mới
    public boolean insertPhieuKham(PhieuKham pk) {
        String sql = "INSERT INTO PhieuKham( id_lich, id_bn, hoten_nv, sdt_nv, id_bs, loaikham, phongkham, id_nv_don, giokham, trangthai, ghichu) "
                   + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (pk.getId_lich() != null) ps.setInt(1, pk.getId_lich()); else ps.setNull(1, Types.INTEGER);
            if (pk.getId_bn() != null) ps.setInt(2, pk.getId_bn()); else ps.setNull(2, Types.INTEGER);
            ps.setString(3, pk.getHoten_nv());
            ps.setString(4, pk.getSdt_nv());
            if (pk.getId_bs() != null) ps.setInt(5, pk.getId_bs()); else ps.setNull(5, Types.INTEGER);
            ps.setString(6, pk.getLoaikham());
            ps.setString(7, pk.getPhongkham());
            if (pk.getId_nv_don() != null) ps.setInt(8, pk.getId_nv_don()); else ps.setNull(8, Types.INTEGER);
            ps.setTime(9, pk.getGiokham());
            ps.setString(10, pk.getTrangthai() != null ? pk.getTrangthai() : "Đã đến");
            ps.setString(11, pk.getGhichu());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3️⃣ Cập nhật phiếu khám
    public boolean updatePhieuKham(PhieuKham pk) {
        String sql = "UPDATE PhieuKham SET sophieu=?, id_lich=?, id_bn=?, hoten_nv=?, sdt_nv=?, id_bs=?, loaikham=?, phongkham=?, id_nv_don=?, giokham=?, trangthai=?, ghichu=? WHERE id_pk=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (pk.getId_lich() != null) ps.setInt(1, pk.getId_lich()); else ps.setNull(1, Types.INTEGER);
            if (pk.getId_bn() != null) ps.setInt(2, pk.getId_bn()); else ps.setNull(2, Types.INTEGER);
            ps.setString(3, pk.getHoten_nv());
            ps.setString(4, pk.getSdt_nv());
            if (pk.getId_bs() != null) ps.setInt(5, pk.getId_bs()); else ps.setNull(5, Types.INTEGER);
            ps.setString(6, pk.getLoaikham());
            ps.setString(7, pk.getPhongkham());
            if (pk.getId_nv_don() != null) ps.setInt(8, pk.getId_nv_don()); else ps.setNull(8, Types.INTEGER);
            ps.setTime(9, pk.getGiokham());
            ps.setString(10, pk.getTrangthai() != null ? pk.getTrangthai() : "Đã đến");
            ps.setString(11, pk.getGhichu());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Xóa phiếu khám
    public boolean deletePhieuKham(int id_pk) {
        String sql = "DELETE FROM PhieuKham WHERE id_pk=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_pk);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Tìm kiếm phiếu khám theo tên, sđt, mã phiếu
    public List<PhieuKham> searchPhieuKham(String keyword) {
        List<PhieuKham> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuKham WHERE hoten_nv LIKE ? OR sdt_nv LIKE ? OR sophieu LIKE ? ORDER BY ngaykhamban DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6️⃣ Lấy danh sách phiếu khám theo bác sĩ
    public List<PhieuKham> getPhieuKhamByBacSi(int id_bs) {
        List<PhieuKham> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuKham WHERE id_bs=? ORDER BY ngaykhamban DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_bs);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 7️⃣ Lọc theo trạng thái (Đã đến, Đang khám, Hoàn thành, Hủy)
    public List<PhieuKham> getPhieuKhamByTrangThai(String trangthai) {
        List<PhieuKham> list = new ArrayList<>();
        String sql = "SELECT * FROM PhieuKham WHERE trangthai=? ORDER BY ngaykhamban DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangthai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 8️⃣ Ánh xạ ResultSet → Entity (dùng lại)
    private PhieuKham mapResultSet(ResultSet rs) throws SQLException {
        PhieuKham pk = new PhieuKham();
        pk.setId_pk(rs.getInt("id_pk"));
        pk.setId_lich(rs.getObject("id_lich") != null ? rs.getInt("id_lich") : null);
        pk.setId_bn(rs.getObject("id_bn") != null ? rs.getInt("id_bn") : null);
        pk.setHoten_nv(rs.getString("hoten_nv"));
        pk.setSdt_nv(rs.getString("sdt_nv"));
        pk.setId_bs(rs.getObject("id_bs") != null ? rs.getInt("id_bs") : null);
        pk.setLoaikham(rs.getString("loaikham"));
        pk.setPhongkham(rs.getString("phongkham"));
        pk.setId_nv_don(rs.getObject("id_nv_don") != null ? rs.getInt("id_nv_don") : null);
        pk.setNgaykhamban(rs.getTimestamp("ngaykhamban"));
        pk.setGiokham(rs.getTime("giokham"));
        pk.setTrangthai(rs.getString("trangthai"));
        pk.setGhichu(rs.getString("ghichu"));
        return pk;
    }
    
}
         