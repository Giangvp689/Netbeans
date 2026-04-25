package Model.DAO;

import Model.DBConnection;
import Model.Entity.HenKham;
import java.sql.*;
import java.util.*;

/**
 * DAO: HenKhamDAO
 * Chức năng CRUD + tìm kiếm lịch hẹn khám
 * @author giang
 */
public class HenKhamDAO {

    // 1️⃣ Lấy toàn bộ lịch hẹn
    public List<HenKham> getAllHenKham() {
        List<HenKham> list = new ArrayList<>();
        String sql = "SELECT * FROM HenKham ORDER BY ngayhen DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HenKham hk = new HenKham();
                hk.setId_hen(rs.getInt("id_hen"));
                hk.setId_ba((Integer) rs.getObject("id_ba"));
                hk.setId_bn(rs.getInt("id_bn"));
                hk.setId_bs((Integer) rs.getObject("id_bs"));
                hk.setNgayhen(rs.getDate("ngayhen"));
                hk.setGiohen(rs.getTime("giohen"));
                hk.setGhichu(rs.getString("ghichu"));
                hk.setTrangthai(rs.getString("trangthai"));
                hk.setCreated_at(rs.getDate("created_at"));
                list.add(hk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2️⃣ Thêm lịch hẹn mới
    public boolean insertHenKham(HenKham hk) {
        String sql = "INSERT INTO HenKham(id_ba, id_bn, id_bs, ngayhen, giohen, ghichu, trangthai) "
                   + "VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (hk.getId_ba() != null)
                ps.setInt(1, hk.getId_ba());
            else
                ps.setNull(1, Types.INTEGER);

            ps.setInt(2, hk.getId_bn());

            if (hk.getId_bs() != null)
                ps.setInt(3, hk.getId_bs());
            else
                ps.setNull(3, Types.INTEGER);

            ps.setDate(4, hk.getNgayhen());
            ps.setTime(5, hk.getGiohen());
            ps.setString(6, hk.getGhichu());
            ps.setString(7, hk.getTrangthai());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3️⃣ Cập nhật thông tin hẹn khám
    public boolean updateHenKham(HenKham hk) {
        String sql = "UPDATE HenKham SET id_ba=?, id_bn=?, id_bs=?, ngayhen=?, giohen=?, ghichu=?, trangthai=? "
                   + "WHERE id_hen=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (hk.getId_ba() != null)
                ps.setInt(1, hk.getId_ba());
            else
                ps.setNull(1, Types.INTEGER);

            ps.setInt(2, hk.getId_bn());

            if (hk.getId_bs() != null)
                ps.setInt(3, hk.getId_bs());
            else
                ps.setNull(3, Types.INTEGER);

            ps.setDate(4, hk.getNgayhen());
            ps.setTime(5, hk.getGiohen());
            ps.setString(6, hk.getGhichu());
            ps.setString(7, hk.getTrangthai());
            ps.setInt(8, hk.getId_hen());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Xóa lịch hẹn
    public boolean deleteHenKham(int id_hen) {
        String sql = "DELETE FROM HenKham WHERE id_hen=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_hen);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Tìm lịch hẹn theo id bệnh nhân
    public List<HenKham> getHenKhamByBenhNhan(int id_bn) {
        List<HenKham> list = new ArrayList<>();
        String sql = "SELECT * FROM HenKham WHERE id_bn=? ORDER BY ngayhen DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_bn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HenKham hk = new HenKham();
                hk.setId_hen(rs.getInt("id_hen"));
                hk.setId_ba((Integer) rs.getObject("id_ba"));
                hk.setId_bn(rs.getInt("id_bn"));
                hk.setId_bs((Integer) rs.getObject("id_bs"));
                hk.setNgayhen(rs.getDate("ngayhen"));
                hk.setGiohen(rs.getTime("giohen"));
                hk.setGhichu(rs.getString("ghichu"));
                hk.setTrangthai(rs.getString("trangthai"));
                hk.setCreated_at(rs.getDate("created_at"));
                list.add(hk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6️⃣ Tìm lịch hẹn theo bác sĩ
    public List<HenKham> getHenKhamByBacSi(int id_bs) {
        List<HenKham> list = new ArrayList<>();
        String sql = "SELECT * FROM HenKham WHERE id_bs=? ORDER BY ngayhen ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_bs);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HenKham hk = new HenKham();
                hk.setId_hen(rs.getInt("id_hen"));
                hk.setId_ba((Integer) rs.getObject("id_ba"));
                hk.setId_bn(rs.getInt("id_bn"));
                hk.setId_bs((Integer) rs.getObject("id_bs"));
                hk.setNgayhen(rs.getDate("ngayhen"));
                hk.setGiohen(rs.getTime("giohen"));
                hk.setGhichu(rs.getString("ghichu"));
                hk.setTrangthai(rs.getString("trangthai"));
                hk.setCreated_at(rs.getDate("created_at"));
                list.add(hk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
