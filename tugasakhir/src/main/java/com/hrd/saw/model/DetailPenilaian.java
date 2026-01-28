package com.hrd.saw.model;

public class DetailPenilaian {

    private int idDetail;      
    private int idPenilaian;
    private int idKriteria;
    private double nilai;
    private String tipe; 

    public int getIdDetail() {
        return idDetail;
    }
    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }
    
    public int getIdPenilaian() { return idPenilaian; }
    public void setIdPenilaian(int idPenilaian) { this.idPenilaian = idPenilaian; }

    public int getIdKriteria() { return idKriteria; }
    public void setIdKriteria(int idKriteria) { this.idKriteria = idKriteria; }

    public double getNilai() { return nilai; }
    public void setNilai(double nilai) { this.nilai = nilai; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }
}
