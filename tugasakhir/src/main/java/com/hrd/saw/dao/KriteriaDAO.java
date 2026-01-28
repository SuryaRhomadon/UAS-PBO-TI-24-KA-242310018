package com.hrd.saw.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hrd.saw.model.Kriteria;

public class KriteriaDAO {

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:8889/db_tugasakhir";
        String user = "root";
        String pass = "root"; 
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    public List<Kriteria> findAll() throws Exception {
        List<Kriteria> list = new ArrayList<>();
        String sql = "SELECT id_kriteria, nama_kriteria, tipe FROM kriteria ORDER BY id_kriteria";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Kriteria k = new Kriteria();
                k.setId(rs.getInt("id_kriteria"));
                k.setNamaKriteria(rs.getString("nama_kriteria"));
                k.setTipe(rs.getString("tipe"));
                list.add(k);
            }
        }

        return list;
    }
}
