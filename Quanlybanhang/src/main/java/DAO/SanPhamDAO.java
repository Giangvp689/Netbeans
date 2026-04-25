/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhachHang;
import Entity.SanPham;
import Model.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giang
 */
public class SanPhamDAO {
    public List<SanPham> getAll() {
        List<SanPham> list=new ArrayList<>();
        String sql="SELECT * FROM SanPham";
        try(
            Connection conn=DBConnect.getConnect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery()){
            while(rs.next()){
                SanPham sp=new SanPham();
                sp.setMasp(rs.getInt("masp"));
                sp.setTensp(rs.getString("tensp"));
                sp.setDvtinh(rs.getString("dvtinh"));
                sp.setLoaisp(rs.getString("loaisp"));
                sp.setDongia(rs.getBigDecimal("dongia"));
                sp.setSoluong(rs.getInt("soluong"));
                list.add(sp);
             }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean addSP(SanPham sp){
        String sql="INSERT INTO SanPham (tensp,dvtinh,loaisp,dongia,soluong)"
                + "VALUES(?,?,?,?,?)";
        try (Connection conn=DBConnect.getConnect();
             PreparedStatement ps=conn.prepareStatement(sql);){
            ps.setString(1, sp.getTensp());
            ps.setString(2, sp.getDvtinh());
            ps.setString(3, sp.getLoaisp());
            ps.setBigDecimal(4, sp.getDongia());
            ps.setInt(5,sp.getSoluong());
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(SanPham sp) {
        String sql="UPDATE SanPham SET tensp=?, dvtinh=?, loaisp=?, dongia=?, soluong=? "
                + "WHERE masp=?";
        try(Connection conn=DBConnect.getConnect();
                PreparedStatement ps=conn.prepareStatement(sql)){
           ps.setString(1, sp.getTensp());
           ps.setString(2, sp.getDvtinh());
           ps.setString(3, sp.getLoaisp());
           ps.setBigDecimal(4, sp.getDongia());
           ps.setInt(5, sp.getSoluong());
           ps.setInt(6, sp.getMasp());
           return ps.executeUpdate()>0;
        } catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(int sp) {
        String sql="DELETE FROM SanPham WHERE masp=?";
        try(Connection conn=DBConnect.getConnect();
                PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1, sp);
            return ps.executeUpdate()>0;  
        }catch(Exception e ){
            e.printStackTrace();
        }
        return false;
    }
    
    
    public List<SanPham> search(String key) {
        List<SanPham> list=new ArrayList<>();
        String sql="SELECT * FROM SanPham WHERE masp LIKE ? OR tensp LIKE ? OR loaisp LIKE ?";
        try(Connection conn=DBConnect.getConnect();
                PreparedStatement ps=conn.prepareStatement(sql)){
            String keyw= "%"+key+"%";
            ps.setString(1, keyw);
            ps.setString(2, keyw);
            ps.setString(3, keyw);
            
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                SanPham sp=new SanPham();
                
                sp.setMasp(rs.getInt("masp"));
                sp.setTensp(rs.getString("tensp"));
                sp.setDvtinh(rs.getString("dvtinh"));
                sp.setLoaisp(rs.getString("loaisp"));
                sp.setDongia(rs.getBigDecimal("dongia"));
                sp.setSoluong(rs.getInt("soluong"));
                list.add(sp);
            }
        }catch(Exception    e){
            e.printStackTrace();
        }
        return list;
    }
    
    
    
    
    
    
}
