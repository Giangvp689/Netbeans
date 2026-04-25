package Controller;

import Model.DAO.LichLamViecDAO;
import Model.Entity.LichLamViec;
import java.sql.Date;

import java.util.List;


public class LichLamViecController {

    private LichLamViecDAO dao;

    public LichLamViecController() {
        dao = new LichLamViecDAO();
    }

   
    public List<LichLamViec> getAllLichLamViec() {
        return dao.getAllLichLamViec();
    }

 
    public boolean addLichLamViec(LichLamViec llv) {
        return dao.insertLichLamViec(llv);
    }
    
    public boolean updateLichLamViec(LichLamViec llv) {
        return dao.updateLichLamViec(llv);
    }

    public boolean deleteLichLamViec(int id_llv) {
        return dao.deleteLichLamViec(id_llv);
    }

    public List<LichLamViec> searchLichLamViec(String keyword) {
        return dao.searchLichLamViec(keyword);
    }

    public List<LichLamViec> getLichLamViecByBacSi(int id_bs) {
        return dao.getLichLamViecByBacSi(id_bs);
    }
    public boolean deleteLlvBS(int idBS){
        return dao.deleteLichByBacSi(idBS);
    }
    
     public List<Object[]> xemLichLamViec(int idBs, Date ngay) {
        return dao.getLichLamViec(idBs, ngay);
    }
      public List<LichLamViec> getLichByBacSiNgay(int idBs, Date ngay) {
        return dao.findByBacSiAndNgay(idBs, ngay);
    }
}
