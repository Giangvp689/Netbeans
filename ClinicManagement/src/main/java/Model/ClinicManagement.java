package Model;

import View.Login_Form;
import View.MainFrame;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import com.formdev.flatlaf.FlatLightLaf;  // ⚡ Thêm import này

public class ClinicManagement {
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
            new Login_Form().setVisible(true);
        });
    }
}
