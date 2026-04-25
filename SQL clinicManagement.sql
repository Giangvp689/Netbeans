
CREATE DATABASE ClinicManagement;
GO
USE ClinicManagement;
GO

CREATE TABLE BenhNhan (
    id_bn INT IDENTITY(1,1) PRIMARY KEY,
    hoten NVARCHAR(100) NOT NULL,
    gioitinh NVARCHAR(10),
    ngaysinh DATE,
    sdt VARCHAR(15) UNIQUE,
    diachi NVARCHAR(200),
    nghenghiep NVARCHAR(100),
    dantoc NVARCHAR(50),
    nguoithan NVARCHAR(200),
    ghichu NVARCHAR(300),
    ngaydangky DATETIME DEFAULT GETDATE()
);


CREATE TABLE BacSi (
    id_bs INT IDENTITY(1,1) PRIMARY KEY,
    hoten NVARCHAR(100) NOT NULL,
    chuyenmon NVARCHAR(100),
    gioitinh NVARCHAR(10),
    ngaysinh DATE,
    sdt VARCHAR(15) UNIQUE,
    email VARCHAR(100),
    diachi NVARCHAR(200),
    ghichu NVARCHAR(300),
    trangthai NVARCHAR(50) DEFAULT N'Đang làm việc'
);


CREATE TABLE NhanVien (
    id_nv INT IDENTITY(1,1) PRIMARY KEY,
    hoten NVARCHAR(100) NOT NULL,
    ngaysinh DATE,
    gioitinh NVARCHAR(10),
    diachi NVARCHAR(200),
    sdt VARCHAR(15) UNIQUE,
    email VARCHAR(100),
    chucvu NVARCHAR(50),
    trangthai NVARCHAR(50) DEFAULT N'Đang làm việc',
    taikhoan VARCHAR(100) UNIQUE,    -- username
    matkhau VARCHAR(255)              -- lưu mật khẩu đã hash (không lưu plain)
);


--Lịch làm vc bác sĩ
CREATE TABLE LichLamViec (
    id_llv INT IDENTITY(1,1) PRIMARY KEY,
    id_bs INT NOT NULL,
    ngaybatdau DATE,
    ngayketthuc DATE,
    thu NVARCHAR(100),    -- ví dụ: 'Thứ 2;Thứ 4;Thứ 6'
    giobatdau TIME,
    gioketthuc TIME,
    ghichu NVARCHAR(300),
    FOREIGN KEY (id_bs) REFERENCES BacSi(id_bs)
);


/* ========== BẢNG ĐẶT LỊCH (qua hotline / receptionist) ========== 
   - Lưu thông tin cơ bản bệnh nhân (tối thiểu) => liên kết nếu BN đã tồn tại */
CREATE TABLE DatLich (
    id_lich INT IDENTITY(1,1) PRIMARY KEY,
    id_bn INT NULL,                      -- NULL nếu đặt tạm (chưa tạo BN)
    hoten_nv NVARCHAR(100),              -- copy tên lúc đặt (dễ tra cứu)
    sdt_nv VARCHAR(15),
    gioitinh_nv NVARCHAR(10),
    ngaysinh_nv DATE,
    ghichu NVARCHAR(500),
    id_bs INT NOT NULL,                  -- bác sĩ được yêu cầu
    ngaykham DATE NOT NULL,
    giokham TIME,
    trangthai NVARCHAR(50) DEFAULT N'Chưa khám',
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_bn) REFERENCES BenhNhan(id_bn),
    FOREIGN KEY (id_bs) REFERENCES BacSi(id_bs)
);


/*  BẢNG PHIẾU KHÁM (Phiếu tiếp nhận / Số phiếu khi đến)
   - Một bản ghi khi bệnh nhân đến phòng khám, do lễ tân tạo.*/

CREATE TABLE PhieuKham (
    id_pk INT IDENTITY(1,1) PRIMARY KEY,     -- số phiếu
    id_lich INT NULL,                        -- nếu từ DatLich
    id_bn INT NULL,                          -- bệnh nhân
    hoten_nv NVARCHAR(100),                  -- lưu lại tên tại thời điểm
    sdt_nv VARCHAR(15),
    id_bs INT NULL,                          -- bác sĩ phụ trách
    loaikham NVARCHAR(100),                  -- ví dụ: 'Khám thường', 'Khám BHYT', ...
    phongkham NVARCHAR(100),                 -- phòng khám/ phòng số
    id_nv_don INT NULL,                      -- nhân viên đón
    ngaykhamban DATETIME DEFAULT GETDATE(),  -- ngày giờ tiếp nhận
    giokham TIME NULL,
    trangthai NVARCHAR(50) DEFAULT N'Đã đến',-- ví dụ: Đã đến, Đang khám, Hoàn thành, Hủy
    ghichu NVARCHAR(500),
    FOREIGN KEY (id_lich) REFERENCES DatLich(id_lich),
    FOREIGN KEY (id_bn) REFERENCES BenhNhan(id_bn),
    FOREIGN KEY (id_bs) REFERENCES BacSi(id_bs),
    FOREIGN KEY (id_nv_don) REFERENCES NhanVien(id_nv)
);


/*BẢNG BỆNH ÁN (BenhAn) - mỗi bản ghi là 1 phiên khám lâm sàng  */
CREATE TABLE BenhAn (
    id_ba INT IDENTITY(1,1) PRIMARY KEY,
    id_pk INT NULL,                 -- liên kết phiếu khám
    id_bn INT NOT NULL,             -- bệnh nhân
    id_bs INT NOT NULL,             -- bác sĩ chỉ định / khám
    id_nv_lap INT NULL,             -- nhân viên lập phiếu/bác sĩ nhập
    ngaykhambenhan DATE DEFAULT CAST(GETDATE() AS DATE),
    giokham TIME,
    khamlam_sang NVARCHAR(1000),    -- mô tả khám lâm sàng
    benhkemtheo NVARCHAR(500),
    nhietdo DECIMAL(5,2),           -- °C
    cannang DECIMAL(6,2),           -- kg
    chieucao DECIMAL(6,2),          -- m hoặc cm (quy 1)
    huyetap NVARCHAR(50),           -- ví dụ: '120/80'
    nhiptho NVARCHAR(50),
    nhiptim NVARCHAR(50),
    diungthuoc NVARCHAR(300),
    ma_icd10 NVARCHAR(50),          -- mã ICD-10 (có thể NULL)
    chandoan NVARCHAR(500),
    ketluan NVARCHAR(500),
    hen_kham BIT DEFAULT 0,         -- 0: không hẹn, 1: có hẹn
    ngayhenkhambit DATE NULL,       -- ngày hẹn khám lại (nếu có)
    ghichu NVARCHAR(500),
    FOREIGN KEY (id_pk) REFERENCES PhieuKham(id_pk),
    FOREIGN KEY (id_bn) REFERENCES BenhNhan(id_bn),
    FOREIGN KEY (id_bs) REFERENCES BacSi(id_bs),
    FOREIGN KEY (id_nv_lap) REFERENCES NhanVien(id_nv)
);


