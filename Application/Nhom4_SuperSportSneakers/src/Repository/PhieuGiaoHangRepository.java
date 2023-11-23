/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.HoaDon;
import Model.KhachHang;
import Model.PhieuGiaoHang;
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
public class PhieuGiaoHangRepository {

    private String query = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;

    public List<PhieuGiaoHang> getAll(int page, int limt) {
        List<PhieuGiaoHang> list = new ArrayList<>();
        try {
            query = "SELECT PGH.ID ,  HD.ID AS IDHD ,  MaVanDon , MaHoaDon , TenKhachHang , PGH.DiaChi , SDTNguoiNhan , GiaShip , TenShip , SDTShip , PGH.NgayTaoPhieu , ĐonViVanChuyen ,NgayHoanThanhDon , PGH.TrangThai FROM PHIEUGIAOHANG AS PGH\n"
                    + "JOIN HOADON AS HD ON HD.ID = PGH.IdHoaDon\n"
                    + "JOIN KHACHHANG AS KH ON KH.ID = PGH.IdKH "
                    + "	order by PGH.NgayTaoPhieu DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, (page - 1) * limt);
            pstm.setInt(2, limt);
            rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setTenKhachHang(rs.getString("TenKhachHang"));
                khachHang.setSdt(rs.getString("SDTNguoiNhan"));
                khachHang.setDiaChi(rs.getString("DiaChi"));
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(rs.getLong("IDHD"));

                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));

