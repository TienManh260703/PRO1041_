/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.ChiTietHoaDon;
import Model.SanPhamChiTiet;
import Model.DotGiamGia_M;
import Model.HoaDon;
import Model.KhachHang;
import Model.NhanVien;
import Model.PhieuGiamGia;
import Model.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manhnt
 */
public class ChiTietHoaDon_RepositoryM {

    private DotGiamGia_MRpository dotGiamGia_MRpository = new DotGiamGia_MRpository();
    private String query = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;

    public List<ChiTietHoaDon> getAllHDCT(String maHD) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try {
            query = "SELECT \n"
                    + "             HDCT.ID AS IdHDCT , HD.ID  AS IdHD , HD.MaHoaDon AS MaHoaDon  , HD.NgayTao AS NgayTao , HD.TrangThai AS TrangThaiHD , HD.CapBac AS CAPBAC , HD.PhanTramGia AS PhanTramGia, HD.TienPhieuGiam AS TienPhieuGiam , HD.DiemDoi AS DiemDoi , HD.PhuongThucTT AS PhuongThucTT , \n"
                    + "             HD.TienKhDua AS TienKhDua , HD.TienKhChuyenKhoan AS TienKhChuyenKhoan ,HD.TienThua AS TienThua , HD.ThanhTien AS ThanhTien , HD.HinhThucMua AS  HinhThucMua , HD.TrangThai AS TrangThaiHD ,\n"
                    + "             CTSP.ID AS IdCTSP,CTSP.MaCTSP AS MaCTSP , CTSP.GiaBan AS GiaBan ,  CTSP.GiaNiemYet  AS GiaNiemYet,\n"
                    + "             SP.ID AS IDSP ,SP.MaSP AS MASP , SP.TenSP AS TENSP ,\n"
                    + "             DGG.ID AS IDDGG , DGG.MaDGG AS MADGG ,  DGG.TenDGG AS TENDGG , DGG.Loai AS LOAIDGG , DGG.GiaTri AS GIATRI , DGG.NgayBatDau AS NGAYBD , DGG.NgayKetThuc AS NGAYKT , DGG.MoTa AS MOTA , DGG.TrangThai TRANGTHAIDGG,\n"
                    + "             NV.ID AS IdNV , NV.HoVaTen AS TenNV , NV.MaNhanVien AS MaNhanVien , \n"
                    + "             KH.ID AS IdKH , KH.TenKhachHang AS TenKH , KH.MaKhachHang AS  MaKhachHang ,\n"
                    + "             PGG.ID AS IdPGG , PGG.MaPhieu AS  MaPhieu , PGG.TenPhieu AS TenPhieu , PGG.GiaTri AS GiaTri  , PGG.LoaiPhieu AS  LoaiPhieu, PGG.SoLuongPhieu AS SoLuongPhieu  , PGG.DonToiThieu AS DonToiThieu  , PGG.TrangThai AS TrangThaiPGG ,\n"
                    + "             HDCT.IdCTSP AS IdCTSP , HDCT.SoLuong AS SoLuongHDCT , HDCT.MaDGG AS MaDGGHDCT , HDCT.LoaiDGG AS LoaiGGHDCT , HDCT.GiaTriDGG AS GiaTriHDCT , HDCT.QuyDoiDGGTT AS QuyDoiDGGTT , HDCT.GiaBan AS GiaBanHDCT , HDCT.DonGia AS DonGiaHDCT , HDCT.ThanhTien AS ThanhtIENHDCT \n"
                    + "              FROM HOADONCHITIET AS HDCT\n"
                    + "			LEFT JOIN HOADON AS HD ON HD.ID = HDCT.IdHoaDon\n"
                    + "			LEFT JOIN CHI_TIET_SAN_PHAM AS CTSP ON CTSP.ID = HDCT.IdCTSP\n"
                    + "			LEFT JOIN SANPHAM AS SP  ON SP.ID = CTSP.ID\n"
                    + "			LEFT JOIN DOT_GIAM_GIA AS DGG ON DGG.ID = CTSP.IdDGG\n"
                    + "			LEFT JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH \n"
                    + "                 LEFT JOIN NHANVIEN AS NV ON NV.ID = HD.IdNV\n"
                    + "			LEFT JOIN PHIEU_GIAM_GIA AS PGG ON PGG.ID  = HD.IdPGG \n"
                    + " WHERE HD.MaHoaDon LIKE '" + maHD + "' "
                    + "              ORDER BY HDCT.NgayTao  DESC";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(rs.getLong("IdNV"), rs.getString("MaNhanVien"), rs.getString("TenNV"));
                KhachHang khachHang = new KhachHang(rs.getLong("IdKH"), rs.getString("MaKhachHang"), rs.getString("TenKH"));
                SanPham sanPham = new SanPham(rs.getLong("IDSP"), rs.getString("MASP"), rs.getString("TENSP"));
                DotGiamGia_M dotGiamGia = new DotGiamGia_M(rs.getLong("IDDGG"), nhanVien, rs.getString("MADGG"), rs.getString("TENDGG"), rs.getInt("LOAIDGG"), rs.getFloat("GIATRI"), rs.getDate("NGAYBD"), rs.getDate("NGAYKT"), rs.getString("MOTA"), rs.getInt("TRANGTHAIDGG"));

                //  DotGiamGia_M dotGiamGia = dotGiamGia_MRpository.getDGGByMaCTSP(rs.getString("MaCTSP"));
                System.out.println("GG :" + dotGiamGia);
                SanPhamChiTiet chiTietSanPham = new SanPhamChiTiet(rs.getLong("IdCTSP"), dotGiamGia, rs.getString("MaCTSP"), rs.getFloat("GiaBan"), rs.getFloat("GiaNiemYet"), sanPham);
                PhieuGiamGia phieuGiamGia = new PhieuGiamGia();
                phieuGiamGia.setIdPGG(rs.getLong("IdPGG"));
                phieuGiamGia.setMaPhieu(rs.getString("MaPhieu"));
                phieuGiamGia.setTenPhieu(rs.getString("TenPhieu"));
                phieuGiamGia.setLoaiPhieu(rs.getInt("LoaiPhieu"));
                phieuGiamGia.setGiaTri(rs.getFloat("GiaTri"));
                phieuGiamGia.setSoLuongPhieu(rs.getInt("SoLuongPhieu"));
                phieuGiamGia.setDonToiThieu(rs.getFloat("DonToiThieu"));
                phieuGiamGia.setTrangThai(rs.getInt("TrangThaiPGG"));
                HoaDon hoaDon = new HoaDon(phieuGiamGia, nhanVien, khachHang, rs.getString("MaHoaDon"), rs.getInt("CAPBAC"), rs.getFloat("PhanTramGia"), rs.getFloat("TienPhieuGiam"), rs.getFloat("DiemDoi"), rs.getInt("PhuongThucTT"), rs.getFloat("TienKhDua"), rs.getFloat("TienKhChuyenKhoan"), rs.getFloat("TienThua"), rs.getFloat("ThanhTien"), rs.getBoolean("HinhThucMua"), rs.getInt("TrangThaiHD"));
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, chiTietSanPham, rs.getInt("SoLuongHDCT"), rs.getString("MaDGGHDCT"), rs.getInt("LoaiGGHDCT"), rs.getFloat("GiaTriHDCT"), rs.getFloat("QuyDoiDGGTT"), rs.getFloat("GiaBanHDCT"), rs.getFloat("DonGiaHDCT"), rs.getFloat("ThanhtIENHDCT"));
                list.add(chiTietHoaDon);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int insertCTHD(ChiTietHoaDon chiTietHoaDon) {

        try {
            query = "INSERT INTO HOADONCHITIET ( IdHoaDon , IdCTSP , SoLuong , MaDGG , LoaiDGG , GiaTriDGG , QuyDoiDGGTT  , DonGia , GiaBan , ThanhTien) VALUES \n"
                    + "	(? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setLong(1, chiTietHoaDon.getIdHoaDon().getId());
            pstm.setLong(2, chiTietHoaDon.getIdCTSP().getIdSPCT());
            pstm.setInt(3, chiTietHoaDon.getSoLuong());
            pstm.setString(4, chiTietHoaDon.getMaDGG());
            pstm.setInt(5, chiTietHoaDon.getLoaiDGG());
            pstm.setFloat(6, chiTietHoaDon.getGiaTriDGG());
            pstm.setFloat(7, chiTietHoaDon.getQuyDoiDGGTT());
            pstm.setFloat(8, chiTietHoaDon.getDonGia());
            pstm.setFloat(9, chiTietHoaDon.getGiaBan());
            pstm.setFloat(10, chiTietHoaDon.getThanhTien());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public ChiTietHoaDon fildCTHDByMaHoaDon(String maHoaDon) {

        try {
            query = "SELECT \n"
                    + "             HDCT.ID AS IdHDCT , HD.ID  AS IdHD , HD.MaHoaDon AS MaHoaDon  , HD.NgayTao AS NgayTao , HD.TrangThai AS TrangThaiHD , HD.CapBac AS CAPBAC , HD.PhanTramGia AS PhanTramGia, HD.TienPhieuGiam AS TienPhieuGiam , HD.DiemDoi AS DiemDoi , HD.PhuongThucTT AS PhuongThucTT , \n"
                    + "             HD.TienKhDua AS TienKhDua , HD.TienKhChuyenKhoan AS TienKhChuyenKhoan ,HD.TienThua AS TienThua , HD.ThanhTien AS ThanhTien , HD.HinhThucMua AS  HinhThucMua , HD.TrangThai AS TrangThaiHD ,\n"
                    + "             CTSP.ID AS IdCTSP,CTSP.MaCTSP AS MaCTSP , CTSP.GiaBan AS GiaBan ,  CTSP.GiaNiemYet  AS GiaNiemYet,\n"
                    + "             SP.ID AS IDSP ,SP.MaSP AS MASP , SP.TenSP AS TENSP ,\n"
                    + "             DGG.ID AS IDDGG , DGG.MaDGG AS MADGG ,  DGG.TenDGG AS TENDGG , DGG.Loai AS LOAIDGG , DGG.GiaTri AS GIATRI , DGG.NgayBatDau AS NGAYBD , DGG.NgayKetThuc AS NGAYKT , DGG.MoTa AS MOTA , DGG.TrangThai TRANGTHAIDGG,\n"
                    + "             NV.ID AS IdNV , NV.HoVaTen AS TenNV , NV.MaNhanVien AS MaNhanVien , \n"
                    + "             KH.ID AS IdKH , KH.TenKhachHang AS TenKH , KH.MaKhachHang AS  MaKhachHang ,\n"
                    + "             PGG.ID AS IdPGG , PGG.MaPhieu AS  MaPhieu , PGG.TenPhieu AS TenPhieu , PGG.GiaTri AS GiaTri  , PGG.LoaiPhieu AS  LoaiPhieu, PGG.SoLuongPhieu AS SoLuongPhieu  , PGG.DonToiThieu AS DonToiThieu  , PGG.TrangThai AS TrangThaiPGG ,\n"
                    + "             HDCT.IdCTSP AS IdCTSP , HDCT.SoLuong AS SoLuongHDCT , HDCT.MaDGG AS MaDGGHDCT , HDCT.LoaiDGG AS LoaiGGHDCT , HDCT.GiaTriDGG AS GiaTriHDCT , HDCT.QuyDoiDGGTT AS QuyDoiDGGTT , HDCT.GiaBan AS GiaBanHDCT , HDCT.DonGia AS DonGiaHDCT , HDCT.ThanhTien AS ThanhtIENHDCT \n"
                    + "              FROM HOADONCHITIET AS HDCT\n"
                    + "			LEFT JOIN HOADON AS HD ON HD.ID = HDCT.IdHoaDon\n"
                    + "			LEFT JOIN CHI_TIET_SAN_PHAM AS CTSP ON CTSP.ID = HDCT.IdCTSP\n"
                    + "			LEFT JOIN SANPHAM AS SP  ON SP.ID = CTSP.ID\n"
                    + "			LEFT JOIN DOT_GIAM_GIA AS DGG ON DGG.ID = CTSP.IdDGG\n"
                    + "			LEFT JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH \n"
                    + "                 LEFT JOIN NHANVIEN AS NV ON NV.ID = HD.IdNV\n"
                    + "			LEFT JOIN PHIEU_GIAM_GIA AS PGG ON PGG.ID  = HD.IdPGG \n"
                    + " WHERE HD.MaHoaDon LIKE '" + maHoaDon + "' "
                    + "              ORDER BY HDCT.NgayTao  DESC";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            if (rs.next()) {
                NhanVien nhanVien = new NhanVien(rs.getLong("IdNV"), rs.getString("MaNhanVien"), rs.getString("TenNV"));
                KhachHang khachHang = new KhachHang(rs.getLong("IdKH"), rs.getString("MaKhachHang"), rs.getString("TenKH"));
                SanPham sanPham = new SanPham(rs.getLong("IDSP"), rs.getString("MASP"), rs.getString("TENSP"));
                DotGiamGia_M dotGiamGia = new DotGiamGia_M(rs.getLong("IDDGG"), nhanVien, rs.getString("MADGG"), rs.getString("TENDGG"), rs.getInt("LOAIDGG"), rs.getFloat("GIATRI"), rs.getDate("NGAYBD"), rs.getDate("NGAYKT"), rs.getString("MOTA"), rs.getInt("TRANGTHAIDGG"));
                SanPhamChiTiet chiTietSanPham = new SanPhamChiTiet(rs.getLong("IdCTSP"), dotGiamGia, rs.getString("MASP"), rs.getFloat("GiaBan"), rs.getFloat("GiaNiemYet"), sanPham);
//                PhieuGiamGia_M phieuGiamGia = new PhieuGiamGia_M(
//                        rs.getLong("IdPGG"),
//                        rs.getString("MaPhieu"), 
//                        rs.getString("TenPhieu"), 
//                        rs.getBoolean("LoaiPhieu"), 
//                        rs.getFloat("GiaTri"), 
//                        rs.getInt("SoLuongPhieu"), 
//                        rs.getFloat("DonToiThieu"), 
//                        rs.getInt("TrangThaiPGG"));
               
                
                 PhieuGiamGia phieuGiamGia = new PhieuGiamGia();
                phieuGiamGia.setIdPGG(rs.getLong("IdPGG"));
                phieuGiamGia.setMaPhieu(rs.getString("MaPhieu"));
                phieuGiamGia.setTenPhieu(rs.getString("TenPhieu"));
                phieuGiamGia.setLoaiPhieu(rs.getInt("LoaiPhieu"));
                phieuGiamGia.setGiaTri(rs.getFloat("GiaTri"));
                phieuGiamGia.setSoLuongPhieu(rs.getInt("SoLuongPhieu"));
                phieuGiamGia.setDonToiThieu(rs.getFloat("DonToiThieu"));
                phieuGiamGia.setTrangThai(rs.getInt("TrangThaiPGG"));
               
                
                HoaDon hoaDon = new HoaDon(phieuGiamGia, nhanVien, khachHang, rs.getString("MaHoaDon"), rs.getInt("CAPBAC"), rs.getFloat("PhanTramGia"), rs.getFloat("TienPhieuGiam"), rs.getFloat("DiemDoi"), rs.getInt("PhuongThucTT"), rs.getFloat("TienKhDua"), rs.getFloat("TienKhChuyenKhoan"), rs.getFloat("TienThua"), rs.getFloat("ThanhTien"), rs.getBoolean("HinhThucMua"), rs.getInt("TrangThaiHD"));
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hoaDon, chiTietSanPham, rs.getInt("SoLuongHDCT"), rs.getString("MaDGGHDCT"), rs.getInt("LoaiGGHDCT"), rs.getFloat("GiaTriHDCT"), rs.getFloat("QuyDoiDGGTT"), rs.getFloat("GiaBanHDCT"), rs.getFloat("DonGiaHDCT"), rs.getFloat("ThanhtIENHDCT"));
                return chiTietHoaDon;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int deleteHDCT(Long idCTSP, Long idHD) {
        try {
            query = "DELETE HOADONCHITIET WHERE IdCTSP = ?  and IdHoaDon = ? ";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setLong(1, idCTSP);
            pstm.setLong(2, idHD);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int deleteAll(Long idHD) {
        try {
            query = "DELETE HOADONCHITIET\n"
                    + "WHERE IdHoaDon = ? ";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setLong(1, idHD);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }
}
