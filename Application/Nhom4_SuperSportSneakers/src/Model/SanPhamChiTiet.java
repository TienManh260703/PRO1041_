/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author manhnt
 */
public class SanPhamChiTiet {

    private Long idSPCT;
    private DotGiamGia_M idDGG;
    private String maSPCT;
    private int soLuong;
    private Float giaBan;
    private Float giaNiemYet;
    private int TrangThai;
    private String moTa;
    private MauSac idMau;
    private KichThuoc idKichThuoc;
    private ThuongHieu idThuongHieu;
    private SanPham idSanPham;
    
    


    public SanPhamChiTiet() {
    }
    
     public SanPhamChiTiet(Long idSPCT, String maSPCT, int soLuong, Float giaBan, Float giaNiemYet, int TrangThai, String moTa, MauSac idMau, KichThuoc idKichThuoc, ThuongHieu idThuongHieu, SanPham idSanPham) {
        this.idSPCT = idSPCT;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaNiemYet = giaNiemYet;
        this.TrangThai = TrangThai;
        this.moTa = moTa;
        this.idMau = idMau;
        this.idKichThuoc = idKichThuoc;
        this.idThuongHieu = idThuongHieu;
        this.idSanPham = idSanPham;
    }

    public SanPhamChiTiet(String maSPCT, int soLuong, Float giaBan, Float giaNiemYet, int TrangThai, String moTa, MauSac idMau, KichThuoc idKichThuoc, ThuongHieu idThuongHieu, SanPham idSanPham) {
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaNiemYet = giaNiemYet;
        this.TrangThai = TrangThai;
        this.moTa = moTa;
        this.idMau = idMau;
        this.idKichThuoc = idKichThuoc;
        this.idThuongHieu = idThuongHieu;
        this.idSanPham = idSanPham;
    }
    

    public SanPhamChiTiet(Long idSPCT, DotGiamGia_M idDGG, String maSPCT, int soLuong, Float giaBan, Float giaNiemYet, SanPham idSanPham) {
        this.idSPCT = idSPCT;
        this.idDGG = idDGG;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaNiemYet = giaNiemYet;
        this.idSanPham = idSanPham;
    }
     public SanPhamChiTiet(Long idSPCT, DotGiamGia_M idDGG, String maSPCT, Float giaBan, Float giaNiemYet, SanPham idSanPham) {
        this.idSPCT = idSPCT;
        this.idDGG = idDGG;
        this.maSPCT = maSPCT;
        this.giaBan = giaBan;
        this.giaNiemYet = giaNiemYet;
        this.idSanPham = idSanPham;
    }

    public SanPhamChiTiet(Long idSPCT, String maSPCT, int soLuong, Float giaBan, Float giaNiemYet, MauSac idMau, KichThuoc idKichThuoc, ThuongHieu idThuongHieu, SanPham idSanPham) {
        this.idSPCT = idSPCT;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaNiemYet = giaNiemYet;
        this.idMau = idMau;
        this.idKichThuoc = idKichThuoc;
        this.idThuongHieu = idThuongHieu;
        this.idSanPham = idSanPham;

    }

 

    public SanPhamChiTiet(Long idSPCT, DotGiamGia_M idDGG, String maSPCT, int soLuong, Float giaBan, Float giaNiemYet, MauSac idMau, KichThuoc idKichThuoc, ThuongHieu idThuongHieu, SanPham idSanPham) {
        this.idSPCT = idSPCT;
        this.idDGG = idDGG;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giaNiemYet = giaNiemYet;

        this.idMau = idMau;
        this.idKichThuoc = idKichThuoc;
        this.idThuongHieu = idThuongHieu;
        this.idSanPham = idSanPham;
    }

    public Long getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(Long idSPCT) {
        this.idSPCT = idSPCT;
    }

    public DotGiamGia_M getIdDGG() {
        return idDGG;
    }

    public void setIdDGG(DotGiamGia_M idDGG) {
        this.idDGG = idDGG;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Float giaBan) {
        this.giaBan = giaBan;
    }

    public Float getGiaNiemYet() {
        return giaNiemYet;
    }

    public void setGiaNiemYet(Float giaNiemYet) {
        this.giaNiemYet = giaNiemYet;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public MauSac getIdMau() {
        return idMau;
    }

    public void setIdMau(MauSac idMau) {
        this.idMau = idMau;
    }

    public KichThuoc getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(KichThuoc idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public ThuongHieu getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(ThuongHieu idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public SanPham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(SanPham idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Float tinhGiaBan() {
        if(this.idDGG== null){
            return giaBan;
        }
        if (this.idDGG != null && idDGG.getTrangThai()==1) {
//            System.out.println("iDDGG KO NULL : ");
//            if (idDGG.getTrangThai() == 1) {
//                System.out.println("iDDGG TRANG THAI 1 : ");
                Integer checkLoai = this.idDGG.getHinhThucDGG();
                if (checkLoai==0) {
//                    System.out.println("LOAI 0 % ");
//                    System.out.println("Model.SanPhamChiTiet. GT 0 "+(idDGG.getGiaTri() / (float) 100));
//                    System.out.println("Model.SanPhamChiTiet. GT 1 "+((idDGG.getGiaTri() / (float) 100) * giaNiemYet));
                    giaBan = giaNiemYet - ((idDGG.getGiaTri() / (float) 100) * giaNiemYet);
                    System.out.println("loai 0 ... %: " + giaBan);
                    return giaBan;
                } else if(checkLoai==1) {
                    giaBan = giaNiemYet - idDGG.getGiaTri();
//                    System.out.println("loai 1: VND" + giaBan);
                    return giaBan;
                }
                return giaBan;
//            }
//            System.out.println("KO VÀO TRANG THAI" + giaBan);
//            return giaBan;
        } else {
            giaBan = giaNiemYet;
    //        System.out.println("yet = ban");
            return giaBan;
        }
    }

    public Float khoiPhucGiaBan() {
        if (this.idDGG == null) {
            giaBan = giaNiemYet;
        }
        return giaBan;
    }

    public int getLoaiDGG() {
        if (null==idDGG.getHinhThucDGG()) {
            return 2;
        } else switch (idDGG.getHinhThucDGG()) {
            case 1:
                return 1;
            case 0:
                return 0;
            default:
                return 2;
        }
    }

    public Float getGiaTriDGG() {
        if (this.idDGG != null) {
            return idDGG.getGiaTri();
        } else {
            return -1.0f;
        }
    }

    public Float getGiaGiam() {
        return this.giaNiemYet - tinhGiaBan();
    }

    public Object[] rowDataSPBH() {
        
        return new Object[]{
            maSPCT, idSanPham.getTenSanpham(), idMau.getTenMau(), idKichThuoc.getTenSize(), idThuongHieu.getTenThuongHieu(), soLuong, giaNiemYet
        };
    }

    @Override
    public String toString() {
        return "ChiTietSanPham_M{" + "idSPCT=" + idSPCT + ", idDGG=" + idDGG + ", maSPCT=" + maSPCT + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", giaNiemYet=" + giaNiemYet + ", TrangThai=" + TrangThai + ", moTa=" + moTa + ", idMau="/* + idMau.getIdMau() + ", idKichThuoc=" + idKichThuoc.getIdSize() + ", idThuongHieu=" + idThuongHieu.getIdThuongHieu() + ", idSanPham=" + idSanPham.getIdSanPham() + */+'}';
    }

}
