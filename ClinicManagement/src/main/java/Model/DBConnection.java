/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author giang
 */
public class DBConnection {
   
 private static final String URL =
        "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ClinicManagement;encrypt=false;trustServerCertificate=true;";
    private static final String USER = "sa";          // tài khoản SQL Server
    private static final String PASSWORD = "1234";   // mật khẩu SQL Server

    public static Connection getConnection() {
        Connection conn = null;
        try {
            //  Nạp driver JDBC cho SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //  Mở kết nối
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Kết nối SQL Server thành công!");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Không tìm thấy driver JDBC SQL Server!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối SQL Server: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    //test nhanh
    public static void main(String[] args) {
        Connection testConn = getConnection();
        if (testConn != null) {
            try {
                testConn.close();
                System.out.println("🔒 Đã đóng kết nối thành công.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
