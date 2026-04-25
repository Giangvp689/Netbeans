/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAO.ChiTiet_HoaDonDAO;
import Model.Entity.ChiTiet_HoaDon;
import Model.DBConnection;

import java.sql.*;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * @author giang
 */
public class ChiTiet_HoaDonController {

    private ChiTiet_HoaDonDAO dao;

    public ChiTiet_HoaDonController() {
        dao = new ChiTiet_HoaDonDAO();
    }

    public ResultSet getChiTietByHoaDonResultSet(int id_hd) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = """
                SELECT cthd.*, 
                       CASE 
                           WHEN cthd.loai_item = 'THUOC' THEN t.tenthuoc
                           WHEN cthd.loai_item = 'DICHVU' THEN dv.tendv
                           ELSE NULL
                       END AS tenItem
                FROM ChiTiet_HoaDon cthd
                LEFT JOIN Thuoc t ON (cthd.loai_item='THUOC' AND cthd.id_ref=t.id_thuoc)
                LEFT JOIN DichVu dv ON (cthd.loai_item='DICHVU' AND cthd.id_ref=dv.id_dv)
                WHERE cthd.id_hd = ?
                ORDER BY cthd.id_cthd
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_hd);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
      public List<ChiTiet_HoaDon> getChiTietByHD(int id_hd) {
    // SAI: throw new UnsupportedOperationException("Not supported yet.");
    
    // ĐÚNG: Gọi hàm từ DAO mà bạn vừa viết
    return dao.getByHoaDonId(id_hd); 
}

}
