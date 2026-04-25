package Model.DAO;

import Model.DBConnection;
import Model.Entity.HoaDon;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

/**
 * DAO: HoaDonDAO
 * Quản lý hóa đơn thanh toán (liên kết với BenhAn)
 * 
 * @author giang
 */
public class HoaDonDAO {
    
    
    public List<Object[]> getChiTietHoaDon(int idHd) {

    List<Object[]> list = new ArrayList<>();

    String sql = """
        SELECT
            c.loai_item,
            CASE
                WHEN c.loai_item = 'THUOC' THEN t.tenthuoc
                ELSE dv.tendv
            END AS ten,
            c.soluong,
            c.dongia,
            c.thanhtien
        FROM ChiTiet_HoaDon c
        LEFT JOIN Thuoc t
            ON c.loai_item = 'THUOC' AND c.id_ref = t.id_thuoc
        LEFT JOIN DichVu dv
            ON c.loai_item = 'DICHVU' AND c.id_ref = dv.id_dv
        WHERE c.id_hd = ?
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idHd);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("loai_item"),
                rs.getString("ten"),
                rs.getInt("soluong"),
                rs.getDouble("dongia"),
                rs.getDouble("thanhtien")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

public int getHoaDonByBenhAn(int idBa) {
        try (Connection c = DBConnection.getConnection()) {
            String sql = "SELECT id_hd FROM HoaDon WHERE id_ba = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idBa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id_hd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getGhiChu(int idHd) {
        try (Connection c = DBConnection.getConnection()) {
            PreparedStatement ps =
                c.prepareStatement("SELECT ghichu FROM HoaDon WHERE id_hd=?");
            ps.setInt(1, idHd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("ghichu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void updateGhiChu(int idHd, String ghiChu) {
        try (Connection c = DBConnection.getConnection()) {
            PreparedStatement ps =
                c.prepareStatement("UPDATE HoaDon SET ghichu=? WHERE id_hd=?");
            ps.setString(1, ghiChu);
            ps.setInt(2, idHd);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   public int getOrCreateHoaDon(int idBa, int idBn) {

    String findSql = "SELECT id_hd FROM HoaDon WHERE id_ba = ?";
    String insertSql = """
        INSERT INTO HoaDon(id_ba, id_bn)
        VALUES (?, ?)
    """;

    try (Connection con = DBConnection.getConnection()) {

        // 1️⃣ Tìm hóa đơn theo bệnh án
        PreparedStatement ps = con.prepareStatement(findSql);
        ps.setInt(1, idBa);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("id_hd");
        }

        // 2️⃣ Chưa có → tạo mới
        ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, idBa);
        ps.setInt(2, idBn);
        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return 0;
}
   public List<HoaDon> getListHoaDon(String keyword) {
    List<HoaDon> list = new ArrayList<>();
    // SQL JOIN để lấy tên bệnh nhân và nhân viên dựa trên các ID trong bảng HoaDon
    String sql = "SELECT hd.*, bn.HoTen as TenBN, nv.HoTen as TenNV " +
                 "FROM HoaDon hd " +
                 "LEFT JOIN BenhNhan bn ON hd.id_bn = bn.id_bn " +
                 "LEFT JOIN NhanVien nv ON hd.id_nv_lap = nv.id_nv " +
                 "WHERE hd.mahd LIKE ? OR bn.HoTen LIKE ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setId_hd(rs.getInt("id_hd"));
            hd.setMahd(rs.getString("mahd"));
            hd.setNgaylap(rs.getTimestamp("ngaylap"));
            hd.setTongtien(rs.getBigDecimal("tongtien"));
            hd.setTrangthai(rs.getString("trangthai"));
            hd.setTenBenhNhan(rs.getString("TenBN"));
            hd.setTenNhanVien(rs.getString("TenNV"));
            list.add(hd);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}



 private Connection conn = DBConnection.getConnection();

    // ===============================
    // 1. Lấy hóa đơn theo bệnh án
    // ===============================
    public HoaDon getByBenhAn(int idBa) {
        String sql = "SELECT * FROM HoaDon WHERE id_ba = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idBa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapHoaDon(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ===============================
    // 2. Tạo hóa đơn (nếu chưa có)
    // ===============================
    public HoaDon createHoaDon(int idBa, int idBn, int idNv) {
        String sql =
            "INSERT INTO HoaDon(id_ba, id_bn, id_nv_lap, trangthai) " +
            "VALUES (?, ?, ?, N'Chưa thanh toán')";

        try (PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idBa);
            ps.setInt(2, idBn);
            ps.setInt(3, idNv);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return getById(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ===============================
    public HoaDon getById(int idHd) {
        String sql = "SELECT * FROM HoaDon WHERE id_hd = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapHoaDon(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ===============================
    private HoaDon mapHoaDon(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setId_hd(rs.getInt("id_hd"));
        hd.setMahd(rs.getString("mahd"));
        hd.setId_ba(rs.getInt("id_ba"));
        hd.setId_bn(rs.getInt("id_bn"));
        hd.setId_nv_lap(rs.getInt("id_nv_lap"));
        hd.setNgaylap(rs.getTimestamp("ngaylap"));
        hd.setTongtien(rs.getBigDecimal("tongtien"));
        hd.setTrangthai(rs.getString("trangthai"));
        hd.setGhichu(rs.getString("ghichu"));
        return hd;
    }
    public List<Object[]> getHoaDonTableData(int idBa) {
    List<Object[]> list = new ArrayList<>();

    String sql = """
        -- ===== DỊCH VỤ =====
        SELECT 
            N'Dịch vụ' AS loai,
            dv.tendv AS ten,
            ctdv.soluong,
            ctdv.dongia,
            ctdv.soluong * ctdv.dongia AS thanhtien
        FROM ChiTiet_DichVu ctdv
        JOIN DichVu dv ON dv.id_dv = ctdv.id_dv
        WHERE ctdv.id_ba = ?

        UNION ALL

        -- ===== THUỐC =====
        SELECT 
            N'Thuốc' AS loai,
            t.tenthuoc AS ten,
            ctdt.soluong,
            ctdt.dongia,
            ctdt.soluong * ctdt.dongia AS thanhtien
        FROM DonThuoc dt
        JOIN ChiTiet_DonThuoc ctdt ON dt.id_dt = ctdt.id_dt
        JOIN Thuoc t ON t.id_thuoc = ctdt.id_thuoc
        WHERE dt.id_ba = ?
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idBa);
        ps.setInt(2, idBa);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("loai"),
                rs.getString("ten"),
                rs.getInt("soluong"),
                rs.getBigDecimal("dongia"),
                rs.getBigDecimal("thanhtien")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

