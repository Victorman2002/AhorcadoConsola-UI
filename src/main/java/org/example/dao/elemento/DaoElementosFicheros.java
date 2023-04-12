package org.example.dao.elemento;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.example.common.CategoriaException;
import org.example.common.Constantes;
import org.example.domain.Elemento;

public class DaoElementosFicheros {

    public static final String FICHERO = "src/main/java/org/example/common/ficheros/elementos.txt";

    public static void crearFicheros() {
        File fichero1 = new File(FICHERO);
        if (!fichero1.exists()) {
            try {
                fichero1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Elemento> leerFichero() {
        return leerFichero(FICHERO);
    }

    public static List<Elemento> leerFichero(String fichero) {
        crearFicheros();
        ArrayList<Elemento> auxiliar = null;

        try {
            Scanner sc = new Scanner(new File(fichero));
            auxiliar = new ArrayList<>();
            while (sc.hasNextLine()) {
                String cadena = sc.nextLine();
                String[] trozos = cadena.split(";");
                //crear elemento y añadirlo a auxiliar.
                try {
                    auxiliar.add(new Elemento(trozos[1], trozos[2]));
                } catch (CategoriaException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(DaoElementosFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
        return auxiliar;
    }

    public static void escribirElementosEnFichero(ArrayList<Elemento> elementos) {
        crearFicheros();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(Constantes.FICHERO));
            for (Elemento elemento : elementos) {
                pw.println(elemento.toStringFichero());
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    public static List<Elemento> leerFicheroBinario() {
        List<Elemento> auxiliar = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(FICHEROB))) {
            auxiliar = (List<Elemento>) is.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DaoElementosFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
        return auxiliar;
    }

    public static boolean escribirFicheroBinario(List<Elemento> Elementos) {
        boolean escrito = false;
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FICHEROB))) {
            os.writeObject(Elementos);
            escrito = true;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(DaoElementosFicheros.class.getName()).log(java.util.logging.Level.SEVERE, ex.getMessage(), ex);
        }
        return escrito;
    }
*/

}
