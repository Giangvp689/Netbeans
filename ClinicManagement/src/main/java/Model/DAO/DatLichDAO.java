/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.Entity.DatLich;
import Model.DBConnection;
import Model.Entity.BenhAn;
import Model.Entity.BenhNhan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author giang
 */
public class DatLichDAO {

    private Connection conn = DBConnection.getConnection();
    
    public List<Object[]> getLichTheoNgay(int idBs, Date ngay) {
    List<Object[]> list = new ArrayList<>();

    String sql = """
        SELECT 
            dl.ngaykham,
            dl.giokham,
            ISNULL(bn.hoten, dl.hoten_nv) AS ten_bn,
            dl.trangthai,
            dl.ghichu
        FROM DatLich dl
        LEFT JOIN BenhNhan bn ON dl.id_bn = bn.id_bn
        WHERE dl.id_bs = ?
          AND dl.ngaykham = ?
        ORDER BY dl.giokham
    """;

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idBs);
        ps.setDate(2, new java.sql.Date(ngay.getTime()));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getDate("ngaykham"),
                rs.getTime("giokham"),
                rs.getString("ten_bn"),
                rs.getString("trangthai"),
                rs.getString("ghichu")
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

    public int insertAndGetId(DatLich dl) {

    String sql = """
        INSERT INTO DatLich (
            id_bn, hoten_nv, sdt_nv, gioitinh_nv, ngaysinh_nv,
            ghichu, id_bs, ngaykham, giokham
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement ps = conn.prepareStatement(
            sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setInt(1, dl.getId_bn());
        ps.setString(2, dl.getHoten_nv());
        ps.setString(3, dl.getSdt_nv());
        ps.setString(4, dl.getGioitinh_nv());
        ps.setDate(5, dl.getNgaysinh_nv());
        ps.setString(6, dl.getGhichu());
        ps.setInt(7, dl.getId_bs());
        ps.setDate(8, dl.getNgaykham());
        ps.setTime(9, dl.getGiokham());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // id_lich
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}


    public List<DatLich> getLichTheoNgayHt(int idBs, Date ngayKham) {

        List<DatLich> list = new ArrayList<>();

        String sql = """
            SELECT dl.*
            FROM DatLich dl
            WHERE dl.id_bs = ? AND dl.ngaykham = ?
            ORDER BY dl.giokham
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idBs);
            ps.setDate(2, ngayKham);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                DatLich dl = new DatLich();
                dl.setId_lich(rs.getInt("id_lich"));
                dl.setId_bn((Integer) rs.getObject("id_bn"));
                dl.setHoten_nv(rs.getString("hoten_nv"));
                dl.setSdt_nv(rs.getString("sdt_nv"));
                dl.setGioitinh_nv(rs.getString("gioitinh_nv"));
                dl.setNgaysinh_nv(rs.getDate("ngaysinh_nv"));
                dl.setGhichu(rs.getString("ghichu"));
                dl.setId_bs(rs.getInt("id_bs"));
                dl.setNgaykham(rs.getDate("ngaykham"));
                dl.setGiokham(rs.getTime("giokham"));
                dl.setTrangthai(rs.getString("trangthai"));
                dl.setCreated_at(rs.getTimestamp("created_at"));

                list.add(dl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // Lấy lịch theo ID
    public DatLich getById(int idLich) {

        String sql = """
            SELECT *
            FROM DatLich
            WHERE id_lich = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idLich);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                DatLich dl = new DatLich();
                dl.setId_lich(rs.getInt("id_lich"));
                dl.setHoten_nv(rs.getString("hoten_nv"));
                dl.setSdt_nv(rs.getString("sdt_nv"));
                dl.setGioitinh_nv(rs.getString("gioitinh_nv"));
                dl.setNgaysinh_nv(rs.getDate("ngaysinh_nv"));
                dl.setGhichu(rs.getString("ghichu"));
                dl.setId_bs(rs.getInt("id_bs"));
                dl.setNgaykham(rs.getDate("ngaykham"));
                dl.setGiokham(rs.getTime("giokham"));
                dl.setTrangthai(rs.getString("trangthai"));
                return dl;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update đầy đủ + Đã khám
    public boolean updateFull(DatLich dl) {

        String sql = """
            UPDATE DatLich
            SET hoten_nv = ?, sdt_nv = ?, gioitinh_nv = ?,
                ngaysinh_nv = ?, ghichu = ?,
                id_bs = ?, ngaykham = ?, giokham = ?,
                trangthai = N'Đã khám'
            WHERE id_lich = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dl.getHoten_nv());
            ps.setString(2, dl.getSdt_nv());
            ps.setString(3, dl.getGioitinh_nv());
            ps.setDate(4, dl.getNgaysinh_nv());
            ps.setString(5, dl.getGhichu());
            ps.setInt(6, dl.getId_bs());
            ps.setDate(7, dl.getNgaykham());
            ps.setTime(8, dl.getGiokham());
            ps.setInt(9, dl.getId_lich());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Chỉ update trạng thái
    public boolean updateTrangThaiDaKham(int idLich) {

        String sql = """
            UPDATE DatLich
            SET trangthai = N'Đã khám'
            WHERE id_lich = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idLich);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteDatLich(int idLich) {
    String sql = "DELETE FROM DatLich WHERE id_lich = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idLich);
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
   

public List<Object[]> getLichSapToiRaw(java.sql.Date fromDate) {

    List<Object[]> list = new ArrayList<>();

    String sql = """
        SELECT dl.id_lich,
               dl.ngaykham,
               dl.giokham,
               ISNULL(bn.hoten, dl.hoten_nv) AS hoten,
               ISNULL(bn.sdt, dl.sdt_nv) AS sdt,
               bs.hoten AS tenbacsi,
               dl.trangthai
        FROM DatLich dl
        JOIN BacSi bs ON dl.id_bs = bs.id_bs
        LEFT JOIN BenhNhan bn ON dl.id_bn = bn.id_bn
        WHERE dl.ngaykham >= ?
        ORDER BY dl.ngaykham ASC, dl.giokham ASC
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDate(1, fromDate);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Object[]{
                rs.getInt("id_lich"),
                rs.getDate("ngaykham"),
                rs.getTime("giokham"),
                rs.getString("hoten"),
                rs.getString("sdt"),
                rs.getString("tenbacsi"),
                rs.getString("trangthai")
            });
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}

}
