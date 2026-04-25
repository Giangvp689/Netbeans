/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import UI.MainForm;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author giang
 */
public class Quanlybanhang {
      public static void main(String[] args) {
         try {
            // ⚙️ Cài đặt giao diện FlatLaf (sáng)
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Nếu muốn giao diện tối thì thay bằng:
            // UIManager.setLookAndFeel(new FlatDarkLaf());
            // Có thể tùy chỉnh bo góc, font luôn ở đây
            UIManager.put("Button.arc", 20);
            UIManager.put("Component.arc", 15);
            UIManager.put("TextComponent.arc", 15);
         } catch (Exception ex) {
            System.err.println("❌ Không thể khởi tạo FlatLaf UI!");
            ex.printStackTrace();
        }

        // ⚡ Chạy giao diện chính
        SwingUtilities.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }
}
