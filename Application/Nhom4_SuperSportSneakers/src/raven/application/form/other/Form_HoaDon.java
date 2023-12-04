/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package raven.application.form.other;

import Impl.ExcelHoaDon;
import Model.ChiTietHoaDon;
import Model.HoaDon;
import Repository.ChiTietDotGiamRepository;
import Repository.ChiTietHoaDon_RepositoryM;
import Repository.HoaDon_MRepositoryM;
import Utils.MsgBox;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pro1041.team_3.swing.jnafilechooser.api.JnaFileChooser;
import raven.application.Application;

/**
 *
 * @author manhnt
 */
public class Form_HoaDon extends javax.swing.JPanel {

    private ExcelHoaDon excelHoaDon = new ExcelHoaDon();

    private static List<HoaDon> listEx = new ArrayList<>();

    private static HoaDon_MRepositoryM hoaDon_MRepositoryM = new HoaDon_MRepositoryM();
    private static ChiTietHoaDon_RepositoryM chiTietHoaDon_RepositoryM = new ChiTietHoaDon_RepositoryM();
    private static List<HoaDon> listHD = new ArrayList<>();
    private static List<ChiTietHoaDon> listCTHD = new ArrayList<>();
    static int page = 1;
    static int lmit = 9;
    private int index = 1;
    private int gioiHanPage = (int) ((Math.ceil(hoaDon_MRepositoryM.getRowCountHD() / lmit))) + 1;

    /**
     * Creates new form Form_HoaDon
     */
    public Form_HoaDon() {
        initComponents();
        listHD = hoaDon_MRepositoryM.getAlHD_HD("", page, lmit);
        fillToTableHD(listHD);
    }

    private void fillToTableHD(List<HoaDon> list) {
        DefaultTableModel dtm = (DefaultTableModel) this.tblHD3.getModel();
        int i = 1;
        dtm.setRowCount(0);
        for (HoaDon hd : list) {
            dtm.addRow(hd.rowData_HD(i));
            i++;
        }
        lblSoHD.setText(hoaDon_MRepositoryM.getRowCountHD() + "");
    }

    private void fillToTableHDCT(List<ChiTietHoaDon> list) {
        DefaultTableModel dtm = (DefaultTableModel) this.tblHDCT.getModel();
        int i = 1;
        dtm.setRowCount(0);
        for (ChiTietHoaDon hd : list) {
            dtm.addRow(hd.rowDataHD(i));
            i++;
        }
    }

    
    private void first() {
        if (index == 2) {
            listHD = hoaDon_MRepositoryM.getAll_Loc(txtDau.getDate(), txtDau1.getDate(), cboLoai.getSelectedIndex() - 1, cboTrangThai.getSelectedIndex() - 1, 1, lmit);
            lblPageTTKH.setText(1 + " / " + gioiHanPage);
            fillToTableHD(listHD);
        } else {
            listHD = hoaDon_MRepositoryM.getAlHD_HD(txtTim.getText().trim(), page, lmit);
            lblPageTTKH.setText(1 + " / " + gioiHanPage);
            fillToTableHD(listHD);
        }

        //  fillTable(listHD);
    }

    private void prev() {
        page--;

        if (page >= 1) {
            if (index == 2) {
                lblPageTTKH.setText(page + " / " + gioiHanPage);
                listHD = hoaDon_MRepositoryM.getAll_Loc(txtDau.getDate(), txtDau1.getDate(), cboLoai.getSelectedIndex() - 1, cboTrangThai.getSelectedIndex() - 1, page, lmit);
                fillToTableHD(listHD);
                return;
            } else {

                lblPageTTKH.setText(page + " / " + gioiHanPage);
                listHD = hoaDon_MRepositoryM.getAlHD_HD(txtTim.getText().trim(), page, lmit);
                fillToTableHD(listHD);
                return;

            }
        }
        page = 1;

    }

    private void next() {
        page++;

        if (page <= gioiHanPage) {
            if (index == 2) {
                listHD = hoaDon_MRepositoryM.getAll_Loc(txtDau.getDate(), txtDau1.getDate(), cboLoai.getSelectedIndex() - 1, cboTrangThai.getSelectedIndex() - 1, page, lmit);
                fillToTableHD(listHD);
                lblPageTTKH.setText(page + " / " + gioiHanPage);
                return;
            } else {
                listHD = hoaDon_MRepositoryM.getAlHD_HD(txtTim.getText().trim(), page, lmit);
                fillToTableHD(listHD);
                lblPageTTKH.setText(page + " / " + gioiHanPage);
                return;
            }
        }
        page = gioiHanPage;
    }

