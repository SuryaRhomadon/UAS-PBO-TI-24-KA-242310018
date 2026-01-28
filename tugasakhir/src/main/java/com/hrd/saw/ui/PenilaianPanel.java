package com.hrd.saw.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.hrd.saw.dao.KaryawanDAO;
import com.hrd.saw.dao.KriteriaDAO;
import com.hrd.saw.dao.PenilaianDAO;
import com.hrd.saw.model.Karyawan;
import com.hrd.saw.model.Kriteria;
import java.util.List;

public class PenilaianPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JComboBox<String> cbKaryawan;
    private JTable tableKriteria;
    private DashboardPanel dashboard;

    public PenilaianPanel(DashboardPanel dashboard) {
        this.dashboard = dashboard;
        setLayout(null);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(650, 10, 100, 30);
        btnBack.addActionListener(e -> dashboard.showCard("Dashboard"));
        add(btnBack);

        JLabel lblKaryawan = new JLabel("Karyawan:");
        lblKaryawan.setBounds(20, 20, 80, 25);
        add(lblKaryawan);

        cbKaryawan = new JComboBox<>();
        cbKaryawan.setBounds(100, 20, 200, 25);
        add(cbKaryawan);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 60, 730, 350);
        add(scrollPane);

        tableKriteria = new JTable();
        scrollPane.setViewportView(tableKriteria);

        JButton btnSave = new JButton("Save Penilaian");
        btnSave.setBounds(300, 430, 150, 30);
        btnSave.addActionListener(e -> savePenilaian());
        add(btnSave);

        loadKaryawan();
        loadKriteria();
    }

    private void loadKaryawan() {
        try {
            cbKaryawan.removeAllItems();
            KaryawanDAO dao = new KaryawanDAO();
            List<Karyawan> list = dao.findAll();

            for (Karyawan k : list) {
                cbKaryawan.addItem(k.getId() + " - " + k.getNama());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadKriteria() {
        try {
            KriteriaDAO dao = new KriteriaDAO();
            List<Kriteria> list = dao.findAll();

            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID_Kriteria", "Kriteria", "Nilai"}, 0
            );

            for (Kriteria k : list) {
                model.addRow(new Object[]{k.getId(), k.getNamaKriteria(), ""});
            }

            tableKriteria.setModel(model);
            tableKriteria.getColumnModel().getColumn(0).setMinWidth(0);
            tableKriteria.getColumnModel().getColumn(0).setMaxWidth(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ============================
    // SAVE (REPLACE OLD DATA)
    // ============================
    private void savePenilaian() {
        try {
            String selected = cbKaryawan.getSelectedItem().toString();
            int idKaryawan = Integer.parseInt(selected.split(" - ")[0]);

            PenilaianDAO dao = new PenilaianDAO();

            // 🔥 DELETE OLD PENILAIAN FIRST
            dao.deletePenilaianByKaryawan(idKaryawan);

            // INSERT NEW PENILAIAN
            int idPenilaian = dao.insertPenilaian(idKaryawan);

            DefaultTableModel model = (DefaultTableModel) tableKriteria.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                int idKriteria = Integer.parseInt(model.getValueAt(i, 0).toString());
                String nilaiStr = model.getValueAt(i, 2).toString();
                double nilai = nilaiStr.isEmpty() ? 0 : Double.parseDouble(nilaiStr);

                dao.insertDetailPenilaian(idPenilaian, idKriteria, nilai);
            }

            JOptionPane.showMessageDialog(this, "Penilaian berhasil diperbarui!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menyimpan penilaian");
        }
    }

    public void refresh() {
        loadKaryawan();
        loadKriteria();
    }
}
