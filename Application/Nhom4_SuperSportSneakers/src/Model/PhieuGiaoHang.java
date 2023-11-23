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
public class PhieuGiaoHang {

    private Long id;
    private HoaDon idHD;
    private KhachHang idKH;
    private String tenNguoiNhan;
    private String sdtNNguoiNhan;
    private String diaChi;
    private String tenShip;
    private String sdtShip;
    private Float giaShip;
    private Date ngayTao;
    private String maVanDon;
    private String donViVanChuyen;
    private Date ngayHoanThanh;
    private Integer trangThai;

    public PhieuGiaoHang() {
    }

    public PhieuGiaoHang(Long id, HoaDon idHD, KhachHang idKH, String tenNguoiNhan, String sdtNNguoiNhan, String diaChi, String tenShip, String sdtShip, Date ngayTao, String maVanDon, String donViVanChuyen, Date ngayHoanThanh, Integer trangThai) {
        this.id = id;
        this.idHD = idHD;
        this.idKH = idKH;
        this.tenNguoiNhan = tenNguoiNhan;
        this.sdtNNguoiNhan = sdtNNguoiNhan;
        this.diaChi = diaChi;
        this.tenShip = tenShip;
        this.sdtShip = sdtShip;
        this.ngayTao = ngayTao;
        this.maVanDon = maVanDon;
        this.donViVanChuyen = donViVanChuyen;
        this.ngayHoanThanh = ngayHoanThanh;
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getGiaShip() {
        return giaShip;
    }

    public void setGiaShip(Float giaShip) {
        this.giaShip = giaShip;
    }

    public HoaDon getIdHD() {
        return idHD;
    }

    public void setIdHD(HoaDon idHD) {
        this.idHD = idHD;
    }

    public KhachHang getIdKH() {
        return idKH;
    }

    public void setIdKH(KhachHang idKH) {
        this.idKH = idKH;
    }

    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getSdtNNguoiNhan() {
        return sdtNNguoiNhan;
    }

    public void setSdtNNguoiNhan(String sdtNNguoiNhan) {
        this.sdtNNguoiNhan = sdtNNguoiNhan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenShip() {
        return tenShip;
    }

    public void setTenShip(String tenShip) {
        this.tenShip = tenShip;
    }

    public String getSdtShip() {
        return sdtShip;
    }

    public void setSdtShip(String sdtShip) {
        this.sdtShip = sdtShip;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getMaVanDon() {
        return maVanDon;
    }

    public void setMaVanDon(String maVanDon) {
        this.maVanDon = maVanDon;
    }

    public String getDonViVanChuyen() {
        return donViVanChuyen;
    }

    public void setDonViVanChuyen(String donViVanChuyen) {
        this.donViVanChuyen = donViVanChuyen;
    }

    public Date getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(Date ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String setTrangThaiString(int tt) {
        if (tt == 0) {
            return "Đang tạo";
        } else if (tt == 1) {
            return "Chờ giao";
        } else if (tt == 2) {
            return "Đang giao";
        } else if (tt == 3) {
            return "Hẹn lại";
        } else {
            return "Hủy";
        }
    }

    public Object[] rowData(int iindex) {
        
        String result = (trangThai == null) ? "Giá trị null" :
                (trangThai == 0 ? "Đang tạo" :
                (trangThai == 1 ? "Chờ giao" :
                (trangThai == 2 ? "Đang giao" :
                (trangThai == 3 ? "Hẹn lại" :
                "Hủy"))));
        return new Object[]{
            iindex, maVanDon, idHD.getMaHoaDon(), idKH.getTenKhachHang(), idKH.getSdt(), giaShip, tenShip, sdtShip, ngayTao, ngayHoanThanh,result
//            trangThai=0 ? "Đang tạo":( (trangThai==1 ? "Chờ giao": (trangThai ==2 ? "Đang giao":(trangThai==3 ? "Hẹn lại" : "Hủy"))))
        };
    }

}
