/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import EDD.ArbolNario;
import EDD.Hashtable;
import GUI.Simulacion;

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
    private Simulacion simulacion;
    
    /**
     * Constructor del Sistema de Archivos.
     * @param totalBloques Cantidad total de bloques en la SD.
     */
    public SistemaArchivos(int totalBloques, Simulacion simulacion) {
    this.estructuraArchivos = new ArbolNario();
    this.tablaAsignacion = new Hashtable();
    this.totalBloques = totalBloques;
    this.sd = new Bloque[totalBloques];
    this.simulacion = simulacion;

    // 🔥 Inicializar la SD con bloques vacíos (null)
    for (int i = 0; i < totalBloques; i++) {
        sd[i] = null; // ✅ Ahora los bloques estarán vacíos hasta que sean asignados
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
    
    public boolean estaBloqueOcupado(int index) {
    if (index < 0 || index >= totalBloques) {
        return false; // Si el índice está fuera de rango, asumimos que no está ocupado
    }
    return sd[index] != null; // Devuelve true si el bloque está ocupado, false si está libre
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
    public boolean asignarBloques(Archivo archivo) {
    int bloquesNecesarios = archivo.getTamañoEnBloques();
    int primerBloque = -1;
    int bloqueAnterior = -1;
    int bloquesAsignados = 0;

    // 🔥 Buscar bloques libres
    for (int i = 0; i < totalBloques && bloquesAsignados < bloquesNecesarios; i++) {
        if (sd[i] == null) { // ✅ Ahora buscamos bloques libres
            if (primerBloque == -1) primerBloque = i;
            if (bloqueAnterior != -1) sd[bloqueAnterior].setSiguienteBloque(i);
            bloqueAnterior = i;

            sd[i] = new Bloque(i); // ✅ Marcar el bloque como asignado
            bloquesAsignados++;
        }
    }

    // ❌ Si no hay suficientes bloques, liberar los que fueron asignados
    if (bloquesAsignados < bloquesNecesarios) {
        System.out.println("⚠️ No hay suficientes bloques disponibles para asignar el archivo.");
        liberarBloques(primerBloque); // 🔥 Rollback
        simulacion.actualizarVistaSD();
        return false;
    }

    archivo.setPrimerBloque(primerBloque);
    tablaAsignacion.insert(archivo.getNombre(), archivo);
    simulacion.actualizarVistaSD();
    return true; // ✅ Asignación exitosa
}
    
    public void liberarBloques(int primerBloque) {
    int bloqueActual = primerBloque;
    while (bloqueActual != -1 && sd[bloqueActual] != null) {
        int siguiente = sd[bloqueActual].getSiguienteBloque();
        sd[bloqueActual] = null; // ✅ Liberar bloque
        bloqueActual = siguiente;
    }
    
    // 🔥 Notificar a la UI que la memoria se actualizó
    simulacion.actualizarVistaSD();
}


public void actualizarVistaSD() {
    simulacion.actualizarVistaSD();
}




    /**
     * Método para imprimir la estructura del sistema de archivos.
     */
    public void imprimirSistemaArchivos() {
        estructuraArchivos.imprimirSistemaArchivos();
    }
    
    public int contarBloquesDisponibles() {
    int disponibles = 0;
    for (int i = 0; i < totalBloques; i++) {
        if (sd[i] == null) { // ✅ Solo cuenta los bloques libres
            disponibles++;
        }
    }
    return disponibles;
}


}

