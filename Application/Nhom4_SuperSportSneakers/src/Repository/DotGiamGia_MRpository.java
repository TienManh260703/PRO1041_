/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.DotGiamGia_M;
import Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manhnt
 */
public class DotGiamGia_MRpository {

    private String query = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;

    public List<DotGiamGia_M> getAll(int page, int limt) {
        List<DotGiamGia_M> list = new ArrayList<>();
        try {
            query = " SELECT DGG.ID AS ID , NV.ID AS IDNV , NV.HoVaTen  , TenDGG , Loai , GiaTri , NgayBatDau , NgayKetThuc ,"
                    + " DGG.NgayTao , DGG.TrangThai  FROM DOT_GIAM_GIA AS DGG\n"
                    + "JOIN NHANVIEN AS NV ON NV.ID = DGG.IdNV " + "	order by ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";;
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, (page - 1) * limt);
            pstm.setInt(2, limt);
            rs = pstm.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(rs.getLong("IDNV"));
                nhanVien.setTenNhanVien(rs.getString("HoVaTen"));
                DotGiamGia_M dggm = new DotGiamGia_M();
                dggm.setIdDGG(rs.getLong("ID"));
                dggm.setTenDGG(rs.getString("TenDGG"));
                dggm.setHinhThucDGG(rs.getInt("Loai"));
                dggm.setGiaTri(rs.getFloat("GiaTri"));
                dggm.setNgayBatDau(rs.getDate("NgayBatDau"));
                dggm.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                dggm.setNgayTao(rs.getDate("NgayTao"));
                dggm.setTrangThai(rs.getInt("TrangThai"));
                dggm.setIdNV(nhanVien);
                list.add(dggm);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DotGiamGia_MRpository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public DotGiamGia_M getDGGByMaCTSP(String maCTSP) {
        try {
            query = " SELECT CTSP.ID as ID , CTSP.MaCTSP as  MaCTSP ,\n"
                    + "  IdDGG , DGG.MaDGG AS MaDGG , DGG.Loai AS LoaiDGG , DGG.GiaTri AS GiaTriDGG , DGG.TrangThai AS TrangThaiDGG ,\n"
                    + "  SoLuongTon, GiaNiemYet , GiaBan\n"
                    + "   FROM  DOT_GIAM_GIA  AS DGG\n"
                    + "    JOIN CHI_TIET_SAN_PHAM AS CTSP ON CTSP.IdDGG = DGG.ID\n"
                    + "	WHERE CTSP.MaCTSP LIKE '" + maCTSP + "'";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            if (rs.next()) {
                DotGiamGia_M dgg = new DotGiamGia_M(
                        rs.getLong("IdDGG"),
                        rs.getString("MaDGG"),
                        rs.getInt("LoaiDGG"),
                        rs.getFloat("GiaTriDGG"),
                        rs.getInt("TrangThaiDGG"));
                return dgg;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DotGiamGia_MRpository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int getRowCount() {
        String countSql = "SELECT COUNT(*) AS totalRows FROM DOT_GIAM_GIA";
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