    private void last() {
        if (index == 2) {
            listHD = hoaDon_MRepositoryM.getAll_Loc(txtDau.getDate(), txtDau1.getDate(), cboLoai.getSelectedIndex() - 1, cboTrangThai.getSelectedIndex() - 1, page, lmit);
            lblPageTTKH.setText(gioiHanPage + " / " + gioiHanPage);

            fillToTableHD(listHD);
        } else {
            listHD = hoaDon_MRepositoryM.getAlHD_HD(txtTim.getText().trim(), page, lmit);
            lblPageTTKH.setText(gioiHanPage + " / " + gioiHanPage);
            fillToTableHD(listHD);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        txtDau = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDau1 = new com.toedter.calendar.JDateChooser();
        cboLoai = new javax.swing.JComboBox<>();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHD3 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnLamMoi1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnInHoaDon = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnDau = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblSoHD = new javax.swing.JLabel();
        btnLui = new javax.swing.JButton();
        lblPageTTKH = new javax.swing.JLabel();
        btnTien = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Hóa đơn");

        jLabel2.setText("Tìm Kiếm :");

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        txtDau.setDateFormatString("dd-MM-yyyy");
        txtDau.setMaxSelectableDate(new java.util.Date(253370743316000L));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Từ ngày :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Đến ngày :");

        txtDau1.setDateFormatString("dd-MM-yyyy");
        txtDau1.setMaxSelectableDate(new java.util.Date(253370743316000L));

        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Tất cả ---", "Tại quầy", "Đặt hàng" }));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Tất cả ---", "Chưa thanh toán", "Đã thanh toán", " " }));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Loại hóa đơn :");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Trạng thái hóa đơn :");

        tblHD3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Tổng tiền", "Loại hóa đơn", "Ngày tạo", "Ngày thanh toán", "Trạng thái"
            }
        ));
        tblHD3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHD3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHD3);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Danh sách hóa đơn : ");

        jButton1.setText("Lọc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnLamMoi1.setText("Tìm");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtDau, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDau1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15))
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16))
                            .addComponent(btnLamMoi1))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoi)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1291, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi)
                            .addComponent(btnLamMoi1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDau1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel16)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Giá bán", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tblHDCT);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Hóa đơn chi tiết");

        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        jButton7.setText("Xuất dánh sach hóa đơn");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Xem chi tiết hóa đơn");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton6.setText("Hủy");

        btnDau.setText("<<");
        btnDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Số hóa đơn:");

        lblSoHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSoHD.setForeground(new java.awt.Color(255, 0, 0));
        lblSoHD.setText("0");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(lblSoHD)
                                .addGap(18, 18, 18)
                                .addComponent(btnDau, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPageTTKH)
                                .addGap(18, 18, 18)
                                .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(196, 196, 196)
                                .addComponent(btnInHoaDon)
                                .addGap(44, 44, 44)
                                .addComponent(jButton7)
                                .addGap(65, 65, 65)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6))
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1292, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(btnDau)
                        .addComponent(btnLui)
                        .addComponent(btnCuoi)
                        .addComponent(btnTien)
                        .addComponent(lblPageTTKH)
                        .addComponent(lblSoHD))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInHoaDon)
                        .addComponent(jButton7)
                        .addComponent(jButton8)
                        .addComponent(jButton6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        index = 2;
        Date date1 = null;
        Date date2 = null;
        int loai = cboLoai.getSelectedIndex() - 1;
        int tt = cboTrangThai.getSelectedIndex() - 1;
        try {
            date1 = txtDau.getDate();
            date2 = txtDau1.getDate();
        } catch (Exception e) {
            MsgBox.aleart(this, "Ngày tháng năm ko hợp lệ");
            return;
        }
        System.out.println(" l " + loai);
        System.out.println(" tt " + tt);
        listHD = hoaDon_MRepositoryM.getAll_Loc(date1, date2, loai, tt, page, lmit);
        listEx = hoaDon_MRepositoryM.getAll_Loc_ALL(date2, date2, loai, tt);
        fillToTableHD(listHD);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed

    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        index = 1;
        String tt = txtTim.getText().trim();
        if (tt.isEmpty()) {

            return;
        }
        listHD = hoaDon_MRepositoryM.getAlHD_HD(tt, page, lmit);
        fillToTableHD(listHD);
    }//GEN-LAST:event_txtTimKeyReleased

    private void tblHD3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHD3MouseClicked
        int index = tblHD3.getSelectedRow();
        String maHD = tblHD3.getValueAt(index, 1).toString();

        listCTHD = chiTietHoaDon_RepositoryM.getListCTHDByMaHD(maHD);
        fillToTableHDCT(listCTHD);
    }//GEN-LAST:event_tblHD3MouseClicked

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

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int index = tblHD3.getSelectedRow();
        String maHD = tblHD3.getValueAt(index, 1).toString();
        System.out.println("ravormed()" + " " + chiTietHoaDon_RepositoryM.viewCTHDByMaHD(maHD));

        View_CTHD view_CTHD = new View_CTHD(new Application(), true, maHD);
        view_CTHD.setVisible(true);

    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JnaFileChooser jfc = new JnaFileChooser();
        jfc.setMode(JnaFileChooser.Mode.Directories);
        if (!jfc.showOpenDialog((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this))) {
            return;
        }
        String path = jfc.getSelectedFile().getAbsolutePath();
        LocalDateTime local = LocalDateTime.now();
        File file = new File(path + "\\Danh_Sach_HD_" + local.getDayOfMonth() + "_" + local.getMonthValue() + "_" + local.getYear() + ".xlsx");

        if (excelHoaDon.export(file,listEx)) {
            JOptionPane.showMessageDialog(this, "Export thành công", "Export", JOptionPane.INFORMATION_MESSAGE);
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    try {
                        desktop.open(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Mở thất bại", "Export", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại", "Export", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnTien;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPageTTKH;
    private javax.swing.JLabel lblSoHD;
    private javax.swing.JTable tblHD3;
    private javax.swing.JTable tblHDCT;
    private com.toedter.calendar.JDateChooser txtDau;
    private com.toedter.calendar.JDateChooser txtDau1;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
