/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import Model.ChiTietHoaDon;
import Model.SanPhamChiTiet;
import Model.DotGiamGia_M;
import Model.HoaDon;
import Model.KhachHang;
import Model.NhanVien;
import Model.PhieuGiamGia;
import Model.PhieuGiamGia;
import Repository.ChiTietHoaDon_RepositoryM;
import Repository.SanPhamCT_Repository;
import Repository.DotGiamGia_MRpository;
import Repository.HoaDon_MRepositoryM;
import Repository.KhachHangRepositoryM;
import Utils.MsgBox;
import Utils.XDate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import raven.application.Application;

/**
 *
 * @author manhnt
 */
public class Form_BanHang extends javax.swing.JPanel {

    private static List<SanPhamChiTiet> list = new ArrayList<>();
    private static List<HoaDon> listHD = new ArrayList<>();
    private static List<ChiTietHoaDon> listGH = new ArrayList<>();
    private static HoaDon_MRepositoryM hoaDon_MRepository = new HoaDon_MRepositoryM();
    private static SanPhamCT_Repository chiTietSanPham_Repository = new SanPhamCT_Repository();
    private static ChiTietHoaDon_RepositoryM chiTietHoaDon_Repository = new ChiTietHoaDon_RepositoryM();
    private static DotGiamGia_MRpository dotGiamGia_MRpository = new DotGiamGia_MRpository();
    private static KhachHangRepositoryM khachHangRepositoryM = new KhachHangRepositoryM();
    private static KhachHang defaultKhachHang = new KhachHang(1L, "KH00", "Khách bán lẻ", 0, 3);

    private static int page = 1;
    private static int lmit = 5;
    private static int gioiHanPage = (int) ((Math.ceil(chiTietSanPham_Repository.getRowCount() / lmit))) + 1;

    private DefaultTableModel dtm = new DefaultTableModel();
    private static int indexHD = -1;

    /**
     * Creates new form Form_BanHang
     */
    public Form_BanHang() {
        initComponents();
        init();
    }

    void init() {
        list = chiTietSanPham_Repository.getAll_M(page, lmit);
        fillToTableSP(list);
        lblPageTTKH.setText(1 + " / " + gioiHanPage);
        setLblCapBac(defaultKhachHang);
        cboTrangThaiHD.setSelectedIndex(0);
        listHD = hoaDon_MRepository.getAllHDByTrangThai(0);
        fillToTableHD(listHD);

    }

    private void setLblCapBac(KhachHang khachHang) {

        if (null != khachHang.getCapBac()) {
            lblDiem.setText(khachHang.getDiem() + "");
            txtMaKH.setText(khachHang.getMaKhachHang());
            txtTenKh.setText(khachHang.getTenKhachHang());

            switch (khachHang.getCapBac()) {
                case 0:
                    Color copperColor = new Color(184, 115, 51);
                    lblCapBac.setForeground(copperColor);
                    lblCapBac.setText("Đồng");
                    lblPhanTramHD.setText(khachHang.setGiamGiaCapBac(khachHang.getCapBac()) + "");
                    lblCapBac.setBackground(Color.GRAY);
                    break;
                case 1:
                    Color silverColor = new Color(192, 192, 192);
                    lblCapBac.setForeground(silverColor);
                    lblCapBac.setText("Bạc");
                    lblPhanTramHD.setText(khachHang.setGiamGiaCapBac(khachHang.getCapBac()) + "");
                    lblCapBac.setBackground(Color.GRAY);
                    break;
                case 2:
                    lblCapBac.setForeground(Color.YELLOW);
                    lblCapBac.setText("Vàng");
                    lblPhanTramHD.setText(khachHang.setGiamGiaCapBac(khachHang.getCapBac()) + "");
                    lblCapBac.setBackground(Color.GRAY);
                    break;
                case 3:
                    lblCapBac.setForeground(null);
                    lblCapBac.setBackground(null);
                    lblPhanTramHD.setText(khachHang.setGiamGiaCapBac(khachHang.getCapBac()) + "");
                    lblCapBac.setText("");
                    break;
                default:
                    break;
            }
        }
    }

    private void fillToTableSP(List<SanPhamChiTiet> list) {
        DefaultTableModel dtm = (DefaultTableModel) this.tblSP.getModel();
        dtm.setRowCount(0);
        for (SanPhamChiTiet ctspm : list) {
            dtm.addRow(ctspm.rowDataSPBH());
        }
    }

