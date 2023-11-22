/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.NhanVien;
import Utils.XDate2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tabah
 */
public class NhanVienRepository {

    private final String insert_sql = "insert into NHANVIEN(MaNhanVien,HoVaTen,MatKhau,NgaySinh,CCCD,Email,DiaChi,SDT,VaiTro,NgayTao,TrangThai)\n"
            + "values (?,?,?,?,?,?,?,?,?,?,?)";
    private final String update_sql = "update NhanVien set HoVaTen=?,NgaySinh=?,CCCD=?,Email=?,DiaChi=?,SDT=?,VaiTro=? where MaNhanVien=?";
    private final String thoiviec_sql = "update NhanVien set TrangThai=0 where MaNhanVien=?";
    private final String getcreatedinvoicebymanv = "SELECT NHANVIEN.ID,NHANVIEN.HoVaTen, COUNT(*)  AS N'Số Hóa Đơn Đã Tạo'\n"
            + "FROM HOADON\n"
            + "JOIN NHANVIEN ON NHANVIEN.ID = HOADON.IdNV\n"
            + "where NHANVIEN.MaNhanVien=?\n"
            + "GROUP BY NHANVIEN.ID,NHANVIEN.HoVaTen\n"
            + "ORDER BY NHANVIEN.ID;";
    private final String getdoanhsobymanv = "SELECT NHANVIEN.ID,NHANVIEN.HoVaTen,SUM(HOADON.ThanhTien) as N'Doanh Số'\n"
            + "FROM  HOADON\n"
            + "JOIN NHANVIEN ON NHANVIEN.ID = HOADON.IdNV\n"
            + "where NHANVIEN.MaNhanVien=?\n"
            + "GROUP BY NHANVIEN.ID,NHANVIEN.HoVaTen\n"
            + "ORDER BY NHANVIEN.ID;";
    private final String getcreatedcustomersbymanv = "SELECT NHANVIEN.ID,NHANVIEN.HoVaTen,Count(*) as N'Số khách hàng thêm mới'\n"
            + "FROM  KHACHHANG\n"
            + "JOIN NHANVIEN ON NHANVIEN.ID = KHACHHANG.IdNV\n"
            + "where NHANVIEN.MaNhanVien=?\n"
            + "GROUP BY NHANVIEN.ID,NHANVIEN.HoVaTen\n"
            + "ORDER BY NHANVIEN.ID;";
    private final String getcreatedpgg = "SELECT NHANVIEN.ID,NHANVIEN.HoVaTen,Count(*) as N'Số Phiếu Giảm Giá Đã Tạo'\n"
            + "FROM  PHIEU_GIAM_GIA\n"
            + "JOIN NHANVIEN ON NHANVIEN.ID = PHIEU_GIAM_GIA.IdNV\n"
            + "where NHANVIEN.MaNhanVien=?\n"
            + "GROUP BY NHANVIEN.ID,NHANVIEN.HoVaTen\n"
            + "ORDER BY NHANVIEN.ID;";
    private final String getcreateddgg = "SELECT NHANVIEN.ID,NHANVIEN.HoVaTen,Count(*) as N'Số Đợt Giảm Giá Đã Tạo'\n"
            + "FROM  DOT_GIAM_GIA\n"
            + "JOIN NHANVIEN ON NHANVIEN.ID = DOT_GIAM_GIA.IdNV\n"
            + "where NHANVIEN.MaNhanVien=?\n"
            + "GROUP BY NHANVIEN.ID,NHANVIEN.HoVaTen\n"
            + "ORDER BY NHANVIEN.ID;";
    private final String totalnv_sql = "select Count(*) from NhanVien";
    private final String select_all = "select * from NHANVIEN";

