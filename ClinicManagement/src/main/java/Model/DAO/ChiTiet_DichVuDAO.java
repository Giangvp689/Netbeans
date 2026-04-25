package Model.DAO;

import Model.DBConnection;
import Model.Entity.ChiTiet_DichVu;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

/**
 * DAO: ChiTiet_DichVuDAO
 * Quản lý chi tiết dịch vụ được chỉ định trong bệnh án
 * (liên kết BenhAn - DichVu)
 * @author giang
 */
public class ChiTiet_DichVuDAO {

    // 1️⃣ Lấy tất cả chi tiết dịch vụ của một bệnh án
    public List<ChiTiet_DichVu> getByBenhAn(int id_ba) {
        List<ChiTiet_DichVu> list = new ArrayList<>();
        String sql = """
            SELECT ctdv.*, dv.tendv
            FROM ChiTiet_DichVu ctdv
            JOIN DichVu dv ON ctdv.id_dv = dv.id_dv
            WHERE ctdv.id_ba = ?
            ORDER BY ctdv.id_ctdv
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_ba);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChiTiet_DichVu c = new ChiTiet_DichVu();
                c.setId_ctdv(rs.getInt("id_ctdv"));
                c.setId_ba(rs.getInt("id_ba"));
                c.setId_dv(rs.getInt("id_dv"));
                c.setSoluong(rs.getInt("soluong"));
                c.setDongia(rs.getDouble("dongia"));
                c.setTenDichVu(rs.getString("tendv"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ChiTiet_DichVu> getChiTietDichVuByBenhAn(int idBa) {
    List<ChiTiet_DichVu> list = new ArrayList<>();
    String sql = "SELECT * FROM ChiTiet_DichVu WHERE id_ba = ?";

    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, idBa);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ChiTiet_DichVu ct = new ChiTiet_DichVu();
            ct.setId_ctdv(rs.getInt("id_ctdv"));
            ct.setId_ba(rs.getInt("id_ba"));
            ct.setId_dv(rs.getInt("id_dv"));
            ct.setSoluong(rs.getInt("soluong"));
            ct.setDongia(rs.getDouble("dongia"));
            list.add(ct);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    public void insertChiTietDichVu(ChiTiet_DichVu ct) {
    String sql = """
        INSERT INTO ChiTiet_DichVu(id_ba, id_dv, soluong, dongia)
        VALUES (?,?,?,?)
    """;
    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, ct.getId_ba());
        ps.setInt(2, ct.getId_dv());
        ps.setInt(3, ct.getSoluong());
        ps.setDouble(4, ct.getDongia());

        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    // 3️⃣ Cập nhật chi tiết dịch vụ
    public boolean updateChiTietDichVu(ChiTiet_DichVu ctdv) {
        String sql = "UPDATE ChiTiet_DichVu SET id_dv=?, soluong=?, dongia=? WHERE id_ctdv=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ctdv.getId_dv());
            ps.setInt(2, ctdv.getSoluong());
            ps.setDouble(3, ctdv.getDongia());
            ps.setInt(4, ctdv.getId_ctdv());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Xóa chi tiết dịch vụ theo ID
    public boolean deleteChiTietDichVu(int id_ctdv) {
        String sql = "DELETE FROM ChiTiet_DichVu WHERE id_ctdv=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_ctdv);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Tính tổng tiền dịch vụ trong 1 bệnh án
    public BigDecimal tinhTongTienDichVu(int id_ba) {
        BigDecimal tong = BigDecimal.ZERO;
        String sql = "SELECT SUM(soluong * dongia) AS tong FROM ChiTiet_DichVu WHERE id_ba=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_ba);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tong = rs.getBigDecimal("tong");
                if (tong == null) tong = BigDecimal.ZERO;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tong;
    }
    
    public void deleteChiTietDichVuByBenhAn(int idBa) {
    String sql = "DELETE FROM ChiTiet_DichVu WHERE id_ba = ?";
    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {
        ps.setInt(1, idBa);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
