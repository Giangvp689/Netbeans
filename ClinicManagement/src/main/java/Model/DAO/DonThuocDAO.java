package Model.DAO;

import Model.DBConnection;
import Model.Entity.DonThuoc;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/**
 * DAO: DonThuocDAO
 * Quản lý các thao tác CRUD trên bảng DonThuoc (đơn thuốc)
 * @author giang
 */
public class DonThuocDAO {
public int getOrCreateDonThuoc(int idBa) {

        try (Connection c = DBConnection.getConnection()) {

            // 1️⃣ kiểm tra đã có đơn chưa
            String check = "SELECT id_dt FROM DonThuoc WHERE id_ba=?";
            PreparedStatement ps = c.prepareStatement(check);
            ps.setInt(1, idBa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

            // 2️⃣ chưa có → tạo mới
            String insert = "INSERT INTO DonThuoc(id_ba) VALUES (?)";
            ps = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idBa);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void insertChiTiet(
        int idDt,
        int idThuoc,
        int soLuong,
        String lieuDung,
        BigDecimal donGia
    ) {

        try (Connection c = DBConnection.getConnection()) {

            // thêm chi tiết
            String sql =
                "INSERT INTO ChiTiet_DonThuoc " +
                "(id_dt, id_thuoc, soluong, lieudung, dongia) " +
                "VALUES (?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idDt);
            ps.setInt(2, idThuoc);
            ps.setInt(3, soLuong);
            ps.setString(4, lieuDung);
            ps.setBigDecimal(5, donGia);
            ps.executeUpdate();

            // trừ kho thuốc
            ps = c.prepareStatement(
                "UPDATE Thuoc SET soluong = soluong - ? WHERE id_thuoc=?"
            );
            ps.setInt(1, soLuong);
            ps.setInt(2, idThuoc);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // lấy id đơn thuốc theo bệnh án
    public int getDonThuocByBenhAn(int idBa) {
        try (Connection c = DBConnection.getConnection()) {
            PreparedStatement ps =
                c.prepareStatement("SELECT id_dt FROM DonThuoc WHERE id_ba=?");
            ps.setInt(1, idBa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public String getGhiChuByDonThuoc(int idDt) {
    try (Connection c = DBConnection.getConnection()) {
        String sql = "SELECT ghichu FROM DonThuoc WHERE id_dt = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, idDt);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("ghichu");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "";
}

    
    
    
    public void updateDonThuoc(
        int idDt,
        int idBa,
        int idBs,
        String ghiChu
) {
    try (Connection c = DBConnection.getConnection()) {

        String sql =
            "UPDATE DonThuoc " +
            "SET id_ba = ?, id_bs = ?, ghichu = ? " +
            "WHERE id_dt = ?";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, idBa);
        ps.setInt(2, idBs);
        ps.setString(3, ghiChu);
        ps.setInt(4, idDt);

        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
