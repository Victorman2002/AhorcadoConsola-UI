package org.example.ui;

import org.example.common.LetraException;

import java.util.Scanner;

public class MenuPrincipal {

    public static void menuPrincipal()  {

        MenuJuego menuJuego = new MenuJuego();
        MenuGestionDiccionario menuGestionDiccionario = new MenuGestionDiccionario();
        Scanner sc = new Scanner(System.in);
        boolean salirDelPrograma = false;

        do {

            System.out.println("\n------------------");
            System.out.println("   AHORCADO");
            System.out.println("------------------\n");
            System.out.println("1- Jugar Partida Anterior");
            System.out.println("2- Jugar Partida Nueva");
            System.out.println("3- Administrador");
            System.out.println("4- Salir");

                int opcion = sc.nextInt();
                sc.nextLine();


                switch (opcion) {
                    case 1 -> {
                        try {
                            menuJuego.jugar(true, 0, "");
                        } catch (LetraException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    case 2 -> {

                        System.out.println("De que nivel quieres la palabra?");
                        int nivel = sc.nextInt();
                        sc.nextLine();
                        System.out.println("De que categoria quieres la palabra?");
                        String categoria = sc.nextLine();

                        try {
                            menuJuego.jugar(false, nivel, categoria);
                        } catch (LetraException e) {
                            System.err.println(e.getMessage());
                        }

                    }
                    case 3 -> menuGestionDiccionario.ejecucionAdmin();
                    case 4 -> salirDelPrograma = true;
                    default -> System.out.println("Introduce una opcion valida");
                }

        } while (!salirDelPrograma);
        System.out.println("Has salido del programa");

    }

}
