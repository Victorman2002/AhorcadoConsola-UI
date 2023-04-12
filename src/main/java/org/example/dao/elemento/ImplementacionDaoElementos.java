package org.example.dao.elemento;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.example.common.CategoriaException;
import org.example.common.Comprobacion;
import org.example.common.Constantes;
import org.example.common.IncognitaException;
import org.example.domain.Elemento;

public class ImplementacionDaoElementos implements InterfazDaoElementos {

    private DaoElementos daoElementos;

    public ImplementacionDaoElementos() {
        if (primeraEjecucion()) {
            daoElementos = new DaoElementos(true);
        } else {
            daoElementos = new DaoElementos();
        }
    }

    public static boolean primeraEjecucion() {
        File file = new File(Constantes.FICHERO);
        return !file.exists() || file.length() == 0;
    }

    @Override
    public boolean isEmptyElementosList() {
        return daoElementos.getListaElementos().isEmpty();
    }

    @Override
    public boolean insertarElemento(Elemento elemento) {
        return daoElementos.getListaElementos().add(elemento);
    }

    @Override
    public boolean insertarElemento(String incognita, String categoria) throws IncognitaException {
        Elemento elemento = null;
        try {
            Comprobacion.incognitaOk(incognita, daoElementos.getListaElementos());
            elemento = new Elemento(incognita, categoria);
        } catch (CategoriaException e) {
            System.err.println(e.getMessage());
        }
        if (elemento != null) {
            return daoElementos.getListaElementos().add(elemento);
        } else {
            return false;
        }
    }

    public List<Elemento> getListaDeElementos() {
        return daoElementos.getListaElementos();
    }

    @Override
    public List<Elemento> getElementosCategoria(String categoria) {
        List<Elemento> elementosAux = new ArrayList<>();
        for (int i = 0; i < daoElementos.getListaElementos().size(); i++) {
            Elemento elemento = daoElementos.getListaElementos().get(i);
            if (elemento.getCategoria().equalsIgnoreCase(categoria)) {
                elementosAux.add(elemento);
            }
        }
        return elementosAux;
    }

    @Override
    public List<Elemento> getElementosNivelCategoria(int nivel, String categoria) {
        List<Elemento> elementosAux = new ArrayList<>();
        for (int i = 0; i < daoElementos.getListaElementos().size(); i++) {
            Elemento elemento = daoElementos.getListaElementos().get(i);
            if (elemento.getCategoria().equalsIgnoreCase(categoria) && elemento.getLevel() == nivel) {
                elementosAux.add(elemento);
            }
        }
        return elementosAux;
    }

    @Override
    public List<Elemento> getElementosNivel(int nivel) {
        List<Elemento> elementosAux = new ArrayList<>();
        for (int i = 0; i < daoElementos.getListaElementos().size(); i++) {
            Elemento elemento = daoElementos.getListaElementos().get(i);
            if (elemento.getLevel() == nivel) {
                elementosAux.add(elemento);
            }
        }
        return elementosAux;
    }

    @Override
    public List<Elemento> getElementos(boolean ascendente) {
        return daoElementos.getListaElementos();
    }

    public List<Elemento> getElementosOrdenados(boolean ascendente) {
        List<Elemento> elementosAux = daoElementos.getListaElementos();
        if (ascendente) {
            Collections.sort(elementosAux);
        }
        return elementosAux;
    }

    @Override
    public boolean modificarCategoria(String incognita, String nuevaCategoria) {
        boolean modificado = false;
        for (int i = 0; i < daoElementos.getListaElementos().size(); i++) {
            if (daoElementos.getListaElementos().get(i).equals(new Elemento(incognita))) {
                try {
                    daoElementos.getListaElementos().get(i).setCategoria(nuevaCategoria);
                    modificado = true;
                } catch (CategoriaException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return modificado;
    }

    public boolean modificarIncognita(String incognita, String nuevaIncognita) {
        boolean modificado = false;
        for (int i = 0; i < daoElementos.getListaElementos().size(); i++) {
            if (daoElementos.getListaElementos().get(i).equals(new Elemento(incognita))) {

                try {
                    daoElementos.getListaElementos().get(i).setIncognita(nuevaIncognita);
                    modificado = true;
                } catch (IncognitaException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return modificado;
    }

    @Override
    public void eliminarElemento(String incognita) {

        for (int i = 0; i < daoElementos.getListaElementos().size(); i++) {
            if (daoElementos.getListaElementos().get(i).equals(new Elemento(incognita))) {
                daoElementos.getListaElementos().remove(i);
                return;
            }
        }
        System.err.println("Elemento no encontrado");
    }

}
