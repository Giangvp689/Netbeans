package Model.DAO;

import Model.DBConnection;
import Model.Entity.ChiTiet_HoaDon;
import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList; // Quan trọng
import java.util.List;      // Quan trọng

/**
 * DAO: ChiTiet_HoaDonDAO
 * 
 * Quản lý các dòng chi tiết của hóa đơn (thuốc / dịch vụ)
 * 
 * @author giang
 */
public class ChiTiet_HoaDonDAO {
 public void addThuoc(
        int idHd, int idThuoc, int soLuong, BigDecimal donGia
    ) {
        try (Connection c = DBConnection.getConnection()) {

            String sql =
              "INSERT INTO ChiTiet_HoaDon(id_hd, loai_item, id_ref, soluong, dongia) " +
              "VALUES (?, N'THUOC', ?, ?, ?)";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idHd);
            ps.setInt(2, idThuoc);
            ps.setInt(3, soLuong);
            ps.setBigDecimal(4, donGia);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 public List<Object[]> getTableDataByHoaDon(int idHd) {
    List<Object[]> list = new ArrayList<>();

    String sql = """
        SELECT 
            cthd.loai_item,
            CASE 
                WHEN cthd.loai_item = 'THUOC' THEN t.tenthuoc
                ELSE dv.tendv
            END AS ten_item,
            cthd.soluong,
            cthd.dongia,
            cthd.thanhtien
        FROM ChiTiet_HoaDon cthd
        LEFT JOIN Thuoc t 
            ON cthd.loai_item = 'THUOC' AND cthd.id_ref = t.id_thuoc
        LEFT JOIN DichVu dv 
            ON cthd.loai_item = 'DICHVU' AND cthd.id_ref = dv.id_dv
        WHERE cthd.id_hd = ?
    """;

    try (Connection c = DBConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, idHd);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("loai_item"),
                rs.getString("ten_item"),
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
  private Connection conn = DBConnection.getConnection();

    public void themDichVu(int idHd, int idDv, int soluong, double dongia) {
        String sql =
            "INSERT INTO ChiTiet_HoaDon(id_hd, loai_item, id_ref, soluong, dongia) " +
            "VALUES (?, N'DICHVU', ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHd);
            ps.setInt(2, idDv);
            ps.setInt(3, soluong);
            ps.setDouble(4, dongia);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void themThuoc(int idHd, int idThuoc, int soluong, double dongia) {
        String sql =
            "INSERT INTO ChiTiet_HoaDon(id_hd, loai_item, id_ref, soluong, dongia) " +
            "VALUES (?, N'THUOC', ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHd);
            ps.setInt(2, idThuoc);
            ps.setInt(3, soluong);
            ps.setDouble(4, dongia);
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
public List<ChiTiet_HoaDon> getByHoaDonId(int id_hd) {
    List<ChiTiet_HoaDon> list = new ArrayList<>();
    
    // Đã sửa lại JOIN để khớp với tên cột phổ biến (dv.TenDV thay vì dv.TenDichVu)
    String sql = "SELECT ct.*, " +
                 "COALESCE(t.TenThuoc, dv.TenDV) as TenItem " + 
                 "FROM ChiTiet_HoaDon ct " +
                 "LEFT JOIN Thuoc t ON ct.id_ref = t.id_thuoc AND ct.loai_item = 'THUOC' " +
                 "LEFT JOIN DichVu dv ON ct.id_ref = dv.id_dv AND ct.loai_item = 'DICHVU' " +
                 "WHERE ct.id_hd = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, id_hd);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ChiTiet_HoaDon ct = new ChiTiet_HoaDon();
            ct.setId_cthd(rs.getInt("id_cthd"));
            ct.setId_hd(rs.getInt("id_hd"));
            ct.setLoai_item(rs.getString("loai_item"));
            ct.setId_ref(rs.getInt("id_ref"));
            ct.setSoluong(rs.getInt("soluong"));
            ct.setDongia(rs.getBigDecimal("dongia"));
            ct.setThanhtien(rs.getBigDecimal("thanhtien"));
            ct.setTenItem(rs.getString("TenItem")); 
            
            list.add(ct);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
}
