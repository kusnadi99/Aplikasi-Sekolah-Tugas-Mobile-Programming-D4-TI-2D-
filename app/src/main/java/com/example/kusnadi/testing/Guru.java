package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/23/2016.
 */
public class Guru {
    private long guru_id;
    private String nama_guru;
    private String alamat;
    private String umur;
    private String status;

    public Guru() {

    }
    public long getGuru_id() {
        return guru_id;
    }
    public void setGuru_id(long guru_id) {
        this.guru_id = guru_id;
    }
    public String getNama_guru() {
        return nama_guru;
    }
    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getUmur(){
        return umur;
    }
    public void setUmur(String umur) {
        this.umur = umur;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "Nama "+ nama_guru + "Alamat " + alamat + "Umur " + umur + "Status " + status;
    }
}