    private void fillToTableGH(List<ChiTietHoaDon> list) {
        dtm = (DefaultTableModel) this.tblGH.getModel();
        dtm.setRowCount(0);
        for (ChiTietHoaDon chiTietHoaDon : list) {
            dtm.addRow(chiTietHoaDon.rowDataGioHang());
            //setFormTT();
        }
    }

    private void fillToTableHD(List<HoaDon> list) {
        DefaultTableModel dtm = (DefaultTableModel) this.tblHD.getModel();
        dtm.setRowCount(0);
        for (HoaDon don : list) {
            dtm.addRow(don.rowDataHDBH());
        }
    }

    private void setFormTT(int index) {
        String maKH = tblHD.getValueAt(index, 3).toString();
        defaultKhachHang = khachHangRepositoryM.findKHByMaKH(maKH);
        setLblCapBac(defaultKhachHang);
        int khachHang = defaultKhachHang.getCapBac();
        // Float giaTriGiam = 0f;
        Float thanhTien = 0f;
        Float thanhTienKhLe = 0f;
        String maHD = tblHD.getValueAt(indexHD, 0).toString();
        String tenNhanVien = "Nguyễn Tiến Mạnh";
        txtMHD.setText(maHD);

        txtNV.setText(tenNhanVien);
        int i = 0;
        for (ChiTietHoaDon cthd : listGH) {
            if (defaultKhachHang.getCapBac() != 3) {
                // giaTriGiam += cthd.getIdCTSP().tinhGiaBan();
                thanhTien += cthd.setThanhTien2();
            } else {
                System.out.println(tblGH.getValueAt(i, 6).toString() + "0-----------------");
                thanhTien += Float.parseFloat(tblGH.getValueAt(i, 6).toString());
            }
            i++;

        }
        txtTongTien.setText(thanhTien + "");
        Float capBac = 0f;
        if (defaultKhachHang.getCapBac() == 0 || defaultKhachHang.getCapBac() == 3) {
            capBac = 0f;
            lblPhanTramHD.setText(capBac + " % ");
        } else if (defaultKhachHang.getCapBac() == 1) {
            capBac = 3f;
            lblPhanTramHD.setText(capBac + " % ");
        } else if (defaultKhachHang.getCapBac() == 2) {
            capBac = 8f;
            lblPhanTramHD.setText(capBac + " % ");
        }
        if (cboHTTT.getSelectedIndex() == 0) {
            txtChuyenKhoan.setEditable(false);
        } else {
            txtChuyenKhoan.setEditable(true);
        }
        float diemDoi = 0;
        float tienKhachDua = 0;
        try {
            if (khachHang != 3) {
                tienKhachDua = Float.parseFloat(txtThua.getText().trim());
            }

        } catch (Exception e) {
        }

        float tongTien2 = 0;
        tongTien2 = (((capBac / (float) 100) * thanhTien) + diemDoi);

        txtThua.setText(tienKhachDua + "");
        if (defaultKhachHang.getCapBac() != 3) {
            txtThanhTien.setText(tongTien2 + "");
        } else {
            txtThanhTien.setText(thanhTien + "");
        }

    }

    private void first() {
        lblPageTTKH.setText(1 + " / " + gioiHanPage);
        list = chiTietSanPham_Repository.getAll_M(1, lmit);
        fillToTableSP(list);
    }

    private void prev() {
        page--;
        if (page >= 1) {
            list = chiTietSanPham_Repository.getAll_M(page, lmit);
            lblPageTTKH.setText(page + " / " + gioiHanPage);
            fillToTableSP(list);
        } else {
            page = 1;
        }
    }

    private void next() {
        page++;
        if (page <= gioiHanPage) {
            list = chiTietSanPham_Repository.getAll_M(page, lmit);
            fillToTableSP(list);
            lblPageTTKH.setText(page + " / " + gioiHanPage);
            return;
        }
        page = gioiHanPage;
    }

    private void last() {
        lblPageTTKH.setText(gioiHanPage + " / " + gioiHanPage);
        list = chiTietSanPham_Repository.getAll_M(gioiHanPage, lmit);
        fillToTableSP(list);
    }

