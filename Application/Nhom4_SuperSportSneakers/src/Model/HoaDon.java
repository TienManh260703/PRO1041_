/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author manhnt
 */
public class HoaDon {

    private Long id;
    private PhieuGiamGia IdPGG;
    private NhanVien IdNV;
    private KhachHang IdKH;
    private String maHoaDon;
    private Integer capBac;
    private Float phanTramGG;//	PhanTramGia INT NULL , --- CẤP BẬC + PHIẾU THEO % ++ Doi thanh FLoat
    private Float tienPhieuGiam;
    private Float diemDoi; //	DiemDoi INT NULL, -- tiền khách hàng từ đó lấy ra tiền 1 diem = 10k => MONEY
    private Integer phuongThucTT;
    private Float tienKhDua;
    private Float tienKhChuyenKhoan;
    private Float tienThua;
    private Float thanhTien;
    private Date ngayTao;
    private Date ngayThanhToan;
    private boolean hinhThucMua;
    private Integer trangThai;

    public HoaDon() {
    }

    public HoaDon(NhanVien IdNV, KhachHang IdKH, String maHoaDon, Integer capBac, Float phanTramGG, Float tienPhieuGiam, Float diemDoi, Integer phuongThucTT, Float tienKhDua, Float tienKhChuyenKhoan, Float tienThua, Float thanhTien, Date ngayThanhToan, boolean hinhThucMua, Integer trangThai) {
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.maHoaDon = maHoaDon;
        this.capBac = capBac;
        this.phanTramGG = phanTramGG;
        this.tienPhieuGiam = tienPhieuGiam;
        this.diemDoi = diemDoi;
        this.phuongThucTT = phuongThucTT;
        this.tienKhDua = tienKhDua;
        this.tienKhChuyenKhoan = tienKhChuyenKhoan;
        this.tienThua = tienThua;
        this.thanhTien = thanhTien;
        this.ngayThanhToan = ngayThanhToan;
        this.hinhThucMua = hinhThucMua;
        this.trangThai = trangThai;
    }

    public HoaDon(PhieuGiamGia IdPGG, NhanVien IdNV, KhachHang IdKH, String maHoaDon) {
        this.IdPGG = IdPGG;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.maHoaDon = maHoaDon;
    }

   

    public HoaDon(Long id, PhieuGiamGia IdPGG, NhanVien IdNV, KhachHang IdKH, Integer trangThai) {
        this.id = id;
        this.IdPGG = IdPGG;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.trangThai = trangThai;
    }

    public HoaDon(Long id, NhanVien IdNV, KhachHang IdKH, Integer trangThai) {
        this.id = id;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.trangThai = trangThai;
    }

