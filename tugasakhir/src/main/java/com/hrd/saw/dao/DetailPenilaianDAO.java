package com.hrd.saw.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hrd.saw.model.DetailPenilaian;

public class DetailPenilaianDAO {

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:8889/db_tugasakhir";
        String user = "root";
        String pass = "root";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    public void insertDetailPenilaian(int idPenilaian, int idKriteria, double nilai) throws Exception {
        String sql = "INSERT INTO detail_penilaian (id_penilaian, id_kriteria, nilai) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPenilaian);
            ps.setInt(2, idKriteria);
            ps.setDouble(3, nilai);
            ps.executeUpdate();
        }
    }

    public List<DetailPenilaian> getDetailByKaryawan(int idKaryawan) throws Exception {
        List<DetailPenilaian> list = new ArrayList<>();
        String sql = "SELECT dp.id_kriteria, dp.nilai, k.tipe " +
                     "FROM detail_penilaian dp " +
                     "JOIN penilaian p ON dp.id_penilaian = p.id_penilaian " +
                     "JOIN kriteria k ON dp.id_kriteria = k.id_kriteria " +
                     "WHERE p.id_karyawan = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idKaryawan);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetailPenilaian dp = new DetailPenilaian();
                    dp.setIdKriteria(rs.getInt("id_kriteria"));
                    dp.setNilai(rs.getDouble("nilai"));
                    dp.setTipe(rs.getString("tipe"));
                    list.add(dp);
                }
            }
        }

        return list;
    }
}
