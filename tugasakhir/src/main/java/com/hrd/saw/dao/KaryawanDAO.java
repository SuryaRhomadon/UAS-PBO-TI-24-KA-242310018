package com.hrd.saw.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hrd.saw.model.Karyawan;

public class KaryawanDAO {

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:8889/db_tugasakhir";
        String user = "root";
        String pass = "root";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    public void insert(Karyawan k) throws Exception {
        String sql = "INSERT INTO karyawan (nama, jabatan, departemen, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, k.getNama());
            ps.setString(2, k.getJabatan());
            ps.setString(3, k.getDepartemen());
            ps.setString(4, k.getStatus());
            ps.executeUpdate();
        }
    }

    public List<Karyawan> findAll() throws Exception {
        List<Karyawan> list = new ArrayList<>();
        String sql = "SELECT * FROM karyawan";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Karyawan k = new Karyawan();
                k.setId(rs.getInt("id_karyawan"));
                k.setNama(rs.getString("nama"));
                k.setJabatan(rs.getString("jabatan"));
                k.setDepartemen(rs.getString("departemen"));
                k.setStatus(rs.getString("status"));
                list.add(k);
            }
        }
        return list;
    }
}