/* BẢNG HENKHAM (lưu lịch hẹn khám lại, linh hoạt) */
CREATE TABLE HenKham (
    id_hen INT IDENTITY(1,1) PRIMARY KEY,
    id_ba INT NULL,                 -- nếu hẹn dựa trên 1 phiên khám
    id_bn INT NOT NULL,
    id_bs INT NULL,
    ngayhen DATE NOT NULL,
    giohen TIME NULL,
    ghichu NVARCHAR(500),
    trangthai NVARCHAR(50) DEFAULT N'Đã hẹn', -- Đã hẹn, Đã đến, Đã huỷ
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (id_ba) REFERENCES BenhAn(id_ba),
    FOREIGN KEY (id_bn) REFERENCES BenhNhan(id_bn),
    FOREIGN KEY (id_bs) REFERENCES BacSi(id_bs)
);


/* ========== BẢNG THUỐC (kho) ========== */
CREATE TABLE Thuoc (
    id_thuoc INT IDENTITY(1,1) PRIMARY KEY,
    tenthuoc NVARCHAR(200) NOT NULL,
    loai NVARCHAR(100),
    soluongton INT DEFAULT 0,
    donvitinh NVARCHAR(50),
    giaban DECIMAL(12,2),
    hansudung DATE,
    ghichu NVARCHAR(300)
);


/* ========== BẢNG ĐƠN THUỐC (mỗi đơn thuộc 1 BenhAn) ========== */
CREATE TABLE DonThuoc (
    id_dt INT IDENTITY(1,1) PRIMARY KEY,
    id_ba INT NOT NULL,
    id_bs INT NULL,
    id_nv_lap INT NULL,
    ngaylap DATE DEFAULT CAST(GETDATE() AS DATE),
    ghichu NVARCHAR(500),
    FOREIGN KEY (id_ba) REFERENCES BenhAn(id_ba),
    FOREIGN KEY (id_bs) REFERENCES BacSi(id_bs),
    FOREIGN KEY (id_nv_lap) REFERENCES NhanVien(id_nv)
);


/* ========== CHI TIẾT ĐƠN THUỐC ========== */
CREATE TABLE ChiTiet_DonThuoc (
    id_ctdt INT IDENTITY(1,1) PRIMARY KEY,
    id_dt INT NOT NULL,
    id_thuoc INT NOT NULL,
    soluong INT,
    lieudung NVARCHAR(300),
    dongia DECIMAL(12,2),     -- snapshot giá tại thời điểm kê
    FOREIGN KEY (id_dt) REFERENCES DonThuoc(id_dt),
    FOREIGN KEY (id_thuoc) REFERENCES Thuoc(id_thuoc)
);


/* ========== BẢNG DỊCH VỤ ========== */
CREATE TABLE DichVu (
    id_dv INT IDENTITY(1,1) PRIMARY KEY,
    madv NVARCHAR(50) UNIQUE,
    tendv NVARCHAR(200) NOT NULL,
    loai NVARCHAR(100),
    dongia DECIMAL(12,2),
    mota NVARCHAR(500),
    trangthai NVARCHAR(50) DEFAULT N'Hoạt động'
);


/* ========== CHI TIẾT DỊCH VỤ (dịch vụ được chỉ định trong 1 BenhAn) ========== */
CREATE TABLE ChiTiet_DichVu (
    id_ctdv INT IDENTITY(1,1) PRIMARY KEY,
    id_ba INT NOT NULL,
    id_dv INT NOT NULL,
    soluong INT DEFAULT 1,
    dongia DECIMAL(12,2),
    FOREIGN KEY (id_ba) REFERENCES BenhAn(id_ba),
    FOREIGN KEY (id_dv) REFERENCES DichVu(id_dv)
);


/* ========== BẢNG HÓA ĐƠN ========== */
CREATE TABLE HoaDon (
    id_hd INT IDENTITY(1,1) PRIMARY KEY,
    mahd NVARCHAR(50) UNIQUE,             -- mã hóa đơn
    id_ba INT NULL,                       -- hóa đơn gắn với phiên khám
    id_bn INT NULL,                       -- customer snapshot
    id_nv_lap INT NULL,                   -- nhân viên lập hóa đơn
    ngaylap DATETIME DEFAULT GETDATE(),
    tongtien DECIMAL(14,2) DEFAULT 0,
    trangthai NVARCHAR(50) DEFAULT N'Chưa thanh toán',
    ghichu NVARCHAR(500),
    FOREIGN KEY (id_ba) REFERENCES BenhAn(id_ba),
    FOREIGN KEY (id_bn) REFERENCES BenhNhan(id_bn),
    FOREIGN KEY (id_nv_lap) REFERENCES NhanVien(id_nv)
);


/* ========== CHI TIẾT HÓA ĐƠN ========== 
   - có thể chứa thuốc hoặc dịch vụ; loại = 'THUOC'/'DICHVU' */
CREATE TABLE ChiTiet_HoaDon (
    id_cthd INT IDENTITY(1,1) PRIMARY KEY,
    id_hd INT NOT NULL,
    loai_item NVARCHAR(20) NOT NULL,  -- 'THUOC' hoặc 'DICHVU'
    id_ref INT NOT NULL,              -- id_thuoc hoặc id_dv tương ứng
    soluong INT DEFAULT 1,
    dongia DECIMAL(14,2),
    thanhtien AS (soluong * dongia) PERSISTED,
    FOREIGN KEY (id_hd) REFERENCES HoaDon(id_hd)
);
--Bảng Tài khoản dùng để đăng nhập
CREATE TABLE TaiKhoan (
    id_tk INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL, -- mật khẩu đã hash
    loai_tk NVARCHAR(50) NOT NULL,        -- 'BACSI', 'NHANVIEN', 'ADMIN'
    id_ref INT NOT NULL,                  -- trỏ tới id_bs hoặc id_nv
    trangthai NVARCHAR(50) DEFAULT N'Hoạt động',
    created_at DATETIME DEFAULT GETDATE()
);
GO




