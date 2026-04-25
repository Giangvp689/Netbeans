/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAO.DonThuocDAO;
import Model.Entity.DonThuoc;
import Model.DBConnection;
import java.math.BigDecimal;

import java.sql.*;
import java.util.List;

public class DonThuocController {

    private DonThuocDAO dao = new DonThuocDAO();

    public void themThuocVaoDon(
        int idBa,
        int idThuoc,
        int soLuong,
        String lieuDung,
        BigDecimal donGia
    ) {
        int idDt = dao.getOrCreateDonThuoc(idBa);
        dao.insertChiTiet(idDt, idThuoc, soLuong, lieuDung, donGia);
    }

    public int getDonThuocByBenhAn(int idBa) {
        return dao.getDonThuocByBenhAn(idBa);
    }
    


public void luuDonThuoc(
        int idDt,
        int idBa,
        int idBs,
        String ghiChu
) {
    dao.updateDonThuoc(idDt, idBa, idBs, ghiChu);
}
public String getGhiChuDonThuoc(int idDt) {
    return dao.getGhiChuByDonThuoc(idDt);
}

}
