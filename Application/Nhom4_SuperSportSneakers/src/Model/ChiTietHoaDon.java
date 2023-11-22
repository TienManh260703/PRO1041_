/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author manhnt
 */
public class ChiTietHoaDon {

    private Long id;
    private HoaDon IdHoaDon;
    private SanPhamChiTiet IdCTSP;
    private Integer soLuong;
    private String maDGG;
    private Integer loaiDGG;
    private Float giaTriDGG;
    private Float quyDoiDGGTT;
    private Float GiaBan;
    private Float DonGia;
    private Float thanhTien;
    private Date ngayTao;
    private Integer trangThai;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(HoaDon IdHoaDon, SanPhamChiTiet IdCTSP, Integer soLuong) {
        this.IdHoaDon = IdHoaDon;
        this.IdCTSP = IdCTSP;
        this.soLuong = soLuong;
    }

    public ChiTietHoaDon(HoaDon IdHoaDon, SanPhamChiTiet IdCTSP, Integer soLuong, String maDGG, Integer loaiDGG, Float giaTriDGG, Float quyDoiDGGTT, Float GiaBan, Float DonGia, Integer trangThai) {
        this.IdHoaDon = IdHoaDon;
        this.IdCTSP = IdCTSP;
        this.soLuong = soLuong;
        this.maDGG = maDGG;
        this.loaiDGG = loaiDGG;
        this.giaTriDGG = giaTriDGG;
        this.quyDoiDGGTT = quyDoiDGGTT;
        this.GiaBan = GiaBan;
        this.DonGia = DonGia;
        this.trangThai = trangThai;
    }

    public ChiTietHoaDon(HoaDon IdHoaDon, SanPhamChiTiet IdCTSP, Integer soLuong, String maDGG, Integer loaiDGG, Float giaTriDGG, Float quyDoiDGGTT, Float GiaBan, Float DonGia, Float thanhTien) {
        this.IdHoaDon = IdHoaDon;
        this.IdCTSP = IdCTSP;
        this.soLuong = soLuong;
        this.maDGG = maDGG;
        this.loaiDGG = loaiDGG;
        this.giaTriDGG = giaTriDGG;
        this.quyDoiDGGTT = quyDoiDGGTT;
        this.GiaBan = GiaBan;
        this.DonGia = DonGia;
        this.thanhTien = thanhTien;

    }

    public ChiTietHoaDon(Long id, HoaDon IdHoaDon, SanPhamChiTiet IdCTSP, Integer soLuong, String maDGG, Integer loaiDGG, Float giaTriDGG, Float quyDoiDGGTT, Float GiaBan, Float DonGia, Float thanhTien) {
        this.id = id;
        this.IdHoaDon = IdHoaDon;
        this.IdCTSP = IdCTSP;
        this.soLuong = soLuong;
        this.maDGG = maDGG;
        this.loaiDGG = loaiDGG;
        this.giaTriDGG = giaTriDGG;
        this.quyDoiDGGTT = quyDoiDGGTT;
        this.GiaBan = GiaBan;
        this.DonGia = DonGia;
        this.thanhTien = thanhTien;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HoaDon getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(HoaDon IdHoaDon) {
        this.IdHoaDon = IdHoaDon;
    }

    public SanPhamChiTiet getIdCTSP() {
        return IdCTSP;
    }

    public void setIdCTSP(SanPhamChiTiet IdCTSP) {
        this.IdCTSP = IdCTSP;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaDGG() {
        return maDGG;
    }

    public void setMaDGG(String maDGG) {
        this.maDGG = maDGG;
    }

    public Integer getLoaiDGG() {
        return loaiDGG;
    }

    public Float getGiaTriDGG() {
        return giaTriDGG;
    }

    public void setGiaTriDGG(Float giaTriDGG) {
        this.giaTriDGG = giaTriDGG;
    }

    public Float getQuyDoiDGGTT() {
        return quyDoiDGGTT;
    }

    public void setQuyDoiDGGTT(Float quyDoiDGGTT) {
        this.quyDoiDGGTT = quyDoiDGGTT;
    }

    public Float getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Float GiaBan) {
        this.GiaBan = GiaBan;
    }

    public Float getDonGia() {
        return DonGia;
    }

    public void setDonGia(Float DonGia) {
        this.DonGia = DonGia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

//    public Float setGiamGia() {
//        if (IdCTSP.getIdDGG() != null && IdCTSP.getIdDGG().getTrangThai() == 1) {
//            if (IdCTSP.getIdDGG().getLoai() == 0) {
//                return IdCTSP.getIdDGG().getGiaTri();
//            } else if (IdCTSP.getIdDGG().getLoai() == 1) {
//                return IdCTSP.getGiaNiemYet() - IdCTSP.tinhGiaBan();
//            }
//        }
//        return 0f;
//    }

    public Float setThanhTien2() {
        Float thanhTien = soLuong * IdCTSP.tinhGiaBan();
        System.err.println("TT : " + thanhTien);
        return thanhTien;
    }

    public Object[] rowDataGioHang() {
        String str = "";
        if (IdCTSP.getIdDGG() != null && IdCTSP.getIdDGG().getTrangThai() == 1) {
            if (IdCTSP.getIdDGG().getHinhThucDGG() == 0) {
                str = " ( " + IdCTSP.getIdDGG().getGiaTri() + " %)  ";
            } else if (IdCTSP.getIdDGG().getHinhThucDGG() == 1) {//(IdCTSP.getGiaNiemYet() - IdCTSP.tinhGiaBan()) + 
                str = " VND ";
            }
        }

        return new Object[]{
            IdCTSP.getMaSPCT(),
            IdCTSP.getIdSanPham().getTenSanpham(),
            IdCTSP.getGiaNiemYet(),
            soLuong,
          ( IdCTSP.getGiaBan() - IdCTSP.tinhGiaBan())+ str,
            IdCTSP.tinhGiaBan(),
            setThanhTien2(),
            id
        };
    }
    
    

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "id=" + id + ", IdHoaDon=" + IdHoaDon + ", IdCTSP=" + IdCTSP + ", soLuong=" + soLuong + ", maDGG=" + maDGG + ", loaiDGG=" + loaiDGG + ", giaTriDGG=" + giaTriDGG + ", quyDoiDGGTT=" + quyDoiDGGTT + ", GiaBan=" + GiaBan + ", DonGia=" + DonGia + ", thanhTien=" + thanhTien + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + '}';
    }

}