--Dữ liệu mẫu

-- 1️⃣ BỆNH NHÂN
INSERT INTO BenhNhan (hoten, gioitinh, ngaysinh, sdt, diachi, nghenghiep, dantoc, nguoithan, ghichu)
VALUES
(N'Nguyễn Văn A', N'Nam', '1990-03-12', '0901000001', N'Hà Nội', N'Kỹ sư IT', N'Kinh', N'Nguyễn Thị Lan', N'Không dị ứng'),
(N'Trần Thị B', N'Nữ', '1985-07-08', '0901000002', N'Hà Nội', N'Giáo viên', N'Kinh', N'Trần Văn Nam', N'Viêm xoang nhẹ'),
(N'Lê Văn C', N'Nam', '1993-09-25', '0901000003', N'Đà Nẵng', N'Tài xế', N'Kinh', N'Lê Thị Hoa', N'Hút thuốc lá'),
(N'Phạm Thị D', N'Nữ', '1992-12-18', '0901000004', N'TP.HCM', N'Kế toán', N'Kinh', N'Phạm Văn Minh', N'Dị ứng hải sản'),
(N'Hoàng Văn E', N'Nam', '1980-02-01', '0901000005', N'Huế', N'Ngân hàng', N'Kinh', N'Hoàng Thị Hương', N'Huyết áp cao'),
(N'Đỗ Thị F', N'Nữ', '1998-06-14', '0901000006', N'Cần Thơ', N'Sinh viên', N'Kinh', N'Đỗ Văn Long', N'Không ghi chú'),
(N'Vũ Văn G', N'Nam', '1994-11-20', '0901000007', N'Hải Phòng', N'Công nhân', N'Kinh', N'Vũ Thị Mai', N'Dạ dày nhẹ'),
(N'Lý Thị H', N'Nữ', '1987-05-06', '0901000008', N'Quảng Nam', N'Bán hàng', N'Kinh', N'Lý Văn Tâm', N'Tiểu đường'),
(N'Ngô Văn I', N'Nam', '1996-09-03', '0901000009', N'Hà Nội', N'Nhân viên IT', N'Kinh', N'Ngô Thị Duyên', N'Khỏe mạnh'),
(N'Phan Thị K', N'Nữ', '1991-01-10', '0901000010', N'Hà Nội', N'Bác sĩ thú y', N'Kinh', N'Phan Văn Lộc', N'Không có ghi chú');


-- 2️⃣ BÁC SĨ
INSERT INTO BacSi (hoten, chuyenmon, gioitinh, ngaysinh, sdt, email, diachi, ghichu)
VALUES
(N'Nguyễn Hữu An', N'Nội tổng quát', N'Nam', '1978-02-15', '0912000001', 'an.nguyen@clinic.vn', N'Hà Nội', N'Bác sĩ trưởng'),
(N'Trần Minh Bình', N'Nhi khoa', N'Nam', '1985-06-10', '0912000002', 'binh.tran@clinic.vn', N'Hà Nội', N'Chuyên khoa trẻ em'),
(N'Lê Thị Cúc', N'Sản phụ khoa', N'Nữ', '1982-09-09', '0912000003', 'cuc.le@clinic.vn', N'Đà Nẵng', N'Kinh nghiệm cao'),
(N'Hoàng Văn Dũng', N'Tim mạch', N'Nam', '1975-11-23', '0912000004', 'dung.hoang@clinic.vn', N'Huế', N'Tận tâm'),
(N'Phạm Thị Hồng', N'Da liễu', N'Nữ', '1988-01-05', '0912000005', 'hong.pham@clinic.vn', N'Hà Nội', N'Nhiệt tình'),
(N'Đỗ Quốc Hưng', N'Răng hàm mặt', N'Nam', '1983-03-14', '0912000006', 'hung.do@clinic.vn', N'TP.HCM', N'Năng động'),
(N'Vũ Thị Lan', N'Mắt', N'Nữ', '1989-04-20', '0912000007', 'lan.vu@clinic.vn', N'Hà Nội', N'Chu đáo'),
(N'Lý Minh Quân', N'Tai mũi họng', N'Nam', '1986-05-30', '0912000008', 'quan.ly@clinic.vn', N'Đà Nẵng', N'Tỉ mỉ'),
(N'Ngô Quang Sơn', N'Nội tiết', N'Nam', '1979-08-09', '0912000009', 'son.ngo@clinic.vn', N'Hà Nội', N'Điềm đạm'),
(N'Phan Thị Tâm', N'Cơ xương khớp', N'Nữ', '1987-12-15', '0912000010', 'tam.phan@clinic.vn', N'Cần Thơ', N'Nhiều kinh nghiệm');


-- 3️⃣ NHÂN VIÊN
INSERT INTO NhanVien (hoten, ngaysinh, gioitinh, diachi, sdt, email, chucvu, taikhoan, matkhau)
VALUES
(N'Nguyễn Văn Lễ', '1995-02-12', N'Nam', N'Hà Nội', '0921000001', 'letan@clinic.vn', N'Lễ tân', 'letan', '123456'),
(N'Trần Thị Kế Toán', '1990-04-08', N'Nữ', N'Hà Nội', '0921000002', 'ketoan@clinic.vn', N'Kế toán', 'ketoan', '123456'),
(N'Lê Văn Quản Lý', '1988-10-10', N'Nam', N'Hà Nội', '0921000003', 'admin@clinic.vn', N'Quản lý', 'admin', '123456'),
(N'Phạm Thị Tiếp Đón', '1997-07-21', N'Nữ', N'Đà Nẵng', '0921000004', 'tiepdon@clinic.vn', N'Lễ tân', 'tiepdon', '123456'),
(N'Hoàng Văn IT', '1994-01-01', N'Nam', N'Huế', '0921000005', 'it@clinic.vn', N'IT', 'itadmin', '123456'),
(N'Đỗ Thị Thu Ngân', '1996-09-19', N'Nữ', N'Hà Nội', '0921000006', 'thungan@clinic.vn', N'Thu ngân', 'thungan', '123456'),
(N'Vũ Văn Dược', '1985-11-14', N'Nam', N'TP.HCM', '0921000007', 'duocsi@clinic.vn', N'Dược sĩ', 'duocsi', '123456'),
(N'Lý Thị Hậu', '1993-12-02', N'Nữ', N'Hà Nội', '0921000008', 'hau@clinic.vn', N'Lễ tân', 'nhanvien1', '123456'),
(N'Ngô Quang Phúc', '1989-03-17', N'Nam', N'Hà Nội', '0921000009', 'phuc@clinic.vn', N'Bảo vệ', 'baove', '123456'),
(N'Phan Thị Mai', '1998-06-06', N'Nữ', N'Cần Thơ', '0921000010', 'mai@clinic.vn', N'Lễ tân', 'nhanvien2', '123456');


