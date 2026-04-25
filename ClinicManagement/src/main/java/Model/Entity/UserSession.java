/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entity;

/**
 *
 * @author giang
 */
public class UserSession {
    private static String username;
    private static String role;
    private static String hoTen;

    private UserSession() {
        // private constructor
    }

    public static void setUser(String u, String r, String h) {
        username = u;
        role = r;
        hoTen = h;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static String getHoTen() {
        return hoTen;
    }

    public static boolean isLoggedIn() {
        return username != null;
    }

    public static void clear() {
        username = null;
        role = null;
        hoTen = null;
    }   
}
