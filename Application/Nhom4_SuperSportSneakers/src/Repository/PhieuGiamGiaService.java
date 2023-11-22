/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.HoaDon;
import Model.NhanVien;
import Model.PhieuGiamGia;

import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class PhieuGiamGiaService {

    private String query = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
    private Connection con = null;

    public ArrayList<PhieuGiamGia> getAllPGG(int page, int limt) {
        ArrayList<PhieuGiamGia> listPGG = new ArrayList<>();
        Connection conn = DBConnection.getConnect();
        String sql = "SELECT PGG.ID , NV.HoVaTen  , nv.ID as IDNV  , MaPhieu , TenPhieu , LoaiPhieu , GiaTri , SoLuongPhieu ,  "
                + "DonToiThieu , \n"
                + "NgayBatDau , NgayKetThuc ,  PGG.NgayTao , MoTa , PGG.TrangThai FROM PHIEU_GIAM_GIA AS PGG\n"
                + "JOIN NHANVIEN AS NV ON NV.ID = PGG.IdNV"
                + "	order by ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";;

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, (page - 1) * limt);
            pstm.setInt(2, limt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                PhieuGiamGia pgg = new PhieuGiamGia();
                NhanVien nhanVien = new NhanVien();
                nhanVien.setId(rs.getLong("IDNV"));
                nhanVien.setTenNhanVien(rs.getString("HoVaTen"));
                pgg.setIdNV(nhanVien);
                pgg.setMaPhieu(rs.getString("MaPhieu"));
                pgg.setTenPhieu(rs.getString("TenPhieu"));
                pgg.setLoaiPhieu(rs.getInt(("LoaiPhieu")));
                pgg.setGiaTri(rs.getFloat("GiaTri"));
                pgg.setSoLuongPhieu(rs.getInt("SoLuongPhieu"));
                pgg.setDonToiThieu(rs.getFloat("DonToiThieu"));
                pgg.setNgayBatDau(rs.getDate("NgayBatDau"));
                pgg.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                pgg.setNgayTao(rs.getDate("NgayTao"));
                pgg.setMoTa(rs.getString("MoTa"));
                pgg.setTrangThai(rs.getInt("TrangThai"));

                listPGG.add(pgg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPGG;
    }

    public ArrayList<PhieuGiamGia> getLoc(Date NgayBatDau, Date NgayKetThuc, int LoaiPhieu, int TrangThai) {
        ArrayList<PhieuGiamGia> listPGG = new ArrayList<>();
        Connection conn = DBConnection.getConnect();
        System.out.println(TrangThai);

        try {
            String sql = "Select IdNV, MaPhieu, TenPhieu, LoaiPhieu, GiaTri, SoLuongPhieu, DonToiThieu, NgayBatDau, NgayKetThuc,NgayTao, MoTa, TrangThai from PHIEU_GIAM_GIA ";
            ArrayList<String> whereQueries = new ArrayList<>();
            String NgayBatDauStr = null, NgayKetThucStr = null;

            if (NgayBatDau != null && NgayKetThuc != null) {
                SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
                NgayBatDauStr = spd.format(NgayBatDau);
                NgayKetThucStr = spd.format(NgayKetThuc);

                whereQueries.add("NgayBatDau >= ? AND NgayKetThuc <= ?");
            }
            whereQueries.add("LoaiPhieu = ?");
            if (TrangThai != -1) {
                whereQueries.add("TrangThai = ?");
            }
            String whereQuery = "";
            for (int i = 0; i < whereQueries.size(); i++) {
                String dieukien = whereQueries.get(i);
                whereQuery += dieukien;
                if (i != whereQueries.size() - 1) {
                    whereQuery += " AND ";
                }
            }
            System.out.println(whereQuery);

            sql += "WHERE " + whereQuery;

            PreparedStatement pr = conn.prepareStatement(sql);
            if (NgayBatDau != null && NgayKetThuc != null) {
                pr.setObject(1, NgayBatDauStr);
                pr.setObject(2, NgayKetThucStr);
                pr.setObject(3, LoaiPhieu);
                if (TrangThai != -1) {
                    pr.setObject(4, TrangThai);
                }
            } else if (TrangThai != -1) {
                pr.setObject(1, LoaiPhieu);
                pr.setObject(2, TrangThai);
            } else {
                pr.setObject(1, LoaiPhieu);
            }

            //PreparedStatement pr= conn.prepareStatement(sql);
//            pr.setObject(1, dateStart);
//            pr.setObject(2, dateEnd);
//            pr.setObject(3, loai);
//            pr.setObject(4, trangThai);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                PhieuGiamGia pgg = new PhieuGiamGia();
//                NhanVien nv= new NhanVien();
//                nv.setIdNV(rs.getLong(1));
                //  pgg.setIdNV(rs.getLong(1));
                pgg.setMaPhieu(rs.getString(2));
                pgg.setTenPhieu(rs.getString(3));
                pgg.setLoaiPhieu(rs.getInt(4));
                pgg.setGiaTri(rs.getFloat(5));
                pgg.setSoLuongPhieu(rs.getInt(6));
                pgg.setDonToiThieu(rs.getFloat(7));
                pgg.setNgayBatDau(rs.getDate(8));
                pgg.setNgayKetThuc(rs.getDate(9));
                pgg.setNgayTao(rs.getDate(10));
                pgg.setMoTa(rs.getString(11));
                pgg.setTrangThai(rs.getInt(12));

                listPGG.add(pgg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPGG;
    }

    public List<PhieuGiamGia> getListLoc(PhieuGiamGia giamGia, int page, int limt) {
        List<PhieuGiamGia> list = new ArrayList<>();
        query = "SELECT ID , nv.ID AS IDNV , NV.HoVaTen , MaPhieu , TenPhieu , LoaiPhieu , GiaTri , \n"
                + "SoLuongPhieu , DonToiThieu , NgayBatDau , NgayKetThuc , PGG.NgayTao , MoTa , PGG.TrangThai FROM PHIEU_GIAM_GIA  AS PGG\n"
                + "JOIN NHANVIEN AS NV ON NV.ID = PGG.IdNV\n";
        ArrayList<String> where = new ArrayList<>();
        String wheres = "";
        con = DBConnection.getConnect();
        try {

            if (giamGia == null) {
                query += "order by ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                pstm = con.prepareStatement(query);
                pstm.setInt(1, (page - 1) * limt);
                pstm.setInt(2, limt);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    PhieuGiamGia pgg = new PhieuGiamGia();
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setId(rs.getLong("IDNV"));
                    nhanVien.setTenNhanVien(rs.getString("HoVaTen"));
                    pgg.setIdNV(nhanVien);
                    pgg.setMaPhieu(rs.getString("MaPhieu"));
                    pgg.setTenPhieu(rs.getString("TenPhieu"));
                    pgg.setLoaiPhieu(rs.getInt(("LoaiPhieu")));
                    pgg.setGiaTri(rs.getFloat("GiaTri"));
                    pgg.setSoLuongPhieu(rs.getInt("SoLuongPhieu"));
                    pgg.setDonToiThieu(rs.getFloat("DonToiThieu"));
                    pgg.setNgayBatDau(rs.getDate("NgayBatDau"));
                    pgg.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                    pgg.setNgayTao(rs.getDate("NgayTao"));
                    pgg.setMoTa(rs.getString("MoTa"));
                    pgg.setTrangThai(rs.getInt("TrangThai"));
                    list.add(pgg);
                }
                return list;
            }
            if (giamGia.getNgayBatDau() != null && giamGia.getNgayKetThuc() != null) {
                where.add("(NgayBatDau >= ? AND NgayKetThuc <= ? ) ");
            }
            if (giamGia.getLoaiPhieu() != -1) {
                where.add("LoaiPhieu = ?");
            }
            if (giamGia.getTrangThai() != -1) {
                where.add("PGG.TrangThai = ? ");
            }

            for (String w : where) {
                wheres += w + " AND ";
            }
            System.out.println(wheres);
            query += "WHERE " + wheres;
            System.out.println(query);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuGiamGiaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return list;
    }

    public static NhanVien getNhanVien(long idPGG) {
        //ArrayList<PhieuGiamGia> listPGG= new ArrayList<>();
        NhanVien nv = null;
        Connection conn = DBConnection.getConnect();
        String sql = "select NHANVIEN.ID,HoVaTen from PHIEU_GIAM_GIA join NHANVIEN on PHIEU_GIAM_GIA.ID= NHANVIEN.ID where PHIEU_GIAM_GIA.ID=?";

        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setObject(1, idPGG);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                nv = new NhanVien();
                nv.setId(rs.getLong("ID"));
                nv.setTenNhanVien(rs.getString("HoVaTen"));
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

//     public ArrayList<PhieuGiamGia> getLoc(Date NgayBatDau, Date NgayKetThuc, int LoaiPhieu, int TrangThai){
//        ArrayList<PhieuGiamGia> listPGG= new ArrayList<>();
//        Connection conn= DBContext.getConnection();
//        System.out.println(TrangThai);
//        
//        try {
//            String sql="Select IdNV, MaPhieu, TenPhieu, LoaiPhieu, GiaTri, SoLuongPhieu, DonToiThieu, NgayBatDau, NgayKetThuc,NgayTao, MoTa, TrangThai from PHIEU_GIAM_GIA ";
//            ArrayList<String> whereQueries = new ArrayList<>();
//            String NgayBatDauStr = null, NgayKetThucStr = null;
//            
//            if (NgayBatDau != null && NgayKetThuc != null) {
//                SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
//                NgayBatDauStr = spd.format(NgayBatDau);
//                NgayKetThucStr = spd.format(NgayKetThuc);
//                
//                whereQueries.add("NgayBatDau >= ? AND NgayKetThuc <= ?");
//            }
//            whereQueries.add("LoaiPhieu = ?");
//            if (TrangThai != -1) {
//                whereQueries.add("TrangThai = ?");
//            }
//            String whereQuery = "";
//            for (int i = 0; i < whereQueries.size(); i++) {
//                String dieukien = whereQueries.get(i);
//                whereQuery += dieukien;
//                if (i != whereQueries.size() - 1) {
//                    whereQuery += " AND ";
//                }
//            }
//            System.out.println(whereQuery);
//           
//            sql += "WHERE " + whereQuery;
//            
//            PreparedStatement pr= conn.prepareStatement(sql);
//            if (NgayBatDau != null && NgayKetThuc != null) {
//                pr.setObject(1, NgayBatDauStr);
//                pr.setObject(2, NgayKetThucStr);
//                pr.setObject(3, LoaiPhieu);
//                if (TrangThai != -1){
//                    pr.setObject(4, TrangThai);
//                }
//            } else if (TrangThai != -1) {
//                pr.setObject(1, LoaiPhieu);
//                pr.setObject(2, TrangThai);
//            } else {
//                pr.setObject(1, LoaiPhieu);
//            }
//            
//        
//            //PreparedStatement pr= conn.prepareStatement(sql);
////            pr.setObject(1, dateStart);
////            pr.setObject(2, dateEnd);
////            pr.setObject(3, loai);
////            pr.setObject(4, trangThai);
//            
//            ResultSet rs= pr.executeQuery();
//            while(rs.next()){
//                PhieuGiamGia pgg= new PhieuGiamGia();
////                NhanVien nv= new NhanVien();
////                nv.setIdNV(rs.getLong(1));
//                pgg.setIdNV(rs.getLong(1));
//                pgg.setMaPhieu(rs.getString(2));
//                pgg.setTenPhieu(rs.getString(3));
//                pgg.setLoaiPhieu(rs.getInt(4));
//                pgg.setGiaTri(rs.getFloat(5));
//                pgg.setSoLuongPhieu(rs.getInt(6));
//                pgg.setDonToiThieu(rs.getBigDecimal(7));
//                pgg.setNgayBatDau(rs.getDate(8));
//                pgg.setNgayKetThuc(rs.getDate(9));
//                pgg.setNgayTao(rs.getDate(10));
//                pgg.setMoTa(rs.getString(11));
//                pgg.setTrangThai(rs.getInt(12));
//                
//                listPGG.add(pgg);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return listPGG;
//    }
    public Integer ThemPGG(PhieuGiamGia pgg) {
        Integer row = null;
        Connection conn = DBConnection.getConnect();
        String sql = "INSERT INTO PHIEU_GIAM_GIA (IdNV , MaPhieu , TenPhieu , LoaiPhieu , GiaTri , SoLuongPhieu , DonToiThieu , NgayBatDau , NgayKetThuc , MoTa)\n"
                + "VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
        try {
            PreparedStatement pr = conn.prepareCall(sql);
            pr.setObject(1, pgg.getIdNV().getId());
            pr.setObject(2, pgg.getMaPhieu());
            pr.setObject(3, pgg.getTenPhieu());
            pr.setObject(4, pgg.getLoaiPhieu());
            pr.setObject(5, pgg.getGiaTri());
            pr.setObject(6, pgg.getSoLuongPhieu());
            pr.setObject(7, pgg.getDonToiThieu());
            pr.setObject(8, pgg.getNgayBatDau());
            pr.setObject(9, pgg.getNgayKetThuc());
            pr.setObject(10, pgg.getMoTa());
            row = pr.executeUpdate();
        } catch (Exception e) {
        }
        return row;
    }

    public Integer SuaPGG(PhieuGiamGia pgg) {
        Integer row = null;
        Connection conn = DBConnection.getConnect();
        String sql = "UPDATE PHIEU_GIAM_GIA\n"
                + "SET TenPhieu = ? , LoaiPhieu = ? , GiaTri =? , SoLuongPhieu = ? , DonToiThieu = ? , NgayBatDau = ? , NgayKetThuc = ? , MoTa =? \n"
                + "WHERE MaPhieu LIKE ?";
        try {
            PreparedStatement pr = conn.prepareCall(sql);
            pr.setObject(1, pgg.getTenPhieu());
            pr.setObject(2, pgg.getLoaiPhieu());
            pr.setObject(3, pgg.getGiaTri());
            pr.setObject(4, pgg.getSoLuongPhieu());
            pr.setObject(5, pgg.getDonToiThieu());
            pr.setObject(6, pgg.getNgayBatDau());
            pr.setObject(7, pgg.getNgayKetThuc());
            pr.setObject(8, pgg.getMoTa());
            pr.setObject(9, pgg.getMaPhieu());

            row = pr.executeUpdate();
        } catch (Exception e) {
        }
        return row;
    }

    public Integer XoaPGG(String ma) {
        Integer row = null;
        Connection conn = DBConnection.getConnect();
        String sql = "DELETE PHIEU_GIAM_GIA WHERE MaPhieu=?";
        try {
            PreparedStatement pr = conn.prepareCall(sql);
            pr.setObject(1, ma);
//            pr.setObject(11, pgg.getMaPhieu());
//            pr.setObject(2, pgg.getTenPhieu());
//            pr.setObject(3, pgg.getLoaiPhieu());
//            pr.setObject(4, pgg.getGiaTri());
//            pr.setObject(5, pgg.getSoLuongPhieu());
//            pr.setObject(6, pgg.getDonToiThieu());
//            pr.setObject(7, pgg.getNgayBatDau());
//            pr.setObject(8, pgg.getNgayKetThuc());
//            pr.setObject(9, pgg.getMoTa());
//            pr.setObject(10, pgg.getTrangThai());
            row = pr.executeUpdate();
        } catch (Exception e) {
        }
        return row;
    }

    public ArrayList<PhieuGiamGia> getPhanTrang(int page) {
//        paginate();
        ArrayList<PhieuGiamGia> listPGG = new ArrayList<>();
        int startRow = (page - 1) * 10 + 1;
        int endRow = page * 10;
        Connection conn = DBConnection.getConnect();
        String sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY id) AS rownum,* FROM PHIEU_GIAM_GIA ) as PGG_NUMROW  WHERE rownum between ? and ?";
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setObject(1, startRow);
            pr.setObject(2, endRow);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                PhieuGiamGia pgg = new PhieuGiamGia();
//                NhanVien nv= new NhanVien();
//                nv.setIdNV(rs.getLong("IdNV"));
                pgg.setIdPGG(rs.getLong("ID"));
                //     pgg.setIdNV(rs.getLong("IDNV"));
                pgg.setMaPhieu(rs.getString("MaPhieu"));
                pgg.setTenPhieu(rs.getString("TenPhieu"));
                pgg.setLoaiPhieu(rs.getInt("LoaiPhieu"));
                pgg.setGiaTri(rs.getFloat("GiaTri"));
                pgg.setSoLuongPhieu(rs.getInt("SoLuongPhieu"));
                pgg.setDonToiThieu(rs.getFloat("DonToiThieu"));
                pgg.setNgayBatDau(rs.getDate("NgayBatDau"));
                pgg.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                pgg.setNgayTao(rs.getDate("NgayTao"));
                pgg.setMoTa(rs.getString("MoTa"));
                pgg.setTrangThai(rs.getInt("TrangThai"));

                listPGG.add(pgg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPGG;
    }

    public Integer countPageItem(int page) {
//        paginate();
        int startRow = (page - 1) * 10 + 1;
//        int endRow= page * 10;
        Connection conn = DBConnection.getConnect();
        String sql = "SELECT TOP 1 * FROM (SELECT ROW_NUMBER() OVER (ORDER BY id) AS rownum,COUNT(ID) AS sl FROM PHIEU_GIAM_GIA GROUP BY ID) as PGG_NUMROW  WHERE rownum >= ?";
        try {
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setObject(1, startRow);
            //pr.setObject(2, endRow);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getInt("sl");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getRowCountPGG() {
        String countSql = "SELECT COUNT(*) AS totalRows FROM PHIEU_GIAM_GIA";
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
