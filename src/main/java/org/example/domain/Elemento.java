package org.example.domain;

import java.io.Serializable;
import java.util.Objects;
import org.example.common.CategoriaException;
import org.example.common.Comprobacion;
import org.example.common.IncognitaException;

public class Elemento implements Comparable<Elemento>, Serializable {

    private int level;
    private String incognita;
    private String categoria;

    public Elemento(String incognita, String categoria) throws CategoriaException {
        this.level = calcularNivel(incognita);
        this.incognita = incognita;
        Comprobacion.categoriaOk(categoria);
        this.categoria = categoria;
    }

    public Elemento(String incognita) {
        this.incognita = incognita;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) throws CategoriaException {
        Comprobacion.categoriaOk(categoria);
        this.categoria = categoria;
    }

    public int getLevel() {
        return level;
    }

    public void setIncognita(String incognita) throws IncognitaException {
        this.incognita = incognita;
    }

    public String getIncognita() {
        return incognita;
    }

    public static int calcularNivel(String incognita) {
        int nivel;
        int size;
        size = incognita.length();
        if (size >= 2 && size <= 4) {
            nivel = 1;
        } else if (size >= 5 && size <= 7) {
            nivel = 2;
        } else if (size >= 8 && size <= 9) {
            nivel = 3;
        } else if (size >= 10 && size <= 12) {
            nivel = 4;
        } else {
            nivel = 5;
        }
        return nivel;
    }

    @Override
    public String toString() {
        return "{" + incognita + ";" + categoria + "}";
    }

    public String toStringFichero() {
        return level + ";" + incognita + ";" + categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Elemento elemento = (Elemento) o;
        return incognita.equalsIgnoreCase(elemento.incognita);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incognita);
    }

    @Override
    public int compareTo(Elemento elemento) {
        return incognita.compareTo(elemento.getIncognita());
    }

}
