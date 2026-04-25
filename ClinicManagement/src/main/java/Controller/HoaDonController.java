package Controller;

import Model.DAO.ChiTiet_HoaDonDAO;
import Model.DAO.HoaDonDAO;
import Model.Entity.HoaDon;
import java.math.BigDecimal;
import java.util.List;

public class HoaDonController {
    private HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private ChiTiet_HoaDonDAO cthdDAO = new ChiTiet_HoaDonDAO();

    // --- Cập nhật hàm searchHoaDon ---
    public List<HoaDon> searchHoaDon(String keyword) {
        // Gọi xuống DAO để lấy dữ liệu thay vì ném ra lỗi UnsupportedOperationException
        return hoaDonDAO.getListHoaDon(keyword); 
    }

    public BigDecimal getTongTien(int idHd) {
        return hoaDonDAO.getTongTien(idHd);
    }

    public int getHoaDonByBenhAn(int idBa) {
        return hoaDonDAO.getHoaDonByBenhAn(idBa);
    }

    public void themThuocVaoHoaDon(int idBa, int idThuoc, int soLuong, BigDecimal donGia) {
        int idHd = getHoaDonByBenhAn(idBa);
        if (idHd <= 0)
            throw new RuntimeException("Không tìm thấy hóa đơn");
        cthdDAO.addThuoc(idHd, idThuoc, soLuong, donGia);
    }

    public String getGhiChu(int idBa) {
        int idHd = getHoaDonByBenhAn(idBa);
        return idHd > 0 ? hoaDonDAO.getGhiChu(idHd) : "";
    }

    public void saveGhiChu(int idBa, String ghiChu) {
        int idHd = getHoaDonByBenhAn(idBa);
        if (idHd > 0) {
            hoaDonDAO.updateGhiChu(idHd, ghiChu);
        }
    }

    public int getOrCreateHoaDon(int idBa, int idBn) {
        return hoaDonDAO.getOrCreateHoaDon(idBa, idBn);
    }

    public void copyDichVuToHoaDon(int idHd, int idBa) {
        hoaDonDAO.copyDichVuToHoaDon(idHd, idBa);
    }

    public void copyThuocToHoaDon(int idHd, int idBa) {
        hoaDonDAO.copyThuocToHoaDon(idHd, idBa);
    }

    public List<Object[]> getHoaDonTableData(int idHd) {
        return hoaDonDAO.getChiTietHoaDon(idHd);
    }

    public boolean deleteHoaDonByBenhAn(int idBa) {
        return hoaDonDAO.deleteHoaDonByBenhAn(idBa);
    }
}