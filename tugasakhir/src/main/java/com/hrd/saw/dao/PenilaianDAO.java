package com.hrd.saw.dao;

import com.hrd.saw.model.DetailPenilaian;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PenilaianDAO {

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:8889/db_tugasakhir";
        String user = "root";
        String pass = "root";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    // DELETE OLD PENILAIAN (KEY FIX)
    public void deletePenilaianByKaryawan(int idKaryawan) throws Exception {
        String deleteDetail = 
            "DELETE dp FROM detail_penilaian dp " +
            "JOIN penilaian p ON dp.id_penilaian = p.id_penilaian " +
            "WHERE p.id_karyawan = ?";

        String deletePenilaian = 
            "DELETE FROM penilaian WHERE id_karyawan = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(deleteDetail);
                 PreparedStatement ps2 = conn.prepareStatement(deletePenilaian)) {

                ps1.setInt(1, idKaryawan);
                ps1.executeUpdate();

                ps2.setInt(1, idKaryawan);
                ps2.executeUpdate();

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        }
    }

    // INSERT PENILAIAN (NEW)
    public int insertPenilaian(int idKaryawan) throws Exception {
        String sql = "INSERT INTO penilaian (id_karyawan, tanggal_penilaian) VALUES (?, CURDATE())";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idKaryawan);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new Exception("Gagal mendapatkan id_penilaian");
        }
    }

    // INSERT DETAIL PENILAIAN
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

    // GET DETAIL BY KARYAWAN
    public List<DetailPenilaian> getDetailByKaryawan(int idKaryawan) throws Exception {
        List<DetailPenilaian> list = new ArrayList<>();
        String sql =
            "SELECT dp.id_detail, dp.id_penilaian, dp.id_kriteria, dp.nilai, k.tipe " +
            "FROM detail_penilaian dp " +
            "JOIN penilaian p ON dp.id_penilaian = p.id_penilaian " +
            "JOIN kriteria k ON dp.id_kriteria = k.id_kriteria " +
            "WHERE p.id_karyawan = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idKaryawan);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetailPenilaian dp = new DetailPenilaian();
                dp.setIdDetail(rs.getInt("id_detail"));
                dp.setIdPenilaian(rs.getInt("id_penilaian"));
                dp.setIdKriteria(rs.getInt("id_kriteria"));
                dp.setNilai(rs.getDouble("nilai"));
                dp.setTipe(rs.getString("tipe"));
                list.add(dp);
            }
        }
        return list;
    }
}
