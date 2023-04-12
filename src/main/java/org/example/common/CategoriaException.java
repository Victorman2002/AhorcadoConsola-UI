package org.example.common;

import java.util.Arrays;

public class CategoriaException extends Exception {
    public CategoriaException(String categoria) {
        super("La categoria " + categoria + " no está permitida. Sólo son válidas:" + Arrays.toString(Categoria.values()));
    }
}
