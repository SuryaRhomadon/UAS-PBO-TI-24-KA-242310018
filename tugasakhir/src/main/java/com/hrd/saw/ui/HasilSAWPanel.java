package com.hrd.saw.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.hrd.saw.service.SAWService;
import com.hrd.saw.service.SAWService.SAWResult;
import java.awt.*;
import java.util.List;

public class HasilSAWPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
    private JTable table;
    private SAWService sawService = new SAWService();
    private DashboardPanel dashboard;

    public HasilSAWPanel(DashboardPanel dashboard) {
        this.dashboard = dashboard;
        setLayout(new BorderLayout());

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> dashboard.showCard("Dashboard"));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadResults();
    }

    private void loadResults() {
        try {
            List<SAWResult> results = sawService.calculateSAW();
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"Nama", "Nilai Preferensi", "Peringkat"}, 0);

            for (SAWResult r : results) {
                model.addRow(new Object[]{r.getNama(), r.getNilaiPreferensi(), r.getPeringkat()});
            }

            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error calculating SAW");
        }
    }
    
    public void refresh() {
        try {
            List<SAWService.SAWResult> results = sawService.calculateSAW();

            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Nama", "Nilai Preferensi", "Peringkat"}, 0
            );

            for (SAWService.SAWResult r : results) {
                model.addRow(new Object[]{
                    r.getNama(),
                    r.getNilaiPreferensi(),
                    r.getPeringkat()
                });
            }

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menghitung SAW");
        }
    }
}
