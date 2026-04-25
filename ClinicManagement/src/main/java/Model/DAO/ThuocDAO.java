package Model.DAO;

import Model.DBConnection;
import Model.Entity.Thuoc;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

/**
 * DAO: ThuocDAO
 * Chức năng quản lý kho thuốc (CRUD + tìm kiếm)
 * @author giang
 */
public class ThuocDAO {

    // 1️⃣ Lấy danh sách toàn bộ thuốc
    public List<Thuoc> getAllThuoc() {
        List<Thuoc> list = new ArrayList<>();
        String sql = "SELECT * FROM Thuoc ORDER BY tenthuoc";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Thuoc t = new Thuoc();
                t.setId_thuoc(rs.getInt("id_thuoc"));
                t.setTenthuoc(rs.getString("tenthuoc"));
                t.setLoai(rs.getString("loai"));
                t.setSoluongton(rs.getInt("soluongton"));
                t.setDonvitinh(rs.getString("donvitinh"));
                t.setGiaban(rs.getBigDecimal("giaban"));
                t.setHansudung(rs.getDate("hansudung"));
                t.setGhichu(rs.getString("ghichu"));
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

public boolean insertThuoc(Thuoc t) {
    String sql = """
        INSERT INTO Thuoc
        (tenthuoc, loai, soluongton, donvitinh, giaban, hansudung, ghichu)
        VALUES (?,?,?,?,?,?,?)
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, t.getTenthuoc());
        ps.setString(2, t.getLoai());
        ps.setInt(3, t.getSoluongton());
        ps.setString(4, t.getDonvitinh());
        ps.setBigDecimal(5, t.getGiaban());
        ps.setDate(6, t.getHansudung());
        ps.setString(7, t.getGhichu());

        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

public boolean updateThuoc(Thuoc t) {
    String sql = """
        UPDATE Thuoc SET
        tenthuoc=?, loai=?, soluongton=?, donvitinh=?,
        giaban=?, hansudung=?, ghichu=?
        WHERE id_thuoc=?
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, t.getTenthuoc());
        ps.setString(2, t.getLoai());
        ps.setInt(3, t.getSoluongton());
        ps.setString(4, t.getDonvitinh());
        ps.setBigDecimal(5, t.getGiaban());
        ps.setDate(6, t.getHansudung());
        ps.setString(7, t.getGhichu());
        ps.setInt(8, t.getId_thuoc());

        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

public boolean deleteThuoc(int id_thuoc) {
    String sql = "DELETE FROM Thuoc WHERE id_thuoc=?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id_thuoc);
        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

public List<Object[]> searchThuoc(String keyword) {
    List<Object[]> list = new ArrayList<>();

    String sql = """
        SELECT id_thuoc, tenthuoc, loai, soluongton,
               donvitinh, giaban, hansudung, ghichu
        FROM Thuoc
        WHERE tenthuoc LIKE ?
    """;

    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getInt("id_thuoc"),
                rs.getString("tenthuoc"),
                rs.getString("loai"),
                rs.getInt("soluongton"),
                rs.getString("donvitinh"),
                rs.getBigDecimal("giaban"),
                rs.getDate("hansudung"),
                rs.getString("ghichu")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


    // 6️⃣ Cập nhật số lượng tồn (nhập / xuất)
    public boolean updateSoLuongTon(int id_thuoc, int newSoLuong) {
        String sql = "UPDATE Thuoc SET soluongton=? WHERE id_thuoc=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newSoLuong);
            ps.setInt(2, id_thuoc);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Object[]> getThuocTableData() {

    List<Object[]> list = new ArrayList<>();

    String sql = """
        SELECT 
            id_thuoc,
            tenthuoc,
            loai,
            soluongton,
            donvitinh,
            giaban,
            hansudung,
            ghichu
        FROM Thuoc
        ORDER BY tenthuoc
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Object[]{
                rs.getInt("id_thuoc"),
                rs.getString("tenthuoc"),
                rs.getString("loai"),
                rs.getInt("soluongton"),
                rs.getString("donvitinh"),
                rs.getBigDecimal("giaban"),
                rs.getDate("hansudung"),
                rs.getString("ghichu")
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
public List<Object[]> getThuocTableData(String keyword) {
    List<Object[]> list = new ArrayList<>();

    String sql =
      "SELECT id_thuoc, tenthuoc, loai, soluongton, donvitinh, giaban, hansudung, ghichu " +
      "FROM Thuoc " +
      "WHERE tenthuoc LIKE ? OR loai LIKE ?";

    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        String key = "%" + keyword + "%";
        ps.setString(1, key);
        ps.setString(2, key);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Object[]{
                rs.getInt("id_thuoc"),
                rs.getString("tenthuoc"),
                rs.getString("loai"),
                rs.getInt("soluongton"),
                rs.getString("donvitinh"),
                rs.getBigDecimal("giaban"),
                rs.getDate("hansudung"),
                rs.getString("ghichu")
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
// Lấy danh sách loại thuốc (distinct)
public List<String> getAllLoaiThuoc() {
    List<String> list = new ArrayList<>();
    String sql = "SELECT DISTINCT loai FROM Thuoc WHERE loai IS NOT NULL";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(rs.getString("loai"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
public List<Object[]> searchThuoc(String keyword, String loai) {

    List<Object[]> list = new ArrayList<>();

    String sql = """
        SELECT id_thuoc, tenthuoc, loai, soluongton, donvitinh, giaban, hansudung, ghichu
        FROM Thuoc
        WHERE tenthuoc LIKE ?
        AND ( ? = 'Tất cả' OR loai = ? )
        ORDER BY tenthuoc
    """;

    try (Connection c = DBConnection.getConnection();
     PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, loai);
        ps.setString(3, loai);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Object[]{
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5),
                rs.getBigDecimal(6),
                rs.getDate(7),
                rs.getString(8)
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
