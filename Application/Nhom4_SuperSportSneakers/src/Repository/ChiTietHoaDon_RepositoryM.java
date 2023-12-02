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
            query = "SELECT HDCT.ID , MaCTSP , TenSP , HDCT.DonGia , HDCT.GiaBan , HDCT.SoLuong , HDCT.LoaiDGG ,"
                    + " HDCT.GiaTriDGG , HDCT.ThanhTien FROM HOADONCHITIET AS HDCT \n"
                    + "JOIN HOADON AS HD ON HD.ID = HDCT.IdHoaDon\n"
                    + "JOIN CHI_TIET_SAN_PHAM AS SPCT ON SPCT.ID = HDCT.IdCTSP\n"
                    + "JOIN SANPHAM AS SP ON SP.ID = SPCT.IdSP \n"
                    + "WHERE HD.MaHoaDon LIKE '" + maHD + "'";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                SanPham sanPham = new SanPham(rs.getString("TenSP"));
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                sanPhamChiTiet.setIdSanPham(sanPham);
                sanPhamChiTiet.setMaSPCT(rs.getString("MaCTSP"));
                chiTietHoaDon.setIdCTSP(sanPhamChiTiet);
                chiTietHoaDon.setId(rs.getLong("ID"));
                chiTietHoaDon.setDonGia(rs.getBigDecimal("DonGia"));
                chiTietHoaDon.setGiaBan(rs.getBigDecimal("GiaBan"));
                chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));
                chiTietHoaDon.setLoaiDGG(rs.getInt("LoaiDGG"));
                chiTietHoaDon.setGiaTriDGG(rs.getBigDecimal("GiaTriDGG"));
                chiTietHoaDon.setThanhTien(rs.getBigDecimal("ThanhTien"));

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
            pstm.setObject(5, chiTietHoaDon.getLoaiDGG());
            pstm.setBigDecimal(6, chiTietHoaDon.getGiaTriDGG());
            pstm.setBigDecimal(7, chiTietHoaDon.getQuyDoiDGGTT());
            pstm.setBigDecimal(8, chiTietHoaDon.getDonGia());
            pstm.setBigDecimal(9, chiTietHoaDon.getGiaBan());
            pstm.setBigDecimal(10, chiTietHoaDon.getThanhTien());
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int deleteHDCT( Long idHD) {
        try {
            query = "DELETE HOADONCHITIET WHERE    id = ? ";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);

            pstm.setLong(1, idHD);
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

    public SanPhamChiTiet getSLSPByMa(String ma) {
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        try {
            query = "SELECT ID , MaCTSP , SoLuongTon FROM CHI_TIET_SAN_PHAM\n"
                    + "WHERE MaCTSP LIKE '" + ma + "';";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            if (rs.next()) {
                sanPhamChiTiet.setIdSPCT(rs.getLong("ID"));
                sanPhamChiTiet.setMaSPCT(rs.getString("MaCTSP"));
                sanPhamChiTiet.setSoLuong(rs.getInt("SoLuongTon"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sanPhamChiTiet;
    }

    public void updateSL_Ban(Long id, int soLuong) {
        try {
            con = DBConnection.getConnect();
            query = "update HOADONCHITIET\n"
                    + "set SoLuong = ?\n"
                    + "where ID = ? ";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, soLuong);
            pstm.setLong(2, id);
            pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIDSP_ByIdHD(int ma) {
        try {
            con = DBConnection.getConnect();
            query = "SELECT IdCTSP FROM HOADONCHITIET\n"
                    + "	WHERE IdHoaDon = '"+ma+"'";
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("IdCTSP");
            }
            return -1;
        } catch (SQLException ex) {

            return -1;
        }
    }

    public Long getIDSPCT(Long id) {
        try {
            query = "SELECT IdCTSP FROM HOADONCHITIET\n"
                    + " WHERE IdHoaDon = ?";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getLong("IdCTSP");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDon_RepositoryM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1L;
    }
}