-- 4️⃣ LỊCH LÀM VIỆC
INSERT INTO LichLamViec (id_bs, ngaybatdau, ngayketthuc, thu, giobatdau, gioketthuc, ghichu)
VALUES
(1, '2025-11-01', '2025-11-30', N'Thứ 2,4,6', '08:00', '16:00', N'Ca sáng'),
(2, '2025-11-01', '2025-11-30', N'Thứ 3,5,7', '08:00', '16:00', N'Ca sáng'),
(3, '2025-11-01', '2025-11-30', N'Thứ 2,4,6', '08:00', '16:00', N'Ca sáng'),
(4, '2025-11-01', '2025-11-30', N'Thứ 2,3,5', '08:00', '16:00', N'Ca sáng'),
(5, '2025-11-01', '2025-11-30', N'Thứ 3,5,7', '08:00', '16:00', N'Ca sáng'),
(6, '2025-11-01', '2025-11-30', N'Thứ 2,4,6', '08:00', '16:00', N'Ca sáng'),
(7, '2025-11-01', '2025-11-30', N'Thứ 3,5,7', '08:00', '16:00', N'Ca sáng'),
(8, '2025-11-01', '2025-11-30', N'Thứ 2,4,6', '08:00', '16:00', N'Ca sáng'),
(9, '2025-11-01', '2025-11-30', N'Thứ 3,5,7', '08:00', '16:00', N'Ca sáng'),
(10, '2025-11-01', '2025-11-30', N'Thứ 2,4,6', '08:00', '16:00', N'Ca sáng');



INSERT INTO DatLich (id_bn, id_bs, ngaykham, giokham, ghichu)
VALUES
(1, 1, '2025-11-05', '08:00', N'Khám tổng quát'),
(2, 2, '2025-11-06', '09:00', N'Khám nhi cho bé'),
(3, 3, '2025-11-07', '10:00', N'Khám sản phụ khoa'),
(4, 4, '2025-11-08', '08:30', N'Khám tim mạch'),
(5, 5, '2025-11-09', '09:30', N'Khám da liễu'),
(6, 6, '2025-11-10', '10:30', N'Khám răng'),
(7, 7, '2025-11-11', '14:00', N'Khám mắt'),
(8, 8, '2025-11-12', '15:00', N'Khám tai mũi họng'),
(9, 9, '2025-11-13', '13:30', N'Khám nội tiết'),
(10, 10, '2025-11-14', '16:00', N'Khám cơ xương khớp');

--  PHIẾU KHÁM
INSERT INTO PhieuKham ( id_lich, id_bn, hoten_nv, sdt_nv, id_bs, loaikham, phongkham, id_nv_don, ngaykhamban, giokham, trangthai, ghichu)
VALUES
(1, 1, N'Nguyễn Văn Lễ', '0921000001', 1, N'Khám tổng quát', N'Phòng 101', 1, GETDATE(), '08:30', N'Đã đến', N'Lần khám đầu'),
( 2, 2, N'Phạm Thị Tiếp Đón', '0921000004', 2, N'Khám nhi', N'Phòng 102', 4, GETDATE(), '09:00', N'Đang khám', N'Bé bị sốt'),
( 3, 3, N'Nguyễn Văn Lễ', '0921000001', 3, N'Khám phụ khoa', N'Phòng 103', 1, GETDATE(), '10:00', N'Hoàn thành', N'Khám định kỳ'),
( 4, 4, N'Phạm Thị Tiếp Đón', '0921000004', 4, N'Khám tim mạch', N'Phòng 104', 4, GETDATE(), '08:45', N'Đã đến', N'Kiểm tra lại huyết áp'),
( 5, 5, N'Nguyễn Văn Lễ', '0921000001', 5, N'Khám da liễu', N'Phòng 105', 1, GETDATE(), '09:30', N'Đang khám', N'Nổi mẩn đỏ'),
( 6, 6, N'Phạm Thị Tiếp Đón', '0921000004', 6, N'Khám răng', N'Phòng 106', 4, GETDATE(), '10:15', N'Hoàn thành', N'Lấy cao răng'),
( 7, 7, N'Nguyễn Văn Lễ', '0921000001', 7, N'Khám mắt', N'Phòng 107', 1, GETDATE(), '08:15', N'Đã đến', N'Cận thị nhẹ'),
( 8, 8, N'Phạm Thị Tiếp Đón', '0921000004', 8, N'Khám tai mũi họng', N'Phòng 108', 4, GETDATE(), '09:45', N'Đang khám', N'Viêm họng'),
( 9, 9, N'Nguyễn Văn Lễ', '0921000001', 9, N'Khám nội tiết', N'Phòng 109', 1, GETDATE(), '10:00', N'Hoàn thành', N'Kiểm tra đường huyết'),
( 10, 10, N'Phạm Thị Tiếp Đón', '0921000004', 10, N'Khám cơ xương khớp', N'Phòng 110', 4, GETDATE(), '08:50', N'Đã đến', N'Đau khớp gối');




