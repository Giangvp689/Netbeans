/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhachHang;
import Model.DBConnect;
import java.sql.*;
import java.util.*;
/**
 *
 * @author giang
 */
public class KhachHangDAO {
    public List<KhachHang> getALl(){
        List<KhachHang> list =new ArrayList<>();
        String sql= "SELECT * FROM KhachHang";
        try(
            Connection conn = DBConnect.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                KhachHang kh=new KhachHang();
                kh.setMakh(rs.getInt("makh"));
                kh.setTenkh(rs.getString("tenkh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiachi(rs.getString("diachi"));
                kh.setGioitinh(rs.getString("gioitinh"));
                kh.setNgaysinh(rs.getDate("ngaysinh"));
                kh.setGhichu(rs.getString("ghichu"));
                list.add(kh);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean addKh(KhachHang kh) throws SQLException{
        String sql="INSERT INTO KhachHang(tenkh,sdt,diachi,gioitinh,ngaysinh,ghichu)" +"VALUES(?,?,?,?,?,?)";
        try(Connection conn= DBConnect.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, kh.getTenkh());
            ps.setString(2,kh.getSdt());
            ps.setString(3, kh.getDiachi());
            ps.setString(4, kh.getGioitinh());
            ps.setDate(5, kh.getNgaysinh() != null ? new java.sql.Date(kh.getNgaysinh().getTime()) : null);
            ps.setString(6, kh.getGhichu());
            
            return ps.executeUpdate()>0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateKh(KhachHang kh) throws SQLException{
        String sql="UPDATE KhachHang SET tenkh=?, sdt=?, diachi=?, gioitinh=?, ngaysinh=?, ghichu=? WHERE makh=?";
        try(Connection conn=DBConnect.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, kh.getTenkh());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getDiachi());
            ps.setString(4, kh.getGioitinh());
            ps.setDate(5, kh.getNgaysinh() != null ? new java.sql.Date(kh.getNgaysinh().getTime()):null);
            ps.setString(6, kh.getGhichu());
            ps.setInt(7, kh.getMakh());
            return ps.executeUpdate()>0;
        } catch(Exception  e){
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean deleteKh(int makh) throws SQLException{
        String sql="DELETE FROM KhachHang WHERE makh=?";
        try(Connection conn=DBConnect.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){
            
            ps.setInt(1, makh);
            return ps.executeUpdate()>0;
        }catch(Exception    e){
            e.printStackTrace();
        }
        return false;
    }
    
    public List<KhachHang> searchKh(String keyword) throws SQLException {
        List<KhachHang> list= new ArrayList<>();
        String sql="SELECT * FROM KhachHang WHERE makh LIKE ? OR tenkh LIKE ? OR sdt LIKE ? OR diachi LIKE ?";
        try(Connection conn=DBConnect.getConnect();
            PreparedStatement ps=conn.prepareStatement(sql)){
            String key ="%"+keyword+"%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ps.setString(4, key);
            
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                KhachHang kh=new KhachHang();
                kh.setMakh(rs.getInt("makh"));
                kh.setTenkh(rs.getString("tenkh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiachi(rs.getString("diachi"));
                kh.setGioitinh(rs.getString("gioitinh"));
                kh.setNgaysinh(rs.getDate("ngaysinh"));
                kh.setGhichu(rs.getString("ghichu"));
                
                list.add(kh);
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
