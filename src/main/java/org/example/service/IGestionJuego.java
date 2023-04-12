package org.example.service;

import org.example.domain.Juego;

public interface IGestionJuego {

    void escribirJuegoFichero(Juego juego);
    Juego leerJuegoFichero();

}
