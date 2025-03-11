/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

/**
 * Clase que representa un archivo en el sistema de archivos simulado.
 * @author Cristian
 */
public class Archivo {
    private String nombre;
    private int tamañoEnBloques;
    private int primerBloque; // Índice del primer bloque en la SD

    /**
     * Constructor de la clase Archivo.
     * @param nombre Nombre del archivo
     * @param tamañoEnBloques Tamaño del archivo en bloques
     * @param primerBloque Índice del primer bloque asignado
     */
    public Archivo(String nombre, int tamañoEnBloques, int primerBloque) {
        this.nombre = nombre;
        this.tamañoEnBloques = tamañoEnBloques;
        this.primerBloque = primerBloque;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamañoEnBloques() {
        return tamañoEnBloques;
    }

    public void setTamañoEnBloques(int tamañoEnBloques) {
        this.tamañoEnBloques = tamañoEnBloques;
    }

    public int getPrimerBloque() {
        return primerBloque;
    }

    public void setPrimerBloque(int primerBloque) {
        this.primerBloque = primerBloque;
    }

    @Override
    public String toString() {
        return "Archivo: " + nombre + " | Tamaño: " + tamañoEnBloques + " bloques | Bloque inicial: " + primerBloque;
    }
}

