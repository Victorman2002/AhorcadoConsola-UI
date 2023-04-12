package org.example;

import org.example.ui.MainConsola;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        do {
            System.out.println("1-Consola" + "\n" + "2-Grafico" + "\n" + "3-Salir");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1 -> MainConsola.main(args);
                case 2 -> MainUI.main(args);
                case 3 -> salir = true;
                default -> System.out.println("Introduce una opcion valida");
            }
        } while (!salir);

    }
}
