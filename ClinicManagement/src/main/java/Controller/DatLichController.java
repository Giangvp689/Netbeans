/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAO.DatLichDAO;
import Model.Entity.DatLich;
import Model.DBConnection;

import java.sql.*;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author giang
 */
public class DatLichController {

    private DatLichDAO dao;

    public DatLichController() {
        dao = new DatLichDAO();
    }

    public ResultSet getAllDatLichResultSet() {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DatLich ORDER BY created_at DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

public List<Object[]> getLichBacSiTheoNgay(int idBs, Date ngay) {
    return dao.getLichTheoNgay(idBs, ngay);
}

    public List<DatLich> getLichTheoNgay(int idBs, Date ngayKham) {
        return dao.getLichTheoNgayHt(idBs, ngayKham);
    }
public boolean updateLich(DatLich newData) {

        DatLich oldData = dao.getById(newData.getId_lich());
        if (oldData == null) return false;

        boolean isChanged =
               !Objects.equals(oldData.getHoten_nv(), newData.getHoten_nv())
            || !Objects.equals(oldData.getSdt_nv(), newData.getSdt_nv())
            || !Objects.equals(oldData.getGioitinh_nv(), newData.getGioitinh_nv())
            || !Objects.equals(oldData.getNgaysinh_nv(), newData.getNgaysinh_nv())
            || !Objects.equals(oldData.getGhichu(), newData.getGhichu())
            || oldData.getId_bs() != newData.getId_bs()
            || !Objects.equals(oldData.getNgaykham(), newData.getNgaykham())
            || !Objects.equals(oldData.getGiokham(), newData.getGiokham());

        if (isChanged) {
            return dao.updateFull(newData);
        } else {
            return dao.updateTrangThaiDaKham(newData.getId_lich());
        }
    }

public boolean huyLich(int idLich) {
    return dao.deleteDatLich(idLich);
}
public List<Object[]> getLichSapToi(java.sql.Date fromDate) {
    return dao.getLichSapToiRaw(fromDate);
}


}
