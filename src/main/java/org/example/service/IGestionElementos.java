package org.example.service;

import java.io.IOException;
import java.util.List;

import org.example.common.CategoriaException;
import org.example.domain.Elemento;

public interface IGestionElementos {
    boolean isEmptyElementosList();

    List<Elemento> getListaElementos();

    boolean insertarElemento(Elemento Elemento);

    boolean insertarElemento( String incognita, String categoria) throws CategoriaException;

    List<Elemento> listar(String categoria);

    List<Elemento> listar(int nivel, String categoria);

    List<Elemento> listar(int nivel);

    List<Elemento> listarElementos(boolean ascendente);

    boolean modificarCategoria(String incognita, String nuevaCategoria) throws CategoriaException;

    boolean modificarIncognita(String incognita, String nuevaIncognita);

    List<Elemento> getListaElementosCategoria(String categoria);

    void eliminarElemento(String incognita);

    void crearFicheros() throws IOException;

    boolean cargarFichero() throws IOException;

    boolean escribirFichero();

    boolean escribirFicheroBinario();

    boolean cargarFicheroBinario();

}