                PhieuGiaoHang phieuGiaoHang = new PhieuGiaoHang();
                phieuGiaoHang.setId(rs.getLong("ID"));
                phieuGiaoHang.setIdHD(hoaDon);
                phieuGiaoHang.setIdKH(khachHang);
                phieuGiaoHang.setMaVanDon(rs.getString("MaVanDon"));
                phieuGiaoHang.setSdtShip(rs.getString("SDTShip"));
                phieuGiaoHang.setTenShip(rs.getString("TenShip"));
                phieuGiaoHang.setGiaShip(rs.getFloat("GiaShip"));
                phieuGiaoHang.setSdtNNguoiNhan(rs.getString("SDTNguoiNhan"));
                phieuGiaoHang.setNgayTao(rs.getDate("NgayTaoPhieu"));
                phieuGiaoHang.setNgayHoanThanh(rs.getDate("NgayHoanThanhDon"));
                phieuGiaoHang.setDonViVanChuyen(rs.getString("ĐonViVanChuyen"));
                phieuGiaoHang.setTrangThai(rs.getInt("TrangThai"));
                list.add(phieuGiaoHang);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuGiaoHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int update(PhieuGiaoHang phieuGiaoHang) {
        try {
            query = "UPDATE PHIEUGIAOHANG \n"
                    + "SET TenNguoiNhan = ? , SDTNguoiNhan = ? , DiaChi = ? , TenShip = ? , SDTShip = ? , ĐonViVanChuyen = ? , NgayHoanThanhDon = ? , GiaShip = ? \n"
                    + "WHERE MaVanDon LIKE ? ";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, phieuGiaoHang.getTenNguoiNhan());
            pstm.setString(2, phieuGiaoHang.getSdtNNguoiNhan());
            pstm.setString(3, phieuGiaoHang.getDiaChi());
            pstm.setString(4, phieuGiaoHang.getTenShip());
            pstm.setString(5, phieuGiaoHang.getSdtShip());
            pstm.setString(6, phieuGiaoHang.getDonViVanChuyen());
            pstm.setString(7, XDate.toString(phieuGiaoHang.getNgayHoanThanh(), "MM-dd-yyyy"));
            pstm.setFloat(8, phieuGiaoHang.getGiaShip());
            pstm.setString(9, phieuGiaoHang.getMaVanDon());
            System.out.println("Repository.PhieuGiaoHangRepository.update()" + "      OKE");
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Repository.PhieuGiaoHangRepository.update()");
            Logger.getLogger(PhieuGiaoHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public List<HoaDon> listDSHD() {
        List<HoaDon> list = new ArrayList<>();
        try {
            query = "SELECT HD.ID AS IDHD, MaHoaDon, KH.ID AS IDKH, HD.NgayTao, TenKhachHang\n"
                    + "FROM HOADON AS HD\n"
                    + "JOIN KHACHHANG AS KH ON KH.ID = HD.IdKH\n"
                    + "WHERE HD.HinhThucMua = 1 AND HD.TrangThai = 1 AND NOT EXISTS (\n"
                    + "    SELECT 1 FROM PHIEUGIAOHANG AS PGH WHERE PGH.IDHoaDon = HD.ID\n"
                    + ");";
            con = DBConnection.getConnect();
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setTenKhachHang(rs.getString("TenKhachHang"));

                khachHang.setId(rs.getLong("IDKH"));
                HoaDon hoaDon = new HoaDon();
                hoaDon.setIdKH(khachHang);
                hoaDon.setId(rs.getLong("IDHD"));
                hoaDon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDon.setNgayTao(rs.getDate("NgayTao"));
                list.add(hoaDon);

            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuGiaoHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int insert(PhieuGiaoHang phieuGiaoHang) {
        try {
            query = "INSERT INTO PHIEUGIAOHANG (IdHoaDon , IdKH , TenNguoiNhan , SDTNguoiNhan , DiaChi , TenShip , SDTShip , GiaShip , MaVanDon , ĐonViVanChuyen , NgayHoanThanhDon)\n"
                    + "VALUES  (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setLong(1, phieuGiaoHang.getIdHD().getId());
            pstm.setLong(2, phieuGiaoHang.getIdKH().getId());
            pstm.setString(3, phieuGiaoHang.getTenNguoiNhan());
            pstm.setString(4, phieuGiaoHang.getSdtNNguoiNhan());
            pstm.setString(5, phieuGiaoHang.getDiaChi());
            pstm.setString(6, phieuGiaoHang.getTenShip());
            pstm.setString(7, phieuGiaoHang.getSdtShip());
            pstm.setFloat(8, phieuGiaoHang.getGiaShip());
            pstm.setString(9, phieuGiaoHang.getMaVanDon());
            pstm.setString(10, phieuGiaoHang.getDonViVanChuyen());
            pstm.setDate(11, new java.sql.Date(phieuGiaoHang.getNgayHoanThanh().getTime()));
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuGiaoHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public List<Object> listPGHCT(String maP) {
        List<Object> list = new ArrayList<>();
        try {
            query = "SELECT MaHoaDon , TenNguoiNhan , PGH.NgayTaoPhieu , TenSP , SoLuong , HDCT.GiaBan , (SoLuong * HDCT.GiaBan) AS THANHTIEN  FROM HOADON\n"
                    + "JOIN PHIEUGIAOHANG AS PGH ON PGH.IdHoaDon = HOADON.ID\n"
                    + "JOIN HOADONCHITIET AS HDCT ON HDCT.IdHoaDon = HOADON.ID\n"
                    + "JOIN CHI_TIET_SAN_PHAM AS CTSP ON CTSP.ID = HDCT.IdCTSP\n"
                    + "JOIN SANPHAM AS SP ON SP.ID = CTSP.IdSP\n"
                    + "WHERE MaVanDon LIKE ?";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setString(1, maP);
            rs = pstm.executeQuery();
            int i = 1;
            while (rs.next()) {
                Object[] ob = new Object[]{i, rs.getString("MaHoaDon"),
                    rs.getString("TenNguoiNhan"), rs.getDate("NgayTaoPhieu"), rs.getString("TenSP"), rs.getInt("SoLuong"),
                    rs.getFloat("GiaBan"), rs.getFloat("THANHTIEN")};
                list.add(ob);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PhieuGiaoHangRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int getRowCount() {
        String countSql = "SELECT COUNT(*) AS totalRows FROM PHIEUGIAOHANG";
        Connection con = DBConnection.getConnect();
        Statement stm;
        ResultSet rs;
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
        }
    }
}
