package org.example.dao.elemento;

import org.example.domain.Elemento;

import java.io.IOException;
import java.util.List;

public interface InterfazDaoElementosFicheros {

    List<Elemento> leerFichero() throws IOException;

    void escribirElementosEnFichero();

    List<Elemento> leerFicheroBinario();

    List<Elemento> escribirFicheroBinario();

}