    private void insertHD(int index, int indexHD) {
        KhachHang khachHang = defaultKhachHang;
        String maCTSP = tblSP.getValueAt(index, 0).toString();
        Integer soLuongTon = Integer.parseInt(tblSP.getValueAt(index, 5).toString());
        DotGiamGia_M dgg = dotGiamGia_MRpository.getDGGByMaCTSP(maCTSP);
        NhanVien nhanVien_M = new NhanVien(2L);
        PhieuGiamGia phieuGiamGia = new PhieuGiamGia();
        int SLmua = 0;
        if (indexHD != -1) {

            // Hóa đơn
            String getMaHDTableHD = tblHD.getValueAt(indexHD, 0).toString();

            HoaDon hoaDon;
            hoaDon = new HoaDon(phieuGiamGia, nhanVien_M, defaultKhachHang, getMaHDTableHD);
            Long idHD = hoaDon_MRepository.findIDByMaHD(getMaHDTableHD);
            hoaDon.setId(idHD);
            SanPhamChiTiet chiTietSanPham = chiTietSanPham_Repository.getCTSPByMaCTSP(maCTSP);
// ko DGG

            if (dgg == null) {
                String soLuong = JOptionPane.showInputDialog("Xin mời bạn nhập số lượn sảng phẩm 1: " + "");
                try {
                    SLmua = Integer.parseInt(soLuong);
                    if (SLmua > chiTietSanPham_Repository.getSoLuongTonByMaCTSP(maCTSP)) {
                        MsgBox.aleart(this, "Số lượng sản phẩm không đủ");
                        SLmua = 0;
                        return;
                    }
                    chiTietSanPham_Repository.updateSLSP(soLuongTon - SLmua, maCTSP);
                    list = chiTietSanPham_Repository.getAll_M(page, lmit);
                    fillToTableSP(list);
                } catch (Exception e) {
                    MsgBox.aleart(this, "Hãy nhập số");
                    SLmua = 0;
                    return;
                }
                ChiTietHoaDon chiTietHoaDon;
                // update laij
                chiTietHoaDon = new ChiTietHoaDon(
                        hoaDon,
                        chiTietSanPham,
                        Integer.parseInt(soLuong),
                        "",
                        2,
                        0.0f,
                        0.0f,
                        chiTietSanPham.getGiaBan(),
                        chiTietSanPham.getGiaNiemYet(),
                        chiTietSanPham.getGiaBan() * chiTietSanPham.getGiaNiemYet());
                chiTietHoaDon_Repository.insertCTHD(chiTietHoaDon);
                tblHD.setRowSelectionInterval(indexHD, indexHD);
                String maHD = "";
                maHD = tblHD.getValueAt(indexHD, 0).toString();
                listGH = chiTietHoaDon_Repository.getAllHDCT(maHD);
                return;
            }
            chiTietSanPham.setIdDGG(dgg);
            //MsgBox.aleart(this, "Tạo thành công hóa đơn : " + maHD);
            String soLuong = JOptionPane.showInputDialog("Xin mời bạn nhập số lượn sảng phẩm : " + "");
            try {
                SLmua = Integer.parseInt(soLuong);
                if (SLmua > chiTietSanPham_Repository.getSoLuongTonByMaCTSP(maCTSP)) {
                    MsgBox.aleart(this, "Số lượng sản phẩm không đủ");
                    SLmua = 0;
                    return;
                }
                chiTietSanPham_Repository.updateSLSP(soLuongTon - SLmua, maCTSP);
                list = chiTietSanPham_Repository.getAll_M(page, lmit);
                fillToTableSP(list);
            } catch (Exception e) {
                MsgBox.aleart(this, "Hãy nhập số");
                SLmua = 0;
                return;
            }

            // cboTrangThaiHD.setSelectedIndex(0);
            ChiTietHoaDon chiTietHoaDon;
            chiTietHoaDon = new ChiTietHoaDon(
                    hoaDon,
                    chiTietSanPham,
                    Integer.parseInt(soLuong),
                    chiTietSanPham.getIdDGG().getMaDGG(),
                    chiTietSanPham.getIdDGG().getHinhThucDGG(),
                    chiTietSanPham.getIdDGG().getGiaTri(),
                    chiTietSanPham.getGiaGiam(),
                    chiTietSanPham.getGiaBan(),
                    chiTietSanPham.getGiaNiemYet(),
                    chiTietSanPham.getGiaBan() * chiTietSanPham.getGiaNiemYet());
            chiTietHoaDon_Repository.insertCTHD(chiTietHoaDon);
            tblHD.setRowSelectionInterval(indexHD, indexHD);
            String maHD = "";

            maHD = tblHD.getValueAt(indexHD, 0).toString();
            listGH = listGH = chiTietHoaDon_Repository.getAllHDCT(maHD);

            fillToTableGH(listGH);
            return;
        }
//        else {
//            MsgBox.aleart(this, "Thất bại thành công hóa đơn : " + getMaHDTableHD);
//        }
//-----------------------------------------------Tao Hoa Don------------------------------------------
        int maxHD = hoaDon_MRepository.getRowCountHD();
        String maHD = "HD";
        Date date = new Date();
        String dateStr = XDate.toString(date, "yy-MM-ddHHmm");
        maHD += dateStr + maxHD;
// Hoa Don

        HoaDon hoaDon;
        hoaDon = new HoaDon(phieuGiamGia, nhanVien_M, khachHang, maHD);

        if (indexHD == -1) {

            int ketQua = hoaDon_MRepository.create(hoaDon);
            listHD = hoaDon_MRepository.getAllHDByHTM(0);
            fillToTableHD(listHD);
            tblHD.setRowSelectionInterval(0, 0);
            if (ketQua == 1) {
                MsgBox.aleart(this, "Tạo thành công hóa đơn : " + maHD);
                System.out.println("In : " + Form_BanHang.indexHD + "     +  " + indexHD);
            }
        }

    }

