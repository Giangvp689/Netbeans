package Controller;

import Model.DAO.PhieuKhamDAO;
import Model.Entity.PhieuKham;
import java.util.List;

public class PhieuKhamController {

    private PhieuKhamDAO dao;

    public PhieuKhamController() {
        dao = new PhieuKhamDAO();
    }

    public List<PhieuKham> getAllPhieuKham() {
        return dao.getAllPhieuKham();
    }

    public boolean addPhieuKham(PhieuKham pk) {
        return dao.insertPhieuKham(pk);
    }

    public boolean updatePhieuKham(PhieuKham pk) {
        return dao.updatePhieuKham(pk);
    }

    public boolean deletePhieuKham(int id_pk) {
        return dao.deletePhieuKham(id_pk);
    }

    public List<PhieuKham> searchPhieuKham(String keyword) {
        return dao.searchPhieuKham(keyword);
    }

    public List<PhieuKham> getPhieuKhamByBacSi(int id_bs) {
        return dao.getPhieuKhamByBacSi(id_bs);
    }

    public List<PhieuKham> getPhieuKhamByTrangThai(String trangthai) {
        return dao.getPhieuKhamByTrangThai(trangthai);
    }
}
