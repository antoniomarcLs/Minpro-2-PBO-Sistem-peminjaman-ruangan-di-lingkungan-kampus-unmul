package com.mycompany.peminjamanruangan.model;

public class Peminjaman {

    private String namaPihak;
    private String tujuan;
    private int nomorSurat;
    private Ruangan ruangan;
    private Jadwal jadwal;

    public Peminjaman(
            String namaPihak,
            String tujuan,
            int nomorSurat,
            Ruangan ruangan,
            Jadwal jadwal) {

        this.namaPihak = namaPihak;
        this.tujuan = tujuan;
        this.nomorSurat = nomorSurat;
        this.ruangan = ruangan;
        this.jadwal = jadwal;
    }

    public String getNamaPihak() {
        return namaPihak;
    }

    public String getTujuan() {
        return tujuan;
    }

    public int getNomorSurat() {
        return nomorSurat;
    }

    public Ruangan getRuangan() {
        return ruangan;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public void setNamaPihak(String namaPihak) {
        this.namaPihak = namaPihak;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public void setNomorSurat(int nomorSurat) {
        this.nomorSurat = nomorSurat;
    }

    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }

    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }
}