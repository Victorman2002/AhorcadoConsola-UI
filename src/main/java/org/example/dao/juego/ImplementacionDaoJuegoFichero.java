package org.example.dao.juego;

import org.example.common.Constantes;
import org.example.domain.Juego;

import java.io.File;

public class ImplementacionDaoJuegoFichero implements InterfazDaoJuegoFichero {

    DaoJuegoFichero daoJuegoFichero = new DaoJuegoFichero();

    @Override
    public boolean primeraEjecucion() {
        File file = new File(Constantes.FICHEROB);
        return !file.exists() || file.length() == 0;
    }

    @Override
    public void escribirJuegoFichero(Juego juego) {
        daoJuegoFichero.escribirJuegoBinario(juego);
    }

    @Override
    public Juego leerJuegoFichero() {
        return daoJuegoFichero.leerJuegoBinario();
    }

}