INSERT INTO BenhAn (id_pk, id_bn, id_bs, id_nv_lap, khamlam_sang, benhkemtheo, nhiptim, nhietdo, cannang, chieucao, huyetap, nhiptho, diungthuoc, ma_icd10, chandoan, ketluan, hen_kham, ngayhenkhambit, ghichu)
VALUES
(1, 1, 1, 1, N'Khám tổng quát bình thường', N'Không', '78', 36.6, 65, 170, N'120/80', N'18', N'Không', N'Z00.0', N'Khỏe mạnh', N'Tái khám sau 6 tháng', 1, '2026-05-07', N''),
(2, 2, 2, 4, N'Khám tai mũi họng, viêm nhẹ', N'Viêm họng cấp', '85', 37.5, 20, 110, N'110/70', N'20', N'Không', N'J02.9', N'Viêm họng cấp', N'Kê kháng sinh', 1, '2025-11-14', N'Đã kê đơn'),
(3, 3, 3, 1, N'Khám phụ khoa', N'Không', '75', 36.8, 55, 160, N'115/75', N'17', N'Không', N'N91.2', N'Rối loạn kinh nguyệt', N'Uống thuốc điều hòa', 1, '2025-12-07', N''),
(4, 4, 4, 4, N'Đo huyết áp cao, cần theo dõi', N'Tăng huyết áp độ 1', '95', 36.9, 70, 168, N'145/95', N'21', N'Không', N'I10', N'Tăng huyết áp', N'Theo dõi thêm', 1, '2025-11-21', N''),
(5, 5, 5, 1, N'Nổi mẩn đỏ vùng tay', N'Dị ứng da', '80', 37.0, 58, 160, N'115/80', N'18', N'Hải sản', N'L23', N'Viêm da tiếp xúc', N'Dùng thuốc bôi', 0, NULL, N'Đã kê đơn thuốc'),
(6, 6, 6, 4, N'Răng có cao bám nhiều', N'Không', '78', 36.5, 60, 165, N'118/78', N'18', N'Không', N'K05.3', N'Viêm lợi nhẹ', N'Đã xử lý', 0, NULL, N'Hoàn thành'),
(7, 7, 7, 1, N'Kiểm tra mắt, cận thị nhẹ', N'Cận thị', '80', 36.8, 64, 170, N'120/80', N'18', N'Không', N'H52.1', N'Cận thị 1 độ', N'Đã đo kính', 0, NULL, N''),
(8, 8, 8, 4, N'Viêm họng mủ', N'Sốt nhẹ', '90', 38.2, 68, 168, N'115/75', N'22', N'Không', N'J03.9', N'Viêm họng cấp', N'Kê thuốc 7 ngày', 1, '2025-11-15', N''),
(9, 9, 9, 1, N'Đường huyết cao nhẹ', N'Tiểu đường type 2', '85', 36.7, 70, 170, N'130/90', N'18', N'Không', N'E11', N'Tiểu đường nhẹ', N'Điều chỉnh ăn uống', 1, '2025-12-01', N''),
(10, 10, 10, 4, N'Đau khớp gối trái', N'Không', '82', 36.9, 66, 168, N'120/80', N'18', N'Không', N'M17.1', N'Thoái hóa khớp gối', N'Dùng thuốc giảm đau', 1, '2025-12-10', N'');



INSERT INTO HenKham (id_ba, id_bn, id_bs, ngayhen, giohen, ghichu, trangthai)
VALUES
(1, 1, 1, '2025-11-20', NULL, N'Tái khám huyết áp', N'Đã hẹn'),
(3, 3, 3, '2025-11-25', NULL, N'Tái khám dạ dày', N'Đã hẹn'),
(4, 4, 4, '2025-11-28', NULL, N'Kiểm tra tim mạch', N'Đã hẹn'),
(7, 7, 7, '2025-12-02', NULL, N'Tái khám mắt', N'Đã hẹn'),
(9, 9, 9, '2025-12-10', NULL, N'Theo dõi tiểu đường', N'Đã hẹn'),
(2, 2, 2, '2025-11-15', NULL, N'Khám cảm cúm lại', N'Đã hẹn'),
(5, 5, 5, '2025-11-19', NULL, N'Tái khám da liễu', N'Đã hẹn'),
(6, 6, 6, '2025-11-18', NULL, N'Kiểm tra răng miệng', N'Đã hẹn'),
(8, 8, 8, '2025-11-23', NULL, N'Tái khám tai', N'Đã hẹn'),
(10, 10, 10, '2025-12-05', NULL, N'Tái khám khớp', N'Đã hẹn');


-- ===========================
-- 1) THUỐC (10 dòng)
-- ===========================
INSERT INTO Thuoc ( tenthuoc, loai, soluongton, donvitinh, giaban, hansudung, ghichu)
VALUES
(N'Paracetamol 500mg', N'Giảm đau - Hạ sốt', 1000, N'Viên', 1500, '2026-12-31', N'Hạ sốt, giảm đau nhẹ'),
( N'Amoxicillin 500mg', N'Kháng sinh', 800, N'Viên', 2500, '2026-06-30', N'Kháng sinh phổ rộng'),
( N'Lansoprazole 30mg', N'Dạ dày', 600, N'Viên', 3000, '2027-01-01', N'Giảm tiết acid dạ dày'),
( N'Vitamin C 1000mg', N'Vitamin', 500, N'Viên', 1000, '2026-05-31', N'Tăng đề kháng'),
( N'Aspirin 81mg', N'Giảm đau', 700, N'Viên', 1800, '2026-08-31', N'Giảm đông, giảm đau nhẹ'),
( N'Clarithromycin 500mg', N'Kháng sinh', 400, N'Viên', 3200, '2026-11-30', N'Kháng sinh đường hô hấp'),
( N'Ibuprofen 400mg', N'Giảm đau - Chống viêm', 900, N'Viên', 2200, '2027-02-28', N'Chống viêm, giảm đau'),
( N'Cetirizine 10mg', N'Chống dị ứng', 600, N'Viên', 1200, '2026-09-30', N'Chống dị ứng'),
( N'Metformin 500mg', N'Tiểu đường', 1000, N'Viên', 2200, '2026-12-31', N'Hạ đường huyết'),
( N'Glucosamine 1500mg', N'Hỗ trợ khớp', 300, N'Viên', 3500, '2026-10-31', N'Hỗ trợ thoái hóa khớp');

GO

