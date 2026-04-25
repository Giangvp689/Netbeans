package Controller;

import Model.DAO.NhanVienDAO;
import Model.Entity.NhanVien;
import java.util.List;

public class NhanVienController {

    private NhanVienDAO dao;

    public NhanVienController() {
        dao = new NhanVienDAO();
    }

    public List<NhanVien> getAllNhanVien() {
        return dao.getAllNhanVien();
    }

    public boolean addNhanVien(NhanVien nv) {
        return dao.insertNhanVien(nv);
    }

    public boolean updateNhanVien(NhanVien nv) {
        return dao.updateNhanVien(nv);
    }

    public boolean deleteNhanVien(int id_nv) {
        return dao.deleteNhanVien(id_nv);
    }

    public List<NhanVien> searchNhanVien(String keyword) {
        return dao.searchNhanVien(keyword);
    }
   

}
