/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.DAO.BenhNhanDAO;
import Model.Entity.BenhNhan;
import java.util.*;

public class BenhNhanController {

    private BenhNhanDAO benhNhanDAO;

    public BenhNhanController() {
        benhNhanDAO = new BenhNhanDAO();
    }

    public List<BenhNhan> getAllBenhNhan() {
        return benhNhanDAO.getAllBenhNhan();
    }

    public int insertBenhNhan(BenhNhan bn) {
        return benhNhanDAO.insertBenhNhan(bn);
    }

    public boolean updateBenhNhan(BenhNhan bn) {
        return benhNhanDAO.updateBenhNhan(bn);
    }

    public boolean deleteBenhNhan(int id_bn) {
        return benhNhanDAO.deleteBenhNhan(id_bn);
    }

    public List<BenhNhan> searchBenhNhan(String keyword) {
        return benhNhanDAO.searchBenhNhan(keyword);
    }
     public List<Object[]> getBenhNhanTableData1() {
    return benhNhanDAO.getBenhNhanWithBacSi();
    }
    public List<Object[]> getBenhNhanTableData() {
    return benhNhanDAO.getBenhNhanWithBacSi();
    }
     public BenhNhan getBenhNhanBySdt(String sdt) {
        return benhNhanDAO.getBenhNhanBySdt(sdt);  // Gọi xuống DAO
    }
    
}