    public BigDecimal getTongTien(int idBa) {

    String sql = """
        SELECT SUM(tien) AS tong FROM (
            SELECT ctdv.soluong * ctdv.dongia AS tien
            FROM ChiTiet_DichVu ctdv
            WHERE ctdv.id_ba = ?

            UNION ALL

            SELECT ctdt.soluong * ctdt.dongia
            FROM DonThuoc dt
            JOIN ChiTiet_DonThuoc ctdt ON dt.id_dt = ctdt.id_dt
            WHERE dt.id_ba = ?
        ) x
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idBa);
        ps.setInt(2, idBa);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getBigDecimal("tong") != null
                ? rs.getBigDecimal("tong")
                : BigDecimal.ZERO;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return BigDecimal.ZERO;
}
public void saveGhiChu(int idBa, String ghiChu) {

    String sql = """
        UPDATE HoaDon
        SET ghichu = ?
        WHERE id_ba = ?
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, ghiChu);
        ps.setInt(2, idBa);

        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void copyDichVuToHoaDon(int idHd, int idBa) {

    String sql = """
        INSERT INTO ChiTiet_HoaDon(id_hd, loai_item, id_ref, soluong, dongia)
        SELECT 
            ?, 
            'DICHVU',
            id_dv,
            soluong,
            dongia
        FROM ChiTiet_DichVu
        WHERE id_ba = ?
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idHd);
        ps.setInt(2, idBa);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public void copyThuocToHoaDon(int idHd, int idBa) {

    String sql = """
        INSERT INTO ChiTiet_HoaDon(id_hd, loai_item, id_ref, soluong, dongia)
        SELECT 
            ?, 
            'THUOC',
            ctdt.id_thuoc,
            ctdt.soluong,
            ctdt.dongia
        FROM DonThuoc dt
        JOIN ChiTiet_DonThuoc ctdt ON dt.id_dt = ctdt.id_dt
        WHERE dt.id_ba = ?
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idHd);
        ps.setInt(2, idBa);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public void deleteChiTietHoaDon(int idHd) {

    String sql = "DELETE FROM ChiTiet_HoaDon WHERE id_hd = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idHd);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public boolean deleteHoaDon(int idHd) {

    String sql = "DELETE FROM HoaDon WHERE id_hd = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idHd);
        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
public boolean deleteHoaDonByBenhAn(int idBa) {

    String findSql = "SELECT id_hd FROM HoaDon WHERE id_ba = ?";

    try (Connection con = DBConnection.getConnection()) {

        PreparedStatement ps = con.prepareStatement(findSql);
        ps.setInt(1, idBa);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) return false;

        int idHd = rs.getInt("id_hd");

        deleteChiTietHoaDon(idHd);
        return deleteHoaDon(idHd);

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}

    public List<HoaDon> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

}
