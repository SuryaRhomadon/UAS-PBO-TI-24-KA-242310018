package com.hrd.saw.service;

import com.hrd.saw.dao.BobotDAO;
import com.hrd.saw.dao.KaryawanDAO;
import com.hrd.saw.dao.PenilaianDAO;
import com.hrd.saw.model.Bobot;
import com.hrd.saw.model.Karyawan;
import com.hrd.saw.model.DetailPenilaian;

import java.util.*;

public class SAWService {

    private KaryawanDAO karyawanDAO = new KaryawanDAO();
    private PenilaianDAO penilaianDAO = new PenilaianDAO();
    private BobotDAO bobotDAO = new BobotDAO();

    // Inner class to store results
    public static class SAWResult {
        private String nama;
        private double nilaiPreferensi;
        private int peringkat;

        public SAWResult(String nama, double nilaiPreferensi) {
            this.nama = nama;
            this.nilaiPreferensi = nilaiPreferensi;
        }

        public String getNama() { return nama; }
        public double getNilaiPreferensi() { return nilaiPreferensi; }
        public int getPeringkat() { return peringkat; }
        public void setPeringkat(int peringkat) { this.peringkat = peringkat; }
    }

    public List<SAWResult> calculateSAW() throws Exception {
    	List<Karyawan> allKaryawan = karyawanDAO.findAll();
    	List<Karyawan> karyawans = new ArrayList<>();

    	for (Karyawan k : allKaryawan) {
    	    if (!penilaianDAO.getDetailByKaryawan(k.getId()).isEmpty()) {
    	        karyawans.add(k);
    	    }
    	}


        // Load all bobot
        Map<Integer, Double> bobotMap = new HashMap<>(); // id_kriteria -> bobot
        for (Bobot b : bobotDAO.findAll()) {
            bobotMap.put(b.getIdKriteria(), b.getNilaiBobot());
        }

        // Load all penilaian
        Map<Integer, List<DetailPenilaian>> penilaianMap = new HashMap<>();
        Map<Integer, List<Double>> kriteriaNilaiMap = new HashMap<>(); // For normalization

        for (Karyawan k : karyawans) {
            List<DetailPenilaian> details = penilaianDAO.getDetailByKaryawan(k.getId());
            penilaianMap.put(k.getId(), details);

            for (DetailPenilaian d : details) {
                kriteriaNilaiMap.computeIfAbsent(d.getIdKriteria(), key -> new ArrayList<>())
                                .add(d.getNilai());
            }
        }

        List<SAWResult> results = new ArrayList<>();

        for (Karyawan k : karyawans) {
            double total = 0;
            List<DetailPenilaian> details = penilaianMap.get(k.getId());

            if (details != null) {
                for (DetailPenilaian d : details) {
                    double normalized = normalize(d.getNilai(), d.getIdKriteria(), kriteriaNilaiMap, d.getTipe());
                    double weighted = normalized * bobotMap.getOrDefault(d.getIdKriteria(), 0.0);
                    total += weighted;
                }
            }

            results.add(new SAWResult(k.getNama(), total));
        }

        // Sort descending by nilai preferensi
        results.sort((a, b) -> Double.compare(b.getNilaiPreferensi(), a.getNilaiPreferensi()));

        // Assign rank
        int rank = 1;
        for (SAWResult r : results) {
            r.setPeringkat(rank++);
        }

        return results;
    }

    private double normalize(double nilai, int idKriteria, Map<Integer, List<Double>> kriteriaNilaiMap, String tipe) {
        List<Double> all = kriteriaNilaiMap.get(idKriteria);
        if (tipe.equalsIgnoreCase("Benefit")) {
            double max = Collections.max(all);
            if (max == 0) return 0; // avoid division by zero
            return nilai / max;
        } else { // Cost
            double min = Collections.min(all);
            if (nilai == 0) return 0; // avoid division by zero
            return min / nilai; // Correct formula for Cost criteria
        }
    }


}