-- ===========================
-- 2) DỊCH VỤ (10 dòng)
-- ===========================
INSERT INTO DichVu (madv, tendv, loai, dongia, mota, trangthai)
VALUES
('DV001', N'Khám tổng quát', N'Khám', 80000, N'Khám lâm sàng tổng quát', N'Hoạt động'),
('DV002', N'Khám nhi', N'Khám', 100000, N'Khám cho trẻ em', N'Hoạt động'),
('DV003', N'Khám sản phụ khoa', N'Khám', 120000, N'Khám sản', N'Hoạt động'),
('DV004', N'Khám tim mạch', N'Chuyên khoa', 150000, N'Đo ECG và tư vấn tim mạch', N'Hoạt động'),
('DV005', N'Khám da liễu', N'Chuyên khoa', 90000, N'Khám và tư vấn bệnh da', N'Hoạt động'),
('DV006', N'Khám răng', N'Chuyên khoa', 110000, N'Khám & điều trị răng', N'Hoạt động'),
('DV007', N'Khám mắt', N'Chuyên khoa', 100000, N'Đo thị lực, khám mắt', N'Hoạt động'),
('DV008', N'Khám tai mũi họng', N'Chuyên khoa', 95000, N'Nội soi tai mũi họng', N'Hoạt động'),
('DV009', N'Khám nội tiết', N'Chuyên khoa', 110000, N'Khám nội tiết & tư vấn', N'Hoạt động'),
('DV010', N'Khám xương khớp', N'Chuyên khoa', 130000, N'Khám cơ xương khớp', N'Hoạt động');

GO

-- ===========================
-- 3) ĐƠN THUỐC (10 dòng)
--   => mỗi đơn liên kết với BenhAn.id_ba = 1..10
-- ===========================
INSERT INTO DonThuoc (id_ba, id_bs, id_nv_lap, ngaylap, ghichu)
VALUES
(1, 1, 1, '2025-11-05', N'Đơn cho bệnh nhân A'),
(2, 2, 4, '2025-11-06', N'Đơn cho bệnh nhân B'),
(3, 3, 1, '2025-11-07', N'Đơn cho bệnh nhân C'),
(4, 4, 4, '2025-11-08', N'Đơn cho bệnh nhân D'),
(5, 5, 6, '2025-11-09', N'Đơn cho bệnh nhân E'),
(6, 6, 6, '2025-11-10', N'Đơn cho bệnh nhân F'),
(7, 7, 8, '2025-11-11', N'Đơn cho bệnh nhân G'),
(8, 8, 8, '2025-11-12', N'Đơn cho bệnh nhân H'),
(9, 9, 1, '2025-11-13', N'Đơn cho bệnh nhân I'),
(10, 10, 4, '2025-11-14', N'Đơn cho bệnh nhân K');

GO

-- ===========================
-- 4) CHI TIẾT ĐƠN THUỐC (10 dòng)
--   => id_dt phải khớp (sau insert DonThuoc sẽ là 1..10)
-- ===========================
INSERT INTO ChiTiet_DonThuoc (id_dt, id_thuoc, soluong, lieudung, dongia)
VALUES
(1, 1, 10, N'500mg', 1500.00),
(2, 2, 14, N'500mg', 2500.00),
(3, 3, 28, N'30mg', 3000.00),
(4, 5, 7, N'81mg', 1800.00),
(5, 8, 10, N'10mg', 1200.00),
(6, 6, 5, N'500mg', 3200.00),
(7, 7, 12, N'400mg', 2200.00),
(8, 2, 14, N'500mg', 2500.00),
(9, 9, 60, N'500mg', 2200.00),
(10, 10, 30, N'1500mg', 3500.00);

GO

-- ===========================
-- 5) CHI TIẾT DỊCH VỤ (10 dòng)
--   => id_ba 1..10, id_dv 1..10
-- ===========================
INSERT INTO ChiTiet_DichVu (id_ba, id_dv, soluong, dongia)
VALUES
(1, 1, 1, 80000.00),
(2, 2, 1, 100000.00),
(3, 3, 1, 120000.00),
(4, 4, 1, 150000.00),
(5, 5, 1, 90000.00),
(6, 6, 1, 110000.00),
(7, 7, 1, 100000.00),
(8, 8, 1, 95000.00),
(9, 9, 1, 110000.00),
(10, 10, 1, 130000.00);

GO

-- ===========================
-- 6) HÓA ĐƠN (10 dòng)
--   => id_ba 1..10, id_bn 1..10, id_nv_lap = NV id (dùng 6 as thu ngan)
-- ===========================
INSERT INTO HoaDon (mahd, id_ba, id_bn, id_nv_lap, ngaylap, tongtien, trangthai, ghichu)
VALUES
('HD20251105-001', 1, 1, 6, '2025-11-05', 95000.00, N'Đã thanh toán', N'Bao gồm khám + thuốc'),
('HD20251106-002', 2, 2, 6, '2025-11-06', 120000.00, N'Đã thanh toán', N'Bao gồm khám nhi'),
('HD20251107-003', 3, 3, 6, '2025-11-07', 145000.00, N'Đã thanh toán', N'Khám + test'),
('HD20251108-004', 4, 4, 6, '2025-11-08', 155000.00, N'Chưa thanh toán', N'Khám tim mạch'),
('HD20251109-005', 5, 5, 6, '2025-11-09', 90000.00, N'Đã thanh toán', N'Khám da liễu'),
('HD20251110-006', 6, 6, 6, '2025-11-10', 110000.00, N'Chưa thanh toán', N'Khám răng'),
('HD20251111-007', 7, 7, 6, '2025-11-11', 100000.00, N'Đã thanh toán', N'Khám mắt'),
('HD20251112-008', 8, 8, 6, '2025-11-12', 95000.00, N'Đã thanh toán', N'Khám tai mũi họng'),
('HD20251113-009', 9, 9, 6, '2025-11-13', 110000.00, N'Đã thanh toán', N'Khám nội tiết'),
('HD20251114-010', 10, 10, 6, '2025-11-14', 130000.00, N'Đã thanh toán', N'Khám xương khớp');

GO

-- ===========================
-- 7) CHI TIẾT HÓA ĐƠN (10 dòng)
--   => mix DICHVU / THUOC: loai_item = 'DICHVU' => id_ref = id_dv ; 'THUOC' => id_ref = id_thuoc
--   => id_hd values will be 1..10 after inserting HoaDon above
-- ===========================
INSERT INTO ChiTiet_HoaDon (id_hd, loai_item, id_ref, soluong, dongia)
VALUES
(1, N'DICHVU', 1, 1, 80000.00),   -- khám tổng quát
(1, N'THUOC', 1, 10, 1500.00),    -- paracetamol
(2, N'DICHVU', 2, 1, 100000.00),  -- khám nhi
(3, N'DICHVU', 3, 1, 120000.00),  -- khám sản
(4, N'DICHVU', 4, 1, 150000.00),  -- khám tim mạch
(5, N'DICHVU', 5, 1, 90000.00),   -- khám da liễu
(6, N'DICHVU', 6, 1, 110000.00),  -- khám răng
(7, N'DICHVU', 7, 1, 100000.00),  -- khám mắt
(8, N'THUOC', 2, 7, 2500.00),     -- Amoxicillin x7
(9, N'DICHVU', 9, 1, 110000.00);
GO

