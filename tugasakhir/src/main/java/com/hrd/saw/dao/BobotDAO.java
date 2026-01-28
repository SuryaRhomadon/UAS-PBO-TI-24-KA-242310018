package com.hrd.saw.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hrd.saw.model.Bobot;

public class BobotDAO {

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:8889/db_tugasakhir";
        String user = "root";
        String pass = "root"; 
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    public List<Bobot> findAll() throws Exception {
        List<Bobot> list = new ArrayList<>();
        String sql = "SELECT id_bobot, id_kriteria, nilai_bobot FROM bobot";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bobot b = new Bobot();
                b.setIdBobot(rs.getInt("id_bobot"));
                b.setIdKriteria(rs.getInt("id_kriteria"));
                b.setNilaiBobot(rs.getDouble("nilai_bobot"));
                list.add(b);
            }
        }

        return list;
    }
}
