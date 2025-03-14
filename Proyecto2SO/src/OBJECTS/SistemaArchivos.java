/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import EDD.ArbolNario;
import EDD.Hashtable;

/**
 * Clase que administra el sistema de archivos completo.
 * Controla la estructura de archivos, directorios y la asignación de bloques en la SD.
 * @author Cristian
 */
public class SistemaArchivos {
    private ArbolNario estructuraArchivos;
    private Hashtable tablaAsignacion;
    private int totalBloques;
    private Bloque[] sd;

    /**
     * Constructor del Sistema de Archivos.
     * @param totalBloques Cantidad total de bloques en la SD.
     */
    public SistemaArchivos(int totalBloques) {
    this.estructuraArchivos = new ArbolNario();
    this.tablaAsignacion = new Hashtable();
    this.totalBloques = totalBloques;
    this.sd = new Bloque[totalBloques];

    // 🔥 Asegurar que cada posición del array está inicializada con un objeto Bloque
    for (int i = 0; i < totalBloques; i++) {
        sd[i] = new Bloque(i);  // Antes faltaba esta inicialización
    }
}

    public int getTotalBloques() {
        return totalBloques;
    }

    public void setTotalBloques(int totalBloques) {
        this.totalBloques = totalBloques;
    }

    public Bloque[] getSd() {
        return sd;
    }

    public void setSd(Bloque[] sd) {
        this.sd = sd;
    }


    public ArbolNario getEstructuraArchivos() {
        return estructuraArchivos;
    }

    public Hashtable getTablaAsignacion() {
        return tablaAsignacion;
    }

    /**
     * Asigna bloques de almacenamiento a un archivo.
     * @param archivo Archivo al que se le asignarán bloques.
     */
    public void asignarBloques(Archivo archivo) {
    int bloquesNecesarios = archivo.getTamañoEnBloques();
    int primerBloque = -1;
    int bloqueAnterior = -1;

    // 🔥 Verificar que hay bloques disponibles antes de asignar
    for (int i = 0; i < totalBloques && bloquesNecesarios > 0; i++) {
        if (sd[i] != null && sd[i].getSiguienteBloque() == -1) {  // Asegurar que el bloque no sea null
            if (primerBloque == -1) primerBloque = i;
            if (bloqueAnterior != -1) sd[bloqueAnterior].setSiguienteBloque(i);
            bloqueAnterior = i;
            bloquesNecesarios--;
        }
    }

    if (bloquesNecesarios > 0) {
        System.out.println("⚠️ No hay suficientes bloques disponibles para asignar el archivo.");
        return; // Salir si no hay suficiente espacio
    }

    archivo.setPrimerBloque(primerBloque);
    tablaAsignacion.insert(archivo.getNombre(), archivo);
}


    /**
     * Método para imprimir la estructura del sistema de archivos.
     */
    public void imprimirSistemaArchivos() {
        estructuraArchivos.imprimirSistemaArchivos();
    }
}