--Dữ liệu Tài Khoản Bác Sĩ 
INSERT INTO TaiKhoan (username, password_hash, loai_tk, id_ref)
VALUES
('bs.an',      '123456', N'BACSI', 1),
('bs.binh',   '123456', N'BACSI', 2),
('bs.cuc',    '123456', N'BACSI', 3),
('bs.dung',   '123456', N'BACSI', 4),
('bs.hong',   '123456', N'BACSI', 5),
('bs.hung',   '123456', N'BACSI', 6),
('bs.lan',    '123456', N'BACSI', 7),
('bs.quan',   '123456', N'BACSI', 8),
('bs.son',    '123456', N'BACSI', 9),
('bs.tam',    '123456', N'BACSI', 10);
GO
--Tài Khoản nhân viên
INSERT INTO TaiKhoan (username, password_hash, loai_tk, id_ref)
VALUES
('letan',      '123456', N'NHANVIEN', 1), -- Nguyễn Văn Lễ
('ketoan',    '123456', N'NHANVIEN', 2),
('admin',     '123456', N'ADMIN',    3),
('tiepdon',   '123456', N'NHANVIEN', 4),
('itadmin',   '123456', N'NHANVIEN', 5),
('thungan',   '123456', N'NHANVIEN', 6),
('duocsi',    '123456', N'NHANVIEN', 7),
('nhanvien1', '123456', N'NHANVIEN', 8),
('baove',     '123456', N'NHANVIEN', 9),
('nhanvien2', '123456', N'NHANVIEN', 10);
GO


GO
CREATE TRIGGER trg_UpdateTongTienHoaDon
ON ChiTiet_HoaDon
AFTER INSERT, DELETE, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE hd
    SET tongtien = (
        SELECT ISNULL(SUM(thanhtien), 0)
        FROM ChiTiet_HoaDon
        WHERE id_hd = hd.id_hd
    )
    FROM HoaDon hd
    WHERE hd.id_hd IN (
        SELECT DISTINCT id_hd FROM inserted
        UNION
        SELECT DISTINCT id_hd FROM deleted
    );
END
GO


--trigger xóa bệnh nhân
GO
CREATE TRIGGER trg_Delete_BenhNhan_Cascade
ON BenhNhan
INSTEAD OF DELETE
AS
BEGIN
    SET NOCOUNT ON;

    -- Lấy danh sách bệnh nhân cần xóa
    DECLARE @tbl_bn TABLE (id_bn INT);
    INSERT INTO @tbl_bn
    SELECT id_bn FROM deleted;

    DELETE cthd
    FROM ChiTiet_HoaDon cthd
    JOIN HoaDon hd ON cthd.id_hd = hd.id_hd
    WHERE hd.id_bn IN (SELECT id_bn FROM @tbl_bn);


    DELETE FROM HoaDon
    WHERE id_bn IN (SELECT id_bn FROM @tbl_bn);

    DELETE ctdt
    FROM ChiTiet_DonThuoc ctdt
    JOIN DonThuoc dt ON ctdt.id_dt = dt.id_dt
    JOIN BenhAn ba ON dt.id_ba = ba.id_ba
    WHERE ba.id_bn IN (SELECT id_bn FROM @tbl_bn);


    DELETE dt
    FROM DonThuoc dt
    JOIN BenhAn ba ON dt.id_ba = ba.id_ba
    WHERE ba.id_bn IN (SELECT id_bn FROM @tbl_bn);


    DELETE ctdv
    FROM ChiTiet_DichVu ctdv
    JOIN BenhAn ba ON ctdv.id_ba = ba.id_ba
    WHERE ba.id_bn IN (SELECT id_bn FROM @tbl_bn);

    DELETE FROM HenKham
    WHERE id_bn IN (SELECT id_bn FROM @tbl_bn);

    DELETE FROM BenhAn
    WHERE id_bn IN (SELECT id_bn FROM @tbl_bn);


    DELETE FROM PhieuKham
    WHERE id_bn IN (SELECT id_bn FROM @tbl_bn);


    DELETE FROM DatLich
    WHERE id_bn IN (SELECT id_bn FROM @tbl_bn);


    DELETE FROM BenhNhan
    WHERE id_bn IN (SELECT id_bn FROM @tbl_bn);

END;
GO


-- 1. SỬA BẢNG BenhAn: THÊM KHÓA NGOẠI CHẶT VỚI PhieuKham
ALTER TABLE BenhAn
DROP COLUMN giokham;  -- Xóa trùng lặp, lấy từ PhieuKham

ALTER TABLE BenhAn
ADD CONSTRAINT FK_BenhAn_PhieuKham FOREIGN KEY (id_pk) 
REFERENCES PhieuKham(id_pk) ON DELETE SET NULL;

-- 2. THÊM TRẠNG THÁI CHO BenhAn
ALTER TABLE BenhAn
ADD trangthai NVARCHAR(50) DEFAULT N'Chờ khám';

-- 3. TRIGGER TỰ ĐỘNG TẠO BỆNH ÁN KHI TẠO PHIẾU KHÁM

DROP TRIGGER IF EXISTS trg_AutoCreateBenhAn;
GO

CREATE TRIGGER trg_AutoCreateBenhAn
ON PhieuKham
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;
    
    INSERT INTO BenhAn (
        id_pk, 
        id_bn, 
        id_bs, 
        ngaykhambenhan, 
        trangthai, 
        ghichu
     
    )
    SELECT 
        i.id_pk, 
        i.id_bn,
        i.id_bs,
        CAST(i.ngaykhamban AS DATE),
        N'Chờ khám',
        N'Tự động tạo từ phiếu khám #' + CAST(i.id_pk AS NVARCHAR)
    FROM inserted i
    WHERE i.id_bn IS NOT NULL 
      AND i.id_bs IS NOT NULL
      AND NOT EXISTS (
          SELECT 1 FROM BenhAn ba 
          WHERE ba.id_pk = i.id_pk
      );
