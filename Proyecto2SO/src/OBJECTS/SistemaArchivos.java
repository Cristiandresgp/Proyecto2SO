/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OBJECTS;

import EDD.ArbolNario;
import EDD.Hashtable;
import EDD.ListaDoble;
import EDD.NodoArbol;
import EDD.NodoDoble;
import GUI.Simulacion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*; 


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
    private BufferedWriter logWriter;
    
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
    
    if (bloquesAsignados < bloquesNecesarios) {
            liberarBloques(primerBloque);
            simulacion.actualizarVistaSD();
            registrarEvento("Error: No hay suficientes bloques para asignar el archivo '" + archivo.getNombre() + "'");
            return false;
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
    registrarEvento("Archivo '" + archivo.getNombre() + "' asignado con éxito. Bloque inicial: " + primerBloque);
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
    registrarEvento("Bloques liberados a partir del bloque " + primerBloque);
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
    
    
    
    
    public void guardarEstadoEnTxt(String nombreArchivo) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
        writer.write("Estructura del Sistema de Archivos\n");
        writer.write("=================================\n\n");
        registrarEvento("Estado del sistema guardado en " + nombreArchivo);
        guardarNodoEnTxt(writer, estructuraArchivos.getRaiz(), 0);
        writer.write("\nTabla de Asignación de Archivos:\n");
        writer.write("=================================\n");
        guardarTablaAsignacion(writer);
        System.out.println("✅ Estado guardado en: " + nombreArchivo);
    } catch (IOException e) {
        System.out.println("❌ Error al guardar el estado: " + e.getMessage());
        registrarEvento("Error al guardar el estado: " + e.getMessage());
    }
}

private void guardarNodoEnTxt(BufferedWriter writer, NodoArbol nodo, int nivel) throws IOException {
    for (int i = 0; i < nivel; i++) writer.write("  "); // Indentación
    writer.write((nodo.isDirectorio() ? "[D] " : "[A] ") + nodo.getNombre() + "\n");

    NodoDoble<NodoArbol> actual = nodo.getHijos().getHead();
    while (actual != null) {
        guardarNodoEnTxt(writer, actual.getElement(), nivel + 1);
        actual = actual.getNext();
    }
}

private void guardarTablaAsignacion(BufferedWriter writer) throws IOException {
    HashSet<String> archivosGuardados = new HashSet<>(); // Para evitar duplicados

    for (int i = 0; i < tablaAsignacion.getHashSize(); i++) {
        ListaDoble<Archivo> lista = tablaAsignacion.getHashtable()[i];
        if (lista != null && !lista.isEmpty()) {
            NodoDoble<Archivo> actual = lista.getHead();
            while (actual != null) {
                Archivo archivo = actual.getElement();
                if (!archivosGuardados.contains(archivo.getNombre())) { // Evita duplicados
                    writer.write("Archivo: " + archivo.getNombre() +
                                 " | Tamaño: " + archivo.getTamañoEnBloques() +
                                 " bloques | Bloque inicial: " + archivo.getPrimerBloque() + "\n");
                    archivosGuardados.add(archivo.getNombre());
                }
                actual = actual.getNext();
            }
        }
    }
}

public void cargarDesdeTxt(String nombreArchivo) {
    try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
        estructuraArchivos = new ArbolNario(); // Reiniciar la estructura
        tablaAsignacion = new Hashtable(); // Reiniciar la tabla hash
        sd = new Bloque[totalBloques]; // Reiniciar los bloques de memoria

        String linea;
        boolean leyendoArchivos = false;
        NodoArbol nodoActual = estructuraArchivos.getRaiz(); // La raíz ya existe

        while ((linea = reader.readLine()) != null) {
            linea = linea.trim();

            if (linea.isEmpty() || linea.equals("Estructura del Sistema de Archivos") || linea.equals("=================================")) {
                continue;
            }

            if (linea.contains("Tabla de Asignación de Archivos:")) {
                leyendoArchivos = true;
                continue;
            }

            if (!leyendoArchivos) {
                nodoActual = procesarLineaEstructura(linea, nodoActual);
            } else {
                procesarLineaArchivo(linea);
            }
        }

        // 🔥 Asegurar que los nodos cargados sean editables en la UI
        simulacion.actualizarJTree(); 
        registrarEvento("Estado del sistema cargado desde " + nombreArchivo);

        System.out.println("✅ Estado cargado desde: " + nombreArchivo);
    } catch (IOException e) {
        System.out.println("❌ Error al cargar el estado: " + e.getMessage());
        registrarEvento("Error al cargar el estado: " + e.getMessage());
    }
}

private NodoArbol procesarLineaEstructura(String linea, NodoArbol nodoPadre) {
    if (linea.isEmpty()) return nodoPadre;

    int nivel = 0;
    while (linea.startsWith("  ")) {
        nivel++;
        linea = linea.substring(2);
    }

    boolean esDirectorio = linea.startsWith("[D] ");
    String nombre = linea.substring(4); 

    if (nombre.equals("/") && esDirectorio) {
        return estructuraArchivos.getRaiz();
    }

    NodoArbol nuevoNodo = new NodoArbol(nombre, esDirectorio);
    nuevoNodo.setPadre(nodoPadre);  // 🔥 Asignar el padre correctamente
    nodoPadre.agregarHijo(nuevoNodo);

    return esDirectorio ? nuevoNodo : nodoPadre;
}


private void procesarLineaArchivo(String linea) {
    if (linea.isEmpty() || !linea.startsWith("Archivo: ")) return;

    Pattern pattern = Pattern.compile("Archivo: (.+?) \\| Tamaño: (\\d+) bloques \\| Bloque inicial: (\\d+)");
    Matcher matcher = pattern.matcher(linea);

    if (matcher.find()) {
        String nombre = matcher.group(1);
        int tamaño = Integer.parseInt(matcher.group(2));
        int primerBloque = Integer.parseInt(matcher.group(3));

        Archivo archivo = new Archivo(nombre, tamaño, primerBloque);
        tablaAsignacion.insert(nombre, archivo);

        // 🔥 Reservar los bloques en memoria
        asignarBloquesDesdeCarga(archivo, primerBloque, tamaño);
    }
}

private void asignarBloquesDesdeCarga(Archivo archivo, int primerBloque, int tamaño) {
    int bloqueActual = primerBloque;
    for (int i = 0; i < tamaño; i++) {
        if (bloqueActual >= totalBloques) {
            System.out.println("⚠️ Error: Bloque fuera de rango.");
            return;
        }
        sd[bloqueActual] = new Bloque(bloqueActual);
        bloqueActual++;
    }
}

private void registrarEvento(String mensaje) {
    try {
        // Obtener timestamp correctamente
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = ahora.format(formato);

        // Verificar que simulacion no sea null antes de llamar a esAdmin()
        String usuario = (simulacion != null && simulacion.esAdmin()) ? "Administrador" : "Usuario";

        // Mensaje formateado
        String logMensaje = String.format("[%s] [%s] %s", timestamp, usuario, mensaje);

        // Escribir en el archivo de log
        FileWriter fw = new FileWriter("auditoria.log", true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(logMensaje);
        writer.newLine();
        writer.close();

        System.out.println("✅ Log registrado: " + logMensaje);

    } catch (IOException e) {
        System.out.println("❌ Error al escribir en el log: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("⚠️ Error inesperado: " + e.getMessage());
    }




}
}

