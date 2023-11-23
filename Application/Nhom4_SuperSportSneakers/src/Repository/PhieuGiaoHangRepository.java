/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.HoaDon;
import Model.KhachHang;
import Model.PhieuGiaoHang;
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
            query = "SELECT PGH.ID ,  MaVanDon , MaHoaDon , TenKhachHang , SDTNguoiNhan , GiaShip , TenShip , SDTShip , PGH.NgayTaoPhieu ,NgayHoanThanhDon , PGH.TrangThai FROM PHIEUGIAOHANG AS PGH\n"
                    + "JOIN HOADON AS HD ON HD.ID = PGH.IdHoaDon\n"
                    + "JOIN KHACHHANG AS KH ON KH.ID = PGH.IdKH "
                    + "	order by ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";;;
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, (page - 1) * limt);
            pstm.setInt(2, limt);
            rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setTenKhachHang(rs.getString("TenKhachHang"));
                khachHang.setSdt(rs.getString("SDTNguoiNhan"));
                HoaDon hoaDon = new HoaDon();
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
                phieuGiaoHang.setTrangThai(rs.getInt("TrangThai"));
                list.add(phieuGiaoHang);
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
