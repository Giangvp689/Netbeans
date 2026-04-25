/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SanPhamDAO;
import Entity.SanPham;
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
public class SanPhamController {
    private SanPhamDAO Sanphamdao;

    public SanPhamController() {
        Sanphamdao=new SanPhamDAO();
    }
    public ResultSet gelAll(){
        try{
            Connection conn=DBConnect.getConnect();
            String sql="SELECT * FROM SanPham ORDER BY masp DESC";
            PreparedStatement ps=conn.prepareStatement(sql);
            return ps.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<SanPham> getAllSp(){
        return Sanphamdao.getAll();
    }
    public boolean addsp(SanPham sp){
        return Sanphamdao.addSP(sp);
    }
    public boolean update(SanPham sp){
        return Sanphamdao.update(sp);
    }
    public boolean delete(int masp){
        return Sanphamdao.delete(masp);
    }
    public List<SanPham> search(String keyw){
        return Sanphamdao.search(keyw);
    }
    
    
    
}
