package org.example.ui;

import org.example.common.Comprobacion;
import org.example.common.LetraException;
import org.example.dao.juego.ImplementacionDaoJuegoFichero;
import org.example.domain.Juego;

import java.util.Scanner;

public class MenuJuego {

    ImplementacionDaoJuegoFichero implementacionJuego = new ImplementacionDaoJuegoFichero();

    public void jugar(boolean isPartidaAntigua, int nivel, String categoria) throws LetraException {

        Scanner sc = new Scanner(System.in);
        Juego juego;
        boolean finPartida = false;

        if (isPartidaAntigua) {
            juego = implementacionJuego.leerJuegoFichero();
        } else {
            juego = new Juego(nivel, categoria);
        }

        juego.calcularLetrasDescubiertas('*');

        System.out.println("\n" + juego.getElementoAdivinar().getIncognita());
        System.out.println("La categoria es: " + juego.getElementoAdivinar().getCategoria());
        System.out.println("\nLa palabra es:");
        System.out.println(juego.getLetrasDescubiertas());

        do {
            System.out.println("Intentos restantes: " + juego.getIntentos() + "\n");
            System.out.println("1-Probar letra");
            System.out.println("2-Probar palabra");
            System.out.println("3-Salir");
            int opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> {
                    System.out.println("Introduce la letra");

                    try {
                        char letra = sc.next().charAt(0);
                        Comprobacion.letraOk(letra);
                        juego.calcularLetrasDescubiertas(letra);
                        System.out.println(juego.getLetrasDescubiertas());
                        if (!juego.comprobrarSiLaLetraEsta(letra)) {
                            juego.setIntentos(juego.getIntentos() - 1);
                            System.out.println("La letra " + letra + " no está en la palabra\n");
                        }
                    } catch (LetraException e) {
                        System.err.println(e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.println("Introduce la palabra");
                    sc.nextLine();
                    String palabra = sc.nextLine();
                    if (juego.comprobarPalabraAcertada(palabra)) {
                        System.out.println("Acertaste la palabra!!! FIN DEL JUEGO\n");
                    } else {
                        System.out.println("Esa no era la palabra...La palabra era "
                                + juego.getElementoAdivinar().getIncognita() + ". FIN DEL JUEGO\n");
                    }
                    finPartida = true;
                }

                case 3 -> finPartida = true;

                default -> System.out.println("Elige una opcion valida");

            }

            //Guardar el estado del juego cada vez que se elija una opcion
            implementacionJuego.escribirJuegoFichero(juego);

            if (!(juego.getIntentos() > 0)) {
                System.out.println("Has perdido por falta de intentos");
            }

        } while (!finPartida && juego.getIntentos() > 0);

    }


}
