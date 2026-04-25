/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAO.ChiTietDonThuocDAO;
import Model.DAO.DonThuocDAO;
import Model.Entity.ChiTietDonThuoc;
import Model.DBConnection;
import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author giang
 */
public class ChiTietDonThuocController {

    private ChiTietDonThuocDAO chiTietDAO;
    private DonThuocDAO donThuocdao=new DonThuocDAO();

    public ChiTietDonThuocController() {
        chiTietDAO = new ChiTietDonThuocDAO();
    }

    public ResultSet getChiTietByDonThuocResultSet(int id_dt) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM ChiTiet_DonThuoc WHERE id_dt = ? ORDER BY id_ctdt";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_dt);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

public void themThuocVaoDon(
            int idDonThuoc,
            int idThuoc,
            int soLuong,
            String lieuDung,
            BigDecimal donGia
    ) throws Exception {

        if (soLuong <= 0) {
            throw new Exception("Số lượng phải > 0");
        }

        if (lieuDung == null || lieuDung.isEmpty()) {
            throw new Exception("Chưa nhập liều dùng");
        }

        try {
            chiTietDAO.insertChiTietDon(
                idDonThuoc,
                idThuoc,
                soLuong,
                lieuDung,
                donGia
            );
        } catch (SQLException e) {

            // Lỗi từ TRIGGER
            if (e.getMessage().contains("Không đủ số lượng thuốc")) {
                throw new Exception("❌ Không đủ thuốc trong kho");
            }

            throw new Exception("Lỗi thêm thuốc: " + e.getMessage());
        }
    }

    public boolean xoaChiTietDonThuoc(int id_ctdt) {
        return chiTietDAO.delete(id_ctdt);
    }
}
