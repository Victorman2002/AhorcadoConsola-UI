package org.example.service;

import org.example.dao.juego.ImplementacionDaoJuegoFichero;
import org.example.domain.Juego;

public class GestionJuego implements IGestionJuego {

    private final ImplementacionDaoJuegoFichero implementacionDaoJuegoFichero;

    public GestionJuego() {
        this.implementacionDaoJuegoFichero = new ImplementacionDaoJuegoFichero();
    }


    @Override
    public void escribirJuegoFichero(Juego juego) {
        implementacionDaoJuegoFichero.escribirJuegoFichero(juego);
    }

    @Override
    public Juego leerJuegoFichero() {
        return implementacionDaoJuegoFichero.leerJuegoFichero();
    }
}
