package org.example.dao.juego;

import org.example.domain.Juego;

public interface InterfazDaoJuegoFichero {

    boolean primeraEjecucion();
    void escribirJuegoFichero(Juego juego);
    Juego leerJuegoFichero();

}
