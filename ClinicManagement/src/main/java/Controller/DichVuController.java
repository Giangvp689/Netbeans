/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAO.DichVuDAO;
import Model.Entity.DichVu;
import Model.DBConnection;

import java.sql.*;
import java.util.List;

/**
 *
 * @author giang
 */
public class DichVuController {

    private DichVuDAO dao;

    public DichVuController() {
        dao = new DichVuDAO();
    }

    public ResultSet getAllDichVuResultSet() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DichVu ORDER BY id_dv";
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DichVu> getAllDichVu() {
        return dao.getAllDichVu();
    }

    public boolean addDichVu(DichVu dv) {
        return dao.insertDichVu(dv);
    }

    public boolean updateDichVu(DichVu dv) {
        return dao.updateDichVu(dv);
    }

    public boolean deleteDichVu(int id_dv) {
        return dao.deleteDichVu(id_dv);
    }

    public List<DichVu> searchDichVu(String keyword) {
        return dao.searchDichVu(keyword);
    }

    public DichVu getDichVuById(int id_dv) {
        return dao.getDichVuById(id_dv);
    }
}