    private void setCboTrangThaiHoaDon() {
        int trangThaiHD = cboTrangThaiHD.getSelectedIndex();
        listHD = hoaDon_MRepository.getAllHDByTrangThai(trangThaiHD);
        fillToTableHD(listHD);
    }

    private void setCboHinhThucMua() {
        int hinhThucBan = cboHinhThucBan.getSelectedIndex();
        listHD = hoaDon_MRepository.getAllHDByHTM(hinhThucBan);
        fillToTableHD(listHD);
    }

    private List<ChiTietHoaDon> getGioHang() {
        List<ChiTietHoaDon> list = new ArrayList<>();
        int rowCount = dtm.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object ktr = dtm.getValueAt(i, 7);
            if (ktr instanceof Boolean && (Boolean) ktr) {
                ChiTietHoaDon chiTietHoaDonRow = listGH.get(i);
                list.add(chiTietHoaDonRow);
            }
        }
        return list;
    }

    private void deleteGH() {

        String maHD = "";
//         update lai so luong
        for (ChiTietHoaDon chiTietHoaDon : getGioHang()) {
            Long idHD = hoaDon_MRepository.findIDByMaHD(chiTietHoaDon.getIdHoaDon().getMaHoaDon());
            maHD = chiTietHoaDon.getIdHoaDon().getMaHoaDon();
            // int soLuongSPGH = tblGH.getValueAt(ERROR, lmit)
            chiTietHoaDon_Repository.deleteHDCT(chiTietHoaDon.getIdCTSP().getIdSPCT(), idHD);
        }
        if (maHD.isEmpty()) {
            maHD = tblHD.getValueAt(indexHD, 0).toString();
        }
        fillToTableGH(chiTietHoaDon_Repository.getAllHDCT(maHD));

    }

