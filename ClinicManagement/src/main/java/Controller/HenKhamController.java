/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAO.HenKhamDAO;
import Model.Entity.HenKham;
import Model.DBConnection;

import java.sql.*;
import java.util.List;

public class HenKhamController {

    private HenKhamDAO dao;

    public HenKhamController() {
        dao = new HenKhamDAO();
    }

   
    public ResultSet getAllHenKhamResultSet() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM HenKham ORDER BY ngayhen DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<HenKham> getAllHenKham() {
        return dao.getAllHenKham();
    }

    public boolean addHenKham(HenKham hk) {
        return dao.insertHenKham(hk);
    }

    public boolean updateHenKham(HenKham hk) {
        return dao.updateHenKham(hk);
    }

    public boolean deleteHenKham(int id_hen) {
        return dao.deleteHenKham(id_hen);
    }

    public List<HenKham> getHenKhamByBenhNhan(int id_bn) {
        return dao.getHenKhamByBenhNhan(id_bn);
    }

    public List<HenKham> getHenKhamByBacSi(int id_bs) {
        return dao.getHenKhamByBacSi(id_bs);
    }
}
