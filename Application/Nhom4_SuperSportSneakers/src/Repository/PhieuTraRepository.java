/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.ChiTietHoaDon;
import Model.SanPham;
import Model.SanPhamChiTiet;
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
public class PhieuTraRepository {

    private String query = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;

    public List<ChiTietHoaDon> getHDCTByMaHD(String maHD) {
        List<ChiTietHoaDon> list = new ArrayList<>();

        try {
            query = "SELECT MaCTSP , TenSP , HDCT.SoLuong , HDCT.GiaBan , HDCT.ThanhTien\n"
                    + "FROM HOADON AS HD\n"
                    + "JOIN KHACHHANG AS KH  ON KH.ID = HD.IdKH\n"
                    + "JOIN HOADONCHITIET AS HDCT ON HDCT.IdHoaDon = HD.ID\n"
                    + "JOIN CHI_TIET_SAN_PHAM AS CTSP ON CTSP.ID = HDCT.IdCTSP\n"
                    + "JOIN SANPHAM AS SP ON SP.ID = CTSP.IdSP\n"
                    + "WHERE HD.NgayThanhToan >= DATEADD(DAY, -3, GETDATE())\n"
                    + "      AND HD.NgayThanhToan <= GETDATE() AND HD.MaHoaDon like '" + maHD + "' ;";
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
                chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));
                chiTietHoaDon.setGiaBan(rs.getBigDecimal("GiaBan"));
                chiTietHoaDon.setThanhTien(rs.getBigDecimal("ThanhTien"));
                list.add(chiTietHoaDon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuTraRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
