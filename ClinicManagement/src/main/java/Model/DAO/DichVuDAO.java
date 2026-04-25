package Model.DAO;

import Model.DBConnection;
import Model.Entity.DichVu;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

/**
 * DAO: DichVuDAO
 * Quản lý CRUD cho bảng DichVu (dịch vụ phòng khám)
 * @author giang
 */
public class DichVuDAO {

    // 1️⃣ Lấy tất cả dịch vụ
    public List<DichVu> getAllDichVu() {
        List<DichVu> list = new ArrayList<>();
        String sql = "SELECT * FROM DichVu ORDER BY id_dv";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DichVu dv = new DichVu();
                dv.setId_dv(rs.getInt("id_dv"));
                dv.setMadv(rs.getString("madv"));
                dv.setTendv(rs.getString("tendv"));
                dv.setLoai(rs.getString("loai"));
                dv.setDongia(rs.getDouble("dongia"));
                dv.setMota(rs.getString("mota"));
                dv.setTrangthai(rs.getString("trangthai"));
                list.add(dv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2️⃣ Thêm dịch vụ mới
    public boolean insertDichVu(DichVu dv) {
        String sql = "INSERT INTO DichVu(madv, tendv, loai, dongia, mota, trangthai) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dv.getMadv());
            ps.setString(2, dv.getTendv());
            ps.setString(3, dv.getLoai());
            ps.setDouble(4, dv.getDongia());
            ps.setString(5, dv.getMota());
            ps.setString(6, dv.getTrangthai());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3️⃣ Cập nhật dịch vụ
    public boolean updateDichVu(DichVu dv) {
        String sql = "UPDATE DichVu SET madv=?, tendv=?, loai=?, dongia=?, mota=?, trangthai=? WHERE id_dv=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dv.getMadv());
            ps.setString(2, dv.getTendv());
            ps.setString(3, dv.getLoai());
            ps.setDouble(4, dv.getDongia());
            ps.setString(5, dv.getMota());
            ps.setString(6, dv.getTrangthai());
            ps.setInt(7, dv.getId_dv());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Xóa dịch vụ
    public boolean deleteDichVu(int id_dv) {
        String sql = "DELETE FROM DichVu WHERE id_dv=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_dv);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Tìm kiếm dịch vụ theo tên, mã hoặc loại
    public List<DichVu> searchDichVu(String keyword) {
        List<DichVu> list = new ArrayList<>();
        String sql = "SELECT * FROM DichVu WHERE madv LIKE ? OR tendv LIKE ? OR loai LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DichVu dv = new DichVu();
                dv.setId_dv(rs.getInt("id_dv"));
                dv.setMadv(rs.getString("madv"));
                dv.setTendv(rs.getString("tendv"));
                dv.setLoai(rs.getString("loai"));
                dv.setDongia(rs.getDouble("dongia"));
                dv.setMota(rs.getString("mota"));
                dv.setTrangthai(rs.getString("trangthai"));
                list.add(dv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6️⃣ Lấy dịch vụ theo ID
    public DichVu getDichVuById(int id_dv) {
        DichVu dv = null;
        String sql = "SELECT * FROM DichVu WHERE id_dv=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_dv);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                dv = new DichVu();
                dv.setId_dv(rs.getInt("id_dv"));
                dv.setMadv(rs.getString("madv"));
                dv.setTendv(rs.getString("tendv"));
                dv.setLoai(rs.getString("loai"));
                dv.setDongia(rs.getDouble("dongia"));
                dv.setMota(rs.getString("mota"));
                dv.setTrangthai(rs.getString("trangthai"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dv;
    }
    
    // 7️⃣ Lấy dịch vụ theo tên (dùng khi lưu bệnh án)
public DichVu getDichVuByTen(String tenDv) {
    String sql = "SELECT * FROM DichVu WHERE tendv = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, tenDv);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            DichVu dv = new DichVu();
            dv.setId_dv(rs.getInt("id_dv"));
            dv.setMadv(rs.getString("madv"));
            dv.setTendv(rs.getString("tendv"));
            dv.setLoai(rs.getString("loai"));
            dv.setDongia(rs.getDouble("dongia"));
            dv.setMota(rs.getString("mota"));
            dv.setTrangthai(rs.getString("trangthai"));
            return dv;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}
