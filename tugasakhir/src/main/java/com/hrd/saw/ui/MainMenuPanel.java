package com.hrd.saw.ui;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
    public MainMenuPanel(DashboardPanel dashboard) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton btnKaryawan = new JButton("Karyawan");
        JButton btnPenilaian = new JButton("Penilaian");
        JButton btnHasil = new JButton("Hasil SAW");

        btnKaryawan.addActionListener(e -> dashboard.showCard("Karyawan"));
        btnPenilaian.addActionListener(e -> dashboard.showCard("Penilaian"));
        btnHasil.addActionListener(e -> dashboard.showCard("HasilSAW"));

        gbc.gridx = 0; gbc.gridy = 0; add(btnKaryawan, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(btnPenilaian, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(btnHasil, gbc);
    }
}
