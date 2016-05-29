package com.example.kusnadi.testing;

/**
 * Created by Kusnadi on 5/24/2016.
 */
public class Peralatan {
    private long peralatan_id;
    private String nama_alat;
    private String stok;

    public Peralatan() {

    }

    public long getPeralatan_id(){
        return peralatan_id;
    }
    public void setPeralatan_id(long perlatan_id) {
        this.peralatan_id = perlatan_id;
    }

    public String getNama_alat() {
        return nama_alat;
    }
    public void setNama_alat(String nama_alat) {
        this.nama_alat = nama_alat;
    }
    public String getStok() {
        return stok;
    }
    public void setStok(String stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "Peralatan " + nama_alat + " " + stok;
    }


}
