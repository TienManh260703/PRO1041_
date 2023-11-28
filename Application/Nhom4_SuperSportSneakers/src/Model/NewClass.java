///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Model;
//
//import Repository.DBConnection;
//import java.util.List;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author manhnt
// */
//public class NewClass {
//
//    public static void main(String[] args) {
//        NewClass c = new NewClass();
//        SanPhamChiTiet ct = c.listSP().get(2);
//
//        HoaDon hd = new HoaDon(12L);
//        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(hd, ct, 2);
//        c.insertDGG(chiTietHoaDon);
//
//    }
//
//    public List<SanPhamChiTiet> listSP() {
//        List<SanPhamChiTiet> list = new ArrayList<>();
//        try {
//            String sql = "SELECT CTSP.ID AS ID, IdSP , IdThuongHieu, IdMau, IdSize  , MaCTSP , SoLuongTon , GiaNiemYet , GiaBan, CTSP.MoTa AS ID , CTSP.TrangThai AS TrangThai ,\n"
//                    + "	IdDGG , DGG.MaDGG AS MaDGG , DGG.Loai AS Loai , DGG.GiaTri AS GiaTri , DGG.TrangThai AS TrangThaiDGG \n"
//                    + "	\n"
//                    + "	FROM CHI_TIET_SAN_PHAM AS CTSP\n"
//                    + "  LEFT  JOIN DOT_GIAM_GIA AS DGG ON CTSP.IdDGG = DGG.ID ";
//            Connection con = DBConnection.getConnect();
//            Statement stm = con.createStatement();
//            ResultSet rs = stm.executeQuery(sql);
//            while (rs.next()) {
//                DotGiamGia_M dgg = new DotGiamGia_M(
//                        rs.getLong("IdDGG"),
//                        rs.getString("MaDGG"),
//                        rs.getInt("Loai") ,
//                        rs.getFloat("GiaTri"),
//                        rs.getInt("TrangThaiDGG"));
//                SanPhamChiTiet chiTietSanPham_M = new SanPhamChiTiet(
//                        rs.getLong("ID"),
//                        dgg,
//                        rs.getString("MaCTSP"),
//                        rs.getInt("SoLuongTon"),
//                        rs.getFloat("GiaBan"),
//                        rs.getFloat("GiaNiemYet"),
//                        new MauSac(rs.getLong("IdMau")),
//                        new KichThuoc(rs.getLong("IdSize")),
//                        new ThuongHieu(rs.getLong("IdThuongHieu")),
//                        new SanPham(rs.getLong("IdSP")));
//                list.add(chiTietSanPham_M);
//            }
//            return list;
//        } catch (SQLException ex) {
//            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
//
//    public void insertDGG(ChiTietHoaDon cthd) {
//        try {
//            String sql = "INSERT INTO HOADONCHITIET (IdHoaDon, IdCTSP, SoLuong , MaDGG , LoaiDGG ,"
//                    + " GiaTriDGG ,  QuyDoiDGGTT , DonGia , GiaBan)\n"
//                    + "	VALUES (?, ?, ? , ? , ? , ? ,  ? , ? , ?)";
//            Connection con = DBConnection.getConnect();
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setLong(1, cthd.getIdHoaDon().getId());
//            pstm.setLong(2, cthd.getIdCTSP().getIdSPCT());
//            pstm.setInt(3, cthd.getSoLuong());
//            pstm.setString(4, cthd.getIdCTSP().getIdDGG().getMaDGG());
//            
//            pstm.setInt(5, cthd.getIdCTSP().getIdDGG().getHinhThucDGG());
//            
//            pstm.setFloat(6, cthd.getIdCTSP().getIdDGG().getGiaTri());
//            float quiDoiDGGTT = cthd.getIdCTSP().getGiaNiemYet() - cthd.getIdCTSP().tinhGiaBan();
//            pstm.setFloat(7, quiDoiDGGTT);
//            pstm.setFloat(8, cthd.getIdCTSP().getGiaNiemYet());
//            pstm.setFloat(9, cthd.getIdCTSP().tinhGiaBan());
//            pstm.executeUpdate();
//            return;
//        } catch (SQLException ex) {
//            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//}
