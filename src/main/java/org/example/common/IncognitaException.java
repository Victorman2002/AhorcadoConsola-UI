package org.example.common;

public class IncognitaException extends Exception{
    public IncognitaException(String incognita){
        super("La palabra "+incognita+" ya existe en el diccionario");
    }
}
