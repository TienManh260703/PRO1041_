/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.DotGiamGia_M;
import Model.KichThuoc;
import Model.MauSac;
import Model.NhanVien;
import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.ThuongHieu;
import Utils.XDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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

       public List<DotGiamGia_M> getAllDGG(int page, int limit) {
        List<DotGiamGia_M> list = new ArrayList<>();
        try {
            query = "SELECT NV.ID AS IDNV , NV.HoVaTen ,DGG.ID AS ID  , MaDGG , TenDGG, Loai , GiaTri , "
                    + " NgayBatDau , NgayKetThuc , DGG.NgayTao, MoTa , DGG.TrangThai FROM DOT_GIAM_GIA AS DGG\n"
                    + "	JOIN NHANVIEN AS NV ON NV.ID = DGG.IdNV"
                    + "	order by DGG.NgayTao OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";;
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setInt(1, (page - 1) * limit);
            pstm.setInt(2, limit);
            rs = pstm.executeQuery();
            while (rs.next()) {
                DotGiamGia_M dggm = new DotGiamGia_M();
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(rs.getLong("IDNV"));
                nhanVien.setTenNhanVien(rs.getString("HoVaTen"));
                dggm.setIdNV(nhanVien);
                dggm.setIdDGG(rs.getLong("ID"));
                dggm.setMaDGG(rs.getString("MaDGG"));
                dggm.setTenDGG(rs.getString("TenDGG"));
                dggm.setHinhThucDGG(rs.getInt("Loai"));
                dggm.setGiaTri(rs.getFloat("GiaTri"));
                dggm.setNgayBatDau(rs.getDate("NgayBatDau"));
                dggm.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                dggm.setNgayTao(rs.getDate("NgayTao"));
                dggm.setMoTa(rs.getString("MoTa"));
                dggm.setTrangThai(rs.getInt("TrangThai"));
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

    public List<SanPhamChiTiet> getAllSPST_DGG(Date ngayBatDau, Date ngayKetThuc) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {

            query = "SELECT CTSP.ID AS IdCTSP, CTSP.MaCTSP , SP.TenSP , CTSP.GiaBan , TH.TenThuongHieu , S.TenSize , M.TenMau\n"
                    + "FROM CHI_TIET_SAN_PHAM AS CTSP\n"
                    + "JOIN SANPHAM AS SP ON SP.ID  = CTSP.IdSP\n"
                    + "JOIN SIZE AS S ON S.ID = CTSP.IdSize\n"
                    + "JOIN THUONGHIEU AS TH ON TH.ID = CTSP.IdThuongHieu\n"
                    + "JOIN MAU AS M ON M.ID = CTSP.IdMau\n"
                    + "WHERE CTSP.TrangThai = 0 -- Chi tiết sản phẩm còn hàng\n"
                    + "    AND NOT EXISTS (\n"
                    + "        SELECT 1\n"
                    + "        FROM CHI_TIET_DGG AS CTDGG\n"
                    + "        JOIN DOT_GIAM_GIA AS DGG ON CTDGG.IdDGG = DGG.ID\n"
                    + "        WHERE CTDGG.IdCTSP = CTSP.ID\n"
                    + "            AND (DGG.NgayBatDau BETWEEN ? AND ? \n"
                    + "                 OR DGG.NgayKetThuc BETWEEN ?  AND ? \n"
                    + "                 OR (DGG.NgayBatDau <= ? AND DGG.NgayKetThuc >= ? ))\n"
                    + "    );";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            System.out.println(XDate.toString(XDate.convertDateFormat(XDate.toString(ngayBatDau, "dd-MM-yyyy"), "MM-dd-yyyy"), "MM-dd-yyyy"));
            System.out.println(XDate.toString(XDate.convertDateFormat(XDate.toString(ngayKetThuc, "dd-MM-yyyy"), "MM-dd-yyyy"), "MM-dd-yyyy"));

            pstm.setString(1, XDate.toString(XDate.convertDateFormat(XDate.toString(ngayBatDau, "dd-MM-yyyy"), "MM-dd-yyyy"), "MM-dd-yyyy"));
            pstm.setString(2, XDate.toString(XDate.convertDateFormat(XDate.toString(ngayKetThuc, "dd-MM-yyyy"), "MM-dd-yyyy"), "MM-dd-yyyy"));
            pstm.setString(3, XDate.toString(XDate.convertDateFormat(XDate.toString(ngayBatDau, "dd-MM-yyyy"), "MM-dd-yyyy"), "MM-dd-yyyy"));
            pstm.setString(4, XDate.toString(XDate.convertDateFormat(XDate.toString(ngayKetThuc, "dd-MM-yyyy"), "MM-dd-yyyy"), "MM-dd-yyyy"));
            pstm.setString(5, XDate.toString(XDate.convertDateFormat(XDate.toString(ngayBatDau, "dd-MM-yyyy"), "MM-dd-yyyy"), "MM-dd-yyyy"));
            pstm.setString(6, XDate.toString(XDate.convertDateFormat(XDate.toString(ngayKetThuc, "dd-MM-yyyy"), "MM-dd-yyyy"), "MM-dd-yyyy"));

            rs = pstm.executeQuery();
            while (rs.next()) {
                KichThuoc kichThuoc = new KichThuoc();
                kichThuoc.setTenSize(rs.getFloat("TenSize"));
                MauSac mauSac = new MauSac();
                mauSac.setTenMau(rs.getString("TenMau"));
                SanPham sanPham = new SanPham();
                sanPham.setTenSanpham(rs.getString("TenSP"));
                ThuongHieu thuongHieu = new ThuongHieu();
                thuongHieu.setTenThuongHieu(rs.getString("TenThuongHieu"));
                SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
                sanPhamChiTiet.setIdSPCT(rs.getLong("IdCTSP"));
                sanPhamChiTiet.setMaSPCT(rs.getString("MaCTSP"));

                sanPhamChiTiet.setIdKichThuoc(kichThuoc);
                sanPhamChiTiet.setIdMau(mauSac);
                sanPhamChiTiet.setIdSanPham(sanPham);
                sanPhamChiTiet.setIdThuongHieu(thuongHieu);
                list.add(sanPhamChiTiet);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DotGiamGia_MRpository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int getRowCount() {
        String countSql = "SELECT COUNT(*) AS totalRows FROM DOT_GIAM_GIA";
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
        }
    }
}