//    private void deleteGHBySelected() {
//        String maHD = "";
//        // update lai so luong
//        for (ChiTietHoaDon chiTietHoaDon : getGioHang()) {
//            Long idHD = hoaDon_MRepository.findIDByMaHD(chiTietHoaDon.getIdHoaDon().getMaHoaDon());
//            maHD = chiTietHoaDon.getIdHoaDon().getMaHoaDon();
//            // int soLuongSPGH = tblGH.getValueAt(ERROR, lmit)
//            chiTietHoaDon_Repository.deleteHDCT(chiTietHoaDon.getIdCTSP().getIdSPCT(), idHD);
//        }
//        fillToTableGH(chiTietHoaDon_Repository.getAllHDCT(maHD));
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboTrangThaiHD = new javax.swing.JComboBox<>();
        cboHinhThucBan = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        btnXoaHD = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGH = new javax.swing.JTable();
        ckbAll = new javax.swing.JCheckBox();
        btnXoaGioiHang = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cboMauSac = new javax.swing.JComboBox<>();
        cbiSize = new javax.swing.JComboBox<>();
        cboHang = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnDau = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        lblPageTTKH = new javax.swing.JLabel();
        btnTien = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenKh = new javax.swing.JTextField();
        lblCapBac = new javax.swing.JLabel();
        btnThemNhanhKH = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblDiem = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtMHD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNV = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        lblGiamDiem = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        chkTenPhieu = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        lblPhanTramHD = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblVndHD = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cboHTTT = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        txtThua = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtChuyenKhoan = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cboTrangThaiHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Đang giao ", "Thanh toán trước", "-------------------------" }));
        cboTrangThaiHD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTrangThaiHDItemStateChanged(evt);
            }
        });

        cboHinhThucBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tại quầy", "Online" }));
        cboHinhThucBan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHinhThucBanItemStateChanged(evt);
            }
        });

        jLabel2.setText("Đơn đợi");

        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HD", "Ngày tạo", "Nhân viên", "Khách Hàng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHD);

        btnXoaHD.setText("Xóa");
        btnXoaHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(cboHinhThucBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHinhThucBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaHD)
                        .addGap(90, 90, 90))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Hóa đơn");

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblGH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Giảm giá", "Giá bán", "Thành tiền", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblGH);

        ckbAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ckbAll.setText("Tất cả");
        ckbAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbAllActionPerformed(evt);
            }
        });

        btnXoaGioiHang.setText("Xóa");
        btnXoaGioiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGioiHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckbAll)
                    .addComponent(btnXoaGioiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ckbAll)
                        .addGap(20, 20, 20)
                        .addComponent(btnXoaGioiHang)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Giỏ hàng");

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("Tim");

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Má CTSP", "Tên SP", "Màu Sắc", "Size", "Hãng", "SL tồn", "Đơn giá", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSP);

        jLabel15.setText("Màu sắc :");

        jLabel16.setText("Size :");

        jLabel17.setText("Hãng :");

        btnDau.setText("<<");
        btnDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauActionPerformed(evt);
            }
        });

        btnLui.setText("<");
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        lblPageTTKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPageTTKH.setText("1/ 2");

        btnTien.setText(">");
        btnTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienActionPerformed(evt);
            }
        });

        btnCuoi.setText(">>");
        btnCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel15)
                        .addGap(28, 28, 28)
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(cbiSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDau, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPageTTKH)
                .addGap(31, 31, 31)
                .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbiSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDau)
                    .addComponent(btnLui)
                    .addComponent(btnCuoi)
                    .addComponent(btnTien)
                    .addComponent(lblPageTTKH))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Danh sách sản phẩm");

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("Mã KH :");

        jLabel7.setText("Tên KH :");

        lblCapBac.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCapBac.setForeground(new java.awt.Color(255, 0, 0));
        lblCapBac.setText("Cấp bậc");

        btnThemNhanhKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemNhanhKH.setText("+");
        btnThemNhanhKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhKHActionPerformed(evt);
            }
        });

        jLabel9.setText("Điểm KH :");

        lblDiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDiem.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemNhanhKH))
                    .addComponent(lblDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCapBac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanhKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblDiem))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("Mã hóa đơn :");

        jLabel12.setText("Tên nhân viên :");

        jLabel13.setText("Tổng tiền :");

        jLabel14.setText("Tổng tiền :");

        jLabel18.setText("Giảm điểm :");

        txtDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemActionPerformed(evt);
            }
        });

        lblGiamDiem.setText("0");

        jLabel20.setText("Phiếu GG :");

        chkTenPhieu.setText("Tên phiếu");

        jLabel21.setText("% giảm HD :");

        lblPhanTramHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPhanTramHD.setText("0%");

        jLabel23.setText("Tiền giảm HD :");

        lblVndHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblVndHD.setText("VND");

        jLabel19.setText("HT thanh toán :");

        cboHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt ", "Chuyển Khoản", "Tiền mắt vs chuyển khoản" }));
        cboHTTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHTTTItemStateChanged(evt);
            }
        });

        jLabel22.setText("Tiền Thừa");

        txtThua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThuaActionPerformed(evt);
            }
        });

        jLabel24.setText("Tiền khách chuyển khoản :");

        txtChuyenKhoan.setEditable(false);
        txtChuyenKhoan.setText("0");
        txtChuyenKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChuyenKhoanActionPerformed(evt);
            }
        });

        jLabel25.setText("Tong Tien");

        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });

        jLabel26.setText("Tiền khách đưa :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(chkTenPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGiamDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPhanTramHD, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblVndHD, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNV, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(txtMHD)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(jTextField6)))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtThua, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTienKhachDua))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnThanhToan))
                            .addComponent(txtThanhTien))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(chkTenPhieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblGiamDiem)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(lblPhanTramHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblVndHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThanhToan)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Thông tin đơn hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Tại quầy", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 694, Short.MAX_VALUE)
        );

        tab.addTab("Đặt hàng", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Đơn hàng");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(511, 511, 511))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNhanhKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhKHActionPerformed
        Form_KhachHangJDialogManh form_KhachHangJDialog = new Form_KhachHangJDialogManh(new Application(), true);
        form_KhachHangJDialog.setVisible(true);
        defaultKhachHang = form_KhachHangJDialog.getSelectedKhachHang();
        indexHD = tblHD.getSelectedRow();
        if (indexHD != -1) {
            String maHD = tblHD.getValueAt(indexHD, 0).toString();
            hoaDon_MRepository.updateHDBy(defaultKhachHang, maHD);
            listHD = hoaDon_MRepository.getAllHDByTrangThai(0);
            fillToTableHD(listHD);
        }
        if (defaultKhachHang != null) {
            setLblCapBac(defaultKhachHang);
        }


    }//GEN-LAST:event_btnThemNhanhKHActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int index = tblSP.getSelectedRow();
        // indexHD = tblHD.getSelectedRow();
        insertHD(index, indexHD);
        listHD = hoaDon_MRepository.getAllHDByTrangThai(0);
        fillToTableHD(listHD);


    }//GEN-LAST:event_tblSPMouseClicked

    private void tblHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMouseClicked
        indexHD = tblHD.getSelectedRow();
        if (indexHD == -1) {
            return;
        }
        String maHD = tblHD.getValueAt(indexHD, 0).toString();
        listGH = chiTietHoaDon_Repository.getAllHDCT(maHD);
        fillToTableGH(listGH);
        setFormTT(indexHD);
    }//GEN-LAST:event_tblHDMouseClicked

    private void txtDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemActionPerformed
        float tt1 = Float.parseFloat(txtTongTien.getText().trim());
        if (txtDiem.getText().equals("")) {
            return;
        }
        if ((defaultKhachHang.getDiem() >= Integer.parseInt(txtDiem.getText().trim()))) {
            String diemSTr = txtDiem.getText().trim();
            int diem = Integer.parseInt(diemSTr);
            Float tienDiem = diem * (float) 10000;
            lblGiamDiem.setText(tienDiem + "");
            float tt = tt1 - tienDiem;
            txtThanhTien.setText(tt + "");
        } else {
            txtDiem.setText("0");
            txtThanhTien.setText(tt1 + "");
        }
    }//GEN-LAST:event_txtDiemActionPerformed

    private void txtThuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThuaActionPerformed

    private void txtChuyenKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChuyenKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChuyenKhoanActionPerformed

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void btnDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauActionPerformed
        first();
    }//GEN-LAST:event_btnDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        prev();
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        next();
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiActionPerformed
        last();
    }//GEN-LAST:event_btnCuoiActionPerformed

    private void cboTrangThaiHDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTrangThaiHDItemStateChanged
        setCboTrangThaiHoaDon();
        indexHD = -1;
    }//GEN-LAST:event_cboTrangThaiHDItemStateChanged

    private void cboHinhThucBanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHinhThucBanItemStateChanged
        setCboHinhThucMua();
        indexHD = -1;// TODO add your handling code here:
    }//GEN-LAST:event_cboHinhThucBanItemStateChanged

    private void btnXoaGioiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGioiHangActionPerformed
        deleteGH();
    }//GEN-LAST:event_btnXoaGioiHangActionPerformed

    private void btnXoaHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDActionPerformed
        indexHD = tblHD.getSelectedRow();
        String maHD = tblHD.getValueAt(indexHD, 0).toString();
        // update lai sol
        Long idHD = hoaDon_MRepository.findIDByMaHD(maHD);
        chiTietHoaDon_Repository.deleteAll(idHD);
        listGH = chiTietHoaDon_Repository.getAllHDCT(maHD);
        fillToTableGH(listGH);
        setLblCapBac(defaultKhachHang);
    }//GEN-LAST:event_btnXoaHDActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        float tienKHDua = Float.parseFloat(txtTienKhachDua.getText().trim());
        float thanhTien = Float.parseFloat(txtThanhTien.getText().trim());

        if (tienKHDua >= thanhTien) {
            float ketQua = tienKHDua - thanhTien;
            txtThua.setText(String.valueOf(ketQua));
        } else {
            MsgBox.aleart(this, "Tiền khách đưa phải > tiền thành tiền");
        }
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void cboHTTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHTTTItemStateChanged
        if (cboHTTT.getSelectedIndex() == 0) {
            txtChuyenKhoan.setEditable(true);
        } else {
            txtChuyenKhoan.setEditable(true);
        }
    }//GEN-LAST:event_cboHTTTItemStateChanged

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        System.out.println(defaultKhachHang.toString());
        String maHD = txtMHD.getText();
        int capBac = defaultKhachHang.getCapBac();
        int phuongThucTT = cboHTTT.getSelectedIndex();
        float tienThua = Float.parseFloat(txtThua.getText());
        float tienCK = Float.parseFloat(txtChuyenKhoan.getText());
        float thanhTien = Float.parseFloat(txtThanhTien.getText());
        float tienKhachDua = Float.parseFloat(txtTienKhachDua.getText());
        int hinhThucMua = tab.getSelectedIndex();
        float diemDoi = Integer.parseInt(lblGiamDiem.getText());
        Date date = new Date();
        NhanVien nhanVien_M = new NhanVien(1L);
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdNV(nhanVien_M);
        hoaDon.setMaHoaDon(maHD);
        hoaDon.setIdKH(defaultKhachHang);
        hoaDon.setCapBac(capBac);
        hoaDon.setDiemDoi(diemDoi);
        hoaDon.setTienPhieuGiam(0f);
        hoaDon.setPhanTramGG(0f);
        hoaDon.setPhuongThucTT(phuongThucTT);
        hoaDon.setTienThua(tienThua);
        hoaDon.setThanhTien(thanhTien);
        hoaDon.setTienKhChuyenKhoan(tienCK);
        hoaDon.setTienKhDua(tienKhachDua);
        System.out.println(XDate.toString(date, "yyyy-MM-dd HH:mm:ss.SSS"));

        hoaDon.setNgayThanhToan(date);

        hoaDon.setHinhThucMua(hinhThucMua == 0 ? false : true);
        //   (nhanVien_M, defaultKhachHang, maHD, capBac, 0f, 0f, diemDoi, phuongThucTT, tienKhachDua, tienCK, tienThua, thanhTien, date, hinhThucMua, capBac)
        hoaDon_MRepository.updateHD(hoaDon);
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void ckbAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbAllActionPerformed
        for (int i = 0; i < tblGH.getRowCount(); i++) {
            tblGH.setValueAt(true, i, 7);
            String maHD = "";
        }
    }//GEN-LAST:event_ckbAllActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemNhanhKH;
    private javax.swing.JButton btnTien;
    private javax.swing.JButton btnXoaGioiHang;
    private javax.swing.JButton btnXoaHD;
    private javax.swing.JComboBox<String> cbiSize;
    private javax.swing.JComboBox<String> cboHTTT;
    private javax.swing.JComboBox<String> cboHang;
    private javax.swing.JComboBox<String> cboHinhThucBan;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboTrangThaiHD;
    private javax.swing.JCheckBox chkTenPhieu;
    private javax.swing.JCheckBox ckbAll;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lblCapBac;
    private javax.swing.JLabel lblDiem;
    private javax.swing.JLabel lblGiamDiem;
    private javax.swing.JLabel lblPageTTKH;
    private javax.swing.JLabel lblPhanTramHD;
    private javax.swing.JLabel lblVndHD;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tblGH;
    private javax.swing.JTable tblHD;
    private javax.swing.JTable tblSP;
    private javax.swing.JTextField txtChuyenKhoan;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtMHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNV;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtThua;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
