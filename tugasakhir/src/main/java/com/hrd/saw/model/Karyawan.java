package com.hrd.saw.model;

public class Karyawan {
    private int id;
    private String nama;
    private String jabatan;
    private String departemen;
    private String status;

    public Karyawan() { }

    public Karyawan(String nama, String jabatan, String departemen, String status) {
        this.nama = nama;
        this.jabatan = jabatan;
        this.departemen = departemen;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }
    public String getDepartemen() { return departemen; }
    public void setDepartemen(String departemen) { this.departemen = departemen; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
