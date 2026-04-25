/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAO.BacSiDAO;
import Model.Entity.BacSi;
import java.util.List;
import Model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author giang
 */
public class BacSiController {
    private BacSiDAO bacSiDAO;
    
     public ResultSet getAllBacSiResultSet() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM BacSi";
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public BacSiController() {
        bacSiDAO = new BacSiDAO();
    }

    public List<BacSi> getAllBacSi() {
        return bacSiDAO.getAllBacSi();
    }

    public boolean addBacSi(BacSi bs) {
        return bacSiDAO.insertBacSi(bs);
    }

    public boolean updateBacSi(BacSi bs) {
        return bacSiDAO.updateBacSi(bs);
    }

    public boolean deleteBacSi(int id) {
        return bacSiDAO.deleteBacSi(id);
    }

    public List<BacSi> searchBacSi(String keyword) {
        return bacSiDAO.searchBacSi(keyword);
    }
    public int addBacSiReturnID(BacSi bs) {
    return bacSiDAO.insertBacSiReturnID(bs);
}
    public BacSi getBacSiById(int id) {
    return bacSiDAO.getBacSiById(id);
}

public BacSi getBacSiByTen(String ten) {
    return bacSiDAO.getBacSiByTen(ten);
}

   

}