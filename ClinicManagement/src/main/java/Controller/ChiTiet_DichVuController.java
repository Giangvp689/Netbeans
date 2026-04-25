/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAO.BenhAnDAO;
import Model.DAO.ChiTiet_DichVuDAO;
import Model.Entity.ChiTiet_DichVu;
import Model.DBConnection;
import Model.Entity.DichVu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author giang
 */
public class ChiTiet_DichVuController {

    private ChiTiet_DichVuDAO dichVuDAO;

    public ChiTiet_DichVuController() {
        dichVuDAO = new ChiTiet_DichVuDAO();
    }

    public ResultSet getDichVuByBenhAnResultSet(int id_ba) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = """
                SELECT ctdv.*, dv.tendv
                FROM ChiTiet_DichVu ctdv
                JOIN DichVu dv ON ctdv.id_dv = dv.id_dv
                WHERE ctdv.id_ba = ?
                ORDER BY ctdv.id_ctdv
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_ba);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChiTiet_DichVu> getDichVuByBenhAn(int id_ba) {
        return dichVuDAO.getByBenhAn(id_ba);
    }

    

    public boolean updateDichVu(ChiTiet_DichVu ctdv) {
        return dichVuDAO.updateChiTietDichVu(ctdv);
    }

    public boolean deleteDichVu(int id_ctdv) {
        return dichVuDAO.deleteChiTietDichVu(id_ctdv);
    }

    public BigDecimal tinhTongTienDichVu(int id_ba) {
        return dichVuDAO.tinhTongTienDichVu(id_ba);
    }


}
