/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.KhachHangDAO;
import Entity.KhachHang;
import Model.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author giang
 */
public class KhachHangController {
    private KhachHangDAO KhachhangDao;

    public KhachHangController(){
        KhachhangDao=new KhachHangDAO();
    }
    public ResultSet getAll() {
        try{
            Connection conn=DBConnect.getConnect();
            String sql="SELECT * FROM KhachHang ORDER BY makh DESC";
            PreparedStatement ps= conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<KhachHang> getAllKhachHang(){
        return KhachhangDao.getALl();
    }
    public boolean add(KhachHang kh) throws SQLException{
        return KhachhangDao.addKh(kh);
    }
    public boolean update(KhachHang kh) throws SQLException{
        return KhachhangDao.updateKh(kh);
    }
    public boolean delete(int makh) throws SQLException{
        return KhachhangDao.deleteKh(makh);
    }
    public List<KhachHang> getALl(String keyword) throws SQLException{
        return KhachhangDao.searchKh(keyword);
    }
}
