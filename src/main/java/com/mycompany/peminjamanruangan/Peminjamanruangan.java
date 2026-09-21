package com.mycompany.peminjamanruangan;

import com.mycompany.peminjamanruangan.model.Services;
import java.util.Scanner;

public class Peminjamanruangan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Services services = new Services(scanner);
        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n=== SISTEM PENGAJUAN SURAT PEMINJAMAN RUANGAN ===");
            System.out.println("1. Pengajuan Surat");
            System.out.println("2. Cek Status");
            System.out.println("3. Hapus Peminjaman");
            System.out.println("4. Ganti Ruangan");
            System.out.println("5. Keluar");

            int pilihan = services.bacaPilihanMenu();
            switch (pilihan) {
                case 1 -> services.ajukanSurat();
                case 2 -> services.tampilkanStatus();
                case 3 -> services.hapusSurat();
                case 4 -> services.gantiRuangan();
                case 5 -> {
                    berjalan = false;
                    System.out.println("\n>> Sistem telah dihentikan.");
                }
            }
        }
        scanner.close();
    }
}