    public HoaDon(Long id,PhieuGiamGia IdPGG, NhanVien IdNV, KhachHang IdKH, String maHoaDon,Date ngayTao ,Integer trangThai) {
        this.id = id;
        this.IdPGG = IdPGG;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.maHoaDon = maHoaDon;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    public HoaDon(PhieuGiamGia IdPGG, NhanVien IdNV, KhachHang IdKH, String maHoaDon, Integer capBac, Float phanTramGG, Float tienPhieuGiam, Float diemDoi, Integer phuongThucTT, Float TienKhDua, Float TienKhChuyenKhoan, Float TienThua, Float ThanhTien, boolean hinhThucMua, Integer trangThai) {
        this.IdPGG = IdPGG;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.maHoaDon = maHoaDon;
        this.capBac = capBac;
        this.phanTramGG = phanTramGG;
        this.tienPhieuGiam = tienPhieuGiam;
        this.diemDoi = diemDoi;
        this.phuongThucTT = phuongThucTT;
        this.tienKhDua = TienKhDua;
        this.tienKhChuyenKhoan = TienKhChuyenKhoan;
        this.tienThua = TienThua;
        this.thanhTien = ThanhTien;
        this.hinhThucMua = hinhThucMua;
        this.trangThai = trangThai;
    }

    public HoaDon(PhieuGiamGia IdPGG, NhanVien IdNV, KhachHang IdKH, String maHoaDon, Integer capBac, Float phanTramGG, Float tienPhieuGiam, Float diemDoi, Integer phuongThucTT, Float TienKhDua, Float TienKhChuyenKhoan, Float TienThua, Float ThanhTien, Date NgayThanhToan, boolean hinhThucMua, Integer trangThai) {
        this.IdPGG = IdPGG;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.maHoaDon = maHoaDon;
        this.capBac = capBac;
        this.phanTramGG = phanTramGG;
        this.tienPhieuGiam = tienPhieuGiam;
        this.diemDoi = diemDoi;
        this.phuongThucTT = phuongThucTT;
        this.tienKhDua = TienKhDua;
        this.tienKhChuyenKhoan = TienKhChuyenKhoan;
        this.tienThua = TienThua;
        this.thanhTien = ThanhTien;
        this.ngayThanhToan = NgayThanhToan;
        this.hinhThucMua = hinhThucMua;
        this.trangThai = trangThai;
    }

    public HoaDon(Long id, PhieuGiamGia IdPGG, NhanVien IdNV, KhachHang IdKH, String maHoaDon, Integer capBac, Float phanTramGG, Float tienPhieuGiam, Float diemDoi, Integer phuongThucTT, Float tienKhDua, Float tienKhChuyenKhoan, Float tienThua, Float thanhTien, boolean hinhThucMua, Integer trangThai) {
        this.id = id;
        this.IdPGG = IdPGG;
        this.IdNV = IdNV;
        this.IdKH = IdKH;
        this.maHoaDon = maHoaDon;
        this.capBac = capBac;
        this.phanTramGG = phanTramGG;
        this.tienPhieuGiam = tienPhieuGiam;
        this.diemDoi = diemDoi;
        this.phuongThucTT = phuongThucTT;
        this.tienKhDua = tienKhDua;
        this.tienKhChuyenKhoan = tienKhChuyenKhoan;
        this.tienThua = tienThua;
        this.thanhTien = thanhTien;
        this.hinhThucMua = hinhThucMua;
        this.trangThai = trangThai;
    }

    public HoaDon(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhieuGiamGia getIdPGG() {
        return IdPGG;
    }

    public void setIdPGG(PhieuGiamGia IdPGG) {
        this.IdPGG = IdPGG;
    }

    public NhanVien getIdNV() {
        return IdNV;
    }

    public void setIdNV(NhanVien IdNV) {
        this.IdNV = IdNV;
    }

    public KhachHang getIdKH() {
        return IdKH;
    }

    public void setIdKH(KhachHang IdKH) {
        this.IdKH = IdKH;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Integer getCapBac() {
        return capBac;
    }

    public void setCapBac(Integer capBac) {
        this.capBac = capBac;
    }

    public Float getPhanTramGG() {
        return phanTramGG;
    }

    public void setPhanTramGG(Float phanTramGG) {
        this.phanTramGG = phanTramGG;
    }

    public Float getTienPhieuGiam() {
        return tienPhieuGiam;
    }

    public void setTienPhieuGiam(Float tienPhieuGiam) {
        this.tienPhieuGiam = tienPhieuGiam;
    }

    public Float getDiemDoi() {
        return diemDoi;
    }

    public void setDiemDoi(Float diemDoi) {
        this.diemDoi = diemDoi;
    }

    public Integer getPhuongThucTT() {
        return phuongThucTT;
    }

    public void setPhuongThucTT(Integer phuongThucTT) {
        this.phuongThucTT = phuongThucTT;
    }

    public Float getTienKhDua() {
        return tienKhDua;
    }

    public void setTienKhDua(Float TienKhDua) {
        this.tienKhDua = TienKhDua;
    }

    public Float getTienKhChuyenKhoan() {
        return tienKhChuyenKhoan;
    }

    public void setTienKhChuyenKhoan(Float TienKhChuyenKhoan) {
        this.tienKhChuyenKhoan = TienKhChuyenKhoan;
    }

    public Float getTienThua() {
        return tienThua;
    }

    public void setTienThua(Float TienThua) {
        this.tienThua = TienThua;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Float ThanhTien) {
        this.thanhTien = ThanhTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.ngayTao = NgayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.ngayThanhToan = NgayThanhToan;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isHinhThucMua() {
        return hinhThucMua;
    }

    public void setHinhThucMua(boolean hinhThucMua) {
        this.hinhThucMua = hinhThucMua;
    }

    private String setTrangThaiHD(int trangThaiIP) {
        switch (trangThaiIP) {
            case 0:
                return "Chờ thanh toán";
            case 1:
                return "Đã thanh toán";
            case 2:
                return "Đang giao";
            case 3:
                return "Thanh toán trước";
            case 4:
                return "Đã hủy";
            default:
                return "Đang nghĩ";
        }
    }

    public Object[] rowDataHDBH() {
        return new Object[]{
            maHoaDon, ngayTao, IdNV.getMaNhanVien(), IdKH.getMaKhachHang(), this.setTrangThaiHD(trangThai)
        };
    }

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", IdPGG=" + IdPGG + ", IdNV=" + IdNV + ", IdKH=" + IdKH + ", maHoaDon=" + maHoaDon + ", capBac=" + capBac + ", phanTramGG=" + phanTramGG + ", tienPhieuGiam=" + tienPhieuGiam + ", diemDoi=" + diemDoi + ", phuongThucTT=" + phuongThucTT + ", TienKhDua=" + tienKhDua + ", TienKhChuyenKhoan=" + tienKhChuyenKhoan + ", TienThua=" + tienThua + ", ThanhTien=" + thanhTien + ", NgayTao=" + ngayTao + ", NgayThanhToan=" + ngayThanhToan + ", hinhThucMua=" + hinhThucMua + ", trangThai=" + trangThai + '}';
    }
    
    
}
