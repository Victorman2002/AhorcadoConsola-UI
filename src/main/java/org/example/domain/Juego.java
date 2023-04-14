package org.example.domain;

import javafx.scene.control.Button;
import org.example.dao.elemento.ImplementacionDaoElementos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego implements Serializable {

    private Elemento elementoAdivinar;
    private String letrasDescubiertas;
    private int intentos;
    private ArrayList<Boolean> botones = new ArrayList<>();

    public Juego(int nivel, String categoria) {
        this.elementoAdivinar = cogerElementoAleatorio(nivel, categoria);
        this.letrasDescubiertas = ("_".repeat(elementoAdivinar.getIncognita().length()));
        this.intentos = 6;
        for (int i = 0; i < 26; i++) {
            this.botones.add(false);
        }
    }

    public Elemento getElementoAdivinar() {
        return elementoAdivinar;
    }

    public void setElementoAdivinar(Elemento elementoAdivinar) {
        this.elementoAdivinar = elementoAdivinar;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public String getLetrasDescubiertas() {
        return letrasDescubiertas;
    }

    public void setLetrasDescubiertas(String letrasDescubiertas) {
        this.letrasDescubiertas = letrasDescubiertas;
    }

    public Elemento cogerElementoAleatorio(int nivel, String categoria) {
        ImplementacionDaoElementos i = new ImplementacionDaoElementos();
        Random r = new Random();
        List<Elemento> elementos = i.getElementosNivelCategoria(nivel, categoria);
        int indiceElemento = r.nextInt(elementos.size() - 1);
        return elementos.get(indiceElemento);
    }

    public boolean comprobrarSiLaLetraEsta(char letra) {
        char caracter = Character.toLowerCase(letra);
        String incognita = elementoAdivinar.getIncognita().toLowerCase();
        boolean esta = false;
        for (int i = 0; i < incognita.length(); i++) {
            if (incognita.charAt(i) == caracter) {
                esta = true;
                break;
            }
        }
        return esta;
    }

    public void calcularLetrasDescubiertas(char letra) {
        char caracter = Character.toLowerCase(letra);

        String incognita = elementoAdivinar.getIncognita().toLowerCase();
        StringBuilder letrasDescubiertas = new StringBuilder(this.letrasDescubiertas);

        for (int i = 0; i < incognita.length(); i++) {
            if (incognita.charAt(i) == caracter) {
                letrasDescubiertas.setCharAt(i, caracter);
            }
            //Poner espacio en la incognita si lo tiene
            if (incognita.charAt(i) == ' '){
                letrasDescubiertas.setCharAt(i, ' ');
            }
        }
        this.letrasDescubiertas = letrasDescubiertas.substring(0, 1).toUpperCase() + letrasDescubiertas.substring(1);
    }

    public boolean comprobarPalabraAcertada(String palabra) {
        return this.elementoAdivinar.getIncognita().equalsIgnoreCase(palabra);
    }

    public List<Boolean> getBotones() {
        return botones;
    }

    public void setBotones(ArrayList<Boolean> botones) {
        this.botones = botones;
    }
}