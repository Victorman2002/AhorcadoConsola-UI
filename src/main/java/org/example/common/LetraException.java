package org.example.common;

public class LetraException extends Exception {

    public LetraException(char letra) {
        super("el caracter "+letra+ " no es una letra");
    }

}
