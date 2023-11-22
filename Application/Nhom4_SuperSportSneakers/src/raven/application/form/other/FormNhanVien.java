package raven.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import Model.NhanVien;
import Repository.NhanVienRepository;
import Utils.MsgBox;
import Utils.XDate2;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Raven
 */
public class FormNhanVien extends javax.swing.JPanel {

    //Khai báo mảng khi filter
    private static List<Object> lst = new ArrayList<>();
    //Khai báo vị trí trang
    private int indexpage = 1;
    //Khai báo số trang
    private int totalpage = 0;
    //khai báo model table
    private DefaultTableModel model;
    //Khai báo Reponsitory Nhân Viên
    private NhanVienRepository nvreponsitoy = new NhanVienRepository();
    //Khai báo biến check bộ lọc
    private boolean checkfilter = false;
    //Khai báo biến vị trí hàng
    private int index = -1;
    //khai báo biến đang dùng nút hiện hay không
    private boolean hiddenpass = true;

    public FormNhanVien() {
        initComponents();
//        inits();//method xử lí các thao tác khi mở form lên
//        lb.putClientProperty(FlatClientProperties.STYLE, ""
//                + "font:$h1.font");
        filltable();
        lbl_page.setText(indexpage + "/" + totalpage);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_manv = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        txt_hovaten = new javax.swing.JTextField();
        txt_ngaysinh = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_cccd = new javax.swing.JTextField();
        cbo_chucvu = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_diachi = new javax.swing.JTextArea();
        txt_pass = new javax.swing.JPasswordField();
        btn_them = new javax.swing.JButton();
        btn_capnhat = new javax.swing.JButton();
        btn_buocthoiviec = new javax.swing.JButton();
        btn_new = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbo_fChucVu = new javax.swing.JComboBox<>();
        cbo_trangthai = new javax.swing.JComboBox<>();
        txt_findnameorsdt = new javax.swing.JTextField();
        btn_cancelfilter = new javax.swing.JButton();
        btn_filter = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_dsnv = new javax.swing.JTable();
        btn_showorhiddenpass = new javax.swing.JButton();
        btn_first = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        lbl_page = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_sohoadon = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_doanhso = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_khachhangthem = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_countpggcreatedbymanv = new javax.swing.JTextField();
        txt_countdggcreated = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Quản lý nhân viên");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Họ và Tên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Mã Nhân Viên");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("SDT");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Password");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Ngày Sinh");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("CCCD");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Chức Vụ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 153));
        jLabel10.setText("Địa chỉ");

        txt_manv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_manv.setText("Mã tự động");
        txt_manv.setEnabled(false);

        txt_sdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_hovaten.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_ngaysinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_cccd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbo_chucvu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_chucvu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân Viên", "Quản Lý" }));

        txt_diachi.setColumns(20);
        txt_diachi.setRows(5);
        jScrollPane1.setViewportView(txt_diachi);

        txt_pass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_capnhat.setText("Cập Nhật");
        btn_capnhat.setEnabled(false);
        btn_capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capnhatActionPerformed(evt);
            }
        });

        btn_buocthoiviec.setText("Buộc thôi việc");
        btn_buocthoiviec.setEnabled(false);
        btn_buocthoiviec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buocthoiviecActionPerformed(evt);
            }
        });

        btn_new.setText("New");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Chức vụ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Trạng Thái ");

        cbo_fChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Chức Vụ--", "Tất cả", "Nhân Viên", "Quản Lý" }));

        cbo_trangthai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn Trạng Thái--", "Tất Cả", "Đang làm việc", "Đã nghỉ" }));
        cbo_trangthai.setToolTipText("");

        txt_findnameorsdt.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_findnameorsdt.setText("Tìm theo tên hoặc số điện thoại");
        txt_findnameorsdt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_findnameorsdtMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_findnameorsdtMouseExited(evt);
            }
        });
        txt_findnameorsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_findnameorsdtActionPerformed(evt);
            }
        });

        btn_cancelfilter.setText("Hủy Bộ Lọc");
        btn_cancelfilter.setEnabled(false);
        btn_cancelfilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelfilterActionPerformed(evt);
            }
        });

        btn_filter.setText("Áp dụng bộ lọc");
        btn_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txt_findnameorsdt, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_fChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(btn_cancelfilter, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btn_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel11, jLabel12});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_fChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_findnameorsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelfilter)
                    .addComponent(btn_filter))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_dsnv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Nhân Viên", "Họ Và Tên", "Ngày Sinh", "CCCD", "Email", "DiaChi", "SDT", "VaiTro", "NgayTao", "Trạng Thái"
            }
        ));
        tbl_dsnv.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_dsnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dsnvMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_dsnv);
        if (tbl_dsnv.getColumnModel().getColumnCount() > 0) {
            tbl_dsnv.getColumnModel().getColumn(0).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_dsnv.getColumnModel().getColumn(1).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(1).setPreferredWidth(70);
            tbl_dsnv.getColumnModel().getColumn(2).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(2).setPreferredWidth(70);
            tbl_dsnv.getColumnModel().getColumn(3).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(3).setPreferredWidth(40);
            tbl_dsnv.getColumnModel().getColumn(4).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(4).setPreferredWidth(60);
            tbl_dsnv.getColumnModel().getColumn(5).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbl_dsnv.getColumnModel().getColumn(6).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(6).setPreferredWidth(150);
            tbl_dsnv.getColumnModel().getColumn(7).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(7).setPreferredWidth(50);
            tbl_dsnv.getColumnModel().getColumn(8).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(8).setPreferredWidth(40);
            tbl_dsnv.getColumnModel().getColumn(9).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(9).setPreferredWidth(40);
            tbl_dsnv.getColumnModel().getColumn(10).setResizable(false);
            tbl_dsnv.getColumnModel().getColumn(10).setPreferredWidth(55);
        }

        btn_showorhiddenpass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/raven/menu/icon/eye.png"))); // NOI18N
        btn_showorhiddenpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_showorhiddenpassActionPerformed(evt);
            }
        });

        btn_first.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_first.setText("<<");
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });

        btn_prev.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_prev.setText("<");
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });

        btn_next.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_next.setText(">");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_last.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_last.setText(">>");
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });

        lbl_page.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_page.setText("1/1");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Số Hóa Đơn Đã Tạo");

        txt_sohoadon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_sohoadon.setForeground(new java.awt.Color(255, 0, 51));
        txt_sohoadon.setText("0");
        txt_sohoadon.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Khách Hàng Thêm Mới");

        txt_doanhso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_doanhso.setForeground(new java.awt.Color(255, 0, 51));
        txt_doanhso.setText("0");
        txt_doanhso.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("Doanh Số Bán Hàng");

        txt_khachhangthem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_khachhangthem.setForeground(new java.awt.Color(255, 0, 51));
        txt_khachhangthem.setText("0");
        txt_khachhangthem.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("Đợt Giảm Giá Đã Tạo");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("Phiếu Giảm Giá Đã Tạo");

        txt_countpggcreatedbymanv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_countpggcreatedbymanv.setForeground(new java.awt.Color(255, 0, 51));
        txt_countpggcreatedbymanv.setText("0");
        txt_countpggcreatedbymanv.setEnabled(false);

        txt_countdggcreated.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_countdggcreated.setForeground(new java.awt.Color(255, 0, 51));
        txt_countdggcreated.setText("0");
        txt_countdggcreated.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_first)
                .addGap(18, 18, 18)
                .addComponent(btn_prev)
                .addGap(18, 18, 18)
                .addComponent(lbl_page, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_next)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_last)
                .addGap(380, 380, 380))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel2)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txt_hovaten, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btn_showorhiddenpass, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(60, 60, 60)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel9)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cbo_chucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel10)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txt_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addComponent(txt_manv, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(102, 102, 102)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(btn_them)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_capnhat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_buocthoiviec)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_new)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel13)
                                .addComponent(txt_sohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(txt_doanhso, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14)
                            .addComponent(txt_khachhangthem, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(txt_countpggcreatedbymanv, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(txt_countdggcreated, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel9});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbo_chucvu, jScrollPane1, txt_cccd});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_hovaten, txt_pass});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_doanhso, txt_khachhangthem, txt_sohoadon});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(txt_manv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cccd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel8)
                        .addComponent(txt_hovaten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_sohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_showorhiddenpass, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel9)
                        .addComponent(cbo_chucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel10)
                                        .addComponent(txt_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_doanhso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel14))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btn_new)
                                            .addComponent(btn_buocthoiviec)
                                            .addComponent(btn_capnhat)
                                            .addComponent(btn_them)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(txt_khachhangthem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 7, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_countpggcreatedbymanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_countdggcreated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_last)
                            .addComponent(btn_next)
                            .addComponent(lbl_page, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_prev)
                            .addComponent(btn_first)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_findnameorsdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_findnameorsdtActionPerformed

    }//GEN-LAST:event_txt_findnameorsdtActionPerformed

    private void txt_findnameorsdtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_findnameorsdtMouseClicked
        txt_findnameorsdt.setText("");
    }//GEN-LAST:event_txt_findnameorsdtMouseClicked

    private void txt_findnameorsdtMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_findnameorsdtMouseExited
        if (txt_findnameorsdt.getText().trim().equals("")) {
            txt_findnameorsdt.setText("Tìm theo tên hoặc số điện thoại");
        }
    }//GEN-LAST:event_txt_findnameorsdtMouseExited

    private void btn_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filterActionPerformed
        setStatusfilter(true);
        indexpage = 1;
        paddingtablefilter();
    }//GEN-LAST:event_btn_filterActionPerformed

    private void btn_cancelfilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelfilterActionPerformed
        setStatusfilter(false);
        tbl_dsnv.setRowSorter(null);
        changtotalpage();
        filltable();
    }//GEN-LAST:event_btn_cancelfilterActionPerformed

    private void btn_showorhiddenpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_showorhiddenpassActionPerformed
        if (hiddenpass) {
            txt_pass.setEchoChar((char) 0);
            hiddenpass = false;
        } else {
            txt_pass.setEchoChar('\u2022');
            hiddenpass = true;
        }
    }//GEN-LAST:event_btn_showorhiddenpassActionPerformed

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
        reset();
    }//GEN-LAST:event_btn_newActionPerformed

    private void tbl_dsnvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dsnvMouseClicked
        index = tbl_dsnv.getSelectedRow();
        showForm();
        setStatusbutton(true);
    }//GEN-LAST:event_tbl_dsnvMouseClicked

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        try {
            nvreponsitoy.ADDNhanVien(readform());
            changtotalpage();
            filltable();
            reset();
            System.out.println("Thêm Thành Công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
        try {
            if (MsgBox.confirm(this, "Bạn có muốn cập nhật thông tin không?")) {
                nvreponsitoy.UpdateNhanVien(readform());
                filltable();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_capnhatActionPerformed

    private void btn_buocthoiviecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buocthoiviecActionPerformed
        try {
            if (MsgBox.confirm(this, "Bạn có muốn buộc thôi việc người này không?")) {
                nvreponsitoy.BuocThoiViecNhanVien(txt_manv.getText());
                filltable();
                reset();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_buocthoiviecActionPerformed

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        indexpage = 1;
        if (checkfilter) {
            filltablefilter();
        } else {
            filltable();
        }
        lbl_page.setText(indexpage + "/" + totalpage);
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        if (indexpage > 1) {
            indexpage--;
            if (checkfilter) {
                filltablefilter();
            } else {
                filltable();
            }
            lbl_page.setText(indexpage + "/" + totalpage);
        }
    }//GEN-LAST:event_btn_prevActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        if (indexpage < totalpage) {
            indexpage++;
            if (checkfilter) {
                filltablefilter();
            } else {
                filltable();
            }
            lbl_page.setText(indexpage + "/" + totalpage);
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        indexpage = totalpage;
        if (checkfilter) {
            filltablefilter();
        } else {
            filltable();
        }
        lbl_page.setText(indexpage + "/" + totalpage);
    }//GEN-LAST:event_btn_lastActionPerformed
    void setStatusfilter(boolean check) {
        checkfilter = check;
        btn_filter.setEnabled(!check);
        btn_cancelfilter.setEnabled(check);
    }

    void setStatusbutton(boolean bl) {
        btn_buocthoiviec.setEnabled(bl);
        btn_capnhat.setEnabled(bl);
    }

    void filter() {
        //Khai báo list filter
        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        //Khai báo filter các dòng
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        filters.clear();
        tbl_dsnv.setRowSorter(tr);
        int countdk = 0;
        if (!txt_findnameorsdt.getText().trim().equalsIgnoreCase("Tìm theo tên hoặc số điện thoại")) {
            filters.add(countdk, RowFilter.regexFilter(txt_findnameorsdt.getText().trim()));
            countdk++;
        }
        if (cbo_fChucVu.getSelectedIndex() != 0 && cbo_fChucVu.getSelectedIndex() != 1) {
            filters.add(countdk, RowFilter.regexFilter(cbo_fChucVu.getSelectedItem().toString()));
            countdk++;
        }

        if (cbo_trangthai.getSelectedIndex() != 0 && cbo_trangthai.getSelectedIndex() != 1) {
            filters.add(countdk, RowFilter.regexFilter(cbo_trangthai.getSelectedItem().toString()));
            countdk++;
        }
        if (countdk == 0) {
            tbl_dsnv.setRowSorter(null);
        } else {
            RowFilter<Object, Object> compoundRowFilter = RowFilter.andFilter(filters);
            tr.setRowFilter(compoundRowFilter);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buocthoiviec;
    private javax.swing.JButton btn_cancelfilter;
    private javax.swing.JButton btn_capnhat;
    private javax.swing.JButton btn_filter;
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_showorhiddenpass;
    private javax.swing.JButton btn_them;
    private javax.swing.JComboBox<String> cbo_chucvu;
    private javax.swing.JComboBox<String> cbo_fChucVu;
    private javax.swing.JComboBox<String> cbo_trangthai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_page;
    private javax.swing.JTable tbl_dsnv;
    private javax.swing.JTextField txt_cccd;
    private javax.swing.JTextField txt_countdggcreated;
    private javax.swing.JTextField txt_countpggcreatedbymanv;
    private javax.swing.JTextArea txt_diachi;
    private javax.swing.JTextField txt_doanhso;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_findnameorsdt;
    private javax.swing.JTextField txt_hovaten;
    private javax.swing.JTextField txt_khachhangthem;
    private javax.swing.JTextField txt_manv;
    private javax.swing.JTextField txt_ngaysinh;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_sohoadon;
    // End of variables declaration//GEN-END:variables
    private void changtotalpage() {
        int total;
        if (checkfilter) {
            total = lst.size();
        } else {
            total = nvreponsitoy.gettotalNhanVien();
        }
        if (total % 5 == 0) {
            totalpage = total / 5;
        } else {
            totalpage = (total - (total % 5)) / 5 + 1;
        }
        lbl_page.setText(indexpage + "/" + totalpage);
    }

    private void paddingtablefilter() {
        lst.clear();
        model = (DefaultTableModel) tbl_dsnv.getModel();
        model.setRowCount(0);
        List<NhanVien> list = nvreponsitoy.getALL();
        int i = 0;
        for (NhanVien nv : list) {
            i++;
            String vaitro, trangthai;
            if (nv.isVaitro());
            if (nv.isVaitro()) {
                vaitro = "Quản Lý";
            } else {
                vaitro = "Nhân Viên";
            }
            if (nv.isTrangthailamviec()) {
                trangthai = "Đang làm việc";
            } else {
                trangthai = "Đã nghỉ";
            }
            Object[] ob = {i, nv.getMaNhanVien(), nv.getTenNhanVien(), XDate2.convertDateToString(nv.getNgaysinh(), "dd-MM-yyyy"), nv.getCCCD(), nv.getEmail(), nv.getDiaChi(), nv.getSDT(), vaitro, XDate2.convertDateToString(nv.getNgaytao(), "dd-MM-yyyy"), trangthai};
            model.addRow(ob);
        } //load lại table
        filter(); //áp dụng filter
        for (int index = 0; index < tbl_dsnv.getRowCount(); index++) {
            String manv = tbl_dsnv.getValueAt(index, 1).toString();
            String hovaten = tbl_dsnv.getValueAt(index, 2).toString();
            String email = tbl_dsnv.getValueAt(index, 5).toString();
            String ngaysinh = tbl_dsnv.getValueAt(index, 3).toString();
            String cccd = tbl_dsnv.getValueAt(index, 4).toString();
            String diachi = tbl_dsnv.getValueAt(index, 6).toString();
            String sdt = tbl_dsnv.getValueAt(index, 7).toString();
            String vaitro = tbl_dsnv.getValueAt(index, 8).toString();
            String ngaytao = tbl_dsnv.getValueAt(index, 9).toString();
            String trangthai = tbl_dsnv.getValueAt(index, 10).toString();
            Object[] ob = {index + 1, manv, hovaten, ngaysinh, cccd, email, diachi, sdt, vaitro, ngaytao, trangthai};
            lst.add(ob);
        } //đọc lại bảng      
        changtotalpage();
        filltablefilter(); //gọi hàm phân trang
    }

    private void reset() {
        index = -1;
        btn_capnhat.setEnabled(false);
        txt_ngaysinh.setText("");
        txt_cccd.setText("");
        txt_diachi.setText("");
        txt_email.setText("");
        txt_hovaten.setText("");
        txt_pass.setText("");
        txt_sdt.setText("");
        txt_manv.setText("Mã tự động");
        txt_sohoadon.setText("0");
        txt_doanhso.setText("0");
        txt_khachhangthem.setText("0");
        txt_countdggcreated.setText("0");
        txt_countpggcreatedbymanv.setText("0");

        setStatusbutton(false);
    }

    //phương thức đọc form trả về variable Nhân Viên
    private NhanVien readform() {
        NhanVien nv = new NhanVien();
        if (btn_capnhat.isEnabled()) {
            nv.setMaNhanVien(txt_manv.getText());
        } else {
            nv.setMaNhanVien(nvreponsitoy.automanv());
        }
        try {
            nv.setTenNhanVien(txt_hovaten.getText());
            Date ngsinh = XDate2.convertStringToDate(txt_ngaysinh.getText().trim(), "dd-MM-yyyy");
            nv.setNgaysinh(ngsinh); //Convert String ngày sinh thành LocalDate;
            nv.setDiaChi(txt_diachi.getText());
            nv.setCCCD(txt_cccd.getText());
            nv.setEmail(txt_email.getText());
            nv.setNgaytao(XDate2.getCurrentDate());
            nv.setPassword(txt_pass.getText());
            nv.setSDT(txt_sdt.getText());
            nv.setTrangthailamviec(true);
        } catch (ParseException e) {
            System.out.println("Lỗi đọc form");
        }

        return nv;
    }

    private void showForm() {
        txt_manv.setText(tbl_dsnv.getValueAt(index, 1).toString());
        txt_hovaten.setText(tbl_dsnv.getValueAt(index, 2).toString());
        txt_email.setText(tbl_dsnv.getValueAt(index, 5).toString());
        txt_ngaysinh.setText(tbl_dsnv.getValueAt(index, 3).toString());
        txt_cccd.setText(tbl_dsnv.getValueAt(index, 4).toString());
        txt_diachi.setText(tbl_dsnv.getValueAt(index, 6).toString());
        txt_sdt.setText(tbl_dsnv.getValueAt(index, 7).toString());
        cbo_chucvu.setSelectedItem(tbl_dsnv.getValueAt(index, 8));
        txt_sohoadon.setText(String.valueOf(nvreponsitoy.getsohoadondataobymanv(tbl_dsnv.getValueAt(index, 1).toString())));
        txt_doanhso.setText(String.valueOf(nvreponsitoy.getdoanhsobymanv(tbl_dsnv.getValueAt(index, 1).toString())));
        txt_khachhangthem.setText(String.valueOf(nvreponsitoy.getkhachhangdathembymanv((tbl_dsnv.getValueAt(index, 1).toString()))));
        txt_countdggcreated.setText(String.valueOf(nvreponsitoy.getcreateddggbymanv((tbl_dsnv.getValueAt(index, 1).toString()))));
        txt_countpggcreatedbymanv.setText(String.valueOf(nvreponsitoy.getcreatedpggbymanv((tbl_dsnv.getValueAt(index, 1).toString()))));
    }

    private void filltablefilter() {
        model = (DefaultTableModel) tbl_dsnv.getModel();
        model.setRowCount(0);
        int i = 0;
        int limit = indexpage * 5;
        for (Object ob : lst) {
            i++;
            if (i <= limit && i > (indexpage - 1) * 5) {
                model.addRow((Object[]) ob);
            }
        }
    }

    private void filltable() {
        changtotalpage();
        model = (DefaultTableModel) tbl_dsnv.getModel();
        model.setRowCount(0);
        List<NhanVien> list = nvreponsitoy.getALL();
//        try {
//            if (checkfilter) {
//                //filltable theo bộ lọc trong panel bộ lọc phù hợp với các trường dữ liệu của bộ lọc
//                String name = txt_findnameorsdt.getText();
//                String vaitro = cbo_fChucVu.getSelectedItem().toString();
//                String trangthai = cbo_trangthai.getSelectedItem().toString();
//                lst = nvreponsitoy.Filternameorsdt(name, vaitro, trangthai);
//            } else {
//                //filltable bình thường
//                
//            }
//        } catch (Exception e) {
//            MsgBox.aleart(this, e.getMessage());
//        }
        int i = 0;
        for (NhanVien nv : list) {
            int limit = indexpage * 5;
            i++;
            if (i <= limit && i > (indexpage - 1) * 5) {
                String vaitro, trangthai;
                if (nv.isVaitro());
                if (nv.isVaitro()) {
                    vaitro = "Quản Lý";
                } else {
                    vaitro = "Nhân Viên";
                }
                if (nv.isTrangthailamviec()) {
                    trangthai = "Đang làm việc";
                } else {
                    trangthai = "Đã nghỉ";
                }
                Object[] ob = {i, nv.getMaNhanVien(), nv.getTenNhanVien(), XDate2.convertDateToString(nv.getNgaysinh(), "dd-MM-yyyy"), nv.getCCCD(), nv.getEmail(), nv.getDiaChi(), nv.getSDT(), vaitro, XDate2.convertDateToString(nv.getNgaytao(), "dd-MM-yyyy"), trangthai};
                model.addRow(ob);
            }
        }
    }
}
