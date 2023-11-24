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

    public ArrayList<DotGiamGia_M> getAllKhuyenmai() {

        ArrayList<DotGiamGia_M> lst = new ArrayList<>();
        String sql = "SELECT IdNV, MaDGG, TenDGG, Loai, GiaTri, NgayBatDau, NgayKetThuc, MoTa, NgayTao, TrangThai   FROM DOT_GIAM_GIA";
        //Tạo kết nối
        con = DBConnection.getConnect();
        try {
            //Tạo statement
            PreparedStatement pstm = con.prepareStatement(sql);
            //Thi hành Statement=> dùng Resultset nhận kq
            ResultSet rs = pstm.executeQuery();
            //xử lý kq: duyệt rs => đổ dữ liệu vào lst
            while (rs.next()) {

                DotGiamGia_M n = new DotGiamGia_M();
                NhanVien nhanVien = new NhanVien(rs.getLong("IdNV"));
                n.setIdNV(nhanVien);
                n.setMaDGG(rs.getString("MaDGG"));
                n.setTenDGG(rs.getString("TenDGG"));
                n.setHinhThucDGG(rs.getInt("Loai"));
                n.setGiaTri(rs.getFloat("GiaTri"));
                n.setNgayBatDau(rs.getDate("NgayBatDau"));
                n.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                n.setMoTa(rs.getString("MoTa"));
                n.setNgayTao(rs.getDate("NgayTao"));
                n.setTrangThai(rs.getInt("TrangThai"));
                lst.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lst;
    }

    private boolean isMaKhuyenmaiExist(String maKhuyenmai) {
        boolean result = false;
        String sql = "SELECT COUNT(*) FROM DOT_GIAM_GIA WHERE MaDGG = ?";

        try (Connection connection = DBConnection.getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, maKhuyenmai);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu cần
        }

        return result;
    }

    public Integer addKhuyenmai(DotGiamGia_M sv) {
        if (!isMaKhuyenmaiExist(sv.getMaDGG())) {
            Integer row = null;
            String sql = "INSERT INTO DOT_GIAM_GIA (IdNV, MaDGG, TenDGG, Loai, GiaTri, NgayBatDau, NgayKetThuc, MoTa, TrangThai, NgayTao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";

            try (Connection cn = DBConnection.getConnect(); PreparedStatement pstm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, sv.getIdNV().getId());
                pstm.setObject(2, sv.getMaDGG());
                pstm.setObject(3, sv.getTenDGG());
                pstm.setObject(4, sv.getHinhThucDGG());
                pstm.setObject(5, sv.getGiaTri());
                pstm.setObject(6, sv.getNgayBatDau());
                pstm.setObject(7, sv.getNgayKetThuc());
                pstm.setObject(8, sv.getMoTa());
                pstm.setObject(9, sv.getTrangThai());

                row = pstm.executeUpdate();
                ResultSet generatedKeys = pstm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    NhanVien nhanVien = new NhanVien(rs.getLong("IdNV"));
                    sv.setIdNV(nhanVien);
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println("Mã khuyến mại đã tồn tại. Hãy chọn mã khác.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return row;
        } else {
            System.out.println("bị trùng mã");
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
            System.out.println(query);
            pstm.setDate(1, new java.sql.Date(ngayBatDau.getTime()));
            pstm.setDate(2, new java.sql.Date(ngayKetThuc.getTime()));
            pstm.setDate(3, new java.sql.Date(ngayBatDau.getTime()));
            pstm.setDate(4, new java.sql.Date(ngayKetThuc.getTime()));
            pstm.setDate(5, new java.sql.Date(ngayBatDau.getTime()));
            pstm.setDate(6, new java.sql.Date(ngayKetThuc.getTime()));
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
