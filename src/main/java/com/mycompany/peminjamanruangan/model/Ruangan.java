package com.mycompany.peminjamanruangan.model;

public class Ruangan {

    private int idRuangan;
    private String namaRuangan;
    private int kapasitas;
    private boolean tersedia;

    public Ruangan(
            int idRuangan,
            String namaRuangan,
            int kapasitas,
            boolean tersedia) {

        this.idRuangan = idRuangan;
        this.namaRuangan = namaRuangan;
        this.kapasitas = kapasitas;
        this.tersedia = tersedia;
    }

    public int getIdRuangan() {
        return idRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setIdRuangan(int idRuangan) {
        this.idRuangan = idRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }
}