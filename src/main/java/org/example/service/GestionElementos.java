package org.example.service;

import java.io.IOException;
import java.util.List;
import org.example.common.Comprobacion;
import org.example.common.IncognitaException;
import org.example.dao.elemento.ImplementacionDaoElementos;
import org.example.domain.Elemento;

public class GestionElementos implements IGestionElementos {

    private final ImplementacionDaoElementos implementacionDaoElementos;

    public GestionElementos() {
        this.implementacionDaoElementos = new ImplementacionDaoElementos();
    }

    @Override
    public boolean isEmptyElementosList() {
        return implementacionDaoElementos.isEmptyElementosList();
    }

    @Override
    public List<Elemento> getListaElementos() {
        return implementacionDaoElementos.getListaDeElementos();
    }

    @Override
    public boolean insertarElemento(Elemento Elemento) {
        return false;
    }

    @Override
    public boolean insertarElemento(String incognita, String categoria) {
        try {
            return implementacionDaoElementos.insertarElemento(incognita, categoria);
        } catch (IncognitaException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Elemento> listar(String categoria) {
        return null;
    }

    @Override
    public List<Elemento> listar(int nivel, String categoria) {
        return null;
    }

    @Override
    public List<Elemento> listar(int nivel) {
        return null;
    }

    @Override
    public List<Elemento> listarElementos(boolean ascendente) {
        return implementacionDaoElementos.getElementosOrdenados(ascendente);
    }

    @Override
    public boolean modificarCategoria(String incognita, String nuevaCategoria) {
        return implementacionDaoElementos.modificarCategoria(incognita, nuevaCategoria);
    }

    @Override
    public boolean modificarIncognita(String incognita, String nuevaIncognita) {
        try {
            Comprobacion.incognitaOk(incognita, implementacionDaoElementos.getListaDeElementos());
        } catch (IncognitaException e) {
            System.err.println(e.getMessage());
        }
        return implementacionDaoElementos.modificarIncognita(incognita, nuevaIncognita);
    }

    @Override
    public List<Elemento> getListaElementosCategoria(String categoria) {
        return implementacionDaoElementos.getElementosCategoria(categoria);
    }

    @Override
    public void crearFicheros() throws IOException {

    }

    @Override
    public boolean cargarFichero() throws IOException {
        return false;
    }

    @Override
    public boolean escribirFichero() {
        return false;
    }

    @Override
    public boolean escribirFicheroBinario() {
        return false;
    }

    @Override
    public boolean cargarFicheroBinario() {
        return false;
    }

    @Override
    public void eliminarElemento(String incognita) {
        implementacionDaoElementos.eliminarElemento(incognita);

    }

}