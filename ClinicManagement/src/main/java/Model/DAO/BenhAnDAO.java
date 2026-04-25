package Model.DAO;

import Model.DBConnection;
import Model.Entity.BenhAn;
import java.sql.*;
import java.util.*;

/**
 * DAO cho bảng BenhAn
 * CRUD + tìm kiếm + lọc theo bác sĩ hoặc bệnh nhân
 * @author giang
 */
public class BenhAnDAO {

    // 1️⃣ Lấy danh sách tất cả bệnh án
    public List<BenhAn> getAllBenhAn() {
        List<BenhAn> list = new ArrayList<>();
        String sql = "SELECT * FROM BenhAn ORDER BY ngaykhambenhan DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapResultSet(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2️⃣ Thêm bệnh án mới
    public boolean insertBenhAn(BenhAn ba) {
        String sql = """
            INSERT INTO BenhAn(id_pk, id_bn, id_bs, id_nv_lap, ngaykhambenhan,  khamlam_sang, benhkemtheo,
                               nhietdo, cannang, chieucao, huyetap, nhiptho, nhiptim, diungthuoc,
                               ma_icd10, chandoan, ketluan, hen_kham, ngayhenkhambit, ghichu)
            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (ba.getId_pk() != null) ps.setInt(1, ba.getId_pk()); else ps.setNull(1, Types.INTEGER);
            ps.setInt(2, ba.getId_bn());
            ps.setInt(3, ba.getId_bs());
            if (ba.getId_nv_lap() != null) ps.setInt(4, ba.getId_nv_lap()); else ps.setNull(4, Types.INTEGER);
            ps.setDate(5, ba.getNgaykhambenhan());
            ps.setString(7, ba.getKhamlam_sang());
            ps.setString(8, ba.getBenhkemtheo());
            if (ba.getNhietdo() != null) ps.setDouble(9, ba.getNhietdo()); else ps.setNull(9, Types.DECIMAL);
            if (ba.getCannang() != null) ps.setDouble(10, ba.getCannang()); else ps.setNull(10, Types.DECIMAL);
            if (ba.getChieucao() != null) ps.setDouble(11, ba.getChieucao()); else ps.setNull(11, Types.DECIMAL);
            ps.setString(12, ba.getHuyetap());
            ps.setString(13, ba.getNhiptho());
            ps.setString(14, ba.getNhiptim());
            ps.setString(15, ba.getDiungthuoc());
            ps.setString(16, ba.getMa_icd10());
            ps.setString(17, ba.getChandoan());
            ps.setString(18, ba.getKetluan());
            ps.setBoolean(19, ba.isHen_kham());
            ps.setDate(20, ba.getNgayhenkhambit());
            ps.setString(21, ba.getGhichu());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3️⃣ Cập nhật bệnh án
public boolean updateBenhAn(BenhAn ba) {
    String sql = """
        UPDATE BenhAn SET id_pk=?, id_bn=?, id_bs=?, id_nv_lap=?, ngaykhambenhan=?, khamlam_sang=?,
            benhkemtheo=?, nhietdo=?, cannang=?, chieucao=?, huyetap=?, nhiptho=?, nhiptim=?, diungthuoc=?,
            ma_icd10=?, chandoan=?, ketluan=?, hen_kham=?, ngayhenkhambit=?, ghichu=? WHERE id_ba=?
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Có 20 tham số trước WHERE + 1 sau WHERE = 21 tham số
        
        if (ba.getId_pk() != null) ps.setInt(1, ba.getId_pk()); else ps.setNull(1, Types.INTEGER);
        ps.setInt(2, ba.getId_bn());
        ps.setInt(3, ba.getId_bs());
        if (ba.getId_nv_lap() != null) ps.setInt(4, ba.getId_nv_lap()); else ps.setNull(4, Types.INTEGER);
        ps.setDate(5, ba.getNgaykhambenhan());
        ps.setString(6, ba.getKhamlam_sang());      // 🔥 SỬA: index 6 (không phải 7)
        ps.setString(7, ba.getBenhkemtheo());       // 🔥 SỬA: index 7 (không phải 8)
        if (ba.getNhietdo() != null) ps.setDouble(8, ba.getNhietdo()); else ps.setNull(8, Types.DECIMAL);
        if (ba.getCannang() != null) ps.setDouble(9, ba.getCannang()); else ps.setNull(9, Types.DECIMAL);
        if (ba.getChieucao() != null) ps.setDouble(10, ba.getChieucao()); else ps.setNull(10, Types.DECIMAL);
        ps.setString(11, ba.getHuyetap());
        ps.setString(12, ba.getNhiptho());
        ps.setString(13, ba.getNhiptim());
        ps.setString(14, ba.getDiungthuoc());
        ps.setString(15, ba.getMa_icd10());
        ps.setString(16, ba.getChandoan());
        ps.setString(17, ba.getKetluan());
        ps.setBoolean(18, ba.isHen_kham());
        ps.setDate(19, ba.getNgayhenkhambit());
        ps.setString(20, ba.getGhichu());
        ps.setInt(21, ba.getId_ba());  // 🔥 SỬA: index 21 (không phải 22)

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
    // 4️⃣ Xóa bệnh án
    public boolean deleteBenhAn(int id_ba) {
        String sql = "DELETE FROM BenhAn WHERE id_ba=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_ba);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Tìm theo bệnh nhân
    public List<BenhAn> getBenhAnByBenhNhan(int id_bn) {
        List<BenhAn> list = new ArrayList<>();
        String sql = "SELECT * FROM BenhAn WHERE id_bn=? ORDER BY ngaykhambenhan DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_bn);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 6️⃣ Tìm theo bác sĩ
    public List<BenhAn> getBenhAnByBacSi(int id_bs) {
        List<BenhAn> list = new ArrayList<>();
        String sql = "SELECT * FROM BenhAn WHERE id_bs=? ORDER BY ngaykhambenhan DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id_bs);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapResultSet(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private BenhAn mapResultSet(ResultSet rs) throws SQLException {
        BenhAn ba = new BenhAn();
        ba.setId_ba(rs.getInt("id_ba"));
        ba.setId_pk(rs.getObject("id_pk") != null ? rs.getInt("id_pk") : null);
        ba.setId_bn(rs.getInt("id_bn"));
        ba.setId_bs(rs.getInt("id_bs"));
        ba.setId_nv_lap(rs.getObject("id_nv_lap") != null ? rs.getInt("id_nv_lap") : null);
        ba.setNgaykhambenhan(rs.getDate("ngaykhambenhan"));
        ba.setKhamlam_sang(rs.getString("khamlam_sang"));
        ba.setBenhkemtheo(rs.getString("benhkemtheo"));
        ba.setNhietdo(rs.getObject("nhietdo") != null ? rs.getDouble("nhietdo") : null);
        ba.setCannang(rs.getObject("cannang") != null ? rs.getDouble("cannang") : null);
        ba.setChieucao(rs.getObject("chieucao") != null ? rs.getDouble("chieucao") : null);
        ba.setHuyetap(rs.getString("huyetap"));
        ba.setNhiptho(rs.getString("nhiptho"));
        ba.setNhiptim(rs.getString("nhiptim"));
        ba.setDiungthuoc(rs.getString("diungthuoc"));
        ba.setMa_icd10(rs.getString("ma_icd10"));
        ba.setChandoan(rs.getString("chandoan"));
        ba.setKetluan(rs.getString("ketluan"));
        ba.setHen_kham(rs.getBoolean("hen_kham"));
        ba.setNgayhenkhambit(rs.getDate("ngayhenkhambit"));
        ba.setGhichu(rs.getString("ghichu"));
        return ba;
    }
    
    
        // 7️⃣ Lấy danh sách ngày khám theo bệnh nhân (cho listDay)
    public List<java.sql.Date> getNgayKhamByBenhNhan(int id_bn) {
        List<java.sql.Date> list = new ArrayList<>();
        String sql = """
            SELECT ngaykhambenhan 
            FROM BenhAn 
            WHERE id_bn = ?
            ORDER BY ngaykhambenhan DESC
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_bn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getDate("ngaykhambenhan"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
// 8️⃣ Lấy bệnh án theo bệnh nhân + ngày khám
    public BenhAn getBenhAnByBenhNhanAndNgay(int id_bn, java.sql.Date ngay) {
    String sql = """
        SELECT * FROM BenhAn 
        WHERE id_bn = ? AND ngaykhambenhan = ?
    """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id_bn);
        ps.setDate(2, new java.sql.Date(ngay.getTime()));
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return mapResultSet(rs);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

}
