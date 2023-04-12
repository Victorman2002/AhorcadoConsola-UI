package org.example.common;

import java.util.List;

import org.example.domain.Elemento;

public class Comprobacion {


    public static void categoriaOk(String categoria) throws CategoriaException {
        boolean esta = false;
        Categoria[] aux = Categoria.values();
        for (int i = 0; i < aux.length && !esta; i++) {
            if (aux[i].toString().equalsIgnoreCase(categoria)) {
                esta = true;
            }
        }
        if (!esta) {
            throw new CategoriaException(categoria);
        }
    }

    public static void incognitaOk(String incognita, List<Elemento> elementos) throws IncognitaException {

        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i).getIncognita().equalsIgnoreCase(incognita)) {
                throw new IncognitaException(incognita);
            }
        }
    }

    public static void letraOk(char letra) throws LetraException {

        if((letra>='A'&& letra<='Z')||(letra>='a'&& letra<='z')){

        }else{
            throw new LetraException(letra);
        }

    }

}
