package Model.Entity;

import java.sql.Date;
import java.sql.Time;

/**
 * Entity cho bảng BenhAn
 * Mỗi bản ghi tương ứng một lần khám lâm sàng của bệnh nhân.
 * @author giang
 */
public class BenhAn {
    private int id_ba;
    private Integer id_pk;
    private int id_bn;
    private int id_bs;
    private Integer id_nv_lap;
    private Date ngaykhambenhan;

    private String khamlam_sang;
    private String benhkemtheo;
    private Double nhietdo;
    private Double cannang;
    private Double chieucao;
    private String huyetap;
    private String nhiptho;
    private String nhiptim;
    private String diungthuoc;
    private String ma_icd10;
    private String chandoan;
    private String ketluan;
    private boolean hen_kham;
    private Date ngayhenkhambit;
    private String ghichu;
    private String trangthai;

    public BenhAn() {}

    public BenhAn(int id_ba, Integer id_pk, int id_bn, int id_bs, Integer id_nv_lap,
                  Date ngaykhambenhan,  String khamlam_sang, String benhkemtheo,
                  Double nhietdo, Double cannang, Double chieucao, String huyetap, String nhiptho,
                  String nhiptim, String diungthuoc, String ma_icd10, String chandoan,
                  String ketluan, boolean hen_kham, Date ngayhenkhambit, String ghichu) {
        this.id_ba = id_ba;
        this.id_pk = id_pk;
        this.id_bn = id_bn;
        this.id_bs = id_bs;
        this.id_nv_lap = id_nv_lap;
        this.ngaykhambenhan = ngaykhambenhan;

        this.khamlam_sang = khamlam_sang;
        this.benhkemtheo = benhkemtheo;
        this.nhietdo = nhietdo;
        this.cannang = cannang;
        this.chieucao = chieucao;
        this.huyetap = huyetap;
        this.nhiptho = nhiptho;
        this.nhiptim = nhiptim;
        this.diungthuoc = diungthuoc;
        this.ma_icd10 = ma_icd10;
        this.chandoan = chandoan;
        this.ketluan = ketluan;
        this.hen_kham = hen_kham;
        this.ngayhenkhambit = ngayhenkhambit;
        this.ghichu = ghichu;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    // ===== Getter & Setter =====
    public int getId_ba() { return id_ba; }
    public void setId_ba(int id_ba) { this.id_ba = id_ba; }

    public Integer getId_pk() { return id_pk; }
    public void setId_pk(Integer id_pk) { this.id_pk = id_pk; }

    public int getId_bn() { return id_bn; }
    public void setId_bn(int id_bn) { this.id_bn = id_bn; }

    public int getId_bs() { return id_bs; }
    public void setId_bs(int id_bs) { this.id_bs = id_bs; }

    public Integer getId_nv_lap() { return id_nv_lap; }
    public void setId_nv_lap(Integer id_nv_lap) { this.id_nv_lap = id_nv_lap; }

    public Date getNgaykhambenhan() { return ngaykhambenhan; }
    public void setNgaykhambenhan(Date ngaykhambenhan) { this.ngaykhambenhan = ngaykhambenhan; }


    public String getKhamlam_sang() { return khamlam_sang; }
    public void setKhamlam_sang(String khamlam_sang) { this.khamlam_sang = khamlam_sang; }

    public String getBenhkemtheo() { return benhkemtheo; }
    public void setBenhkemtheo(String benhkemtheo) { this.benhkemtheo = benhkemtheo; }

    public Double getNhietdo() { return nhietdo; }
    public void setNhietdo(Double nhietdo) { this.nhietdo = nhietdo; }

    public Double getCannang() { return cannang; }
    public void setCannang(Double cannang) { this.cannang = cannang; }

    public Double getChieucao() { return chieucao; }
    public void setChieucao(Double chieucao) { this.chieucao = chieucao; }

    public String getHuyetap() { return huyetap; }
    public void setHuyetap(String huyetap) { this.huyetap = huyetap; }

    public String getNhiptho() { return nhiptho; }
    public void setNhiptho(String nhiptho) { this.nhiptho = nhiptho; }

    public String getNhiptim() { return nhiptim; }
    public void setNhiptim(String nhiptim) { this.nhiptim = nhiptim; }

    public String getDiungthuoc() { return diungthuoc; }
    public void setDiungthuoc(String diungthuoc) { this.diungthuoc = diungthuoc; }

    public String getMa_icd10() { return ma_icd10; }
    public void setMa_icd10(String ma_icd10) { this.ma_icd10 = ma_icd10; }

    public String getChandoan() { return chandoan; }
    public void setChandoan(String chandoan) { this.chandoan = chandoan; }

    public String getKetluan() { return ketluan; }
    public void setKetluan(String ketluan) { this.ketluan = ketluan; }

    public boolean isHen_kham() { return hen_kham; }
    public void setHen_kham(boolean hen_kham) { this.hen_kham = hen_kham; }

    public Date getNgayhenkhambit() { return ngayhenkhambit; }
    public void setNgayhenkhambit(Date ngayhenkhambit) { this.ngayhenkhambit = ngayhenkhambit; }

    public String getGhichu() { return ghichu; }
    public void setGhichu(String ghichu) { this.ghichu = ghichu; }
}
