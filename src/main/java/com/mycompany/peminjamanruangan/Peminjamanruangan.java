package com.mycompany.peminjamanruangan;

import com.mycompany.peminjamanruangan.controller.PeminjamanController;
import com.mycompany.peminjamanruangan.View.peminjamanView;
import java.util.Scanner;

public class Peminjamanruangan {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        peminjamanView view=new peminjamanView(scanner);
        PeminjamanController controller=new PeminjamanController(view);
        controller.jalankan();
        scanner.close();
    }
}