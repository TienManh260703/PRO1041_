/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.ChiTietDotGiamGia;
import Model.DotGiamGia_M;
import Model.KichThuoc;
import Model.MauSac;

import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.ThuongHieu;
import java.math.BigDecimal;
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
public class ChiTietDotGiamRepository {

    private String query = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;

    public List<ChiTietDotGiamGia> getAll() {
        List<ChiTietDotGiamGia> list = new ArrayList<>();
        con = DBConnection.getConnect();
        query = "";
        return null;

    }

    public List<ChiTietDotGiamGia> getAllByIDDgg(Long idDGG) {
        try {
            List<ChiTietDotGiamGia> list = new ArrayList<>();
            query = "SELECT IdDGG AS IDDGG , IdCTSP , MaCTSP , DonGia , DonGiaConLai , GiaTriGiam FROM CHI_TIET_DGG AS CTDGG\n"
                    + "LEFT JOIN DOT_GIAM_GIA AS DGG ON DGG.ID = CTDGG.IdDGG\n"
                    + " WHERE IDDGG = ?";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setLong(1, idDGG);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ChiTietDotGiamGia chiTietDotGiamGia = new ChiTietDotGiamGia();
                DotGiamGia_M dgg = new DotGiamGia_M();
                dgg.setIdDGG(rs.getLong("IDDGG"));
                chiTietDotGiamGia.setIdDGG(dgg);
                chiTietDotGiamGia.setIdCTSP(rs.getLong("IdCTSP"));
                chiTietDotGiamGia.setMsSP(rs.getString("MaCTSP"));
                chiTietDotGiamGia.setDonGia(rs.getBigDecimal("DonGia"));
                chiTietDotGiamGia.setDonGiaConLai(rs.getBigDecimal("DonGiaConLai"));
                chiTietDotGiamGia.setGiaTriGiam(rs.getFloat("GiaTriGiam"));
                list.add(chiTietDotGiamGia);

            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(ChiTietDotGiamRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void update(DotGiamGia_M dgg, ChiTietDotGiamGia ctdgg) {
        try {
            query = "UPDATE CHI_TIET_DGG \n"
                    + "SET DonGia = ? , DonGiaConLai = ? , GiaTriGiam = ? \n"
                    + "WHERE IdCTSP = ? AND IdDGG = ?  AND MaCTSP LIKE ?";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setBigDecimal(1, ctdgg.getDonGia());
            pstm.setObject(2, ctdgg.getDonGiaConLai());
            pstm.setObject(3, dgg.getGiaTri());
            pstm.setLong(4, ctdgg.getIdCTSP());
            pstm.setLong(5, dgg.getIdDGG());
            pstm.setString(6, ctdgg.getMsSP());
            pstm.execute();

        } catch (Exception ex) {
            Logger.getLogger(ChiTietDotGiamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
    }

    public SanPhamChiTiet getProductByID(Long id) {
        con = DBConnection.getConnect();
        SanPhamChiTiet result = null;
        String sql = "SELECT CTSP.ID, CTSP.MaCTSP, SP.TenSP, TH.TenThuongHieu, S.TenSize, M.TenMau, CTSP.SoLuongTon, CTSP.GiaBan, CTSP.GiaNiemYet, CTSP.MoTa, CTSP.TrangThai \n"
                + "FROM CHI_TIET_SAN_PHAM AS CTSP\n"
                + "JOIN MAU AS M ON M.ID = CTSP.IdMau\n"
                + "JOIN SIZE AS S ON S.ID = CTSP.IdSize\n"
                + "JOIN THUONGHIEU AS TH ON TH.ID = CTSP.IdThuongHieu\n"
                + "JOIN SANPHAM AS SP ON SP.ID = CTSP.IdSP\n"
                + "WHERE CTSP.ID = ?;";

        try {
            pstm = con.prepareStatement(sql);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                SanPham sanPham = new SanPham(rs.getString("TenSP"));
                MauSac mauSac = new MauSac(rs.getString("TenMau"));
                ThuongHieu thuongHieu = new ThuongHieu(rs.getString("TenThuongHieu"));
                KichThuoc kichThuoc = new KichThuoc(rs.getFloat("TenSize"));

                result = new SanPhamChiTiet(rs.getLong("ID"), rs.getString("MaCTSP"), rs.getInt("SoLuongTon"), rs.getBigDecimal("GiaBan"), rs.getBigDecimal("GiaNiemYet"), rs.getInt("TrangThai"), rs.getString("MoTa"), mauSac, kichThuoc, thuongHieu, sanPham);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public List<Object> listSPCTDGG(String maDGG) {
        List<Object> list = new ArrayList<>();
        try {
            query = "SELECT IdCTSP , IdDGG , MaDGG, CTDGG.DonGia , GiaTriGiam , GiaTri FROM CHI_TIET_DGG AS CTDGG\n"
                    + "JOIN DOT_GIAM_GIA AS DGG ON DGG.ID = CTDGG.IdDGG\n"
                    + "WHERE MaDGG LIKE '" + maDGG + "'";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            int i = 1;
            while (rs.next()) {
                SanPhamChiTiet spct = this.getProductByID(rs.getLong("IdCTSP"));
                BigDecimal donGia = rs.getBigDecimal("DonGia");
                Object[] ob = new Object[]{
                    i, spct.getMaSPCT(), spct.getIdSanPham().getTenSanpham(), spct.getIdThuongHieu().getTenThuongHieu(),
                    spct.getIdMau().getTenMau(), spct.getIdKichThuoc().getTenSize(),
                    donGia, rs.getFloat("GiaTriGiam")
                };

                list.add(ob);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    
    public int insert(ChiTietDotGiamGia cTDGG, DotGiamGia_M dgg, SanPhamChiTiet spct) {

        try {
            query = "INSERT INTO CHI_TIET_DGG (IdDGG , IdCTSP , MaCTSP , DonGia , DonGiaConLai , GiaTriGiam) VALUES \n"
                    + "(? , ? , ? , ? , ? , ?)";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            pstm.setLong(1, dgg.getIdDGG());
            pstm.setLong(2, spct.getIdSPCT());
            pstm.setString(3, spct.getMaSPCT());
            pstm.setBigDecimal(4, spct.getGiaNiemYet());
            pstm.setBigDecimal(5, cTDGG.getDonGiaConLai());
            pstm.setFloat(6, Float.parseFloat(dgg.getGiaTri() + ""));
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietDotGiamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public SanPhamChiTiet getSPCTByMa(String ma) {

        try {
            query = "SELECT id , MaCTSP , GiaNiemYet FROM CHI_TIET_SAN_PHAM WHERE MaCTSP LIKE '" + ma + "'";
            con = DBConnection.getConnect();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            SanPhamChiTiet spct = new SanPhamChiTiet();
            if (rs.next()) {

                spct.setIdSPCT(rs.getLong("id"));
                spct.setMaSPCT(rs.getString("MaCTSP"));
                spct.setGiaNiemYet(rs.getBigDecimal("GiaNiemYet"));
            }
            return spct;
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietDotGiamRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
