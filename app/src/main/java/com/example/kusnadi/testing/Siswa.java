package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/24/2016.
 */
public class Siswa {
    private long siswa_id;
    private String nama_siswa;
    private String kelas;

    public Siswa() {

    }

    public long getSiswa_id(){
        return siswa_id;
    }
    public void setSiswa_id(long siswa_id) {
        this.siswa_id = siswa_id;
    }
    public String getNama_siswa() {
        return nama_siswa;
    }
    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }
    public String getKelas() {
        return kelas;
    }
    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    @Override
    public String toString() {
        return "Siswa " + nama_siswa + " " + kelas;
    }
}
