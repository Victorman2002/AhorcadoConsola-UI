package org.example.ui;

import org.example.common.Constantes;
import org.example.dao.elemento.DaoElementosFicheros;
import org.example.domain.Elemento;
import org.example.service.GestionElementos;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuGestionDiccionario {

    private static final String adminPass = "2223";

    private int mostrarMenu() {
        Scanner lector = new Scanner(System.in);
        System.out.println(Constantes.MENU + "\n" + Constantes.OPCION1 + "\n" + Constantes.OPCION2
                + "\n" + Constantes.OPCION3 + "\n" + Constantes.OPCION4 + "\n" + Constantes.OPCION5
                + "\n" + Constantes.OPCION6);
        return lector.nextInt();
    }

    public void ejecucionAdmin() {

        String clave;
        String palabra;
        String nuevaPalabra;
        String categoria;
        Scanner sc = new Scanner(System.in);
        GestionElementos gestionElementos = new GestionElementos();

        boolean enEjecucion = true;

        System.out.println("introduce la clave del administrador");
        clave = sc.nextLine();

        if (clave.equalsIgnoreCase(adminPass)) {
            do {
                int opcion = mostrarMenu();
                switch (opcion) {
                    case 1 -> System.out.println(gestionElementos.listarElementos(true) + "\n");
                    case 2 -> {
                        System.out.println("Introduce la palabra");
                        palabra = sc.nextLine();
                        System.out.println("introduce la categoria");
                        categoria = sc.nextLine();
                        gestionElementos.insertarElemento(palabra, categoria);
                        DaoElementosFicheros.escribirElementosEnFichero((ArrayList<Elemento>) gestionElementos.getListaElementos());
                    }
                    case 3 -> {
                        System.out.println("Introduce la palabra");
                        palabra = sc.nextLine();
                        System.out.println("introduce la categoria");
                        categoria = sc.nextLine();
                        System.out.println(gestionElementos.modificarCategoria(palabra, categoria));
                        DaoElementosFicheros.escribirElementosEnFichero((ArrayList<Elemento>) gestionElementos.getListaElementos());
                    }
                    case 4 -> {
                        System.out.println("Introduce la palabra a modificar");
                        palabra = sc.nextLine();
                        System.out.println("introduce la nueva palabra");
                        nuevaPalabra = sc.nextLine();
                        System.out.println(gestionElementos.modificarIncognita(palabra, nuevaPalabra));
                        DaoElementosFicheros.escribirElementosEnFichero((ArrayList<Elemento>) gestionElementos.getListaElementos());
                    }
                    case 5 -> {
                        System.out.println("Introduce la palabra que quiere eliminar");
                        palabra = sc.nextLine();
                        gestionElementos.eliminarElemento(palabra);
                        DaoElementosFicheros.escribirElementosEnFichero((ArrayList<Elemento>) gestionElementos.getListaElementos());
                    }
                    case 6 -> enEjecucion = false;
                    default -> System.out.println("Elige una opcion valida");
                }
                //Guardar cambios del Administrador en el fichero
                DaoElementosFicheros.escribirElementosEnFichero((ArrayList<Elemento>) gestionElementos.getListaElementos());
            } while (enEjecucion);
        } else System.out.println("Clave incorrecta");

    }

}