package org.example.dao.elemento;

import java.util.List;

import org.example.common.CategoriaException;
import org.example.common.IncognitaException;
import org.example.domain.Elemento;

public interface InterfazDaoElementos {

    boolean isEmptyElementosList();

    boolean insertarElemento(Elemento elemento);

    boolean insertarElemento(String incognita, String categoria) throws CategoriaException, IncognitaException;

    List<Elemento> getElementosCategoria(String categoria);

    List<Elemento> getElementosNivelCategoria(int nivel, String categoria);

    List<Elemento> getElementosNivel(int nivel);

    List<Elemento> getElementos(boolean ascendente);

    boolean modificarCategoria(String categoria, String nuevaCategoria);

    boolean modificarIncognita(String categoria, String incognita);

	void eliminarElemento(String incognita);

}
