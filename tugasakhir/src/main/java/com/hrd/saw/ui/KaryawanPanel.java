package com.hrd.saw.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.hrd.saw.dao.KaryawanDAO;
import com.hrd.saw.model.Karyawan;

import java.awt.*;
import java.util.List;

public class KaryawanPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private DashboardPanel dashboard;

    private JTextField tfNama;
    private JTextField tfJabatan;
    private JTextField tfDepartemen;
    private JTextField tfStatus;

    public KaryawanPanel(DashboardPanel dashboard) {
        this.dashboard = dashboard;
        setLayout(null); // 🔥 ABSOLUTE LAYOUT

        // ================= BACK BUTTON =================
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(530, 29, 80, 25);
        btnBack.addActionListener(e -> dashboard.showCard("Dashboard"));
        add(btnBack);

        // ================= TITLE =================
        JLabel lblTitle = new JLabel("DATA KARYAWAN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBounds(275, 246, 153, 25);
        add(lblTitle);

        // ================= FORM =================
        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(38, 28, 100, 25);
        add(lblNama);

        tfNama = new JTextField();
        tfNama.setBounds(138, 28, 200, 25);
        add(tfNama);

        JLabel lblJabatan = new JLabel("Jabatan");
        lblJabatan.setBounds(38, 68, 100, 25);
        add(lblJabatan);

        tfJabatan = new JTextField();
        tfJabatan.setBounds(138, 68, 200, 25);
        add(tfJabatan);

        JLabel lblDepartemen = new JLabel("Departemen");
        lblDepartemen.setBounds(38, 108, 100, 25);
        add(lblDepartemen);

        tfDepartemen = new JTextField();
        tfDepartemen.setBounds(138, 108, 200, 25);
        add(tfDepartemen);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(38, 148, 100, 25);
        add(lblStatus);

        tfStatus = new JTextField();
        tfStatus.setBounds(138, 148, 200, 25);
        add(tfStatus);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(138, 184, 120, 30);
        btnSimpan.addActionListener(e -> simpanKaryawan());
        add(btnSimpan);

        // ================= TABLE =================
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(96, 287, 500, 180);
        add(scrollPane);

        loadKaryawan();
    }

    private void loadKaryawan() {
        try {
            KaryawanDAO dao = new KaryawanDAO();
            List<Karyawan> list = dao.findAll();

            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Nama", "Jabatan", "Departemen", "Status"}, 0
            );

            for (Karyawan k : list) {
                model.addRow(new Object[]{
                    k.getId(),
                    k.getNama(),
                    k.getJabatan(),
                    k.getDepartemen(),
                    k.getStatus()
                });
            }

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memuat data karyawan");
        }
    }

    private void simpanKaryawan() {
        try {
            if (tfNama.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama wajib diisi");
                return;
            }

            Karyawan k = new Karyawan();
            k.setNama(tfNama.getText());
            k.setJabatan(tfJabatan.getText());
            k.setDepartemen(tfDepartemen.getText());
            k.setStatus(tfStatus.getText());

            KaryawanDAO dao = new KaryawanDAO();
            dao.insert(k);

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan");

            clearForm();
            loadKaryawan();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data");
        }
    }

    private void clearForm() {
        tfNama.setText("");
        tfJabatan.setText("");
        tfDepartemen.setText("");
        tfStatus.setText("");
    }
}
