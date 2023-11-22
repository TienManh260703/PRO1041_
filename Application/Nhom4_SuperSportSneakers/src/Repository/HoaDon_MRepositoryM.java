/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.HoaDon;
import Model.KhachHang;
import Model.NhanVien;
import Model.PhieuGiamGia;
import Utils.XDate;
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
public class HoaDon_MRepositoryM {

    private String query = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;

    public int updateHD(HoaDon hoaDon) {
        try {
            query = " UPDATE HOADON  SET IdNV = ? , IdKH = ? , CapBac = ? , TienPhieuGiam = ? , PhanTramGia = ? , DiemDoi = ? ,\n"
                    + "		PhuongThucTT = ? , TienKhDua = ?  , TienKhChuyenKhoan = ? , TienThua = ? , ThanhTien = ? ,\n"
                    + "		NgayThanhToan = ? , TrangThai = 1 \n"
                    + "		WHERE MaHoaDon like ?";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setLong(1, hoaDon.getIdNV().getId());
            pstm.setLong(2, hoaDon.getIdKH().getId());
            pstm.setInt(3, hoaDon.getCapBac());
            pstm.setFloat(4, hoaDon.getTienPhieuGiam());
            pstm.setFloat(5, hoaDon.getPhanTramGG());

            pstm.setFloat(6, hoaDon.getDiemDoi());
            pstm.setInt(7, hoaDon.getPhuongThucTT());
            pstm.setFloat(8, hoaDon.getTienKhDua());
            pstm.setFloat(9, hoaDon.getTienKhChuyenKhoan());
            pstm.setFloat(10, hoaDon.getTienThua());
            pstm.setFloat(11, hoaDon.getThanhTien());
            pstm.setString(12, XDate.toString(hoaDon.getNgayThanhToan(), "yyyy-MM-dd HH:mm:ss.SSS"));

            pstm.setString(13, hoaDon.getMaHoaDon());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_MRepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public void updateHDBy(KhachHang khachHang, String maHD) {
        try {
            query = "UPDATE HOADON\n"
                    + "SET IdKH = ? \n"
                    + "WHERE MaHoaDon LIKE ?";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setLong(1, khachHang.getId());
            pstm.setString(2, maHD);
            pstm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_MRepositoryM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getRowCountHD() {
        String countSql = "SELECT COUNT(*) AS totalRows FROM HOADON";
        con = DBConnection.getConnect();
        try {
            stm = con.createStatement();
            rs = stm.executeQuery(countSql);
            int totalRows = 0;
            if (rs.next()) {
                return totalRows = rs.getInt("totalRows");
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (stm != null && !stm.isClosed()) {
                    stm.close();
                }
                if (pstm != null && !pstm.isClosed()) {
                    pstm.close();
                }
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangRepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int create(HoaDon hoaDon) {
        try {
            query = " INSERT INTO HOADON(IdPGG , IdNV , IdKH, MaHoaDon) VALUES \n"
                    + "	(? ,? , ? , ?) ";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setObject(1, hoaDon.getIdPGG().getIdPGG());
            pstm.setLong(2, hoaDon.getIdNV().getId());
            pstm.setLong(3, hoaDon.getIdKH().getId());
            pstm.setString(4, hoaDon.getMaHoaDon());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_MRepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (stm != null && !stm.isClosed()) {
                    stm.close();
                }
                if (pstm != null && !pstm.isClosed()) {
                    pstm.close();
                }
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhachHangRepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<HoaDon> getAllHDByTrangThai(int trangThai) {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            query = "SELECT \n"
                    + "  HD.ID  AS IdHD , HD.MaHoaDon AS MaHoaDon  , HD.NgayTao AS NgayTao , HD.TrangThai AS TrangThaiHD ,\n"
                    + " NV.ID AS IdNV , NV.HoVaTen AS TenNV ,  NV.MaNhanVien AS MaNhanVien , \n"
                    + " KH.ID AS IdKH , KH.TenKhachHang AS TenKH , KH.MaKhachHang AS  MaKhachHang ,\n"
                    + " PGG.ID AS IdPGG , PGG.MaPhieu AS  MaPhieu , PGG.TenPhieu AS TenPhieu , PGG.GiaTri AS GiaTri , PGG.LoaiPhieu AS  LoaiPhieu, PGG.SoLuongPhieu AS SoLuongPhieu  , PGG.DonToiThieu AS DonToiThieu  , PGG.TrangThai AS TrangThaiPGG \n"
                    + " FROM HOADON AS HD\n"
                    + " LEFT JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH \n"
                    + " LEFT JOIN NHANVIEN AS NV ON NV.ID = HD.IdNV\n"
                    + " LEFT JOIN PHIEU_GIAM_GIA AS PGG ON PGG.ID  = HD.IdPGG\n"
                    + " WHERE HD.TrangThai = ? \n"
                    + " ORDER BY HD.NgayTao DESC";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, trangThai);
            rs = pstm.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getLong("IdNV"),
                        rs.getString("MaNhanVien"),
                        rs.getString("TenNV"));
                KhachHang khachHang = new KhachHang(
                        rs.getLong("IdKH"),
                        rs.getString("MaKhachHang"),
                        rs.getString("TenKH"));
//                PhieuGiamGia_M pggm = new PhieuGiamGia_M(
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
                HoaDon hoaDon = new HoaDon(
                        rs.getLong("IdHD"),
                        phieuGiamGia,
                        nhanVien,
                        khachHang,
                        rs.getString("MaHoaDon"),
                        rs.getDate("NgayTao"),
                        trangThai);
                listHD.add(hoaDon);
            }
            return listHD;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<HoaDon> getAllHDByHTM(int pttt) {
        List<HoaDon> listHD = new ArrayList<>();
        try {
            query = "SELECT \n"
                    + "  HD.ID  AS IdHD , HD.MaHoaDon AS MaHoaDon  , HD.NgayTao AS NgayTao , HD.TrangThai AS TrangThaiHD ,\n"
                    + " NV.ID AS IdNV , NV.HoVaTen AS TenNV ,  NV.MaNhanVien AS MaNhanVien , \n"
                    + " KH.ID AS IdKH , KH.TenKhachHang AS TenKH , KH.MaKhachHang AS  MaKhachHang ,\n"
                    + " PGG.ID AS IdPGG , PGG.MaPhieu AS  MaPhieu , PGG.TenPhieu AS TenPhieu , PGG.GiaTri AS GiaTri , PGG.LoaiPhieu AS  LoaiPhieu, PGG.SoLuongPhieu AS SoLuongPhieu  , PGG.DonToiThieu AS DonToiThieu  , PGG.TrangThai AS TrangThaiPGG \n"
                    + " FROM HOADON AS HD\n"
                    + " LEFT JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH \n"
                    + " LEFT JOIN NHANVIEN AS NV ON NV.ID = HD.IdNV\n"
                    + " LEFT JOIN PHIEU_GIAM_GIA AS PGG ON PGG.ID  = HD.IdPGG\n"
                    + " WHERE HD.HinhThucMua = ? \n"
                    + " ORDER BY HD.NgayTao DESC";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, pttt);
            rs = pstm.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien(
                        rs.getLong("IdNV"),
                        rs.getString("MaNhanVien"),
                        rs.getString("TenNV"));
                KhachHang khachHang = new KhachHang(
                        rs.getLong("IdKH"),
                        rs.getString("MaKhachHang"),
                        rs.getString("TenKH"));
//                PhieuGiamGia_M pggm = new PhieuGiamGia_M(
//                        rs.getLong("IdPGG"),
//                        rs.getString("MaPhieu"),
//                        rs.getString("TenPhieu"),
//                        rs.getBoolean("LoaiPhieu"),
//                        rs.getFloat("GiaTri"),
//                        rs.getInt("SoLuongPhieu"),
//                        rs.getFloat("DonToiThieu"),
//                        rs.getInt("TrangThaiPGG"));
//                

                PhieuGiamGia phieuGiamGia = new PhieuGiamGia();
                phieuGiamGia.setIdPGG(rs.getLong("IdPGG"));
                phieuGiamGia.setMaPhieu(rs.getString("MaPhieu"));
                phieuGiamGia.setTenPhieu(rs.getString("TenPhieu"));
                phieuGiamGia.setLoaiPhieu(rs.getInt("LoaiPhieu"));
                phieuGiamGia.setGiaTri(rs.getFloat("GiaTri"));
                phieuGiamGia.setSoLuongPhieu(rs.getInt("SoLuongPhieu"));
                phieuGiamGia.setDonToiThieu(rs.getFloat("DonToiThieu"));
                phieuGiamGia.setTrangThai(rs.getInt("TrangThaiPGG"));
                HoaDon hoaDon = new HoaDon(
                        rs.getLong("IdHD"),
                        phieuGiamGia,
                        nhanVien,
                        khachHang,
                        rs.getString("MaHoaDon"),
                        rs.getDate("NgayTao"),
                        pttt);
                listHD.add(hoaDon);
            }
            return listHD;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Long findIDByMaHD(String maHoaDon) {
        Long id = 0L;
        try {
            query = "SELECT ID FROM HOADON WHERE MaHoaDon LIKE '" + maHoaDon + "'";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            if (rs.next()) {
                id = rs.getLong("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDon_MRepositoryM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}