    public List<NhanVien> getALLSQL(String sql) {
        List<NhanVien> lst = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTenNhanVien(rs.getString("HoVaTen"));
                nv.setPassword(rs.getString("MatKhau"));
                nv.setNgaysinh((rs.getDate("NgaySinh")));
                nv.setEmail(rs.getString("Email"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setSDT(rs.getString("SDT"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setNgaytao((rs.getDate("NgayTao")));
                nv.setVaitro(rs.getBoolean("VaiTro"));
                nv.setTrangthailamviec(rs.getBoolean("TrangThai"));
//                if(rs.getInt("ROLE_NHANVIEN")==0) nv.setVaiTro(false);
//                else nv.setVaiTro(true);

                lst.add(nv);
            }
            rs.close();
            stm.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    public List<NhanVien> Filternameorsdt(String nameorsdt, String vaitro, String trangthai) {
        List<NhanVien> lst = new ArrayList<>();
        String sql = "sql= select * from NhanVien\n"
                + "where (HoVaTen like N'%?%' or SDT like '%?%') and VaiTro =?  and TrangThai = ?";
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(sql);
            //set parameter
            if (nameorsdt.equalsIgnoreCase("Tìm theo nhân viên hoặc sđt")) {
                stm.setString(1, "");
                stm.setString(2, "");
            } else {
                stm.setString(1, nameorsdt);
                stm.setString(2, nameorsdt);
            }
            if (vaitro.equalsIgnoreCase("Tất Cả")) {
                stm.setString(3, "(0,1)");
            } else if (vaitro.equalsIgnoreCase("Quản Lý")) {
                stm.setInt(3, 1);
            } else {
                stm.setInt(3, 0);
            }
            if (trangthai.equalsIgnoreCase("Đang Làm Việc")) {
                stm.setInt(4, 1);
            } //            else if(vaitro.equalsIgnoreCase("Quản Lý")) stm.setString(3,"()");
            else {
                stm.setInt(4, 0);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MaNhanVien"));
                nv.setTenNhanVien(rs.getString("HoVaTen"));
                nv.setPassword(rs.getString("MatKhau"));
                nv.setNgaysinh((rs.getDate("NgaySinh")));
                nv.setEmail(rs.getString("Email"));
                nv.setCCCD(rs.getString("CCCD"));
                nv.setSDT(rs.getString("SDT"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setNgaytao((rs.getDate("NgayTao")));
                nv.setVaitro(rs.getBoolean("VaiTro"));
                nv.setTrangthailamviec(rs.getBoolean("TrangThai"));
//                if(rs.getInt("ROLE_NHANVIEN")==0) nv.setVaiTro(false);
//                else nv.setVaiTro(true);

                lst.add(nv);

            }
        } catch (Exception e) {
        }
        return lst;
    }

    public void ADDNhanVien(NhanVien nv) {
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(insert_sql);
            stm.setString(1, nv.getMaNhanVien());
            stm.setString(2, nv.getTenNhanVien());
            stm.setString(3, nv.getPassword());
            stm.setDate(4, XDate2.convertUtilDateToSqlDate(nv.getNgaysinh()));
            stm.setString(5, nv.getCCCD());
            stm.setString(6, nv.getEmail());
            stm.setString(7, nv.getDiaChi());
            stm.setString(8, nv.getSDT());
            stm.setBoolean(9, nv.isVaitro());
            stm.setDate(10, XDate2.convertUtilDateToSqlDate(nv.getNgaytao()));
            stm.setBoolean(11, nv.isTrangthailamviec());
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateNhanVien(NhanVien nv) {
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(update_sql);
            stm.setString(8, nv.getMaNhanVien());
            stm.setString(1, nv.getTenNhanVien());
            stm.setDate(2, XDate2.convertUtilDateToSqlDate(nv.getNgaysinh()));
            stm.setString(3, nv.getCCCD());
            stm.setString(4, nv.getEmail());
            stm.setString(5, nv.getDiaChi());
            stm.setString(6, nv.getSDT());
            stm.setBoolean(7, nv.isVaitro());
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void BuocThoiViecNhanVien(String manv) {
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(thoiviec_sql);
            stm.setString(1, manv);
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (NumberFormatException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NhanVien> getALL() {
        return getALLSQL(select_all);
    }

    public int gettotalNhanVien() {
        int total = 0;
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(totalnv_sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return total;
    }

    public String automanv() {
        String manv = "NV";
        try {
            int newtotal = gettotalNhanVien() + 1;
            if (newtotal < 10) {
                manv += "00";
            } else if (newtotal < 100) {
                manv += "0";
            }
            manv += newtotal;
        } catch (Exception e) {
        }
        return manv;
    }

    public int getsohoadondataobymanv(String manv) {
        int total = 0;
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(getcreatedinvoicebymanv);
            stm.setString(1, manv);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getInt(3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }

    public int getdoanhsobymanv(String manv) {
        int total = 0;
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(getdoanhsobymanv);
            stm.setString(1, manv);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getInt(3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }

    public int getkhachhangdathembymanv(String manv) {
        int total = 0;
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(getcreatedcustomersbymanv);
            stm.setString(1, manv);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getInt(3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }

    public int getcreatedpggbymanv(String manv) {
        int total = 0;
        try {
            Connection con = DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(getcreatedpgg);
            stm.setString(1, manv);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getInt(3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }

    public int getcreateddggbymanv(String manv) {
        int total = 0;
        try {
            Connection con =  DBConnection.getConnect();
            PreparedStatement stm = con.prepareStatement(getcreateddgg);
            stm.setString(1, manv);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getInt(3);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }
}
