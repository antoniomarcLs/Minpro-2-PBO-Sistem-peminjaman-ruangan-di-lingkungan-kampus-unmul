package com.mycompany.peminjamanruangan.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Services {

    private final ArrayList<Peminjaman> daftarPeminjaman;
    private final Scanner scanner;

    public Services(Scanner scanner) {
        this.daftarPeminjaman = new ArrayList<>();
        this.scanner = scanner;

        Dummy();
    }

    private void Dummy() {

        MeetingRoom meetingRoom = new MeetingRoom(
                201,
                "Meeting Room A",
                15,
                false,
                "Proyektor dan Smart TV"
        );

        Jadwal jadwal = new Jadwal(
                "2026-09-20",
                "09:00",
                "11:00"
        );

        Peminjaman dummy = new Peminjaman(
                "PT Contoh Indonesia",
                "Rapat Evaluasi Bulanan",
                1001,
                meetingRoom,
                jadwal
        );

        daftarPeminjaman.add(dummy);
    }

    // =========================================================
    // Menu pengajuan surat
    // =========================================================

    public void ajukanSurat() {

        System.out.println("\n=== PENGAJUAN SURAT PEMINJAMAN ===");

        String namaPihak = bacaString(
                "Nama pihak peminjam      : "
        );

        int idRuangan;

        while (true) {

            idRuangan = bacaInt(
                    "ID ruangan          (INT): "
            );

            if (idRuangan <= 0) {
                System.out.println(
                        ">> ID ruangan harus lebih dari 0."
                );
                continue;
            }

            if (idRuanganSudahDigunakan(idRuangan)) {
                System.out.println(
                        ">> ID ruangan tersebut sudah digunakan."
                );
                continue;
            }

            break;
        }

        String namaRuangan = bacaString(
                "Nama ruangan             : "
        );

        int kapasitas;

        while (true) {

            kapasitas = bacaInt(
                    "Kapasitas ruangan   (INT): "
            );

            if (kapasitas <= 0) {
                System.out.println(
                        ">> Kapasitas harus lebih dari 0."
                );
            } else {
                break;
            }
        }

        String tujuan = bacaString(
                "Tujuan peminjaman        : "
        );

        int nomorSurat;

        while (true) {

            nomorSurat = bacaInt(
                    "Nomor Surat         (INT): "
            );

            if (nomorSurat <= 0) {
                System.out.println(
                        ">> Nomor surat harus lebih dari 0."
                );
                continue;
            }

            if (nomorSuratSudahDigunakan(nomorSurat)) {
                System.out.println(
                        ">> Nomor surat tersebut sudah digunakan."
                );
                continue;
            }

            break;
        }

        String tanggal = bacaTanggal(
                "Tanggal Peminjaman       : "
        );

        String jamMulai = bacaJam(
                "Jam Mulai                : "
        );
        String jamSelesai;
        
        while (true) {

            jamSelesai = bacaJam(
                    "Jam Selesai              : "
            );

            LocalTime waktuMulai = LocalTime.parse(
                    jamMulai,
                    DateTimeFormatter.ofPattern("HH:mm")
            );

            LocalTime waktuSelesai = LocalTime.parse(
                    jamSelesai,
                    DateTimeFormatter.ofPattern("HH:mm")
            );
            if (!waktuSelesai.isAfter(waktuMulai)) {
                System.out.println(
                        ">> Jam selesai harus setelah jam mulai."
                );
            } else {
                break;
            }
        }
        Ruangan ruanganBaru = new Ruangan(
                idRuangan,
                namaRuangan,
                kapasitas,
                false
        );
        Jadwal jadwalBaru = new Jadwal(
                tanggal,
                jamMulai,
                jamSelesai
        );
        Peminjaman peminjamanBaru = new Peminjaman(
                namaPihak,
                tujuan,
                nomorSurat,
                ruanganBaru,
                jadwalBaru
        );

        daftarPeminjaman.add(peminjamanBaru);
        System.out.println("\n>> SURAT BERHASIL DIAJUKAN!");
        System.out.println("\nTekan ENTER untuk kembali...");
        scanner.nextLine();
    }

    // =========================================================
    // Menu tampilkan status
    // =========================================================

    public void tampilkanStatus() {

        System.out.println("\n=== STATUS PEMINJAMAN ===");

        for (Peminjaman p : daftarPeminjaman) {

            System.out.println(
                    "\n--------------------------------"
            );

            System.out.println(
                    "Nomor Surat  : "
                    + p.getNomorSurat()
            );

            System.out.println(
                    "Nama Pihak   : "
                    + p.getNamaPihak()
            );

            System.out.println(
                    "Nama Ruangan : "
                    + p.getRuangan().getNamaRuangan()
            );

            System.out.println(
                    "Kapasitas    : "
                    + p.getRuangan().getKapasitas()
            );

            System.out.println(
                    "Tujuan       : "
                    + p.getTujuan()
            );

            System.out.println(
                    "Tanggal      : "
                    + p.getJadwal().getTanggal()
            );

            System.out.println(
                    "Waktu        : "
                    + p.getJadwal().getJamMulai()
                    + " - "
                    + p.getJadwal().getJamSelesai()
            );

            System.out.println(
                    "Status       : "
                    + (p.getRuangan().isTersedia()
                            ? "Tersedia"
                            : "Sedang Dipinjam")
            );
        }

        System.out.println(
                "\nTekan ENTER untuk kembali..."
        );

        scanner.nextLine();
    }

    // =========================================================
    // Menu hapus surat
    // =========================================================

    public void hapusSurat() {

        System.out.println("\n=== HAPUS PEMINJAMAN ===");

        int nomorTarget = bacaInt(
                "Masukkan Nomor Surat: "
        );

        Peminjaman peminjamanDitemukan = cariPeminjaman(
                nomorTarget
        );

        if (peminjamanDitemukan != null) {

            String konfirmasi = bacaKonfirmasi(
                    "\nYakin ingin menghapus surat ini? (Y/N): "
            );

            if (konfirmasi.equalsIgnoreCase("Y")) {

                daftarPeminjaman.remove(
                        peminjamanDitemukan
                );

                System.out.println(
                        ">> Surat peminjaman berhasil dihapus!"
                );

            } else {

                System.out.println(
                        ">> Penghapusan dibatalkan."
                );
            }

        } else {

            System.out.println(
                    ">> Nomor surat tidak ditemukan."
            );
        }

        System.out.println(
                "\nTekan ENTER untuk kembali..."
        );

        scanner.nextLine();
    }

    // =========================================================
    // Menu ganti ruangan
    // =========================================================

    public void gantiRuangan() {

        System.out.println("\n=== GANTI RUANGAN ===");

        int nomorTarget = bacaInt(
                "Masukkan Nomor Surat: "
        );

        Peminjaman peminjamanDitemukan = cariPeminjaman(
                nomorTarget
        );

        if (peminjamanDitemukan != null) {

            System.out.println(
                    "\nRuangan saat ini : "
                    + peminjamanDitemukan
                            .getRuangan()
                            .getNamaRuangan()
            );

            String konfirmasi = bacaKonfirmasi(
                    "Apakah ingin mengganti ruangan? (Y/N): "
            );

            if (konfirmasi.equalsIgnoreCase("Y")) {

                int idBaru;

                while (true) {

                    idBaru = bacaInt(
                            "Masukkan ID Ruangan Baru: "
                    );

                    if (idBaru <= 0) {

                        System.out.println(
                                ">> ID ruangan harus lebih dari 0."
                        );

                        continue;
                    }

                    if (idBaruSudahDipakaiOlehPeminjamanLain(
                            idBaru,
                            peminjamanDitemukan)) {

                        System.out.println(
                                ">> ID ruangan tersebut sudah digunakan."
                        );

                        continue;
                    }

                    break;
                }

                String namaBaru = bacaString(
                        "Masukkan Nama Ruangan Baru: "
                );

                int kapasitasBaru;

                while (true) {

                    kapasitasBaru = bacaInt(
                            "Masukkan Kapasitas Ruangan Baru: "
                    );

                    if (kapasitasBaru <= 0) {

                        System.out.println(
                                ">> Kapasitas harus lebih dari 0."
                        );

                    } else {
                        break;
                    }
                }

                Ruangan ruanganBaru = new Ruangan(
                        idBaru,
                        namaBaru,
                        kapasitasBaru,
                        false
                );

                peminjamanDitemukan.setRuangan(
                        ruanganBaru
                );

                System.out.println(
                        ">> Ruangan berhasil diganti!"
                );

                System.out.println(
                        "Ruangan baru : "
                        + peminjamanDitemukan
                                .getRuangan()
                                .getNamaRuangan()
                );

            } else {

                System.out.println(
                        ">> Penggantian ruangan dibatalkan."
                );
            }

        } else {

            System.out.println(
                    ">> Nomor surat tidak ditemukan."
            );
        }

        System.out.println(
                "\nTekan ENTER untuk kembali..."
        );

        scanner.nextLine();
    }

    // =========================================================
    // Validasi input(prevent error)
    // =========================================================

    private String bacaString(String pesan) {

        while (true) {

            System.out.print(pesan);

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {

                System.out.println(
                        ">> Input tidak boleh kosong."
                );

            } else {
                return input;
            }
        }
    }

    private int bacaInt(String pesan) {

        while (true) {

            System.out.print(pesan);

            String input = scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        ">> Input harus berupa angka."
                );
            }
        }
    }

    private String bacaTanggal(String pesan) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {

            System.out.print(pesan);

            String input = scanner.nextLine().trim();

            try {

                LocalDate.parse(input, formatter);

                return input;

            } catch (DateTimeParseException e) {

                System.out.println(
                        ">> Format tanggal salah."
                        + " Gunakan YYYY-MM-DD."
                );
            }
        }
    }

    private String bacaJam(String pesan) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm");

        while (true) {

            System.out.print(pesan);

            String input = scanner.nextLine().trim();

            try {

                LocalTime.parse(input, formatter);

                return input;

            } catch (DateTimeParseException e) {

                System.out.println(
                        ">> Format jam salah."
                        + " Gunakan HH:mm."
                );
            }
        }
    }

    private String bacaKonfirmasi(String pesan) {

        while (true) {

            System.out.print(pesan);

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")
                    || input.equalsIgnoreCase("N")) {

                return input;
            }

            System.out.println(
                    ">> Masukkan hanya Y atau N."
            );
        }
    }

    // =========================================================
    // Validasi data
    // =========================================================

    private boolean nomorSuratSudahDigunakan(
            int nomorSurat) {

        for (Peminjaman p : daftarPeminjaman) {

            if (p.getNomorSurat() == nomorSurat) {
                return true;
            }
        }

        return false;
    }

    private boolean idRuanganSudahDigunakan(
            int idRuangan) {

        for (Peminjaman p : daftarPeminjaman) {

            if (p.getRuangan().getIdRuangan() == idRuangan) {
                return true;
            }
        }

        return false;
    }

    private boolean idBaruSudahDipakaiOlehPeminjamanLain(
            int idRuangan,
            Peminjaman peminjamanSekarang) {

        for (Peminjaman p : daftarPeminjaman) {

            if (p != peminjamanSekarang
                    && p.getRuangan().getIdRuangan()
                    == idRuangan) {

                return true;
            }
        }

        return false;
    }

    private Peminjaman cariPeminjaman(
            int nomorSurat) {

        for (Peminjaman p : daftarPeminjaman) {

            if (p.getNomorSurat() == nomorSurat) {
                return p;
            }
        }

        return null;
    }

    // =========================================================
    // Validasi menu
    // =========================================================

    public int bacaPilihanMenu() {

        while (true) {

            int pilihan = bacaInt(
                    "Pilih menu (1-5): "
            );

            if (pilihan >= 1 && pilihan <= 5) {
                return pilihan;
            }

            System.out.println(
                    ">> Pilihan menu harus antara 1 sampai 5."
            );
        }
    }
}