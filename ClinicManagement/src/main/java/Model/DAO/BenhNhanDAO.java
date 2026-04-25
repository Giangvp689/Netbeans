/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

/**
 *
 * @author giang
 */
import Model.Entity.BenhNhan;
import Model.DBConnection;
import java.sql.*;
import java.util.*;

public class BenhNhanDAO {

    // 1️⃣ Lấy tất cả bệnh nhân
    public List<BenhNhan> getAllBenhNhan() {
        List<BenhNhan> list = new ArrayList<>();
        String sql = "SELECT * FROM BenhNhan ORDER BY id_bn";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BenhNhan bn = new BenhNhan();
                bn.setId_bn(rs.getInt("id_bn"));
                bn.setHoten(rs.getString("hoten"));
                bn.setGioitinh(rs.getString("gioitinh"));
                bn.setNgaysinh(rs.getDate("ngaysinh"));
                bn.setSdt(rs.getString("sdt"));
                bn.setDiachi(rs.getString("diachi"));
                bn.setNghenghiep(rs.getString("nghenghiep"));
                bn.setDantoc(rs.getString("dantoc"));
                bn.setNguoithan(rs.getString("nguoithan"));
                bn.setGhichu(rs.getString("ghichu"));
                bn.setNgaydangky(rs.getDate("ngaydangky"));
                list.add(bn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insertBenhNhan(BenhNhan bn) {
    String sql = """
        INSERT INTO BenhNhan
        (hoten, gioitinh, ngaysinh, sdt, diachi,
         nghenghiep, dantoc, nguoithan, ghichu, ngaydangky)
        VALUES (?,?,?,?,?,?,?,?,?,?)
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, bn.getHoten());
        ps.setString(2, bn.getGioitinh());
        ps.setDate(3, bn.getNgaysinh());
        ps.setString(4, bn.getSdt());
        ps.setString(5, bn.getDiachi());
        ps.setString(6, bn.getNghenghiep());
        ps.setString(7, bn.getDantoc());
        ps.setString(8, bn.getNguoithan());
        ps.setString(9, bn.getGhichu());
        ps.setDate(10, new java.sql.Date(System.currentTimeMillis()));

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // id_bn
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1;
}

    
            // 6️⃣ Thêm bệnh nhân & lấy ID vừa tạo
        public int insertBenhNhanAndGetId(BenhNhan bn) {
            String sql = """
                INSERT INTO BenhNhan
                (hoten, gioitinh, ngaysinh, sdt, diachi,
                 nghenghiep, dantoc, nguoithan, ghichu, ngaydangky)
                VALUES (?,?,?,?,?,?,?,?,?,?)
            """;

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, bn.getHoten());
                ps.setString(2, bn.getGioitinh());
                ps.setDate(3, bn.getNgaysinh());
                ps.setString(4, bn.getSdt());
                ps.setString(5, bn.getDiachi());
                ps.setString(6, bn.getNghenghiep());
                ps.setString(7, bn.getDantoc());
                ps.setString(8, bn.getNguoithan());
                ps.setString(9, bn.getGhichu());
                ps.setDate(10, bn.getNgaydangky());

                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // id_bn vừa insert
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        }

    // 3️⃣ Cập nhật bệnh nhân
    public boolean updateBenhNhan(BenhNhan bn) {
        String sql = "UPDATE BenhNhan SET hoten=?, gioitinh=?, ngaysinh=?, sdt=?, diachi=?, nghenghiep=?, dantoc=?, nguoithan=?, ghichu=? "
                   + "WHERE id_bn=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bn.getHoten());
            ps.setString(2, bn.getGioitinh());
            ps.setDate(3, bn.getNgaysinh() != null ? new java.sql.Date(bn.getNgaysinh().getTime()) : null);
            ps.setString(4, bn.getSdt());
            ps.setString(5, bn.getDiachi());
            ps.setString(6, bn.getNghenghiep());
            ps.setString(7, bn.getDantoc());
            ps.setString(8, bn.getNguoithan());
            ps.setString(9, bn.getGhichu());
            ps.setInt(10, bn.getId_bn());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Xóa bệnh nhân theo id
    public boolean deleteBenhNhan(int id_bn) {
        String sql = "DELETE FROM BenhNhan WHERE id_bn=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_bn);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Tìm kiếm bệnh nhân theo tên, sdt, hoặc địa chỉ
    public List<BenhNhan> searchBenhNhan(String keyword) {
        List<BenhNhan> list = new ArrayList<>();
        String sql = "SELECT * FROM BenhNhan WHERE hoten LIKE ? OR sdt LIKE ? OR diachi LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BenhNhan bn = new BenhNhan();
                bn.setId_bn(rs.getInt("id_bn"));
                bn.setHoten(rs.getString("hoten"));
                bn.setGioitinh(rs.getString("gioitinh"));
                bn.setNgaysinh(rs.getDate("ngaysinh"));
                bn.setSdt(rs.getString("sdt"));
                bn.setDiachi(rs.getString("diachi"));
                bn.setNghenghiep(rs.getString("nghenghiep"));
                bn.setDantoc(rs.getString("dantoc"));
                bn.setNguoithan(rs.getString("nguoithan"));
                bn.setGhichu(rs.getString("ghichu"));
               bn.setNgaydangky(rs.getDate("ngaydangky"));
                list.add(bn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
   
    
public List<Object[]> getBenhNhanWithBacSi() {

    List<Object[]> list = new ArrayList<>();

    String sql =
        "SELECT bn.id_bn, bn.hoten, bn.gioitinh, bn.ngaysinh, " +
        "bn.sdt, bn.diachi, bn.nghenghiep, bn.dantoc, " +
        "bn.nguoithan, bn.ghichu, bn.ngaydangky, " +
        "bs.hoten AS bacsi, pk.id_pk,pk.loaikham,pk.phongkham,pk.hoten_nv,pk.sdt_nv,pk.trangthai " +   // 👈 CỘT ẨN
        "FROM benhnhan bn " +
        "LEFT JOIN phieukham pk ON bn.id_bn = pk.id_bn " +
        "LEFT JOIN bacsi bs ON pk.id_bs = bs.id_bs " +
        "ORDER BY bn.id_bn DESC";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            list.add(new Object[]{
                rs.getInt("id_bn"),
                rs.getString("hoten"),
                rs.getString("gioitinh"),
                rs.getDate("ngaysinh"),
                rs.getString("sdt"),
                rs.getString("diachi"),
                rs.getString("nghenghiep"),
                rs.getString("dantoc"),
                rs.getString("nguoithan"),
                rs.getString("ghichu"),
                rs.getDate("ngaydangky"),
                rs.getString("bacsi"),
                rs.getInt("id_pk"),
                rs.getString("loaikham"),
                rs.getString("phongkham"),
                rs.getString("trangthai"),
                rs.getString("hoten_nv"),
            });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
    
      public BenhNhan getBenhNhanBySdt(String sdt) {
        String sql = "SELECT * FROM BenhNhan WHERE sdt = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSet(rs);  // map từ ResultSet → BenhNhan object
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
       private BenhNhan mapResultSet(ResultSet rs) throws SQLException {
        BenhNhan bn = new BenhNhan();
        bn.setId_bn(rs.getInt("id_bn"));
        bn.setHoten(rs.getString("hoten"));
        bn.setGioitinh(rs.getString("gioitinh"));
        bn.setNgaysinh(rs.getDate("ngaysinh"));
        bn.setSdt(rs.getString("sdt"));
        bn.setDiachi(rs.getString("diachi"));
        bn.setNghenghiep(rs.getString("nghenghiep"));
        bn.setDantoc(rs.getString("dantoc"));
        bn.setNguoithan(rs.getString("nguoithan"));
        bn.setGhichu(rs.getString("ghichu"));
        bn.setNgaydangky(rs.getDate("ngaydangky"));
        return bn;
    }
}