END
GO

-- 4. SỬA PROCEDURE sp_ThemThuocVaoDon - THÊM KIỂM TRA BỆNH ÁN
ALTER PROCEDURE sp_ThemThuocVaoDon
    @id_ba INT,
    @id_thuoc INT,
    @soluong INT,
    @lieudung NVARCHAR(300)
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRAN;

    DECLARE @id_dt INT;
    DECLARE @ton INT;
    DECLARE @dongia DECIMAL(12,2);
    DECLARE @trangthai_ba NVARCHAR(50);

    -- 1. Kiểm tra bệnh án có tồn tại và đang ở trạng thái phù hợp
    SELECT @trangthai_ba = trangthai 
    FROM BenhAn 
    WHERE id_ba = @id_ba;

    IF @trangthai_ba IS NULL
    BEGIN
        ROLLBACK;
        RAISERROR(N'Bệnh án không tồn tại', 16, 1);
        RETURN;
    END

    IF @trangthai_ba = N'Đã thanh toán' OR @trangthai_ba = N'Đã hủy'
    BEGIN
        ROLLBACK;
        RAISERROR(N'Bệnh án đã đóng, không thể thêm thuốc', 16, 1);
        RETURN;
    END

    -- 2. Lấy giá thuốc hiện tại
    SELECT @dongia = giaban, @ton = soluongton 
    FROM Thuoc 
    WHERE id_thuoc = @id_thuoc;

    IF @dongia IS NULL
    BEGIN
        ROLLBACK;
        RAISERROR(N'Thuốc không tồn tại', 16, 1);
        RETURN;
    END

    IF @ton < @soluong
    BEGIN
        ROLLBACK;
        RAISERROR(N'Không đủ thuốc trong kho. Tồn kho: %d', 16, 1, @ton);
        RETURN;
    END

    -- 3. Lấy hoặc tạo đơn thuốc
    SELECT @id_dt = id_dt 
    FROM DonThuoc 
    WHERE id_ba = @id_ba;

    IF @id_dt IS NULL
    BEGIN
        INSERT INTO DonThuoc(id_ba, ngaylap)
        VALUES(@id_ba, GETDATE());

        SET @id_dt = SCOPE_IDENTITY();
    END

    -- 4. Thêm chi tiết đơn thuốc
    INSERT INTO ChiTiet_DonThuoc(id_dt, id_thuoc, soluong, lieudung, dongia)
    VALUES(@id_dt, @id_thuoc, @soluong, @lieudung, @dongia);

    -- 5. Trừ kho
    UPDATE Thuoc
    SET soluongton = soluongton - @soluong
    WHERE id_thuoc = @id_thuoc;

    -- 6. Cập nhật trạng thái bệnh án nếu cần
    IF @trangthai_ba = N'Chờ khám'
    BEGIN
        UPDATE BenhAn
        SET trangthai = N'Đang điều trị'
        WHERE id_ba = @id_ba;
    END

    COMMIT;
END
GO

CREATE TRIGGER trg_TruSoLuongThuoc
ON ChiTiet_DonThuoc
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE t
    SET t.soluongton = t.soluongton - i.soluong
    FROM Thuoc t
    INNER JOIN inserted i
        ON t.id_thuoc = i.id_thuoc;
END;
GO

CREATE TRIGGER trg_HoanSoLuongThuoc
ON ChiTiet_DonThuoc
AFTER DELETE
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE t
    SET t.soluongton = t.soluongton + d.soluong
    FROM Thuoc t
    JOIN deleted d ON t.id_thuoc = d.id_thuoc;
END;
GO


-- 5. THÊM TRIGGER TỰ ĐỘNG TẠO HÓA ĐƠN KHI CÓ DỊCH VỤ/THUỐC
GO
CREATE TRIGGER trg_AutoCreateHoaDon
ON BenhAn
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    
    -- Chỉ tạo hóa đơn khi bệnh án chuyển sang trạng thái "Đang điều trị" hoặc "Hoàn thành"
    IF UPDATE(trangthai)
    BEGIN
        INSERT INTO HoaDon (id_ba, id_bn, mahd, trangthai, ghichu)
        SELECT 
            i.id_ba,
            i.id_bn,
            'HD' + FORMAT(GETDATE(), 'yyyyMMddHHmmss') + CAST(i.id_ba AS NVARCHAR),
            N'Chưa thanh toán',
            N'Tự động tạo từ bệnh án #' + CAST(i.id_ba AS NVARCHAR)
        FROM inserted i
        LEFT JOIN HoaDon hd ON hd.id_ba = i.id_ba
        WHERE i.trangthai IN (N'Đang điều trị', N'Hoàn thành')
          AND hd.id_ba IS NULL;  -- Chưa có hóa đơn
    END
END
GO
-- 7. THÊM VIEW ĐỂ TRA CỨU
CREATE VIEW vw_BenhNhan_PhieuKham_BenhAn AS
SELECT 
    bn.id_bn,
    bn.hoten AS TenBenhNhan,
    bn.sdt,
    pk.id_pk,
    pk.trangthai AS TrangThaiPhieuKham,
    ba.id_ba,
    ba.ngaykhambenhan,
    ba.trangthai AS TrangThaiBenhAn,
    bs.hoten AS TenBacSi,
    hd.id_hd,
    hd.tongtien,
    hd.trangthai AS TrangThaiHoaDon
FROM BenhNhan bn
LEFT JOIN PhieuKham pk ON bn.id_bn = pk.id_bn
LEFT JOIN BenhAn ba ON pk.id_pk = ba.id_pk
LEFT JOIN BacSi bs ON ba.id_bs = bs.id_bs
LEFT JOIN HoaDon hd ON ba.id_ba = hd.id_ba;
GO



-- 10. THÊM CỘT THỜI GIAN CẬP NHẬT
ALTER TABLE BenhAn ADD updated_at DATETIME DEFAULT GETDATE();
ALTER TABLE PhieuKham ADD updated_at DATETIME DEFAULT GETDATE();
ALTER TABLE HoaDon ADD updated_at DATETIME DEFAULT GETDATE();
GO

CREATE TRIGGER trg_UpdateTimestamp_BenhAn
ON BenhAn
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE BenhAn SET updated_at = GETDATE()
    WHERE id_ba IN (SELECT id_ba FROM inserted);
END
GO