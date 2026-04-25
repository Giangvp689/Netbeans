/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author giang
 */
public class DBConnect {
    private static final String url=
            "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=QLBH;encrypt=false;trustServerCertificate=true;";
    private static final String User="sa";
    private static final String Pass="1234";
    
    public static Connection getConnect() throws SQLException{
        Connection conn=null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn= DriverManager.getConnection(url,User,Pass);
            System.out.println("Kết nối thành công");
        }catch(ClassNotFoundException e){
            System.err.println("Không tìm thấy driver");
            e.printStackTrace();
        }catch(SQLException s){
            System.err.println("Lỗi kết nối SQL "+s.getMessage());
            s.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args) throws SQLException {
        Connection test =getConnect();
        if(test!=null){
            try{
                test.close();
                System.out.println("Đã đóng kết nối");
            } catch(SQLException s){
                s.printStackTrace();
            }
        }
    }
}
