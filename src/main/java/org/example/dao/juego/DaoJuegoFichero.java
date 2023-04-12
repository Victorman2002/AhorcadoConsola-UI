package org.example.dao.juego;

import org.example.common.Constantes;
import org.example.dao.elemento.DaoElementosFicheros;
import org.example.domain.Juego;
import java.io.*;

public class DaoJuegoFichero {

    public Juego leerJuegoBinario() {
        Juego juego = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(Constantes.FICHEROB))) {
            juego = (Juego) is.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DaoElementosFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
        return juego;
    }

    public  boolean escribirJuegoBinario(Juego juego) {
        boolean escrito = false;
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(Constantes.FICHEROB))) {
            os.writeObject(juego);
            escrito = true;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DaoElementosFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
        return escrito;
    }

}
