package org.example.dao.elemento;

import org.example.domain.Elemento;
import java.util.List;

public class ImplementacionDaoElementosFicheros implements InterfazDaoElementosFicheros {

    protected final DaoElementosFicheros daoElementosFicheros;

    public ImplementacionDaoElementosFicheros(DaoElementosFicheros daoElementosFicheros) {
        this.daoElementosFicheros = daoElementosFicheros;
    }

    @Override
    public List<Elemento> leerFichero() {
        return DaoElementosFicheros.leerFichero();
    }

    @Override
    public void escribirElementosEnFichero() {
        //DaoElementosFicheros.escribirElementosEnFichero();
    }

    @Override
    public List<Elemento> leerFicheroBinario() {
        return null;
    }

    @Override
    public List<Elemento> escribirFicheroBinario() {
        return null;
    }

}
