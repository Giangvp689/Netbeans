/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAO.BenhAnDAO;
import Model.DAO.ChiTiet_DichVuDAO;
import Model.DAO.DichVuDAO;
import Model.Entity.BenhAn;
import Model.DBConnection;
import Model.Entity.ChiTiet_DichVu;
import Model.Entity.DichVu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author giang
 */
public class BenhAnController {

    private BenhAnDAO benhAnDAO;
    private ChiTiet_DichVuDAO ChitietdichvuDAO =new ChiTiet_DichVuDAO();
    private DichVuDAO dichvuDAO=new DichVuDAO();
    public BenhAnController() {
        benhAnDAO = new BenhAnDAO();
    }

    public ResultSet getAllBenhAnResultSet() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM BenhAn ORDER BY ngaykhambenhan DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<BenhAn> getAllBenhAn() {
        return benhAnDAO.getAllBenhAn();
    }

    public boolean addBenhAn(BenhAn ba) {
        return benhAnDAO.insertBenhAn(ba);
    }

    public boolean updateBenhAn(BenhAn ba) {
        return benhAnDAO.updateBenhAn(ba);
    }

    public boolean deleteBenhAn(int id_ba) {
        return benhAnDAO.deleteBenhAn(id_ba);
    }

    public List<BenhAn> getBenhAnByBenhNhan(int id_bn) {
        return benhAnDAO.getBenhAnByBenhNhan(id_bn);
    }

    public List<BenhAn> getBenhAnByBacSi(int id_bs) {
        return benhAnDAO.getBenhAnByBacSi(id_bs);
    }
    
     public BenhAn getBenhAnByBenhNhanAndNgay(int idBn, java.sql.Date ngay) {
        return benhAnDAO.getBenhAnByBenhNhanAndNgay(idBn, ngay);
    }

    public List<java.sql.Date> getNgayKhamByBenhNhan(int idBn) {
        return benhAnDAO.getNgayKhamByBenhNhan(idBn);
    }
    
    
   public boolean saveDichVuChoBenhAn(
    int idBa,
    List<String> listTenDichVu
) {
    if (idBa <= 0) return false;

    try {
        // Xóa dịch vụ cũ
        ChitietdichvuDAO.deleteChiTietDichVuByBenhAn(idBa);

        // Thêm dịch vụ mới
        for (String tenDv : listTenDichVu) {

            DichVu dv = dichvuDAO.getDichVuByTen(tenDv);
            if (dv == null) continue;

            ChiTiet_DichVu ct = new ChiTiet_DichVu();
            ct.setId_ba(idBa);
            ct.setId_dv(dv.getId_dv());
            ct.setSoluong(1);                 // ✅ mặc định 1
            ct.setDongia(dv.getDongia());

            ChitietdichvuDAO.insertChiTietDichVu(ct);
        }

        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public boolean deleteBenhAnVaDichVu(int idBa) {
    if (idBa <= 0) return false;

    try {
        // 1️⃣ Xóa dịch vụ trước
        ChitietdichvuDAO.deleteChiTietDichVuByBenhAn(idBa);

        // 2️⃣ Xóa bệnh án
        return benhAnDAO.deleteBenhAn(idBa);

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


}
