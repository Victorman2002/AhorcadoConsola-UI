package org.example.dao.elemento;

import java.util.ArrayList;
import java.util.List;
import org.example.common.CategoriaException;
import org.example.common.IncognitaException;
import org.example.domain.Elemento;
import com.github.javafaker.*;

public class DaoElementos {

    private ArrayList<Elemento> elementos = new ArrayList<>();

    public DaoElementos(boolean primeraEjecucion) {
        try {
            //Si es la primera ejecucion el Array se rellena con el Faker y lo mete en el fichero
            DaoElementosFicheros.crearFicheros();
            dameListaSinRepetir(60);
            DaoElementosFicheros.escribirElementosEnFichero(elementos);
        } catch (CategoriaException | IncognitaException e) {
            throw new RuntimeException(e);
        }
    }

    public DaoElementos(){
        //Si no es la primera ejecucion el Array se rellena con el Fichero
        elementos = (ArrayList<Elemento>) DaoElementosFicheros.leerFichero();
    }

    public List<Elemento> getListaElementos() {
        return elementos;
    }

    public void setListaElementos(List<Elemento> elementos) {
        this.elementos.clear();
        this.elementos.addAll(elementos);
    }


    public void dameListaSinRepetir(int numeroDeElementos) throws CategoriaException, IncognitaException {
        Faker faker = new Faker();

        for (int i = 0; i < numeroDeElementos; i++) {
            String pokemon = faker.pokemon().name();
            if (!elementos.contains(new Elemento(pokemon))) {
                elementos.add(new Elemento(pokemon, "pokemon"));
            }
        }

        for (int i = 0; i < numeroDeElementos; i++) {
            String country = faker.country().name();
            if (!elementos.contains(new Elemento(country))) {
                elementos.add(new Elemento(country, "country"));
            }
        }

        for (int i = 0; i < numeroDeElementos; i++) {
            String hero = faker.overwatch().hero();
            if (!elementos.contains(new Elemento(hero))) {
                elementos.add(new Elemento(hero, "overwatch"));
            }
        }

    }

}

