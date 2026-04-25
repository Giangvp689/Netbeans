package Model.DAO;

import Model.Entity.NhanVien;
import Model.DBConnection;
import java.sql.*;
import java.util.*;

public class NhanVienDAO {

    // Lấy tất cả nhân viên
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId_nv(rs.getInt("id_nv"));
                nv.setHoten(rs.getString("hoten"));
                nv.setNgaysinh(rs.getDate("ngaysinh"));
                nv.setGioitinh(rs.getString("gioitinh"));
                nv.setDiachi(rs.getString("diachi"));
                nv.setSdt(rs.getString("sdt"));
                nv.setEmail(rs.getString("email"));
                nv.setChucvu(rs.getString("chucvu"));
                nv.setTrangthai(rs.getString("trangthai"));
                nv.setTaikhoan(rs.getString("taikhoan"));
                nv.setMatkhau(rs.getString("matkhau"));
                list.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm nhân viên mới
    public boolean insertNhanVien(NhanVien nv) {
        String sql = "INSERT INTO NhanVien(hoten, ngaysinh, gioitinh, diachi, sdt, email, chucvu, trangthai, taikhoan, matkhau) "
                   + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nv.getHoten());
            if (nv.getNgaysinh() != null)
                ps.setDate(2, new java.sql.Date(nv.getNgaysinh().getTime()));
            else
                ps.setDate(2, null);
            ps.setString(3, nv.getGioitinh());
            ps.setString(4, nv.getDiachi());
            ps.setString(5, nv.getSdt());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getChucvu());
            ps.setString(8, nv.getTrangthai());
            ps.setString(9, nv.getTaikhoan());
            ps.setString(10, nv.getMatkhau());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật
    public boolean updateNhanVien(NhanVien nv) {
        String sql = "UPDATE NhanVien SET hoten=?, ngaysinh=?, gioitinh=?, diachi=?, sdt=?, email=?, chucvu=?, trangthai=?, taikhoan=?, matkhau=? WHERE id_nv=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nv.getHoten());
            if (nv.getNgaysinh() != null)
                ps.setDate(2, new java.sql.Date(nv.getNgaysinh().getTime()));
            else
                ps.setDate(2, null);
            ps.setString(3, nv.getGioitinh());
            ps.setString(4, nv.getDiachi());
            ps.setString(5, nv.getSdt());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getChucvu());
            ps.setString(8, nv.getTrangthai());
            ps.setString(9, nv.getTaikhoan());
            ps.setString(10, nv.getMatkhau());
            ps.setInt(11, nv.getId_nv());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa nhân viên
    public boolean deleteNhanVien(int id_nv) {
        String sql = "DELETE FROM NhanVien WHERE id_nv=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_nv);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm
    public List<NhanVien> searchNhanVien(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE hoten LIKE ? OR sdt LIKE ? OR chucvu LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId_nv(rs.getInt("id_nv"));
                nv.setHoten(rs.getString("hoten"));
                nv.setChucvu(rs.getString("chucvu"));
                nv.setSdt(rs.getString("sdt"));
                list.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
