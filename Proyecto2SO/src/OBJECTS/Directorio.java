/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import EDD.ListaDoble;

/**
 * Clase que representa un directorio dentro del sistema de archivos.
 * Puede contener archivos y subdirectorios.
 * @author Cristian
 */
public class Directorio {
    private String nombre;
    private ListaDoble<Archivo> archivos;
    private ListaDoble<Directorio> subdirectorios;

    /**
     * Constructor de la clase Directorio.
     * @param nombre Nombre del directorio
     */
    public Directorio(String nombre) {
        this.nombre = nombre;
        this.archivos = new ListaDoble<>();
        this.subdirectorios = new ListaDoble<>();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaDoble<Archivo> getArchivos() {
        return archivos;
    }

    public ListaDoble<Directorio> getSubdirectorios() {
        return subdirectorios;
    }

    /**
     * Agrega un archivo al directorio.
     * @param archivo Archivo a agregar
     */
    public void agregarArchivo(Archivo archivo) {
        archivos.insertFinal(archivo);
    }

    /**
     * Agrega un subdirectorio al directorio.
     * @param subdirectorio Directorio a agregar
     */
    public void agregarSubdirectorio(Directorio subdirectorio) {
        subdirectorios.insertFinal(subdirectorio);
    }

    @Override
    public String toString() {
        return "Directorio: " + nombre + " | Archivos: " + archivos.getSize() + " | Subdirectorios: " + subdirectorios.getSize();
    }
}

