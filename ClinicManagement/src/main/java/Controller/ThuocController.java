package Controller;

import Model.DAO.ThuocDAO;
import Model.Entity.Thuoc;
import java.util.List;

public class ThuocController {

    private final ThuocDAO dao = new ThuocDAO();

    public List<Thuoc> getAllThuoc() {
        return dao.getAllThuoc();
    }



 // load tất cả
    public List<Object[]> getThuocTableData() {
        return dao.getThuocTableData("");
    }

    public List<Object[]> getThuocTableData(String keyword) {
        return dao.getThuocTableData(keyword);
    }
    public boolean updateSoLuongTon(int id_thuoc, int newSoLuong) {
        return dao.updateSoLuongTon(id_thuoc, newSoLuong);
    }
    

    public Thuoc getThuocById(int id_thuoc) {
        List<Thuoc> list = dao.getAllThuoc();
        for (Thuoc t : list) {
            if (t.getId_thuoc() == id_thuoc) return t;
        }
        return null;
    }
    
     public List<Object[]> getThuocTable() {
        return dao.getThuocTableData();
    }

    // ========================
    // 2️⃣ TÌM KIẾM THUỐC
    // ========================
    public List<Object[]> searchThuoc(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getThuocTable();
        }
        return dao.getThuocTableData(keyword.trim());
    }

    public boolean addThuoc(Thuoc t) {
        if (t == null) return false;
        return dao.insertThuoc(t);
    }


    public boolean updateThuoc(Thuoc t) {
        if (t == null || t.getId_thuoc() <= 0) return false;
        return dao.updateThuoc(t);
    }


    public boolean deleteThuoc(int id_thuoc) {
        if (id_thuoc <= 0) return false;
        return dao.deleteThuoc(id_thuoc);
    }


    public boolean updateSoLuongTon1(int id_thuoc, int soLuongMoi) {
        if (id_thuoc <= 0 || soLuongMoi < 0) return false;
        return dao.updateSoLuongTon(id_thuoc, soLuongMoi);
    }
    
    public List<String> getAllLoaiThuoc() {
    return dao.getAllLoaiThuoc();
}
public List<Object[]> searchThuoc(String keyword, String loai) {
    return dao.searchThuoc(keyword, loai);
}

}

