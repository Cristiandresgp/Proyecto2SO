/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

/**
 * Clase que representa un bloque de almacenamiento en la SD.
 * Cada bloque tiene un número de índice y puede estar enlazado a otro bloque.
 * @author Cristian
 */
public class Bloque {
    private int indice;
    private int siguienteBloque; // -1 si no hay más bloques enlazados

    /**
     * Constructor de la clase Bloque.
     * @param indice Índice del bloque en la SD
     */
    public Bloque(int indice) {
        this.indice = indice;
        this.siguienteBloque = -1;
    }

    // Getters y Setters
    public int getIndice() {
        return indice;
    }

    public int getSiguienteBloque() {
        return siguienteBloque;
    }

    public void setSiguienteBloque(int siguienteBloque) {
        this.siguienteBloque = siguienteBloque;
    }

    @Override
    public String toString() {
        return "Bloque: " + indice + " | Siguiente: " + (siguienteBloque == -1 ? "N/A" : siguienteBloque);
    }
}

