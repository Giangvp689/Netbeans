package Model.DAO;

import Model.DBConnection;
import Model.Entity.ChiTietDonThuoc;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

/**
 * DAO: ChiTietDonThuocDAO
 * Quản lý CRUD cho bảng ChiTiet_DonThuoc (thuốc trong đơn)
 * @author giang
 */
public class ChiTietDonThuocDAO {
public List<ChiTietDonThuoc> getByDonThuoc(int idDt) {

        List<ChiTietDonThuoc> list = new ArrayList<>();

        String sql =
          "SELECT ct.id_ctdt, ct.id_thuoc, t.tenthuoc, " +
          "ct.soluong, ct.lieudung, ct.dongia " +
          "FROM ChiTiet_DonThuoc ct " +
          "JOIN Thuoc t ON ct.id_thuoc = t.id_thuoc " +
          "WHERE ct.id_dt = ?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idDt);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChiTietDonThuoc ct = new ChiTietDonThuoc();
                ct.setId_ctdt(rs.getInt(1));
                ct.setId_thuoc(rs.getInt(2));
                ct.setTenThuoc(rs.getString(3));
                ct.setSoluong(rs.getInt(4));
                ct.setLieudung(rs.getString(5));
                ct.setDongia(rs.getBigDecimal(6));
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

public boolean delete(int id_ctdt) {
    String sql = "DELETE FROM ChiTiet_DonThuoc WHERE id_ctdt = ?";
    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, id_ctdt);
        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
    public void insertChiTietDon(
            int idDt,
            int idThuoc,
            int soLuong,
            String lieuDung,
            BigDecimal donGia
    ) throws SQLException {

        String sql =
            "INSERT INTO ChiTiet_DonThuoc " +
            "(id_dt, id_thuoc, soluong, lieudung, dongia) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDt);
            ps.setInt(2, idThuoc);
            ps.setInt(3, soLuong);
            ps.setString(4, lieuDung);
            ps.setBigDecimal(5, donGia);

            ps.executeUpdate(); 
            // ⚠ Nếu thiếu kho → trigger RAISERROR → SQLException
        }
    }
}
