package com.hrd.saw.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel cards;
    private CardLayout cardLayout;

    // 🔹 SIMPAN INSTANCE PANEL
    private KaryawanPanel karyawanPanel;
    private PenilaianPanel penilaianPanel;
    private HasilSAWPanel hasilPanel;

    public DashboardPanel() {
        setTitle("HRD SAW Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // 🔹 INIT PANEL SEBAGAI INSTANCE
        karyawanPanel = new KaryawanPanel(this);
        penilaianPanel = new PenilaianPanel(this);
        hasilPanel = new HasilSAWPanel(this);

        cards.add(new MainMenuPanel(this), "Dashboard");
        cards.add(karyawanPanel, "Karyawan");
        cards.add(penilaianPanel, "Penilaian");
        cards.add(hasilPanel, "HasilSAW");

        add(cards);
        cardLayout.show(cards, "Dashboard");
    }

    // 🔹 NAVIGATION METHOD
    public void showCard(String name) {

        if (name.equals("Penilaian")) {
            penilaianPanel.refresh(); // ✅ BENAR
        }

        cardLayout.show(cards, name); // ❗ gunakan cards
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardPanel frame = new DashboardPanel();
            frame.setVisible(true);
        });
    }
}
